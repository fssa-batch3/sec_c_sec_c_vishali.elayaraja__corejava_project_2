package com.fssa.collage.admission.app.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	public static void main(String[] args) {
		getConnection();
		System.out.println("connection created");
	}

	public static Connection getConnection() {
		Connection con = null;

//	        String url, userName, passWord;

//	            url = System.getenv("DATABASE_HOST");
//	            userName = System.getenv("DATABASE_USERNAME");
//	            passWord = System.getenv("DATABASE_PASSWORD");

		String url = "jdbc:mysql://localhost:3306/Collage_management_app";
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
}
