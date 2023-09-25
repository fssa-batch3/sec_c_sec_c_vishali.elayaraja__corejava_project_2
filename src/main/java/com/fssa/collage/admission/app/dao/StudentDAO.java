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

/**
 * This class provides data access methods for managing student data.
 */

public class StudentDAO {

	public StudentDAO() {
		// Private constructor to prevent instantiation.
	}

	/**
	 * Retrieves a list of all students from the database.
	 *
	 * @return A list of Student objects representing all students.
	 * @throws DAOException If there's an issue with the database.
	 * @throws SQLException If there's an issue executing SQL queries.
	 */

	public static List<Student> getAllStudent() throws DAOException, SQLException {
		// Implementation
		List<Student> studentList = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT student_class.id, students.first_name,students.last_name,students.gender,students.dob,students.mobile_no,students.email,departments.dept_name,student_class.status\r\n"
					+ "FROM students\r\n" + "JOIN student_class \r\n" + "ON students.id = student_class.student_id\r\n"
					+ "join departments\r\n"
					+ "on student_class.department_id = departments.id where students.status = 1";
			try (Statement statement = connection.createStatement()) {

				try (ResultSet resultSet = statement.executeQuery(query)) {

					while (resultSet.next()) {
						Student student = new Student();
						student.setId(resultSet.getInt("id"));
						student.setFirstName(resultSet.getString("first_name"));
						student.setLastName(resultSet.getString("last_name"));
						student.setGender(resultSet.getString("gender"));
						student.setDob(resultSet.getDate("dob").toLocalDate());
						student.setEmailId(resultSet.getString("email"));
						student.setMobileNumber(resultSet.getLong("mobile_no"));
						student.setStatus(resultSet.getString("status"));
						student.setDepartment(resultSet.getString("dept_name"));
						studentList.add(student);
					}
					return studentList;
				}
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DAOException("unable to retrive student list");
			}

		}

	}

	/**
	 * Adds a new student to the database.
	 *
	 * @param student The Student object representing the new student.
	 * @return True if the student is added successfully, false otherwise.
	 * @throws InvalidStudentException If the student data is invalid.
	 */

	public static boolean addStudent(Student student) throws InvalidStudentException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO students(first_name, last_name, gender, dob, email, password, mobile_no) VALUES (?,?,?,?,?,?,?)";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, student.getFirstName());
				pst.setString(2, student.getLastName());
				pst.setString(3, student.getGender());
				pst.setDate(4, Date.valueOf(student.getDob()));
				pst.setString(5, student.getEmailId());
				pst.setString(6, student.getPassword());
				pst.setLong(7, student.getMobileNumber());

				int rows = pst.executeUpdate();
				return (rows > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidStudentException(StudentErrors.CANNOT_ADD_STUDENT);
		}

	}

	/**
	 * Updates the email of a student in the database.
	 *
	 * @param student The Student object representing the updated student.
	 * @param id      The ID of the student to update.
	 * @return True if the student is updated successfully, false otherwise.
	 * @throws SQLException
	 * @throws DAOException            If there's an issue with the database.
	 * @throws InvalidStudentException If the student data is invalid.
	 */
	public static int getIdByStudentEmail(String email) throws SQLException {
		int id = 0;
		String query = "SELECT id FROM students WHERE email = ?";
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, email);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						id = rs.getInt("id");
					}
				}
			}
		}
		return id;
	}

	public static boolean updateStudent(Student student) throws DAOException, InvalidStudentException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE students SET first_name = ?,last_name=?," + " mobile_no = ? WHERE email = ?";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, student.getFirstName());
				pst.setString(2, student.getLastName());
				pst.setLong(3, student.getMobileNumber());
				pst.setString(4, student.getEmailId());

				int rows = pst.executeUpdate();

				return (rows > 0);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

	/**
	 * Removes a student from the database.
	 *
	 * @param id The ID of the student to remove.
	 * @return True if the student is removed successfully, false otherwise.
	 * @throws DAOException            If there's an issue with the database.
	 * @throws InvalidStudentException If the student ID is invalid.
	 */
	public static boolean removeStudent(String email, boolean status) throws DAOException, InvalidStudentException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE students SET status = ? WHERE email = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setBoolean(1, status);
				pst.setString(2, email);

				int rows = pst.executeUpdate();

				return (rows > 0);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

	/**
	 * Finds students by their first name and last name in the database.
	 *
	 * @param firstName The first name to search for.
	 * @param lastName  The last name to search for.
	 * @return A list of Student objects matching the search criteria.
	 * @throws DAOException            If there's an issue with the database.
	 * @throws SQLException            If there's an issue executing SQL queries.
	 * @throws InvalidStudentException If the search criteria is invalid.
	 */

	public static Student findStudentById(int id) throws DAOException, SQLException, InvalidStudentException {
		StudentValidator.validateId(id);

		Student studentList = null;
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM students WHERE id =?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, id);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
						Student student = new Student();
						student.setId(resultSet.getInt("Id"));
						student.setFirstName(resultSet.getString("first_name"));
						student.setLastName(resultSet.getString("last_name"));
						student.setGender(resultSet.getString("gender"));
						student.setDob(resultSet.getDate("dob").toLocalDate());
						student.setMobileNumber(resultSet.getLong("mobile_no"));
						student.setEmailId(resultSet.getString("email"));
						student.setPassword(resultSet.getString("password"));

					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return studentList;
	}

	public static List<Student> findStudentByName(String firstName)
			throws DAOException, SQLException, InvalidStudentException {

		List<Student> studentList = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "SELECT students.id, students.first_name, students.last_name, students.gender, students.dob, students.mobile_no, students.email, departments.dept_name, student_class.status\r\n"
					+ "FROM students\r\n"
					+ "JOIN student_class ON students.id = student_class.student_id\r\n"
					+ "JOIN departments ON student_class.department_id = departments.id\r\n"
					+ "WHERE students.status = 1\r\n"
					+ "AND students.first_name LIKE ?;";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, "%" + firstName + "%");

				try (ResultSet resultSet = pst.executeQuery()) {
					while (resultSet.next()) {
						Student student = new Student();
						student.setId(resultSet.getInt("id"));
						student.setFirstName(resultSet.getString("first_name"));
						student.setLastName(resultSet.getString("last_name"));
						student.setGender(resultSet.getString("gender"));
						student.setDob(resultSet.getDate("dob").toLocalDate());
						student.setEmailId(resultSet.getString("email"));
						student.setMobileNumber(resultSet.getLong("mobile_no"));
						student.setStatus(resultSet.getString("status"));
						student.setDepartment(resultSet.getString("dept_name"));
						studentList.add(student);
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException(e.getMessage());
			}
		}
		return studentList;
	}

	public static boolean updatingStatusOfStudent(int id, String status) throws DAOException, InvalidStudentException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE student_class SET status = ? WHERE id = ?";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, status);
				pst.setInt(2, id);

				int rows = pst.executeUpdate();

				return (rows > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
	}

