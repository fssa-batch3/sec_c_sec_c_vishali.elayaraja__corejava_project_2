package com.fssa.collage.admission.app.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.collage.admission.app.dao.StudentDAO;
import com.fssa.collage.admission.app.exception.DAOException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Student;
import com.fssa.collage.admission.app.validator.StudentValidator;

public class StudentService {

	private StudentService() {
//		private constructor
	}

	public static boolean addStudent(Student student) throws InvalidStudentException {
		if (StudentValidator.validateStudent(student)) {
			StudentDAO.addStudent(student);
		}
		return true;

	}

	public static List<Student> getAllStudent() throws DAOException, SQLException {
		List<Student> studentList = new ArrayList<>();
		studentList = StudentDAO.getAllStudent();
		System.out.println(studentList);
		return studentList;
	}

	public static boolean updateStudent(Student student, int id) throws InvalidStudentException, DAOException {
		if (StudentValidator.validateStudent(student) && StudentValidator.validateId(id)) {
			StudentDAO.updateStudent(student, id);
		}
		return true;

	}

	public static boolean removeStudent(int id) throws InvalidStudentException, DAOException {
		if (StudentValidator.validateId(id)) {
			StudentDAO.removeStudent(id);
		}
		return true;

	}

	public static List<Student> findStudentByName(String firstName, String lastname)
			throws InvalidStudentException, DAOException, SQLException {
		List<Student> studentList = new ArrayList<>();
		if (StudentValidator.validateFirstName(firstName) && StudentValidator.validateLastName(lastname)) {
			studentList = StudentDAO.findStudentByName(firstName, lastname);

		}
		return studentList;
	}

}
