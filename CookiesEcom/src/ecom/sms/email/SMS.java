package ecom.sms.email;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMS {
	
	final static String baseUrl = "http://103.16.101.52:8080/bulksms/bulksms?";

	@SuppressWarnings("deprecation")
	public static void sendSMS(String mobile, String message, String senderId, String smsUserId, String smsPassword) {
		
		try {	
	        
	        System.out.println(baseUrl);
	        URL url = new URL(baseUrl);
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
	
	        String content = "username=" + URLEncoder.encode(smsUserId) + "&password=" 
	        		+ URLEncoder.encode(smsPassword)+ "&type=0"+ "&dlr=1" + "&destination="+mobile
	        		+  "&source="+senderId + "&message=" + URLEncoder.encode (message);
	        
	        System.out.println(content);
	        
	        writer.writeBytes(content);
	        
	        writer.flush();
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        if(reader.ready()) {
	             String line;
	             
	             while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	             }
	        }
	        
	        reader.close();
	        writer.close();
	    }
		
	    catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}

	public static void main(String[] args) {  
		
	    try {
	    	
	    	String sms = "Dear shab your present balance is 1000"; //160 letters s
	    	
		 	SMS.sendSMS("0000000000", sms, "RECHRG","ysk-yaduvanshi","000000");
		 	
	    } catch (Exception ex) {
	        Logger.getLogger(SMS.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
}


/*
ysk-suraksha
senderId = SUACPL
password = 000000
*/

