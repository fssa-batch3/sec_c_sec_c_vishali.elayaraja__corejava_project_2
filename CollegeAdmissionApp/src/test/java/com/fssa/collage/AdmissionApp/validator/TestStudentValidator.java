package com.fssa.collage.AdmissionApp.validator;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.collageAdmissionApp.exception.InvaliMobileNumberException;
import com.fssa.collageAdmissionApp.exception.InvalidEmailException;
import com.fssa.collageAdmissionApp.exception.InvalidStudentDobException;
import com.fssa.collageAdmissionApp.exception.InvalidStudentException;
import com.fssa.collageAdmissionApp.model.Student;
import com.fssa.collageAdmissionApp.model.StudentsErrors;
import com.fssa.collageAdmissionApp.validator.StudentValidator;

public class TestStudentValidator {

	@Test
	void testNullStudent() throws InvalidStudentException, InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException {
		Student student = null;
		Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, StudentValidator.validateStudent(student));
	}
	@Test
	void testObjectStudent() throws InvalidStudentException, InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException {
		Student student = new Student();
		Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, StudentValidator.validateStudent(student));
	}
	
	@Test
	void testValidateStudent() throws InvalidStudentException, InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException {
		Student student = new Student();
		student.setDob(LocalDate.of(2004, 10, 30));
		student.setEmailId("abecd@gmail.com");
		student.setFirstName("Vishali");
		student.setGender("Female");
		student.setLastName("Elayaraja");
		student.setIsActive(true);
		student.setMobileNumber("8778642632");
		student.setPassword("Vishali@123");
		Assertions.assertTrue(StudentValidator.validateStudent(student));
		
		
	}
	@Test
	void testInvalidValidStudent() throws InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException {
		Student student = new Student();
		try {
			Assertions.assertTrue(StudentValidator.validateStudent(student));
		}catch(InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
		}
		
	}
	@Test
	void testValidFirstName() throws InvalidStudentException{
		String name = "Vishali";
		Assertions.assertTrue(StudentValidator.validateFirstName(name));
	}
	@Test
	void testInvalidFirstName(){
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
		String name = null;
		try {
			Assertions.assertTrue(StudentValidator.validateLastName(name));
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_NAME, e.getMessage());
		}
	}
	@Test
	void testValidId() throws InvalidStudentException {
		Assertions.assertTrue(StudentValidator.validateId(12));
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
	void testValidEmail() throws InvalidEmailException {
		String email = "vishali@gmail.com";
		Assertions.assertTrue(StudentValidator.validateEmail(email));
	}
	@Test
	void testInvalidEmail() {
		String email = "vishali.com";
		try {
			Assertions.assertTrue(StudentValidator.validateEmail(email));
		}catch(InvalidEmailException e) {
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
		}catch(InvalidStudentException e) {
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
		}catch(InvalidStudentException e) {
			
			Assertions.assertEquals(StudentsErrors.INVALID_GENDER, e.getMessage());
		}
	}
	@Test
	
	void testValidDateOfBirth() throws InvalidStudentDobException {
		LocalDate dob = LocalDate.of(2004, 10, 30);
		Assertions.assertTrue(StudentValidator.validateDateOfBirth(dob));
	}
	@Test
	void testInvalidDateOfBirth() {
		LocalDate dob = LocalDate.of(2006, 10, 30);
		try {
			Assertions.assertTrue(StudentValidator.validateDateOfBirth(dob));
		}catch(InvalidStudentDobException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_DOB, e.getMessage());
		}
		
	}
	@Test
	void testValidMobileNumber() throws InvaliMobileNumberException {
		String number = "8778642632";
		Assertions.assertTrue(StudentValidator.validateMobileNumber(number));
	}
	@Test
	void testInvalidMobileNumber() {
		String number = "12300000000000";
		try {
			Assertions.assertTrue(StudentValidator.validateMobileNumber(number));
		}catch(InvaliMobileNumberException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_MOBILE_NUMBER, e.getMessage());
		}
	}
}
