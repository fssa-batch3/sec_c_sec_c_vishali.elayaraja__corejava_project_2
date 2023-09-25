package com.fssa.collage.admission.app.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.collage.admission.app.dao.DepartmentDAO;
import com.fssa.collage.admission.app.exception.DAOException;
import com.fssa.collage.admission.app.exception.InvalidDepartmentException;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.model.Department;
import com.fssa.collage.admission.app.model.Student;
import com.fssa.collage.admission.app.validator.DepartmentValidator;

public class DepartmentService {
	

		public DepartmentService() {
//			private constructor
		}

		public static boolean addDepartment(Department department) throws InvalidDepartmentException, SQLException {
			if (DepartmentValidator.validateDepartment(department)) {
				DepartmentDAO.addDepartment(department);
			}
			return true;

		}

		public static boolean listAllDepartment() throws DAOException, SQLException {
			DepartmentDAO.getALlDepartment();
			return true;
		}

		public static boolean updateDepartment(Department department, int id) throws InvalidDepartmentException, DAOException, SQLException {
			if (DepartmentValidator.validateDepartment(department) && DepartmentValidator.validateId(id)) {
				DepartmentDAO.updateDepartment(department, id);
			}
			return true;

			
		}

		public static boolean removeDepartment(int id) throws InvalidDepartmentException, DAOException, SQLException {
			if (DepartmentValidator.validateId(id)) {
				DepartmentDAO.removeDepartment(id);
			}
			return true;
		}

		public static List<Department> findDepartmentByName(String name)
				throws InvalidDepartmentException, DAOException, SQLException, InvalidStudentException {
			List<Department> departmentList = new ArrayList<>();
			if (DepartmentValidator.validateDepartmentName(name)) {
				DepartmentDAO.findDepartmentByName(name);
			}
			return departmentList;

		}

	}


