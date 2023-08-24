package com.fssa.collage.admission.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.collage.admission.app.exception.DAOException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Student;
import com.fssa.collage.admission.app.model.StudentErrors;
import com.fssa.collage.admission.app.util.ConnectionUtil;
import com.fssa.collage.admission.app.validator.StudentValidator;

public class StudentDAO {

	private StudentDAO() {

	}

	public static List<Student> getAllStudent() throws DAOException, SQLException {

		Student student = new Student();
		List<Student> studentList = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM students";
			try (Statement statement = connection.createStatement()) {

				try (ResultSet resultSet = statement.executeQuery(query)) {

					while (resultSet.next()) {

						student.setId(resultSet.getInt("roll_no"));
						student.setFirstName(resultSet.getString("first_name"));
						student.setLastName(resultSet.getString("last_name"));
						student.setGender(resultSet.getString("gender"));
						student.setDob(resultSet.getDate("dob").toLocalDate());
						student.setEmailId(resultSet.getString("email"));
						student.setPassword(resultSet.getString("password"));
						student.setIsActive(resultSet.getBoolean("status"));
						studentList.add(student);

					}
					return studentList;
				}
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DAOException("No Objects Found");
			}

		}

	}

	public static boolean addStudent(Student student) throws InvalidStudentException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO students(roll_no,first_name, last_name, gender, dob, email, password, mobile_no) VALUES (?,?,?,?,?,?,?,?)";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, student.getRollNo());
				pst.setString(2, student.getFirstName());
				pst.setString(3, student.getLastName());
				pst.setString(4, student.getGender());
				pst.setDate(5, Date.valueOf(student.getDob()));
				pst.setString(6, student.getEmailId());
				pst.setString(7, student.getPassword());
				pst.setLong(8, student.getMobileNumber());

				int rows = pst.executeUpdate();
				return (rows > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidStudentException(StudentErrors.CANNOT_ADD_STUDENT);
		}

	}

	public static boolean updateStudent(Student student, int rollNo) throws DAOException, InvalidStudentException {
		StudentValidator.validateStudent(student);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE students SET email = ? WHERE id = (SELECT * from students WHERE roll_no = ? )";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, student.getEmailId());
				pst.setInt(2, rollNo);

				int rows = pst.executeUpdate();

				return (rows > 0);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

	public static boolean removeStudent(int rollNo) throws DAOException, InvalidStudentException {

		StudentValidator.validateId(rollNo);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "DELETE students FROM students JOIN (SELECT id FROM students WHERE roll_no = ?) AS subquery ON students.id = subquery.id;";
			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, rollNo);

				int rows = pst.executeUpdate();

				return (rows > 0);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

	public static List<Student> findStudentByName(String firstName, String lastName)
			throws DAOException, SQLException, InvalidStudentException {
		StudentValidator.validateFirstName(firstName);
		StudentValidator.validateLastName(lastName);

		List<Student> studentList = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM students WHERE first_name = ? AND last_name =?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, firstName);
				pst.setString(2, lastName);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
						Student student = new Student();
						student.setId(resultSet.getInt("roll_no"));
						student.setFirstName(resultSet.getString("first_name"));
						student.setLastName(resultSet.getString("last_name"));
						student.setGender(resultSet.getString("gender"));
						student.setDob(resultSet.getDate(5).toLocalDate());
						student.setEmailId(resultSet.getString("email_id"));
						student.setPassword(resultSet.getString("password"));
						student.setIsActive(resultSet.getBoolean("status"));
						studentList.add(student);
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return studentList;
	}

}