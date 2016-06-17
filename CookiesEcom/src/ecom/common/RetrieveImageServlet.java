package ecom.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RetrieveImageServlet")
public class RetrieveImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servletPath = request.getServletPath();
				
		if (servletPath.equals("/IconImageFromProduct")) {
			
			System.out.println("Entered ImageFromProduct");
			
			/*******************************************
			 			* Get Request *
			 *******************************************/
			
			String productId = request.getParameter("productId");
			
			/*******************************************
			 			* Retrieve Image *
			 *******************************************/
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet result = null;
			String query = null;
			InputStream inputStream;		
			
			
			try {
				
				connection = ConnectionFactoryImage.getNewConnection();
				connection.setAutoCommit(false);
				query = "SELECT iconImage FROM images WHERE product_id= ?";				
				preparedStatement = connection.prepareStatement(query);					
				preparedStatement.setObject(1, productId);			
				
				result = preparedStatement.executeQuery();
				
				if (result.next()) {
					
					byte[] imagebytes = new byte[1048576];
					inputStream = result.getBinaryStream("iconImage");
					
					int size = 0;
					response.setContentType("image/jpeg");
					
					try {
						while ((size = inputStream.read(imagebytes)) != -1) {
							response.getOutputStream().write(imagebytes, 0, size);
						}
					} catch (IOException e) {}
					
				}
				
				System.out.println("SQL Statement Executed Fetch Icon Image");
				
								
				connection.commit();
				
				
				
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {			
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
			}
			finally {
				
				try {
					result.close();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}				
				try {
					preparedStatement.close();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}				
				try {
					connection.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			
		}
		
		else if (servletPath.equals("/Image1FromProduct")) {
			
			System.out.println("Entered Image1FromProduct");
			
			/*******************************************
			 			* Get Request *
			 *******************************************/
			
			String productId = request.getParameter("productId");
			
			/*******************************************
			 			* Retrieve Image *
			 *******************************************/
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet result = null;
			String query = null;
			InputStream inputStream;
			
			
			try {
				
				connection = ConnectionFactoryImage.getNewConnection();
				connection.setAutoCommit(false);
				query = "SELECT Image1 FROM images WHERE product_id= ?";				
				preparedStatement = connection.prepareStatement(query);					
				preparedStatement.setObject(1, productId);			
				
				result = preparedStatement.executeQuery();
				
				if (result.next()) {
					
					byte[] imagebytes = new byte[1048576];
					inputStream = result.getBinaryStream("Image1");
					
					int size = 0;
					response.setContentType("image/jpeg");
					
					try {
						while ((size = inputStream.read(imagebytes)) != -1) {
							response.getOutputStream().write(imagebytes, 0, size);
						}
					} catch (IOException e) {}
					
				}
				
				System.out.println("SQL Statement Executed Fetch Image 1");
				
								
				connection.commit();
				
				
				
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {			
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
			}
			finally {
				
				try {
					result.close();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}				
				try {
					preparedStatement.close();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}				
				try {
					connection.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			
		}

		else if (servletPath.equals("/Image2FromProduct")) {
		
			System.out.println("Entered Image2FromProduct");
			
			/*******************************************
			 			* Get Request *
			 *******************************************/
			
			String productId = request.getParameter("productId");
			
			/*******************************************
			 			* Retrieve Image *
			 *******************************************/
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet result = null;
			String query = null;
			InputStream inputStream;
			
			
			try {
				
				connection = ConnectionFactoryImage.getNewConnection();
				connection.setAutoCommit(false);
				query = "SELECT Image2 FROM images WHERE product_id= ?";				
				preparedStatement = connection.prepareStatement(query);					
				preparedStatement.setObject(1, productId);			
				
				result = preparedStatement.executeQuery();
				
				if (result.next()) {
					
					byte[] imagebytes = new byte[1048576];
					inputStream = result.getBinaryStream("Image2");
					
					int size = 0;
					response.setContentType("image/jpeg");
					
					try {
						while ((size = inputStream.read(imagebytes)) != -1) {
							response.getOutputStream().write(imagebytes, 0, size);
						}
					} catch (IOException e) {}
					
				}
				
				System.out.println("SQL Statement Executed Fetch Image 2");
				
								
				connection.commit();
				
				
				
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {			
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
			}
			finally {
				
				try {
					result.close();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}				
				try {
					preparedStatement.close();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}				
				try {
					connection.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			
		}
	}

}
