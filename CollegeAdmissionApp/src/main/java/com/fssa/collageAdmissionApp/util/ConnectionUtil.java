package com.fssa.collageAdmissionApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	
	public static void main(String[] args) {
		getConnection();
		System.out.println("Connection created....");
	}
	
	public static Connection getConnection()  {
		 
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/core_java";
        String userName = "root";
        String passWord = "123456";
        try {
            con = DriverManager.getConnection(url, userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to the database");
        }
        return con;
    }

	

public static void close(Connection conn , Statement stmt, ResultSet rs){
    
   try
   {
       if ( rs != null ){
           rs.close();
       }
       if ( stmt != null ) {
           ((Connection) stmt).close();
       }
       if ( conn != null ){
           conn.close();
       }
   }
   catch(SQLException e){
        e.printStackTrace();
					 // No need re throw the exception.
    }
  }
}

