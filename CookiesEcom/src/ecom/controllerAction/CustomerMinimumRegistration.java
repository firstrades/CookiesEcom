package ecom.controllerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ecom.DAO.User.CreateUserDAO;
import ecom.DAO.User.UserDAO;
import ecom.model.User;
import ecom.sms.email.SMS;

public class CustomerMinimumRegistration {
	//External
	private String userId;
	private String password;
	private String name;
	//Internal
	private String errorMessage;
	private UserDAO userDAO;
	private int userIdNo;  //'0' user exists, 'maxId > 0' user created, '-1' some error occurred
	private String nextPage;	
	private User user;
	
	public CustomerMinimumRegistration() {
		userDAO = UserDAO.getInstance();
		errorMessage = null;
		userIdNo = -1;
		nextPage = null;		
	}

	public String execute(HttpSession session, HttpServletRequest request) {
		
		String[] fullName = name.split(" ");
		
		try {
			/********* Database ***********/				
			userIdNo = CreateUserDAO.createCustomer(userId, password, fullName);
			
			if (userIdNo > 0)
				user = userDAO.getUser(userIdNo);
			else if (userIdNo == 0)
				throw new Exception();
			else if (userIdNo < 0)
				throw new Exception();
			
			/*********** setSession **************/
			session.setAttribute("user", user);			
			nextPage = "jsp_Buyer/BuyerMainPanel.jsp";
			
			
			//-----------------------------------------------------------------------SMS
			//In case you want to check error, u can use same catch block for sms errors.
			String smsMessage = "Dear customer, your userId: "+userId+" and password: "+password+ " is created";
			final String senderId = "SUACPL";            //"RECHRG";
			final String smsUserId = "ysk-suraksha";     //"ysk-yaduvanshi";
			final String smsPassword = "000000";         //"000000";
			SMS.sendSMS(userId/*mobile*/, smsMessage, senderId, smsUserId, smsPassword);
			//-----------------------------------------------------------------------SMS
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			if (userIdNo > 0) {
				CreateUserDAO.deleteCustomer(userIdNo);
			}
			if (userIdNo == 0)
				errorMessage = "User already exists!";
			else
				errorMessage = "Some problem occured!";
			//error page
			nextPage = "ErrorPages/errorMessage.jsp";
		}
		
		request.setAttribute("errorMessage", errorMessage);
		
		return nextPage;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}






































/*else if (servletPath.equals("/customerMinimumRegistration")) {

System.out.println("Entered customerMinimumRegistration");

int userIdNo = -1;  //'0' user exists, 'maxId > 0' user created, '-1' some error occurred
String nextPage = null;
String errorMessage = null;
User user = null;

try {

	*//*********** getRequest ************//*			
	String userId   = request.getParameter("userId")  .trim();    
	String password = request.getParameter("password").trim(); 			
	
	*//********* Database ***********//*				
	userIdNo = CreateUserDAO.createCustomer(userId, password);
	
	if (userIdNo > 0)
		user = userDAO.getUser(userIdNo);
	else if (userIdNo == 0)
		throw new Exception();
	else if (userIdNo < 0)
		throw new Exception();
	
	*//*********** setSession **************//*
	session.setAttribute("user", user);
	
	nextPage = "jsp_Buyer/BuyerMainPanel.jsp";
	
} catch (Exception e) {				
	e.printStackTrace();
	if (userIdNo > 0) {
		CreateUserDAO.deleteCustomer(userIdNo);
	}
	if (userIdNo == 0)
		errorMessage = "User already exists!";
	else
		errorMessage = "Some problem occured!";
	//error page
	nextPage = "ErrorPages/errorMessage.jsp";
}

request.setAttribute("errorMessage", errorMessage);			
request.getRequestDispatcher(nextPage).forward(request, response);

}//customerMinimumRegistration
*/	
