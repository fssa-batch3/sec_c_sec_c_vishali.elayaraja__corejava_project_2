package com.fssa.collage.AdmissionApp.validator;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.collageAdmission.service.StudentService;
import com.fssa.collageAdmissionApp.dao.StudentDao;
import com.fssa.collageAdmissionApp.exception.DaoException;
import com.fssa.collageAdmissionApp.exception.InvaliMobileNumberException;
import com.fssa.collageAdmissionApp.exception.InvalidEmailException;
import com.fssa.collageAdmissionApp.exception.InvalidStudentDobException;
import com.fssa.collageAdmissionApp.exception.InvalidStudentException;
import com.fssa.collageAdmissionApp.model.Student;
import com.fssa.collageAdmissionApp.model.StudentsErrors;

public class TestStudentDao {

	
	
	
	@Test
	void TestAddStudent() throws DaoException, SQLException, InvalidStudentException, InvalidEmailException,
			InvalidStudentDobException, InvaliMobileNumberException {
		Student student = new Student();
		student.setFirstName("Yazhini");
		student.setLastName("Elayaraja");
		student.setGender("female");
		student.setDob(LocalDate.of(2004, 10, 30));
		student.setEmailId("yazh303@gmail.com");
		student.setPassword("Icodeu100%");
		student.setMobileNumber("9443456678");
		student.setIsActive(true);

		Assertions.assertTrue(StudentDao.addStudent(student));
	}

	@Test
	
	void testInvalidAddStudents() throws InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException  {

		try {
			Assertions.assertTrue(StudentDao.addStudent(null));
			Assertions.fail("Cannot add a new Student");
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
		}
	}
	
	@Test
	void TestUpdateStudent() throws DaoException, InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException, InvalidStudentException {
		Student student = new Student();
		student.setFirstName("Vishali");
		student.setLastName("Elayaraja");
		student.setGender("female");
		student.setDob(LocalDate.of(2004, 10, 30));
		student.setEmailId("vishal5920@gmail.com");
		student.setPassword("Icodeu100%");
		student.setMobileNumber("9774445668");
		student.setIsActive(true);
		
		student.setId(1);
		
		Assertions.assertTrue(StudentDao.updateStudent(student, 4));
		
	}
	
	@Test
	void TestInvalidUpdateStudent() throws DaoException, InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException {
		try {
			Student student = new Student();
			Assertions.assertTrue(StudentDao.updateStudent(student,-1));
			Assertions.fail("Invalid Student");
		} catch (InvalidStudentException e) {
		
		Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
		}
	}
	
	
	
	@Test
	void testremoveStudent() throws DaoException, InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException, InvalidStudentException {
		
		Assertions.assertTrue(StudentDao.removeStudent(15));
		
	}
	
	@Test
	void testInvalidremoveStudent() throws DaoException  {
		try {
			Assertions.assertTrue(StudentDao.removeStudent(0));
			Assertions.fail("Invalid Student");
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_ID, e.getMessage());
		}
	}
	
	@Test
	void testValidfindStudentByName() throws DaoException, SQLException, InvalidStudentException  {

	
		Assertions.assertTrue(StudentDao.findStudentByName(" "));
		

	}

	@Test
	void testInvalidfindStudentByName() throws DaoException, SQLException, InvalidStudentException  {

	
//		Assertions.assertTrue(StudentDao.findStudentByName(""));
		try {
			Assertions.assertTrue(StudentDao.findStudentByName("ab"));
			Assertions.fail("Invalid Student");
		} catch (InvalidStudentException e) {
			Assertions.assertEquals(StudentsErrors.INVALID_NAME, e.getMessage());
		}
	}
	
	@Test
	
	void testValidReadStudent() throws DaoException, SQLException {
		
		Student student= new Student();
		Assertions.assertTrue(StudentDao.readStudent());
		
	}
	
	@Test
	
	void testInvalidReadStudent() throws DaoException, SQLException {
		
		Student student= new Student();
		try {
			StudentDao.readStudent();
		}
		catch(DaoException e) {
			Assertions.assertEquals(e.getMessage(), "No Objects Found");
		}
		Assertions.assertTrue(true);
	}
}
