package ecom.SERVLET.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecom.DAO.User.UserDAO;
import ecom.Interface.project.OrderInterface;
import ecom.beans.BankTransaction;
import ecom.beans.OrderImpl;
import ecom.common.UserType;
import ecom.model.Order;
import ecom.model.OrderedItems;
import ecom.model.User;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	
	@Override
	public void init() {
		userDAO = UserDAO.getInstance();
	}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("OrderServlet Destroyed"); 
	};

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entered OrderServlet");
		
		String servletPath = request.getServletPath();
		
		HttpSession session = request.getSession();
		
		
		
		/*************************************************************************************************************/
		
		
		
		if (servletPath.equals("/OrderBank")) {
			
					System.out.println("Entered OrderBank");
					
					/********* Get Session ************/
					User  user  = (User)  session.getAttribute("user" );
					Order order = (Order) session.getAttribute("order");
					
					/********** Process Commission ***************/
					
					//Get Object
					OrderInterface orderInterface = OrderImpl.getNewInstance();
					boolean commissionSuccessFlag = false;
					
					try {
					
						//Set Commission Balance
						if (user.getUserInfo().getUserType() == UserType.CUSTOMER) {
							orderInterface.setFranchiseCommission(user, order);
							commissionSuccessFlag = true;
						}
						else if (user.getUserInfo().getUserType() == UserType.DISTRIBUTOR) {
							orderInterface.setDistributorFranchiseCommission(user, order);
							commissionSuccessFlag = true;
						}
						
						
						//Set Order Database - Failed 
						BankTransaction bankTransaction = BankTransaction.getNewInstance();
						String trnxId = bankTransaction.getNextTransactionId();
						
						String paymentType = "BANK";
						
						@SuppressWarnings("unused")
						boolean isExecuted = OrderImpl.setOrderFailed(user, order, trnxId, paymentType);					
						
						//Bank Transaction
						orderInterface.bankTransaction(user, order, request, response, trnxId/*orderId*/);			
						
						
					} catch(Exception e) {
						
						//Return Commission Balance
						if (commissionSuccessFlag == true) {					
							if (user.getUserInfo().getUserType() == UserType.CUSTOMER)
								orderInterface.returnFranchiseCommission(user, order);
							else if (user.getUserInfo().getUserType() == UserType.DISTRIBUTOR)
								orderInterface.returnDistributorFranchiseCommission(user, order);
						}
						
						e.printStackTrace();
					}
		} //OrderBank
		
		
		
		/*************************************************************************************************************/
		
		
		
		else if (servletPath.equals("/OrderCOD")) {
			
					System.out.println("Entered OrderCOD");
					
					/********* Get Session ************/
					User  user  = (User)  session.getAttribute("user" );
					Order order = (Order) session.getAttribute("order");
					
					/********** Process Commission ***************/
					
					//Get Object
					OrderInterface orderInterface = OrderImpl.getNewInstance();
					boolean commissionSuccessFlag = false;
					
					try {
					
						//Set Commission Balance
						if (user.getUserInfo().getUserType() == UserType.CUSTOMER) {
							orderInterface.setFranchiseCommission(user, order);
							commissionSuccessFlag = true;
						}
						else if (user.getUserInfo().getUserType() == UserType.DISTRIBUTOR) {
							orderInterface.setDistributorFranchiseCommission(user, order);
							commissionSuccessFlag = true;
						}
						
						
						//Set Order Database - Failed 
						BankTransaction bankTransaction = BankTransaction.getNewInstance();
						String trnxId = bankTransaction.getNextTransactionId();
						
						String paymentType = "COD";
						
						@SuppressWarnings("unused")
						boolean isExecuted = OrderImpl.setOrderFailed(user, order, trnxId, paymentType);		
						
						//Success---------------------------------------------------------
						
						setOrderSuccess(request, response, trnxId);			
						
						
					} catch(Exception e) {
						
						//Return Commission Balance
						if (commissionSuccessFlag == true) {					
							if (user.getUserInfo().getUserType() == UserType.CUSTOMER)
								orderInterface.returnFranchiseCommission(user, order);
							else if (user.getUserInfo().getUserType() == UserType.DISTRIBUTOR)
								orderInterface.returnDistributorFranchiseCommission(user, order);
						}
						
						e.printStackTrace();
					}
		} //OrderCOD
		
		
		
		/*************************************************************************************************************/
		
		
		else if (servletPath.equals("/SuccessServlet")) {
			
					System.out.println("Entered SuccessServlet");				
					
					String trnxId =  request.getParameter("txnid");     //"J20160212160056256";               	
					
					/******** Get User from Database and Set Session ***************/					
					User user = userDAO.getUserByTranxId(trnxId);  
					session.setAttribute("user", user);
					
					//Success---------------------------------------------------------
					
					setOrderSuccess(request, response, trnxId);
			
			
		}
		
		
		
		/*************************************************************************************************************/
		
		
		else if (servletPath.equals("/FailureServlet")) {
			
					System.out.println("Entered FailureServlet");
					
					String trnxId =  request.getParameter("txnid");     //"J20160212160056256";   
					
					/******** Get User from Database and Set Session ***************/					
					User user = userDAO.getUserByTranxId(trnxId);  
					session.setAttribute("user", user);
					
					
					//create order object again
					Order order = null;
					
					/********** Process Return Commission ***************/
					
					//Get Object
					OrderInterface orderInterface = OrderImpl.getNewInstance();				
					
					
					if (user.getUserInfo().getUserType() == UserType.CUSTOMER)
						orderInterface.returnFranchiseCommission(user, order);
					else if (user.getUserInfo().getUserType() == UserType.DISTRIBUTOR)
						orderInterface.returnDistributorFranchiseCommission(user, order);
		}
		
	}
	
	
	
	
	
	/****************************************************************************************************************
	 *                                            Methods                                                           *
	 ****************************************************************************************************************/
	
	
	
	
	
	
	private void setOrderSuccess(HttpServletRequest request, HttpServletResponse response, String trnxId) throws ServletException, IOException {
		
		
		OrderInterface orderInterface = OrderImpl.getNewInstance();
		boolean isExecuted = false;
		
		//not necessary till now XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		//isExecuted = orderInterface.setOrderPaid(trnxId);
		
		//if (isExecuted)
			isExecuted = orderInterface.setOrderSuccess(trnxId);     
		
		List<OrderedItems> orderedItemsList = null;
		if (isExecuted)  {						
			
			orderedItemsList = orderInterface.getJustOrderedItems(trnxId);				
			request.setAttribute("orderedItemsList", orderedItemsList);				
			request.getRequestDispatcher("jsp_Buyer/OrderedItem.jsp").forward(request, response);
			
		} else {
			
			System.out.println("SuccessServlet  Failed");
		}		
	}

}
