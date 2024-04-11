package com.ambi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBRepo {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/advjava";
	static final String DB_UNAME = "root";
	static final String DB_PWD = "1234";
	static final String INSER_SQL = "Insert into BOOKS values(105,'Gita',3500)";
	static final String UPD_SQL = "Update BOOKS set name = 'alchemist' where id = 101";
	static final String SELECT = "select * from books where id = 101";

	
	public static void main(String[] args) throws Exception {
		
		
		
		Class clz =Class.forName("com.mysql.jdbc.Driver");
//		Object obj = clz.newInstance();
//		System.out.println(obj);
//		Method[] m =clz.getDeclaredMethods();
//		
//		for(Method m1 :m) {
//			System.out.println(m1.getName());
//			}
		
		Connection conn =DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		
		Statement st = conn.createStatement();
		
		int rowsAffected = st.executeUpdate(INSER_SQL);
		
		System.out.println(rowsAffected);
		
		conn.close();
		
		
		
	}
}
