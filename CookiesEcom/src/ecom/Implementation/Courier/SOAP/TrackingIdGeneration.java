package ecom.Implementation.Courier.SOAP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.Calendar;

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

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import ecom.Interface.Courier.TrackingIdGenerationInterface;
import ecom.common.ConnectionFactory;
import ecom.common.FrequentUse;

public class TrackingIdGeneration implements TrackingIdGenerationInterface {	
	
	private long   orderTableId;	
	private String paymentType;
	
	private String highestSeverity;
	
	//From Database
	private String shipperContact;
	private String shipperAddress;
	private String shipperAddress1;
	private String shipperCity;
	private String shipperState;
	private String shipperPin;
	private String shipperFName;
	private String shipperLName;
	private String shipperEmail;
	private String shipperCompany;
	
	private String recipientContact;
	private String recipientAddress;
	private String recipientAddress1;
	private String recipientCity;
	private String recipientState;
	private String recipientPin;
	private String recipientFName;
	private String recipientLName;
	private String recipientEmail;
	private String recipientCompany;
	
	private double sellPrice;
	private double shippingCost;
	private int    qtyOrdered;
	
	private String productName;
	private String companyName;
	
	private double weight;
	
	private String orderId;
	
	//Into Database
	private String trackingIdType;
	private String formId;
	private String trackingNumber;	
	private String[] image;	
	
	
	/**************************** New Instance *************************************/
	
	private TrackingIdGeneration() {
		image = new String[2];
	}
	
	public static TrackingIdGeneration getNewInstance() {
		return new TrackingIdGeneration();
	}
	
	@Override
	public boolean getTrackingNumber(long orderTableId, String paymentType) throws SOAPException, IOException, ParserConfigurationException, SAXException, ParseException {
		this.orderTableId = orderTableId;
		this.paymentType  = paymentType;
		getDataForTrackNumberGeneration();
		getTrackIdAndInvoiceXML();		
		
		if (this.highestSeverity.toUpperCase().equals("SUCCESS"))
			return setTrackNumberIntoDatabase();
		else
			return false;  //ERROR
	}
	
	/******************************** API ******************************************/	
	
