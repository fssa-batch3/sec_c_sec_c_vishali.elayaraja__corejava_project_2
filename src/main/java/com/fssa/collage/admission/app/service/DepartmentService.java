package com.fssa.collage.admission.app.service;

import java.sql.SQLException;

import com.fssa.collage.admission.app.dao.DepartmentDAO;
import com.fssa.collage.admission.app.exception.DAOException;
import com.fssa.collage.admission.app.exception.InvalidDepartmentException;
import com.fssa.collage.admission.app.model.Department;
import com.fssa.collage.admission.app.validator.DepartmentValidator;

public class DepartmentService {
	

		private DepartmentService() {
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

		public static boolean findDepartmentByName(String name)
				throws InvalidDepartmentException, DAOException, SQLException {
			if (DepartmentValidator.validateDepartmentName(name)) {
				DepartmentDAO.findDepartmentByName(name);
			}
			return true;

		}

	}


