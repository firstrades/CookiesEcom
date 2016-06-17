package ecom.SERVLET.ErrorPages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorPagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("ErrorPagesServlet Destroyed"); 
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
		
		System.out.println("Entered ErrorPagesServlet");
		String servletPath = request.getServletPath();
		
		if (servletPath.equals("/ConnectionError")) {
			
			System.out.println("Entered ConnectionError");
			
			request.getRequestDispatcher("ErrorPages/ConnectionError.jsp").forward(request, response);
		}
	}

}
