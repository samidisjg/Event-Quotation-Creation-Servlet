package s_supplier;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	private static String url = "jdbc:mysql://localhost:3306/eventplan";  //establishing the DBconnection
	private static String user = "root";
	private static String password = "s2001";
	

	private static Connection con;

	public static Connection getConnection() {

		try { //starting try catch block
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Database connection is not success!");  
		}//end of try catch block

		return con;
	}
}
