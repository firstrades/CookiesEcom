package ecom.DAO.Buyer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ecom.beans.Handler;
import ecom.common.ConnectionFactory;
import ecom.model.CartWishlist;
import ecom.model.CustomerOrderHistroy;
import ecom.model.DeliveryAddress;
import ecom.model.Order;
import ecom.model.Price;
import ecom.model.Product;
import ecom.model.TwoObjects;
import ecom.model.User;

public class BuyerSearchDAO {
	
	private static BuyerSearchDAO buyerSearchDAO;
	
	private BuyerSearchDAO() {
		buyerSearchDAO = null;
	}
	
	public static BuyerSearchDAO getInstance() {
		
		if (buyerSearchDAO == null) {			
			synchronized (BuyerSearchDAO.class) {				
				if (buyerSearchDAO == null)
					buyerSearchDAO = new BuyerSearchDAO();
			}			
		}
		
		return buyerSearchDAO;
	}
	
	//-------------------------------------------------------------------------------------------searchBySubCategory
	
	public List<Product> searchBySubCategory(String subCategory) { 		
		return searchBySubCategory(subCategory, null, null);
	}
	
	public List<Product> searchBySubCategory(String[] subCategory, String search) { 		
		return searchBySubCategory(null, subCategory, search);
	}

	public List<Product> searchBySubCategory(String subCategory, String[] stringArray, String search) {  		
		
		Connection connection = null;
		Statement statement = null;
		String sql = null;
		ResultSet resultSet = null;		
		List<Product> list = new ArrayList<>();	
		Handler handler = Handler.getInstance();
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = handler.searchBySubCategory(subCategory, stringArray, search);
				
			statement = connection.createStatement();	  
			 			
			resultSet = statement.executeQuery(sql);	
			 			
			while (resultSet.next()) {
				
				Product productBean = new Product();				
				
				productBean.setProductId                 (resultSet.getInt   ("id"));
				productBean.setSellerId                  (resultSet.getLong  ("seller_id"));
				
				productBean.setCategory                  (resultSet.getString("category"));
				productBean.setSubCategory               (resultSet.getString("sub_category"));
				productBean.setProductName               (resultSet.getString("product_name"));
				productBean.setCompanyName               (resultSet.getString("company_name"));
				
				productBean.getPrice().setListPrice      (resultSet.getDouble("list_price"));
				productBean.getPrice().setDiscount       (resultSet.getDouble("discount"));
				productBean.getPrice().setSalePriceCustomer(resultSet.getDouble("salePriceCustomer"));				
				productBean.getPrice().setMarkup         (resultSet.getDouble("markup"));
				
				
				list.add(productBean);
			}
			
			connection.commit();
			
			System.out.println("SQL getProducts Executed");
			
			if (list.size() > 0)			
				return list;
			else
				return null;
			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			return null;
		} finally {		
			if (resultSet != null)
				try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			if (statement != null)
				try { statement.close();         } catch (SQLException e)  { e.printStackTrace();  }
			if (connection != null)
				try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}		
		
		
				
		
	}
	
	//---------------------------------------------------------------------------------------------------------addToCartOrWishList
	
	
	
	public List<TwoObjects<Product, CartWishlist>> addToCartOrWishList(long productId, String userId, 
			String cartOrWishlist, String size) {		
			
			Connection connection   = null;
			PreparedStatement preparedStatement = null;
			String sql              = null;
			ResultSet resultSet     = null;		
			Product productBean     = null;	
			
			List<TwoObjects<Long, CartWishlist>>    productIdAndQtyList   = new ArrayList<>();
			List<TwoObjects<Product, CartWishlist>> productBeanAndQtyList = new ArrayList<>();
			
			try {
					connection = ConnectionFactory.getNewConnection();
					connection.setAutoCommit(false);
					
					//---------------------------------------------------------------------------------------------------------------------------------
					if (productId != 0L)  {
					
								/**
								 * @Check if Product already exist
								 */
								sql = "SELECT * FROM cart_wishlist WHERE product_id = ? AND cart_wishlist = ? AND user_id = ? AND size = ?";
																															
								preparedStatement = connection.prepareStatement(sql);				
								preparedStatement.setLong   (1, productId);   
								preparedStatement.setString (2, cartOrWishlist); 
								preparedStatement.setString (3, userId);
								preparedStatement.setString (4, size);
								 			
								resultSet = preparedStatement.executeQuery();			
								 																								
								if (resultSet.next()) {										
									
										resultSet.close();
										preparedStatement.close();		
									
								} else {
									
										resultSet.close();
										preparedStatement.close();
										
										/**
										 * @Insert Product
										 */
										sql = "INSERT INTO cart_wishlist (product_id, user_id, cart_wishlist, qty, size) " +
										      "VALUES (?,?,?, 1,?)";
										
										preparedStatement = connection.prepareStatement(sql);
										preparedStatement.setLong   (1, productId);
										preparedStatement.setString (2, userId);
										preparedStatement.setString (3, cartOrWishlist); 
										preparedStatement.setString (4, size);										
										 			
										int result = preparedStatement.executeUpdate();
										
										if (result != 0) System.out.println("SQL - Insert successfull.");
										
										preparedStatement.close();
								}  // else close
								
					} // if close
					
					//--------------------------------------------------COMMON FOR ALL--------------------------------------------------------------------
					
					/**
					 * @Retrieve all productIds for this User
					 */
					
					sql = "SELECT * FROM cart_wishlist WHERE user_id = ? AND cart_wishlist = ?";
					
					preparedStatement = connection.prepareStatement(sql);				
					preparedStatement.setString (1, userId); 
					preparedStatement.setString (2, cartOrWishlist);
					 			
					resultSet = preparedStatement.executeQuery();
				
					while (resultSet.next()) {
						
						TwoObjects<Long, CartWishlist> twoObjects = new TwoObjects<>();
						CartWishlist cartWishlist = new CartWishlist();
						
						twoObjects.setObj1 (resultSet.getLong("product_id")); 
						
						cartWishlist.setCartOrWishlist(resultSet.getString("cart_wishlist"));
						cartWishlist.setId            (resultSet.getLong  ("id"           ));
						cartWishlist.setProductId     (resultSet.getLong  ("product_id"   ));
						cartWishlist.setQty           (resultSet.getInt   ("qty"          ));
						cartWishlist.setSize          (resultSet.getString("size"         ));
						cartWishlist.setUserId        (resultSet.getString("user_id"      ));
						
						twoObjects.setObj2 (cartWishlist);  
						
						productIdAndQtyList.add(twoObjects);
					}
					
					resultSet.close();
					preparedStatement.close();
					
					/**
					 * @Retrieve ProductBeanList
					 */
					
					for (TwoObjects<Long, CartWishlist> twoObjects : productIdAndQtyList) {
						
							sql = "SELECT * FROM product WHERE id = ?";
							
							preparedStatement = connection.prepareStatement(sql);				
							preparedStatement.setLong (1, twoObjects.getObj1());   
							 			
							resultSet = preparedStatement.executeQuery();
							
							if (resultSet.next()) {
							
									productBean = new Product();
								  //productBean.setKeyFeatures(new KeyFeatures());
								  //productBean.setPrice(new Price());
									
									TwoObjects<Product, CartWishlist> twoObjects2 = new TwoObjects<>();
									
									productBean.setProductId                 (resultSet.getInt   ("id"  ));
									productBean.setSellerId                  (resultSet.getLong  ("seller_id"   ));
									
									productBean.setCategory                  (resultSet.getString("category"    ));
									productBean.setSubCategory               (resultSet.getString("sub_category"));
									productBean.setProductName               (resultSet.getString("product_name"));
									productBean.setCompanyName               (resultSet.getString("company_name"));
									
									productBean.getPrice().setListPrice      (resultSet.getDouble("list_price"  ));
									productBean.getPrice().setDiscount       (resultSet.getDouble("discount"    ));
									productBean.getPrice().setSalePriceCustomer(resultSet.getDouble("salePriceCustomer"));
									productBean.getPrice().setMarkup         (resultSet.getDouble("markup"      ));
									
									
									
									twoObjects2.setObj1(productBean);
									twoObjects2.setObj2(twoObjects.getObj2());
									
									productBeanAndQtyList.add(twoObjects2);
									
							}  // if close
							
							resultSet.close();
							preparedStatement.close();
							
					}  // for close
					
					connection.commit();		
					
					System.out.println("SQL addToCartOrWishList Executed");
					
					return productBeanAndQtyList;
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {			
				try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
				e.printStackTrace();
				
			} finally {		
				productIdAndQtyList = null;
				productBeanAndQtyList = null;
				try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
				try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
				try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
				System.gc();
			}		
			
			
			return null;		
	}
	
	
	public boolean deleteCartOrWishListItem(long productId, String userId, String cartOrWishlist) {		
			
			Connection connection   = null;
			PreparedStatement preparedStatement = null;
			String sql              = null;
			
			try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				sql = "DELETE FROM cart_wishlist WHERE product_id = ? AND user_id = ? AND cart_wishlist = ?";
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong  (1, productId);
				preparedStatement.setString(2, userId);
				preparedStatement.setString(3, cartOrWishlist);
				int result = preparedStatement.executeUpdate();
				
				connection.commit();		
				
				System.out.println("SQL deleteCartOrWishListItem Executed");
				
				if (result != 0)  return true;
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {			
				try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
				e.printStackTrace();
				
			} finally {						
				try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
				try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
				System.gc();
			}		
			
			return false;
	}
	
	
	public List<TwoObjects<Product, CartWishlist>> moveToCartOrWishList(long productId, String userId, String cartOrWishlist,
			int qty, String size) {  //wish		
				
				Connection connection   = null;
				PreparedStatement preparedStatement = null;
				String sql              = null;
				ResultSet resultSet     = null;		
				Product productBean = null;	
				
				List<TwoObjects<Long, CartWishlist>>    productIdAndQtyList   = new ArrayList<>();
				List<TwoObjects<Product, CartWishlist>> productBeanAndQtyList = new ArrayList<>();
				
				try {
						connection = ConnectionFactory.getNewConnection();
						connection.setAutoCommit(false);
						
						//----------------------------------------------------------------------------------------------------------------------------------
						
						String toggle = null;
						
						if (cartOrWishlist.equals("cart"))					
							toggle = "wishlist";				
						else					
							toggle = "cart";
						
						/**
						 * @Check if Product already exist
						 */
						sql = "SELECT * FROM cart_wishlist WHERE product_id = ? AND cart_wishlist = ? AND user_id = ? AND size = ?";
																													
						preparedStatement = connection.prepareStatement(sql);				
						preparedStatement.setLong   (1, productId);   
						preparedStatement.setString (2, toggle);  
						preparedStatement.setString (3, userId);
						preparedStatement.setString (4, size);
						 			
						resultSet = preparedStatement.executeQuery();			
						 																								
						if (resultSet.next()) {
							
								resultSet.close();
								preparedStatement.close();	
								
								sql = "DELETE FROM cart_wishlist WHERE product_id = ? AND user_id = ? AND cart_wishlist = ? AND size = ?";
								
								preparedStatement = connection.prepareStatement(sql);
								preparedStatement.setLong  (1, productId);
								preparedStatement.setString(2, userId);
								preparedStatement.setString(3, cartOrWishlist);
								preparedStatement.setString(4, size);
								
								int result = preparedStatement.executeUpdate();
								
								if (result != 0) System.out.println("SQL - Delete successfull.");
							
						} else {
							
								resultSet.close();
								preparedStatement.close();
						
								
								
								sql = "UPDATE cart_wishlist SET cart_wishlist = ?, qty = ? WHERE product_id = ? AND user_id = ? AND cart_wishlist = ? AND size = ?";
								
								preparedStatement = connection.prepareStatement(sql);
								preparedStatement.setString (1, toggle);
								preparedStatement.setInt    (2, qty);
								preparedStatement.setLong   (3, productId);
								preparedStatement.setString (4, userId); 
								preparedStatement.setString (5, cartOrWishlist);
								preparedStatement.setString (6, size);
								 			
								int result = preparedStatement.executeUpdate();
								
								if (result != 0) System.out.println("SQL - Update successfull.");
								
						}  // else close		
						
						//--------------------------------------------------COMMON FOR ALL--------------------------------------------------------------------
						
						/**
						 * @Retrieve all productIds for this User
						 */
						
						sql = "SELECT * FROM cart_wishlist WHERE user_id = ? AND cart_wishlist = ?";
						
						preparedStatement = connection.prepareStatement(sql);				
						preparedStatement.setString (1, userId); 
						preparedStatement.setString (2, toggle);
						 			
						resultSet = preparedStatement.executeQuery();
					
						while (resultSet.next()) {							
							
							TwoObjects<Long, CartWishlist> twoObjects = new TwoObjects<>();
							CartWishlist cartWishlist = new CartWishlist();
							
							twoObjects.setObj1 (resultSet.getLong("product_id")); 
							
							cartWishlist.setCartOrWishlist(resultSet.getString("cart_wishlist"));
							cartWishlist.setId            (resultSet.getLong  ("id"           ));
							cartWishlist.setProductId     (resultSet.getLong  ("product_id"   ));
							cartWishlist.setQty           (resultSet.getInt   ("qty"          ));
							cartWishlist.setSize          (resultSet.getString("size"         ));
							cartWishlist.setUserId        (resultSet.getString("user_id"      ));
							
							twoObjects.setObj2 (cartWishlist);  
							
							productIdAndQtyList.add(twoObjects);
						}
						
						resultSet.close();
						preparedStatement.close();
						
						/**
						 * @Retrieve ProductBeanList
						 */
						
						for (TwoObjects<Long, CartWishlist> twoObjects : productIdAndQtyList) {
							
								sql = "SELECT * FROM product WHERE id = ?";
								
								preparedStatement = connection.prepareStatement(sql);				
								preparedStatement.setLong (1, twoObjects.getObj1());   
								 			
								resultSet = preparedStatement.executeQuery();
								
								if (resultSet.next()) {
								
										productBean = new Product();
										//productBean.setKeyFeatures(new KeyFeature());
										productBean.setPrice(new Price());
										
										TwoObjects<Product, CartWishlist> twoObjects2 = new TwoObjects<>();
										
										productBean.setProductId                 (resultSet.getInt   ("id"  ));
										productBean.setSellerId                  (resultSet.getLong  ("seller_id"   ));
										
										productBean.setCategory                  (resultSet.getString("category"    ));
										productBean.setSubCategory               (resultSet.getString("sub_category"));
										productBean.setProductName               (resultSet.getString("product_name"));
										productBean.setCompanyName               (resultSet.getString("company_name"));
										
										productBean.getPrice().setListPrice      (resultSet.getDouble("list_price"  ));
										productBean.getPrice().setDiscount       (resultSet.getDouble("discount"    ));
										productBean.getPrice().setSalePriceCustomer(resultSet.getDouble("salePriceCustomer"));
										productBean.getPrice().setMarkup         (resultSet.getDouble("markup"  ));
										
										/*productBean.getKeyFeatures().setKf1      (resultSet.getString("kf_1"));
										productBean.getKeyFeatures().setKf2      (resultSet.getString("kf_2"));
										productBean.getKeyFeatures().setKf3      (resultSet.getString("kf_3"));
										productBean.getKeyFeatures().setKf4      (resultSet.getString("kf_4"));*/
										
										twoObjects2.setObj1(productBean);
										twoObjects2.setObj2(twoObjects.getObj2());
										
										productBeanAndQtyList.add(twoObjects2);
										
								}  // if close
								
								resultSet.close();
								preparedStatement.close();
								
						}  // for close
						
						connection.commit();		
						
						System.out.println("SQL addToCartOrWishList Executed");
						
						return productBeanAndQtyList;
						
						
					
					
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e) {			
					try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
					e.printStackTrace();
					
				} finally {		
					productIdAndQtyList   = null;
					productBeanAndQtyList = null;
					try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
					try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
					try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
					System.gc();
				}		
				
				
				return null;		
	} // moveToCartOrWishList	
	
	
	public int insertQtyOfRow(String user_id, int qty, long productId, long cartWishlistID) {		
		
		Connection connection   = null;
		CallableStatement callableStatement = null;
		String sql = "{call insertQtyOfRow(?,?,?,?)}";			
		
		int qty1 = 0;
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				callableStatement.setInt   (1, qty             );
				callableStatement.setLong  (2, productId       );
				callableStatement.setString(3, user_id         );
				callableStatement.setLong  (4, cartWishlistID  );
				callableStatement.registerOutParameter(1, java.sql.Types.INTEGER);
				 			
				callableStatement.execute();			
					
				qty1 = callableStatement.getInt(1);			
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select insertQtyOfRow() successfull.");
				
				return qty1;
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {				
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}		
		
		return 0;
		
	} // insertQtyOfRow
	
	
	public static List<CartWishlist> getCartWishlist(Long productId, int qty, String cart, long userId, String size) {			
		
		Connection connection   = null;
		PreparedStatement preparedStatement = null;
		String sql              = null;
		ResultSet resultSet     = null;	
		
		long id = 0;
		int  result;
		
		CartWishlist cartWishlist = new CartWishlist();
		List<CartWishlist> list = new ArrayList<>();
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				//----SQL----
				sql = "SELECT * FROM cart_wishlist WHERE product_id = ? AND user_id = ? AND cart_wishlist = ?";			
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong  (1, productId);
				preparedStatement.setLong  (2, userId);
				preparedStatement.setString(3, cart);			
				resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					
						id = resultSet.getLong("id");
						
						System.out.println("Product Exist...");
						resultSet.close();
						preparedStatement.close();
						
				} else {
					
						System.out.println("Product Not Exist...");
						resultSet.close();
						preparedStatement.close();
						//----SQL---
						sql = "INSERT INTO cart_wishlist (product_id, user_id, cart_wishlist, qty, size) VALUES (?,?,?,?,?)";					
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setLong  (1, productId);
						preparedStatement.setLong  (2, userId);
						preparedStatement.setString(3, cart);
						preparedStatement.setInt   (4, qty);
						preparedStatement.setString(5, size);
						result = preparedStatement.executeUpdate();
						preparedStatement.close();
						
						if (result != 0) {  
								//----SQL----
								sql = "SELECT MAX(id) FROM cart_wishlist";							
								preparedStatement = connection.prepareStatement(sql);
								resultSet         = preparedStatement.executeQuery();	
								
								if (resultSet.next()) {
									id = resultSet.getLong(1);
								}
								
								resultSet.close();
								preparedStatement.close();
						}
				}
			
				//----SQL----
				sql = "SELECT product_id, qty, size FROM cart_wishlist WHERE id = ?";  
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong(1, id);
				resultSet         = preparedStatement.executeQuery();	
				
				if (resultSet.next()) {
					
					cartWishlist.setProductId(resultSet.getLong("product_id"));
					cartWishlist.setQty      (resultSet.getInt   ("qty"));
					cartWishlist.setSize     (resultSet.getString("size"));
					
					list.add(cartWishlist);
				}							
			
				connection.commit();
				
				System.out.println("SQL - getProductIdAndQty(Long productId, int qty, String cart, long userId) Successfull.");
				
				return list;
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {				
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		return null;
	}
	
	public static List<CartWishlist> getCartWishlist(String cart, long userId) {			
		
		Connection connection   = null;
		PreparedStatement preparedStatement = null;
		String sql              = null;
		ResultSet resultSet     = null;				
		
		List<CartWishlist> list = new ArrayList<>();
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				//----SQL----
				sql = "SELECT id, product_id, qty, size FROM cart_wishlist WHERE user_id = ? AND cart_wishlist = ?";
				preparedStatement = connection.prepareStatement(sql);				
				preparedStatement.setLong  (1, userId);
				preparedStatement.setString(2, cart);			
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {						
					
					CartWishlist cartWishlist = new CartWishlist();
					
					cartWishlist.setId       (resultSet.getLong  ("id"        ));
					cartWishlist.setProductId(resultSet.getLong  ("product_id"));
					cartWishlist.setQty      (resultSet.getInt   ("qty"       ));
					cartWishlist.setSize     (resultSet.getString("size"      ));
			
					list.add(cartWishlist);	
				}
			
				connection.commit();
				
				System.out.println("SQL - getProductIdAndQty(String cart, long userId) Successfull.");
				
				return list;
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {				
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		return null;
	} // getProductIdAndQty
	
	public Order setDeliveryAddressIfExists(Order order, User user) {
		
			Connection connection               = null;
			CallableStatement callableStatement = null;			
			String sql = "{call setDeliveryAddressIfExists(?,?,?,?,?,?,?,?,?,?,?,?,?)}";				
			
			
			try {
					connection = ConnectionFactory.getNewConnection();
					connection.setAutoCommit(false);
					
					callableStatement = connection.prepareCall(sql);
					
					callableStatement.setLong(1, user.getUserInfo().getId());
					callableStatement.registerOutParameter(1,  java.sql.Types.INTEGER);
					callableStatement.registerOutParameter(2,  java.sql.Types.BOOLEAN);
					callableStatement.registerOutParameter(3,  java.sql.Types.INTEGER);
					callableStatement.registerOutParameter(4,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(5,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(6,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(7,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(8,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(9,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(11, java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(12, java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(13, java.sql.Types.VARCHAR);
					 			
					callableStatement.execute();			
					// if address exists
					order.getDeliveryAddress().setExits(callableStatement.getBoolean(2));	
					
					boolean addressExists = order.getDeliveryAddress().isExits();
					
					if (addressExists) {
						
						order.getDeliveryAddress().setUserId (callableStatement.getLong(1)   );
						order.getDeliveryAddress().setId     (callableStatement.getLong(3)   );
						order.getDeliveryAddress().setfName  (callableStatement.getString(4) );
						order.getDeliveryAddress().setlName  (callableStatement.getString(5) );
						order.getDeliveryAddress().setContact(callableStatement.getString(6) );
						order.getDeliveryAddress().setAddress(callableStatement.getString(7) );  
						order.getDeliveryAddress().setAddress1(callableStatement.getString(8) );  
						order.getDeliveryAddress().setCity   (callableStatement.getString(9) );
						order.getDeliveryAddress().setState  (callableStatement.getString(10) );
						order.getDeliveryAddress().setPin    (callableStatement.getString(11));
						order.getDeliveryAddress().setEmail  (callableStatement.getString(12));
						order.getDeliveryAddress().setCompany(callableStatement.getString(13));
					}
					
					connection.commit();
					callableStatement.close();
					
					System.out.println("SQL - Select setDeliveryAddressIfExists() successfull.");
					
					return order;
					
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {			
				try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
				e.printStackTrace();
				
			} finally {					
				try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
				try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
				System.gc();
			}		
			
		return null;
		
	} // setDeliveryAddressIfExists
	
	
	public Order editDeliveryAddress(String fName, String lName, String pincode, String address1, String address2, String city, 
			String state, String contact, Order order, User user) {
		
			Connection connection               = null;
			CallableStatement callableStatement = null;			
			String sql = "{call editDeliveryAddress(?,?,?,?,?,?,?,?,?)}";			
			
			try {
					connection = ConnectionFactory.getNewConnection();
					connection.setAutoCommit(false);
					
					callableStatement = connection.prepareCall(sql);
					
					callableStatement.setLong  (1, user.getUserInfo().getId());
					callableStatement.setString(2, fName  );
					callableStatement.setString(3, lName  );
					callableStatement.setString(4, contact);
					callableStatement.setString(5, address1);
					callableStatement.setString(6, address2);
					callableStatement.setString(7, city   );
					callableStatement.setString(8, state  );
					callableStatement.setString(9, pincode);					
					
					callableStatement.registerOutParameter(1,  java.sql.Types.INTEGER);
					callableStatement.registerOutParameter(2,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(3,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(4,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(5,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(6,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(7,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(8,  java.sql.Types.VARCHAR);	
					callableStatement.registerOutParameter(9,  java.sql.Types.VARCHAR);	
					 			
					callableStatement.execute();						
					
					order.getDeliveryAddress().setfName  (callableStatement.getString(2));
					order.getDeliveryAddress().setlName  (callableStatement.getString(3));
					order.getDeliveryAddress().setContact(callableStatement.getString(4));					
					order.getDeliveryAddress().setAddress(callableStatement.getString(5));
					order.getDeliveryAddress().setAddress1(callableStatement.getString(6));
					order.getDeliveryAddress().setCity   (callableStatement.getString(7));
					order.getDeliveryAddress().setState  (callableStatement.getString(8));					
					order.getDeliveryAddress().setPin    (callableStatement.getString(9));
					
					
					connection.commit();
					callableStatement.close();
					
					System.out.println("SQL - Select editDeliveryAddress() successfull.");
					
					return order;
					
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {			
				try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
				e.printStackTrace();
				
			} finally {					
				try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
				try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
				System.gc();
			}		
			
			return null;
		
	} // editDeliveryAddress
	
	
	public Order newDeliveryAddress(String fName, String lName, String pincode, String address1, String address2, String city, 
			String state, String contact, Order order, User user) {
		
			Connection connection               = null;
			CallableStatement callableStatement = null;			
			String sql = "{call newDeliveryAddress(?,?,?,?,?,?,?,?,?)}";				
			
			
			try {
					connection = ConnectionFactory.getNewConnection();
					connection.setAutoCommit(false);
					
					callableStatement = connection.prepareCall(sql);
					
					callableStatement.setLong  (1, user.getUserInfo().getId());
					callableStatement.setString(2, fName  );
					callableStatement.setString(3, lName  );
					callableStatement.setString(4, contact);
					callableStatement.setString(5, address1);
					callableStatement.setString(6, address2);
					callableStatement.setString(7, city   );
					callableStatement.setString(8, state  );
					callableStatement.setString(9, pincode);					
					
					callableStatement.registerOutParameter(2,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(3,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(4,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(5,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(6,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(7,  java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(8,  java.sql.Types.VARCHAR);	
					callableStatement.registerOutParameter(9,  java.sql.Types.VARCHAR);
					 			
					callableStatement.execute();						
					
					order.getDeliveryAddress().setfName  (callableStatement.getString(2));
					order.getDeliveryAddress().setlName  (callableStatement.getString(3));
					order.getDeliveryAddress().setContact(callableStatement.getString(4));					
					order.getDeliveryAddress().setAddress(callableStatement.getString(5));
					order.getDeliveryAddress().setAddress1(callableStatement.getString(6));
					order.getDeliveryAddress().setCity   (callableStatement.getString(7));
					order.getDeliveryAddress().setState  (callableStatement.getString(8));					
					order.getDeliveryAddress().setPin    (callableStatement.getString(9));
					
					order.getDeliveryAddress().setExits  (true);
					
					connection.commit();
					callableStatement.close();
					
					System.out.println("SQL - Select newDeliveryAddress() successfull.");
					
					return order;
					
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {			
				try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
				e.printStackTrace();
				
			} finally {					
				try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
				try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
				System.gc();
			}		
			
			return null;
		
	} // newDeliveryAddress
	
	public Product getProductBean(long productId) {
		
		Connection connection   = null;
		PreparedStatement preparedStatement = null;
		String sql              = null;
		ResultSet resultSet     = null;				
		
		Product productBean = new Product();
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);				
				
				sql = "SELECT category, company_name, product_name, seller_company, sub_category, warranty, calcellation_after_booked FROM product WHERE id = ?";
				
				preparedStatement = connection.prepareStatement(sql);				
				preparedStatement.setLong  (1, productId);
						
				resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {					
				
					productBean.setCategory     (resultSet.getString("category"      ));
					productBean.setCompanyName  (resultSet.getString("company_name"  ));
					productBean.setProductName  (resultSet.getString("product_name"  ));
					productBean.setSellerCompany(resultSet.getString("seller_company"));
					productBean.setSubCategory  (resultSet.getString("sub_category"  ));
					productBean.setWarranty     (resultSet.getString("warranty"      ));
					productBean.setCancellationAfterBooked(resultSet.getInt("calcellation_after_booked"));
				}
			
				connection.commit();
				
				System.out.println("SQL - getProductBean Successfull.");
				
				return productBean;
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {			
			productBean = null;
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		return null;
	} // getProductBean
	
	
	/**********************  Get All Sizes  ************************/
	
	
	/*public SizeGarment getSizeGarmentModel(long productId, SizeGarment sizeGarment) {
		
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;
		
		String sql = "{call getSizeGarmentModel(?)}";		
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				
				callableStatement.setLong(1, productId);							
				 			
				resultSet = callableStatement.executeQuery();						
				
				if (resultSet.next()) {
					//if exists
					sizeGarment.setProductIdExists(true);
					
					sizeGarment.setId       (resultSet.getLong("id"));
					sizeGarment.setProductId(resultSet.getLong("product_id"));
					
					sizeGarment.getSize().setQtyOfSize26(resultSet.getInt("s26"));  
					sizeGarment.getSize().setQtyOfSize28(resultSet.getInt("s28"));
					sizeGarment.getSize().setQtyOfSize30(resultSet.getInt("s30"));
					sizeGarment.getSize().setQtyOfSize32(resultSet.getInt("s32"));
					sizeGarment.getSize().setQtyOfSize34(resultSet.getInt("s34"));
					sizeGarment.getSize().setQtyOfSize36(resultSet.getInt("s36"));
					sizeGarment.getSize().setQtyOfSize38(resultSet.getInt("s38"));
					sizeGarment.getSize().setQtyOfSize40(resultSet.getInt("s40"));
					sizeGarment.getSize().setQtyOfSize42(resultSet.getInt("s42"));
					sizeGarment.getSize().setQtyOfSize44(resultSet.getInt("s44"));
					sizeGarment.getSize().setQtyOfSize46(resultSet.getInt("s46"));
					sizeGarment.getSize().setQtyOfSize48(resultSet.getInt("s48")); 
					
				}
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select getSizeGarmentModel() successfull.");
				
				return sizeGarment;
				
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
		
	} //getSizeGarmentModel
	
	
	public SizeInYears getSizeInYears(long productId, SizeInYears sizeInYears) {
		
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;
		
		String sql = "{call getSizeInYears(?)}";		
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				
				callableStatement.setLong(1, productId);							
				 			
				resultSet = callableStatement.executeQuery();						
				
				if (resultSet.next()) {
					//if exists
					sizeInYears.setProductIdExists(true);
					
					sizeInYears.setId       (resultSet.getLong("id"));
					sizeInYears.setProductId(resultSet.getLong("product_id"));
					
					sizeInYears.setStockOfSIZE_1_2  (resultSet.getInt("y_1_2"  ));  
					sizeInYears.setStockOfSIZE_2_3  (resultSet.getInt("y_2_3"  ));
					sizeInYears.setStockOfSIZE_3_4  (resultSet.getInt("y_3_4"  ));
					sizeInYears.setStockOfSIZE_4_5  (resultSet.getInt("y_4_5"  ));
					sizeInYears.setStockOfSIZE_5_6  (resultSet.getInt("y_5_6"  ));
					sizeInYears.setStockOfSIZE_6_7  (resultSet.getInt("y_6_7"  ));
					sizeInYears.setStockOfSIZE_7_8  (resultSet.getInt("y_7_8"  ));
					sizeInYears.setStockOfSIZE_8_9  (resultSet.getInt("y_8_9"  ));
					sizeInYears.setStockOfSIZE_9_10 (resultSet.getInt("y_9_10" ));
					sizeInYears.setStockOfSIZE_10_11(resultSet.getInt("y_10_11"));
					sizeInYears.setStockOfSIZE_11_12(resultSet.getInt("y_11_12"));
					
				}
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select getSizeGarmentModel() successfull.");
				
				return sizeInYears;
				
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
		
	} //getSizeInYears
*/	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Soumya
	public List<CustomerOrderHistroy> getCustomerOrderHistroy(User user) {
		
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;
		
	    List<CustomerOrderHistroy> list = new ArrayList<CustomerOrderHistroy>();
	    
		String sql = "{call getCustomerOrderHistroy(?)}";		
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				
				callableStatement.setLong(1, user.getUserInfo().getId());							
				 			
				resultSet = callableStatement.executeQuery();						
				
				while (resultSet.next()) {
					
					CustomerOrderHistroy customerOrderHistroy = new CustomerOrderHistroy();
					
					customerOrderHistroy.setCalcellationAfterBooked(resultSet.getInt   ("calcellationAfterBooked"));
					customerOrderHistroy.setCompanyName            (resultSet.getString("companyName"            ));
					customerOrderHistroy.setDeliveredDate          (resultSet.getString("deliveredDate"          ));
					customerOrderHistroy.setId                     (resultSet.getInt   ("id"                     )); // Order Table ID 
					customerOrderHistroy.setOrderId                (resultSet.getString("orderId"                ));  
					customerOrderHistroy.setOrderState             (resultSet.getString("orderState"             ));
					customerOrderHistroy.setOrderTableId           (resultSet.getLong  ("orderTableId"           ));
					customerOrderHistroy.setPaymentType            (resultSet.getString("paymentType"            ));
					customerOrderHistroy.setProductId              (resultSet.getLong  ("productId"              ));
					customerOrderHistroy.setProductName            (resultSet.getString("productName"            ));
					customerOrderHistroy.setQty                    (resultSet.getInt   ("qty"                    ));
					customerOrderHistroy.setSellerCompany          (resultSet.getString("sellerCompany"          ));
					customerOrderHistroy.setSellPrice              (resultSet.getDouble("sellPrice"              ));
					customerOrderHistroy.setShippingCost           (resultSet.getDouble("shippingCost"           ));
					customerOrderHistroy.setSize                   (resultSet.getString("size"                   ));
					customerOrderHistroy.setStatus                 (resultSet.getString("status"                 ));
					customerOrderHistroy.setWarranty               (resultSet.getString("warranty"               ));
					customerOrderHistroy.setOrderBookedDate        (resultSet.getString("orderBookedDate"        ));             
		
					list.add(customerOrderHistroy);
					
				}
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select getCustomerOrderHistroy() successfull.");
				
				return list;
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {		
			list = null;
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		return null;
		
	} //getCustomerOrderHistroy
	
	//Soumya
	public List<String> getOrderIdForCustomer(User user) {
		
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;
		
	    List<String> list = new ArrayList<>();
	    
		String sql = "{call getOrderIdForCustomer(?)}";		
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				
				callableStatement.setLong(1, user.getUserInfo().getId());							
				 			
				resultSet = callableStatement.executeQuery();						
				
				while (resultSet.next()) {
					
					
					String orderId = resultSet.getString("order_id");
		
					list.add(orderId);
					
				}
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select getOrderIdForCustomer() successfull.");
				
				return list;
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {	
			list = null;
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}		
		
		return null;
		
	} //getOrderIdForCustomer
	
	
	public boolean setItemCancelOfCustomer(long orderTableId) {
		
		Connection connection = null; CallableStatement callableStatement = null;     
	    
		String sql = "{call setItemCancelOfCustomer(?,?)}";	
		
		boolean status = false;
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				
				callableStatement.setLong(1, orderTableId);							
				callableStatement.registerOutParameter(2, Types.BOOLEAN);			
										
				callableStatement.execute();
				
				status = callableStatement.getBoolean(2);				
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select setItemCancelOfCustomer() successfull.");
				
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
		
	} //setItemCancelOfCustomer
	
	public DeliveryAddress getDeliveryAddressCustomer(User user, long userId) {
		
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;  
	    
		String sql = "{call getDeliveryAddressCustomer(?)}";	
		
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);
				
				callableStatement.setLong(1, user.getUserInfo().getId());					
										
				resultSet = callableStatement.executeQuery();
				
				while(resultSet.next()) {
					
					deliveryAddress.setAddress    (resultSet.getString("address"   ));
					deliveryAddress.setAddress1   (resultSet.getString("address1"  ));
					deliveryAddress.setCity       (resultSet.getString("city"      ));
					deliveryAddress.setCompany    (resultSet.getString("company"   ));
					deliveryAddress.setContact    (resultSet.getString("contact"   ));
					deliveryAddress.setCountry    (resultSet.getString("country"   ));
					deliveryAddress.setEmail      (resultSet.getString("email"     ));
					deliveryAddress.setfName      (resultSet.getString("first_name"));
					deliveryAddress.setId         (resultSet.getLong  ("id"        ));
					deliveryAddress.setlName      (resultSet.getString("last_name" ));
					deliveryAddress.setPin        (resultSet.getString("pin"       ));
					deliveryAddress.setState      (resultSet.getString("state"     ));
					deliveryAddress.setUserId     (resultSet.getLong  ("user_id"   ));
				}
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select getDeliveryAddressCustomer() successfull.");
				
				return deliveryAddress;
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {	
			deliveryAddress = null;
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		return null;
	}//getDeliveryAddressCustomer
	
	
	
	
	public Map<String,Product> getFirstPageProducts() {
		
		Connection connection = null; CallableStatement callableStatement = null; ResultSet resultSet = null;  
	    
		String sql = "{call getFirstPageProducts()}";	
		
		Map<String,Product> map = new HashMap<>();	
		
		try {
				connection = ConnectionFactory.getNewConnection();
				connection.setAutoCommit(false);
				
				callableStatement = connection.prepareCall(sql);								
										
				resultSet = callableStatement.executeQuery();
				
				while(resultSet.next()) {
					
					Product productBean = new Product();					
					
					productBean.setProductId                   (resultSet.getInt   ("id"               ));
					productBean.setSellerId                    (resultSet.getLong  ("seller_id"        ));
					
					productBean.setCategory                    (resultSet.getString("category"         ));
					productBean.setSubCategory                 (resultSet.getString("sub_category"     ));
					productBean.setProductName                 (resultSet.getString("product_name"     ));
					productBean.setCompanyName                 (resultSet.getString("company_name"     ));
					
					productBean.getPrice().setListPrice        (resultSet.getDouble("list_price"       ));
					productBean.getPrice().setDiscount         (resultSet.getDouble("discount"         ));
					productBean.getPrice().setSalePriceCustomer(resultSet.getDouble("salePriceCustomer"));				
					productBean.getPrice().setMarkup           (resultSet.getDouble("markup"           ));
					
					map.put(productBean.getSubCategory(), productBean);
				}
				
				connection.commit();
				callableStatement.close();
				
				System.out.println("SQL - Select getFirstPageProducts() successfull.");
				
				return map;
				
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e1) {			
			try { connection.rollback();     } catch (SQLException e)  { e.printStackTrace(); }
			e1.printStackTrace();
			
		} finally {	
			map = null;
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		return null;
	} //getFirstPageProducts
	
	
}
