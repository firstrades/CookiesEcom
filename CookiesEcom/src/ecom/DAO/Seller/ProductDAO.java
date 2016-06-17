package ecom.DAO.Seller;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PacketTooBigException;

import ecom.model.KeyFeature;
import ecom.model.Product;
import ecom.model.Size;
import ecom.model.TwoObjects;
import ecom.model.User;
import ecom.common.ConnectionFactory;
import ecom.common.ConnectionFactoryImage;
import ecom.common.Conversions;

public class ProductDAO {
	
	private static ProductDAO productDAO;
	
	private ProductDAO() {
		productDAO = null;
	}
	
	public static ProductDAO getInstance() {
		
		if (productDAO == null) {			
			synchronized (ProductDAO.class) {				
				if (productDAO == null)
					productDAO = new ProductDAO();
			}			
		}
		
		return productDAO;
	}
	
	
	//------------------------------ Product Creation -------------------------------------------//
	

	public static synchronized long addProduct(User user, Product productBean) throws Exception {		
		
		Connection connection = null; CallableStatement callableStatement = null;	
		
		String sql = "{call addProduct(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";			
		long id = -1;
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			callableStatement = connection.prepareCall(sql); 			
			
			callableStatement.registerOutParameter(1, java.sql.Types.INTEGER);
			
			callableStatement.setLong  (2, user.getUserInfo().getId()     );
			callableStatement.setString(3, user.getUserInfo().getCompany());			
			
			callableStatement.setString(4,  productBean.getCategory());
			callableStatement.setString(5,  productBean.getSubCategory());
			callableStatement.setString(6,  productBean.getCompanyName());
			callableStatement.setString(7, productBean.getProductName());			
			
			callableStatement.setDouble(8, productBean.getPrice().getManufacturingCost()     );
			callableStatement.setDouble(9, productBean.getPrice().getProfitMarginPercentage());
			callableStatement.setDouble(10, productBean.getPrice().getSalePriceToAdmin()      );
			callableStatement.setDouble(11, productBean.getPrice().getListPrice()             );
			callableStatement.setDouble(12, productBean.getPrice().getDiscount()              );
			
			callableStatement.setInt   (13, productBean.getStock()                  );
			callableStatement.setDouble(14, productBean.getWeight()                 );
			callableStatement.setString(15, productBean.getWarranty()               );
			callableStatement.setInt   (16, productBean.getCancellationAfterBooked());
			
			callableStatement.execute();
			
			id = callableStatement.getInt(1);  
			
			connection.commit();					
			System.out.println("SQL - addProduct executed");
			
			return id;
			
			
		} catch (PacketTooBigException e) {
			throw e;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		//return id;
	} //addProduct
	
	
	public void addImages(long productId, InputStream inputStream1, InputStream inputStream2, InputStream inputStream3) throws Exception {		
		
		Connection connection = null; PreparedStatement preparedStatement = null;
		
		String sql = "INSERT INTO images (product_id, iconImage, Image1, Image2) VALUES(?, ?, ?, ?)";			
	
		try {
			
			connection = ConnectionFactoryImage.getNewConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);				
			
			preparedStatement.setLong(1, productId);
			preparedStatement.setBlob(2, inputStream1);
			preparedStatement.setBlob(3, inputStream2);
			preparedStatement.setBlob(4, inputStream3);			
			
			preparedStatement.executeUpdate();  
			
			connection.commit();					
			System.out.println("SQL - addImages executed");				
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		//return 0;
	} //addImages
	
	
	
	public int addKeyFeatures(long productId, Product product) throws Exception {		
		
		Connection connection = null; PreparedStatement preparedStatement = null;
		
		String sql = "INSERT INTO key_features (product_id, header, value) VALUES(?, ?, ?)";		
		List<KeyFeature> keyFeatures = product.getKeyFeatures();
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);				
			
			for (KeyFeature keyFeature : keyFeatures) {						
				preparedStatement.setLong  (1, productId);
				preparedStatement.setString(2, keyFeature.getKey()  );  
				preparedStatement.setString(3, keyFeature.getValue());  
				preparedStatement.addBatch();
			}
			
			int[] count = preparedStatement.executeBatch();  System.out.println("Length: " + count.length);
			
			connection.commit();					
			System.out.println("SQL - addKeyFeatures executed");
			
