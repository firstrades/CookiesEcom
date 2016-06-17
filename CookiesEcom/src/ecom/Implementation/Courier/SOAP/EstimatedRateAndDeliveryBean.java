package ecom.Implementation.Courier.SOAP;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import ecom.Interface.Courier.EstimatedRateAndDelivery;
import ecom.common.ConnectionFactory;
import ecom.common.FrequentUse;
import ecom.model.Address;
import ecom.model.User;

public class EstimatedRateAndDeliveryBean implements EstimatedRateAndDelivery {
	
	// Input
	private long productId;
	private User user;
	private int  qty;
	
	// Output
	private BigDecimal rate;
	private String     delivery;
	
	// RateService Data
	private double  salePriceToCustomer;	
	private double  weight;
	private Address shipper;
	private Address recipient;
	private double  outOfDeliveryAreaRate;
	private double  outOfDeliveryAreaRateAllTaxes;
	private double  totalOutOfDeliveryAreaRate;
	//private double  octroiPercentage;
	//private double  octroiPlusODA;
	
	private EstimatedRateAndDeliveryBean() {
		this.rate = new BigDecimal(0f);
		this.outOfDeliveryAreaRateAllTaxes = 20;  // in %
	}
	
	private EstimatedRateAndDeliveryBean(long productId, User user, int qty) throws SOAPException, IOException, ParserConfigurationException, SAXException, ParseException {
		this();
		
		this.productId = productId;
		this.user      = user;
		this.qty       = qty;
		
		this.shipper   = new Address();
		this.recipient = new Address();
		
		if (this.user != null) {			
			getInputDataForRateRequest();
			getRateAndDeliveryXML();   //with soap			
		}
	}	
	
	@Override
	public BigDecimal getRate() {
		// If user not exist, Provide default value
		if (this.user != null)
			return rate;
		else
			return new BigDecimal("70.00");
	}	

