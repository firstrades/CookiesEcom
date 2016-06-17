package ecom.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ecom.common.ConnectionFactory;

public class TransientData {

	public synchronized static int getStock(long productId) {		
				
				
				String sql = "SELECT stock FROM product WHERE id = ?";
				ResultSet resultSet = null;
				int stock = 9999999;
				
				try (Connection connection = ConnectionFactory.getNewConnection();
					 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					
					connection.setAutoCommit(false);
					
					preparedStatement.setLong(1, productId);
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet.next()) {
						
						stock = resultSet.getInt("stock");
					}
					
					
					connection.commit();
					
					System.out.println("SQL - getStock()  Executed");
					
					return stock;
					
				} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException sqlException) {			
					sqlException.printStackTrace();
				} finally {			
					try {
						resultSet.close();  
					} catch (SQLException e) {				
						e.printStackTrace();
					}
					System.gc();
				}	
				
				return stock;
	}
	
	public static int getMAX(long userId, String category, String subCategory) {	
		
				
				String sql = "SELECT COUNT(id) FROM product WHERE seller_id = ? AND status = 'approved' AND category = ? AND sub_category = ? ";
				ResultSet resultSet = null;
				int MAX = 0;
				
				try (Connection connection = ConnectionFactory.getNewConnection();
					 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					
					connection.setAutoCommit(false);
					
					preparedStatement.setLong  (1, userId);
					preparedStatement.setString(2, category);
					preparedStatement.setString(3, subCategory);
					
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet.next()) {  
						
						MAX = resultSet.getInt(1);   System.out.println("MAX: " + MAX);
					}					
					
					
					connection.commit();
					
					System.out.println("SQL - getMAX()  Executed");
					
					if (MAX > 10)  return MAX = 10;
					else           return MAX;					
					
					
				} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException sqlException) {			
					sqlException.printStackTrace();
				} finally {			
					try {
						resultSet.close();  
					} catch (SQLException e) {				
						e.printStackTrace();
					}
					System.gc();
				}	
				
				return MAX;
	}
	
	public static void main(String[] args) {
		
		
		System.out.println(TransientData.getStock(229L));
	}
	
}
