package ecom.SERVLET.buyer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import ecom.DAO.Buyer.BuyerSearchDAO;
import ecom.DAO.Buyer.ProductDetailsDAO;
import ecom.beans.TransientData;
import ecom.common.Conversions;
import ecom.model.BookedProduct;
import ecom.model.CartWishlist;
import ecom.model.Order;
import ecom.model.Product;
import ecom.model.TwoObjects;
import ecom.model.User;
import ecom.model.WholeSaleOffer;

@MultipartConfig
public class OrderReviewAndSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BuyerSearchDAO buyerSearchDAO;
	
	@Override
	public void init() {
		buyerSearchDAO = BuyerSearchDAO.getInstance();
	}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("OrderReviewAndSubmit Destroyed"); 
	};
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@SuppressWarnings("unchecked")
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entered OrderReviewAndSubmit");
		
		String servletPath  = request.getServletPath();
		HttpSession session = request.getSession();
		
		if (servletPath.equals("/OrderReview_ProductDetails")) {
			
			System.out.println("Entered OrderReview_ProductDetails");
			
			/************ Get Request **************/
			String size           = (String) request.getParameter("size");  //System.out.println("Size: " + size);
			String productId111   = request.getParameter("productId");
			String cartOrWishlist = request.getParameter("cartOrWishlist");
			
			/************ Get Session **************/
			User user = (User) session.getAttribute("user");
			List<TwoObjects<BigDecimal, String>> apiDataList = (List<TwoObjects<BigDecimal, String>>) session.getAttribute("apiDataList");
			
			/************ Accumulate Data ******************/
			Long   productId = Long.parseLong(productId111);
			int    qty       = 1;
			String cart      = cartOrWishlist;
			long   userId    = user.getUserInfo().getId();
			//int    size      = Integer.parseInt(size1);
			
			/************ Database *****************/
			// Get ProductId and Quantity
			List<CartWishlist> cartWishlistList = BuyerSearchDAO.getCartWishlist(productId, qty, cart, userId, size);
			
			//Common for          /OrderReview_ProductDetails    and   /OrderReview_Cart
			goto_OrderReviewPage(request, response, session, cartWishlistList, user, apiDataList);
			
			
		}  // END /OrderReview_ProductDetails
		
		else if (servletPath.equals("/OrderReview_Cart")) {
			
			System.out.println("Entered OrderReview_Cart");
			
			/************ Get Request **************/			
			String cartOrWishlist = request.getParameter("cartOrWishlist");
			
			/************ Get Session **************/
			User user = (User) session.getAttribute("user");
			List<TwoObjects<BigDecimal, String>> apiDataList = (List<TwoObjects<BigDecimal, String>>) session.getAttribute("apiDataList");
			
			/************ Accumulate Data ******************/			
			String cart      = cartOrWishlist;
			long   userId    = user.getUserInfo().getId();
			
			/************ Database *****************/
			// Get ProductId and Quantity
			List<CartWishlist> cartWishlistList = BuyerSearchDAO.getCartWishlist(cart, userId);
			
			//Common for          /OrderReview_ProductDetails    and   /OrderReview_Cart
			goto_OrderReviewPage(request, response, session, cartWishlistList, user, apiDataList);
			
		}  // END /OrderReview_Cart
		
		else if (servletPath.equals("/EditDeliveryAddress")) {
			
			System.out.println("Entered EditDeliveryAddress");
			
			/************ Get Request Data ******************/
			String fName   = request.getParameter("fName"  );     
			String lName   = request.getParameter("lName"  );       
			String pincode = request.getParameter("pincode");     
			String address1 = request.getParameter("address");
			String address2 = request.getParameter("address1");   
			String city    = request.getParameter("city"   );     
			String state   = request.getParameter("state"  );     
			String contact = request.getParameter("contact");     
			
			/************* Get Session *********************/
			Order order = (Order) session.getAttribute("order");
			User  user  = (User)  session.getAttribute("user" );
			
			/***************** Database ********************/			
			order = buyerSearchDAO.editDeliveryAddress(fName, lName, pincode, address1, address2, city, state, contact, order, user);
			
			/********* Set Session ****************/
			session.setAttribute("order", order);
			
			/********** Set Data For Next Page ***********/
			
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("fName",   order.getDeliveryAddress().getfName()  );
				jsonObject.put("lName",   order.getDeliveryAddress().getlName()  );
				jsonObject.put("pincode", order.getDeliveryAddress().getPin()    );
				jsonObject.put("address", order.getDeliveryAddress().getAddress());
				jsonObject.put("address1", order.getDeliveryAddress().getAddress1());
				jsonObject.put("city",    order.getDeliveryAddress().getCity()   );
				jsonObject.put("state",   order.getDeliveryAddress().getState()  );
				jsonObject.put("contact", order.getDeliveryAddress().getContact());
			} catch (JSONException e) {				
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());	
			
			
		}  // END /EditDeliveryAddress
		
		
		else if (servletPath.equals("/NewDeliveryAddress")) {
			
			System.out.println("Entered NewDeliveryAddress");
			
			/************ Get Request Data ******************/
			String fName   = request.getParameter("fName1"  );      
			String lName   = request.getParameter("lName1"  );       
			String pincode = request.getParameter("pincode1");     
			String address1 = request.getParameter("address1");  
			String address2 = request.getParameter("address2"); 
			String city    = request.getParameter("city1"   );     
			String state   = request.getParameter("state1"  );     
			String contact = request.getParameter("contact1");     
			
			/************* Get Session *********************/
			Order order = (Order) session.getAttribute("order");
			User  user  = (User)  session.getAttribute("user" );
			
			/***************** Database ********************/			
			order = buyerSearchDAO.newDeliveryAddress(fName, lName, pincode, address1, address2, city, state, contact, order, user);
			
			/********* Set Session ****************/
			session.setAttribute("order", order);
			
			/********** Set Data For Next Page ***********/
			
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("fName",   order.getDeliveryAddress().getfName()  );
				jsonObject.put("lName",   order.getDeliveryAddress().getlName()  );
				jsonObject.put("pincode", order.getDeliveryAddress().getPin()    );
				jsonObject.put("address", order.getDeliveryAddress().getAddress()); System.out.println(order.getDeliveryAddress().getAddress());
				jsonObject.put("address1", order.getDeliveryAddress().getAddress1()); System.out.println(order.getDeliveryAddress().getAddress1());
				jsonObject.put("city",    order.getDeliveryAddress().getCity()   );
				jsonObject.put("state",   order.getDeliveryAddress().getState()  );
				jsonObject.put("contact", order.getDeliveryAddress().getContact());
			} catch (JSONException e) {				
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());	
			
			
		}  // END /NewDeliveryAddress
		
	}  // END process()
	
	public void goto_OrderReviewPage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			List<CartWishlist> cartWishlistList, User user,
			List<TwoObjects<BigDecimal, String>> apiDataList) throws ServletException, IOException {		
		
			/************ Create Required Objects **********/		
			Order order = new Order();			
			
			/*************** Set Products To Buy *************/
		
			//----List<BookedProduct>----		
			List<BookedProduct> bookedProducts = new ArrayList<>();
			double totalSellPrice = 0;
			int i = 0;
			double totalSavings = 0;
			
			for (CartWishlist cartWishlist : cartWishlistList) {
				
				long cartWishlistId = cartWishlist.getId();
				
				BookedProduct bookedProduct = new BookedProduct();
				
						bookedProduct.setCartWishlistId(cartWishlistId);
				
				TwoObjects<BigDecimal, String> apiRateAndDelivery = apiDataList.get(i);
				
						bookedProduct.setProductId             (cartWishlist.getProductId() );
						bookedProduct.setQty                   (cartWishlist.getQty()       );
						bookedProduct.getGarmentOrder().setSize(cartWishlist.getSize()      );  System.out.println(bookedProduct.getGarmentOrder().getSize());
						bookedProduct.setRate                  (apiRateAndDelivery.getObj1());
						bookedProduct.setDelivery              (apiRateAndDelivery.getObj2());
				//----------------------------------
				long productId = cartWishlist.getProductId();
				TwoObjects<Double, String> twoObjects = ProductDetailsDAO.getSellPriceAndWarranty( productId );
				double sellPrice = twoObjects.getObj1();
				
						/********* WholeSale Offer or not **********/
						WholeSaleOffer wholeSaleOffer = WholeSaleOffer.getWholeSaleOffer(productId);
						boolean isWholeSaleOfferValid = wholeSaleOffer != null && wholeSaleOffer.getQty() <= bookedProduct.getQty();
						if (isWholeSaleOfferValid) {
							double discountedPrice = sellPrice * (1 - (wholeSaleOffer.getDiscount() / 100)); //System.out.println(discountedPrice);
							totalSavings += (sellPrice - discountedPrice) * bookedProduct.getQty();          //System.out.println(totalSavings);
							bookedProduct.setSellPrice(discountedPrice); 	// discount added on each item
						}
						else {
							//Normal Price
							bookedProduct.setSellPrice(sellPrice); 
						}
						/********* End WholeSale Offer **********/
				
						bookedProduct.setWarranty (twoObjects.getObj2());
				//----------------------------------
						bookedProduct.setStock(TransientData.getStock(productId));
				//----------------------------------
				Product productBean = buyerSearchDAO.getProductBean(bookedProduct.getProductId());
						
						bookedProduct.setProductBean(productBean);
						
				if(bookedProduct.getStock() != 0)  {
					totalSellPrice += (bookedProduct.getSellPrice() * bookedProduct.getQty()) + bookedProduct.getRate().doubleValue(); 
				}
				
				totalSellPrice = Conversions.round(totalSellPrice, 2);
				System.out.println(totalSellPrice);
				
				bookedProducts.add(bookedProduct);
				
				i++;
			} // for close			
			
			order.setBookedProductList(bookedProducts);
			order.setTotalSellPrice   (totalSellPrice);
			order.setTotalSavings(totalSavings);
			
			
			/********** Set Address To Ship ********/
			
			order = buyerSearchDAO.setDeliveryAddressIfExists(order, user);				
			
			
			/************ Set Session **************/
			session.setAttribute("order", order);
			
			
			/********** Next Page ************/
			request.getRequestDispatcher("jsp_Buyer/OrderReviewAndSubmit.jsp").forward(request, response);
			

			
			
	}  //  END goto_OrderReviewPage()
	
	
	
	
}
