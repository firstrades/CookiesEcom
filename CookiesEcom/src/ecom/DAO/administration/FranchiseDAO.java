package ecom.DAO.administration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import ecom.common.ConnectionFactory;
import ecom.model.ExtractDistributorDetails;
import ecom.model.FranchisePins;

public class FranchiseDAO {
	
	private static FranchiseDAO franchiseDAO;
	
	private FranchiseDAO() {
		franchiseDAO = null;
	}
	
	public static FranchiseDAO getInstance() {
		
		if (franchiseDAO == null) {			
			synchronized (FranchiseDAO.class) {				
				if (franchiseDAO == null)
					franchiseDAO = new FranchiseDAO();
			}			
		}
		
		return franchiseDAO;
	}
	
	

	public String getPins(long userId) {
		
			Connection connection = null;
			PreparedStatement preparedStatement = null;		
			ResultSet resultSet = null;
			String sql = "SELECT pin FROM franchise_pin_map WHERE f_id = ?";			
			String options = "<option value='null'>---Select Sex---</option>";
		
			try {
				
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				preparedStatement = connection.prepareStatement(sql);  
				preparedStatement.setLong(1, userId);					 								
				
				resultSet = preparedStatement.executeQuery();				
				
				while (resultSet.next()) {
					
					String value = resultSet.getString("pin");
					
					options += "<option value='"+ value +"'>" + value + "</option>";
				}
				
				connection.commit();					
				System.out.println("SQL - getPins executed");
					
				return options;
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				try { connection.rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
				e.printStackTrace();
			} finally {
				
				try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
				System.gc();
			}
		
		
		return null;
	}
	
	public List<ExtractDistributorDetails> getPinAreaCommission(long f_user_id) {
		
			Connection connection = null;
			CallableStatement callableStatement = null;
			ResultSet resultSet = null;
			String sql = "{call extractDistributorDetails(?)}";
			
			List<ExtractDistributorDetails> list = new ArrayList<>();			
			
		
			try {
				
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				callableStatement.setLong(1, f_user_id);
				
				resultSet = callableStatement.executeQuery();
				
				while (resultSet.next()) {
					
					ExtractDistributorDetails extractDistributorDetails = new ExtractDistributorDetails();				
					
					extractDistributorDetails.getUser().getUserInfo().setId         (resultSet.getLong  ("id"        ));
					extractDistributorDetails.getUser().getUserInfo().setCompany    (resultSet.getString("company"   ));
					extractDistributorDetails.getUser().getPerson().setFirstName    (resultSet.getString("first_name"));
					extractDistributorDetails.getUser().getPerson().setLastName     (resultSet.getString("last_name" ));
					extractDistributorDetails.getUser().getUserInfo().setBalance    (resultSet.getDouble("balance"   ));
					
					extractDistributorDetails.getPinAreaMap().setPin             (resultSet.getString("pin"       ));				
					extractDistributorDetails.getPinAreaMap().setArea            (resultSet.getString("area"      ));
					
					extractDistributorDetails.getCommission().setFranchiseCommission(resultSet.getDouble("commission"));
					
					list.add(extractDistributorDetails);
					
				}
				
				
				connection.commit();
				System.out.println("SQL - getPinAreaCommission executed");
				
				return list;
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				try { connection.rollback();     } catch (SQLException e1) {	e1.printStackTrace(); }
				e.printStackTrace();
			} finally {
				list = null;
				try { resultSet.close();         } catch (SQLException e) { e.printStackTrace(); }
				try { callableStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
				System.gc();
			}
			
			return null;
			
	} // getPinAreaCommission
	
	public FranchisePins getPins1(long userId) {
		
			Connection connection = null;
			PreparedStatement preparedStatement = null;		
			ResultSet resultSet = null;
			String sql = "SELECT pin FROM franchise_pin_map WHERE f_id = ?";
			FranchisePins franchisePins = new FranchisePins();
		
			try {
				
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				preparedStatement = connection.prepareStatement(sql);  
				preparedStatement.setLong(1, userId);					 								
				
				resultSet = preparedStatement.executeQuery();				
				
				if (resultSet.next()) {
					
					franchisePins.setPin1(resultSet.getString("pin"));
					
					resultSet.next();
					franchisePins.setPin2(resultSet.getString("pin"));
					
					resultSet.next();
					franchisePins.setPin3(resultSet.getString("pin"));
					
					resultSet.next();
					franchisePins.setPin4(resultSet.getString("pin"));
					
					resultSet.next();
					franchisePins.setPin5(resultSet.getString("pin"));
				}
				
				connection.commit();					
				System.out.println("SQL - getPins executed");
					
				return franchisePins;
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
				e.printStackTrace();
			} finally {
				franchisePins = null;
				try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
				System.gc();
			}
		
		
			return null;
	} // getPins1
	
	public String setPin(long d_id, String pin) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;		
		String sql = "{call setDistributorPin(?,?)}";		
		String returnPin = null; 
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);
			callableStatement.setLong  (1, d_id);
			callableStatement.setString(2, pin);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			
			callableStatement.execute();
			
			returnPin = callableStatement.getString(2);
			
			connection.commit();
			System.out.println("SQL - setPin executed");
			
			return returnPin;
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
		} finally {					
			try { callableStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}
		
		return null;
	} // setPin
	
	
	public String setArea(long d_id, String area) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;		
		String sql = "{call setDistributorArea(?,?)}";		
		String returnArea = null; 
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);
			callableStatement.setLong  (1, d_id);
			callableStatement.setString(2, area);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			
			callableStatement.execute();
			
			returnArea = callableStatement.getString(2);
			
			connection.commit();
			System.out.println("SQL - setArea executed");
			
			return returnArea;
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
		} finally {				
			try { callableStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}
		
		return null;
	} // setArea
	
	public double setCommission(long d_id, double commission) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;		
		String sql = "{call setDistributorCommission(?,?)}";		
		double returnCommission = 0; 
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);
			callableStatement.setLong  (1, d_id);
			callableStatement.setDouble(2, commission);
			callableStatement.registerOutParameter(2, Types.DOUBLE);
			
			callableStatement.execute();
			
			returnCommission = callableStatement.getDouble(2);
			
			connection.commit();
			System.out.println("SQL - setCommission executed");
			
			return returnCommission;
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) {	e1.printStackTrace(); }
			e.printStackTrace();
		} finally {				
			try { callableStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}
		
		return 0.0;
	} // setArea
	
	public double setAddtionalBalance(long id, double addtionalBalance) {
		
			Connection connection = null;
			CallableStatement callableStatement = null;			
			String sql = "{call setAddtionalBalance2(?,?,?)}";			
			double balance = 0;
		
			try {
				
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);    
				callableStatement.setLong  (1, id);     
				callableStatement.setDouble(2, addtionalBalance);	
				callableStatement.registerOutParameter(3, java.sql.Types.DOUBLE);
				
				callableStatement.execute();
				
				balance = callableStatement.getDouble(3);  System.out.println(balance);
				
				connection.commit();					
				System.out.println("SQL - setAddtionalBalance2 executed");
				
				return balance;
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				try { connection.rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
				e.printStackTrace();
			} finally {				
				try { callableStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
				System.gc();
			}
			
			return balance;
	
	}  // setAddtionalBalance
}
