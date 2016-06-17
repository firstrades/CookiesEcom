package ecom.DAO.Buyer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ecom.common.ConnectionFactory;
import ecom.model.TwoObjects;

public class ProductDetailsDAO {
	
	public static TwoObjects<Double, String> getSellPriceAndWarranty(long productId) {		
		
		String sql = null;			
		TwoObjects<Double, String> twoObjects = new TwoObjects<>();		
		sql = "SELECT salePriceCustomer, warranty FROM product WHERE id = ?";
		
		try (Connection connection = ConnectionFactory.getNewConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {					
							
				preparedStatement.setLong(1, productId);	
				
						try (ResultSet resultSet = preparedStatement.executeQuery()) {					
						
							if (resultSet.next()) {											
								
								twoObjects.setObj1(resultSet.getDouble("salePriceCustomer"));
								twoObjects.setObj2(resultSet.getString("warranty"  ));					
							}	 
						} catch(SQLException e) { throw e; }
				
				System.out.println("SQL getSellPriceAndWarranty(long productId) Executed");
				return twoObjects;
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {				
			e.printStackTrace();				
		} finally {
			twoObjects = null;
			System.gc();
		}
		
		return null;
	}//getSellPriceAndWarranty
	
	
	
	
	

	

}