	@Override
	public String getDelivery() {
		// If user not exist, Provide default value
		if (this.user != null)
			return delivery;
		else {
			Date currentDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.DATE, 7);
			Date newDate = c.getTime();
			return newDate.toString();
		}
	}	
	
	

	/************************************  New Instance  ******************************************************************/

	public static EstimatedRateAndDeliveryBean getNewInstance(long productId, User user, int qty) throws SOAPException, IOException, ParserConfigurationException, SAXException, ParseException {		
		return new EstimatedRateAndDeliveryBean(productId, user, qty);
	}
	
	/*********************************** Database Fetch *******************************************************************/	
	
	
	private void getInputDataForRateRequest() {		
			
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;
		String sql = "{call getInputDataForRateRequest(?,?)}";		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);
			callableStatement.setLong(1, this.productId);
			callableStatement.setLong(2, user.getUserInfo().getId());
			
			resultSet = callableStatement.executeQuery();
			
			while (resultSet.next()) {
				
				this.weight    = resultSet.getDouble("weight");
				this.salePriceToCustomer = resultSet.getDouble("salePriceCustomer");				
				// seller				
				this.shipper.setAddress  (resultSet.getString("address" ));  
				this.shipper.setAddress1 (resultSet.getString("address1"));
				this.shipper.setCity     (resultSet.getString("city"    ));
				this.shipper.setPin      (resultSet.getString("pin"     ));
				this.shipper.setState    (resultSet.getString("state"   ));
				this.shipper.setCountry  (resultSet.getString("country" ));
				
				this.shipper.setFirstName(resultSet.getString("first_name"));
				this.shipper.setLastName (resultSet.getString("last_name" ));
				this.shipper.setContact  (resultSet.getString("contact"   ));
				this.shipper.setCompany  (resultSet.getString("company"   ));
				this.shipper.setEmail    (resultSet.getString("email"     ));
				
				// customer
				this.recipient.setAddress  (resultSet.getString("cAddress" ));  
				this.recipient.setAddress1 (resultSet.getString("cAddress1"));     
				this.recipient.setCity     (resultSet.getString("cCity"    ));
				this.recipient.setPin      (resultSet.getString("cPin"     ));
				this.recipient.setState    (resultSet.getString("cState"   ));
				this.recipient.setCountry  (resultSet.getString("cCountry" ));
				
				this.recipient.setFirstName(resultSet.getString("cFName"   ));
				this.recipient.setLastName (resultSet.getString("cLName"   ));
				this.recipient.setContact  (resultSet.getString("cContact" ));
				this.recipient.setCompany  (resultSet.getString("cCompany" ));
				this.recipient.setEmail    (resultSet.getString("cEmail"   ));
				
			}			
			
			connection.commit();
			System.out.println("SQL - getInputDataForRateRequest executed");				
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
		} finally {			
			try { resultSet.close();         } catch (SQLException e) {	e.printStackTrace(); }
			try { callableStatement.close(); } catch (SQLException e) {	e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}		
			
	} // getInputDataForRateRequest
	
	
	
	/************************** Soap XML **********************************/	
	
	
	private void getRateAndDeliveryXML() throws SOAPException, IOException, ParserConfigurationException, SAXException, ParseException {
		
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
        
        
        
        //String responseString = fileContent();
        
        System.out.println(responseString);
        
        parseSoapResponseMessage(responseString);       
        
	}
	
	private SOAPMessage soapMessage() throws SOAPException, IOException {
		
		MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        
        //Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
        envelope.addNamespaceDeclaration("xsi",      "http://www.w3.org/2001/XMLSchema-instance");
        envelope.addNamespaceDeclaration("xsd",      "http://www.w3.org/2001/XMLSchema");
		envelope.addNamespaceDeclaration("v18",      "http://fedex.com/ws/rate/v18");		
        
		//Body
        SOAPBody soapBody = envelope.getBody(); 
        
        //RateRequest
        SOAPElement RateRequest = soapBody.addChildElement("RateRequest", "v18"); 
        
        	//WebAuthenticationDetail
        	SOAPElement WebAuthenticationDetail = RateRequest.addChildElement("WebAuthenticationDetail", "v18");
        		//UserCredential
        		SOAPElement UserCredential = WebAuthenticationDetail.addChildElement("UserCredential", "v18");
        		
        			SOAPElement Key = UserCredential.addChildElement("Key", "v18");
        			Key.addTextNode(FrequentUse.fedExKey);
        			
        			SOAPElement Password = UserCredential.addChildElement("Password", "v18");
        			Password.addTextNode(FrequentUse.fedExPassword);
        	//ClientDetail
        	SOAPElement ClientDetail = RateRequest.addChildElement("ClientDetail", "v18");
        	
        		SOAPElement AccountNumber = ClientDetail.addChildElement("AccountNumber", "v18");
        		AccountNumber.addTextNode(FrequentUse.fedExAccountNumber);
        		
        		SOAPElement MeterNumber = ClientDetail.addChildElement("MeterNumber", "v18");
        		MeterNumber.addTextNode(FrequentUse.fedExMeterNumber);
        	//Version
        	SOAPElement	Version = RateRequest.addChildElement("Version", "v18");
        	
        		SOAPElement ServiceId = Version.addChildElement("ServiceId", "v18");
        		ServiceId.addTextNode("crs");
        		
        		SOAPElement Major = Version.addChildElement("Major", "v18");
        		Major.addTextNode("18");
        		
        		SOAPElement Intermediate = Version.addChildElement("Intermediate", "v18");
        		Intermediate.addTextNode("0");
        		
        		SOAPElement Minor = Version.addChildElement("Minor", "v18");
        		Minor.addTextNode("0");
        		
        		
        		
        		
        		
        		
        		
        	//ReturnTransitAndCommit
        	SOAPElement	ReturnTransitAndCommit = RateRequest.addChildElement("ReturnTransitAndCommit", "v18");
        	ReturnTransitAndCommit.addTextNode("true");
        		
        	//RequestedShipment	
        	SOAPElement RequestedShipment = RateRequest.addChildElement("RequestedShipment", "v18");
        	
        		SOAPElement ShipTimestamp = RequestedShipment.addChildElement("ShipTimestamp", "v18");
        		Calendar calendar = Calendar.getInstance();
        		ShipTimestamp.addTextNode(calendar.get(Calendar.YEAR) + "-" 
        				+ (calendar.get(Calendar.MONTH)+1 < 10 ? "0"+ (calendar.get(Calendar.MONTH)+1) : calendar.get(Calendar.MONTH)) + "-" 
        				+ (calendar.get(Calendar.DATE) < 10 ? "0"+ calendar.get(Calendar.DATE) : calendar.get(Calendar.DATE)) + "T12:34:56-06:00");
        		
        		SOAPElement DropoffType = RequestedShipment.addChildElement("DropoffType", "v18");
        		DropoffType.addTextNode("REGULAR_PICKUP");
        		
        		SOAPElement ServiceType = RequestedShipment.addChildElement("ServiceType", "v18");
        		ServiceType.addTextNode("STANDARD_OVERNIGHT");
        		
        		SOAPElement PackagingType = RequestedShipment.addChildElement("PackagingType", "v18");
        		PackagingType.addTextNode("YOUR_PACKAGING");
        		
        		SOAPElement PreferredCurrency = RequestedShipment.addChildElement("PreferredCurrency", "v18");
        		PreferredCurrency.addTextNode("INR");
        		
        		
        		
        		
        		
        		//Shipper
        		SOAPElement Shipper = RequestedShipment.addChildElement("Shipper", "v18");
        		
        			SOAPElement Contact = Shipper.addChildElement("Contact", "v18");
        			
        				SOAPElement PersonName = Contact.addChildElement("PersonName", "v18");
        				PersonName.addTextNode(this.shipper.getFirstName() + " " + this.shipper.getLastName());
        				
        				SOAPElement CompanyName = Contact.addChildElement("CompanyName", "v18");
        				CompanyName.addTextNode(this.shipper.getCompany());
        				
        				SOAPElement PhoneNumber = Contact.addChildElement("PhoneNumber", "v18");
        				PhoneNumber.addTextNode(this.shipper.getContact());
        				
        				SOAPElement EMailAddress = Contact.addChildElement("EMailAddress", "v18");
        				EMailAddress.addTextNode(this.shipper.getEmail());
        		
        			SOAPElement Address = Shipper.addChildElement("Address", "v18");
        		
        				SOAPElement StreetLines = Address.addChildElement("StreetLines", "v18");
        				StreetLines.addTextNode(this.shipper.getAddress());
        				
        				SOAPElement StreetLines11 = Address.addChildElement("StreetLines", "v18");
        				StreetLines11.addTextNode(this.shipper.getAddress1());
        				
        				SOAPElement City = Address.addChildElement("City", "v18");
        				City.addTextNode(this.shipper.getCity());
        				
        				SOAPElement PostalCode = Address.addChildElement("PostalCode", "v18");
        				PostalCode.addTextNode(this.shipper.getPin());
        				
        				/*SOAPElement StateOrProvinceCode = Address.addChildElement("StateOrProvinceCode", "v18");
        				StateOrProvinceCode.addTextNode(this.shipper.getState());*/
        				
        				SOAPElement CountryCode = Address.addChildElement("CountryCode", "v18");
        				CountryCode.addTextNode(this.shipper.getCountry());
        				
        				
        				
        				
        				
        				
				//Recipient
        		SOAPElement Recipient = RequestedShipment.addChildElement("Recipient", "v18");
        		
        			SOAPElement Contact1 = Recipient.addChildElement("Contact", "v18");
    			
						SOAPElement PersonName1 = Contact1.addChildElement("PersonName", "v18");
						PersonName1.addTextNode(this.recipient.getFirstName() + " " + this.recipient.getLastName());
						
						SOAPElement CompanyName1 = Contact1.addChildElement("CompanyName", "v18");
						CompanyName1.addTextNode(this.recipient.getCompany());
						
						SOAPElement PhoneNumber1 = Contact1.addChildElement("PhoneNumber", "v18");
						PhoneNumber1.addTextNode(this.recipient.getContact());
						
						SOAPElement EMailAddress1 = Contact1.addChildElement("EMailAddress", "v18");
						EMailAddress1.addTextNode(this.recipient.getEmail());
        		
        			SOAPElement Address1 = Recipient.addChildElement("Address", "v18");
        		
        				SOAPElement StreetLines1 = Address1.addChildElement("StreetLines", "v18");
        				StreetLines1.addTextNode(this.recipient.getAddress());
        				
        				SOAPElement StreetLines12 = Address1.addChildElement("StreetLines", "v18");
        				StreetLines12.addTextNode(this.recipient.getAddress1());
        				
        				SOAPElement City1 = Address1.addChildElement("City", "v18");
        				City1.addTextNode(this.recipient.getCity());
        				
        				SOAPElement PostalCode1 = Address1.addChildElement("PostalCode", "v18");
        				PostalCode1.addTextNode(this.recipient.getPin());
        				
        				/*SOAPElement StateOrProvinceCode1 = Address1.addChildElement("StateOrProvinceCode", "v18");
        				StateOrProvinceCode1.addTextNode(this.recipient.getState());*/
        				
        				SOAPElement CountryCode1 = Address1.addChildElement("CountryCode", "v18");
        				CountryCode1.addTextNode(this.recipient.getCountry());
        				
        				
        				
        				
        				
        		//ShippingChargesPayment		
        		SOAPElement ShippingChargesPayment = RequestedShipment.addChildElement("ShippingChargesPayment", "v18");
        		
        			SOAPElement PaymentType = ShippingChargesPayment.addChildElement("PaymentType", "v18");
        			PaymentType.addTextNode("SENDER");
        			
    			//SpecialServicesRequested
        		SOAPElement SpecialServicesRequested = RequestedShipment.addChildElement("SpecialServicesRequested", "v18");
        		
        			SOAPElement SpecialServiceTypes = SpecialServicesRequested.addChildElement("SpecialServiceTypes", "v18");
        			SpecialServiceTypes.addTextNode("COD");
        			
        			SOAPElement CodDetail = SpecialServicesRequested.addChildElement("CodDetail", "v18");
        			
        				SOAPElement CodCollectionAmount = CodDetail.addChildElement("CodCollectionAmount", "v18");
        				
        					SOAPElement Currency1 = CodCollectionAmount.addChildElement("Currency", "v18");
        					Currency1.addTextNode("INR");
        					
        					SOAPElement Amount1 = CodCollectionAmount.addChildElement("Amount", "v18");
        					Amount1.addTextNode(String.valueOf(this.salePriceToCustomer * this.qty));                                                       
        					
        				/*SOAPElement AddTransportationChargesDetail = CodDetail.addChildElement("AddTransportationChargesDetail");
        				
        					SOAPElement RateTypeBasis = AddTransportationChargesDetail.addChildElement("RateTypeBasis");
        					RateTypeBasis.addTextNode("ACCOUNT");
        					
        					SOAPElement ChargeBasis = AddTransportationChargesDetail.addChildElement("ChargeBasis");
        					ChargeBasis.addTextNode("COD_SURCHARGE");
        					
        					SOAPElement ChargeBasisLevel = AddTransportationChargesDetail.addChildElement("ChargeBasisLevel");
        					ChargeBasisLevel.addTextNode("CURRENT_PACKAGE");*/
        					
        				SOAPElement CollectionType = CodDetail.addChildElement("CollectionType", "v18");
        				CollectionType.addTextNode("GUARANTEED_FUNDS");
        			
        		//CustomsClearanceDetail
        		SOAPElement CustomsClearanceDetail = RequestedShipment.addChildElement("CustomsClearanceDetail", "v18");
        		
        			SOAPElement DocumentContent = CustomsClearanceDetail.addChildElement("DocumentContent", "v18");
        			DocumentContent.addTextNode("DOCUMENTS_ONLY");
        			
        			SOAPElement CustomsValue = CustomsClearanceDetail.addChildElement("CustomsValue", "v18");
        			
        				SOAPElement Currency = CustomsValue.addChildElement("Currency", "v18");
        				Currency.addTextNode("INR");
        				
        				SOAPElement Amount = CustomsValue.addChildElement("Amount", "v18");
        				Amount.addTextNode(String.valueOf(this.salePriceToCustomer * this.qty));
        				
        			SOAPElement CommercialInvoice = CustomsClearanceDetail.addChildElement("CommercialInvoice", "v18");
        			
        				SOAPElement Purpose = CommercialInvoice.addChildElement("Purpose", "v18");
        				Purpose.addTextNode("SOLD");
        		
        		//PackageCount
        		SOAPElement PackageCount = RequestedShipment.addChildElement("PackageCount", "v18");
        		PackageCount.addTextNode(String.valueOf(this.qty));
        		
        		//RequestedPackageLineItems
        		SOAPElement RequestedPackageLineItems = RequestedShipment.addChildElement("RequestedPackageLineItems", "v18");
        		
        			SOAPElement GroupPackageCount = RequestedPackageLineItems.addChildElement("GroupPackageCount", "v18");
        			GroupPackageCount.addTextNode("1");
        			
        			SOAPElement Weight = RequestedPackageLineItems.addChildElement("Weight", "v18");
        			
        				SOAPElement Units = Weight.addChildElement("Units", "v18");
        				Units.addTextNode("KG");
        				
        				SOAPElement Value = Weight.addChildElement("Value", "v18");
        				Value.addTextNode(String.valueOf(this.weight * this.qty));
        				
        		
        		
        		
        
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
		document.getDocumentElement().normalize();
		
		//Surcharges
		NodeList SurchargesList = document.getElementsByTagName("Surcharges");	
	
		for (int i = 0; i < SurchargesList.getLength(); i++) {
		
			Node Surcharges = SurchargesList.item(i);
			
			NodeList SurchargesChildNodes = Surcharges.getChildNodes();
		
			Node SurchargeType = SurchargesChildNodes.item(1);
			Node SurchargeTypeText = SurchargeType.getFirstChild();
			
			String outOfDeliveryArea = null;
			if (SurchargeTypeText instanceof CharacterData) {				
				outOfDeliveryArea = ((CharacterData) SurchargeTypeText).getData(); 				
			}
			
			if (outOfDeliveryArea.equals("OUT_OF_DELIVERY_AREA")) {  System.out.println("Enter");
				
				Node AmountChild = SurchargesChildNodes.item(5);  			
				NodeList AmountChildList = AmountChild.getChildNodes();
				Node Amount = AmountChildList.item(3);
				
				this.outOfDeliveryAreaRate = Double.parseDouble(Amount.getTextContent());				
				this.totalOutOfDeliveryAreaRate = this.outOfDeliveryAreaRate * ( 1 + this.outOfDeliveryAreaRateAllTaxes / 100 );
				
				if (this.totalOutOfDeliveryAreaRate != 0)
					this.rate = new BigDecimal(this.totalOutOfDeliveryAreaRate);
				
			}
			
			
		}
		
		/*//TotalNetChargeWithDutiesAndTaxes
		NodeList TotalNetChargeWithDutiesAndTaxesList = document.getElementsByTagName("TotalNetChargeWithDutiesAndTaxes");		
		
		for (int i = 0; i < TotalNetChargeWithDutiesAndTaxesList.getLength(); i++) {
			
			Node TotalNetChargeWithDutiesAndTaxes = TotalNetChargeWithDutiesAndTaxesList.item(i);
			Node amount = TotalNetChargeWithDutiesAndTaxes.getLastChild();
			Node text = amount.getFirstChild();
			
			if (text instanceof CharacterData) {				
				this.rate = new BigDecimal(((CharacterData) text).getData());  
			}
		}*/
		
		//DeliveryDayOfWeek
		NodeList DeliveryDayOfWeekList = document.getElementsByTagName("DeliveryDayOfWeek"); 
		      
		String dayOfWeek = null;
		for (int i = 0; i < DeliveryDayOfWeekList.getLength(); i++) {
			
			Node DeliveryDayOfWeek = DeliveryDayOfWeekList.item(i);
			Node dayOfWeekNode = DeliveryDayOfWeek.getFirstChild();			
			
			if (dayOfWeekNode instanceof CharacterData) {				
				dayOfWeek = ((CharacterData) dayOfWeekNode).getData();  
			}
		}		
		
		//DeliveryTimestamp
		NodeList DeliveryTimestampList = document.getElementsByTagName("DeliveryTimestamp"); 
		      
		String timeStamp = null;
		for (int i = 0; i < DeliveryTimestampList.getLength(); i++) {
			
			Node DeliveryTimestamp = DeliveryTimestampList.item(i);
			Node timeStampNode = DeliveryTimestamp.getFirstChild();			
			
			if (timeStampNode instanceof CharacterData) {				
				timeStamp = ((CharacterData) timeStampNode).getData();  
			}
		}
		
		String date = getDate(timeStamp); 
		
		this.delivery = dayOfWeek + " " + date;  
		
	}
	
	private String getDate(String timeStamp) throws ParseException {
		
		String[] array = timeStamp.split("T");
		String yyyyMmDd = array[0];  
		
		DateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
		fromFormat.setLenient(false);
		String dateStr = yyyyMmDd;
		Date date = fromFormat.parse(dateStr);
		
		Format formatter = new SimpleDateFormat("dd MMMM yyyy"); 
	    String requiredData = formatter.format(date);	    
	    
		return requiredData;
	}
	
	/*private double getOctroiCharge(String shipperState, String recipientState) {
		// will be added with rate
		double rate = 0;
		
		if (shipperState.equals(recipientState)) 
			
			rate = new BigDecimal(this.totalOutOfDeliveryAreaRate);
		
		else {
			
			if (recipientState.equals("MH")) {
				
				double rateInDouble = this.totalOutOfDeliveryAreaRate + this.salePriceToCustomer * ( 1 + this.octroiPercentage / 100 );
				rate = new BigDecimal(rateInDouble);
			}
			else
				rate = new BigDecimal(this.totalOutOfDeliveryAreaRate);
		}
		
		return rate;
	}*/
	
	@SuppressWarnings("unused")
	private String fileContent() throws IOException {		
		
		InputStream in = this.getClass().getResourceAsStream("/Fedex.txt");	
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		String soapResponseMessage1 = "";
		String tempLine = null;
		
		while ((tempLine = reader.readLine()) != null) {
			
			soapResponseMessage1 += tempLine;
		}		
		
		return soapResponseMessage1;
	}
	
	
	/**********************  Main  ***************************************/
	
	
	public static void main(String...args) {
		
		User user = new User();
		user.getUserInfo().setId(1L);
		
			try {
				new EstimatedRateAndDeliveryBean(235, user, 2).getRateAndDeliveryXML();
			} catch (IOException | SOAPException | ParserConfigurationException | SAXException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	

}  // end
