	private void getTrackIdAndInvoiceXML() throws SOAPException, IOException, ParserConfigurationException, SAXException, ParseException {
		
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
        
        String xlns = "v17";
        
        //Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
        envelope.addNamespaceDeclaration("xsi",      "http://www.w3.org/2001/XMLSchema-instance");
        envelope.addNamespaceDeclaration("xsd",      "http://www.w3.org/2001/XMLSchema");
		envelope.addNamespaceDeclaration(xlns,       "http://fedex.com/ws/ship/v17");		
        
		//Body
        SOAPBody soapBody = envelope.getBody(); 
        
        //RateRequest
        SOAPElement ProcessShipmentRequest = soapBody.addChildElement("ProcessShipmentRequest", xlns); 
        
        	//WebAuthenticationDetail
        	SOAPElement WebAuthenticationDetail = ProcessShipmentRequest.addChildElement("WebAuthenticationDetail", xlns);
        		//UserCredential
        		SOAPElement UserCredential = WebAuthenticationDetail.addChildElement("UserCredential", xlns);
        		
        			SOAPElement Key = UserCredential.addChildElement("Key", xlns);
        			Key.addTextNode(FrequentUse.fedExKey);
        			
        			SOAPElement Password = UserCredential.addChildElement("Password", xlns);
        			Password.addTextNode(FrequentUse.fedExPassword);
        	//ClientDetail
        	SOAPElement ClientDetail = ProcessShipmentRequest.addChildElement("ClientDetail", xlns);
        	
        		SOAPElement AccountNumber = ClientDetail.addChildElement("AccountNumber", xlns);
        		AccountNumber.addTextNode(FrequentUse.fedExAccountNumber);
        		
        		SOAPElement MeterNumber = ClientDetail.addChildElement("MeterNumber", xlns);
        		MeterNumber.addTextNode(FrequentUse.fedExMeterNumber);
        	//Version
        	SOAPElement	Version = ProcessShipmentRequest.addChildElement("Version", xlns);
        	
        		SOAPElement ServiceId = Version.addChildElement("ServiceId", xlns);
        		ServiceId.addTextNode("ship");
        		
        		SOAPElement Major = Version.addChildElement("Major", xlns);
        		Major.addTextNode("17");
        		
        		SOAPElement Intermediate = Version.addChildElement("Intermediate", xlns);
        		Intermediate.addTextNode("0");
        		
        		SOAPElement Minor = Version.addChildElement("Minor", xlns);
        		Minor.addTextNode("0");       		
        		
        		
        		
        	
        		
        	//RequestedShipment	
        	SOAPElement RequestedShipment = ProcessShipmentRequest.addChildElement("RequestedShipment", xlns);
        	
        		SOAPElement ShipTimestamp = RequestedShipment.addChildElement("ShipTimestamp", xlns);   
        		Calendar calendar = Calendar.getInstance();
        		int    YEAR  = calendar.get(Calendar.YEAR);
        		Object MONTH = (  calendar.get(Calendar.MONTH)+1 < 10 ? "0" + (calendar.get(Calendar.MONTH)+1) : calendar.get(Calendar.MONTH)   );
        		Object DATE  = (  calendar.get(Calendar.DATE) < 10 ? "0" + (calendar.get(Calendar.DATE)) : (calendar.get(Calendar.DATE))  );
        		ShipTimestamp.addTextNode(YEAR + "-" + MONTH.toString() + "-" + DATE.toString() + "T12:34:56-06:00");
        		
        		SOAPElement DropoffType = RequestedShipment.addChildElement("DropoffType", xlns);
        		DropoffType.addTextNode("REGULAR_PICKUP");
        		
        		SOAPElement ServiceType = RequestedShipment.addChildElement("ServiceType", xlns);
        		ServiceType.addTextNode("FEDEX_EXPRESS_SAVER");
        		
        		SOAPElement PackagingType = RequestedShipment.addChildElement("PackagingType", xlns);
        		PackagingType.addTextNode("YOUR_PACKAGING");
        		
        		
        		//Shipper
        		SOAPElement Shipper = RequestedShipment.addChildElement("Shipper", xlns);
        		
        			SOAPElement Contact = Shipper.addChildElement("Contact", xlns);                  // Seller Info
        			
        				SOAPElement PersonName = Contact.addChildElement("PersonName", xlns);
        				PersonName.addTextNode(this.shipperFName + " " + this.shipperLName);
        				
        				SOAPElement CompanyName = Contact.addChildElement("CompanyName", xlns);
        				CompanyName.addTextNode(this.shipperCompany);
        				
        				SOAPElement PhoneNumber = Contact.addChildElement("PhoneNumber", xlns);
        				PhoneNumber.addTextNode(this.shipperContact);
        				
        				SOAPElement EMailAddress = Contact.addChildElement("EMailAddress", xlns);
        				EMailAddress.addTextNode(this.shipperEmail);
        		
        			SOAPElement Address = Shipper.addChildElement("Address", xlns);
        		
        				SOAPElement StreetLines11 = Address.addChildElement("StreetLines", xlns);
        				StreetLines11.addTextNode(this.shipperAddress);
        				
        				SOAPElement StreetLines12 = Address.addChildElement("StreetLines", xlns);
        				StreetLines12.addTextNode(this.shipperAddress1);
        				
        				SOAPElement City = Address.addChildElement("City", xlns);
        				City.addTextNode(this.shipperCity);
        				
        				SOAPElement StateOrProvinceCode = Address.addChildElement("StateOrProvinceCode", xlns);
        				StateOrProvinceCode.addTextNode(this.shipperState);
        				
        				SOAPElement PostalCode = Address.addChildElement("PostalCode", xlns);
        				PostalCode.addTextNode(this.shipperPin);
        				
        				SOAPElement CountryCode = Address.addChildElement("CountryCode", xlns);
        				CountryCode.addTextNode("IN");
        				
        				SOAPElement Residential = Address.addChildElement("Residential", xlns);   //0 - non  residential (optional)
        				Residential.addTextNode("0");
        				
				//Recipient
        		SOAPElement Recipient = RequestedShipment.addChildElement("Recipient", xlns);
        		
        			SOAPElement Contact1 = Recipient.addChildElement("Contact", xlns);             // Customer Info
    			
						SOAPElement PersonName1 = Contact1.addChildElement("PersonName", xlns);
						PersonName1.addTextNode(this.recipientFName + " " + this.recipientLName);
						
						SOAPElement CompanyName1 = Contact1.addChildElement("CompanyName", xlns);
						CompanyName1.addTextNode(this.recipientCompany);
						
						SOAPElement PhoneNumber1 = Contact1.addChildElement("PhoneNumber", xlns);
						PhoneNumber1.addTextNode(this.recipientContact);
						
						SOAPElement EMailAddress1 = Contact1.addChildElement("EMailAddress", xlns);
						EMailAddress1.addTextNode(this.recipientEmail);
        		
        			SOAPElement Address1 = Recipient.addChildElement("Address", xlns);
        		
        				SOAPElement StreetLines21 = Address1.addChildElement("StreetLines", xlns);
        				StreetLines21.addTextNode(this.recipientAddress);
        				
        				SOAPElement StreetLines22 = Address1.addChildElement("StreetLines", xlns);
        				StreetLines22.addTextNode(this.recipientAddress1);
        				
        				SOAPElement City1 = Address1.addChildElement("City", xlns);
        				City1.addTextNode(this.recipientCity);
        				
        				SOAPElement StateOrProvinceCode1 = Address1.addChildElement("StateOrProvinceCode", xlns);
        				StateOrProvinceCode1.addTextNode(this.recipientState);
        				
        				SOAPElement PostalCode1 = Address1.addChildElement("PostalCode", xlns);
        				PostalCode1.addTextNode(this.recipientPin);
        				
        				SOAPElement CountryCode1 = Address1.addChildElement("CountryCode", xlns);
        				CountryCode1.addTextNode("IN");
        				
        				SOAPElement Residential1 = Address1.addChildElement("Residential", xlns);
        				Residential1.addTextNode("0");
        				
        				
        		//ShippingChargesPayment	(Payment will be charged to Admin, AccountNumber of Admin, Address is of Seller	
        		SOAPElement ShippingChargesPayment = RequestedShipment.addChildElement("ShippingChargesPayment", xlns);
        		
        			SOAPElement PaymentType = ShippingChargesPayment.addChildElement("PaymentType", xlns);
        			PaymentType.addTextNode("SENDER");
        			
        			SOAPElement Payor = ShippingChargesPayment.addChildElement("Payor", xlns);
        			
        				SOAPElement ResponsibleParty = Payor.addChildElement("ResponsibleParty", xlns); 
        				
        					SOAPElement AccountNumber1 = ResponsibleParty.addChildElement("AccountNumber", xlns);
        					AccountNumber1.addTextNode(FrequentUse.fedExAccountNumber);
        					
        					SOAPElement Contact2 = ResponsibleParty.addChildElement("Contact", xlns);          // Seller Info
        	    			
	    						SOAPElement PersonName2 = Contact2.addChildElement("PersonName", xlns);
	    						PersonName2.addTextNode(this.shipperFName + "" + this.shipperLName);
	    						
	    						SOAPElement CompanyName2 = Contact2.addChildElement("CompanyName", xlns);
	    						CompanyName2.addTextNode(this.shipperCompany);
	    						
	    						SOAPElement PhoneNumber2 = Contact2.addChildElement("PhoneNumber", xlns);
	    						PhoneNumber2.addTextNode(this.shipperContact);	    						
	    						
            		
	            			SOAPElement Address2 = ResponsibleParty.addChildElement("Address", xlns);
	            		
	            				SOAPElement StreetLines2 = Address2.addChildElement("StreetLines", xlns);
	            				StreetLines2.addTextNode(this.shipperAddress);
	            				
	            				SOAPElement StreetLines3 = Address2.addChildElement("StreetLines", xlns);
	            				StreetLines3.addTextNode(this.shipperAddress1);
	            				
	            				SOAPElement City2 = Address2.addChildElement("City", xlns);
	            				City2.addTextNode(this.shipperCity);
	            				
	            				SOAPElement StateOrProvinceCode2 = Address2.addChildElement("StateOrProvinceCode", xlns);
	            				StateOrProvinceCode2.addTextNode(this.shipperState);
	            				
	            				SOAPElement PostalCode2 = Address2.addChildElement("PostalCode", xlns);
	            				PostalCode2.addTextNode(this.shipperPin);
	            				
	            				SOAPElement CountryCode2 = Address2.addChildElement("CountryCode", xlns);
	            				CountryCode2.addTextNode("IN");
        					
	            				SOAPElement Residential2 = Address2.addChildElement("Residential", xlns);
	            				Residential2.addTextNode("0");
        					
        		/*************************************************************************************************************/	
        			
	            if (this.paymentType.equals("COD")) { 				
	            				
    			//SpecialServicesRequested
        		SOAPElement SpecialServicesRequested = RequestedShipment.addChildElement("SpecialServicesRequested", xlns);
        		
        			SOAPElement SpecialServiceTypes1 = SpecialServicesRequested.addChildElement("SpecialServiceTypes", xlns);
        			SpecialServiceTypes1.addTextNode("DELIVERY_ON_INVOICE_ACCEPTANCE");
        		
        			 
        		
        			SOAPElement SpecialServiceTypes = SpecialServicesRequested.addChildElement("SpecialServiceTypes", xlns);
        			SpecialServiceTypes.addTextNode("COD");        			
        			
        			SOAPElement CodDetail = SpecialServicesRequested.addChildElement("CodDetail", xlns);
        			
        				SOAPElement CodCollectionAmount = CodDetail.addChildElement("CodCollectionAmount", xlns);
        				
        					SOAPElement Currency1 = CodCollectionAmount.addChildElement("Currency", xlns);
        					Currency1.addTextNode("INR");
        					
        					SOAPElement Amount1 = CodCollectionAmount.addChildElement("Amount", xlns);
        					Amount1.addTextNode(String.valueOf((this.sellPrice + this.shippingCost) * this.qtyOrdered));    //Amount
        					
        				SOAPElement CollectionType = CodDetail.addChildElement("CollectionType", xlns);
        				CollectionType.addTextNode("CASH");        				
        			
        					
        			SOAPElement DeliveryOnInvoiceAcceptanceDetail = SpecialServicesRequested.addChildElement("DeliveryOnInvoiceAcceptanceDetail", xlns);
        					
	        			SOAPElement Recipient1 = DeliveryOnInvoiceAcceptanceDetail.addChildElement("Recipient", xlns); // customer
	            		
		        			SOAPElement Contact3 = Recipient1.addChildElement("Contact", xlns);
		    			
								SOAPElement PersonName3 = Contact3.addChildElement("PersonName", xlns);
								PersonName3.addTextNode(this.recipientFName + " " + this.recipientLName);
								
								SOAPElement CompanyName3 = Contact3.addChildElement("CompanyName", xlns);
								CompanyName3.addTextNode(this.recipientCompany);
								
								SOAPElement PhoneNumber3 = Contact3.addChildElement("PhoneNumber", xlns);
								PhoneNumber3.addTextNode(this.recipientContact);							
		        		
		        			SOAPElement Address3 = Recipient1.addChildElement("Address", xlns);
		        		
		        				SOAPElement StreetLines4 = Address3.addChildElement("StreetLines", xlns);
		        				StreetLines4.addTextNode(this.recipientAddress);
		        				
		        				SOAPElement StreetLines5 = Address3.addChildElement("StreetLines", xlns);
		        				StreetLines5.addTextNode(this.recipientAddress1);
		        				
		        				SOAPElement City3 = Address3.addChildElement("City", xlns);
		        				City3.addTextNode(this.recipientCity);
		        				
		        				SOAPElement StateOrProvinceCode3 = Address3.addChildElement("StateOrProvinceCode", xlns);
		        				StateOrProvinceCode3.addTextNode(this.recipientState);
		        				
		        				SOAPElement PostalCode3 = Address3.addChildElement("PostalCode", xlns);
		        				PostalCode3.addTextNode(this.recipientPin);
		        				
		        				SOAPElement CountryCode3 = Address3.addChildElement("CountryCode", xlns);
		        				CountryCode3.addTextNode("IN");		
        		
	            } // end if (COD)
        			
        		//CustomsClearanceDetail
        		SOAPElement CustomsClearanceDetail = RequestedShipment.addChildElement("CustomsClearanceDetail", xlns);
        		
        			SOAPElement DocumentContent = CustomsClearanceDetail.addChildElement("DocumentContent", xlns);
        			DocumentContent.addTextNode("DOCUMENTS_ONLY");
        			
        			SOAPElement CustomsValue = CustomsClearanceDetail.addChildElement("CustomsValue", xlns);
        			
        				SOAPElement Currency = CustomsValue.addChildElement("Currency", xlns);
        				Currency.addTextNode("INR");
        				
        				SOAPElement Amount = CustomsValue.addChildElement("Amount", xlns);
        				Amount.addTextNode(String.valueOf((this.sellPrice + this.shippingCost) * this.qtyOrdered));   //Amount
        				
        			SOAPElement CommercialInvoice = CustomsClearanceDetail.addChildElement("CommercialInvoice", xlns); // required for transit (roads)
        			
        				SOAPElement Purpose = CommercialInvoice.addChildElement("Purpose", xlns);
        				Purpose.addTextNode("SOLD");
        				
        				
        			SOAPElement Commodities = CustomsClearanceDetail.addChildElement("Commodities", xlns);
            			
        				SOAPElement NumberOfPieces = Commodities.addChildElement("NumberOfPieces", xlns);         //Count
        				NumberOfPieces.addTextNode(String.valueOf(this.qtyOrdered));
        				
        				SOAPElement Description = Commodities.addChildElement("Description", xlns);               
        				Description.addTextNode(this.productName + "(" + this.companyName + ")");
        				
        				SOAPElement CountryOfManufacture = Commodities.addChildElement("CountryOfManufacture", xlns);
        				CountryOfManufacture.addTextNode("IN");
        				
        				SOAPElement Weight1 = Commodities.addChildElement("Weight", xlns);                     
        				
        					SOAPElement Units1 = Weight1.addChildElement("Units", xlns);
        					Units1.addTextNode("KG");
        					
        					SOAPElement Value2 = Weight1.addChildElement("Value", xlns);                         //Weight
        					Value2.addTextNode(String.valueOf(this.weight * this.qtyOrdered));
        					
        				SOAPElement Quantity = Commodities.addChildElement("Quantity", xlns);                    //Count
        				Quantity.addTextNode(String.valueOf(this.qtyOrdered));
        				
        				SOAPElement QuantityUnits = Commodities.addChildElement("QuantityUnits", xlns);
        				QuantityUnits.addTextNode("EA"); // EA = each
        				
        				SOAPElement UnitPrice = Commodities.addChildElement("UnitPrice", xlns);
        				
        					SOAPElement Currency2 = UnitPrice.addChildElement("Currency", xlns);
        					Currency2.addTextNode("INR");
        					
        					SOAPElement Amount2 = UnitPrice.addChildElement("Amount", xlns);
        					Amount2.addTextNode(String.valueOf(this.sellPrice + this.shippingCost));
        				
        				SOAPElement CustomsValue1 = Commodities.addChildElement("CustomsValue", xlns);           //Amount
        				
	        				SOAPElement Currency3 = CustomsValue1.addChildElement("Currency", xlns);
	    					Currency3.addTextNode("INR");
        					
	    					SOAPElement Amount3 = CustomsValue1.addChildElement("Amount", xlns);
        					Amount3.addTextNode(String.valueOf((this.sellPrice + this.shippingCost) * this.qtyOrdered));
        				
        			
        		//LabelSpecification
				SOAPElement LabelSpecification = RequestedShipment.addChildElement("LabelSpecification", xlns);
        			
					SOAPElement LabelFormatType = LabelSpecification.addChildElement("LabelFormatType", xlns);
					LabelFormatType.addTextNode("COMMON2D"); //Common 2D contains the FedEx 2D barcode in encrypted format.
					
					SOAPElement ImageType = LabelSpecification.addChildElement("ImageType", xlns);
					ImageType.addTextNode("PDF");
					
					SOAPElement LabelStockType = LabelSpecification.addChildElement("LabelStockType", xlns);
					LabelStockType.addTextNode("PAPER_8.5X11_TOP_HALF_LABEL");
					
					
				//RateRequestTypes
				SOAPElement RateRequestTypes = RequestedShipment.addChildElement("RateRequestTypes", xlns);
				RateRequestTypes.addTextNode("NONE");
				
        		
        		//PackageCount
        		SOAPElement PackageCount = RequestedShipment.addChildElement("PackageCount", xlns);                //Count
        		//PackageCount.addTextNode(String.valueOf(this.qtyOrdered));  // In case two seperate pakages
        		PackageCount.addTextNode(String.valueOf("1"));
        		
        		//RequestedPackageLineItems
        		SOAPElement RequestedPackageLineItems = RequestedShipment.addChildElement("RequestedPackageLineItems", xlns);
        		
	        		//SOAPElement SequenceNumber = RequestedPackageLineItems.addChildElement("SequenceNumber", xlns);     //Required for More than one PackageCount
	        		//SequenceNumber.addTextNode("1");
        		
        			SOAPElement GroupPackageCount = RequestedPackageLineItems.addChildElement("GroupPackageCount", xlns);     //Count
        			GroupPackageCount.addTextNode("1");
        			
        			SOAPElement Weight = RequestedPackageLineItems.addChildElement("Weight", xlns);               //Weight
        			
        				SOAPElement Units = Weight.addChildElement("Units", xlns);
        				Units.addTextNode("KG");
        				
        				SOAPElement Value = Weight.addChildElement("Value", xlns);
        				Value.addTextNode(String.valueOf(this.weight * this.qtyOrdered));  // this is (this.weight * this.qtyOrdered) * PackageCount
        				
        				
    				SOAPElement CustomerReferences = RequestedPackageLineItems.addChildElement("CustomerReferences", xlns);
    				
    					SOAPElement CustomerReferenceType = CustomerReferences.addChildElement("CustomerReferenceType", xlns);
    					CustomerReferenceType.addTextNode("CUSTOMER_REFERENCE");
    					
    					SOAPElement Value1 = CustomerReferences.addChildElement("Value", xlns);
    					Value1.addTextNode(this.orderId);  // order_table --> order_id  starts with 'J'
        				
        		
        		
        		
        
        soapMessage.saveChanges();
       
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        soapMessage.writeTo(stream);
        String message = new String(stream.toByteArray(), "utf-8"); 
        System.out.println(message); //-----------------------------------------------------////////////////////////////////
        
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
		
		/************************ <HighestSeverity>SUCCESS</HighestSeverity> *******************/
		NodeList HighestSeveritys = document.getElementsByTagName("HighestSeverity");
		
		for (int i = 0; i < HighestSeveritys.getLength(); i++) {
			
			Node HighestSeverityNode = HighestSeveritys.item(i);
			
			//Image
			Node HighestSeverity     = HighestSeverityNode.getFirstChild();	
			
			if (HighestSeverity instanceof CharacterData) {				
				this.highestSeverity = ((CharacterData) HighestSeverity).getData();  System.out.println(this.highestSeverity);
			}			
			
		}
		
		/************************* Tracking Id **************************************************/
		NodeList TrackingIds = document.getElementsByTagName("TrackingIds");		
		
		for (int i = 0; i < TrackingIds.getLength(); i++) {
			
			Node TrackingIdsNode = TrackingIds.item(i);
			
			//TrackingIdType
			Node TrackingIdType     = TrackingIdsNode.getFirstChild();	
			Node TrackingIdTypeText = TrackingIdType.getFirstChild();
			if (TrackingIdTypeText instanceof CharacterData) {				
				this.trackingIdType = ((CharacterData) TrackingIdTypeText).getData();  
			}
			
			//FormId
			Node FormId = TrackingIdType.getNextSibling();		
			Node FormIdText = FormId.getFirstChild();
			if (FormIdText instanceof CharacterData) {				
				this.formId = ((CharacterData) FormIdText).getData();  
			}
			
			//TrackingNumber
			Node TrackingNumber = TrackingIdsNode.getLastChild();
			Node TrackingNumberText = TrackingNumber.getFirstChild();
			if (TrackingNumberText instanceof CharacterData) {				
				this.trackingNumber = ((CharacterData) TrackingNumberText).getData();  
			}
		}
		
		System.out.println(this.trackingIdType);
		System.out.println(this.formId);
		System.out.println(this.trackingNumber);
		
		/**************************** PDF Image (Laser Printer) ***********************************************/
		//Other option is PNG for Thermal Printer
		NodeList ImageList = document.getElementsByTagName("Image");
		System.out.println("ImageList length: " + ImageList.getLength());
		
		for (int i = 0; i < ImageList.getLength(); i++) {
			
			Node ImageNode = ImageList.item(i);
			
			//Image
			Node Image     = ImageNode.getFirstChild();	   	
			
			if (Image instanceof CharacterData) {				
				this.image[i] = ((CharacterData) Image).getData();  //System.out.println(this.image[i]);
			}					
			
		}
		
		
	}
	
	
	/*************************** Database *********************************************/
	
