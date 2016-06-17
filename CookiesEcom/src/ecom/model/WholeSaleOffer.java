package ecom.model;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ecom.common.ConnectionFactory;

public class WholeSaleOffer implements Serializable {
	private static final long serialVersionUID = -8401736384071746587L;
	
	
	private long productId;
	private int qty;
	private double discount;
	
	
	public long getProductId() {
		return productId;
	}
	public int getQty() {
		return qty;
	}
	public double getDiscount() {
		return discount;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	//----------------------------------------------------------------------DAO
	
	public static WholeSaleOffer getWholeSaleOffer(long productId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		ResultSet resultSet = null;	
		WholeSaleOffer offer = null;
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "SELECT * FROM whole_sale_offer WHERE product_id = ?";
				
			preparedStatement = connection.prepareStatement(sql);				
			preparedStatement.setLong(1, productId);   
			 			
			resultSet = preparedStatement.executeQuery();	
			 			
			if (resultSet.next()) {
				offer = new WholeSaleOffer();
				offer.setProductId(resultSet.getLong  ("product_id"));
				offer.setQty      (resultSet.getInt   ("qty"       ));
				offer.setDiscount (resultSet.getDouble("discount"  ));
			}
			
			connection.commit();
			
			System.out.println("SQL getWholeSaleOffer Executed");
			
			return offer;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {				
			offer = null;
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}			
		
		return null;
	}//getWholeSaleOffer
	
	
	public synchronized static long setWholeSaleOffer(long productId, int quantity, double discount) {
		
		Connection connection = null; CallableStatement callableStatement = null; 		
		String sql = "{call setWholeSaleOffer(?,?,?)}";	
		long insertedproductId = 0;
		boolean inserted = false;
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql); 				
					
			callableStatement.setLong  (1, productId);
			callableStatement.setInt   (2, quantity);
			callableStatement.setDouble(3, discount);
			
			callableStatement.registerOutParameter(1, Types.INTEGER);
				
			callableStatement.execute();
			
			insertedproductId = callableStatement.getInt(1);			
			
			connection.commit();					
			System.out.println("SQL - setWholeSaleOffer executed");						
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
			if (insertedproductId > 0)  {
				inserted = true;
				insertedproductId = -1;
			}
			
			return insertedproductId;
			
		} finally {			
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			
			if (inserted == true)
				deleteRow(insertedproductId);
			
			System.gc();			
		} 	
		
		return insertedproductId;
	}//setWholeSaleOffer
	
	public static void deleteRow(long productId) {
		
		Connection connection = null; PreparedStatement preparedStatement = null; 		
		String sql = "delete from whole_sale_offer where product_id = ?";		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);			
					
			preparedStatement.setLong(1, productId);
			
			preparedStatement.executeUpdate();		
			
			connection.commit();					
			System.out.println("SQL - deleteRow executed");						
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();			
		} finally {			
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			
			System.gc();			
		} 			
		
	}//deleteRow
	
	
	public static void main(String...args) {	
		
		WholeSaleOffer.deleteRow(12);
		
	}
}
