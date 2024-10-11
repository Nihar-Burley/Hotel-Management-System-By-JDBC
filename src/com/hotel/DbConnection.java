package com.hotel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnection {
	private static final String url="jdbc:mysql://localhost/db";
	private static final String userName="root";
	private static final String password="Mysql@123";
	static Connection conn=null;
	
	public static Connection connect()
	{
		try {
			conn=DriverManager.getConnection(url,userName, password);
			Statement st=conn.createStatement();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
		return conn;
	}
}