	private void getDataForTrackNumberGeneration() {
		
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;
		String sql = "{call getDataForTrackNumberGeneration(?)}";		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);
			callableStatement.setLong(1, this.orderTableId);			
			
			resultSet = callableStatement.executeQuery();
			
			while (resultSet.next()) {
				
				this.orderId          = resultSet.getString("order_id"        );    // Starts with 'J'
				
				this.shipperContact   = resultSet.getString("da_contact"      );
				this.shipperAddress   = resultSet.getString("da_address"      );
				this.shipperAddress1  = resultSet.getString("da_address1"     );
				this.shipperCity      = resultSet.getString("da_city"         );
				this.shipperState     = resultSet.getString("da_state"        );
				this.shipperPin       = resultSet.getString("da_pin"          );
				this.shipperFName     = resultSet.getString("da_fName"        );
				this.shipperLName     = resultSet.getString("da_lName"        );
				this.shipperEmail     = resultSet.getString("da_email"        );
				this.shipperState     = resultSet.getString("da_state"        );
				this.shipperCompany   = resultSet.getString("seller_company"  );
				
				this.recipientContact = resultSet.getString("contact"         );
				this.recipientAddress = resultSet.getString("address"         );
				this.recipientAddress1= resultSet.getString("address1"        );
				this.recipientCity    = resultSet.getString("city"            );
				this.recipientState   = resultSet.getString("state"           );
				this.recipientPin     = resultSet.getString("pin"             );
				this.recipientFName   = resultSet.getString("first_name"      );
				this.recipientLName   = resultSet.getString("last_name"       );
				this.recipientEmail   = resultSet.getString("email"           );
				this.recipientState   = resultSet.getString("state"           );
				this.recipientCompany = resultSet.getString("customer_company");
				
				this.sellPrice        = resultSet.getDouble("sell_price"      );
				this.shippingCost     = resultSet.getDouble("shipping_cost"   );
				this.qtyOrdered       = resultSet.getInt   ("qty"             );
				
				this.productName      = resultSet.getString("product_name"    );
				this.companyName      = resultSet.getString("company_name"    );
				
				this.weight           = resultSet.getDouble("weight"          );
				
			}			
			
