package ecom.beans;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecom.DAO.Buyer.CommissionDistributionDAO;
import ecom.Interface.project.OrderInterface;
import ecom.common.ConnectionFactory;
import ecom.model.BookedProduct;
import ecom.model.Order;
import ecom.model.OrderedItems;
import ecom.model.TwoObjects;
import ecom.model.User;

public class OrderImpl implements OrderInterface {
	
	public OrderImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static OrderImpl getNewInstance() {		
		return new OrderImpl();
	}
	
	/***************************** Set Commission - Customer ***************************************/
	@Override
	public void setFranchiseCommission(User user, Order order) {		
		
		System.out.println("Entered setFranchiseCommission");
		
		CommissionDistributionDAO commissionDistributionDAO = CommissionDistributionDAO.getNewInstance();
		//Get franchise id
		long franchiseId = commissionDistributionDAO.getFranchiseId(user); 
		
		if (franchiseId != 0) {
			
			double newBalance = 0;
			//iterate all product
			for (BookedProduct bookedProduct : order.getBookedProductList()) {
				
				if (bookedProduct.getStock() == 0) continue;
				//deduce franchise commission
				double deducedBalance = commissionDistributionDAO.getFranchiseDeducedBalance(bookedProduct.getProductId());
				//calculate franchise total commission
				newBalance = newBalance + (deducedBalance * bookedProduct.getQty());  
			}
			//set balance
			boolean status = commissionDistributionDAO.setFranchiseCommissionBalance(franchiseId, newBalance);
			
			if (status == true)
				System.out.println("Franchise Commission Set");
			else 
				System.out.println("Franchise Commission Not Set");
			
		}
		
	}
	
	/***************************** Set Commission - Distributor ***************************************/
	
	@Override
	public void setDistributorFranchiseCommission(User user, Order order) {
		
		System.out.println("Entered setDistributorFranchiseCommission");
		
		CommissionDistributionDAO commissionDistributionDAO = CommissionDistributionDAO.getNewInstance();
		
		long franchiseId = commissionDistributionDAO.getFranchiseIdOfDistributor(user);  System.out.println("franchiseId: " + franchiseId);
		
		if (franchiseId != 0) {
			
			double fNewBalance = 0;
			double dNewBalance = 0;
			
			for (BookedProduct bookedProduct : order.getBookedProductList()) {		
				
				if (bookedProduct.getStock() == 0) continue;
				
				TwoObjects<Double, Double> deducedBalanceFD = commissionDistributionDAO.getDistributorFranchiseDeducedBalance(
						bookedProduct.getProductId());
				
				fNewBalance = fNewBalance + (deducedBalanceFD.getObj1() * bookedProduct.getQty());
				dNewBalance = dNewBalance + (deducedBalanceFD.getObj2() * bookedProduct.getQty());
			}
			
			System.out.println(fNewBalance + " ::::::: " + dNewBalance);
			
			boolean status = commissionDistributionDAO.setDistributorFranchiseCommissionBalance(
					franchiseId, user.getUserInfo().getId(), fNewBalance, dNewBalance);
			
			if (status == true)
				System.out.println("Distributor And Franchise Commission Set");
			else 
				System.out.println("Distributor And Franchise Commission Not Set");
			
		}
	}
	
	/***************************** Return Commission - Customer ***************************************/
	
	public void returnFranchiseCommission(User user, Order order) {
		
		System.out.println("Entered setFranchiseCommission");
		
		CommissionDistributionDAO commissionDistributionDAO = CommissionDistributionDAO.getNewInstance();
		
		long franchiseId = commissionDistributionDAO.getFranchiseId(user);
		
		if (franchiseId != 0) {
			
			double newBalance = 0;
			
			for (BookedProduct bookedProduct : order.getBookedProductList()) {
				
				if (bookedProduct.getStock() == 0) continue;
				
				double deducedBalance = commissionDistributionDAO.getFranchiseDeducedBalance(bookedProduct.getProductId());
				
				newBalance = newBalance + (deducedBalance * bookedProduct.getQty()); 
			}
			
			boolean status = commissionDistributionDAO.returnFranchiseCommissionBalance(franchiseId, newBalance);
			
			if (status == true)
				System.out.println("Franchise Commission Set");
			else 
				System.out.println("Franchise Commission Not Set");
		}
	}
	
	/***************************** Return Commission - Distributor ***************************************/
	
