package ecom.SERVLET.administration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ecom.DAO.Seller.ProductDAO;
import ecom.DAO.User.CreateUserDAO;
import ecom.DAO.administration.AdminDAO;
import ecom.model.CategoryList;
import ecom.model.ExtractFranchiseDetails;
import ecom.model.OrderTable;
import ecom.model.Product;
import ecom.model.SubCategory;
import ecom.model.User;
import ecom.model.UserAndPickupAddress;

@MultipartConfig
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminDAO   adminDAO;
	private ProductDAO productDAO;
	
	@Override
	public void init() {
		adminDAO   = AdminDAO.getInstance();
		productDAO = ProductDAO.getInstance();
	}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("AdminServlet Destroyed"); 
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
		
			String servletPath = request.getServletPath();
			HttpSession session = request.getSession();
			
			
			if (servletPath.equals("/RetrieveProductForApproval")) {
				
					System.out.println("Entered RetrieveProductForApproval");				
					
					/***************** DataBase ***********************/					
					List<Product> productBeans = adminDAO.getAwaitingProductList();
					
					/************ Data for next page ******************/
					
					JSONObject items = new JSONObject();
					JSONArray jsonArray = new JSONArray();
					
					if (productBeans != null) {
						
						try {
						
							for (Product productBean : productBeans) {
						
									JSONObject jsonObject = new JSONObject();
									
									jsonObject.put("productId",     productBean.getProductId()    );
									jsonObject.put("sellerId",     productBean.getSellerId()     );
									jsonObject.put("sellerCompany", productBean.getSellerCompany());
									
									jsonObject.put("category",    productBean.getCategory()     );
									jsonObject.put("sub_category",productBean.getSubCategory()  );							
									jsonObject.put("companyName", productBean.getCompanyName()  );	
									jsonObject.put("productName", productBean.getProductName()  );
									
									jsonObject.put("listPrice",              productBean.getPrice().getListPrice()             );
									jsonObject.put("discount",               productBean.getPrice().getDiscount()              );
									jsonObject.put("salePriceCustomer",      productBean.getPrice().getSalePriceCustomer()     );
									jsonObject.put("markupPercentage",       productBean.getPrice().getMarkup()                );
									jsonObject.put("salePriceToAdmin",       productBean.getPrice().getSalePriceToAdmin()      );
									jsonObject.put("manufacturingCost",      productBean.getPrice().getManufacturingCost()     );
									jsonObject.put("profitMarginPercentage", productBean.getPrice().getProfitMarginPercentage());
									
									jsonObject.put("stock",                         productBean.getStock()                  );
									jsonObject.put("weight",                        productBean.getWeight()                 );			
									jsonObject.put("warranty",                      productBean.getWarranty()               );										
									jsonObject.put("calcellationAfterBookedInDays", productBean.getCancellationAfterBooked());									
									
									jsonObject.put("fCommissionPercentage", productBean.getCommission().getFranchiseCommission()  );
									jsonObject.put("dCommissionPercentage", productBean.getCommission().getDistributorCommission());
									
									jsonArray.put(jsonObject);	
									jsonObject = null;
							
						
							}  // for close
						
							items.put("items", jsonArray);
							
						
						} catch (JSONException e) {								
							e.printStackTrace();
						}
						
						String json = items.toString();
						
						/********** Clean Up *********/
						productBeans = null;
						items        = null;
						jsonArray    = null;
						System.gc();						
						
						response.setContentType("application/json");
						response.getWriter().write(json);
						
					} // if close
					
			} // RetrieveProductForApproval
			
			else if (servletPath.equals("/ApproveProduct")) {
				
					System.out.println("Entered ApproveProduct");								
					
					Product productBean = new Product();					
					
			        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			        
			        String jsonData = null;
			        
			        if (br != null) {
			        	
			            jsonData = br.readLine();                               
			        }
			        
			        try {
			        	
						JSONObject jsonObject1 = new JSONObject(jsonData);
						
						productBean.setProductId(Long.parseLong(jsonObject1.getString("productId")));          
						
						productBean.getPrice().setDiscount              (Math.floor(Double.parseDouble(jsonObject1.getString("discount"))));              
						productBean.getPrice().setSalePriceCustomer     (Math.ceil(Double.parseDouble(jsonObject1.getString("salePriceToCustomer"))));
						productBean.getPrice().setMarkup                (Double.parseDouble(jsonObject1.getString("markupPercentage")));
						productBean.getPrice().setSalePriceToAdmin      (Double.parseDouble(jsonObject1.getString("salePriceToAdmin")));     
						productBean.getPrice().setProfitMarginPercentage(Double.parseDouble(jsonObject1.getString("profitMarginPercentage"))); 
						
						productBean.getCommission().setFranchiseCommission  (Double.parseDouble(jsonObject1.getString("franComm")));               
						productBean.getCommission().setDistributorCommission(Double.parseDouble(jsonObject1.getString("drisComm")));             
						
						productBean.setWeight                 (Double.parseDouble(jsonObject1.getString("weight")));               
						productBean.setWarranty               (jsonObject1.getString("warranty"));              
						productBean.setCancellationAfterBooked(Integer.parseInt(jsonObject1.getString("cancellationAfterBooked"))); 
						
						jsonObject1 = null;
						
					} catch (JSONException e) {				
						e.printStackTrace();
					}		
					
				
					/***************** DataBase ***********************/					
					boolean status = adminDAO.setProductApprove(productBean);   System.out.println(status);					
					
					
					/***************  Send Response  *****************/
					
					JSONObject jsonObject;					
						
					jsonObject = new JSONObject();
					
					try {
						jsonObject.put("success", status);
					} catch (JSONException e) {					
						e.printStackTrace();
					}
					
					String json = jsonObject.toString();
					
					/***** Clean Up *****/
					productBean = null;
					br          = null;
					jsonData    = null;
					jsonObject = null;
					System.gc();
					
					response.setContentType("application/json");
					response.getWriter().write(json);				
					
					
			} // ApproveProduct
			
			else if (servletPath.equals("/FranchiseRegistration")) {
				
					System.out.println("Entered FranchiseRegistration");
					
					/******************* Set Request *************/
					
					String title          = "Registration For Franchise";
					String submitFunction = "createFranchise()";
					
					request.setAttribute("title",          title);
					request.setAttribute("submitFunction", submitFunction);
					
					/***** Clean Up ******/
					title = null; submitFunction = null; System.gc();
					
					/***************  Next Page  *****************/
					request.getRequestDispatcher("jsp_Administration/FranchiseRegistration.jsp").forward(request, response);
				
			} // FranchiseRegistration
			
			else if (servletPath.equals("/AddFranchise")) {
				
					System.out.println("Entered AddFranchise");
					
					/*********** Get Request ***************/
					
					BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
					
					String json = buffer.readLine();
					
					JSONObject keyValue;
					String userId = null, password = null, fName = null, lName = null, sex = null, company = null, mobile1 = null, mobile2 = null, 
							email1 = null, email2 = null, phone1 = null, phone2 = null, fax1 = null, fax2 = null, address = null, city = null, 
							state = null, pin = null, pan = null, voterId = null;
					try {
						keyValue = new JSONObject(json);
						
						userId   = (String) keyValue.get("userId"  );   
						password = (String) keyValue.get("password");
						fName    = (String) keyValue.get("fName"   );
						lName    = (String) keyValue.get("lName"   );
						sex      = (String) keyValue.get("sex"     );
						company  = (String) keyValue.get("company" );
						mobile1  = (String) keyValue.get("mobile1" );
						mobile2  = (String) keyValue.get("mobile2" );
						email1   = (String) keyValue.get("email1"  );
						email2   = (String) keyValue.get("email2"  );
						phone1   = (String) keyValue.get("phone1"  );
						phone2   = (String) keyValue.get("phone2"  );
						fax1     = (String) keyValue.get("fax1"    );
						fax2     = (String) keyValue.get("fax2"    );
						address  = (String) keyValue.get("address" );
						city     = (String) keyValue.get("city"    );
						state    = (String) keyValue.get("state"   );
						pin      = (String) keyValue.get("pin"     );
						pan      = (String) keyValue.get("pan"     );
						voterId  = (String) keyValue.get("voterId" );
					} catch (JSONException e) {						
						e.printStackTrace();
					}
					
					/********* Get Session ************/
					User user = (User) session.getAttribute("user"); 
					
					/**************** Database ********************/
					boolean status = false;				
					
					status = CreateUserDAO.setFranchise(userId, password, fName, lName, sex, company, mobile1, mobile2, email1, email2, phone1, phone2,
							fax1, fax2, address, city, state, pin, pan, voterId, new java.sql.Timestamp(new java.util.Date().getTime()),
							user.getUserInfo().getId(), user.getUserInfo().getUserType());
					
					/*********** JSON data for next page ***************/
					JSONObject jsonObject = new JSONObject();;
					
					if (status == true) {  							
						
						try {
							jsonObject.put("message", "* User registration succesful");
						} catch (JSONException e) {							
							e.printStackTrace();
						}
						
					} // if close
					
					else {
						
						try {
							jsonObject.put("message", "* User registration not succesful");
						} catch (JSONException e) {							
							e.printStackTrace();
						}
					}
					
					String jsonData = jsonObject.toString();
					
					/****** Clean Up ****/
					buffer = null; json = null; keyValue = null;
					userId = null; password = null; fName = null; lName = null; sex = null; company = null; mobile1 = null; 
					mobile2 = null; email1 = null; email2 = null; phone1 = null; phone2 = null; fax1 = null; fax2 = null; 
					address = null; city = null; state = null; pin = null; pan = null; voterId = null;
					user = null; jsonObject = null;
					System.gc();
					
					/************* Response **************/
					response.setContentType("application/json");
					response.getWriter().write(jsonData);
				
			} // AddFranchise
			
			else if (servletPath.equals("/RetrievePinCommission")) {
				
				System.out.println("Entered RetrievePinCommission");				
				
				/***************** DataBase ***********************/					
				List<ExtractFranchiseDetails> extractFranchiseDetails = adminDAO.getFranchiseDetails();
				
				/************ Data for next page ******************/
				
				JSONObject items = new JSONObject();
				JSONArray jsonArray = new JSONArray();
				
				if (extractFranchiseDetails != null) {
					
					try {
					
						for (ExtractFranchiseDetails franchiseDetails : extractFranchiseDetails) {
					
								JSONObject jsonObject = new JSONObject();						
						
								jsonObject.put("companyName", franchiseDetails.getUser().getUserInfo().getCompany());							
								jsonObject.put("firstName",   franchiseDetails.getUser().getPerson().getFirstName());
								jsonObject.put("lastName",    franchiseDetails.getUser().getPerson().getLastName() );
								jsonObject.put("id",          franchiseDetails.getUser().getUserInfo().getId()     );
								jsonObject.put("balance",     franchiseDetails.getUser().getUserInfo().getBalance());
								jsonObject.put("pin1",        franchiseDetails.getFranchisePins().getPin1()        );
								jsonObject.put("pin2",        franchiseDetails.getFranchisePins().getPin2()        );
								jsonObject.put("pin3",        franchiseDetails.getFranchisePins().getPin3()        );
								jsonObject.put("pin4",        franchiseDetails.getFranchisePins().getPin4()        );
								jsonObject.put("pin5",        franchiseDetails.getFranchisePins().getPin5()        );																
								
								jsonArray.put(jsonObject);									
						
								jsonObject = null;
						}  // for close
					
						items.put("items", jsonArray);
					
					} catch (JSONException e) {								
						e.printStackTrace();
					}
					
					
					String json = items.toString();
					
					/******* Clean Up *****/
					extractFranchiseDetails = null; items = null; jsonArray = null;
					System.gc();
					
					response.setContentType("application/json");
					response.getWriter().write(json);
					
				} // if close
				
			} // RetrieveProductForApproval
			
			else if (servletPath.equals("/SetPin")) {
				
				System.out.println("Entered SetPin");
				
				String id1         = null;	  
				String pin         = null;   
				String position1   = null;  				  
				
		        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		        
		        String jsonData = null;
		        
		        if (br != null) {
		        	
		            jsonData = br.readLine();                               
		        }
		        
		        JSONObject jsonObject1;
		        
		        try {
		        	
					jsonObject1 = new JSONObject(jsonData);
					
					id1         = jsonObject1.getString("id"      );          
					pin         = jsonObject1.getString("pin"     );        
					position1   = jsonObject1.getString("position");   
					
				} catch (JSONException e) {				
					e.printStackTrace();
				}		        
							
				
				/********* Process ***************/
				
				long id      = Long.parseLong(id1);
				int position = Integer.parseInt(position1);
			
				/***************** DataBase ***********************/				
				boolean status    = adminDAO.setPin(id, pin, position);
				
				/***************  Send Response  *****************/
				
				JSONObject jsonObject = new JSONObject();
				
				if (status == true) {					
					
					try {
						jsonObject.put("success", "* Details Set Successfully");
					} catch (JSONException e) {					
						e.printStackTrace();
					}				
					
				} // if close
				
				else {
					
					try {
						jsonObject.put("failed", "* Details Could Not Set Successfully");
					} catch (JSONException e) {					
						e.printStackTrace();
					}	
				}
				
				String json = jsonObject.toString();
				
				/****** Clean Up ******/
				id1 = null; pin = null; position1 = null; br = null; jsonData = null; jsonObject1 = null; jsonObject = null;
				System.gc();
				
				response.setContentType("application/json");
				response.getWriter().write(json);
				
			} // SetPin
			
			else if (servletPath.equals("/SetCommission")) {
				
				System.out.println("Entered SetCommission");
				
				String commission1 = null;	
				String id1         = null;
				
		        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		        
		        String jsonData = null;
		        
		        if (br != null) {
		        	
		            jsonData = br.readLine();                               
		        }
		        
		        JSONObject jsonObject1;
		        
		        try {
		        	
					jsonObject1 = new JSONObject(jsonData);
					
					commission1 = jsonObject1.getString("commission"); 
					id1         = jsonObject1.getString("id"        );
					
				} catch (JSONException e) {				
					e.printStackTrace();
				}		        
							
				
				/********* Process ***************/
				
				double commission = Double.parseDouble(commission1);  
				long id           = Long.parseLong(id1);          
			
				/***************** DataBase ***********************/				
				boolean status    = adminDAO.setCommission(id, commission);
				
				/***************  Send Response  *****************/
				
				JSONObject jsonObject = new JSONObject();
				
				if (status == true) {					
					
					try {
						jsonObject.put("success", "* Details Set Successfully");
					} catch (JSONException e) {					
						e.printStackTrace();
					}				
					
				} // if close
				
				else {
					
					try {
						jsonObject.put("failed", "* Details Could Not Set Successfully");
					} catch (JSONException e) {					
						e.printStackTrace();
					}	
				}
				
				
				String json = jsonObject.toString();
				
				/***** Clean Up *********/
				commission1 = null; id1 = null; br = null; jsonData = null; jsonObject1 = null; jsonObject = null;
				System.gc();
				
				response.setContentType("application/json");
				response.getWriter().write(json);
				
			} // SetCommission
			
			else if (servletPath.equals("/FranchiseAdditionalBalance")) {
				
					System.out.println("Entered FranchiseAdditionalBalance");
					
					String addtionalBalance1 = null;	
					String id1              = null;
					
			        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			        
			        String jsonData = null;
			        
			        if (br != null) {
			        	
			            jsonData = br.readLine();                               
			        }
			        
			        JSONObject jsonObject1;
			        
			        try {
			        	
						jsonObject1 = new JSONObject(jsonData);
						
						addtionalBalance1 = jsonObject1.getString("addtionalBalance"); 
						id1               = jsonObject1.getString("id"        );
						
					} catch (JSONException e) {				
						e.printStackTrace();
					}		        
								
					
					/********* Process ***************/
					
					double addtionalBalance = Double.parseDouble(addtionalBalance1);  
					long id                 = Long.parseLong(id1);          
				
					/***************** DataBase ***********************/					
					double balance    = adminDAO.setAddtionalBalance(id, addtionalBalance);
					
					/***************  Send Response  *****************/
					
					JSONObject jsonObject = new JSONObject();
					
					if (balance != 0) {					
						
						try {
							jsonObject.put("balance", balance);
							jsonObject.put("success", "* Balance Added Successfully");
						} catch (JSONException e) {					
							e.printStackTrace();
						}				
						
					} // if close
					
					else {
						
						try {
							jsonObject.put("failed", "* Balance Not Added Successfully");
						} catch (JSONException e) {					
							e.printStackTrace();
						}	
					}
					
					
					String json = jsonObject.toString();
					
					/****** Clean Up ******/
					addtionalBalance1 = null; id1 = null; br = null; jsonData = null; jsonObject1 = null; jsonObject = null;
					System.gc();
					
					response.setContentType("application/json");
					response.getWriter().write(json);
					
			} // FranchiseAdditionalBalance
			
			else if (servletPath.equals("/RetrieveOrderedItemsForAdmin")) {
				
				System.out.println("Entered RetrieveOrderedItemsForAdmin");
				
				/************* Database **************/				
				List<OrderTable> orderTables = adminDAO.getOrderTablesForAdmin();			
				
				/********* JSON for Next Page **********/
				
				JSONObject jsonObject = new JSONObject();   
				JSONArray  jsonArray  = new JSONArray();				
				
				try {
				
					for (OrderTable orderTable : orderTables) {
						
						JSONObject orderTableData = new JSONObject();
						
						orderTableData.put("id",             orderTable.getId());
						orderTableData.put("customerId",     orderTable.getCustomerId());
						orderTableData.put("productId",      orderTable.getProductId());
						orderTableData.put("sellerId",       orderTable.getSellerId());
						orderTableData.put("qty",            orderTable.getQty());
						orderTableData.put("sellPrice",      orderTable.getSellPrice());
						orderTableData.put("shippingCost",   orderTable.getShippingCost());
						orderTableData.put("warranty",       orderTable.getWarranty());
						orderTableData.put("orderId",        orderTable.getOrderId());
						orderTableData.put("bookedDateTime", orderTable.getBookedDateTime());
						orderTableData.put("status",         orderTable.getStatus());
						orderTableData.put("size",           orderTable.getSize());
						orderTableData.put("orderState",     orderTable.getOrderState());
						orderTableData.put("paymentType",    orderTable.getPaymentType());
						
						
						JSONObject customerDeliveryAddress = new JSONObject();
						
						customerDeliveryAddress.put("contact",   orderTable.getDeliveryAddress().getContact() );
						customerDeliveryAddress.put("address",   orderTable.getDeliveryAddress().getAddress() );
						customerDeliveryAddress.put("address1",  orderTable.getDeliveryAddress().getAddress1());
						customerDeliveryAddress.put("city",      orderTable.getDeliveryAddress().getCity()    );
						customerDeliveryAddress.put("state",     orderTable.getDeliveryAddress().getState()   );
						customerDeliveryAddress.put("pin",       orderTable.getDeliveryAddress().getPin()     );
						customerDeliveryAddress.put("firstName", orderTable.getDeliveryAddress().getfName()   );
						customerDeliveryAddress.put("lastName",  orderTable.getDeliveryAddress().getlName()   );
						customerDeliveryAddress.put("email",     orderTable.getDeliveryAddress().getEmail()   );
						customerDeliveryAddress.put("company",   orderTable.getDeliveryAddress().getCompany() );
						customerDeliveryAddress.put("country",   orderTable.getDeliveryAddress().getCountry() );
						
						JSONObject sellerData = new JSONObject();
						
						sellerData.put("id",       orderTable.getUser().getUserInfo().getId()      );
						sellerData.put("userType", orderTable.getUser().getUserInfo().getUserType());
						sellerData.put("fName",    orderTable.getUser().getPerson().getFirstName() );
						sellerData.put("lName",    orderTable.getUser().getPerson().getLastName()  );
						sellerData.put("company",  orderTable.getUser().getUserInfo().getCompany() );
						sellerData.put("address",  orderTable.getUser().getAddress().getAddress()  );
						sellerData.put("address1", orderTable.getUser().getAddress().getAddress1() );
						sellerData.put("pin",      orderTable.getUser().getAddress().getPin()      );
						sellerData.put("city",     orderTable.getUser().getAddress().getCity()     );
						sellerData.put("state",    orderTable.getUser().getAddress().getState()    );
						sellerData.put("country",  orderTable.getUser().getAddress().getCountry()  );
						sellerData.put("sex",      orderTable.getUser().getPerson().getSex()       );
						sellerData.put("mobile",   orderTable.getUser().getContact().getMobile1()  );
						sellerData.put("email",    orderTable.getUser().getContact().getEmail1()   );
						sellerData.put("balance",  orderTable.getUser().getUserInfo().getBalance() );
						
						JSONObject orderTableAccessories = new JSONObject();
						
						orderTableAccessories.put("trackNumber",             orderTable.getOrderTableAccessories().getTrackNumber()            );
						orderTableAccessories.put("deliveredDate",           orderTable.getOrderTableAccessories().getDeliveredDate()          );
						orderTableAccessories.put("pickedDate",              orderTable.getOrderTableAccessories().getPickedUpDate()           );
						orderTableAccessories.put("calcellationAfterBooked", orderTable.getOrderTableAccessories().getCancellationAfterBooked());
						orderTableAccessories.put("courier",                 orderTable.getOrderTableAccessories().getCourier()                );
						
						
						/*************************************/
						
						JSONObject jsonObject2 = new JSONObject();						
						
						jsonObject2.put("orderTableData",          orderTableData);
						jsonObject2.put("customerDeliveryAddress", customerDeliveryAddress);
						jsonObject2.put("sellerData",              sellerData);
						jsonObject2.put("orderTableAccessories",   orderTableAccessories);
						
						jsonArray.put(jsonObject2);	
						
						jsonObject2             = null;
						orderTableData          = null;
						customerDeliveryAddress = null;
						sellerData              = null;
						orderTableAccessories   = null;
						
					} // for 
					
					jsonObject.put("items", jsonArray);
				
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String json = jsonObject.toString();    System.out.println(json);
				
				/******** Clean Up **********/
				orderTables = null; jsonObject = null; jsonArray = null; 
				System.gc();
				
				response.setContentType("application/json");
				response.getWriter().write(json);
				
				
			} // /RetrieveOrderedItemsForAdmin
			
			
			else if (servletPath.equals("/RetrieveAllSellerForApprovalRegistration")) {
				
				System.out.println("Entered RetrieveAllSellerForApprovalRegistration");
				
				/************ Database *******************/				
				List<User> userList = adminDAO.getAllSellerForApprovalRegistration();
				
				/********* JSON for Next Page **********/
				
				JSONObject jsonObject = new JSONObject();
				JSONArray  jsonArray  = new JSONArray();
				
				JSONObject jsonObject2 = null;
				
				try {
				
					for (User user : userList) {
						
						jsonObject2 = new JSONObject();						
						
						jsonObject2.put("id",         user.getUserInfo().getId());
						jsonObject2.put("userId",     user.getLogin().getUserId());
						jsonObject2.put("firstName",  user.getPerson().getFirstName());
						jsonObject2.put("lastName",   user.getPerson().getLastName());
						jsonObject2.put("company",    user.getUserInfo().getCompany());
						jsonObject2.put("mobile1",    user.getContact().getMobile1());
						jsonObject2.put("phone1",     user.getContact().getPhone1());
						jsonObject2.put("email1",     user.getContact().getEmail1());													
						
						jsonArray.put(jsonObject2);	
						
						jsonObject2 = null;
						
					} // for 
					
					jsonObject.put("items", jsonArray);
				
				
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				
				String json = jsonObject.toString();
				
				/***** Clean Up ******/
				userList = null; jsonObject = null; jsonArray = null; jsonObject2 = null;
				System.gc();
				
				response.setContentType("application/json");
				response.getWriter().write(json);
				
			} // /RetrieveAllSellerForApprovalRegistration
			
			
			else if (servletPath.equals("/ApproveSellerRegistrationPage")) {
				
				System.out.println("Entered ApproveSellerRegistrationPage");
				
				/***************  Get Request  *****************/
				long id = new Long(request.getParameter("id"));   
				
				/***************  Database  *****************/				
				UserAndPickupAddress userAndPickupAddress = adminDAO.getUserAndPicupAddress(id);
				
				/********** Set Request ****************/
				request.setAttribute("userAndPickupAddress", userAndPickupAddress);
				
				/***** Clean Up ******/
				userAndPickupAddress = null;
				System.gc();
				
				/***************  Next Page  *****************/
				request.getRequestDispatcher("jsp_Administration/ApproveSellerRegistration.jsp").forward(request, response);
			} //ApproveSellerRegistrationPage
			
			
			else if (servletPath.equals("/ApproveSeller")) {
				
				System.out.println("Entered ApproveSeller");
				
				UserAndPickupAddress u = new UserAndPickupAddress();				
				
				// Get Request for User
				u.getUser().getUserInfo().setId       (Integer.parseInt(request.getParameter("id")));
		    	u.getUser().getLogin()   .setUserId   (request.getParameter("userId"        ));		    	
		    	u.getUser().getPerson()  .setFirstName(request.getParameter("firstName"     ));
		    	u.getUser().getPerson()  .setLastName (request.getParameter("lastName"      ));
		    	u.getUser().getUserInfo().setCompany  (request.getParameter("company"       ));
		    	u.getUser().getAddress() .setAddress  (request.getParameter("addressLine1"  ));
		    	u.getUser().getAddress() .setAddress1 (request.getParameter("addressLine2"  ));
		    	u.getUser().getAddress() .setPin      (request.getParameter("pin"           ));
		    	u.getUser().getAddress() .setCity     (request.getParameter("city"          ));
		    	u.getUser().getAddress() .setState    (request.getParameter("state"         ));
		    	u.getUser().getAddress() .setCountry  (request.getParameter("country"       ));
		    	u.getUser().getPerson()  .setSex      (request.getParameter("sex"           ));
		    	u.getUser().getContact() .setMobile1  (request.getParameter("mobile1"       ));
		    	u.getUser().getContact() .setMobile2  (request.getParameter("mobile2"       ));
		    	u.getUser().getContact() .setEmail1   (request.getParameter("email1"        ));
		    	u.getUser().getContact() .setEmail2   (request.getParameter("email2"        ));
		    	u.getUser().getContact() .setPhone1   (request.getParameter("phone1"        ));
		    	u.getUser().getContact() .setPhone2   (request.getParameter("phone2"        ));
		    	u.getUser().getContact() .setFax1     (request.getParameter("fax1"          ));
		    	u.getUser().getContact() .setFax2     (request.getParameter("fax2"          ));
		    	u.getUser().getUserInfo().setPan      (request.getParameter("pan"           ));
		    	u.getUser().getUserInfo().setVoterId  (request.getParameter("voterId"       ));		    	
		    	
		    	
		    	// Get Request for DeliveryAddress
		    	u.getDeliveryAddress().setfName       (request.getParameter("daFirstName"   ));
		    	u.getDeliveryAddress().setlName       (request.getParameter("daLastName"    ));
		    	u.getDeliveryAddress().setCompany     (request.getParameter("daCompany"     ));
		    	u.getDeliveryAddress().setAddress     (request.getParameter("daAddressLine1"));
		    	u.getDeliveryAddress().setAddress1    (request.getParameter("daAddressLine2"));
		    	u.getDeliveryAddress().setCity        (request.getParameter("daCity"        ));
		    	u.getDeliveryAddress().setPin         (request.getParameter("daPin"         ));
		    	u.getDeliveryAddress().setState       (request.getParameter("daState"       ));
		    	u.getDeliveryAddress().setContact     (request.getParameter("daContact"     ));
		    	u.getDeliveryAddress().setEmail       (request.getParameter("daEmail"       ));
		    	u.getDeliveryAddress().setCountry     (request.getParameter("daCountry"     ));
				
				
				/********* Database ***********/		    	
				boolean status = adminDAO.setApproveSeller(u);   
				
				
				/***************  Send Response  *****************/
				
				JSONObject jsonObject = new JSONObject();				
									
					
				try {
					jsonObject.put("status", status);  
					
				} catch (JSONException e) {					
					e.printStackTrace();
				}				
					
				String json = jsonObject.toString();
				
				/****** Clean Up ******/
				u = null; jsonObject = null;
				System.gc();
				
				response.setContentType("application/json");
				response.getWriter().write(json);
				
			
			} //ApproveSeller
			
			else if (servletPath.equals("/OfferedProductsSelection")) {
				
				System.out.println("Entered OfferedProductsSelection");				
				
				/********* Database ***********/
				List<Product> products = productDAO.getProducts();
				
				/********* Set Request **********/
				request.setAttribute("products", products);
				
				/******** Next Page *********/
				request.getRequestDispatcher("jsp_Administration/OfferedHotSelection.jsp").forward(request, response);
				
			} // OfferedProductsSelection
			
			else if (servletPath.equals("/OfferedProductsSelected")) {
				
				System.out.println("Entered OfferedProductsSelected");	
				
				/********** Get Request *********/
				String[] offered = request.getParameterValues("offered");
				String[] hot = request.getParameterValues("hot");
				
				for (int i = 0; i < offered.length; i++) 
					System.out.println("Offered: " + offered[i]);
				
				for (int i = 0; i < hot.length; i++) 
					System.out.println("Hot: " + hot[i]);
				
				/********* Database ***********/
				adminDAO.setOfferedHot(offered, hot);
				List<Product> products = productDAO.getProducts();
				
				/********* Set Request **********/
				request.setAttribute("products", products);
				
				/******** Next Page *********/
				request.getRequestDispatcher("jsp_Administration/OfferedHotSelection.jsp").forward(request, response);
				
			} // OfferedProductsSelected
			
			else if (servletPath.equals("/RetrieveCategoryList")) {
				
				System.out.println("Entered RetrieveCategoryList");	
				
				//Database
				CategoryList categoryList = new CategoryList();
				Map<Integer,String> categories = categoryList.getCategoryList();
				
				categoryList = null;
				
				//Json data for next page
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = null;			
				
				try {
					
					for (Map.Entry<Integer, String> entry : categories.entrySet()) {						
						jsonObject = new JSONObject();
						jsonObject.put("id", entry.getKey());
						jsonObject.put("category", entry.getValue());
						jsonArray.put(jsonObject);
					}
					
				} catch (JSONException e) {					
					e.printStackTrace();					
				}
				
				response.setContentType("application/json");
				response.getWriter().write(jsonArray.toString());
				
				
			} // OfferedProductsSelected
			
			else if (servletPath.equals("/AddCategory")) {
				
				System.out.println("Entered AddCategory");					
				
				//get Request through json------------------------------------------------------------------------------
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			        
		        String jsonData = null;
		        
		        if (br != null) {
		        	
		            jsonData = br.readLine();                               
		        }			       
		        	
				JSONObject jsonObject;
				String category = null; //----------------------request data
				try {
					
					jsonObject = new JSONObject(jsonData);
					category = jsonObject.getString("category").trim().toUpperCase();
					
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				//Database
				int tableId = 0;						
				tableId = CategoryList.addCategory(category);
				CategoryList categoryList = new CategoryList();
				Map<Integer,String> categories = categoryList.getCategoryList();
				
				//Json data for next page
				JSONObject data = new JSONObject();
				JSONArray jsonArray = new JSONArray();
				JSONObject categoryObject = null;
				
				try {
					
					
					//----------------------------------------------------------------------							
					for (Map.Entry<Integer, String> entry : categories.entrySet()) {						
						categoryObject = new JSONObject();
						categoryObject.put("id", entry.getKey());
						categoryObject.put("category", entry.getValue());
						jsonArray.put(categoryObject);
					}					
					//----------------------------------------------------------------------
				
					if (tableId > 0) {
						
							data.put("message", "New Category created with Id: " + tableId);
							data.put("array", jsonArray);
										
					} else {  //tableId == 0
						data.put("message", "New Category not created due to some error!");						
					}
					
				
				} catch (JSONException e) {					
					e.printStackTrace();
				}	
				
				response.setContentType("application/json");
				response.getWriter().write(data.toString());
					
								
				
			} // AddCategory
			
			else if (servletPath.equals("/deleteCategory")) {
				
				System.out.println("Entered deleteCategory");	
				
				//get Request through json------------------------------------------------------------------------------
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			        
		        String jsonData = null;
		        
		        if (br != null) {
		        	
		            jsonData = br.readLine();                               
		        }			       
		        	
				JSONObject jsonObject;
				int id = 0; //----------------------request data
				try {
					
					jsonObject = new JSONObject(jsonData);
					id = Integer.parseInt(jsonObject.getString("id").trim());
					
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				//Database
				int status = 0;  //'0' not executed and '1' executed
				CategoryList categoryList = new CategoryList();
				status = categoryList.deleteCategory(id);
				Map<Integer,String> categories = categoryList.getCategoryList();
				
				//Json data for next page
				JSONObject data = new JSONObject();
				JSONArray jsonArray = new JSONArray();
				JSONObject categoryObject = null;
				
				try {
					
					
					//----------------------------------------------------------------------							
					for (Map.Entry<Integer, String> entry : categories.entrySet()) {						
						categoryObject = new JSONObject();
						categoryObject.put("id", entry.getKey());
						categoryObject.put("category", entry.getValue());
						jsonArray.put(categoryObject);
					}					
					//----------------------------------------------------------------------
				
					if (status == 1) {
						
							data.put("message", "The category is deleted.");
							data.put("array", jsonArray);
										
					} else {  //status == 0
						data.put("message", "The category could not be deleted.");						
					}
					
				
				} catch (JSONException e) {					
					e.printStackTrace();
				}	
				
				response.setContentType("application/json");
				response.getWriter().write(data.toString());
				
			}//deleteCategory
			
			else if (servletPath.equals("/RetrieveSubCategoryList")) {
				
				System.out.println("Entered RetrieveSubCategoryList");
				
				//get Request through json------------------------------------------------------------------------------
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			        
		        String jsonData = null;
		        
		        if (br != null) {
		        	
		            jsonData = br.readLine();                               
		        }			       
		        	
				JSONObject jsonObject;
				int categoryId = 0; //----------------------request data
				try {
					
					jsonObject = new JSONObject(jsonData);
					categoryId = Integer.parseInt(jsonObject.getString("categoryId").trim()); System.out.println(categoryId);
					
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				//Database
				SubCategory subCategory = new SubCategory();
				List<SubCategory> subCategories = subCategory.getSubCategorys(categoryId);
				
				
				//Json data for next page				
				JSONArray jsonArray = new JSONArray();
				JSONObject subCategoryObject = null;
				
				try {
					
					
					//----------------------------------------------------------------------							
					for (SubCategory subCategory2 : subCategories) {						
						subCategoryObject = new JSONObject();
						subCategoryObject.put("id", subCategory2.getId());
						subCategoryObject.put("subSategory", subCategory2.getSubCategory());
						jsonArray.put(subCategoryObject);
					}					
					//----------------------------------------------------------------------
				
					
					
				
				} catch (JSONException e) {					
					e.printStackTrace();
				}	
				
				response.setContentType("application/json");
				response.getWriter().write(jsonArray.toString());
				
			}//RetrieveSubCategoryList
			
			else if (servletPath.equals("/deleteASubCategory")) {
				
				System.out.println("Entered deleteASubCategory");
				
				//get Request through json------------------------------------------------------------------------------
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			        
		        String jsonData = null;
		        
		        if (br != null) {
		        	
		            jsonData = br.readLine();                               
		        }			       
		        	
				JSONObject jsonObject;
				int categoryId = 0; 
				int subCategoryId = 0;
				try {
					//get Request
					jsonObject = new JSONObject(jsonData);
					categoryId = Integer.parseInt(jsonObject.getString("categoryId").trim()); System.out.println(categoryId);
					subCategoryId = Integer.parseInt(jsonObject.getString("subCategoryId").trim()); System.out.println(categoryId);
					
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				//Database
				int status = 0;
				List<SubCategory> subCategories = null;
				SubCategory subCategory = new SubCategory();
				status = subCategory.deleteASubCategory(subCategoryId);
				if (status > 0)
					subCategories = subCategory.getSubCategorys(categoryId);
				
				
				//Json data for next page				
				JSONArray jsonArray = new JSONArray();
				JSONObject subCategoryObject = null;
				
				try {
					
					
					//----------------------------------------------------------------------							
					for (SubCategory subCategory2 : subCategories) {						
						subCategoryObject = new JSONObject();
						subCategoryObject.put("id", subCategory2.getId());
						subCategoryObject.put("subSategory", subCategory2.getSubCategory());
						jsonArray.put(subCategoryObject);
					}					
					//----------------------------------------------------------------------
				
					
					
				
				} catch (JSONException e) {					
					e.printStackTrace();
				}	
				
				response.setContentType("application/json");
				response.getWriter().write(jsonArray.toString());	
				
				
			}//deleteASubCategory
			
			
			else if (servletPath.equals("/addASubCategory")) {
				
				System.out.println("Entered addASubCategory");
				
				//get Request through json------------------------------------------------------------------------------
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			        
		        String jsonData = null;
		        
		        if (br != null) {
		        	
		            jsonData = br.readLine();                               
		        }			       
		        	
				JSONObject jsonObject;
				int categoryId = 0; 
				String subCategory = null;
				try {
					//get Request
					jsonObject = new JSONObject(jsonData);
					categoryId = Integer.parseInt(jsonObject.getString("categoryId").trim()); System.out.println(categoryId);
					subCategory = jsonObject.getString("subCategory").trim(); System.out.println(subCategory);
					
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				//Database
				int tableId = 0;
				tableId = SubCategory.addASubCategory(categoryId, subCategory);
				
				//Json data for next page				
				JSONObject jsonObject2 = new JSONObject();
				
				try {
					
					jsonObject2.put("response", "The SubCategory Added at id " + tableId);				
				
				} catch (JSONException e) {					
					e.printStackTrace();
				}	
				
				response.setContentType("application/json");
				response.getWriter().write(jsonObject2.toString());
				
			}//addASubCategory
			
	}
}
