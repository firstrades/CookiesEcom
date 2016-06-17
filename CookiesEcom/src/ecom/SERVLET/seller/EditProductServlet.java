package ecom.SERVLET.seller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import ecom.DAO.Seller.EditProductDAO;
import ecom.model.KeyFeature;
import ecom.model.Product;
import ecom.model.Size;

@MultipartConfig
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EditProductDAO editProductDAO;
 
	@Override
	public void init() {
		editProductDAO = EditProductDAO.getInstance();
	}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("EditProductServlet Destroyed"); 
	};
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@SuppressWarnings("unused")
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String servletPath = request.getServletPath();
		
		
		if (servletPath.equals("/EditBasicProduct")) {
			
			System.out.println("Entered EditBasicProduct");
			
			/*********************************************
							* Get Request *
			*********************************************/
			
			Product productBean = new Product();
			
			productBean.setProductId                 (Long.parseLong(request.getParameter("productId")));				
			
			productBean.setCategory                  (request.getParameter("category"           ));   
			productBean.setSubCategory               (request.getParameter("subCategory"        ));
			productBean.setCompanyName               (request.getParameter("companyOfTheProduct").trim());
			productBean.setProductName               (request.getParameter("productName"        ).trim());			
			
			productBean.getPrice().setManufacturingCost     (Double.parseDouble(request.getParameter("manufacturingCost").trim())     );
			productBean.getPrice().setProfitMarginPercentage(Double.parseDouble(request.getParameter("profitMarginPercentage").trim()));
			productBean.getPrice().setSalePriceToAdmin      (Double.parseDouble(request.getParameter("salePriceToAdmin").trim())      );
			productBean.getPrice().setSalePriceCustomer     (Double.parseDouble(request.getParameter("salePriceToCustomer").trim())   );
			productBean.getPrice().setMarkup                (Double.parseDouble(request.getParameter("markupPercentage").trim())      );				
			productBean.getPrice().setListPrice             (Double.parseDouble(request.getParameter("listPrice").trim())             );
			productBean.getPrice().setDiscount              (Double.parseDouble(request.getParameter("discount").trim())              );								
			
			productBean.setStock                     (Integer.parseInt(request.getParameter("stock").trim())             );
			productBean.setWeight                    (Double.parseDouble(request.getParameter("weight").trim())          );
			productBean.setWarranty                  (request.getParameter("warranty").trim()                            );
			productBean.setCancellationAfterBooked   (Integer.parseInt(request.getParameter("cancellationPeriod").trim()));
			
			productBean.setShippingCost                  (Double.parseDouble(request.getParameter("shipping")        .trim()));
			productBean.setDeliveryTime                  (Integer.parseInt  (request.getParameter("delivery")        .trim()));
			
			
			/**************** Key Features ***************/
			
		
			List<KeyFeature> updateKeyFeatures = new ArrayList<>();
			List<KeyFeature> newKeyFeatures = new ArrayList<>();
			
			
			
			if (!request.getParameter("keyH1").equals("nil")) {
				
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setId(Integer.parseInt(request.getParameter("keyH1")));
				keyFeature.setKey(request.getParameter("key1"));
				keyFeature.setValue(request.getParameter("value1"));
				
				updateKeyFeatures.add(keyFeature);
				keyFeature = null;
				
			} else {
				
				if (!request.getParameter("key1").equals("") && !request.getParameter("value1").equals("")) {
				
					KeyFeature keyFeature = new KeyFeature();					
					keyFeature.setKey(request.getParameter("key1"));
					keyFeature.setValue(request.getParameter("value1"));
					keyFeature.setProductId(productBean.getProductId());
					
					newKeyFeatures.add(keyFeature);
					keyFeature = null;
				}
			}
			
			
			if (!request.getParameter("keyH2").equals("nil")) {
				
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setId(Integer.parseInt(request.getParameter("keyH2")));
				keyFeature.setKey(request.getParameter("key2"));
				keyFeature.setValue(request.getParameter("value2"));				
				
				updateKeyFeatures.add(keyFeature);
				keyFeature = null;
				
			} else {
				
				if (!request.getParameter("key2").equals("") && !request.getParameter("value2").equals("")) {
				
					KeyFeature keyFeature = new KeyFeature();					
					keyFeature.setKey(request.getParameter("key2"));
					keyFeature.setValue(request.getParameter("value2"));
					keyFeature.setProductId(productBean.getProductId());
					
					newKeyFeatures.add(keyFeature);
					keyFeature = null;
				}
			}
			
			if (!request.getParameter("keyH3").equals("nil")) {
				
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setId(Integer.parseInt(request.getParameter("keyH3")));
				keyFeature.setKey(request.getParameter("key3"));
				keyFeature.setValue(request.getParameter("value3"));				
				
				updateKeyFeatures.add(keyFeature);
				keyFeature = null;
				
			} else {
				
				if (!request.getParameter("key3").equals("") && !request.getParameter("value3").equals("")) {
				
					KeyFeature keyFeature = new KeyFeature();					
					keyFeature.setKey(request.getParameter("key3"));
					keyFeature.setValue(request.getParameter("value3"));
					keyFeature.setProductId(productBean.getProductId());
					
					newKeyFeatures.add(keyFeature);
					keyFeature = null;
				}
			}
			
			if (!request.getParameter("keyH4").equals("nil")) {
				
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setId(Integer.parseInt(request.getParameter("keyH4")));
				keyFeature.setKey(request.getParameter("key4"));
				keyFeature.setValue(request.getParameter("value4"));				
				
				updateKeyFeatures.add(keyFeature);
				keyFeature = null;
				
			} else {
				
				if (!request.getParameter("key4").equals("") && !request.getParameter("value4").equals("")) {
				
					KeyFeature keyFeature = new KeyFeature();					
					keyFeature.setKey(request.getParameter("key4"));
					keyFeature.setValue(request.getParameter("value4"));
					keyFeature.setProductId(productBean.getProductId());
					
					newKeyFeatures.add(keyFeature);
					keyFeature = null;
				}
			}
				
			
			if (!request.getParameter("keyH5").equals("nil")) {
				
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setId(Integer.parseInt(request.getParameter("keyH5")));
				keyFeature.setKey(request.getParameter("key5"));
				keyFeature.setValue(request.getParameter("value5"));				
				
				updateKeyFeatures.add(keyFeature);
				keyFeature = null;
				
			} else {
				
				if (!request.getParameter("key5").equals("") && !request.getParameter("value5").equals("")) {
				
					KeyFeature keyFeature = new KeyFeature();					
					keyFeature.setKey(request.getParameter("key5"));
					keyFeature.setValue(request.getParameter("value5"));
					keyFeature.setProductId(productBean.getProductId());
					
					newKeyFeatures.add(keyFeature);
					keyFeature = null;
				}
			}
			
			if (!request.getParameter("keyH6").equals("nil")) {
				
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setId(Integer.parseInt(request.getParameter("keyH6")));
				keyFeature.setKey(request.getParameter("key6"));
				keyFeature.setValue(request.getParameter("value6"));				
				
				updateKeyFeatures.add(keyFeature);
				keyFeature = null;
				
			} else {
				
				if (!request.getParameter("key6").equals("") && !request.getParameter("value6").equals("")) {
				
					KeyFeature keyFeature = new KeyFeature();					
					keyFeature.setKey(request.getParameter("key6"));
					keyFeature.setValue(request.getParameter("value6"));
					keyFeature.setProductId(productBean.getProductId());
					
					newKeyFeatures.add(keyFeature);
					keyFeature = null;
				}
			}
			
			if (!request.getParameter("keyH7").equals("nil")) {
				
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setId(Integer.parseInt(request.getParameter("keyH7")));
				keyFeature.setKey(request.getParameter("key7"));
				keyFeature.setValue(request.getParameter("value7"));				
				
				updateKeyFeatures.add(keyFeature);
				keyFeature = null;
				
			} else {
				
				if (!request.getParameter("key7").equals("") && !request.getParameter("value7").equals("")) {
				
					KeyFeature keyFeature = new KeyFeature();					
					keyFeature.setKey(request.getParameter("key7"));
					keyFeature.setValue(request.getParameter("value7"));
					keyFeature.setProductId(productBean.getProductId());
					
					newKeyFeatures.add(keyFeature);
					keyFeature = null;
				}
			}
			
			if (!request.getParameter("keyH8").equals("nil")) {
				
				KeyFeature keyFeature = new KeyFeature();
				keyFeature.setId(Integer.parseInt(request.getParameter("keyH8")));
				keyFeature.setKey(request.getParameter("key8"));
				keyFeature.setValue(request.getParameter("value8"));				
				
				updateKeyFeatures.add(keyFeature);
				keyFeature = null;
				
			} else {
				
				if (!request.getParameter("key8").equals("") && !request.getParameter("value8").equals("")) {
				
					KeyFeature keyFeature = new KeyFeature();					
					keyFeature.setKey(request.getParameter("key8"));
					keyFeature.setValue(request.getParameter("value8"));
					keyFeature.setProductId(productBean.getProductId());
					
					newKeyFeatures.add(keyFeature);
					keyFeature = null;
				}
			}
			
			
			/***************** Sizes ********************/
			
			
			List<Size> updateSize = new ArrayList<>();
			List<Size> newSize = new ArrayList<>();
			
			
			
			if (!request.getParameter("sizeH1").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH1")));
				size.setSize(request.getParameter("size1"));
				size.setCount(Integer.parseInt(request.getParameter("count1").trim()));
				
				System.out.println(request.getParameter("sizeH1"));
				System.out.println(request.getParameter("size1").trim());
				System.out.println(Integer.parseInt(request.getParameter("count1").trim()));
				
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size1").equals("") && !request.getParameter("count1").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size1").trim());
					size.setCount(Integer.parseInt(request.getParameter("count1").trim()));
					size.setProductId(productBean.getProductId());
					
					System.out.println(request.getParameter("size1"));
					System.out.println(Integer.parseInt(request.getParameter("count1").trim()));
					
					newSize.add(size);
					size = null;
				}
			}
			
			if (!request.getParameter("sizeH2").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH2")));
				size.setSize(request.getParameter("size2").trim());
				size.setCount(Integer.parseInt(request.getParameter("count2").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size2").equals("") && !request.getParameter("count2").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size2").trim());
					size.setCount(Integer.parseInt(request.getParameter("count2").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			if (!request.getParameter("sizeH3").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH3")));
				size.setSize(request.getParameter("size3").trim());
				size.setCount(Integer.parseInt(request.getParameter("count3").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size3").equals("") && !request.getParameter("count3").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size3").trim());
					size.setCount(Integer.parseInt(request.getParameter("count3").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			
			if (!request.getParameter("sizeH4").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH4")));
				size.setSize(request.getParameter("size4").trim());
				size.setCount(Integer.parseInt(request.getParameter("count4").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size4").equals("") && !request.getParameter("count4").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size4").trim());
					size.setCount(Integer.parseInt(request.getParameter("count4").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			if (!request.getParameter("sizeH5").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH5")));
				size.setSize(request.getParameter("size5").trim());
				size.setCount(Integer.parseInt(request.getParameter("count5").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size5").equals("") && !request.getParameter("count5").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size5").trim());
					size.setCount(Integer.parseInt(request.getParameter("count5").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			
			if (!request.getParameter("sizeH6").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH6")));
				size.setSize(request.getParameter("size6").trim());
				size.setCount(Integer.parseInt(request.getParameter("count6").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size6").equals("") && !request.getParameter("count6").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size6").trim());
					size.setCount(Integer.parseInt(request.getParameter("count6").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			
			if (!request.getParameter("sizeH7").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH7")));
				size.setSize(request.getParameter("size7").trim());
				size.setCount(Integer.parseInt(request.getParameter("count7").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size7").equals("") && !request.getParameter("count7").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size7").trim());
					size.setCount(Integer.parseInt(request.getParameter("count7").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			if (!request.getParameter("sizeH8").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH8")));
				size.setSize(request.getParameter("size8").trim());
				size.setCount(Integer.parseInt(request.getParameter("count8").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size8").equals("") && !request.getParameter("count8").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size8").trim());
					size.setCount(Integer.parseInt(request.getParameter("count8").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			if (!request.getParameter("sizeH9").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH9")));
				size.setSize(request.getParameter("size9").trim());
				size.setCount(Integer.parseInt(request.getParameter("count9").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size9").equals("") && !request.getParameter("count9").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size9").trim());
					size.setCount(Integer.parseInt(request.getParameter("count9").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			
			if (!request.getParameter("sizeH10").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH10")));
				size.setSize(request.getParameter("size10").trim());
				size.setCount(Integer.parseInt(request.getParameter("count10").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size10").equals("") && !request.getParameter("count10").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size10").trim());
					size.setCount(Integer.parseInt(request.getParameter("count10").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			
			if (!request.getParameter("sizeH11").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH11")));
				size.setSize(request.getParameter("size11").trim());
				size.setCount(Integer.parseInt(request.getParameter("count11").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size11").equals("") && !request.getParameter("count11").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size11").trim());
					size.setCount(Integer.parseInt(request.getParameter("count11").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}

			if (!request.getParameter("sizeH12").equals("nil")) {
				
				Size size = new Size();
				size.setId(Integer.parseInt(request.getParameter("sizeH12")));
				size.setSize(request.getParameter("size12").trim());
				size.setCount(Integer.parseInt(request.getParameter("count12").trim()));
				
				updateSize.add(size);
				size = null;
				
			} else {
				
				if (!request.getParameter("size12").equals("") && !request.getParameter("count12").equals("")) {
				
					Size size = new Size();					
					size.setSize(request.getParameter("size12").trim());
					size.setCount(Integer.parseInt(request.getParameter("count12").trim()));
					size.setProductId(productBean.getProductId());
					
					newSize.add(size);
					size = null;
				}
			}
			
			
			/************ If-Size-Exists *****************/
			
			int totalSizesStock = 0;
			
			if (productBean.getSizes().size() > 0) {
				
				for (Size size : productBean.getSizes()) {
					
					if (size.getCount() > 0)
						totalSizesStock += size.getCount();
				}
				
				productBean.setStock(totalSizesStock);
			}
			
			
			
			/************ End If-Size-Exists *****************/
			
			
			
			
			
			/*******************************************************
			 	*  Database - Edit Product Table  *
			*******************************************************/	
			boolean status1 = false; boolean status2 = false; boolean status3 = false;
			
			productBean     = editProductDAO.editProduct(productBean);
			
			status1 = editProductDAO.editKeyFeaturesAndSizes(updateKeyFeatures, updateSize, productBean);
			status2 = editProductDAO.newKeyFeaturesAndSizes (newKeyFeatures, newSize, productBean);
			status3 = editProductDAO.editShippingDelivery   (productBean);
			
			
			/***************** Next Page **********************/
			
			JSONObject jsonObject = new JSONObject();
			
			if (status1 == true && status2 == true && status3 == true) {
				
				try {
					jsonObject.put("success", "Basic Features Updated");
					//System.out.println(jsonObject.get("success"));
				} catch (JSONException e) {					
					e.printStackTrace();
				}
				
				response.getWriter().write(jsonObject.toString());	
			}
			else {
				
				try {
					jsonObject.put("falied", "Basic Features Not Updated");
					//System.out.println(jsonObject.get("falied"));
				} catch (JSONException e) {					
					e.printStackTrace();
				}
				
				response.getWriter().write(jsonObject.toString());	
			}
			
		}
		else if (servletPath.equals("/EditIconImage")) {
			
			System.out.println("Entered EditIconImage");
			
			/*********************************************
						* Get Request *
			*********************************************/
			
			String productId1 = request.getParameter("productId");   
			long productId = Long.parseLong(productId1);
			
			String sellerId1 = request.getParameter("sellerId");
			long sellerId    = Long.parseLong(sellerId1);
			
			Part part               = request.getPart("iconImage");			
			InputStream inputStream = part.getInputStream();               
			
			/*******************************************************
			 	*  Database - Edit Product Table  *
			*******************************************************/			
			boolean status = editProductDAO.editImage(productId, inputStream, "iconImage");
			
			
		}
		else if (servletPath.equals("/EditImage1")) {
			
			System.out.println("Entered EditImage1");
			
			/*********************************************
						* Get Request *
			*********************************************/
			
			String productId1 = request.getParameter("productId");  
			long productId = Long.parseLong(productId1);
			
			String sellerId1 = request.getParameter("sellerId");
			long sellerId    = Long.parseLong(sellerId1);
			
			Part part               = request.getPart("image1");			
			InputStream inputStream = part.getInputStream();               
			
			/*******************************************************
			 	*  Database - Edit Product Table  *
			*******************************************************/		
			boolean status = editProductDAO.editImage(productId, inputStream, "image1");
			
			
		}
		else if (servletPath.equals("/EditImage2")) {
	
			System.out.println("Entered EditImage2");
			
			/*********************************************
						* Get Request *
			*********************************************/
			
			String productId1 = request.getParameter("productId");  
			long productId = Long.parseLong(productId1);
			
			String sellerId1 = request.getParameter("sellerId");
			long sellerId    = Long.parseLong(sellerId1);
			
			Part part               = request.getPart("image2");			
			InputStream inputStream = part.getInputStream();               
			
			/*******************************************************
			 	*  Database - Edit Product Table  *
			*******************************************************/			
			boolean status = editProductDAO.editImage(productId, inputStream, "image2");
			
			
		}
		
		
		
		
		
	}
}
