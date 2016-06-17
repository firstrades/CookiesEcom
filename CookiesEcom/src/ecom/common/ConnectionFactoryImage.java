package ecom.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImage {	

	private final String PROTOCOL        = "jdbc:mysql://localhost:3306/";   
	//private final String PROTOCOL        = "jdbc:mysql://104.254.98.163:3306/";
	
	private final String RemoteDATABASE  = "firstrad_firstdb" + "?noAccessToProcedureBodies=true";
	private final String RemoteUSER      = "firstrad_fisroot";
	private final String RemotePASSWORD  = "1Gx5rD~MsGb.";
	
	private final String LocalDATABASE   = "cookieimage"+ "?noAccessToProcedureBodies=true";
	private final String LocalUSER       = "root";
	private final String LocalPASSWORD   = "";
	
	public static Connection getNewConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		return new ConnectionFactoryImage().createConnection();
	}
	
	private Connection createConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {	
		
			Class.forName("com.mysql.jdbc.Driver");	
			
			Connection connection = null;
			
			try {
					connection = DriverManager.getConnection( PROTOCOL + RemoteDATABASE, RemoteUSER, RemotePASSWORD );
			} catch (SQLException e) {			
				try {System.out.println("Local");
					connection = DriverManager.getConnection( PROTOCOL + LocalDATABASE,  LocalUSER,  LocalPASSWORD  );
				} catch (SQLException e1) {	e1.printStackTrace(); }
			}			
			
			return connection;
	}		
		

}
