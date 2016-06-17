package ecom.DAO.Seller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import ecom.common.ConnectionFactory;
import ecom.model.OrderTable;
import ecom.model.User;

public class SellerDAO {
	
	private static SellerDAO sellerDAO;
	
	private SellerDAO() {
		sellerDAO = null;
	}
	
	public static SellerDAO getInstance() {
		
		if (sellerDAO == null) {			
			synchronized (SellerDAO.class) {				
				if (sellerDAO == null)
					sellerDAO = new SellerDAO();
			}			
		}
		
		return sellerDAO;
	}
	
	/*************************************************************/
	
	public boolean setPickedUp(long orderTableId, String date, String courierName) {
		System.out.println(courierName);
		Connection connection = null;
        CallableStatement callableStatement = null;        
        
        boolean picked = false;   
        
        try{
        	connection = ConnectionFactory.getNewConnection();
		    connection.setAutoCommit(false);
		    
		    callableStatement = connection.prepareCall("{call setPickedUp(?,?,?,?)}");
		    callableStatement.setLong  (1, orderTableId);
		    callableStatement.setString(2, date        );
		    callableStatement.registerOutParameter(3, Types.BOOLEAN);
		    callableStatement.setString(4, courierName);
		    
		    callableStatement.execute(); 		   
		    	
		    picked = callableStatement.getBoolean(3);
		    
		   
		    System.out.println("SQL - setPickedUp Executed");
		    
		    connection.commit();
		    return picked;

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
        
		return picked;
	} //setPickedUp
	
	
	public List<OrderTable> getOrderTables(User user) {
		
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        List<OrderTable> list = new ArrayList<OrderTable>();
   
        
        try{
        	connection = ConnectionFactory.getNewConnection();
		    connection.setAutoCommit(false);
		    
		    callableStatement = connection.prepareCall("{call getOrderTables(?)}");
		    callableStatement.setLong(1, user.getUserInfo().getId());
		    
		    
		    resultSet = callableStatement.executeQuery() ; 
		    
		    while (resultSet.next()) {
		    	
		    	OrderTable orderTable = new OrderTable();
		    	
		    	orderTable.setBookedDateTime(resultSet.getString("date_time"    ));		    	
		    	orderTable.setId            (resultSet.getLong  ("id"           ));
		    	orderTable.setOrderId       (resultSet.getString("order_id"     ));
		    	orderTable.setOrderState    (resultSet.getString("order_state"  ));
		    	orderTable.setProductId     (resultSet.getLong  ("product_id"   ));
		    	orderTable.setQty           (resultSet.getInt   ("qty"          ));
		    	orderTable.setSellPrice     (resultSet.getDouble("sell_price"   ));
		    	orderTable.setShippingCost  (resultSet.getDouble("shipping_cost"));
		    	orderTable.setSize          (resultSet.getString("size"         ));
		    	orderTable.setStatus        (resultSet.getString("status"       ));
		    	orderTable.setWarranty      (resultSet.getString("warranty"     ));
		    	orderTable.setSellerId      (resultSet.getLong  ("seller_id"    ));
		    	orderTable.setPaymentType   (resultSet.getString("payment_type" ));
		    	
		    	orderTable.getDeliveryAddress().setfName   (resultSet.getString("first_name" ));
		    	orderTable.getDeliveryAddress().setlName   (resultSet.getString("last_name"  ));
		    	orderTable.getDeliveryAddress().setContact (resultSet.getString("contact" ));
		    	orderTable.getDeliveryAddress().setAddress (resultSet.getString("address" ));
		    	orderTable.getDeliveryAddress().setAddress1(resultSet.getString("address1"));
		    	orderTable.getDeliveryAddress().setCity    (resultSet.getString("city"    ));
		    	orderTable.getDeliveryAddress().setState   (resultSet.getString("state"   ));
		    	orderTable.getDeliveryAddress().setPin     (resultSet.getString("pin"     ));
		    	orderTable.getDeliveryAddress().setEmail   (resultSet.getString("email"   ));
		    	
		    	list.add(orderTable);
		    }
		   
		    System.out.println("SQL - getOrderTables Executed");
		    
		    connection.commit();
		    return list;

        } catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			list = null;
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
	} //getOrderTables
	
	
	public boolean setItemCancelled(long orderTableId) {
		
		Connection connection = null; CallableStatement callableStatement = null;  
		boolean status = false;
        
        try{
        	connection = ConnectionFactory.getNewConnection();
		    connection.setAutoCommit(false);
		    
		    callableStatement = connection.prepareCall("{call setItemCancelled(?,?)}");
		    callableStatement.setLong   (1, orderTableId);
		    callableStatement.registerOutParameter(2, Types.BOOLEAN);
		    
		    callableStatement.execute();
		    
		    status = callableStatement.getBoolean(2);   System.out.println(status);
		   
		    System.out.println("SQL - setItemCancelled Executed");
		    
		    connection.commit();
		    return status;

        } catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {
			
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}   
        
		return status;
	} //setItemCancelled
	
	
	public String generatePDF(long orderTableId) {
		
		Connection connection = null; CallableStatement callableStatement = null;  
		String base64 = null;
        
        try {
        	connection = ConnectionFactory.getNewConnection();
		    connection.setAutoCommit(false);
		    
		    callableStatement = connection.prepareCall("{call generatePDF(?,?)}");
		    callableStatement.setLong   (1, orderTableId);
		    callableStatement.registerOutParameter(2, Types.LONGVARCHAR);
		    
		    callableStatement.execute();
		    
		    base64 = callableStatement.getString(2);  
		   
		    System.out.println("SQL - generatePDF Executed");
		    
		    connection.commit();
		    return base64;

        } catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {
			base64 = null;
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}   
        
		return null;
	} //generatePDF
	
	
	public String generatePDFRet(long orderTableId) {
		
		Connection connection = null; CallableStatement callableStatement = null;  
		String base64 = null;
        
        try {
        	connection = ConnectionFactory.getNewConnection();
		    connection.setAutoCommit(false);
		    
		    callableStatement = connection.prepareCall("{call generatePDFRet(?,?)}");
		    callableStatement.setLong   (1, orderTableId);
		    callableStatement.registerOutParameter(2, Types.LONGVARCHAR);
		    
		    callableStatement.execute();
		    
		    base64 = callableStatement.getString(2);  
		   
		    System.out.println("SQL - generatePDFRet Executed");
		    
		    connection.commit();
		    return base64;

        } catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {
			base64 = null;
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}   
        
		return null;
	} //generatePDFRet
	
	
	public String getSearchKeyword(long productId) {
		
		Connection connection = null; PreparedStatement preparedStatement = null; ResultSet resultSet = null;
		
		String sql = "select * from search_key_word where productId = ?";		
		String searchKeyword = null;
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);				
			
			preparedStatement.setLong(1, productId);					
			
			resultSet = preparedStatement.executeQuery();  
			
			if (resultSet.next()) {
				searchKeyword = resultSet.getString("searchKeyWord_forProduct");
			}
			
			connection.commit();					
			System.out.println("SQL - getSearchKeyword executed");				
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();			
			return null;
		} finally {
			if (resultSet != null)
				try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			if (preparedStatement != null)
				try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			if (connection != null)
				try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		
		return searchKeyword;
	}//getSearchKeyword
	
	public int setSearchKeyword(long productId, String searchKeyword) {
		
		Connection connection = null; CallableStatement callableStatement = null; 		
		String sql = "{call setSearchKeyword(?,?,?)}";	
		int insertedSearchKeyword = -1;	
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql); 				
					
			callableStatement.registerOutParameter(1, Types.INTEGER);
			
			callableStatement.setLong  (2, productId);		
			callableStatement.setString(3, searchKeyword);	
			
				
			callableStatement.execute();
			
			insertedSearchKeyword = callableStatement.getInt(1);			
			
			connection.commit();					
			System.out.println("SQL - setSearchKeyword executed");						
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
			
			return insertedSearchKeyword;
			
		} finally {			
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }		
			System.gc();			
		} 	
		
		return insertedSearchKeyword;	
		
	}//setSearchKeyword
	

}
