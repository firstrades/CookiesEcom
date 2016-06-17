package ecom.DAO.Buyer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import ecom.common.ConnectionFactory;

public class SearchDAO {

	private static SearchDAO searchDAO;
	
	private SearchDAO() {
		searchDAO = null;
	}
	
	public static SearchDAO getInstance() {
		
		if (searchDAO == null) {			
			synchronized (SearchDAO.class) {				
				if (searchDAO == null)
					searchDAO = new SearchDAO();
			}			
		}
		
		return searchDAO;
	}
	
	public void searchKeyWordsFromCompanyName(String keyWord, Set<String> keyWordSet) {
		
		Connection connection   = null;
		CallableStatement callableStatement = null; ResultSet resultSet = null;
		String sql = "{call searchKeyWordsFromCompanyName(?)}";			
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				callableStatement.setString(1, keyWord);				
				 			
				resultSet = callableStatement.executeQuery();	
				
				while (resultSet.next()) {
					
					keyWordSet.add(resultSet.getString(1));
				}
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select searchKeyWordsFromCompanyName() successfull.");				
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {		 
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		
	}//searchKeyWordsFromCompanyName
	
	public void searchKeyWordsFromSubCategory(String keyWord, Set<String> keyWordSet) {
		
		Connection connection   = null;
		CallableStatement callableStatement = null; ResultSet resultSet = null;
		String sql = "{call searchKeyWordsFromSubCategory(?)}";			
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				callableStatement.setString(1, keyWord);				
				 			
				resultSet = callableStatement.executeQuery();	
				
				while (resultSet.next()) {
					
					keyWordSet.add(resultSet.getString(1));
				}
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select searchKeyWordsFromSubCategory() successfull.");				
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {		 
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		
	}//searchKeyWordsFromSubCategory
	
	public void searchKeyWords(String keyWord, Set<String> keyWordSet) {
		
		Connection connection   = null;
		CallableStatement callableStatement = null; ResultSet resultSet = null;
		String sql = "{call searchKeyWords(?)}";			
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				callableStatement.setString(1, keyWord);				
				 			
				resultSet = callableStatement.executeQuery();	
				
				while (resultSet.next()) {
					
					keyWordSet.add(resultSet.getString(1));
				}
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select searchKeyWords() successfull.");				
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {		 
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		
	}//searchKeyWords
	
	
	
	public static void main(String...args) {
		
		
		
	}

}