//	public static List<Student> getAllStudents() throws DAOException, SQLException {
//		// Implementation
//		List<Student> studentList = new ArrayList<>();
//		try (Connection connection = ConnectionUtil.getConnection()) {
//			String query = "SELECT students.first_name,students.last_name,students.gender,\r\n"
//					+ "students.dob,students.mobile_no,students.email,student_class.status\r\n"
//					+ "FROM students \r\n"
//					+ "JOIN student_class\r\n"
//					+ "ON students.id = student_class.student_id";
//			try (Statement statement = connection.createStatement()) {
//
//				try (ResultSet resultSet = statement.executeQuery(query)) {
//
//					while (resultSet.next()) {
//						Student student = new Student();
//						student.setFirstName(resultSet.getString("first_name"));
//						student.setLastName(resultSet.getString("last_name"));
//						student.setGender(resultSet.getString("gender"));
//						student.setDob(resultSet.getDate("dob").toLocalDate());
//						student.setEmailId(resultSet.getString("email"));
//						student.setPassword(resultSet.getString("password"));
//						student.setMobileNumber(resultSet.getLong("mobile_no"));
//						student.setIsActive(resultSet.getBoolean("status"));
//						studentList.add(student);
//					}
//					return studentList;
//				}
//			} catch (SQLException e) {
//
//				e.printStackTrace();
//				throw new DAOException("unable to retrive student list");
//			}
//
//		}
//
//	}

	/**
	 * Checks if a student with the given email exists in the database.
	 *
	 * @param email The email to check.
	 * @return True if a student with the email exists, false otherwise.
	 * @throws SQLException If there's an issue executing SQL queries.
	 * @throws DAOException If there's an issue with the database.
	 */

	public static boolean checkStudentExists(String email) throws SQLException, DAOException {
		String query = "SELECT email FROM students WHERE email = ?";
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, email);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						return true;
					}
				}
			}
		}
		return false;
	}


	public static boolean login(String email, String password) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT email FROM students WHERE email = ? AND password = ? AND status = 1";
			try (PreparedStatement psmt = con.prepareStatement(query)) {
				psmt.setString(1, email);
				psmt.setString(2, password);
				// Executes the delete query.
				try (ResultSet resultSet = psmt.executeQuery()) {
					if (resultSet.next()) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return false;
	}

	/**
	 * Finds a student by their email in the database.
	 *
	 * @param email The email to search for.
	 * @return A Student object representing the found student, or null if not
	 *         found.
	 * @throws DAOException            If there's an issue with the database.
	 * @throws SQLException            If there's an issue executing SQL queries.
	 * @throws InvalidStudentException If the email is invalid.
	 */

	public static Student findStudentByEmail(String email) throws DAOException, SQLException, InvalidStudentException {

		Student studentList = new Student();
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM students WHERE email =?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, email);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
						studentList.setId(resultSet.getInt("Id"));
						studentList.setFirstName(resultSet.getString("first_name"));
						studentList.setLastName(resultSet.getString("last_name"));
						studentList.setGender(resultSet.getString("gender"));
						studentList.setDob(resultSet.getDate("dob").toLocalDate());
						studentList.setMobileNumber(resultSet.getLong("mobile_no"));
						studentList.setEmailId(resultSet.getString("email"));
						studentList.setPassword(resultSet.getString("password"));
						studentList.setActive(resultSet.getBoolean("status"));

					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return studentList;
	}
	
	
//		

	public static List<Student> getApplicationByEmail(String email)
			throws DAOException, SQLException, InvalidStudentException {

		List<Student> studentList = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "SELECT student_class.id,students.first_name,students.last_name,students.gender,students.dob,students.mobile_no,students.email,departments.dept_name,student_class.status\r\n"
					+ "FROM students\r\n" + "JOIN student_class \r\n" + "ON students.id = student_class.student_id\r\n"
					+ "join departments\r\n" + "on student_class.department_id = departments.id \r\n"
					+ "where students.email=?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, email);
				try (ResultSet resultSet = pst.executeQuery()) {
					while (resultSet.next()) {
						Student student = new Student();
						student.setId(resultSet.getInt("id"));
						student.setFirstName(resultSet.getString("first_name"));
						student.setLastName(resultSet.getString("last_name"));
						student.setGender(resultSet.getString("gender"));
						student.setDob(resultSet.getDate("dob").toLocalDate());
						student.setEmailId(resultSet.getString("email"));
						student.setMobileNumber(resultSet.getLong("mobile_no"));
						student.setStatus(resultSet.getString("status"));
						student.setDepartment(resultSet.getString("dept_name"));
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