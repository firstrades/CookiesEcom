package ecom.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ecom.Implementation.Project.ShippingDelivery;
import ecom.Interface.Courier.EstimatedRateAndDelivery;
import ecom.common.ConnectionFactory;
import ecom.model.TwoObjects;
import ecom.model.WholeSaleOffer;

public class CartAttributesBean {

	private static CartAttributesBean instance = null;
	
	private double totalAmount;
	private int    totalQty;
	
	private CartAttributesBean() {}

	public double getTotalAmount(String user_id) {
		this.totalAmount = 0.0;  // remove residue value
		getSumOfTotalAmount(user_id);
		return totalAmount;
	}

	public int getTotalQty(String user_id) {		
		this.totalQty = 0;  // remove residue value
		getSumOfQty(user_id);
		return totalQty;
	}	
	
	public static CartAttributesBean getInstance() {
		if (instance == null)
			instance = new CartAttributesBean();		
		return instance;
	}
	
	//--------------------------- Processing ---------------------------------------------------------------------------------
	
	private void getSumOfQty(String user_id) {	
			
			
			String sql = "SELECT SUM(qty) FROM cart_wishlist WHERE user_id = ? AND cart_wishlist = 'cart' ";
			ResultSet resultSet   = null;
			
			try (Connection connection = ConnectionFactory.getNewConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {					
					
					preparedStatement.setString(1, user_id);
					resultSet         = preparedStatement.executeQuery();
					
					if (resultSet.next()) {
						this.totalQty = resultSet.getInt(1);
					}
					
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {			
				e.printStackTrace();
			} finally {
				
				try {
					resultSet.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				System.gc();
			}
	}
	
	private void getSumOfTotalAmount(String user_id) {			
			
			
			String sql            = null;
			sql = "SELECT product_id, qty FROM cart_wishlist WHERE user_id = ? AND cart_wishlist = 'cart' ";
			PreparedStatement preparedStatement = null;
			ResultSet resultSet   = null;
			
			List<TwoObjects<Long,Integer>> list = new ArrayList<>();
			EstimatedRateAndDelivery estimatedRateAndDelivery = null;
			
			try (Connection connection = ConnectionFactory.getNewConnection()) {					
					
					//---------- Get All ProductId --------------------------				
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, user_id);
					resultSet         = preparedStatement.executeQuery();
					
					while (resultSet.next()) {
						
						TwoObjects<Long, Integer> twoObjects = new TwoObjects<>();
						
						//productId
						twoObjects.setObj1 ( resultSet.getLong(1) );
						//Qty
						twoObjects.setObj2 ( resultSet.getInt (2) );
						
						list.add(twoObjects);
					}
					
					resultSet.close();
					preparedStatement.close();
					
					//---------- Get The Sum of Prices -----------------------
					
					for (TwoObjects<Long, Integer> twoObjects : list) {
						long productId = twoObjects.getObj1();
						int qty = twoObjects.getObj2();
						
						sql = "SELECT salePriceCustomer FROM product WHERE id = ? ";
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setLong(1, productId);
						resultSet         = preparedStatement.executeQuery();
						
						/**** Shipping Cost Call ****/
						estimatedRateAndDelivery = new ShippingDelivery(productId);
						double shipping = estimatedRateAndDelivery.getRate().doubleValue();
						/**** End Shipping Cost Call ****/
						
						/**** WholeSaleOffer Call ****/
						WholeSaleOffer wholeSaleOffer = WholeSaleOffer.getWholeSaleOffer(productId); 
						/**** End WholeSaleOffer Call ****/
						
						if (resultSet.next()) {
							
							double subTotal = 0;
							if (wholeSaleOffer != null && wholeSaleOffer.getQty() <= qty) {
								double wholeSaleDiscount = wholeSaleOffer.getDiscount();
								subTotal = (resultSet.getDouble(1) * qty) * (1 - (wholeSaleDiscount / 100));
							} else {							
								subTotal = (resultSet.getDouble(1) * qty);
							}
							
							this.totalAmount += subTotal + shipping;   System.out.println("this.totalAmount: "+this.totalAmount);
						}					
						
						
						resultSet.close();
						preparedStatement.close();
					}
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {			
				e.printStackTrace();
			} finally {
				
				try {
					resultSet.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				try {
					preparedStatement.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				System.gc();
			}
	}
	
	public static void main(String[] args) {
		
		CartAttributesBean cartAttributesBean = CartAttributesBean.getInstance();
		System.out.println( cartAttributesBean.getTotalQty("")    );
		System.out.println( cartAttributesBean.getTotalAmount("") );
	}
}



