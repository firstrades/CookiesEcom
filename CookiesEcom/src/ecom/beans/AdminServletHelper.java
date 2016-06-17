package ecom.beans;

import java.text.SimpleDateFormat; 
import java.util.Calendar;

public class AdminServletHelper {

	public String getDateIncremented(String date) {  
		
		String formatted = null;
		
    	if (date != null) {
	    	String[] dateParts = date.split("-");
			Calendar deliveredDate = Calendar.getInstance();
			deliveredDate.set(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1])-1, Integer.parseInt(dateParts[2]));
			deliveredDate.add(Calendar.DATE, 15);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			formatted = format1.format(deliveredDate.getTime());  
    	}
    	
		return formatted;
	}
}
