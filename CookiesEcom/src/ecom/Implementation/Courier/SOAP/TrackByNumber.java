package ecom.Implementation.Courier.SOAP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import ecom.Interface.Courier.TrackByNumberInterface;
import ecom.common.ConnectionFactory;
import ecom.common.FrequentUse;

public class TrackByNumber implements TrackByNumberInterface {
	
	
	private long orderTableId;
	
	//From Database
	private String trackingNumber;
	private String address;
	private String city;
	private String state;
	private String pin;
	
	
	/**************************** New Instance *************************************/
	
	private TrackByNumber() {}
	
	public static TrackByNumber getNewInstance() {
		return new TrackByNumber();
	}
	
	public boolean getTrackingStatus(long orderTableId) throws UnsupportedOperationException, SOAPException, IOException, ParserConfigurationException, SAXException, ParseException {		
		this.orderTableId = orderTableId;
		getDataForTrackStatus();
		getTrackingStatusXML();
		return false;
	}
	
	/******************************** API 
	 * @throws SOAPException 
	 * @throws UnsupportedOperationException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException ******************************************/
	
	private void getTrackingStatusXML() throws UnsupportedOperationException, SOAPException, IOException, ParserConfigurationException, SAXException, ParseException {
		
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        SOAPMessage soapResponse = null;
        
        String testURL       = "https://wsbeta.fedex.com:443/web-services";        
		String productionURL = "https://ws.fedex.com:443/web-services";
        
		if (FrequentUse.fedExAccountNumber.equals("604501202"))
        	soapResponse = soapConnection.call(soapMessage(), testURL); 
        else if (FrequentUse.fedExAccountNumber.equals("729620904"))
        	soapResponse = soapConnection.call(soapMessage(), productionURL);             
        
        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        soapResponse.writeTo(baout);
        soapConnection.close();
        
        String responseString = new String(baout.toByteArray());                
        responseString = responseString.replaceAll("&lt;", "<");
        responseString = responseString.replaceAll("&gt;", ">");   
        
        System.out.println(responseString);
        
        parseSoapResponseMessage(responseString);    
	}
	
	
	private SOAPMessage soapMessage() throws SOAPException, IOException {
		
		MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        
        String xlns = "v10";
        
        //Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //envelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
        //envelope.addNamespaceDeclaration("xsi",      "http://www.w3.org/2001/XMLSchema-instance");
        //envelope.addNamespaceDeclaration("xsd",      "http://www.w3.org/2001/XMLSchema");
		envelope.addNamespaceDeclaration(xlns,       "http://fedex.com/ws/track/v10");		
        
		//Body
        SOAPBody soapBody = envelope.getBody(); 
        
        
        
        
        
      //RateRequest
        SOAPElement TrackRequest = soapBody.addChildElement("TrackRequest", xlns); 
        
        	//WebAuthenticationDetail
        	SOAPElement WebAuthenticationDetail = TrackRequest.addChildElement("WebAuthenticationDetail", xlns);
        		//UserCredential
        		SOAPElement UserCredential = WebAuthenticationDetail.addChildElement("UserCredential", xlns);
        		
        			SOAPElement Key = UserCredential.addChildElement("Key", xlns);
        			Key.addTextNode(FrequentUse.fedExKey);
        			
        			SOAPElement Password = UserCredential.addChildElement("Password", xlns);
        			Password.addTextNode(FrequentUse.fedExPassword);
        	//ClientDetail
        	SOAPElement ClientDetail = TrackRequest.addChildElement("ClientDetail", xlns);
        	
        		SOAPElement AccountNumber = ClientDetail.addChildElement("AccountNumber", xlns);
        		AccountNumber.addTextNode(FrequentUse.fedExAccountNumber);
        		
        		SOAPElement MeterNumber = ClientDetail.addChildElement("MeterNumber", xlns);
        		MeterNumber.addTextNode(FrequentUse.fedExMeterNumber);
        	//Version
        	SOAPElement	Version = TrackRequest.addChildElement("Version", xlns);
        	
        		SOAPElement ServiceId = Version.addChildElement("ServiceId", xlns);
        		ServiceId.addTextNode("trck");
        		
        		SOAPElement Major = Version.addChildElement("Major", xlns);
        		Major.addTextNode("10");
        		
        		SOAPElement Intermediate = Version.addChildElement("Intermediate", xlns);
        		Intermediate.addTextNode("0");
        		
        		SOAPElement Minor = Version.addChildElement("Minor", xlns);
        		Minor.addTextNode("0");  
        		
        		
        		
        		
        		
        	//SelectionDetails
            SOAPElement	SelectionDetails = TrackRequest.addChildElement("SelectionDetails", xlns);	
        		
            	SOAPElement	CarrierCode = SelectionDetails.addChildElement("CarrierCode", xlns);
            	CarrierCode.addTextNode("FDXE");
            	//PackageIdentifier
            	SOAPElement	PackageIdentifier = SelectionDetails.addChildElement("PackageIdentifier", xlns);
        		
            		SOAPElement	Type = PackageIdentifier.addChildElement("Type", xlns);
            		Type.addTextNode("TRACKING_NUMBER_OR_DOORTAG");
            		
            		SOAPElement	Value = PackageIdentifier.addChildElement("Value", xlns);
            		Value.addTextNode(this.trackingNumber);                             // Tracking Number  
            	//ShipmentAccountNumber
            	SOAPElement	ShipmentAccountNumber = SelectionDetails.addChildElement("ShipmentAccountNumber", xlns);
            	ShipmentAccountNumber.addTextNode(FrequentUse.fedExAccountNumber);     //the account number using which the shipment was created.            
            	//SecureSpodAccount
            	SOAPElement	SecureSpodAccount = SelectionDetails.addChildElement("SecureSpodAccount", xlns);
            	SecureSpodAccount.addTextNode(FrequentUse.fedExAccountNumber);      //SPOD denotes the Signature proof of delivery which FedEx provides for each shipment with the signature of the recipient as a proof of delivery. To get this letter you need to use the account number on which shipment was created.
            	//Destination
            	SOAPElement	Destination = SelectionDetails.addChildElement("Destination", xlns);         //?????????
            	
            		SOAPElement	StreetLines = Destination.addChildElement("StreetLines", xlns);
            		StreetLines.addTextNode(this.address);
            		
            		SOAPElement	City = Destination.addChildElement("City", xlns);
            		City.addTextNode(this.city);
            		
            		SOAPElement	StateOrProvinceCode = Destination.addChildElement("StateOrProvinceCode", xlns);
            		StateOrProvinceCode.addTextNode(this.state);
            		
            		SOAPElement	PostalCode = Destination.addChildElement("PostalCode", xlns);
            		PostalCode.addTextNode(this.pin);
            		
            		SOAPElement	CountryCode = Destination.addChildElement("CountryCode", xlns);
            		CountryCode.addTextNode("IN");
            		
    		//ProcessingOptions
        	SOAPElement ProcessingOptions = TrackRequest.addChildElement("ProcessingOptions", xlns);
        	ProcessingOptions.addTextNode("INCLUDE_DETAILED_SCANS");
            	
		soapMessage.saveChanges();
	       
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        soapMessage.writeTo(stream);
        String message = new String(stream.toByteArray(), "utf-8"); 
        System.out.println(message);     	
            	
            	
        
        return soapMessage;
	}
	
	
	
