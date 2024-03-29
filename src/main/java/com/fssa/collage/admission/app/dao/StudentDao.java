package com.fssa.collage.admission.app.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.collage.admission.app.exception.DaoException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Student;
import com.fssa.collage.admission.app.util.ConnectionUtil;
import com.fssa.collage.admission.app.util.Logger;
import com.fssa.collage.admission.app.validator.StudentValidator;

public class StudentDao {
	
	private StudentDao() {
		
	}

	public static boolean readStudent() throws DaoException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM students;";
			try (Statement statement = connection.createStatement()) {

				try (ResultSet resultSet = statement.executeQuery(query)) {

					while (resultSet.next()) {
						Logger.info("Id: " + resultSet.getInt(1));
						Logger.info("first_name: " + resultSet.getString(2));
						Logger.info("last_name: " + resultSet.getString(3));
						Logger.info("gender: " + resultSet.getString(4));
						Logger.info("dob: " + resultSet.getDate(5));
						Logger.info("mobile_no: " + resultSet.getString(6));
						Logger.info("email: " + resultSet.getString(7));
						Logger.info("password: " + resultSet.getString(8));
						Logger.info("is_active: " + resultSet.getBoolean(10));
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

	public static boolean addStudent(Student student) throws InvalidStudentException {
//		try {
//			StudentValidator.validateStudent(student);
//		} catch (InvalidStudentException e) {
//			e.printStackTrace();
//			throw new InvalidStudentException(StudentsErrors.INVALID_STUDENT);
//		}

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO students(first_name, last_name, gender, dob, email, password, mobile_no) VALUES (?,?,?,?,?,?,?);";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, student.getFirstName());
				pst.setString(2, student.getLastName());
				pst.setString(3, student.getGender());
				pst.setDate(4, java.sql.Date.valueOf(student.getDob()));
				pst.setString(5, student.getEmailId());
				pst.setString(6, student.getPassword());
				pst.setString(7, student.getMobileNumber());

				int rows = pst.executeUpdate();
				return (rows > 0) ? true : false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidStudentException("Cannot add a new Student");
		}

	}

	public static boolean updateStudent(Student student, int id) throws DaoException, InvalidStudentException {
		StudentValidator.validateStudent(student);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE students SET email = ? WHERE id = ?";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, student.getEmailId());
				pst.setInt(2, id);

				int rows = pst.executeUpdate();

				if(rows > 0) {
					return true;
				} else {
					return false;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}

	}

	public static boolean removeStudent(int id) throws DaoException, InvalidStudentException {

		StudentValidator.validateId(id);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM students WHERE id = ?";
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

	public static boolean findStudentByName(String firstName)
			throws DaoException, SQLException, InvalidStudentException {
		StudentValidator.validateFirstName(firstName);
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM students WHERE first_name = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, firstName);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
						Logger.info("Id: " + resultSet.getInt(1));
						Logger.info("first_name: " + resultSet.getString(2));
						Logger.info(("last_name: " + resultSet.getString(3)));
						Logger.info(("gender: " + resultSet.getString(4)));
						Logger.info("dob: " + resultSet.getDate(5));
						Logger.info("mobile_no: " + resultSet.getString(6));
						Logger.info("email: " + resultSet.getString(7));
						Logger.info("password: " + resultSet.getString(8));
						Logger.info(("is_active: " + resultSet.getBoolean(10)));
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