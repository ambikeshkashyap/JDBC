package com.ambi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;

public class HandlingUser
{
	final static Scanner sc = new Scanner(System.in);
	static final String DB_URL = "jdbc:mysql://localhost:3306/advjava";
	static final String DB_UNAME = "root";
	static final String DB_PWD = "1234";
	
	static Optional<Connection> getConn() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(DB_URL,DB_UNAME,DB_PWD);
		
		return Optional.ofNullable(conn);
		
	}
	
	static void triggerLogin() throws Exception {
		String in;
		
		while(true) {
			System.out.println("Please enter your UserId");
			
			try {
				in = sc.next();
				break;
			}
			catch(Exception e) {
				System.out.println("UserId should be a number");
				sc.next();
			}
		}
		Connection c = null;
		Optional<Connection> op =HandlingUser.getConn();
		if(op.isPresent()) {
			c = op.get();
		}else {
			System.out.println("Some issue with db connection");
			System.exit(0);
		}
		if(c != null) {
		Statement st = c.createStatement();
		String stf = "Select * from USER where USER_ID = '" + in + "'";
		System.out.println(stf);
		ResultSet res =  st.executeQuery(stf);
		if(res.next()) {
			if(in.equals(res.getString("USER_ID"))) {
				int tries = 0;
				boolean check = true;
				while(tries <= 3) {
				System.out.println("Please Enter your password");
				if(sc.next().equals(res.getString("USER_PWD"))){
					System.out.println("Successfully Logged in..");
					check = false;
					break;
				}
				else {
					System.out.println("Incorrect Password");
					tries++;
				}}
				if(check == true) {
					System.out.println("Maximum attempt over");
					System.exit(0);
				}
			}
			else {
				System.out.println("UserId doest not exist");
			}
		}
		else {
			System.out.println("UserId doest not exist");
		}
		
		}
		
	}
	
	static void triggerSignUp() throws Exception{
		System.out.println("In SignUp");
		
		System.out.println("Please enter new unique userId");
		String uId = sc.next();
		System.out.println("Please enter UserName");
		String uName = sc.next();
		System.out.println("Please enter new Password");
		String uPwd = sc.next();
		System.out.println("Please enter Mobile number");
		String uMob = sc.next();
		
		Connection c = null;
		Optional<Connection> op =HandlingUser.getConn();
		if(op.isPresent()) {
			c = op.get();
		}else {
			System.out.println("Some issue with db connection");
			System.exit(0);
		}
		if(c != null) {
		Statement st = c.createStatement();
		String strr = "Insert into USER values('"+ uId+ "','" + uName+ "','" +uPwd+ "','" + uMob+"')";
		System.out.println(strr);
		int res = 0;
		try {
		 res =  st.executeUpdate(strr);
		}
		catch(Exception e) {
			System.out.println("Email Id already Registered....");
			System.exit(0);
		}
		if(res == 0) {
			System.out.println("Unable to register");
		}
		else {
			System.out.println("Successfully registered");
		}
		}
		
		
		

	}
	
	
	public static void main(String[] args) throws Exception {
		
		int input = 0;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Welcome User, Please press 1 for Login or 2 for Sign-up");
			
			try {
				input = sc.nextInt();
				if(input == 1 || input == 2) {
					break;
					}
				System.out.println("Please enter correct input");
			}
			catch(Exception e) {
				System.out.println("Please enter correct input");
				sc.next();
				
				/*
				     Adding sc.next(); in the catch block clears the invalid input buffer. This is important because when nextInt() method fails to parse the input as an integer (for example, if the user enters a letter instead of a number), the invalid input remains in the buffer. If you don't clear this invalid input from the buffer, the next time nextInt() is called, it will try to parse the same invalid input, leading to the same exception being thrown again and again. This can cause an infinite loop of exceptions.

                    By adding sc.next(); in the catch block, you consume and discard the invalid input from the buffer, so the next time nextInt() is called, it will wait for new input from the user instead of trying to parse the same invalid input again. This prevents the program from getting stuck in an infinite loop of exceptions caused by repeatedly trying to parse the same invalid input.
				 */
			}
		}
		
	
		switch(input) {
		case 1: triggerLogin();
				break;
		case 2: triggerSignUp();
				break;
		}
		
		
	}

}
