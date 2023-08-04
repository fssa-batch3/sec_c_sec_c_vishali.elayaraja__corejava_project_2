package com.fssa.collageAdmission.service;

import java.sql.SQLException;

import com.fssa.collageAdmissionApp.dao.StudentDao;
import com.fssa.collageAdmissionApp.exception.DaoException;
import com.fssa.collageAdmissionApp.exception.InvaliMobileNumberException;
import com.fssa.collageAdmissionApp.exception.InvalidEmailException;
import com.fssa.collageAdmissionApp.exception.InvalidStudentDobException;
import com.fssa.collageAdmissionApp.exception.InvalidStudentException;
import com.fssa.collageAdmissionApp.model.Student;
import com.fssa.collageAdmissionApp.validator.StudentValidator;

public class StudentService {

	
		public static boolean addStudent(Student student) throws InvalidStudentException, InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException {
			if (StudentValidator.validateStudent(student)) {
				StudentDao.addStudent(student);
			}
			return true;
			
		}
		
		

		public static boolean readStudent() throws InvalidStudentException, DaoException, SQLException {
			StudentDao.readStudent();
			return true;
		}

		public static boolean updateStudent(Student student, int id) throws InvalidStudentException, DaoException, InvalidEmailException, InvalidStudentDobException, InvaliMobileNumberException {
			if (StudentValidator.validateStudent(student)) {
				StudentDao.updateStudent(student, id);
			}
			return true;

		}

		public static boolean removeStudent(int id) throws InvalidStudentException, DaoException {
			if (StudentValidator.validateId(id)) {
				StudentDao.removeStudent(id);
			}
			return true;

		}

		public static boolean findStudentByName(String first_name) throws InvalidStudentException, DaoException, SQLException {
			if (StudentValidator.validateFirstName(first_name)) {
				StudentDao.findStudentByName(first_name);
			}
			return true;

		}
		
		
}
