package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class Time extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger log = Logger.getLogger(Time.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			System.out.println(System.currentTimeMillis());
			
			for (long i = 0; i < 10000000000L; i++);
			
			throw new Exception();
			
		} catch (Exception e) {
			
			log.error("This message printed." + System.currentTimeMillis());
			//System.out.println(System.currentTimeMillis());
		}
	}

}
