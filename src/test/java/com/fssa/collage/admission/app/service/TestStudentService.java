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

	@Test
	void TestAddStudent() throws SQLException, InvalidStudentException {
		Student student = new Student();
		student.setRollNo(4673657);
		student.setFirstName("vishali");
		student.setLastName("elayaraja");
		student.setGender("female");
		student.setDob(LocalDate.of(2002, 04, 19));
		student.setEmailId("vish2@gmail.com");
		student.setPassword("Icodeu100%");
		student.setMobileNumber(9445456478L);
		student.setIsActive(true);

		Assertions.assertTrue(StudentService.addStudent(student));
	}

	@Test

	void testInvalidAddStudents() throws DAOException, SQLException, InvalidStudentException {

		try {
			StudentService.addStudent(null);
			Assertions.fail("Cannot add a new Student");
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
		}
	}

	@Test
	void TestUpdateStudent() throws InvalidStudentException, DAOException {
		Student student = new Student();
		student.setFirstName("vinitha");
		student.setLastName("Mohan");
		student.setGender("female");
		student.setDob(LocalDate.of(1999, 04, 19));
		student.setEmailId("vin3ir097@gmail.com");
		student.setPassword("Icodeu100%");
		student.setMobileNumber(9779445668L);
		student.setIsActive(true);
		student.setId(7);

		Assertions.assertTrue(StudentService.updateStudent(student, 33));

	}

	@Test
	void TestInvalidUpdateStudent() throws DAOException, SQLException, InvalidStudentException {
		try {
			StudentService.updateStudent(null, -1);
			Assertions.fail("Invalid Student");
		} catch (InvalidStudentException e) {

			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
		}
	}

	@Test
	void testremoveStudent() throws DAOException, InvalidStudentException {

		Assertions.assertTrue(StudentService.removeStudent(33));

	}

	@Test
	void testInvalidremoveStudent() throws DAOException {
		try {
			Assertions.assertTrue(StudentService.removeStudent(0));
			Assertions.fail("Invalid Student");
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_ROLL_NO, e.getMessage());
		}
	}

	@Test

	void testValidgetAllStudent() throws DAOException, SQLException, InvalidStudentException {

		Assertions.assertNotNull(StudentService.getAllStudent());

	}

	@Test

	void testInvalidgetAllStudent() throws DAOException, SQLException, InvalidStudentException {

		try {

			StudentService.getAllStudent();
		} catch (DAOException e) {
			Assertions.assertEquals("No Objects Found", e.getMessage());
		}
		Assertions.assertTrue(true);
	}

	@Test
	void testValidFindStudentByName() throws DAOException, SQLException, InvalidStudentException {

		Assertions.assertNotNull(StudentService.findStudentByName("vishali", "elayaraja"));

	}

	@Test
	void testInvalidfindStudentByName() throws DAOException, SQLException, InvalidStudentException {

		try {
			Assertions.assertNotNull(StudentService.findStudentByName("b"," "));
			Assertions.fail("Invalid Student");
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_NAME, e.getMessage());
		}
	}

}
