package ecom.DAO.Buyer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ecom.common.ConnectionFactory;
import ecom.model.TwoObjects;
import ecom.model.User;

public class CommissionDistributionDAO {	
	
	private CommissionDistributionDAO() {}
	
	public static CommissionDistributionDAO getNewInstance() {
		return new CommissionDistributionDAO();
	}

	/************************ Franchise **************************/
	
	public long getFranchiseId(User user) {
		
		 Connection connection = null;
		 CallableStatement callableStatement = null;
		 ResultSet resultSet = null;
		 
		 long userId = user.getUserInfo().getId(); 
		 long fId = 0;
		 
	       
		 try{
			   connection = ConnectionFactory.getNewConnection();
			   connection.setAutoCommit(false);
			   
			   callableStatement = connection.prepareCall("{call getFranchiseId(?)}");
			   
			   callableStatement.setLong(1, userId);
			   resultSet = callableStatement.executeQuery();
			   
			   if (resultSet.next()) {
				 
				   fId = resultSet.getLong("fId"); 
	
			   }
			    connection.commit();
			    System.out.println("SQL - getFranchiseId Executed");
			     
			    return fId;
			    
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
		   
			return 0;
	} // getFranchiseId
	
	public double getFranchiseDeducedBalance(long productId) {	
		
		Connection connection = null;
		CallableStatement callableStatement = null;	
		ResultSet resultSet = null;
		String sql = "{call getFranchiseDeducedBalance(?)}";			
		
		double sellPriceToAdmin = 0;
		double fCommission = 0;
		double deducedBalance = 0;
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);    
			callableStatement.setLong(1, productId); 	
			
			resultSet = callableStatement.executeQuery();
			
			while (resultSet.next()) {
				
				sellPriceToAdmin      = resultSet.getDouble("sale_price"      );
				fCommission = resultSet.getDouble("f_commission");
			}
			
			deducedBalance = sellPriceToAdmin * ( fCommission / 100 );
			
			connection.commit();					
			System.out.println("SQL - getFranchiseDeducedBalance executed");
			
			return deducedBalance;
			
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
				resultSet.close();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			
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
		
		return 0;
	}
	
	public boolean setFranchiseCommissionBalance(long franchiseId, double newBalance) {		
		
		Connection connection = null;
		CallableStatement callableStatement = null;	
		
		String sql = "{call setFranchiseCommissionBalance(?,?,?)}";	
		
		boolean status = false;
		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);    
			callableStatement.setLong  (1, franchiseId); 	
			callableStatement.setDouble(2, newBalance );
			
			callableStatement.registerOutParameter(3, Types.BOOLEAN);
			
			callableStatement.execute();
			
			status = callableStatement.getBoolean(3);				
			
			connection.commit();					
			System.out.println("SQL - setFranchiseCommissionBalance executed");
			
			return status;
			
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
	
	public boolean returnFranchiseCommissionBalance(long franchiseId, double newBalance) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;	
		
		String sql = "{call returnFranchiseCommissionBalance(?,?,?)}";	
		
		boolean status = false;
		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);    
			callableStatement.setLong  (1, franchiseId); 	
			callableStatement.setDouble(2, newBalance );
			
			callableStatement.registerOutParameter(3, Types.BOOLEAN);
			
			callableStatement.execute();
			
			status = callableStatement.getBoolean(3);				
			
			connection.commit();					
			System.out.println("SQL - returnFranchiseCommissionBalance executed");
			
			return status;
			
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
	
	/************************ Distributor **************************/
	
	public long getFranchiseIdOfDistributor(User user) {
		
		 Connection connection = null;
		 CallableStatement callableStatement = null;
		 ResultSet resultSet = null;
		 
		 long userId = user.getUserInfo().getId(); 
		 long fId = 0;
		 
	       
		 try{
			   connection = ConnectionFactory.getNewConnection();
			   connection.setAutoCommit(false);
			   
			   callableStatement = connection.prepareCall("{call getFranchiseIdOfDistributor(?)}");
			   
			   callableStatement.setLong(1, userId);
			   resultSet = callableStatement.executeQuery();
			   
			   if (resultSet.next()) {
				 
				   fId = resultSet.getLong("f_id"); 
	
			   }
			    connection.commit();
			    System.out.println("SQL - getFranchiseIdOfDistributor Executed");
			     
			    return fId;
			    
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
		   
			return 0;
	} // getFranchiseIdOfDistributor
	
	public TwoObjects<Double, Double> getDistributorFranchiseDeducedBalance(long productId) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;	
		ResultSet resultSet = null;
		String sql = "{call getDistributorFranchiseDeducedBalance(?)}";			
		
		TwoObjects<Double, Double> twoObjects = new TwoObjects<>();
		double salePriceToAdmin = 0;
		double fCommission = 0;
		double dCommission = 0;
		double fDeducedBalance = 0;
		double dDeducedBalance = 0;
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);    
			callableStatement.setLong(1, productId); 	
			
			resultSet = callableStatement.executeQuery();
			
			while (resultSet.next()) {
				
				salePriceToAdmin = resultSet.getDouble("sale_price"      );
				fCommission      = resultSet.getDouble("f_commission");
				dCommission      = resultSet.getDouble("d_commission");
			}
			
			fDeducedBalance = salePriceToAdmin * ( fCommission / 100 );
			dDeducedBalance = salePriceToAdmin * ( dCommission / 100 );
			
			twoObjects.setObj1(fDeducedBalance);
			twoObjects.setObj2(dDeducedBalance);
			
			connection.commit();					
			System.out.println("SQL - getDistributorFranchiseDeducedBalance executed");
			
			return twoObjects;
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {					
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			twoObjects = null;
			try {
				resultSet.close();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			
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
	} // getDistributorFranchiseDeducedBalance
	
	public boolean setDistributorFranchiseCommissionBalance(long franchiseId, long distributorId, double fNewBalance, double dNewBalance) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;	
		
		String sql = "{call setDistributorFranchiseCommissionBalance(?,?,?,?,?)}";	
		
		boolean status = false;
		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);    
			callableStatement.setLong  (1, franchiseId); 	
			callableStatement.setLong  (2, distributorId );
			callableStatement.setDouble(3, fNewBalance );
			callableStatement.setDouble(4, dNewBalance );			
			
			callableStatement.registerOutParameter(5, Types.BOOLEAN);
			
			callableStatement.execute();
			
			status = callableStatement.getBoolean(5);				
			
			connection.commit();					
			System.out.println("SQL - setDistributorFranchiseCommissionBalance executed");
			
			return status;
			
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
		
	} //setDistributorFranchiseCommissionBalance
	
	public boolean returnDistributorFranchiseCommissionBalance(long franchiseId, long distributorId, double fNewBalance, double dNewBalance) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;	
		
		String sql = "{call returnDistributorFranchiseCommissionBalance(?,?,?,?,?)}";	
		
		boolean status = false;
		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql);    
			callableStatement.setLong  (1, franchiseId); 	
			callableStatement.setLong  (2, distributorId );
			callableStatement.setDouble(3, fNewBalance );
			callableStatement.setDouble(4, dNewBalance );
			
			callableStatement.registerOutParameter(5, Types.BOOLEAN);
			
			callableStatement.execute();
			
			status = callableStatement.getBoolean(5);				
			
			connection.commit();					
			System.out.println("SQL - returnDistributorFranchiseCommissionBalance executed");
			
			return status;
			
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
		
	} // returnDistributorFranchiseCommissionBalance
	
	
	
}
