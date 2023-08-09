package com.fssa.collage.AdmissionApp.validator;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.collageAdmission.service.StudentService;
import com.fssa.collageAdmissionApp.exception.DaoException;
import com.fssa.collageAdmissionApp.exception.InvalidStudentException;
import com.fssa.collageAdmissionApp.model.Student;
import com.fssa.collageAdmissionApp.model.StudentsErrors;

public class TestStudentService {


		@Test
		void TestAddStudent() throws  SQLException, InvalidStudentException {
			Student student = new Student();
			student.setFirstName("viyan");
			student.setLastName("Mohan Kumar");
			student.setGender("female");
			student.setDob(LocalDate.of(1999, 04, 19));
			student.setEmailId("vini987@gmail.com");
			student.setPassword("Icodeu100%");
			student.setMobileNumber("9443456678");
			student.setIsActive(true);

			Assertions.assertTrue(StudentService.addStudent(student));
		}

		@Test
		
		void testInvalidAddStudents() throws DaoException, SQLException, InvalidStudentException {

			try {
				StudentService.addStudent(null);
				Assertions.fail("Cannot add a new Student");
			} catch (InvalidStudentException e) {
				Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
			}
		}
		
		@Test
		void TestUpdateStudent() throws  InvalidStudentException, DaoException {
			Student student = new Student();
			student.setFirstName("vinitha");
			student.setLastName("Mohan");
			student.setGender("female");
			student.setDob(LocalDate.of(1999, 04, 19));
			student.setEmailId("vini987@gmail.com");
			student.setPassword("Icodeu100%");
			student.setMobileNumber("9774445668");
			student.setIsActive(true);
			student.setId(1);
			
			Assertions.assertTrue(StudentService.updateStudent(student, 1));
			
		}
		
		@Test
		void TestInvalidUpdateStudent() throws DaoException, SQLException, InvalidStudentException  {
			try {
				Student student = new Student();
				StudentService.updateStudent(student,-1);
				Assertions.fail("Invalid Student");
			} catch (InvalidStudentException e) {
			
			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
			}
		}
		

		@Test
		void testremoveStudent() throws DaoException, InvalidStudentException {
			
			Assertions.assertTrue(StudentService.removeStudent(2));
			
		}
		
		@Test
		void testInvalidremoveStudent() throws DaoException  {
			try {
				Assertions.assertTrue(StudentService.removeStudent(0));
				Assertions.fail("Invalid Student");
			} catch (InvalidStudentException e) {
				Assertions.assertEquals(StudentsErrors.INVALID_ID, e.getMessage());
			}
		}
		
		@Test
		
		void testValidReadStudent() throws DaoException, SQLException, InvalidStudentException {
			
			Assertions.assertTrue(StudentService.readStudent());
			
		}
		
		@Test
		
		void testInvalidReadStudent() throws DaoException, SQLException, InvalidStudentException {
			
			try {
				
				StudentService.readStudent();
			}
			catch(DaoException e) {
				Assertions.assertEquals(e.getMessage(), "No Objects Found");
			}
			Assertions.assertTrue(true);
		}
		
		@Test
		void testValidfindStudentByName() throws DaoException, SQLException, InvalidStudentException  {

		
			Assertions.assertTrue(StudentService.findStudentByName("pranaw"));
			

		}

		@Test
		void testInvalidfindStudentByName() throws DaoException, SQLException, InvalidStudentException  {

		
//			Assertions.assertTrue(StudentDao.findStudentByName(""));
			try {
				Assertions.assertTrue(StudentService.findStudentByName("ab"));
				Assertions.fail("Invalid Student");
			} catch (InvalidStudentException e) {
				Assertions.assertEquals(StudentsErrors.INVALID_NAME, e.getMessage());
			}
		}
		
}
