package ecom.DAO.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ecom.common.ConnectionFactory;
import ecom.common.UserType;

public class CreateUserDAO {

	public synchronized static boolean setFranchise(String fUserId, String password, String fName, String lName, String sex, String company, String mobile1, String mobile2, 
			String email1, String email2, String phone1, String phone2, String fax1, String fax2, String address, String city, String state, 
			String pin, String pan, String voterId, java.sql.Timestamp joiningDate, long userId, UserType userType) {
		
			String userType1 = null;
		
			if (userType == UserType.ADMIN)  userType1 = "admin";
			
			Connection connection = null;
			CallableStatement callableStatement = null;
		
			String sql = "{call createFranchise(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
			try {
				
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
					
				callableStatement = connection.prepareCall(sql);
				
				callableStatement.setString(1,  fUserId);
				callableStatement.setString(2,  password);
				callableStatement.setString(3,  fName);
				callableStatement.setString(4,  lName);
				callableStatement.setString(5,  sex);
				callableStatement.setString(6,  company);
				callableStatement.setString(7,  mobile1);
				callableStatement.setString(8,  mobile2);
				callableStatement.setString(9,  email1);
				callableStatement.setString(10, email2);
				callableStatement.setString(11, phone1);
				callableStatement.setString(12, phone2);
				callableStatement.setString(13, fax1);
				callableStatement.setString(14, fax2);
				callableStatement.setString(15, address);
				callableStatement.setString(16, city);
				callableStatement.setString(17, state);
				callableStatement.setString(18, pin);
				callableStatement.setString(19, pan);
				callableStatement.setString(20, voterId);
				callableStatement.setTimestamp(21, joiningDate);
				callableStatement.setLong  (22, userId);
				callableStatement.setString(23, userType1);
				
				callableStatement.execute();  				
					
				System.out.println("SQL- setFranchise executed");
				
				connection.commit();
				
				return true;
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				try {
					callableStatement.close();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				try {
					connection.rollback();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				
				e.printStackTrace();
			}
			finally {
				try {
					callableStatement.close();;
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				try {
					connection.close();;
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				System.gc();
			}
		
		
		return false;
		
	} // setFranchise()	
	
	public synchronized static boolean setDistributor(String dUserId, String password, String fName, String lName, String sex, String company, String mobile1, String mobile2, 
			String email1, String email2, String phone1, String phone2, String fax1, String fax2, String address, String city, String state, 
			String pin, String pan, String voterId, java.sql.Timestamp joiningDate, long userId, UserType userType,
			String choosePin, String area) {
		
			String userType1 = null;
		
			if (userType == UserType.FRANCHISE)  userType1 = "franchise";
			
			Connection connection = null;
			CallableStatement callableStatement = null;
		
			String sql = "{call createDistributor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
			try {
				
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
					
				callableStatement = connection.prepareCall(sql);
				
				callableStatement.setString(1,  dUserId);
				callableStatement.setString(2,  password);
				callableStatement.setString(3,  fName);
				callableStatement.setString(4,  lName);
				callableStatement.setString(5,  sex);
				callableStatement.setString(6,  company);
				callableStatement.setString(7,  mobile1);
				callableStatement.setString(8,  mobile2);
				callableStatement.setString(9,  email1);
				callableStatement.setString(10, email2);
				callableStatement.setString(11, phone1);
				callableStatement.setString(12, phone2);
				callableStatement.setString(13, fax1);
				callableStatement.setString(14, fax2);
				callableStatement.setString(15, address);
				callableStatement.setString(16, city);
				callableStatement.setString(17, state);
				callableStatement.setString(18, pin);
				callableStatement.setString(19, pan);
				callableStatement.setString(20, voterId);
				callableStatement.setTimestamp(21, joiningDate);
				callableStatement.setLong  (22, userId);
				callableStatement.setString(23, userType1);
				callableStatement.setString(24, choosePin);
				callableStatement.setString(25, area);
				
				callableStatement.execute();  				
					
				System.out.println("SQL- setDistributor executed");
				
				connection.commit();
				
				return true;
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				
				try {
					connection.rollback();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				
				e.printStackTrace();
			}
			finally {
				try {
					callableStatement.close();;
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				try {
					connection.close();;
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				System.gc();
			}
		
		
		return false;
		
	} // setDistributor()	
	
	
	/*public synchronized static boolean setCustomerLogin(String Userid, String Password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		
		try {
			 connection = ConnectionFactory.getNewConnection();
			 connection.setAutoCommit(false);
			 
			 sql = "INSERT INTO user (user_id, password1) VALUES (?,?)";
			 
				preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setString(1, Userid);
				preparedStatement.setString(2, Password);

				preparedStatement.executeUpdate();
		
				System.out.println("SQL - Executed setRegistration Login for Customer");
				
				connection.commit();
				
				return true;
				
		}  catch (InstantiationException | IllegalAccessException
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
	} //setRegistration Login for Customer
*/	
	
	//Soumya
/*	public synchronized static boolean setUserRegistration (String User_Id, String Password, String First_Name, String Last_Name, 
			 String Gender, String Email, String Contact_Number, String Address, String Pin, String City, String State, 
			 String First_Name2, String Last_Name2, String Email2, String Contact_Number2, String Address2, String Pin2, String City2, String State2) {
			 
			 Connection connection = null;
			 CallableStatement callableStatement = null;
			 boolean exist = false;
			 String User_type = "customer";
			 
			 try{
				 
				 connection = ConnectionFactory.getNewConnection();
				 connection.setAutoCommit(false);
				 
				 callableStatement = connection.prepareCall("{call registerUser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				 
				 callableStatement.setString(1, User_Id);
				 callableStatement.setString(2, Password);
				 callableStatement.setString(3, First_Name);
				 callableStatement.setString(4, Last_Name);
				 callableStatement.setString(5, Gender);
				 callableStatement.setString(6, Email);
				 callableStatement.setString(7, Contact_Number);
				 callableStatement.setString(8, Address);
				 callableStatement.setString(9, Pin);
				 callableStatement.setString(10, City);
				 callableStatement.setString(11, State);
				 callableStatement.setString(12, User_type);
				 callableStatement.setString(13, First_Name2);
				 callableStatement.setString(14, Last_Name2);
				 callableStatement.setString(15, Email2);
				 callableStatement.setString(16, Contact_Number2);
				 callableStatement.setString(17, Address2);
				 callableStatement.setString(18, Pin2);
				 callableStatement.setString(19, City2);
				 callableStatement.setString(20, State2);
				 
				 callableStatement.registerOutParameter(21, Types.BOOLEAN);
				 
			     callableStatement.execute() ; 
				 
				 exist = callableStatement.getBoolean(21);
				 
				 System.out.println("SQL - setregisterCustomer");
				 
				 connection.commit();
				 
				 return exist;
				
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
			}
			 return false;
	}*/
	
	//Soumya
	public synchronized static boolean setSellerRegistration (String User_Id, String Password, String First_Name, String Last_Name,
		   String Gender, String Company, String Mobile_Number1, String Mobile_Number2, String Email1, String Email2, 
		   String Landphone1, String Landphone2, String Fax1, String Fax2, String Address_Line1, String Address_Line2, 
		   String City, String State, String Pin, String Country, String Pancard, String VoterId, 
		   String First_Name2, String Last_Name2, String Company2, String Mobile_Number3, String Address_Line3, 
		   String Address_Line4, String City2, String Pin2, String State2, String Country2, String Email3) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;
		boolean exist = false;
		String User_type = "seller";
		
		try {
			 
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall 
					         ("{call registerSeller(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			callableStatement.setString(1, User_Id        );
			callableStatement.setString(2, Password       );
			callableStatement.setString(3, First_Name     );
			callableStatement.setString(4, Last_Name      );
			callableStatement.setString(5, Gender         );
			callableStatement.setString(6, Company        );
			callableStatement.setString(7, Mobile_Number1 );
			callableStatement.setString(8, Mobile_Number2 );
			callableStatement.setString(9, Landphone1     );
			callableStatement.setString(10, Landphone2    );
			callableStatement.setString(11, Email1        );
			callableStatement.setString(12, Email2        );
			callableStatement.setString(13, Fax1          );
			callableStatement.setString(14, Fax2          );
			callableStatement.setString(15, Address_Line1 );
			callableStatement.setString(16, Address_Line2 );
			callableStatement.setString(17, City          );
			callableStatement.setString(18, State         );
			callableStatement.setString(19, Pin           );
			callableStatement.setString(20, Country       );
			callableStatement.setString(21, Pancard       );
			callableStatement.setString(22, VoterId       );
			callableStatement.setString(23, User_type     );
			
			callableStatement.setString(24, First_Name2   );
			callableStatement.setString(25, Last_Name2    );
			callableStatement.setString(26, Company2      );
			callableStatement.setString(27, Mobile_Number3);
			callableStatement.setString(28, Address_Line3 );
			callableStatement.setString(29, Address_Line4 );
			callableStatement.setString(30, City2         );
			callableStatement.setString(31, Pin2          );
			callableStatement.setString(32, State2        );
			callableStatement.setString(33, Country2      );
			callableStatement.setString(34, Email3        );
			
		    callableStatement.registerOutParameter(35, Types.BOOLEAN);

			callableStatement.execute();
			
			exist = callableStatement.getBoolean(35);
			
			System.out.println("SQL - setregisterSeller UPDATED");
			
			 connection.commit();

			 return exist;

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
	}
	
	//Soumya SP
	public synchronized static boolean setCustomerRegistration (String User_Id, String Password, String First_Name1, String Last_Name1,
			   String Gender, String Company1, String Mobile_Number1, String Mobile_Number2, String Email1, String Email2, 
			   String Address_Line1, String Address_Line2, String City, String State, String Pin, String Country, 
			   String First_Name2, String Last_Name2, String Company2, String Contact, String Address_Line3, 
			   String Address_Line4, String City2, String Pin2, String State2, String Country2, String Email3) {
			
			Connection connection = null;
			CallableStatement callableStatement = null;
			boolean exist = false;
			String User_type = "customer";
			
			try {
				 
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall 
						         ("{call registerCustomer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				
				callableStatement.setString(1, User_Id       );
				callableStatement.setString(2, Password      );
				callableStatement.setString(3, First_Name1   );
				callableStatement.setString(4, Last_Name1    );
				callableStatement.setString(5, Gender        );
				callableStatement.setString(6, Company1      );
				callableStatement.setString(7, Mobile_Number1);
				callableStatement.setString(8, Mobile_Number2);
				callableStatement.setString(9, Email1        );
				callableStatement.setString(10, Email2       );
				callableStatement.setString(11, Address_Line1);
				callableStatement.setString(12, Address_Line2);
				callableStatement.setString(13, City         );
				callableStatement.setString(14, State        );
				callableStatement.setString(15, Pin          );
				callableStatement.setString(16, Country      );
				callableStatement.setString(17, User_type    );
				
				callableStatement.setString(18, First_Name2  );
				callableStatement.setString(19, Last_Name2   );
				callableStatement.setString(20, Company2     );
				callableStatement.setString(21, Contact      );
				callableStatement.setString(22, Address_Line3);
				callableStatement.setString(23, Address_Line4);
				callableStatement.setString(24, City2        );
				callableStatement.setString(25, Pin2         );
				callableStatement.setString(26, State2       );
				callableStatement.setString(27, Country2     );
				callableStatement.setString(28, Email3       );
				
			    callableStatement.registerOutParameter(29, Types.BOOLEAN);

				callableStatement.execute();
				
				exist = callableStatement.getBoolean(29);
				
				System.out.println("SQL - setregisterCustomer UPDATED");
				
				 connection.commit();

				 return exist;

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
		}

	//Soumya DAO 
	public synchronized static String CheckCustomerUserIdFromDB (String User_Id) {
		
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
			     msg = null;
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
	

	public synchronized static int createCustomer(String userId, String password, String[] fullName) throws Exception {	
		
		String firstName = "";
		String lastName = "";
		
		if (fullName.length > 1) {
			firstName = fullName[0];
			lastName = fullName[1];
		} else {
			firstName = fullName[0];
		}
		
		Connection connection = null;
		CallableStatement callableStatement = null;
	
		String sql = "{call createCustomer(?,?,?,?,?,?)}";
		int userIdNo = -1;  //'0' user exists, 'maxId > 0' user created, '-1' some error occurred
		
		Calendar calendar = new GregorianCalendar();
		String date = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
				
			callableStatement = connection.prepareCall(sql);
			
			callableStatement.setString(1, userId);
			callableStatement.setString(2, password);	
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.setString(4, date);
			callableStatement.setString(5, firstName);
			callableStatement.setString(6, lastName);
			
			callableStatement.execute(); 
			
			userIdNo = callableStatement.getInt(3);
				
			System.out.println("SQL- createCustomer executed");
			
			connection.commit();
			
			return userIdNo;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }			
			e.printStackTrace();  throw e;
		}
		finally {
			try { callableStatement.close(); } catch (SQLException e1) { e1.printStackTrace(); }
			try { connection.close();        } catch (SQLException e1) { e1.printStackTrace(); }
			System.gc();
		}			
		
	} //createCustomer
	
	
	public static void deleteCustomer(int userIdNo) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		String sql = "DELETE FROM user where id = ?";		
		
		try{
			
			connection = ConnectionFactory.getNewConnection();		
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userIdNo);
			
			preparedStatement.executeUpdate();
			
			System.out.println("SQL- deleteCustomer executed");			
			connection.commit();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }			
			e.printStackTrace();  
		}
		finally {
			try { preparedStatement.close(); } catch (SQLException e1) { e1.printStackTrace(); }
			try { connection.close();        } catch (SQLException e1) { e1.printStackTrace(); }
			System.gc();
		}	
	}//deleteCustomer
	
}
