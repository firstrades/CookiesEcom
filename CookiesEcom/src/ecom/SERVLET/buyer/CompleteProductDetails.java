package ecom.SERVLET.buyer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecom.DAO.Seller.ProductDAO;
import ecom.Implementation.Project.ShippingDelivery;
import ecom.Interface.Courier.EstimatedRateAndDelivery;
import ecom.beans.TransientData;
import ecom.model.KeyFeature;
import ecom.model.Product;
import ecom.model.Size;
import ecom.model.TwoObjects;
import ecom.model.User;

@WebServlet("/CompleteProductDetails")
public class CompleteProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	
	private ProductDAO productDAO;    
	
	@Override
	public void init() {		
		productDAO     = ProductDAO.getInstance();
	}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("CompleteProductDetails Destroyed"); 
	};
  
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entered CompleteProductDetails");
		
		HttpSession session = request.getSession();
		
			/***************  Get Request  *****************/			
			String subCategory = request.getParameter("subCategory");		
			String productId1  = request.getParameter("productId");	
			
			/************* Get Session ***************/			
			@SuppressWarnings("unused")
			User user = (User) session.getAttribute("user");
		
			/**************** Process ******************/
			long productId = Long.parseLong(productId1);	        
			int  stock     = TransientData.getStock(productId);
		
			/*************************************************************************
		 		* Database Search for 'product' table and different product tables *
		 	*************************************************************************/			
			Product product              = productDAO.getProduct    (productId);
			List<KeyFeature> keyFeatures = productDAO.getKeyFeatures(productId);
			List<Size> sizes             = productDAO.getSizes      (productId);
			
			product.setKeyFeatures(keyFeatures);
			product.setSizes(sizes);
			
			
			/**
			 * @API - @Rate @Delivery
			 */			
			boolean error = false;
			EstimatedRateAndDelivery estimatedRateAndDelivery = null;
			//try {
				//estimatedRateAndDelivery = EstimatedRateAndDeliveryBean.getNewInstance(productId, user, 1);
				estimatedRateAndDelivery = new ShippingDelivery(productId);
				
				BigDecimal rate = estimatedRateAndDelivery.getRate();    
				String delivery = estimatedRateAndDelivery.getDelivery();
				
				List<TwoObjects<BigDecimal, String>> apiDataList  = new ArrayList<>();
				TwoObjects<BigDecimal, String> apiRateAndDelivery = new TwoObjects<>();
				apiRateAndDelivery.setObj1(rate    );
				apiRateAndDelivery.setObj2(delivery);
				apiDataList.add(apiRateAndDelivery );
				
			/************** Set Session ***************/
				session.setAttribute("apiDataList", apiDataList);			
			
			/*************** Set Request ****************/				
				request.setAttribute("productBean",   product);	
				request.setAttribute("stock",         stock);
				
				// API Data
				request.setAttribute("rate",          rate);
				request.setAttribute("delivery",      delivery);
				
				
			/*} catch (SOAPException e) {
				System.out.println("SOAPException");
				e.printStackTrace();
				error = true;				
			} catch (ParserConfigurationException e) {
				System.out.println("ParserConfigurationException");
				e.printStackTrace();
				error = true;	
			} catch (SAXException e) {
				System.out.println("SAXException");
				e.printStackTrace();
				error = true;	
			} catch (ParseException e) {
				System.out.println("ParseException");
				e.printStackTrace();
				error = true;	
			}*/
			
			
			/******** Clear Up **********/
			estimatedRateAndDelivery = null;
			
		
			/************** Next Page ******************/
			if (error) {
				String errorMsg = "Error! Please try again.";				
				response.sendRedirect("SearchBySubCategory?subCategory="+subCategory+"&errorMsg="+errorMsg);
			}
			else
				request.getRequestDispatcher("jsp_Buyer/ProductDetails.jsp").forward(request, response);
		
	}
	
	
}
