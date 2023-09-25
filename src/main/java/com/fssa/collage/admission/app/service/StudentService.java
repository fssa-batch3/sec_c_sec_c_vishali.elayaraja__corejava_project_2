package com.fssa.collage.admission.app.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.collage.admission.app.dao.StudentDAO;
import com.fssa.collage.admission.app.dao.StudentDepartmentDAO;
import com.fssa.collage.admission.app.exception.DAOException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Student;
import com.fssa.collage.admission.app.validator.StudentValidator;

public class StudentService {

	/**
	 * The StudentService class provides services for managing student data.
	 */
	public StudentService() {
//		private constructor
	}

	/**
	 * Adds a new student and associates them with a department.
	 *
	 * @param student        The student to add.
	 * @param departmentName The name of the department to associate the student
	 *                       with.
	 * @return True if the student was successfully added, false otherwise.
	 * @throws InvalidStudentException If the student data is invalid.
	 * @throws SQLException            If a database error occurs.
	 * @throws DAOException            If a data access error occurs.
	 */

	public static boolean addStudent(Student student, String departmentName)
			throws InvalidStudentException, SQLException, DAOException {

		if (StudentValidator.validateStudentApplyForm(student)
				&& (StudentDAO.checkStudentExists(student.getEmailId()))) {

			StudentDepartmentDAO.AddStudentDept(student, departmentName);

		}

		return true;

	}

	public static boolean studentRegisteration(Student student)
			throws InvalidStudentException, SQLException, DAOException {

		if (StudentValidator.validateStudent(student) && (!StudentDAO.checkStudentExists(student.getEmailId()))) {

			StudentDAO.addStudent(student);

		}

		return true;

	}

	/**
	 * Retrieves a list of all students.
	 *
	 * @return A list of all students.
	 * @throws DAOException If a data access error occurs.
	 * @throws SQLException If a database error occurs.
	 */

	public static List<Student> getAllStudent() throws DAOException, SQLException {
		List<Student> studentList = new ArrayList<>();
		studentList = StudentDAO.getAllStudent();

		return studentList;
	}

	/**
	 * Updates student information.
	 *
	 * @param student The updated student information.
	 * @return True if the student information was successfully updated, false
	 *         otherwise.
	 * @throws InvalidStudentException If the student data is invalid.
	 * @throws DAOException            If a data access error occurs.
	 */

	public static boolean updateStudent(Student student) throws InvalidStudentException, DAOException {
		if (StudentValidator.validateFirstName(student.getFirstName())
				&& StudentValidator.validateLastName(student.getLastName())
				&& StudentValidator.validateEmail(student.getEmailId())
				&& StudentValidator.validateMobileNumber(student.getMobileNumber())) {
			StudentDAO.updateStudent(student);
		}
		return true;

	}

	/**
	 * Removes a student by their ID.
	 *
	 * @param id The ID of the student to remove.
	 * @return True if the student was successfully removed, false otherwise.
	 * @throws InvalidStudentException If the student data is invalid.
	 * @throws DAOException            If a data access error occurs.
	 */

	public static boolean removeStudent(String email, boolean status) throws InvalidStudentException, DAOException {
		if (StudentValidator.validateEmail(email)) {
			StudentDAO.removeStudent(email, status);
		}
		return true;

	}

	/**
	 * Finds students by their first name and last name.
	 *
	 * @param firstName The first name of the students to find.
	 * @param lastName  The last name of the students to find.
	 * @return A list of students with matching first and last names.
	 * @throws InvalidStudentException If the student data is invalid.
	 * @throws DAOException            If a data access error occurs.
	 * @throws SQLException            If a database error occurs.
	 */

	public static List<Student> findStudentByName(String firstName)
			throws InvalidStudentException, DAOException, SQLException {
		List<Student> studentList = new ArrayList<>();
		if (StudentValidator.validateFirstName(firstName)) {
			studentList = StudentDAO.findStudentByName(firstName);

		}
		return studentList;
	}

	/**
	 * Finds a student by their email address.
	 *
	 * @param email The email address of the student to find.
	 * @return The student with the matching email address, or null if not found.
	 * @throws InvalidStudentException If the student data is invalid.
	 * @throws DAOException            If a data access error occurs.
	 * @throws SQLException            If a database error occurs.
	 */

	public static Student findStudentByEmail(String email) throws InvalidStudentException, DAOException, SQLException {
		Student studentList = null;
		if (StudentValidator.validateEmail(email)) {
			studentList = StudentDAO.findStudentByEmail(email);

		}
		return studentList;
	}

	public static List<Student> getStudentApplicationById(String email)
			throws DAOException, SQLException, InvalidStudentException {
		List<Student> studentList = new ArrayList<>();
		if (StudentValidator.validateEmail(email)) {
			studentList = StudentDAO.getApplicationByEmail(email);

		}

		return studentList;
	}


	/**
	 * Finds a student by their ID.
	 *
	 * @param id The ID of the student to find.
	 * @return The student with the matching ID, or null if not found.
	 * @throws InvalidStudentException If the student data is invalid.
	 * @throws DAOException            If a data access error occurs.
	 * @throws SQLException            If a database error occurs.
	 */

	public Student findStudentById(int id) throws InvalidStudentException, DAOException, SQLException {

		return StudentDAO.findStudentById(id);

	}

	public static boolean updatingStatusOfStudent(int id, String status) throws InvalidStudentException, DAOException {
		StudentDAO.updatingStatusOfStudent(id, status);
		if ("accept".equals(status)) {
//				 write a method that updates the student table status
//				 boolean s = Boolean.parseBoolean(status);
//				 StudentDAO.updatingStatusOfStudent(email, s);

		}
		return false;
	}
	
	

	public static boolean login(String email, String password)
			throws InvalidStudentException, DAOException, SQLException {
		if (StudentValidator.validateEmail(email) && (StudentDAO.checkStudentExists(email))) {
			StudentDAO.login(email, password);
		}
		return true;

	}
}
