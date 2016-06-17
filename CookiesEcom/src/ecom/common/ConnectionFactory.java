package ecom.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {	

	private final String PROTOCOL        = "jdbc:mysql://localhost:3306/";   
	//private final String PROTOCOL        = "jdbc:mysql://104.254.98.163:3306/";
	
	private final String RemoteDATABASE  = "firstrad_shopingdb?noAccessToProcedureBodies=true";
	private final String RemoteUSER      = "firstrad_shoroot";
	private final String RemotePASSWORD  = "QOWEUF%T^c[)";
	
	private final String LocalDATABASE   = "cookie"+ "?noAccessToProcedureBodies=true";
	private final String LocalUSER       = "root";
	private final String LocalPASSWORD   = "";
	
	public static Connection getNewConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		return new ConnectionFactory().createConnection();
	}
	
	private Connection createConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {	
		
			Class.forName("com.mysql.jdbc.Driver");	
			
			Connection connection = null;
			
			try {
					System.out.println("Remote");
					connection = DriverManager.getConnection( PROTOCOL + RemoteDATABASE, RemoteUSER, RemotePASSWORD );
			} catch (SQLException e) {			
				try {
					System.out.println("Local");
					connection = DriverManager.getConnection( PROTOCOL + LocalDATABASE,  LocalUSER,  LocalPASSWORD  );
				} catch (SQLException e1) {	e1.printStackTrace(); }
			}			
			
			return connection;
	}		
		

}