	public void returnDistributorFranchiseCommission(User user, Order order) {
		
		System.out.println("Entered setDistributorFranchiseCommission");
		
		CommissionDistributionDAO commissionDistributionDAO = CommissionDistributionDAO.getNewInstance();
		
		long franchiseId = commissionDistributionDAO.getFranchiseId(user);
		
		if (franchiseId != 0) {
			
			double fNewBalance = 0;
			double dNewBalance = 0;
			
			for (BookedProduct bookedProduct : order.getBookedProductList()) {	
				
				if (bookedProduct.getStock() == 0) continue;
				
				TwoObjects<Double, Double> deducedBalanceFD = commissionDistributionDAO.getDistributorFranchiseDeducedBalance(
						bookedProduct.getProductId());
				
				fNewBalance = fNewBalance + (deducedBalanceFD.getObj1() * bookedProduct.getQty());
				dNewBalance = dNewBalance + (deducedBalanceFD.getObj2() * bookedProduct.getQty());
			}
			
			boolean status = commissionDistributionDAO.returnDistributorFranchiseCommissionBalance(
					franchiseId, user.getUserInfo().getId(), fNewBalance, dNewBalance);
			
			if (status == true)
				System.out.println("Distributor And Franchise Commission Set");
			else 
				System.out.println("Distributor And Franchise Commission Not Set");
			
		}
		
	}
	
	
	/***************** Bank Transaction ************************/
	
	@Override
	public void bankTransaction(User user, Order order, HttpServletRequest request, HttpServletResponse response,
			String orderId) throws ServletException, IOException {
		
		request.setAttribute("orderId", orderId);
				
		request.getRequestDispatcher("jsp_Buyer/payumoney.jsp").forward(request, response);				
	}
	
	/******************* Order Table Insert ****************************//*
	
	@SuppressWarnings("unused")
	public synchronized static boolean setOrderFailed(User user, Order order, String orderId) {
		
		 Connection connection = null;
		 CallableStatement callableStatement = null;		 
		 
		 boolean queryStatus = false;
		 
		 *//************* Make A String of All products for Store Procedure *********************//*
		 
		 int bookedProductListSize = order.getBookedProductList().size();
		 StringBuilder stringArray = new StringBuilder();
		 int i = 0;
		 
		 for (BookedProduct bookedProduct : order.getBookedProductList()) {
			 
			 if (bookedProduct.getStock() == 0) {
				 i++;
				 continue;
			 }
			 
			 stringArray.append(bookedProduct.getProductId());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getQty());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getGarmentOrder().getSize());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getSellPrice());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getRate());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getWarranty());	
			 stringArray.append(","); // this is required for mysql split string
			 stringArray.append(";");		 
			 
			 i++;
		 }
		 
		 //System.out.println(stringArray.toString());		 
		 
		 
		 *//************************ SQL ******************************************//*
		 
		 long userId = user.getUserInfo().getId();
		 String status = "Failed";
		 
	       
		 try{
			   connection = ConnectionFactory.getNewConnection();
			   connection.setAutoCommit(false);
			   
			   callableStatement = connection.prepareCall("{call setOrderFailed(?,?,?,?,?)}");
			   
			   callableStatement.setLong  (1, userId );
			   callableStatement.setString(2, orderId);
			   callableStatement.setString(3, stringArray.toString());
			   callableStatement.setString(4, status);
			   
			   callableStatement.registerOutParameter(5, Types.BOOLEAN);
			   
			   callableStatement.execute();
			   
			   //Return query status
			   queryStatus = callableStatement.getBoolean(5);
			   
			  
			   connection.commit();
			   System.out.println("SQL - setOrderFailed Executed");
			     
			   return queryStatus;
			    
		  } catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
				e.printStackTrace();
				
			} finally {
				try {
					callableStatement.close();
				} catch (SQLException e) {			
					e.printStackTrace();
				}
				try {
					connection.close();
				} catch (SQLException e) {			
					e.printStackTrace();
				}
			}
		   
			
		return false;
	}*/
	
	/******************* Order Table Update ****************************/
	
	public boolean setOrderPaid(String trnxId) {
		
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean queryStatus = false;
   
        
        try{
        	connection = ConnectionFactory.getNewConnection();
		    connection.setAutoCommit(false);
		    
		    callableStatement = connection.prepareCall("{call setOrderPaid(?,?)}");
		    callableStatement.setString(1, trnxId);
		    callableStatement.registerOutParameter(2, Types.BOOLEAN);
		    
		    callableStatement.execute() ; 
		    
		    queryStatus = callableStatement.getBoolean(2);		    
		   
		    System.out.println("SQL - setOrderPaid Executed");
		    
		    connection.commit();
		    return queryStatus;

        }catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			System.gc();
		}   
        
