package com.fssa.collage.admission.app.validator;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.collage.admission.app.errors.StudentsErrors;
import com.fssa.collage.admission.app.exception.DAOException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Student;

class TestStudentValidator {

	@Test
	void testNullStudent() throws InvalidStudentException {
		try {
			Student student = null;
			StudentValidator.validateStudent(student);
		} catch (InvalidStudentException ex) {
			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, ex.getMessage());
		}
	}

	@Test
	void testValidateStudent() throws InvalidStudentException {
		Student student = new Student();
		student.setDob(LocalDate.of(2004, 10, 30));
		student.setApplicationNo("ABE234");
		student.setEmailId("yazhini@gmail.com");
		student.setFirstName("yazhini");
		student.setGender("Female");
		student.setLastName("Elayaraja");
		student.setStatus("pending");
		student.setMobileNumber(8778345632L);
		student.setPassword("password12");
		Assertions.assertTrue(StudentValidator.validateStudent(student));

	}

	@Test
	void testInvalidStudent() throws DAOException, SQLException, InvalidStudentException {
		try {
			Assertions.assertTrue(StudentValidator.validateStudent(null));
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
		}

	}

	@Test
	void testValidFirstName() throws InvalidStudentException {
		String name = "Vishali";
		Assertions.assertTrue(StudentValidator.validateFirstName(name));
	}

	@Test
	void testInvalidFirstName() {
		String name = "ab";
		try {
			Assertions.assertTrue(StudentValidator.validateFirstName(name));
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_NAME, e.getMessage());
		}
	}

	@Test
	void testValidLastName() throws InvalidStudentException {
		String name = "Elayaraja";
		Assertions.assertTrue(StudentValidator.validateLastName(name));
	}

	@Test
	void testInvalidLastName() {
		String name = "12e";
		try {
			Assertions.assertTrue(StudentValidator.validateLastName(name));
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_NAME, e.getMessage());
		}
	}

	@Test
	void testValidId() throws InvalidStudentException {
		Assertions.assertTrue(StudentValidator.validateId(2));
	}

	@Test
	void testInvalidId() {
		try {
			Assertions.assertTrue(StudentValidator.validateId(-1));
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_ID, e.getMessage());
		}
	}

	@Test

	void testValidEmail() throws InvalidStudentException {
		String email = "vishali@gmail.com";
		Assertions.assertTrue(StudentValidator.validateEmail(email));
	}

	@Test
	void testInvalidEmail() {
		String email = "vishali.com";
		try {
			Assertions.assertTrue(StudentValidator.validateEmail(email));
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_EMAIL, e.getMessage());
		}
	}

	@Test
	void testValidPassword() throws InvalidStudentException {
		String password = "Vishali@123";
		Assertions.assertTrue(StudentValidator.validatePassword(password));
	}

	@Test
	void testInvalidPassword() {
		String password = "abc12";
		try {
			Assertions.assertTrue(StudentValidator.validatePassword(password));
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_PASSWORD, e.getMessage());
		}
	}

	@Test
	void testValidGender() throws InvalidStudentException {
		String gender = "Male";
		Assertions.assertTrue(StudentValidator.validateGender(gender));
	}

	@Test
	void testInvalidGender() {
		String gender = "M";
		try {
			Assertions.assertTrue(StudentValidator.validateGender(gender));
		} catch (InvalidStudentException e) {

			Assertions.assertEquals(StudentsErrors.INVALID_GENDER, e.getMessage());
		}
	}

	@Test

	void testValidDateOfBirth() throws InvalidStudentException {
		LocalDate dob = LocalDate.of(2004, 10, 30);
		Assertions.assertTrue(StudentValidator.validateDateOfBirth(dob));
	}

	@Test
	void testInvalidDateOfBirth() {
		LocalDate dob = LocalDate.of(2006, 10, 30);
		try {
			Assertions.assertTrue(StudentValidator.validateDateOfBirth(dob));
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_DOB, e.getMessage());
		}

	}

	@Test
	void testValidMobileNumber() throws InvalidStudentException {
		long number = 8778567632L;
		Assertions.assertTrue(StudentValidator.validateMobileNumber(number));
	}

	@Test
	void testInvalidMobileNumber() {
		long number = 123000L;
		try {
			Assertions.assertTrue(StudentValidator.validateMobileNumber(number));
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_MOBILE_NUMBER, e.getMessage());
		}
	}
}
