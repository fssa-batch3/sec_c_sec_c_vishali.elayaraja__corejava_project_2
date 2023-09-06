package com.fssa.collage.admission.app.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fssa.collage.admission.app.dao.StudentDAO;
import com.fssa.collage.admission.app.dao.StudentDepartmentDAO;
import com.fssa.collage.admission.app.exception.DAOException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Student;
import com.fssa.collage.admission.app.validator.StudentValidator;

public class StudentService {

	public StudentService() {
//		private constructor
	}

	public static boolean addStudent(Student student, String departmentName)
			throws InvalidStudentException, SQLException, DAOException {

		if (StudentValidator.validateStudent(student) && (!StudentDAO.checkStudentExists(student.getEmailId()))) {

			StudentDAO.addStudent(student);

			StudentDepartmentDAO.AddStudentDept(student, departmentName);

		}
		return true;

	}

	public static List<Student> getAllStudent() throws DAOException, SQLException {
		List<Student> studentList = new ArrayList<>();
		studentList = StudentDAO.getAllStudent();

		return studentList;
	}

	public static boolean updateStudent(Student student) throws InvalidStudentException, DAOException {
		if (StudentValidator.validateStudent(student)) {
			StudentDAO.updateStudent(student);
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

	public static Student findStudentByEmail(String email) throws InvalidStudentException, DAOException, SQLException {
		Student studentList = null;
		if (StudentValidator.validateEmail(email)) {
			studentList = StudentDAO.findStudentByEmail(email);

		}
		return studentList;
	}

	public static Student findStudentById(int id) throws InvalidStudentException, DAOException, SQLException {

		return StudentDAO.findStudentById(id);

	}

}
