package ecom.SERVLET.buyer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import ecom.DAO.Buyer.BuyerSearchDAO;
import ecom.DAO.Buyer.SearchDAO;
import ecom.beans.Handler;
import ecom.common.FrequentUse;
import ecom.model.Product;

@MultipartConfig
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SearchDAO searchDAO;
	private Handler handler;
    
	@Override
	public void init() throws ServletException {
		this.searchDAO = SearchDAO.getInstance();
		this.handler = Handler.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entered SearchServlet");
		
		String servletPath = request.getServletPath();
		HttpSession session = request.getSession();
		
		if (servletPath.equals("/searchByKeyWord")) {
			
			System.out.println("Entered searchByKeyWord");
			
			//getRequest
			String keyWord = request.getParameter("keyWord");
			
			//Database
			Set<String> keyWordSet = new TreeSet<String>();
			searchDAO.searchKeyWordsFromCompanyName(keyWord, keyWordSet);
			searchDAO.searchKeyWordsFromSubCategory(keyWord, keyWordSet);
			searchDAO.searchKeyWords(keyWord, keyWordSet);
			
			//Json for next page
			
			JSONArray jsonArray = new JSONArray();
			
			for (String key : keyWordSet) {
				jsonArray.put(key);
			}
			
			response.setContentType("application/json");
			response.getWriter().write(jsonArray.toString());
			
		}//searchByKeyWord
		
		else if (servletPath.equals("/searchProductsWithKeyWords")) {
			
			System.out.println("Entered searchProductsWithKeyWords");			
			
			//getRequest
			String keyWords = request.getParameter("keyWords");
			
			//Database
			String[] twoKeys = null;
			String[] threeKeys = null;
			
			twoKeys = handler.getTwoKeys(keyWords);   		
			threeKeys = handler.getThreeKeys(keyWords);
			
			BuyerSearchDAO buyerSearchDAO = BuyerSearchDAO.getInstance();
			List<Product> productBeanList1 = null;
			List<Product> productBeans = null;
			
			if (twoKeys != null) { System.out.println("Enter 2k");
				productBeanList1 = buyerSearchDAO.searchBySubCategory(twoKeys, null);
				
			} else {  System.out.println("Enter 3k");
				productBeanList1 = buyerSearchDAO.searchBySubCategory(threeKeys, null);
				productBeans = buyerSearchDAO.searchBySubCategory(threeKeys, "productName"/*no use*/);
				
				if (productBeanList1 != null && productBeans != null) {
					productBeanList1.addAll(productBeans);
				}
				
				if (productBeanList1 == null && productBeans != null) {
					productBeanList1 = new ArrayList<Product>();
					productBeanList1.addAll(productBeans);
				} 
			}
			
			handler.searchBySubCategory_BuyerServlet(productBeanList1, session, null, request, FrequentUse.MAX, response);
			
			
			/*//------------------------------------------
			PrintWriter out = response.getWriter();
			if (twoKeys != null) {
				out.println(twoKeys[0] + " " + twoKeys[1]);
			} else {
				out.println(threeKeys[0] + " " + threeKeys[1] + " " + threeKeys[2]);
			}*/
		}//searchProductsWithKeyWords
	}

}

//http://localhost:8080/EcomImage/searchByKeyWord?keyWord=mob
