package com.fssa.collage.admission.app.service;

import java.sql.SQLException;

import com.fssa.collage.admission.app.dao.StudentDao;
import com.fssa.collage.admission.app.exception.DaoException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Student;
import com.fssa.collage.admission.app.validator.StudentValidator;

public class StudentService {

	private StudentService() {
//		private constructor
	}

	public static boolean addStudent(Student student) throws InvalidStudentException {
		if (StudentValidator.validateStudent(student)) {
			StudentDao.addStudent(student);
		}
		return true;

	}

	public static boolean readStudent() throws DaoException, SQLException {
		StudentDao.readStudent();
		return true;
	}

	public static boolean updateStudent(Student student, int id) throws InvalidStudentException, DaoException {
		if (StudentValidator.validateStudent(student) && StudentValidator.validateId(id)) {
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

	public static boolean findStudentByName(String firstName)
			throws InvalidStudentException, DaoException, SQLException {
		if (StudentValidator.validateFirstName(firstName)) {
			StudentDao.findStudentByName(firstName);
		}
		return true;

	}

}