	private void parseSoapResponseMessage(String soapResponseMessage) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InputSource inputSource = new InputSource(new StringReader(soapResponseMessage));		
		Document document = documentBuilder.parse(inputSource);
		//The Document interface represents the entire HTML or XML document. Conceptually, it is the root of the document tree, and provides the primary access to the document's data.
		//documentBuilder.parse(inputSource) method parses the content of the given input source as an XML document and return a new DOM Document object
		document.getDocumentElement().normalize();
		
		//The normalize() method merges adjacent text() nodes and removes empty ones in the whole document.
		
		//Element foo
	    //Text node: ""
	    //Text node: "Hello "
	    //Text node: "wor"
	    //Text node: "ld"
	    	
	    //Element foo
	    //Text node: "Hello world"
		
		
		
	}
	
	/*************************** Database *********************************************/
	
	private void getDataForTrackStatus() {
		
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;
		String sql = "{call getDataForTrackStatus(?)}";		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);
			callableStatement.setLong(1, this.orderTableId);			
			
			resultSet = callableStatement.executeQuery();
			
			while (resultSet.next()) {
				
				this.trackingNumber = resultSet.getString("track_number");
				
				this.address        = resultSet.getString("address"     );
				this.city           = resultSet.getString("city"        );
				this.state          = resultSet.getString("state"       );
				this.pin            = resultSet.getString("pin"         );
				
			}			
			
			connection.commit();
			System.out.println("SQL - getDataForTrackStatus executed");				
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
		} finally {			
			try { resultSet.close();         } catch (SQLException e) {	e.printStackTrace(); }
			try { callableStatement.close(); } catch (SQLException e) {	e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}	
		
	} // getDataForTrackStatus
	
	
	
	
	
	
	
	
	/*************************** Main 
	 * @throws ParseException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SOAPException 
	 * @throws UnsupportedOperationException *********************************************/

	public static void main(String[] args) throws UnsupportedOperationException, SOAPException, IOException, ParserConfigurationException, SAXException, ParseException {
		
		TrackByNumber trackByNumber = TrackByNumber.getNewInstance();
		trackByNumber.getTrackingStatus(1L);

	}

}
