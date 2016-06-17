package ecom.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecom.DAO.Buyer.BuyerSearchDAO;
import ecom.DAO.User.CreateUserDAO;
import ecom.DAO.User.UserDAO;
import ecom.common.UserType;
import ecom.controllerAction.CustomerMinimumRegistration;
import ecom.model.User;
import ecom.validations.CustomerRegistrationValidation;
import ecom.validations.Validation;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected UserDAO userDAO;
	protected BuyerSearchDAO buyerSearchDAO;
  
	@Override
	public void destroy() {
		System.out.println("Servlet Controller Destroyed.");
	}

	@Override
	public void init() throws ServletException {
		this.userDAO        = UserDAO.getInstance();
		this.buyerSearchDAO = BuyerSearchDAO.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entered Controller");
		
		HttpSession session = request.getSession();
		
		String servletPath = request.getServletPath();  
		
		if (servletPath.equals("/SellerMainPanel")) {                     //  Seller Entry        //4-3-16
			
				System.out.println("Entered SellerMainPanel");
				
				/********* Set Request ************/
				
				String userId   = request.getParameter("userId").trim();    
				String password = request.getParameter("password").trim();   
				
				/********* Database Check ***********/				
				User user = userDAO.getUser(userId, password);			
				
				
				/********* Next Page ****************/				
				
				if (user != null) {					
					
					/********* Set Session **************/
		 			
					session.setAttribute("sellerId", user.getUserInfo().getId());
					session.setAttribute("user", user);
					
					request.setAttribute("userId", user.getLogin().getUserId());
					
					request.getRequestDispatcher("jsp_Seller/SellerMainPanel.jsp").forward(request, response);
					
				} else {
					
					response.sendRedirect("SellerLoginPage");   
				}
				
				
		}
		else if (servletPath.equals("/BuyerMainPanel")) {            //  Customer View   
			
				System.out.println("Entered BuyerMainPanel");					
				
				/************ Next Page ******************/
				request.getRequestDispatcher("jsp_Buyer/BuyerMainPanel.jsp").forward(request, response);
				
		}
		
		else if (servletPath.equals("/CustomerLogin")) {                  //  Buyer Entry
			
				System.out.println("Entered CustomerLogin");
				
				/*********** Get Request ************/
				
				String userId   = request.getParameter("userId").trim();    
				String password = request.getParameter("password").trim(); 			
				
				/********* Database Check ***********/			
				
				User user = userDAO.getUser(userId, password);
				
				
				//Next Page
				
				if (user != null) {
					
					//Set Session				
					session.setAttribute("user", user);
					
					//Next Page Selection				
					response.sendRedirect("BuyerMainPanel");
				}
				else {
					
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			
		}
		
		else if (servletPath.equals("/SellerLoginPage")) {                  //  Seller Entry
			
				System.out.println("Entered SellerLoginPage");
			
			//--------- Test Code -------------
			int a = 2; int b = 7;			
			try { 
				if (a < b) throw new AssertionError();
			} catch (AssertionError e) {
				System.out.println("Assert : " + e);
			}			
			//--------- End Test Code -------------			
				
				
				/********** Next Page **************/
				
				request.getRequestDispatcher("jsp_Seller/SellerLoginPage.jsp").forward(request, response);
				
		} //SellerLoginPage
		
		else if (servletPath.equals("/InitialDashBoard")) {                  
			
			System.out.println("Entered InitialDashBoard");
			
			/********** Next Page **************/
			
			request.getRequestDispatcher("jsp_Seller/Initial.jsp").forward(request, response);
		}
		
		else if (servletPath.equals("/Administration")) {                  //  Admin, Franchise and Distributor  Entry
			
			System.out.println("Entered Administration");
			
			/************* Get Request *******************/
			
			String userId   = request.getParameter("userId")  .trim();    
			String password = request.getParameter("password").trim();   
			
			/********* Database Check ***********/			
			
			User user       = userDAO.getUser(userId, password);
			
			/************* Set Session *******************/
			
			session.setAttribute("user", user);
			
			/********** Set Request ***************/
			
			request.setAttribute("company", user.getUserInfo().getCompany());
			request.setAttribute("fName",   user.getPerson().getFirstName());
			request.setAttribute("lName",   user.getPerson().getLastName() );
			
			/********** Next Page **************/
			
			boolean userExist         = user != null;
			boolean userIsAdmin       = UserType.ADMIN       == user.getUserInfo().getUserType();
			boolean userIsFranchise   = UserType.FRANCHISE   == user.getUserInfo().getUserType();
			boolean userIsDistributor = UserType.DISTRIBUTOR == user.getUserInfo().getUserType();

			if (userExist && userIsAdmin)
				
				request.getRequestDispatcher("jsp_Administration/AdminPanel.jsp").forward(request, response);
			
			else if (userExist && userIsFranchise)
				
				request.getRequestDispatcher("jsp_Administration/FranchisePanel.jsp").forward(request, response);
			
			else if (userExist && userIsDistributor)
				
				request.getRequestDispatcher("jsp_Administration/DistributorPanel.jsp").forward(request, response);
		}
		
		
	  //Soumya Servlet
		else if (servletPath.equals("/SellerRegistrationPage")) {                 
			
			System.out.println("Entered SellerRegistrationPage");			
			
			/************** Next Page *******************/
			request.getRequestDispatcher("jsp_Seller/SellerRegistration.jsp").forward(request, response);
			
		} //SellerRegistrationPage
		
		
		else if (servletPath.equals("/RegisterSeller")) {
			
			System.out.println("Entered RegisterSeller");	

		//String First_Name     = request.getParameter("fName1");  System.out.println("HHHHH"+First_Name);
		//String Last_Name      = request.getParameter("lName1")       .trim();

		String User_Id        = request.getParameter("userId")       .trim();   //System.out.println("HHHHH"+User_Id);
		String Password       = request.getParameter("paSSworD")     .trim();    
		String First_Name     = request.getParameter("fName1")       .trim();   //System.out.println("HHHHH"+First_Name);
		String Last_Name      = request.getParameter("lName1")       .trim();
		String Gender         = request.getParameter("seX")          .trim();
		String Company        = request.getParameter("companY1")     .trim();
		String Mobile_Number1 = request.getParameter("mobileNumber1").trim();    //System.out.println("HHHHH"+Mobile_Number1);
		String Mobile_Number2 = request.getParameter("mobileNumber2").trim(); 
		String Email1         = request.getParameter("emaiL1")       .trim();
		String Email2         = request.getParameter("emaiL2")       .trim();
		String Landphone1     = request.getParameter("landPhonE1")   .trim();    //System.out.println("HHHHH"+Landphone1);
		String Landphone2     = request.getParameter("landPhonE2")   .trim();    //System.out.println("HHHHH"+Landphone2);
		String Fax1           = request.getParameter("faX1")         .trim();
		String Fax2           = request.getParameter("faX2")         .trim();
		String Address_Line1  = request.getParameter("addressLine1") .trim();
		String Address_Line2  = request.getParameter("addressLine2") .trim();
		String City           = request.getParameter("citY1")        .trim();
		String State          = request.getParameter("statE1")       .trim();
		String Pin            = request.getParameter("piN1")         .trim();
		String Country        = request.getParameter("countrY1")     .trim();
		String Pancard        = request.getParameter("pancarD")      .trim();
		String VoterId        = request.getParameter("voterID")      .trim();

		
		String First_Name2    = request.getParameter("fName2")       .trim();
		String Last_Name2     = request.getParameter("lName2")       .trim();
		String Company2       = request.getParameter("companY2")     .trim();
		String Mobile_Number3 = request.getParameter("mobileNumber3").trim();
		String Address_Line3  = request.getParameter("addressLine3") .trim();
	    String Address_Line4  = request.getParameter("addressLine4") .trim();
	    String City2          = request.getParameter("citY2")        .trim();
	    String Pin2           = request.getParameter("piN2")         .trim();
	    String State2         = request.getParameter("statE2")       .trim();
	    String Country2       = request.getParameter("countrY2")     .trim();
	    String Email3         = request.getParameter("emaiL3")       .trim();
	
	/*  String First_Name2    = "";
		String Last_Name2     = "";
		String Company2       = "";
		String Mobile_Number3 = "";
		String Address_Line3  = "";
		String Address_Line4  = "";
		String City2          = "";
		String Pin2           = "";
		String State2         = "";
		String Country2       = "";
		String Email3         = "";
		
		if(request.getParameter("checkbox")!=null) {			   
			
		   First_Name2    = request.getParameter("first_name2").trim();
		   Last_Name2     = request.getParameter("last_name2") .trim();
		   Company2       = request.getParameter("company2")   .trim();
		   Mobile_Number3 = request.getParameter("mobile3")    .trim();
		   Address_Line3  = request.getParameter("address3")   .trim();
		   Address_Line4  = request.getParameter("address4")   .trim();
		   City2          = request.getParameter("city2")      .trim();
		   Pin2           = request.getParameter("pin2")       .trim();
		   State2         = request.getParameter("state2")     .trim();
		   Country2       = request.getParameter("country2")   .trim();
		   Email3         = request.getParameter("email3")     .trim();
		   
		} else {
			
			First_Name = First_Name2;
			Last_Name = Last_Name2;
			Company = Company2;
			Mobile_Number1 = Mobile_Number3;
			Address_Line1 = Address_Line3;
			Address_Line2 = Address_Line4;
			City = City2;
			Pin = Pin2;
			State = State2;
			Country = Country2;
			Email1 = Email3;
			
		}*/
		
   		/********* Database Check ***********/

        //SellerDAO sellerDAO = SellerDAO.getNewInstance();			

        boolean status = CreateUserDAO.setSellerRegistration
        		(User_Id, Password, First_Name, Last_Name, Gender, Company, Mobile_Number1, Mobile_Number2, 
        		 Email1, Email2, Landphone1, Landphone2, Fax1, Fax2, Address_Line1, Address_Line2, City, State, Pin, 
        		 Country, Pancard, VoterId, First_Name2, Last_Name2, Company2, Mobile_Number3, Address_Line3, 
        		 Address_Line4, City2, Pin2, State2, Country2, Email3);
        		
        
        
		User user       = userDAO.getUser(User_Id, Password);
		
		/******* Set Session **********/				
		session.setAttribute("user", user);
		
		if (status == true) {
			
			System.out.println("registerSeller DataBase Updated"); 
			request.getRequestDispatcher("jsp_Seller/SellerRegistration.jsp").forward(request, response);
		}else{
			System.out.println("registerSeller DataBase Not Updated"); 
			request.getRequestDispatcher("jsp_Seller/SellerRegistration.jsp").forward(request, response);
		}
		
	} //SellerRegistrationPage
		
		
		//Soumya Servlet
		else if (servletPath.equals("/CustomerRegistrationPage")) {                 
			
			System.out.println("Entered CustomerRegistrationPage");			
			
			/************** Next Page *******************/
			request.getRequestDispatcher("jsp_Buyer/CustomerRegistration.jsp").forward(request, response);
			
		} //CustomerRegistrationPage
		
		else if (servletPath.equals("/RegisterCustomer")) {                 
			
			System.out.println("Entered RegisterCustomer");	
			
			/*************** Get Request ***************/
            String User_Id        = request.getParameter("userId")       .trim();
            String Password       = request.getParameter("paSSworD")     .trim();
            String First_Name1    = request.getParameter("fName1")       .trim();
            String Last_Name1     = request.getParameter("lName1")       .trim();
            String Gender         = request.getParameter("seX")          .trim();
            String Company1       = request.getParameter("companY1")     .trim();
            String Mobile_Number1 = request.getParameter("mobileNumber1").trim();
            String Mobile_Number2 = request.getParameter("mobileNumber2").trim();
            String Email1         = request.getParameter("emaiL1")       .trim();
            String Email2         = request.getParameter("emaiL2")       .trim();
            String Address_Line1  = request.getParameter("addressLine1") .trim();
            String Address_Line2  = request.getParameter("addressLine2") .trim();
       		String City           = request.getParameter("citY1")        .trim();
       		String State          = request.getParameter("statE1")       .trim();
       		String Pin            = request.getParameter("piN1")         .trim();
            String Country        = request.getParameter("countrY1")     .trim();
            
            
            String First_Name2    = request.getParameter("fName2")       .trim();  
            String Last_Name2     = request.getParameter("lName2")       .trim();
            String Company2       = request.getParameter("companY2")     .trim();
            String Contact        = request.getParameter("contacT")      .trim();
            String Address_Line3  = request.getParameter("addressLine3") .trim();
            String Address_Line4  = request.getParameter("addressLine4") .trim();
            String City2          = request.getParameter("citY2")        .trim();
            String Pin2           = request.getParameter("piN2")         .trim();
            String State2         = request.getParameter("statE2")       .trim();
            String Country2       = request.getParameter("countrY2")     .trim();
            String Email3         = request.getParameter("emaiL3")       .trim();
			 
       		
       		/********* Database Check ***********/

       		boolean status = CreateUserDAO.setCustomerRegistration 
       			   (User_Id, Password, First_Name1, Last_Name1, Gender, Company1, Mobile_Number1, Mobile_Number2,
       			    Email1, Email2, Address_Line1, Address_Line2, City, State, Pin, Country, 
       			    First_Name2, Last_Name2, Company2, Contact, Address_Line3, Address_Line4, City2, Pin2, State2, Country2, Email3);
       		
			
			
			User user       = userDAO.getUser(User_Id, Password);
			
			
			/******* Set Session **********/				
			session.setAttribute("user", user);
       		
       		if (status == true) {
       			
       			System.out.println("registerCustomer DataBase Updated"); 
				request.getRequestDispatcher("jsp_Buyer/CustomerRegistration.jsp").forward(request, response);
       		}else {
				System.out.println("registerCustomer DataBase Not Updated");
				request.getRequestDispatcher("jsp_Buyer/CustomerRegistration.jsp").forward(request, response);
       	}
			
			/************** Next Page *******************/
		//	request.getRequestDispatcher("jsp_Buyer/BuyerMainPanel.jsp").forward(request, response);
			
			
		} //CustomerRegistrationPage
		
	  //Soumya Servlet
		else if (servletPath.equals("/CheckCustomerUserIdFromDB")) {
			
			System.out.println("Enter CheckCustomerUserIdFromDB");
			
			PrintWriter out = response.getWriter();
			
			String User_Id = request.getParameter("userId").trim();
			String msg = "";
			
			try {
				
				msg = CreateUserDAO.CheckCustomerUserIdFromDB(User_Id);
				System.out.println(msg+"YYYYYYYY");
				
				if (msg != null) {
					
					if (msg.equalsIgnoreCase("User_Id Is Already exists")) {
						
						out.print("User_Id Is Already exists");
					} else {
						
						out.print("User_Id Is Accepted");
					}
			} 
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		 }			
		
		else if (servletPath.equals("/customerMinimumRegistration")) {	
			
			/**** Controller ****/
			System.out.println("Controller customerMinimumRegistration");			
			String nextPage = null;
			
			/****** getRequest *****/
			String userId = request.getParameter("userId").trim();
			String name = request.getParameter("name");
			String password = request.getParameter("password").trim();
			
			/********* Validation **********/
			String[] requestParameters = new String[] { userId, password, name };  System.out.println("String: "+requestParameters[0]);
			Validation validation = new CustomerRegistrationValidation();
			validation.setRequestParameters(requestParameters);
			nextPage = validation.validate(request);
			
			/**** Model ****/
			CustomerMinimumRegistration customerMinimumRegistration = null;
			if (nextPage == null) {	// No Validation Error		
				customerMinimumRegistration = new CustomerMinimumRegistration();
				customerMinimumRegistration.setUserId(userId);
				customerMinimumRegistration.setName(name);
				customerMinimumRegistration.setPassword(password);			
				nextPage = customerMinimumRegistration.execute(session, request);
			}			
			
			
			/**** Clean Up ****/
			customerMinimumRegistration = null;
			
			/**** View Resolver ****/
			request.getRequestDispatcher(nextPage).forward(request, response);
			
		}//customerMinimumRegistration
		
	}
}
