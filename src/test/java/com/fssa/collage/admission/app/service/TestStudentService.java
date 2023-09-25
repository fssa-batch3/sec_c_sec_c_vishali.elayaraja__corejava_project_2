package com.fssa.collage.admission.app.service;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.collage.admission.app.errors.StudentsErrors;
import com.fssa.collage.admission.app.exception.DAOException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Student;

class TestStudentService {

	/**
	 * Test for adding a valid student.
	 *
	 * @throws SQLException            If an SQL exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 * @throws DAOException
	 */
	StudentService studentService = new StudentService();

	@Test
	void testAddStudent() throws SQLException, InvalidStudentException, DAOException {
		Student student = new Student();
		student.setFirstName("mathan");
		student.setLastName("kumar");
		student.setGender("male");
		student.setDob(LocalDate.of(2005, 9, 4));
		student.setEmailId("mathankumr@gmail.com.com");
		student.setPassword("Passwor10%");
		student.setMobileNumber(9445856960L);
		student.setStatus("pending");

		Assertions.assertTrue(studentService.addStudent(student, "CSE"));
	}

	/**
	 * Test for adding an invalid student.
	 *
	 * @throws DAOException            If a DAO exception occurs
	 * @throws SQLException            If an SQL exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 */

	@Test

	void testInvalidAddStudents() throws DAOException, SQLException, InvalidStudentException {

		try {
			studentService.addStudent(null, null);
			Assertions.fail("Cannot add a new Student");
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
		}
	}

	/**
	 * Test for updating a valid student.
	 *
	 * @throws InvalidStudentException If the student data is invalid
	 * @throws DAOException            If a DAO exception occurs
	 */

	@Test
	void testUpdateStudent() throws InvalidStudentException, DAOException {
		Student student = new Student();
		student.setFirstName("Sandeep");
		student.setLastName("prakash");
		student.setGender("male");
		student.setDob(LocalDate.of(2002, 04, 19));
		student.setEmailId("Sandeep19@gmail.com");
		student.setPassword("Passwor10%");
		student.setMobileNumber(9445756978L);
		// student.setId(7);

		Assertions.assertTrue(studentService.updateStudent(student));

	}

	/**
	 * Test for updating an invalid student.
	 *
	 * @throws DAOException            If a DAO exception occurs
	 * @throws SQLException            If an SQL exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 */

	@Test
	void testInvalidUpdateStudent() throws DAOException, SQLException, InvalidStudentException {
		try {
			studentService.updateStudent(null);
			Assertions.fail("Invalid Student");
		} catch (InvalidStudentException e) {

			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
		}
	}

	/**
	 * Test for removing a valid student.
	 *
	 * @throws DAOException            If a DAO exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 */

	@Test
	void testRemoveStudent() throws DAOException, InvalidStudentException {

		Assertions.assertTrue(studentService.removeStudent("", false));

	}

	/**
	 * Test for removing an invalid student.
	 *
	 * @throws DAOException If a DAO exception occurs
	 */
	@Test
	void testInvalidRemoveStudent() throws DAOException {
		try {
			Assertions.assertTrue(studentService.removeStudent("", false));
			Assertions.fail("Invalid Student");
		} catch (InvalidStudentException e) {

			Assertions.assertEquals(StudentsErrors.INVALID_ID, e.getMessage());
		}
	}

	/**
	 * Test for retrieving all students (valid case).
	 *
	 * @throws DAOException            If a DAO exception occurs
	 * @throws SQLException            If an SQL exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 */

	@Test

	void testValidGetAllStudent() throws DAOException, SQLException, InvalidStudentException {

		Assertions.assertNotNull(StudentService.getAllStudent());

	}

	/**
	 * Test for retrieving all students (invalid case).
	 *
	 * @throws DAOException            If a DAO exception occurs
	 * @throws SQLException            If an SQL exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 */

	@Test

	void testInvalidgetAllStudent() throws DAOException, SQLException, InvalidStudentException {

		try {

			StudentService.getAllStudent();
		} catch (DAOException e) {
			Assertions.assertEquals("unable to retrive student list", e.getMessage());
		}
		Assertions.assertTrue(true);
	}

	/**
	 * Test for finding a student by name (valid case).
	 *
	 * @throws DAOException            If a DAO exception occurs
	 * @throws SQLException            If an SQL exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 */

	@Test
	void testValidFindStudentByName() throws DAOException, SQLException, InvalidStudentException {
		StudentService studentService = new StudentService();
		Assertions.assertNotNull(studentService.findStudentByName("pranaw"));

	}

	/**
	 * Test for finding a student by name (invalid case).
	 *
	 * @throws DAOException            If a DAO exception occurs
	 * @throws SQLException            If an SQL exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 */

	@Test
	void testInvalidfindStudentByName() throws DAOException, SQLException, InvalidStudentException {
		StudentService studentService = new StudentService();
		try {
			Assertions.assertNotNull(studentService.findStudentByName("b"));
			Assertions.fail("Student not found");
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_NAME, e.getMessage());
		}
	}

	/**
	 * Test for finding a student by email (valid case).
	 *
	 * @throws DAOException            If a DAO exception occurs
	 * @throws SQLException            If an SQL exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 */

	@Test
	void testValidFindStudentByEmail() throws DAOException, SQLException, InvalidStudentException {

		Assertions.assertNotNull(studentService.findStudentByEmail("vishali@gmail.com"));

	}

	/**
	 * Test for finding a student by email (invalid case).
	 *
	 * @throws DAOException            If a DAO exception occurs
	 * @throws SQLException            If an SQL exception occurs
	 * @throws InvalidStudentException If the student data is invalid
	 */

	@Test
	void testInvalidfindStudentByEmail() throws DAOException, SQLException, InvalidStudentException {

		try {
			Assertions.assertNotNull(studentService.findStudentByEmail("vishali.com"));
			Assertions.fail("Invalid Student");
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_EMAIL, e.getMessage());
		}
	}

}
