package ecom.SERVLET.administration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ecom.DAO.User.CreateUserDAO;
import ecom.DAO.administration.FranchiseDAO;
import ecom.model.ExtractDistributorDetails;
import ecom.model.FranchisePins;
import ecom.model.User;

public class FranchiseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FranchiseDAO franchiseDAO;
	
	@Override
	public void init() {
		franchiseDAO = FranchiseDAO.getInstance();
	}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("FranchiseServlet Destroyed"); 
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
		
		
		if (servletPath.equals("/DistributorRegistration")) {
			
				System.out.println("Entered DistributorRegistration");
				
				/*************** Get Session ***************/
				
				User user = (User) session.getAttribute("user");
				
				/************** Get Pins ***********/
				String pins = franchiseDAO.getPins(user.getUserInfo().getId());
				
				/******************* Set Request *************/
				
				String title          = "Registration For Distributor";
				String submitFunction = "createDistributor()";
				
				request.setAttribute("title",          title);
				request.setAttribute("submitFunction", submitFunction);
				request.setAttribute("pins",           pins);
				
				/********** Next Page *************/
				
				request.getRequestDispatcher("jsp_Administration/DistributorRegistration.jsp").forward(request, response);
		}  // DistributorRegistration
		
		else if (servletPath.equals("/AddDistributor")) {
			
				System.out.println("Entered AddDistributor");
				
				/*********** Get Request ***************/
				
				BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
				
				String json = buffer.readLine();
				
				JSONObject keyValue;
				String userId = null, password = null, fName = null, lName = null, sex = null, company = null, mobile1 = null, mobile2 = null, 
						email1 = null, email2 = null, phone1 = null, phone2 = null, fax1 = null, fax2 = null, address = null, city = null, 
						state = null, pin = null, pan = null, voterId = null, choosePin = null, chooseArea = null;
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
					
					choosePin  = (String) keyValue.get("choosePin");  
					chooseArea = (String) keyValue.get("area"     ); 
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				/********* Get Session ************/
				User user = (User) session.getAttribute("user"); 
				
				/**************** Database ********************/
				boolean status = false;				
				
				status = CreateUserDAO.setDistributor(userId, password, fName, lName, sex, company, mobile1, mobile2, email1, email2, phone1, phone2,
						fax1, fax2, address, city, state, pin, pan, voterId, new java.sql.Timestamp(new java.util.Date().getTime()),
						user.getUserInfo().getId(), user.getUserInfo().getUserType(), choosePin, chooseArea);
				
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
				
				
				/************* Response **************/
				response.setContentType("application/json");
				response.getWriter().write(jsonObject.toString());
				
		}  // AddDistributor
		
		else if (servletPath.equals("/RetrievePinAreaCommission")) {
			
				System.out.println("Entered RetrievePinAreaCommission");
			
				/************* Get Request ****************/
				BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
				
				String json = buffer.readLine();
				
				JSONObject keyValue;
				int user_id = 0;
				try {
					keyValue = new JSONObject(json);
					
					user_id   = (int) keyValue.get("user_id");   
					 
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				
				/************** Database ******************/	
				
				FranchisePins franchisePins = franchiseDAO.getPins1(user_id);
				
				List<ExtractDistributorDetails> extractDistributorDetails = franchiseDAO.getPinAreaCommission(user_id);				
				
				/********** JSON for next page ***********/
				
				JSONObject outer = new JSONObject();
				JSONArray  array = new JSONArray();				
				
				for (ExtractDistributorDetails details : extractDistributorDetails) {
					
					JSONObject inner = new JSONObject();
					
					try {
						
						inner.put("id",         details.getUser().getUserInfo().getId()     );
						inner.put("company",    details.getUser().getUserInfo().getCompany());
						inner.put("fName",      details.getUser().getPerson().getFirstName());
						inner.put("lName",      details.getUser().getPerson().getLastName() );
						inner.put("balance",    details.getUser().getUserInfo().getBalance());
						
						inner.put("pin",        details.getPinAreaMap().getPin()            );
						inner.put("area",       details.getPinAreaMap().getArea()           );
						
						inner.put("commission", details.getCommission().getFranchiseCommission());
						
						array.put(inner);
						
					} catch (JSONException e) {						
						e.printStackTrace();
					}
				} // for
				
				
				try {
					
					outer.put("pin1", franchisePins.getPin1());
					outer.put("pin2", franchisePins.getPin2());
					outer.put("pin3", franchisePins.getPin3());
					outer.put("pin4", franchisePins.getPin4());
					outer.put("pin5", franchisePins.getPin5());
					
					outer.put("items", array);
					
				} catch (JSONException e) {					
					e.printStackTrace();
				}
				
				response.setContentType("application/json");
				response.getWriter().write(outer.toString());
			
		} // RetrievePinAreaCommission
		
		else if (servletPath.equals("/SavePin")) {
			
				System.out.println("Entered SavePin");
				
				/************* Get Request ****************/
				BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
				
				String json = buffer.readLine();
				
				JSONObject keyValue;
				int    d_id = 0;
				String pin = null;
				try {
					keyValue = new JSONObject(json);
					
					d_id = (int)    keyValue.get("d_id");       System.out.println(d_id);
					pin  = (String) keyValue.getString("pin");  System.out.println(pin);
					 
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				/************** Database ******************/				
				String pin1 = franchiseDAO.setPin(d_id, pin);  
				
				/********* Set Json Data ****************/
				
				JSONObject jsonObject = new JSONObject();;
				
				if (pin1 != null) {  							
					
					try {
						jsonObject.put("updatedPin", pin1);
						jsonObject.put("message", "* Update Pin Succesful");
					} catch (JSONException e) {							
						e.printStackTrace();
					}
					
				} // if close
				
				else {
					
					try {
						jsonObject.put("message", "* Update Pin Not Succesful");
					} catch (JSONException e) {							
						e.printStackTrace();
					}
				}
				
				
				/************* Response **************/
				response.setContentType("application/json");
				response.getWriter().write(jsonObject.toString());
				
		} // SavePin
		
		else if (servletPath.equals("/SaveArea")) {
			
				System.out.println("Entered SaveArea");
			
				/************* Get Request ****************/
				BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
				
				String json = buffer.readLine();
				
				JSONObject keyValue;
				int    d_id = 0;
				String area = null;
				try {
					keyValue = new JSONObject(json);
					
					d_id = (int)     keyValue.get("d_id");          System.out.println(d_id);
					area  = (String) keyValue.getString("area");    System.out.println(area);  
					 
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				/************** Database ******************/
				String area1 = franchiseDAO.setArea(d_id, area);  
				
				/********* Set Json Data ****************/
				
				JSONObject jsonObject = new JSONObject();;
				
				if (area1 != null) {  							
					
					try {
						jsonObject.put("updatedArea", area1);
						jsonObject.put("message", "* Update Area Succesful");
					} catch (JSONException e) {							
						e.printStackTrace();
					}
					
				} // if close
				
				else {
					
					try {
						jsonObject.put("message", "* Update Area Not Succesful");
					} catch (JSONException e) {							
						e.printStackTrace();
					}
				}
				
				
				/************* Response **************/
				response.setContentType("application/json");
				response.getWriter().write(jsonObject.toString());
				
				
		} // SaveArea
		
		else if (servletPath.equals("/SaveCommission")) {
			
				System.out.println("Entered SaveCommission");
			
				/************* Get Request ****************/
				BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
				
				String json = buffer.readLine();
				
				JSONObject keyValue;
				int    d_id       = 0;
				double commission = 0.0;
				try {
					keyValue = new JSONObject(json);
					
					d_id       = (int) keyValue.get("d_id");                            System.out.println(d_id);
					commission = Double.parseDouble(keyValue.getString("commission"));  System.out.println(commission);   
					 
				} catch (JSONException e) {						
					e.printStackTrace();
				}
				
				/************** Database ******************/
				
				double commission1 = franchiseDAO.setCommission(d_id, commission);  
				
				/********* Set Json Data ****************/
				
				JSONObject jsonObject = new JSONObject();;
				
				if (commission1 != 0) {  							
					
					try {
						jsonObject.put("updatedCommission", commission1);
						jsonObject.put("message", "* Update Area Succesful");
					} catch (JSONException e) {							
						e.printStackTrace();
					}
					
				} // if close
				
				else {
					
					try {
						jsonObject.put("message", "* Update Area Not Succesful");
					} catch (JSONException e) {							
						e.printStackTrace();
					}
				}
				
				
				/************* Response **************/
				response.setContentType("application/json");
				response.getWriter().write(jsonObject.toString());
				
		} // SaveCommission
		
		else if (servletPath.equals("/DistributorAdditionalBalance")) {
			
			System.out.println("Entered DistributorAdditionalBalance");
			
			String addtionalBalance1 = null;	
			String id1               = null;
			
	        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        
	        String jsonData = null;
	        
	        if (br != null) {
	        	
	            jsonData = br.readLine();                               
	        }
	        
	        try {
	        	
				JSONObject jsonObject1 = new JSONObject(jsonData);
				
				addtionalBalance1 = jsonObject1.getString("addtionalBalance"); 
				id1               = jsonObject1.getString("id"        );
				
			} catch (JSONException e) {				
				e.printStackTrace();
			}		        
						
			
			/********* Process ***************/
			
			double addtionalBalance = Double.parseDouble(addtionalBalance1);  
			long id                 = Long.parseLong(id1);          
		
			/***************** DataBase ***********************/				
			double balance = franchiseDAO.setAddtionalBalance(id, addtionalBalance);
			
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
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		} // DistributorAdditionalBalance
		
	}

}