        return false;
	}
	
	public boolean setOrderSuccess(String trnxId) {
		
		Connection connection = null;
        CallableStatement callableStatement = null;
        boolean queryStatus = false;
   
        
        try{
        	connection = ConnectionFactory.getNewConnection();
		    connection.setAutoCommit(false);
		    
		    callableStatement = connection.prepareCall("{call setOrderSuccess(?,?)}");
		    callableStatement.setString(1, trnxId);
		    callableStatement.registerOutParameter(2, Types.BOOLEAN);
		    
		    callableStatement.execute() ; 
		    
		    queryStatus = callableStatement.getBoolean(2);		    
		   
		    System.out.println("SQL - setOrderSuccess Executed");
		    
		    connection.commit();
		    return queryStatus;

        }catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			System.gc();
		}   
        
        return false;
	}
	
	
	/******************* Order Table Insert ****************************/
	
	public static synchronized boolean setOrderFailed(User user, Order order, String orderId, String paymentType) {
		
		 Connection connection = null;
		 CallableStatement callableStatement = null;		 
		 
		 boolean queryStatus = false;
		 
		 //Make A String of All products for Store Procedure
		 
		 @SuppressWarnings("unused")
		 int bookedProductListSize = order.getBookedProductList().size();
		 StringBuilder stringArray = new StringBuilder();
		 @SuppressWarnings("unused")
		 int i = 0;
		 
		 for (BookedProduct bookedProduct : order.getBookedProductList()) {
			 
			 if (bookedProduct.getStock() == 0) {
				 i++;
				 continue;
			 }
			 
			 stringArray.append(bookedProduct.getProductId());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getQty());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getGarmentOrder().getSize());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getSellPrice());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getRate());
			 stringArray.append(",");
			 stringArray.append(bookedProduct.getWarranty());			 
			 stringArray.append(","); 
			 stringArray.append(bookedProduct.getProductBean().getCancellationAfterBooked());			 
			 stringArray.append(","); // this is required for mysql split string
			 stringArray.append(";");		 
			 
			 i++;
		 }	 
		 
		 System.out.println(stringArray.toString());		 
		 
		 
		 // SQL
		 
		 long userId = user.getUserInfo().getId();
		 String status = "Failed";
		 
	       
		 try{
			   connection = ConnectionFactory.getNewConnection();
			   connection.setAutoCommit(false);
			   
			   callableStatement = connection.prepareCall("{call setOrderFailed(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			   
			   callableStatement.setLong  (1,  userId );
			   callableStatement.setString(2,  orderId);
			   callableStatement.setString(3,  stringArray.toString());
			   callableStatement.setString(4,  status);
			   callableStatement.registerOutParameter(5, Types.BOOLEAN);
			   callableStatement.setString(6,  order.getDeliveryAddress().getContact());
			   callableStatement.setString(7,  order.getDeliveryAddress().getAddress());
			   callableStatement.setString(8,  order.getDeliveryAddress().getCity());
			   callableStatement.setString(9,  order.getDeliveryAddress().getState());
			   callableStatement.setString(10, order.getDeliveryAddress().getPin());
			   callableStatement.setString(11, order.getDeliveryAddress().getfName());
			   callableStatement.setString(12, order.getDeliveryAddress().getlName());
			   callableStatement.setString(13, order.getDeliveryAddress().getEmail());
			   callableStatement.setString(14, paymentType);
			   callableStatement.setString(15, order.getDeliveryAddress().getAddress1());
			   callableStatement.setString(16, order.getDeliveryAddress().getCompany());
			   
			   
			   
			   
			   callableStatement.execute();
			   
			   //Return query status
			   queryStatus = callableStatement.getBoolean(5);
			   
			  
			   connection.commit();
			   System.out.println("SQL - setOrderFailed Executed");
			     
			   return queryStatus;
			    
		  } catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
				e.printStackTrace();
				
			} finally {
				try {
					callableStatement.close();
				} catch (SQLException e) {			
					e.printStackTrace();
				}
				try {
					connection.close();
				} catch (SQLException e) {			
					e.printStackTrace();
				}
				System.gc();
			}
		   
			
		return false;
	} // setOrderFailed
	
	
	/****************** Retrieve Ordered Items ********************/
	
	public List<OrderedItems> getJustOrderedItems(String trnxId) {
		
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        List<OrderedItems> list = new ArrayList<OrderedItems>();
   
        
        try{
        	connection = ConnectionFactory.getNewConnection();
		    connection.setAutoCommit(false);
		    
		    callableStatement = connection.prepareCall("{call getJustOrderedItems(?)}");
		    callableStatement.setString(1, trnxId);
		    
		    
		    resultSet = callableStatement.executeQuery() ; 
		    
		    while (resultSet.next()) {
		    	
		    	OrderedItems orderedItems = new OrderedItems();
		    	
		    	orderedItems.setCompanyName          (resultSet.getString("companyName"          ));
		    	orderedItems.setId                   (resultSet.getInt   ("id"                   ));
		    	orderedItems.setProductId            (resultSet.getLong  ("product_id"           ));
		    	orderedItems.setProductName          (resultSet.getString("productName"          ));
		    	orderedItems.setQty                  (resultSet.getInt   ("qty"                  ));
		    	orderedItems.setSellerCompany        (resultSet.getString("sellerCompany"        ));
		    	orderedItems.setSellPriceWithShipping(resultSet.getDouble("sellPriceWithShipping"));
		    	orderedItems.setSize                 (resultSet.getString("size"                 ));
		    	orderedItems.setTranxId              (resultSet.getString("transactionId"        ));
		    	
		    	list.add(orderedItems);
		    }
		   
		    System.out.println("SQL - getJustOrderedItems Executed");
		    
		    connection.commit();
		    return list;

        }catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			System.gc();
		}   
        
        return null;
		
	} // getJustOrderedItems
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
