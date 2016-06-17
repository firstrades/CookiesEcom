package ecom.SERVLET.seller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductCategoryList")
public class ProductCategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("ProductCategoryList Destroyed"); 
	};
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		System.out.println("Entered ProductCategoryList");
		
		String value = request.getParameter("value");
		
		String options = getOptionsList(value);		
		
		response.getWriter().write(options);
	}
	
	public String getOptionsList(String value) {
		
		String options = null;
		
		if (value.equals("null")) {
			
			options = "<option value=\"null\">--sub category--</option>";
					
		}
		else if (value.equals("ELECTRONICS")) {
			
			options = "<option value=\"Mobile\">Mobile</option>"					
					+"<option value=\"Laptop\">Laptop</option>"
					+"<option value=\"Tablet\">Tablet</option>"
					+"<option value=\"Camera\">Camera</option>"
					+"<option value=\"Television\">Television</option>"
					+"<option value=\"AirCondition\">Air Condition</option>"
					+"<option value=\"Refrigerator\">Refrigerator</option>"
					+"<option value=\"WashingMachine\">Washing Machine</option>"
					+"<option value=\"MicrowaveOven\">Microwave Oven</option>"
					+"<option value=\"VacuumCleaner\">Vacuum Cleaner</option>"
					+"<option value=\"Speaker\">Speaker</option>"
					+"<option value=\"Geyser\">Geyser</option>"
					+"";
		}	
		else if (value.equals("MEN")) {
			
			options = "<option value=\"MenTshirt\">Tshirt</option>"					
					+"<option value=\"MenShirt\">Shirt</option>"
					+"<option value=\"MenKurta\">Kurta</option>"
					+"<option value=\"MenSherwani\">Sherwani</option>"
					+"<option value=\"Jeans\">Jeans</option>"
					+"<option value=\"MenTrouser\">Trouser</option>"
					+"<option value=\"MenShoes\">Shoes</option>"
					+"<option value=\"MenWatch\">Watch</option>"
					+"<option value=\"MenWallet\">Wallet</option>"
					+"<option value=\"MenBelt\">Belt</option>"
					+"<option value=\"MenSunglasses\">Sunglasses</option>"
					+"<option value=\"MenDeodrants\">Deodrants</option>"
					+"<option value=\"MenPerfumes\">Perfumes</option>"
					+"";
		}
		else if (value.equals("WOMEN")) {
			
			options = "<option value=\"WomenShoe\">Shoes</option>"
					+"<option value=\"WomenKurta\">Kurta</option>"
					+"<option value=\"WomenSharee\">Sharee</option>"
					+"<option value=\"WomenSalwar\">Salwar</option>"
					+"<option value=\"WomenJeans\">Jeans</option>"
					+"<option value=\"WomenLeggings\">Leggings</option>"
					//+"<option value=\"Sunglasses\">Sunglasses</option>"
					+"";
		}
		else if (value.equals("KIDS")) {
			
			options = "<option value=\"Boys_Shirt\">Boy's Shirt</option>"
					+"<option value=\"Boys_Pant\">Boy's Pant</option>"
					+"<option value=\"Girls_Top\">Girl's Top</option>"
					+"<option value=\"Girls_Shorts\">Girl's Shorts</option>"
					+"<option value=\"Baby_Diapers\">Diapers</option>"
					+"";
		}		
		else if (value.equals("HomeAndKitchen")) {
			
			options = "<option value=\"Bedsheets\">Bedsheets</option>"
					+"<option value=\"Curtains\">Curtains</option>"		
					+"<option value=\"SofaCovers\">Sofa Covers</option>"	
					+"<option value=\"PressureCookers\">Pressure Cookers</option>"	
					+"<option value=\"GasStoves\">Gas Stoves</option>"	
					+"";
		}
		else if (value.equals("FoodAndGrocery")) {
			
			options = "<option value=\"Confectionery\">Confectionery</option>"
					+"<option value=\"PowderProduct\">Powder Product</option>"		
					+"<option value=\"Cakes\">Cakes</option>"	
					+"<option value=\"Dairy\">Dairy</option>"
					+"<option value=\"Rice\">Rice</option>"
					+"";
		}
		else if (value.equals("Herbal")) {
			
			options = "<option value=\"Sampoo\">Sampoo</option>"
					+"<option value=\"Medicine\">Medicine</option>"		
					+"<option value=\"FatAnalyzer\">Fat Analyzer</option>"	
					+"<option value=\"Soap\">Soap</option>"
					+"<option value=\"Moisturizer\">Moisturizer</option>"
					+"";
		}
		
		return options;
	}
}
