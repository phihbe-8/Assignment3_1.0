package net.codejava.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnect {
	
	private static String dbhost = "jdbc:mysql://localhost:3306/soa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
	private static String username = "root";
	private static String password = "ozzyozzy2";
	private static Connection conn;
	
	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					dbhost, username, password);	
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}

}
