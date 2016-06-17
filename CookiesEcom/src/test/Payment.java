package test;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

class Payment {
	
    public static void main(String[] args) throws Exception {
    	
        URL url = new URL("http://example.net/new-message.php");
        
        Map<String,Object> params = new LinkedHashMap<>();
        
        params.put("name", "Freddie the Fish");
        params.put("email", "fishie@seamail.example.com");
        params.put("reply_to_thread", 10394);
        params.put("message", "Shark attacks in Botany Bay have gotten out of control. "
        		+ "We need more defensive dolphins to protect the schools here, but Mayor Porpoise is too "
        		+ "busy stuffing his snout with lobsters. He's so shellfish.");

        StringBuilder postData = new StringBuilder();
        
        for (Map.Entry<String,Object> param : params.entrySet()) {
        	
            if (postData.length() != 0) postData.append('&');
            
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);        
        
        
        // Return

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        
        for ( int c = in.read(); c != -1; c = in.read() )
        	
            System.out.print((char)c);
    }
    
    
    public static Map<String, List<String>> splitQuery(URL url) throws UnsupportedEncodingException {
    	
    	  final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
    	  
    	  final String[] pairs = url.getQuery().split("&");
    	  
    	  for (String pair : pairs) {
    		  
    	    final int idx = pair.indexOf("=");
    	    
    	    final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
    	    
    	    if (!query_pairs.containsKey(key)) {
    	    	
    	      query_pairs.put(key, new LinkedList<String>());
    	    }
    	    
    	    final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
    	    
    	    query_pairs.get(key).add(value);
    	  }
    	  
    	  return query_pairs;
    }
    
    public boolean isInQuery(HttpServletRequest request, String key) {
    	
    	  String query = request.getQueryString();   	 
    	  
    	  String[] nameValuePairs = query.split("&");
    	  
    	  for(String nameValuePair: nameValuePairs) {
    		  
    	    if(nameValuePair.startsWith(key + "=")) {
    	    	
    	      return true;
    	    }
    	  }
    	  
    	  return false;
    }
		    
}
