package ecom.Implementation.Project;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import ecom.Interface.Courier.EstimatedRateAndDelivery;
import ecom.common.ConnectionFactory;

public class ShippingDelivery implements EstimatedRateAndDelivery {
	
	private BigDecimal shippingRate;
	private String     deliveryDate;
	
	private long       productId;
	
	public ShippingDelivery(long productId) {
		this.productId = productId;
		getDatabaseData();
	}

	@Override
	public BigDecimal getRate() {		
		return this.shippingRate;
	}

	@Override
	public String getDelivery() {		
		return this.deliveryDate;
	}
	
	public void getDatabaseData() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		ResultSet resultSet = null;				
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "SELECT * FROM shipping_delivery WHERE product_id = ?";
				
			preparedStatement = connection.prepareStatement(sql);				
			preparedStatement.setLong(1, productId);   
			 			
			resultSet = preparedStatement.executeQuery();
			int time = 0;
			 			
			while (resultSet.next()) {
				//Shipping
				this.shippingRate = new BigDecimal(resultSet.getLong("shippingCost"));   
				
				time = resultSet.getInt("deliveryTime");
				Calendar calendar = new GregorianCalendar();
				int year  = calendar.get(Calendar.YEAR);    System.out.println(year);
				int month = calendar.get(Calendar.MONTH)+1;   System.out.println(month);
				int day   = calendar.get(Calendar.DAY_OF_MONTH);   System.out.println(day);
				
				String monthStr = null;
				if (month < 10)
					monthStr = "0"+month;
				else
					monthStr = String.valueOf(month);
				
				String dayStr = null;
				if (day < 10)
					dayStr = "0"+day;
				else
					dayStr = String.valueOf(day);
				
				String dt = year+"-"+monthStr+"-"+dayStr;  
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				try {
					c.setTime(sdf.parse(dt));
				} catch (ParseException e) {					
					e.printStackTrace();
				}
				c.add(Calendar.DATE, time);  // number of days to add
				dt = sdf.format(c.getTime());  // dt is now the new date
				
				//Delivery
				this.deliveryDate = dt;
			}
			
			connection.commit();
			
			System.out.println("SQL getDatabaseData Executed");
			
			
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
		
	}//getDatabaseData
	
	

	public static void main(String...args) {
		
		EstimatedRateAndDelivery delivery = new ShippingDelivery(33L);
		System.out.println(delivery.getRate());
		System.out.println(delivery.getDelivery());
	}
}
