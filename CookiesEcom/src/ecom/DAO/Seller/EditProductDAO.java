package ecom.DAO.Seller;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import ecom.common.ConnectionFactory;
import ecom.common.ConnectionFactoryImage;
import ecom.model.KeyFeature;
import ecom.model.Product;
import ecom.model.Size;

public class EditProductDAO {
	
	private static EditProductDAO editProductDAO;
	
	private EditProductDAO() {
		editProductDAO = null;
	}
	
	public static EditProductDAO getInstance() {
		
		if (editProductDAO == null) {			
			synchronized (EditProductDAO.class) {				
				if (editProductDAO == null)
					editProductDAO = new EditProductDAO();
			}			
		}
		
		return editProductDAO;
	}

	public Product editProduct(Product productBean) {			
		
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;		
		String sql = "{call editProduct(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";			
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql); 		
			
			callableStatement.setLong  (1,   productBean.getProductId());
			
			callableStatement.setString(2,   productBean.getCategory()   );
			callableStatement.setString(3,   productBean.getSubCategory());
			callableStatement.setString(4,   productBean.getCompanyName());
			callableStatement.setString(5,   productBean.getProductName());
			
			
			
			callableStatement.setDouble(6,  productBean.getPrice().getManufacturingCost()     );
			callableStatement.setDouble(7,  productBean.getPrice().getProfitMarginPercentage());
			callableStatement.setDouble(8,  productBean.getPrice().getSalePriceToAdmin()      );
			callableStatement.setDouble(9,  productBean.getPrice().getSalePriceCustomer()     );
			callableStatement.setDouble(10,  productBean.getPrice().getMarkup()                );
			callableStatement.setDouble(11,  productBean.getPrice().getListPrice()             );
			callableStatement.setDouble(12,  productBean.getPrice().getDiscount()              );			
			
			callableStatement.setInt   (13,  productBean.getStock()                  );
			callableStatement.setDouble(14,  productBean.getWeight()                 );
			callableStatement.setString(15,  productBean.getWarranty()               );
			callableStatement.setInt   (16,  productBean.getCancellationAfterBooked());			
			
			resultSet = callableStatement.executeQuery();
			
			while (resultSet.next()) {
				
				productBean.setProductId                 (resultSet.getInt   ("id")  );				
				
				productBean.setCategory                  (resultSet.getString("category"    ));
				productBean.setSubCategory               (resultSet.getString("sub_category"));
				productBean.setCompanyName               (resultSet.getString("company_name"));
				productBean.setProductName               (resultSet.getString("product_name"));
				
				
				
				productBean.getPrice().setManufacturingCost     (resultSet.getDouble("manufacturingCost"     ));
				productBean.getPrice().setProfitMarginPercentage(resultSet.getDouble("profitMarginPercentage"));
				productBean.getPrice().setSalePriceToAdmin      (resultSet.getDouble("sale_price"            ));
				productBean.getPrice().setSalePriceCustomer     (resultSet.getDouble("salePriceCustomer"     ));
				productBean.getPrice().setMarkup                (resultSet.getDouble("markup"                ));				
				productBean.getPrice().setListPrice             (resultSet.getDouble("list_price"            ));
				productBean.getPrice().setDiscount              (resultSet.getDouble("discount"              ));								
				
				productBean.setStock                     (resultSet.getInt   ("stock"                    ));
				productBean.setWeight                    (resultSet.getDouble("weight"                   ));
				productBean.setWarranty                  (resultSet.getString("warranty"                 ));
				productBean.setCancellationAfterBooked   (resultSet.getInt   ("calcellation_after_booked"));
			}
			
			
			connection.commit();					
			System.out.println("SQL - editProduct executed");
			
			return productBean;			
			
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
		
