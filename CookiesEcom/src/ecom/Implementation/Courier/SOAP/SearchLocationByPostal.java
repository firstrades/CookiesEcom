package ecom.Implementation.Courier.SOAP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
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
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import ecom.Interface.Courier.SearchLocationByPostalInterface;
import ecom.common.FrequentUse;

public class SearchLocationByPostal implements SearchLocationByPostalInterface {
	
	//Input
	private long pin;
	
	//Output
	private String highestSeverity;
	private String highestSeverityStAX;
	
	private SearchLocationByPostal() {}
	
	private SearchLocationByPostal(long pin) throws SOAPException, IOException, ParserConfigurationException, SAXException, ParseException, XMLStreamException, FactoryConfigurationError {
		this.pin = pin;
		
		isLocationAvailableXML();
	}	
	
	@Override
	public boolean isLocationAvailable() {
		
		if (this.highestSeverityStAX.equals("SUCCESS"))
			return true;
		else if (this.highestSeverityStAX.equals("ERROR"))
			return false;
		else 
			return false;
	}
	

	/************************************  New Instance  
	 * @throws ParseException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SOAPException 
	 * @throws FactoryConfigurationError 
	 * @throws XMLStreamException ******************************************************************/

	public static SearchLocationByPostal getNewInstance(long pin) throws SOAPException, IOException, ParserConfigurationException, SAXException, ParseException, XMLStreamException, FactoryConfigurationError {		
		return new SearchLocationByPostal(pin);
	}
	
	
	/************************** Soap XML 
	 * @throws FactoryConfigurationError 
	 * @throws XMLStreamException **********************************/	
	
	
	private void isLocationAvailableXML() throws SOAPException, IOException, ParserConfigurationException, SAXException, ParseException, XMLStreamException, FactoryConfigurationError {
		
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection               = soapConnectionFactory.createConnection();
        SOAPMessage soapResponse                    = null;
        
        String testURL       = "https://wsbeta.fedex.com:443/web-services";
        String productionURL = "https://ws.fedex.com:443/web-services";
        
        if (FrequentUse.fedExAccountNumber.equals(FrequentUse.TestAccountNumber))
        	soapResponse = soapConnection.call(soapMessage(), testURL); 
        else if (FrequentUse.fedExAccountNumber.equals(FrequentUse.ProductionAccountNumber))
        	soapResponse = soapConnection.call(soapMessage(), productionURL); 
        	
        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        soapResponse.writeTo(baout);
        soapConnection.close();
        
        String responseString = new String(baout.toByteArray());                
        responseString        = responseString.replaceAll("&lt;", "<");
        responseString        = responseString.replaceAll("&gt;", ">");         
        
        System.out.println(responseString);
        
        
        //Parse
        parseSoapResponseMessageDOM (responseString);        
		parseSoapResponseMessageStAX(responseString);
		
        
	}
	
