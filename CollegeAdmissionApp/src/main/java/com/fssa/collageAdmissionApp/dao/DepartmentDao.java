package com.fssa.collageAdmissionApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.collageAdmissionApp.exception.DaoException;
import com.fssa.collageAdmissionApp.exception.InvalidDepartmentException;

import com.fssa.collageAdmissionApp.model.Department;
import com.fssa.collageAdmissionApp.util.ConnectionUtil;
import com.fssa.collageAdmissionApp.validator.DepartmentValidator;


public class DepartmentDao {

	public static void main(String[] args) throws InvalidDepartmentException, SQLException {
	
		Department department =  new Department(); 
//		department.setId(0);
		department.setName("ECE");
		department.setName("BSC CS");
		department.setName("MBA");
		department.setName("MCA");
		department.setName("CA");
		department.setName("PARA MED");
		department.setName("BFORM");
		addDepartment(department);
		
	}
	
	
	public static boolean readDepartment() throws DaoException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Departments;";
			try (Statement statement = connection.createStatement()) {

				try (ResultSet resultSet = statement.executeQuery(query)) {

					while (resultSet.next()) {
						
						
						System.out.println("id: "+resultSet.getInt(1));
						System.out.println("DepartmentName: "+resultSet.getString(2));
						System.out.println("\n");

					}
					return true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DaoException("No Objects Found");
			}

		}

	}

	public static void addDepartment(Department department) throws InvalidDepartmentException, SQLException {
	    try {
	        DepartmentValidator.validateDepartment(department);
	    } catch (InvalidDepartmentException e) {
	        e.printStackTrace();
	        throw new InvalidDepartmentException("Invalid department", e);
	    }

	    try (Connection connection = ConnectionUtil.getConnection()) {
	        String query1 = "INSERT INTO departments(name) VALUES (?);";
	        String query2 = "INSERT INTO departments(name) VALUES (?);";
	        String query3 = "INSERT INTO departments(name) VALUES (?);";
	        String query4 = "INSERT INTO departments(name) VALUES (?);";
	        String query5 = "INSERT INTO departments(name) VALUES (?);";
	        String query6 = "INSERT INTO departments(name) VALUES (?);";
	        String query7 = "INSERT INTO departments(name) VALUES (?);";
	        try (Statement statement = connection.createStatement()) {
	        	
	        	PreparedStatement pr=connection.prepareStatement(query1);
	        	pr.setString(1,department.getName());
	        	
	        	connection.setAutoCommit(false);
	        	
	        	statement.addBatch(pr.toString());
				statement.addBatch(query2);
				statement.addBatch(query3);
				
				statement.addBatch(query4);
				statement.addBatch(query5);
			    statement.addBatch(query6);
			    statement.addBatch(query7);

	            int[] rows = statement.executeBatch();
	            System.out.println("added");
	            System.out.println("added");
	            System.out.println("added");
	            System.out.println("added");
//	            int totalRowsAffected = 0;
	            
	            for (int row : rows) {
//	                totalRowsAffected += row

	            	if(row > 0) {
	            		
	            		continue;
	            	}else {
	            		connection.rollback();
	            	}
	            	
	            }
	            connection.commit();

	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new InvalidDepartmentException("Cannot add a new department");
	    }
	}


	public static boolean updateDepartment(Department department, int id)
			throws DaoException,InvalidDepartmentException, SQLException {
		DepartmentValidator.validateDepartment(department);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE departments SET name = ? WHERE id = ?";

			try (PreparedStatement pst = connection.prepareStatement(query)) {
				
				pst.setString(1, department.getName());
				pst.setInt(2, id);
				

				int rows = pst.executeUpdate();

				if(rows > 0) {
					return true;
				}else {
					return false;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}

	}

	public static boolean removeDepartment(int id) throws DaoException, InvalidDepartmentException, SQLException {

		DepartmentValidator.validateId(id);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM departments WHERE id = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, id);

				int rows = pst.executeUpdate();
				

				return (rows > 0) ? true : false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}

	}

	public static boolean findDepartmentByName(String name) throws DaoException, SQLException, InvalidDepartmentException {
		DepartmentValidator.validateDepartmentName(name);
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String query = "SELECT * FROM Departments WHERE name = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, name);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
			            System.out.println("id: "+resultSet.getInt(1));
			            System.out.println("DepartmentName: "+resultSet.getString(2));
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException(e);
			}
		}
		return true;
	}

}

