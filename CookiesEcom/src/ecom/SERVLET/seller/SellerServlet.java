package ecom.SERVLET.seller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.mysql.jdbc.PacketTooBigException;

import ecom.DAO.Seller.ProductDAO;
import ecom.DAO.Seller.SellerDAO;
import ecom.Implementation.Courier.SOAP.TrackByNumber;
import ecom.Implementation.Courier.SOAP.TrackingIdGeneration;
import ecom.Interface.Courier.TrackByNumberInterface;
import ecom.Interface.Courier.TrackingIdGenerationInterface;
import ecom.beans.TransientData;
import ecom.model.KeyFeature;
import ecom.model.OrderTable;
import ecom.model.Product;
import ecom.model.Size;
import ecom.model.TwoObjects;
import ecom.model.User;
import ecom.model.WholeSaleOffer;


@MultipartConfig
public class SellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SellerDAO sellerDAO;
	private ProductDAO productDAO;
	@SuppressWarnings("unused")
	private String nextPage;
	
	private final String SAVE_DIR = "productImages";
	
	@Override
	public void init() {
		sellerDAO = SellerDAO.getInstance();
		productDAO = ProductDAO.getInstance();
		nextPage = null;
	}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("SellerServlet Destroyed"); 
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
		
		HttpSession session = request.getSession();
		
		String servletPath = request.getServletPath();
		
		if (servletPath.equals("/CreateProduct")) {
			
			System.out.println("Entered CreateProduct");
			
			/****************************  Get Request  ******************************/				
			
			Product product = new Product();                             
			
			product.setCategory   (request.getParameter("category")   .trim());
			product.setSubCategory(request.getParameter("subCategory").trim());
			product.setCompanyName(request.getParameter("company")    .trim());
			product.setProductName(request.getParameter("product")    .trim());				
			
			//Key Features						
			
			if (!request.getParameter("key1").equals("") && !request.getParameter("value1").equals("")) {	
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setKey  (request.getParameter("key1"  )); 
				keyFeature.setValue(request.getParameter("value1")); 
				product.getKeyFeatures().add(keyFeature);	
				keyFeature = null;
			}
			if (!request.getParameter("key2").equals("") && !request.getParameter("value2").equals("")) {
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setKey  (request.getParameter("key2"  ));  
				keyFeature.setValue(request.getParameter("value2"));  
				product.getKeyFeatures().add(keyFeature);	
				keyFeature = null;
			}
			if (!request.getParameter("key3").equals("") && !request.getParameter("value3").equals("")) {	
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setKey  (request.getParameter("key3"  ));
				keyFeature.setValue(request.getParameter("value3"));
				product.getKeyFeatures().add(keyFeature);	
				keyFeature = null;
			}
			if (!request.getParameter("key4").equals("") && !request.getParameter("value4").equals("")) {	
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setKey  (request.getParameter("key4"  ));
				keyFeature.setValue(request.getParameter("value4"));
				product.getKeyFeatures().add(keyFeature);
				keyFeature = null;
			}
			if (!request.getParameter("key5").equals("") && !request.getParameter("value5").equals("")) {
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setKey  (request.getParameter("key5"  ));
				keyFeature.setValue(request.getParameter("value5"));
				product.getKeyFeatures().add(keyFeature);
				keyFeature = null;
			}
			if (!request.getParameter("key6").equals("") && !request.getParameter("value6").equals("")) {
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setKey  (request.getParameter("key6"  ));
				keyFeature.setValue(request.getParameter("value6"));
				product.getKeyFeatures().add(keyFeature);	
				keyFeature = null;
			}
			if (!request.getParameter("key7").equals("") && !request.getParameter("value7").equals("")) {
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setKey  (request.getParameter("key7"  ));
				keyFeature.setValue(request.getParameter("value7"));
				product.getKeyFeatures().add(keyFeature);	
				keyFeature = null;
			}
			if (!request.getParameter("key8").equals("") && !request.getParameter("value8").equals("")) {	
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setKey  (request.getParameter("key8"  ));
				keyFeature.setValue(request.getParameter("value8"));
				product.getKeyFeatures().add(keyFeature);
				keyFeature = null;
			}				
			
			//Sizes			
			
			if (!request.getParameter("size1").equals("") && !request.getParameter("count1").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size1"));
				size.setCount(Integer.parseInt(request.getParameter("count1").trim()));
				product.getSizes().add(size);	
				size = null;
			}
			if (!request.getParameter("size2").equals("") && !request.getParameter("count2").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size2"));
				size.setCount(Integer.parseInt(request.getParameter("count2").trim()));
				product.getSizes().add(size);	
				size = null;
			}
			if (!request.getParameter("size3").equals("") && !request.getParameter("count3").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size3"));
				size.setCount(Integer.parseInt(request.getParameter("count3").trim()));
				product.getSizes().add(size);	
				size = null;
			}
			if (!request.getParameter("size4").equals("") && !request.getParameter("count4").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size4"));
				size.setCount(Integer.parseInt(request.getParameter("count4").trim()));
				product.getSizes().add(size);
				size = null;
			}
			if (!request.getParameter("size5").equals("") && !request.getParameter("count5").equals("")) {
				Size size = new Size();
				size.setSize (request.getParameter("size5"));
				size.setCount(Integer.parseInt(request.getParameter("count5").trim()));
				product.getSizes().add(size);	
				size = null;
			}
			if (!request.getParameter("size6").equals("") && !request.getParameter("count6").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size6"));
				size.setCount(Integer.parseInt(request.getParameter("count6").trim()));
				product.getSizes().add(size);	
				size = null;
			}
			if (!request.getParameter("size7").equals("") && !request.getParameter("count7").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size7"));
				size.setCount(Integer.parseInt(request.getParameter("count7").trim()));
				product.getSizes().add(size);
				size = null;
			}
			if (!request.getParameter("size8").equals("") && !request.getParameter("count8").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size8"));
				size.setCount(Integer.parseInt(request.getParameter("count8").trim()));
				product.getSizes().add(size);	
				size = null;
			}
			if (!request.getParameter("size9").equals("") && !request.getParameter("count9").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size9"));
				size.setCount(Integer.parseInt(request.getParameter("count9").trim()));
				product.getSizes().add(size);	
				size = null;
			}
			if (!request.getParameter("size10").equals("") && !request.getParameter("count10").equals("")) {
				Size size = new Size();
				size.setSize (request.getParameter("size10"));
				size.setCount(Integer.parseInt(request.getParameter("count10").trim()));
				product.getSizes().add(size);	
				size = null;
			}
			if (!request.getParameter("size11").equals("") && !request.getParameter("count11").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size11"));
				size.setCount(Integer.parseInt(request.getParameter("count11").trim()));
				product.getSizes().add(size);	
				size = null;
			}
			if (!request.getParameter("size12").equals("") && !request.getParameter("count12").equals("")) {	
				Size size = new Size();
				size.setSize (request.getParameter("size12"));
				size.setCount(Integer.parseInt(request.getParameter("count12").trim()));
				product.getSizes().add(size);
				size = null;
			}
			
			
			//Price
			product.getPrice().setManufacturingCost     (Double.parseDouble(request.getParameter("manufacturingCost")     .trim()));
			product.getPrice().setProfitMarginPercentage(Double.parseDouble(request.getParameter("profitMarginPercentage").trim()));
			product.getPrice().setSalePriceToAdmin      (Double.parseDouble(request.getParameter("salePriceToAdmin")      .trim()));
			product.getPrice().setListPrice             (Double.parseDouble(request.getParameter("listPrice")             .trim()));
			product.getPrice().setDiscount              (Double.parseDouble(request.getParameter("discount")              .trim()));
			
			//Others
			product.setStock                  (Integer.parseInt(request.getParameter("stock")             .trim()));
			product.setWeight                 (Double.parseDouble(request.getParameter("weight")          .trim()));
			product.setWarranty               (request.getParameter("warranty")                           .trim());
			product.setCancellationAfterBooked(Integer.parseInt(request.getParameter("cancellationPeriod").trim()));
			
			product.setShippingCost           (Double.parseDouble(request.getParameter("shipping")        .trim()));
			product.setDeliveryTime           (Integer.parseInt  (request.getParameter("delivery")        .trim()));
			
			/************ If-Size-Exists *****************/
			
			int totalSizesStock = 0;
			
			if (product.getSizes().size() > 0) {
				
				for (Size size : product.getSizes()) {
					
					if (size.getCount() > 0)
						totalSizesStock += size.getCount();
				}
				
				product.setStock(totalSizesStock);
			}
			
			
			
			/************ End If-Size-Exists *****************/
			String savePath = getServletContext().getRealPath("") + SAVE_DIR;
			System.out.println(savePath);
			
			File imageSaveDir = new File(savePath);
			if (!imageSaveDir.exists())
				imageSaveDir.mkdir();
			
			//Images
			Part part1 = request.getPart("iconImage");				   
			InputStream inputStream1 = part1.getInputStream();
			
			Part part2 = request.getPart("image1"   );				
			InputStream inputStream2 = part2.getInputStream();
			
			Part part3 = request.getPart("image2"   );				
			InputStream inputStream3 = part3.getInputStream();
			
			/*****************  Get Session  **************************/
			User user = (User) session.getAttribute("user");				
			
			/*******************************************************
			 	*  Database - Insert & Generate Product Code  *
			*******************************************************/			
			long productId = 0;  boolean isPacketTooBig = false; int keyCount = 0; int sizeCount = 0;
			boolean error = false;
			
			try {
				
				productId = ProductDAO.addProduct(user, product); 
				productDAO.addImages(productId, inputStream1, inputStream2, inputStream3);
				
				if (productId != -1) {					
					keyCount = productDAO.addKeyFeatures(productId, product);					
				}
				
				if (productId != -1) {					
					sizeCount = productDAO.addSizes(productId, product);
				}
				
				if (productId != -1) {
					productDAO.addShippingDelivery(productId, product);
				}
				
			} catch (PacketTooBigException e) {
				isPacketTooBig = true;
				e.printStackTrace();
			} catch (Exception e) {				
				e.printStackTrace();
				
				if (productId != 0 && keyCount == 0)
					productDAO.deleteProduct(productId);
				
				if (productId != 0 && sizeCount == 0)
					productDAO.deleteKeyFeatures(productId);
				
				error = true;
			}
			
			if (productId != -1)
				System.out.println("Database Updated");
			else
				System.out.println("Database Not Updated");	
			
			/********* Clear Up **********/
			product = null;					
			
			/*********************  Send Response  *****************************/
			if (isPacketTooBig)
				response.getWriter().write("Decrease Image Size.");
			else if (error == true)
				response.getWriter().write("Some error occured.");
			else
				response.getWriter().write("Product Sent For Approval. Product ID: " + productId);
			
		}		
		
		else if (servletPath.equals("/AddProduct")) {
			
			System.out.println("Entered AddProduct");
			
			request.getRequestDispatcher("jsp_Seller/Product_Add.jsp").forward(request, response);
		}
		
		else if (servletPath.equals("/ViewProductList")) {
			
			System.out.println("Entered ViewProductList");
			
			/************************  Get Request  **************************/			
			String category    = request.getParameter("category");           
			String subCategory = request.getParameter("subCategory");     
			
			/**********************  Get Session  ******************************/			
			User user     = (User) session.getAttribute("user");
			
			/*******************************************************
			 	*  Database - Get Product List  *
			*******************************************************/				
			List<Product> productList = productDAO.getProducts(category, subCategory, user);
			
			int MAX = TransientData.getMAX(user.getUserInfo().getId(), category, subCategory);
			
			/****************** Sort In Descending Order ***************************/
			
			Comparator<Product> compare = new Comparator<Product>() {
				
				@Override
				public int compare(Product a, Product b) {
					
					if (a.getProductId() < b.getProductId())
						
						return 1;
					
					else if (a.getProductId() > b.getProductId())
						
						return -1;
					
					else
						
						return 0;
				}
			};
			
			Collections.sort(productList, compare);
			
			/*************** Set Request **************************/
			
			request.setAttribute("MAX", MAX);
			
			/*******************  Set Session  ***************************/

			session.setAttribute("productList", productList);				
			
			/*******************  Send Response  **************************/
			nextPage = "jsp_Seller/ViewProductList.jsp";
			request.getRequestDispatcher("jsp_Seller/ViewProductList.jsp").forward(request, response);
		}
		
		else if (servletPath.equals("/ViewProductList_Ajax")) {
			
			System.out.println("Entered ViewProductList_Ajax");			
			
			/*******************************************************
							  *  Get Session  *
			*******************************************************/				
			@SuppressWarnings("unchecked")
			List<Product> productList = (List<Product>) session.getAttribute("productList");
			
			/******************* Get Size **********************/
			
			final int shownMAX         = (int) session.getAttribute("MAX");			
			final int productListSIZE  = productList.size();   System.out.println(productListSIZE);
			
			/*************** Create JSON Data ********************/
			
			if (productListSIZE > shownMAX) {
			
					JSONArray  jsonArray  = new JSONArray();
					
					for (int i = shownMAX; i < productListSIZE; i++) {
						
						JSONObject jsonObject = new JSONObject();
						
						try {
							jsonObject.put("category",    productList.get(i).getCategory());
							jsonObject.put("subCategory", productList.get(i).getSubCategory());
							jsonObject.put("productName", productList.get(i).getProductName());
							jsonObject.put("companyName", productList.get(i).getCompanyName());
							jsonObject.put("companyName", productList.get(i).getCompanyName());
							jsonObject.put("productId",   productList.get(i).getProductId());
							jsonObject.put("listPrice",   productList.get(i).getPrice().getListPrice());
							jsonObject.put("discount",    productList.get(i).getPrice().getDiscount());
							jsonObject.put("salePrice",   productList.get(i).getPrice().getSalePriceToAdmin());
							jsonObject.put("markup",      productList.get(i).getPrice().getMarkup());
							jsonObject.put("iconImage",   productList.get(i).getImages().getIconImage());
							
							jsonArray.put(jsonObject);
							
						} catch (JSONException e) {					
							e.printStackTrace();
						}
					}  // for close
					
					/*************** Send Data ********************/
					response.setContentType("application/json");  
					response.getWriter().write(jsonArray.toString());
			
			} // if close
			
			
		}
		
		else if (servletPath.equals("/DeleteProduct")) {
			
			System.out.println("Entered DeleteProduct");	
			
			String productId1  = null;
			String category    = null;
			String subCategory = null;
			
			if (request.getParameter("productId") == null) {    // Angular
			
			        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			        
			        String jsonData = null;
			        
			        if (br != null) {
			        	
			            jsonData = br.readLine();                               
			        }
			        
			        try {
			        	
						JSONObject jsonObject1 = new JSONObject(jsonData);
						
						productId1  = jsonObject1.getString("productId");        System.out.println("DP1: " + productId1);
						category    = jsonObject1.getString("productId");        System.out.println("DP2: " + category);
						subCategory = jsonObject1.getString("subCategory");      System.out.println("DP3: " + subCategory);
						
					} catch (JSONException e) {				
						e.printStackTrace();
					}
	        
			} else {   // jQuery
			
					/*******************************************************
									*  Get Request  *
					*******************************************************/
					productId1  = request.getParameter("productId");          System.out.println("DP1: " + productId1);
					category    = request.getParameter("category");           System.out.println("DP2: " + category);
					subCategory = request.getParameter("subCategory");        System.out.println("DP3: " + subCategory);
				
			}
			
			/******************************************************
			 				*  Processing  *
			 ******************************************************/
			
			long productId = Long.parseLong(productId1);
			
			/*******************************************************
			 	*  Database - Delete rows from tables  *
			*******************************************************/			
			boolean status = productDAO.deleteProduct(productId, subCategory);			
			System.out.println(status);
			
			/*******************************************************
							*  Send Response  *
			*******************************************************/
			
			if (status == true) {
				
				JSONObject jsonObject = new JSONObject();
				
				try {
					jsonObject.put("success", true);
				} catch (JSONException e) {					
					e.printStackTrace();
				}
				
				response.setContentType("application/json");
				response.getWriter().write(jsonObject.toString());
			} // if close
			

		} //  /DeleteProduct
		
		else if (servletPath.equals("/RetrieveOrderedItemsForSeller")) {
			
			System.out.println("Entered RetrieveOrderedItemsForSeller");
			
			/********** Get Session **************/
			User user = (User) session.getAttribute("user");
			
			/************* Database **************/			
			List<OrderTable> orderTables = sellerDAO.getOrderTables(user);
			
			/********* Set Request *************/
			request.setAttribute("orderTables", orderTables);
			
			
			/********* Next Page **********/
			
			request.getRequestDispatcher("jsp_Seller/OrderedItemsStatus.jsp").forward(request, response);
			
		} //  /RetrieveOrderedItemsForSeller
		
		else if (servletPath.equals("/GenerateTrackNumberCOD")) {
			
			System.out.println("Entered GenerateTrackNumberCOD");	
						
			/*********** Call API *************/		
			
			String paymentType = "COD";	    boolean pickup = false;
			
			try {
				
				pickup = callShipTransaction(request, paymentType, pickup);					
				
			} catch (SOAPException | ParserConfigurationException | SAXException | ParseException e) {				
				e.printStackTrace();			
			} catch (Exception e) {
				e.printStackTrace();					
			}
			
			
			/************* JSON Data for Next Page ****************/
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("pickup", pickup);
			} catch (JSONException e) {	e.printStackTrace(); }
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		} //  /GenerateTrackNumberCOD
		
		else if (servletPath.equals("/GenerateTrackNumberBANK")) {
			
			System.out.println("Entered GenerateTrackNumberBANK");
			
			/*********** Call API *************/
			
			String paymentType = "BANK";    boolean pickup = false;			
			
			try {				
				
				pickup = callShipTransaction(request, paymentType, pickup);
				
			} catch (SOAPException | ParserConfigurationException | SAXException | ParseException e) {				
				e.printStackTrace();
			}
			
			
			/************* JSON Data for Next Page ****************/
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("pickup", pickup);
			} catch (JSONException e) {	e.printStackTrace(); }
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		} //  /GenerateTrackNumberBANK
		
		else if (servletPath.equals("/SetPickedUp")) {
			
			System.out.println("Entered SetPickedUp");
			
			/********* Get Request **********/
			
			long   orderTableId = Long.parseLong(request.getParameter("orderTableId"));
			String date         = request.getParameter("date");
			String courierName  = request.getParameter("courierName");
			
			boolean picked = false;
			
			/*********** Database *************/		
			picked = sellerDAO.setPickedUp(orderTableId, date, courierName);
			
			
			/************* JSON Data for Next Page ****************/
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("picked", picked);
			} catch (JSONException e) {	e.printStackTrace(); }
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		} //  /SetPickedUp
		
		else if (servletPath.equals("/GetTrackingDetails")) {
			
			System.out.println("Entered GetTrackingDetails");
			
			/********* Get Request **********/
			
			long orderTableId = Long.parseLong(request.getParameter("orderTableId"));		
			
			/*********** Database *************/			
			TrackByNumberInterface trackByNumberInterface = TrackByNumber.getNewInstance();
			
			try {
				
				trackByNumberInterface.getTrackingStatus(orderTableId);
				
			} catch (UnsupportedOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SOAPException e) {
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
			
			
			
			/************* JSON Data for Next Page ****************/
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("picked", true);
			} catch (JSONException e) {	e.printStackTrace(); }
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		} //  /GetTrackingDetails
		
		
		else if (servletPath.equals("/SetItemCancelled")) {
			
			System.out.println("Entered SetItemCancelled");
			
			/********* Get Request **********/
			
			long orderTableId = Long.parseLong(request.getParameter("orderTableId"));	
			
			/*********** Database *************/			
			boolean status = sellerDAO.setItemCancelled(orderTableId);	
			//boolean status = true;
			
			/************* JSON Data for Next Page ****************/
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("cancelled", status);
			} catch (JSONException e) {	e.printStackTrace(); }
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		} //  /SetItemCancelled
		
		
		else if (servletPath.equals("/GeneratePDF")) {
			
			System.out.println("Entered GeneratePDF");
			
			/********* Get Request **********/
			
			long orderTableId = Long.parseLong(request.getParameter("orderTableId"));	
			
			/*********** Database *************/			
			String base64 = sellerDAO.generatePDF(orderTableId);	
			//boolean status = true;
			
			/************* JSON Data for Next Page ****************/
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("base64", base64);
			} catch (JSONException e) {	e.printStackTrace(); }
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		} //  /GeneratePDF
		
		else if (servletPath.equals("/GeneratePDFRet")) {
			
			System.out.println("Entered GeneratePDFRet");
			
			/********* Get Request **********/
			
			long orderTableId = Long.parseLong(request.getParameter("orderTableId"));	
			
			/*********** Database *************/			
			String base64 = sellerDAO.generatePDFRet(orderTableId);	
			//boolean status = true;
			
			/************* JSON Data for Next Page ****************/
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("base64", base64);
			} catch (JSONException e) {	e.printStackTrace(); }
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		} //  /GeneratePDFRet
		
		
		else if (servletPath.equals("/EditProduct")) {
			
			System.out.println("Entered EditProduct");
			
			/************** Get Request ***********/
			long productId = Integer.parseInt(request.getParameter("productId"));
			
			/*********** Database ****************/
			Product product              = productDAO.getProduct    (productId);
			List<KeyFeature> keyFeatures = productDAO.getKeyFeatures(productId);
			List<Size> sizes             = productDAO.getSizes      (productId);
			TwoObjects<Double, Integer> twoObjects = productDAO.getShippingDelivery(productId);
			
			product.setKeyFeatures(keyFeatures);
			product.setSizes(sizes);
			
			product.setShippingCost(twoObjects.getObj1());
			product.setDeliveryTime(twoObjects.getObj2());
			
			/********* Set Request ***********/
			request.setAttribute("product", product);
			
			/********* Clean Up *********/
			product = null; keyFeatures = null; sizes = null;
			
			/********* Next Page ************/			
			nextPage = "jsp_Seller/EditProduct.jsp";
			request.getRequestDispatcher("jsp_Seller/EditProduct.jsp").forward(request, response);
			
		} //EditProduct
		
		else if (servletPath.equals("/ProductAdvanceFeatures")) {
			
			System.out.println("Entered ProductAdvanceFeatures");
			
			//Get Request
			long productId = Integer.parseInt(request.getParameter("productId")); 
			
			//Database
			Product product = productDAO.getProduct(productId);
			WholeSaleOffer wholeSaleOffer = WholeSaleOffer.getWholeSaleOffer(productId);
			String searchKeyword = sellerDAO.getSearchKeyword(productId);
			
			//Set Request
			request.setAttribute("product", product);
			request.setAttribute("wholeSaleOffer", wholeSaleOffer);
			request.setAttribute("searchKeyword", searchKeyword);
			
			//Next Page
			request.getRequestDispatcher("jsp_Seller/ProductAdvanceFeatures.jsp").forward(request, response);
		}//ProductAdvanceFeatures
		
		else if (servletPath.equals("/EditProductAdvanceFeatures")) {
			
			System.out.println("Entered EditProductAdvanceFeatures");
			long insertedProductId = -1;
			
			//Get Request
			long productId = Long.parseLong(request.getParameter("productId"));      System.out.println(productId);
			int quantity = Integer.parseInt(request.getParameter("quantity"));       System.out.println(quantity);
			double discount = Double.parseDouble(request.getParameter("discount"));  System.out.println(discount);
			
			//Database		
			//if 0 - updated, else product offer inserted and if -1 then any error occurred
			insertedProductId = WholeSaleOffer.setWholeSaleOffer(productId, quantity, discount);		
			
			//Json for Next Page
			JSONObject jsonObject = new JSONObject();
			
			try {
				if (insertedProductId == -1)
					jsonObject.put("status", "Some error occurred! Please try again.");
				else if (insertedProductId == 0)
					jsonObject.put("status", "Offer is updated.");
				else
					jsonObject.put("status", "New offer inserted.");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		}//EditProductAdvanceFeatures
		
		
else if (servletPath.equals("/EditProductSearchKeyword")) {
			
			System.out.println("Entered EditProductSearchKeyword");
			long insertedSearchKey = -1;
			
			//Get Request
			long productId = Long.parseLong(request.getParameter("productId"));      System.out.println(productId);
			String searchKeyword = request.getParameter("searchKeyword").trim();       System.out.println(searchKeyword);			
			
			//Database		
			//if 0 - updated, else product offer inserted and if -1 then any error occurred
			insertedSearchKey = sellerDAO.setSearchKeyword(productId, searchKeyword);		
			
			//Json for Next Page
			JSONObject jsonObject = new JSONObject();
			
			try {
				if (insertedSearchKey == -1)
					jsonObject.put("status", "Some error occurred! Please try again.");
				else if (insertedSearchKey == 0)
					jsonObject.put("status", "Search Keyword is updated.");
				else
					jsonObject.put("status", "New keyword inserted.");
				
			} catch (JSONException e) {				
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		}//EditProductSearchKeyword
				
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean callShipTransaction(HttpServletRequest request, String paymentType, boolean pickup) throws SOAPException, IOException, ParserConfigurationException, SAXException, ParseException {
		
		/*********** Get Request *******************/			
		long orderTableId = Long.parseLong(request.getParameter("orderTableId"));  
		
		TrackingIdGenerationInterface trackingIdGeneration = TrackingIdGeneration.getNewInstance();
		
		pickup = trackingIdGeneration.getTrackingNumber(orderTableId, paymentType);  
		
		return pickup;
	}
	
	
	
}