	private SOAPMessage soapMessage() throws SOAPException, IOException {
		
		MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage       = messageFactory.createMessage();
        SOAPPart soapPart             = soapMessage.getSOAPPart();
        
        String v3 = "v3";
        
        //Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //envelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
        //envelope.addNamespaceDeclaration("xsi",      "http://www.w3.org/2001/XMLSchema-instance");
        //envelope.addNamespaceDeclaration("xsd",      "http://www.w3.org/2001/XMLSchema");
		envelope.addNamespaceDeclaration  (v3,         "http://fedex.com/ws/locs/v3");		
        
		//Body
        SOAPBody soapBody = envelope.getBody(); 
        
        //SearchLocationsRequest
        SOAPElement SearchLocationsRequest = soapBody.addChildElement("SearchLocationsRequest", v3); 
        
        	//WebAuthenticationDetail
        	SOAPElement WebAuthenticationDetail = SearchLocationsRequest.addChildElement("WebAuthenticationDetail", v3);
        		//UserCredential
        		SOAPElement UserCredential = WebAuthenticationDetail.addChildElement("UserCredential", v3);
        		
        			SOAPElement Key = UserCredential.addChildElement("Key", v3);
        			Key.addTextNode(FrequentUse.fedExKey);
        			
        			SOAPElement Password = UserCredential.addChildElement("Password", v3);
        			Password.addTextNode(FrequentUse.fedExPassword);
        	//ClientDetail
        	SOAPElement ClientDetail = SearchLocationsRequest.addChildElement("ClientDetail", v3);
        	
        		SOAPElement AccountNumber = ClientDetail.addChildElement("AccountNumber", v3);
        		AccountNumber.addTextNode(FrequentUse.fedExAccountNumber);
        		
        		SOAPElement MeterNumber = ClientDetail.addChildElement("MeterNumber", v3);
        		MeterNumber.addTextNode(FrequentUse.fedExMeterNumber);
        		
        		//<v3:Region>US</v3:Region>
        		
        	//Version
        	SOAPElement	Version = SearchLocationsRequest.addChildElement("Version", v3);
        	
        		SOAPElement ServiceId = Version.addChildElement("ServiceId", v3);
        		ServiceId.addTextNode("locs");
        		
        		SOAPElement Major = Version.addChildElement("Major", v3);
        		Major.addTextNode("3");
        		
        		SOAPElement Intermediate = Version.addChildElement("Intermediate", v3);
        		Intermediate.addTextNode("0");
        		
        		SOAPElement Minor = Version.addChildElement("Minor", v3);
        		Minor.addTextNode("0");
        		
        		
        		
        		
        		
        		
        		
        	//ReturnTransitAndCommit
        	SOAPElement	LocationsSearchCriterion = SearchLocationsRequest.addChildElement("LocationsSearchCriterion", v3);
        	LocationsSearchCriterion.addTextNode("ADDRESS");
        		
        	//RequestedShipment	
        	SOAPElement Address = SearchLocationsRequest.addChildElement("Address", v3);        	
        		
        		SOAPElement PostalCode = Address.addChildElement("PostalCode", v3);
        		PostalCode.addTextNode(String.valueOf(this.pin));
        		
        		SOAPElement CountryCode = Address.addChildElement("CountryCode", v3);
        		CountryCode.addTextNode("IN");
        		
        		  		
        
        soapMessage.saveChanges();
       
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        soapMessage.writeTo(stream);
        String message = new String(stream.toByteArray(), "utf-8"); 
        System.out.println(message); 
        
		return soapMessage;
	}
	
	private void parseSoapResponseMessageDOM(String soapResponseMessage) throws ParserConfigurationException, SAXException, IOException, ParseException {		
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder               = documentBuilderFactory.newDocumentBuilder();
		InputSource inputSource                       = new InputSource(new StringReader(soapResponseMessage));
		Document document                             = documentBuilder.parse(inputSource);		
		document.getDocumentElement().normalize();
		
		Element highestSeverity = (Element) document.getElementsByTagName("HighestSeverity").item(0);	
		
		this.highestSeverity = highestSeverity.getTextContent();
		System.out.println(this.highestSeverity);
		
	}
	
	
	private void parseSoapResponseMessageStAX(String soapResponseMessage) throws XMLStreamException, FactoryConfigurationError {		
		
		InputStream inputStream         = new ByteArrayInputStream(soapResponseMessage.getBytes());		
		XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
		
		boolean isHighestSeverity = false, endLoop = true;		
		
		while (xmlStreamReader.hasNext() && endLoop == true) {
			
			switch (xmlStreamReader.next()) {				
			
				case XMLStreamConstants.START_ELEMENT  :
					if (xmlStreamReader.getLocalName().equals("HighestSeverity")) {
						isHighestSeverity = true;
					}
					break;
	
				case XMLStreamConstants.CHARACTERS     :
					if (isHighestSeverity) {
						this.highestSeverityStAX = xmlStreamReader.getText();    System.out.println(this.highestSeverityStAX);
						endLoop = false;
					}
					break;					
				
			} // switch
			
			if (!endLoop) {
				System.out.println("Out of Loop!");
				//break;
			}
			
		} // while
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		
		SearchLocationByPostal searchLocationByPostal = null;
		boolean status = false;
		
		try {
			searchLocationByPostal = SearchLocationByPostal.getNewInstance(700067);
			
			status = searchLocationByPostal.isLocationAvailable();		
			
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(status);
		
	}

}

//1.       122504—BHIWADI- Rajasthan(RJ)
//2.       174102—SOLAN- Himachal Pradesh (HP)
