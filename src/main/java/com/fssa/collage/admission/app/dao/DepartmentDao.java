package com.fssa.collage.admission.app.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.collage.admission.app.exception.DaoException;
import com.fssa.collage.admission.app.exception.InvalidDepartmentException;
import com.fssa.collage.admission.app.model.Department;
import com.fssa.collage.admission.app.util.ConnectionUtil;
import com.fssa.collage.admission.app.util.Logger;
import com.fssa.collage.admission.app.validator.DepartmentValidator;

public class DepartmentDao {

	public static void main(String[] args) throws InvalidDepartmentException, SQLException {

		Department department = new Department();
		addDepartment(department);

	}

	public static boolean readDepartment() throws DaoException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Departments;";
			try (Statement statement = connection.createStatement()) {

				try (ResultSet resultSet = statement.executeQuery(query)) {

					while (resultSet.next()) {

						Logger.info("id: " + resultSet.getInt(1));
						Logger.info("DepartmentName: " + resultSet.getString(2));
						Logger.info("\n");

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
			String query1 = "INSERT INTO departments(id,name) VALUES (CSE);";
			String query2 = "INSERT INTO departments(id,name) VALUES (MECH);";
			String query3 = "INSERT INTO departments(id,name) VALUES (EEE);";
			String query4 = "INSERT INTO departments(id,name) VALUES (ECE);";
			String query5 = "INSERT INTO departments(id,name) VALUES (IT);";
			try (Statement statement = connection.createStatement()) {

				statement.addBatch(query1);
				statement.addBatch(query2);
				statement.addBatch(query3);
				statement.addBatch(query4);
				statement.addBatch(query5);

				int[] rows = statement.executeBatch();

				Logger.info("added");
//	            int totalRowsAffected = 0;

				for (int row : rows) {
//	                totalRowsAffected += row

					if (row > 0) {

						continue;
					} else {
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
			throws DaoException, InvalidDepartmentException, SQLException {
		DepartmentValidator.validateDepartment(department);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE departments SET name = ? WHERE id = ?";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, department.getName());
				pst.setInt(2, id);

				int rows = pst.executeUpdate();

				if (rows > 0) {
					return true;
				} else {
					return false;
				}

			}
			// TODO : no of rows affected
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

	public static boolean findDepartmentByName(String name)
			throws DaoException, SQLException, InvalidDepartmentException {
		DepartmentValidator.validateDepartmentName(name);
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM Departments WHERE name = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, name);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
						Logger.info("id: " + resultSet.getInt(1));
						Logger.info("DepartmentName: " + resultSet.getString(2));
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
