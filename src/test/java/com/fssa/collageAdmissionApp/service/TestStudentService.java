package com.fssa.collageAdmissionApp.service;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.collage.admission.app.exception.DaoException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Student;
import com.fssa.collage.admission.app.model.StudentsErrors;
import com.fssa.collage.admission.app.service.StudentService;

class TestStudentService {


		@Test
		void TestAddStudent() throws  SQLException, InvalidStudentException {
			Student student = new Student();
			student.setFirstName("nithila");
			student.setLastName("vinoth");
			student.setGender("female");
			student.setDob(LocalDate.of(1999, 04, 19));
			student.setEmailId("nithu@gmail.com");
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
			student.setEmailId("vin3ir980687@gmail.com");
			student.setPassword("Icodeu100%");
			student.setMobileNumber("9774445668");
			student.setIsActive(true);
			student.setId(6);
			
			Assertions.assertTrue(StudentService.updateStudent(student, 6));
			
		}
		
		@Test
		void TestInvalidUpdateStudent() throws DaoException, SQLException, InvalidStudentException  {
			try {
				StudentService.updateStudent(null,-1);
				Assertions.fail("Invalid Student");
			} catch (InvalidStudentException e) {
			
			Assertions.assertEquals(StudentsErrors.INVALID_STUDENT, e.getMessage());
			}
		}
		

		@Test
		void testremoveStudent() throws DaoException, InvalidStudentException {
			
			Assertions.assertTrue(StudentService.removeStudent(5));
			
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
				Assertions.assertEquals( "No Objects Found",e.getMessage());
			}
			Assertions.assertTrue(true);
		}
		
		@Test
		void testValidfindStudentByName() throws DaoException, SQLException, InvalidStudentException  {

		
			Assertions.assertTrue(StudentService.findStudentByName("vinitha"));
			

		}

		@Test
		void testInvalidfindStudentByName() throws DaoException, SQLException, InvalidStudentException  {

			try {
				Assertions.assertTrue(StudentService.findStudentByName("b"));
				Assertions.fail("Invalid Student");
			} catch (InvalidStudentException e) {
				Assertions.assertEquals(StudentsErrors.INVALID_NAME, e.getMessage());
			}
		}
		
}
