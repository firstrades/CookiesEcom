package ecom.DAO.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ecom.common.ConnectionFactory;
import ecom.common.Conversions;
import ecom.model.User;

public class UserDAO {
	
	private static UserDAO userDAO;
	
	private UserDAO() {
		userDAO = null;
	}
	
	public static UserDAO getInstance() {
		
		if (userDAO == null) {			
			synchronized (UserDAO.class) {				
				if (userDAO == null)
					userDAO = new UserDAO();
			}			
		}
		
		return userDAO;
	}

	public User getUser(String userId, String password) {		//5-3-16
		
		User user = null;
		
		String sql            = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet   = null;
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getNewConnection();  
			
			sql = "SELECT * FROM user WHERE user_id = ? AND password1 = ? AND status = 'active'";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, password);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {  
				
				user = new User();
				
				user.getLogin().setUserId            (resultSet.getString(  "user_id"       ));           
				user.getLogin().setPassword          (resultSet.getString(  "password1"     ));
				
				user.getPerson().setFirstName        (resultSet.getString(  "first_name"    ));
				user.getPerson().setLastName         (resultSet.getString(  "last_name"     ));
				user.getPerson().setSex              (resultSet.getString(  "sex"           ));
				
				user.getContact().setMobile1         (resultSet.getString(  "mobile1"       ));
				user.getContact().setMobile2         (resultSet.getString(  "mobile2"       ));
				user.getContact().setPhone1          (resultSet.getString(  "phone1"        ));
				user.getContact().setPhone2          (resultSet.getString(  "phone2"        ));
				user.getContact().setEmail1          (resultSet.getString(  "email1"        ));
				user.getContact().setEmail2          (resultSet.getString(  "email2"        ));
				
				user.getAddress().setAddress         (resultSet.getString(  "address"       ));
				user.getAddress().setCity            (resultSet.getString(  "city"          ));
				user.getAddress().setState           (resultSet.getString(  "state"         ));
				user.getAddress().setPin             (resultSet.getString(  "pin"           ));
				
				user.getUserInfo().setUserType       (Conversions.getEnumUserType(resultSet.getString( "user_type" ))); 
				user.getUserInfo().setStatus         (resultSet.getString(  "status"        ));
				user.getUserInfo().setJoiningDate    (resultSet.getString(  "joining_date"  ));
				user.getUserInfo().setCompany        (resultSet.getString(  "company"       ));

				user.getUserInfo().setId             (resultSet.getLong  (  "id"            ));   System.out.println(user.getUserInfo().getId());

				
			}
			
			
			System.out.println("SQL - userLogin() of ecom.DAO.User.UserDAO  Executed...");
			
