package ecom.model;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ecom.common.ConnectionFactory;

public class CategoryList implements Serializable {
	private static final long serialVersionUID = -8097720731671155713L;

	private int id;
	private String category;
	private List<String> subCategory;
	
	
	public int getId() {
		return id;
	}
	public String getCategory() {
		return category;
	}
	public List<String> getSubCategory() {
		return subCategory;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setSubCategory(List<String> subCategory) {
		this.subCategory = subCategory;
	}
	
	
	//------------------------------------------------------------------------------DOW
	
	public synchronized static int addCategory(String category) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;
		String sql = null;
		int tableId = -1;
		boolean executed = false;
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "{call addCategory(?,?)}";
				
			callableStatement = connection.prepareCall(sql);				
			callableStatement.registerOutParameter(1, Types.INTEGER); 
			callableStatement.setString(2, category);
			 			
			callableStatement.execute();	
			 			
			tableId = callableStatement.getInt(1);
			
			connection.commit();
			
			System.out.println("SQL addCategory Executed");			
					
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			if (tableId > 0)
				executed = true;
			
		} finally {				
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
			if (executed == true) {
				CategoryList categoryList = new CategoryList();
				categoryList.deleteCategory(tableId);
			}		
		}	
		
		return tableId;
	}//addCategory
	
	
	public int deleteCategory(int tableId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;	
		int status = 0;
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "delete from category_list where id = ?";
				
			preparedStatement = connection.prepareStatement(sql);			 
			preparedStatement.setInt(1, tableId);
			 			
			status = preparedStatement.executeUpdate();		// 0 if not executed(may be row was not there), 1 if executed
			
			connection.commit();
			
			System.out.println("SQL deleteCategory Executed");			
						
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			//return status;
		} finally {				
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();			
		}		
		
		return status;
	}//deleteCategory
	
	
	public Map<Integer,String> getCategoryList() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;	
		Map<Integer,String> categories = new HashMap<Integer, String>();
				
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "select id, category from category_list";
				
			preparedStatement = connection.prepareStatement(sql);			
			 			
			resultSet = preparedStatement.executeQuery();	
			
			while (resultSet.next()) {	
				
				int id = resultSet.getInt("id");
				String category = resultSet.getString("category");
				categories.put(id, category);
			}
			
			connection.commit();
			
			System.out.println("SQL getCategoryList Executed");			
						
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();	
			categories = null;  // in case any error occurrs inside try
		} finally {				
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();			
		}		
		
		return categories;
	}//getCategoryList
	
	
	public static void main(String...args) {
		
		int tableId = addCategory("jewel3");		
		System.out.println(tableId);
		
		/*CategoryList categoryList = new CategoryList();		
		int status = categoryList.deleteCategory(3);
		System.out.println(status);*/
	}
	
}