			return count.length;		
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		//return 0;
	} //addKeyFeatures
	
	
	
	
	
	public int addSizes(long productId, Product product) throws Exception {		
		
		Connection connection = null; PreparedStatement preparedStatement = null;
		
		String sql = "INSERT INTO size (product_id, size, count) VALUES(?, ?, ?)";		
		List<Size> sizes = product.getSizes();
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);				
			
			for (Size size : sizes) {						
				preparedStatement.setLong  (1, productId);
				preparedStatement.setString(2, size.getSize() );  
				preparedStatement.setInt   (3, size.getCount());  
				preparedStatement.addBatch();
			}
			
			int[] count = preparedStatement.executeBatch();  System.out.println("Length: " + count.length);
			
			connection.commit();					
			System.out.println("SQL - addSizes executed");
			
			return count.length;		
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		//return 0;
	} //addSizes
	
	
	public void addShippingDelivery(long productId, Product product) {		
		
		Connection connection = null; PreparedStatement preparedStatement = null;
		
		String sql = "INSERT INTO shipping_delivery (product_id, shippingCost, deliveryTime) VALUES(?, ?, ?)";	
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);				
								
			preparedStatement.setLong  (1, productId);
			preparedStatement.setDouble(2, product.getShippingCost());  
			preparedStatement.setInt   (3, product.getDeliveryTime());  			
			
			preparedStatement.executeUpdate();  
			
			connection.commit();					
			System.out.println("SQL - addKeyFeatures executed");			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();			
			
		} finally {			
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		
	} //addKeyFeatures
	
	
	
	public int deleteProduct(long productId) {
		
		Connection connection = null; Statement statement = null;		
		String sql = null;			
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();	
			
			sql = "DELETE FROM product where id = " + productId;
			
			statement.addBatch(sql);			
						
			int[] count = statement.executeBatch();  System.out.println("Length: " + count.length);
			
			connection.commit();					
			System.out.println("SQL - deleteProduct executed");
			
			return count.length;		
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();			
			
		} finally {
			
			try { statement.close();  } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close(); } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		return 0;
	}// deleteProduct
	
	
	
	public int deleteKeyFeatures(long productId) {
		
		Connection connection = null; Statement statement = null;
		String sql = null;			
	
		try {
			
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();	
			
			sql = "DELETE FROM product where id = " + productId;			
			statement.addBatch(sql);	
			
			sql = "DELETE FROM key_features where product_id = " + productId;			
			statement.addBatch(sql);
						
			int[] count = statement.executeBatch();  System.out.println("Length: " + count.length);
			
			connection.commit();					
			System.out.println("SQL - deleteKeyFeatures executed");
			
			return count.length;		
			
		
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();			
			
		} finally {			
			try { statement.close();  } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close(); } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}  
		
		return 0;
	}// deleteKeyFeatures
	
	//------------------------------ End Product Creation -------------------------------------------//
	
	
	
	
	
	
	public List<Product> getProducts(String category, String subCategory, User user) {		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		ResultSet resultSet = null;		
		List<Product> list = new ArrayList<>();		
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "SELECT * FROM product WHERE seller_id = ? AND category = ? AND sub_category = ? AND status = 'approved'";
				
			preparedStatement = connection.prepareStatement(sql);			
			preparedStatement.setLong   (1, user.getUserInfo().getId());
			preparedStatement.setString (2, category);
			preparedStatement.setString (3, subCategory);
		
			resultSet = preparedStatement.executeQuery();	
			
			while (resultSet.next()) { 
				
				Product productBean = new Product();				
				
				productBean.setProductId                 (resultSet.getInt   ("id"          ));
				productBean.setSellerId                  (resultSet.getLong  ("seller_id"   ));
				
				productBean.setCategory                  (resultSet.getString("category"    ));
				productBean.setSubCategory               (resultSet.getString("sub_category"));
				productBean.setProductName               (resultSet.getString("product_name"));
				productBean.setCompanyName               (resultSet.getString("company_name"));
				
				productBean.getPrice().setListPrice      (resultSet.getDouble("list_price"  ));
				productBean.getPrice().setDiscount       (resultSet.getDouble("discount"    ));
				productBean.getPrice().setSalePriceToAdmin(resultSet.getDouble("sale_price" ));
				productBean.getPrice().setMarkup         (resultSet.getDouble("markup"      ));			
				
				productBean.setStatus                    (Conversions.getEnumStatus(resultSet.getString("status")));
				
				productBean.getImages().setIconImage(resultSet.getString("category"));
				productBean.getImages().setIconImage(resultSet.getString("category"));
				productBean.getImages().setIconImage(resultSet.getString("category"));
				
				
				list.add(productBean);
			}
			
			connection.commit();
			
			System.out.println("SQL getProducts Executed");
			
			return list;
			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e1) {
			try {
				connection.rollback();       } catch (SQLException e) {	e.printStackTrace(); }
			e1.printStackTrace();
		} finally {
			list = null;
			try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}			
		
		
		return null;
	}
	
	public boolean deleteProduct(long productId, String subCategory) {		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		
		
		try {
			connection = ConnectionFactory.getNewConnection();
			//connection.setAutoCommit(false);   
			
			sql = "DELETE FROM product WHERE product_id = ?";	
			preparedStatement = connection.prepareStatement(sql);	
			preparedStatement.setLong(1, productId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			/********************************************
			 				* Condition *
			 ********************************************/
			
			if (subCategory.equals("Mobile")) {  // if there is no data, autocommit is restricting from deleteing the rows from from both table
				
				sql = "DELETE FROM mobile_spec WHERE product_id = ?";						
			}
			
			/****************************************/
			
			preparedStatement = connection.prepareStatement(sql);	
			preparedStatement.setLong(1, productId);
			preparedStatement.executeUpdate();  
			
			System.out.println("Delete Product SQL Executed...");
			
			//connection.commit();
			
			return true;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			/*try {
				connection.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}*/
			e.printStackTrace();
		} finally {			
			try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}		
		
		return false;
	} //deleteProduct
	
	
	public Product getProduct(long productId) {
		
		Connection connection = null; PreparedStatement preparedStatement = null; ResultSet resultSet = null;
		String sql = null; 		
		Product product = new Product();		
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "SELECT * FROM product WHERE id = ?";
				
			preparedStatement = connection.prepareStatement(sql);			
			preparedStatement.setLong   (1, productId);			
		
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) { 				
				
				product.setProductId                 (resultSet.getInt   ("id"          ));
				product.setSellerId                  (resultSet.getLong  ("seller_id"   ));
				
				product.setCategory                  (resultSet.getString("category"    ));
				product.setSubCategory               (resultSet.getString("sub_category"));
				product.setProductName               (resultSet.getString("product_name"));
				product.setCompanyName               (resultSet.getString("company_name"));
				
				product.setSellerCompany             (resultSet.getString("seller_company"));
				
				product.getPrice().setManufacturingCost     (resultSet.getDouble("manufacturingCost"     ));
				product.getPrice().setProfitMarginPercentage(resultSet.getDouble("profitMarginPercentage"));
				product.getPrice().setSalePriceToAdmin      (resultSet.getDouble("sale_price"            ));
				product.getPrice().setDiscount              (resultSet.getDouble("discount"              ));	
				product.getPrice().setListPrice             (resultSet.getDouble("list_price"            ));
				product.getPrice().setMarkup                (resultSet.getDouble("markup"                ));
				product.getPrice().setSalePriceCustomer     (resultSet.getDouble("salePriceCustomer"     ));
				
				product.setStock                     (resultSet.getInt   ("stock"       ));		
				product.setWeight                    (resultSet.getDouble("weight"      ));
				product.setWarranty                  (resultSet.getString("warranty"    ));
				product.setCancellationAfterBooked   (resultSet.getInt   ("calcellation_after_booked"    ));
				
				
				
			}
			
			connection.commit();
			
			System.out.println("SQL getProduct Executed");
			
			return product;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e1) {
			try {
				connection.rollback();       } catch (SQLException e) {	e.printStackTrace(); }
			e1.printStackTrace();
		} finally {
			product = null;
			try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}			
		
		return null;
	}//getProduct
	
	
	
	/********************  Get Products - 7 methods **************************/
	
	public List<Product> getProducts() {  // All Products
		return getProducts(0, null, null);
	}
	
	public List<Product> getProducts(long productId) {  // 1 product
		return getProducts(productId, null, null);
	}
	
	public List<Product> getProducts(String[] subCategories) {  // products by subCategory
		return getProducts(0, subCategories, null);
	}
	
	public List<Product> getProducts(Long[] productIds) {  // products by subCategory
		return getProducts(0, null, productIds);
	}
	
	private List<Product> getProducts(
			long     productId      /*default: 0   */,
			String[] subCategories  /*default: null*/,
			Long[]   productIds     /*default: null*/
			) {			
		
		Connection connection = null; Statement statement = null; ResultSet resultSet = null;		
		String sql = getSQLForGetProducts(productId, subCategories, productIds);		
		Product product = null;		
		List<Product> productList = new ArrayList<>();		
		
		try {
			connection = ConnectionFactory.getNewConnection();			
			connection.setAutoCommit(false);		
				
			statement = connection.createStatement();						
			resultSet = statement.executeQuery(sql);				
			 			
			while (resultSet.next()) {
				
				product = new Product();				
				
				product.setProductId                        (resultSet.getInt   ("id"                       ));
				product.setSellerId                         (resultSet.getLong  ("seller_id"                ));
				product.setSellerCompany                    (resultSet.getString("seller_company"           ));                  
				
				product.setCategory                         (resultSet.getString("category"                 ));
				product.setSubCategory                      (resultSet.getString("sub_category"             ));
				product.setProductName                      (resultSet.getString("product_name"             ));
				product.setCompanyName                      (resultSet.getString("company_name"             ));
				
				product.getPrice().setManufacturingCost     (resultSet.getDouble("manufacturingCost"        ));
				product.getPrice().setProfitMarginPercentage(resultSet.getDouble("profitMarginPercentage"   ));
				product.getPrice().setSalePriceToAdmin      (resultSet.getDouble("sale_price"               ));
				product.getPrice().setMarkup                (resultSet.getDouble("markup"                   ));
				product.getPrice().setSalePriceCustomer     (resultSet.getDouble("salePriceCustomer"        ));
				product.getPrice().setListPrice             (resultSet.getDouble("list_price"               ));
				product.getPrice().setDiscount              (resultSet.getDouble("discount"                 ));
								
				product.setStock                            (resultSet.getInt   ("stock"                    ));
				product.setWeight                           (resultSet.getDouble("weight"                   ));
				product.setWarranty                         (resultSet.getString("warranty"                 ));
				product.setCancellationAfterBooked          (resultSet.getInt   ("calcellation_after_booked"));
				product.setStatus(Conversions.getEnumStatus (resultSet.getString("status"                  )));
				
				product.getCommission().setFranchiseCommission  (resultSet.getDouble("f_commission"         ));
				product.getCommission().setDistributorCommission(resultSet.getDouble("d_commission"         ));
				
				product.setProductAdditionDate              (resultSet.getString("productAdditionDate"      ));
				
				//Other Tables with Foreign Key
				product.setKeyFeatures                      (getKeyFeatures(product.getProductId()          ));
				product.setSizes                            (getSizes      (product.getProductId()          ));
				
				productList.add(product);
			}
			
			connection.commit();
			
			System.out.println("SQL getProducts Executed");
			
			return productList;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {				
			productList = null; product = null;
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { statement.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}		
		
		return null;
		
	}//getProducts
	
	
	
	public List<KeyFeature> getKeyFeatures(long productId) {
		
		Connection connection = null; PreparedStatement preparedStatement = null; ResultSet resultSet = null;
		String sql = null; 		
		List<KeyFeature> keyFeatures = new ArrayList<>();	
		KeyFeature keyFeature = null;
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "SELECT * FROM key_features WHERE product_id = ?";
				
			preparedStatement = connection.prepareStatement(sql);			
			preparedStatement.setLong   (1, productId);			
		
			resultSet = preparedStatement.executeQuery();	
			
			while (resultSet.next()) { 				
				
				keyFeature = new KeyFeature();
				
				keyFeature.setId       (resultSet.getLong("id"        ));
				keyFeature.setProductId(resultSet.getLong("product_id"));
				
				keyFeature.setKey      (resultSet.getString("header"  ));
				keyFeature.setValue    (resultSet.getString("value"   ));
				
				keyFeatures.add(keyFeature);
				
				keyFeature = null;
			}
			
			connection.commit();
			
			System.out.println("SQL getKeyFeatures Executed");
			
			return keyFeatures;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e1) {
			try {
				connection.rollback();       } catch (SQLException e) {	e.printStackTrace(); }
			e1.printStackTrace();
		} finally {
			keyFeatures = null; keyFeature = null;
			try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}			
		
		return null;
	}//getKeyFeatures
	
	
	
	public List<Size> getSizes(long productId) {
		
		Connection connection = null; PreparedStatement preparedStatement = null; ResultSet resultSet = null;
		String sql = null; 		
		List<Size> sizes = new ArrayList<>();
		Size size = null;
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "SELECT * FROM size WHERE product_id = ?";
				
			preparedStatement = connection.prepareStatement(sql);			
			preparedStatement.setLong   (1, productId);			
		
			resultSet = preparedStatement.executeQuery();	
			
			while (resultSet.next()) { 				
				
				size = new Size();
				
				size.setId       (resultSet.getLong  ("id"        ));
				size.setProductId(resultSet.getLong  ("product_id"));
				
				size.setSize     (resultSet.getString("size"      ));
				size.setCount    (resultSet.getInt   ("count"     ));
				
				sizes.add(size);
				
				size = null;
			}
			
			connection.commit();
			
			System.out.println("SQL getSizes Executed");
			
			return sizes;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e1) {
			try {
				connection.rollback();       } catch (SQLException e) {	e.printStackTrace(); }
			e1.printStackTrace();
		} finally {
			sizes = null; size = null;
			try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}			
		
		return null;
	}//getSizes
	
	
	
	private String getSQLForGetProducts(
			long     productId      /*default: 0   */,
			String[] subCategories  /*default: null*/,
			Long[]   productIds     /*default: null*/
			) {
		
		String sql = null;	
		
		//getProducts()	
		if (productId == 0 && subCategories == null) {		
			sql = "select * from product";
			 System.out.println(sql);
		}
		//getProducts(productId, null, null)
		if (productId != 0 && subCategories == null) {      	
			sql = "select * from product where id = "+ productId;
			System.out.println(sql);
		}
		//getProducts(0, subCategories, null)
		if (subCategories != null && subCategories.length > 0 
				&& productId == 0) {      
			
			StringBuffer stringBuffer = new StringBuffer();
			
			stringBuffer.append("select * from product where ");
			
			for (int i = 0; i < subCategories.length; i++) {
			
				if (i == 0)
					stringBuffer.append("sub_category = '"+ subCategories[i] +"'");
				else 
					stringBuffer.append(" or sub_category = '"+ subCategories[i] +"'");				
			}
			
			sql = stringBuffer.toString();    System.out.println(sql);
		}
		//getProducts(0, null, productIds)
		if (productIds != null && productIds.length > 0 
				&& subCategories == null && productId == 0) {    
			
			StringBuffer stringBuffer = new StringBuffer();
			
			stringBuffer.append("select * from product where ");
			
			for (int i = 0; i < productIds.length; i++) {
			
				if (i == 0)
					stringBuffer.append("id = "+ productIds[i]);
				else 
					stringBuffer.append(" or id = "+ productIds[i]);				
			}
			
			sql = stringBuffer.toString();    System.out.println(sql);
		}
		
		return sql;
	}
	
	 
	
	
	/********************  End Get Products - 7 methods **************************/
	
	
	
	public TwoObjects<Double, Integer> getShippingDelivery(long productId) {
		
		Connection connection = null; PreparedStatement preparedStatement = null; ResultSet resultSet = null;
		String sql = null; 		
		TwoObjects<Double, Integer> twoObjects = new TwoObjects<>();
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "SELECT * FROM shipping_delivery WHERE product_id = ?";
				
			preparedStatement = connection.prepareStatement(sql);			
			preparedStatement.setLong   (1, productId);			
		
			resultSet = preparedStatement.executeQuery();	
			
			if (resultSet.next()) { 				
				
				twoObjects.setObj1(resultSet.getDouble("shippingCost"));
				twoObjects.setObj2(resultSet.getInt("deliveryTime"));
				
			}
			
			connection.commit();
			
			System.out.println("SQL getProduct Executed");
			
			return twoObjects;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e1) {
			try {
				connection.rollback();       } catch (SQLException e) {	e.printStackTrace(); }
			e1.printStackTrace();
		} finally {
			twoObjects = null;
			try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { connection.close();        } catch (SQLException e) { e.printStackTrace(); }
			System.gc();
		}			
		
		return null;
	}//getProduct
	
	
	
	
	public static void main(String...args) {
		
		Long[] str = {1l, 2l, 3l};
		
		List<Product> list = new ProductDAO().getProducts(0, null, str);
		
		for (Product product : list) {
			
			System.out.println(product.getProductId());
			List<KeyFeature> keyFeatures = product.getKeyFeatures();
			
			for (KeyFeature keyFeature : keyFeatures)
				System.out.println(keyFeature.getKey());
		}
	}
	
	
}
