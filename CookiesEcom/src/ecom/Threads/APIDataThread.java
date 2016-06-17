package ecom.Threads;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.xml.sax.SAXException;

import ecom.Implementation.Courier.SOAP.EstimatedRateAndDeliveryBean;
import ecom.Interface.Courier.EstimatedRateAndDelivery;
import ecom.model.TwoObjects;
import ecom.model.User;

public class APIDataThread extends Thread {
	
	private long productId;
	private User user;
	private int  qty;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	TwoObjects<BigDecimal, String> apiRateAndDelivery;

	public APIDataThread(long productId, User user, int qty, HttpServletRequest request, HttpServletResponse response) {
		this.productId = productId;
		this.user      = user;
		this.qty       = qty;
		this.apiRateAndDelivery = new TwoObjects<BigDecimal, String>();	
		this.request = request;
		this.response = response;
	}
	
	public TwoObjects<BigDecimal, String> getApiRateAndDelivery() {
		return apiRateAndDelivery;
	}
	
	public void run() {
		
		EstimatedRateAndDelivery estimatedRateAndDelivery = null;
		
		try {
			estimatedRateAndDelivery = EstimatedRateAndDeliveryBean.getNewInstance(this.productId, user, qty);
		} catch (SOAPException e) {
			System.out.println("SOAPException" + e);
			try {
				request.getRequestDispatcher("ErrorPages/ConnectionError.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		apiRateAndDelivery.setObj1(estimatedRateAndDelivery.getRate());	
		apiRateAndDelivery.setObj2(estimatedRateAndDelivery.getDelivery());
	}
}