			return user;
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			user = null;
			try { resultSet.close();         } catch (SQLException e) { e.printStackTrace(); }			
			try { preparedStatement.close(); } catch (SQLException e) {	e.printStackTrace(); }			
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}
		
		
		return null;
	}
	
	public String getSellerCompany(long sellerId) {	
		
		String sql            = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet   = null;
		Connection connection = null;
		
		String company = null;
		
		try {
			connection = ConnectionFactory.getNewConnection();
			
			sql = "SELECT company FROM user WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, sellerId);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {  
				
				company = resultSet.getString("company");				
			}
			
			
			System.out.println("SQL - getSellerCompany of ecom.DAO.User.UserDAO  Executed...");
			
			return company;
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				resultSet.close();
			} catch (SQLException e2) {			
				e2.printStackTrace();
			}
			
			try {
				preparedStatement.close();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			
			try {
				connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			System.gc();
		}
		
		
		return null;
	}
	
	
	public User getUserByTranxId(String trnxId) {
		
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        User user = new User();   
        
        try{
        	connection = ConnectionFactory.getNewConnection();
		    connection.setAutoCommit(false);
		    
		    callableStatement = connection.prepareCall("{call getUserByTranxId(?)}");
		    callableStatement.setString(1, trnxId);		    
		    
		    resultSet = callableStatement.executeQuery() ; 
		    
		    while (resultSet.next()) {		    	
		    	
		    	user.getLogin().setUserId            (resultSet.getString(  "user_id"       ));           
				user.getLogin().setPassword          (resultSet.getString(  "password1"      ));
				
				user.getPerson().setFirstName        (resultSet.getString(  "first_name"    ));
				user.getPerson().setLastName         (resultSet.getString(  "last_name"     ));
				user.getPerson().setSex              (resultSet.getString(  "sex"           ));
				
				user.getContact().setMobile1         (resultSet.getString(  "mobile1"       ));
				user.getContact().setMobile2         (resultSet.getString(  "mobile2"       ));
				user.getContact().setPhone1          (resultSet.getString(  "phone1"        ));
				user.getContact().setPhone2          (resultSet.getString(  "phone2"        ));
				user.getContact().setEmail1          (resultSet.getString(  "email1"        ));
				user.getContact().setEmail2          (resultSet.getString(  "email2"        ));
				
				user.getAddress().setAddress         (resultSet.getString(  "address"       ));
				user.getAddress().setCity            (resultSet.getString(  "city"          ));
				user.getAddress().setState           (resultSet.getString(  "state"         ));
				user.getAddress().setPin             (resultSet.getString(  "pin"           ));
				
				user.getUserInfo().setUserType       (Conversions.getEnumUserType(resultSet.getString( "user_type" ))); 
				user.getUserInfo().setStatus         (resultSet.getString(  "status"        ));
				user.getUserInfo().setJoiningDate    (resultSet.getString(  "joining_date"  ));
				user.getUserInfo().setCompany        (resultSet.getString(  "company"       ));
				user.getUserInfo().setId             (resultSet.getLong  (  "id"            ));
		    	
		    	
		    }
		   
		    System.out.println("SQL - getUserByTranxId Executed");
		    
		    connection.commit();
		    return user;

        } catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			user = null;
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
	}//getUserByTranxId
	
	//Soumya DAO - Not Used
	public static String CheckUserId (String User_Id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		String msg = "";
		
		try{
			
			connection = ConnectionFactory.getNewConnection();
			sql = "Select * From user Where user_id = '"+User_Id+"'";
			
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				msg = "User_Id Is Already exists";
			}else {
					msg = "User_Id Is Not exists In Database";
				}
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
				preparedStatement.close();
				resultSet.close();
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
		
		return msg;
	 }
	
	
	/**************************** get User - Methods (1) **************************************/
	
	public User getUser(int userIdNo) throws Exception {		
		return getUser(null, null, userIdNo);
	}
	
	public User getUser(String userId, String password, int userIdNo) throws Exception {		
		
		User user = null;
		
		String sql            = null;
		Statement statement = null;
		ResultSet resultSet   = null;
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getNewConnection();  
			
			sql = getUserSql(userId, password, userIdNo);
			
			statement = connection.createStatement();			
			
			resultSet = statement.executeQuery(sql);
			
			if (resultSet.next()) {  
				
				user = new User();
				
				user.getLogin().setUserId            (resultSet.getString(  "user_id"       ));           
				user.getLogin().setPassword          (resultSet.getString(  "password1"     ));
				
				user.getPerson().setFirstName        (resultSet.getString(  "first_name"    ));
				user.getPerson().setLastName         (resultSet.getString(  "last_name"     ));
				user.getPerson().setSex              (resultSet.getString(  "sex"           ));
				
				user.getContact().setMobile1         (resultSet.getString(  "mobile1"       ));
				user.getContact().setMobile2         (resultSet.getString(  "mobile2"       ));
				user.getContact().setPhone1          (resultSet.getString(  "phone1"        ));
				user.getContact().setPhone2          (resultSet.getString(  "phone2"        ));
				user.getContact().setEmail1          (resultSet.getString(  "email1"        ));
				user.getContact().setEmail2          (resultSet.getString(  "email2"        ));
				
				user.getAddress().setAddress         (resultSet.getString(  "address"       ));
				user.getAddress().setCity            (resultSet.getString(  "city"          ));
				user.getAddress().setState           (resultSet.getString(  "state"         ));
				user.getAddress().setPin             (resultSet.getString(  "pin"           ));
				
				user.getUserInfo().setUserType       (Conversions.getEnumUserType(resultSet.getString( "user_type" ))); 
				user.getUserInfo().setStatus         (resultSet.getString(  "status"        ));
				user.getUserInfo().setJoiningDate    (resultSet.getString(  "joining_date"  ));
				user.getUserInfo().setCompany        (resultSet.getString(  "company"       ));

				user.getUserInfo().setId             (resultSet.getLong  (  "id"            ));   System.out.println(user.getUserInfo().getId());

				
			}
			
			
			System.out.println("SQL - userLogin() of ecom.DAO.User.UserDAO  Executed...");
			
			return user;
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace(); throw e;
		}
		finally {
			user = null;
			try { resultSet.close();         } catch (SQLException e) { e.printStackTrace(); }
			try { statement.close();         } catch (SQLException e) { e.printStackTrace(); }		
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}	
		
		
	} //getUser
	
	
	private String getUserSql(String userId, String password, int userIdNo) {
		
		String sql = null;
		
		if (userIdNo > 0) {
			sql = "select * from user where id = "+userIdNo;
		}		
		
		return sql;
	}
	
	
	/******************************** End get User - Methods (1) ************************************************/

}
