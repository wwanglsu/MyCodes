package com.jdjsoftware.dao;

import java.sql.*;
import org.json.JSONObject;

public class DatabaseHelper {
	
	   // JDBC driver name and database URL
	   private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	   private static final String DB_URL = "jdbc:mysql://localhost:3306/testdemo_db";
	   private static Connection connection=null;
	   //  Database credentials
	   private static final String USER = "developer";
	   private static final String PASS = "developer";
	   
	   public static void getConnection (String db_url, String user, String password ) {
		   try{
			      //Register JDBC driver
			      Class.forName(JDBC_DRIVER);

			      //Open a connection
			      System.out.println("Connecting to a selected database...");
			      connection = DriverManager.getConnection(db_url, user, password);
			      System.out.println("Connected database successfully...");
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(connection!=null)
			        	 connection.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
	   }
	   
	   public static boolean validateUser(String sqlString) {
		   Statement stmt = null;
		   try{
			      //Execute a query
			      System.out.println("Creating statement...");
			      stmt = connection.createStatement();
			      
			      ResultSet rs = stmt.executeQuery(sqlString);
			      return rs.first();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			        	 connection.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(connection!=null)
			        	 connection.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		   return false;
	   }
	
}