		return null;
		
	}// editProduct
	
	
	
	/***  Delete It ***/
	
	public Product getBasicFeatures(long productId) {		
		
		Connection connection = null; PreparedStatement preparedStatement = null; String sql = null; ResultSet resultSet = null;		
		Product productBean = new Product();		
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "SELECT * FROM product WHERE id = ?";
				
			preparedStatement = connection.prepareStatement(sql);			
			preparedStatement.setLong (1,  productId);			
		
			resultSet = preparedStatement.executeQuery();	
			
			while (resultSet.next()) {
				
				productBean.setProductId                 (resultSet.getInt   ("id"          ));				
				
				productBean.setCategory                  (resultSet.getString("category"    ));
				productBean.setSubCategory               (resultSet.getString("sub_category"));
				productBean.setCompanyName               (resultSet.getString("company_name"));
				productBean.setProductName               (resultSet.getString("product_name"));
				
				
				
				productBean.getPrice().setManufacturingCost     (resultSet.getDouble("manufacturingCost"     ));
				productBean.getPrice().setProfitMarginPercentage(resultSet.getDouble("profitMarginPercentage"));
				productBean.getPrice().setSalePriceToAdmin      (resultSet.getDouble("sale_price"            ));
				productBean.getPrice().setSalePriceCustomer     (resultSet.getDouble("salePriceCustomer"     ));
				productBean.getPrice().setMarkup                (resultSet.getDouble("markup"                ));				
				productBean.getPrice().setListPrice             (resultSet.getDouble("list_price"            ));
				productBean.getPrice().setDiscount              (resultSet.getDouble("discount"              ));								
				
				productBean.setStock                     (resultSet.getInt   ("stock"                    ));
				productBean.setWeight                    (resultSet.getDouble("weight"                   ));
				productBean.setWarranty                  (resultSet.getString("warranty"                 ));
				productBean.setCancellationAfterBooked   (resultSet.getInt   ("calcellation_after_booked"));
				
				productBean.setSellerCompany             (resultSet.getString("seller_company"));
			}
			
			connection.commit();
			
			System.out.println("SQL getBasicFeatures Executed");
			
			return productBean;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e1) {
			try { connection.rollback();     } catch (SQLException e) { e.printStackTrace(); }
			e1.printStackTrace();
		} finally {
			productBean = null;
			try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}		
		
		return null;
	}
	
	
	
	public boolean editImage(long productId, InputStream inputStream, String image) {	
		
		Connection connection = null; CallableStatement callableStatement = null; 
		String sql = "{call editImage(?,?,?,?)}";	
		boolean status = false;
		
		
		try {
				connection = ConnectionFactoryImage.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				
				callableStatement.setLong  (1, productId);
				callableStatement.setBlob  (2, inputStream);
				callableStatement.setString(3, image);	
				callableStatement.registerOutParameter(4, Types.BOOLEAN);
				
				callableStatement.execute();
				
				status = callableStatement.getBoolean(4);
				
				connection.commit();				
				System.out.println("SQL - Select editImage() successfull.");
				
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
	}
	
	
	public boolean editKeyFeaturesAndSizes(List<KeyFeature> updateKeyFeatures, List<Size> updateSize, Product productBean) {
		
		Connection connection = null; Statement statement = null;		
		String sqlKeyFeature = null; String sqlSizes = null;		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			
			for (KeyFeature keyFeature : updateKeyFeatures) {
				
				sqlKeyFeature = "update key_features set header = '"+keyFeature.getKey()+"', value = '"+keyFeature.getValue()
						+"' where id = "+keyFeature.getId();
				System.out.println(sqlKeyFeature);
				statement.addBatch(sqlKeyFeature);
			}
			
			for (Size size : updateSize) {
				
				sqlSizes = "update size set size = '"+size.getSize()+"', count = "+size.getCount()
						+" where id = "+size.getId();
				System.out.println(sqlSizes);
				statement.addBatch(sqlSizes);
			}
			
			
			
			int[] count = statement.executeBatch();  System.out.println("Length: " + count.length);
			
			connection.commit();					
			System.out.println("SQL - editKeyFeaturesAndSizes executed");
			
			return true;		
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
			
		} finally {			
			try { statement.close();  } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close(); } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		
		return false;
		
	} //editKeyFeaturesAndSizes
	
	
	public boolean newKeyFeaturesAndSizes(List<KeyFeature> newKeyFeatures, List<Size> newSize, Product productBean) {
		
		Connection connection = null; Statement statement = null;		
		String sqlKeyFeature = null;  String sqlSizes = null;		
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			
			for (KeyFeature keyFeature : newKeyFeatures) {
				
				sqlKeyFeature = "insert into key_features (product_id, header, value)"
						+ " values("+keyFeature.getProductId()+", '"+keyFeature.getKey()+"', '"+keyFeature.getValue()+"')";
				
				System.out.println(sqlKeyFeature);
				statement.addBatch(sqlKeyFeature);
			}
			
			for (Size size : newSize) {
				
				sqlSizes = "insert into size (product_id, size, count)"
						+ " values("+size.getProductId()+", '"+size.getSize()+"', '"+size.getCount()+"')";
				
				System.out.println(sqlSizes);				
				statement.addBatch(sqlSizes);
			}
			
			
			
			int[] count = statement.executeBatch();  System.out.println("Length: " + count.length);
			
			connection.commit();					
			System.out.println("SQL - newKeyFeaturesAndSizes executed");
			
			
			return true;		
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();			
			
		} finally {
			
			try { statement.close();  } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close(); } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		
		return false;
		
	} //newKeyFeaturesAndSizes
	
	
	public boolean editShippingDelivery(Product product) {
		
		Connection connection = null; PreparedStatement preparedStatement = null;
		String sql = "UPDATE shipping_delivery SET shippingCost = ?, deliveryTime = ? WHERE product_id = ?";
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, product.getShippingCost());
			preparedStatement.setInt   (2, product.getDeliveryTime());
			preparedStatement.setLong  (3, product.getProductId()   );
			
			preparedStatement.executeUpdate();
			
			connection.commit();					
			System.out.println("SQL - editShippingDelivery executed");
			
			return true;		
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
			
		} finally {			
			try { preparedStatement.close();  } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();         } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		
		return false;
		
	} //editShippingDelivery
	
}