			connection.commit();
			System.out.println("SQL - getDataForTrackNumberGeneration executed");				
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
		} finally {			
			try { resultSet.close();         } catch (SQLException e) {	e.printStackTrace(); }
			try { callableStatement.close(); } catch (SQLException e) {	e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}	
		
	} // getDataForTrackNumberGeneration
	
	private boolean setTrackNumberIntoDatabase() {
		
		Connection connection = null; CallableStatement callableStatement = null; 
		String sql = "{call setTrackNumberIntoDatabase(?,?,?,?,?,?,?)}";	
		boolean status = false;
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);
			
			callableStatement.registerOutParameter(1, Types.BOOLEAN);
			callableStatement.setString(2, this.trackingIdType);
			callableStatement.setString(3, this.formId        );
			callableStatement.setString(4, this.trackingNumber);
			callableStatement.setString(5, this.image[0]      );
			callableStatement.setString(6, this.image[1]      );
			callableStatement.setLong  (7, this.orderTableId  );
			
			callableStatement.execute();
			
			status = callableStatement.getBoolean(1);
			
			connection.commit();
			System.out.println("SQL - setTrackNumberIntoDatabase executed");		
			
			return status;
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
		} finally {					
			try { callableStatement.close(); } catch (SQLException e) {	e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}	
		
		return status;
	} // setTrackNumberIntoDatabase
	
	
	/*************************** Main *********************************************/
	
	public static void main(String[] args) {
		
		TrackingIdGeneration generation = new TrackingIdGeneration();
		
		try {			
			
			boolean b = generation.getTrackingNumber(24L, "COD");
			
			System.out.println(b);
			
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
		}
	}

}
