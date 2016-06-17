package ecom.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecom.model.Product;

public class Handler {

	private static Handler handler;
	
	private Handler() {
		handler = null;
	}
	
	public static Handler getInstance() {
		
		if (handler == null) {			
			synchronized (Handler.class) {				
				if (handler == null)
					handler = new Handler();
			}			
		}
		
		return handler;
	}
	
	public String[] getTwoKeys(String keyWords) {
		
		String[] twoKeys = null;
		
		String[] temp = keyWords.split(" in "); 
		String[] otherPart = temp[1].trim().split(" ");    System.out.println("otherPart.length: "+otherPart.length);
		
		
		if (temp.length == 2 && otherPart.length == 1)	{
			twoKeys = new String[] { temp[0].trim(), temp[1].trim() };	
			System.out.println(temp[0] + " " + temp[1]);
		}
		
		System.out.println("twoKeys: " + twoKeys);
		
		return twoKeys;
	}
	
	public String[] getThreeKeys(String keyWords) {
		
		String[] threeKeys = null;
		
		String[] temp = keyWords.split(" in ");
		
		String[] temp1 = temp[1].trim().split(" ");     System.out.println("temp1.length: "+temp1.length);
		
		String[] otherPart = null;
		if (temp1.length > 1) {
			otherPart = temp[1].trim().split(" of ");
			System.out.println("otherPart.length: "+otherPart.length);
		}
		
		if (temp.length == 2 && temp1.length > 1)  {
			threeKeys = new String[] { temp[0].trim(), otherPart[0].trim(), otherPart[1].trim() };		
			System.out.println(temp[0] + " " + otherPart[0] + " " + otherPart[1]);
		}
		
		System.out.println("threeKeys: " + threeKeys);
		
		return threeKeys;
	}
	
	public void searchBySubCategory_BuyerServlet(List<Product> productBeanList1, HttpSession session, String errorMsg,
			HttpServletRequest request, Integer MAX, HttpServletResponse response) throws ServletException, IOException {
		
		List<Product> productBeanList = new ArrayList<>();
		
		if (productBeanList1.size() < MAX)    // set MAX
			MAX = productBeanList1.size();
		
		for (int i = 0; i < MAX; i++) {
			
			Product productBean = new Product();				
			
			productBean = productBeanList1.get(i);	System.out.println(i);	//Make a list of MAX data out of total list		
			productBeanList.add(productBean);
		}
		
		//int productBeanList1_Size = productBeanList1.size();
		
		
		//Set Session 		 		
		session.setAttribute("productBeanList1", productBeanList1);			
		
		
		//Set Request 		 
		if (errorMsg != null)
			request.setAttribute("errorMsg", errorMsg);
		
		request.setAttribute("productBeanList", productBeanList);
		request.setAttribute("MAX", MAX);
		
		//Next Page
		request.getRequestDispatcher("jsp_Buyer/BuyerSearchPage.jsp").forward(request, response);
	}
	
	
	public String searchBySubCategory(String subCategory, String[] stringArray, String search) {
		
		String sql = null;
		
		if (subCategory != null && stringArray == null && search == null)
			sql = "SELECT * FROM product WHERE sub_category = '"+subCategory+"' AND status = 'approved'";
		else if (stringArray != null && stringArray.length == 2) {
			sql = "SELECT * FROM product WHERE sub_category = '"+stringArray[0]+"' AND category = '"+stringArray[1]+"' AND status = 'approved'";
		}
		else if (stringArray != null && stringArray.length == 3 && search == null) {
			sql = "SELECT * FROM product WHERE company_name = '"+stringArray[0]
					+"' AND sub_category = '"+stringArray[1]+"' AND category = '"+stringArray[2]+"' AND status = 'approved'";	
		}
		else {
			//if (stringArray != null && stringArray.length == 3 && search.equals(" "))
			//MySql View
			sql = "SELECT * FROM searchbyproductname " + 
				  "WHERE searchKeyWord_forProduct = '"+stringArray[0]+"' AND sub_category = '"+stringArray[1]+"' AND category = '"+stringArray[2]+"'" +
				  "AND status = 'approved'";
		}
		
		System.out.println(sql);
		
		return sql;
	}
	
	
	public String getRemoteIpAddress(HttpServletRequest request) {
		
		String ipAddress = request.getRemoteAddr();	
		
		return ipAddress;
	}
	
}
