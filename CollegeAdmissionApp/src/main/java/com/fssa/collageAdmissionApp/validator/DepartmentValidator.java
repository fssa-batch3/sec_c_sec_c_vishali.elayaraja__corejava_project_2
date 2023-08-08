package com.fssa.collageAdmissionApp.validator;

import java.sql.SQLException;
import com.fssa.collageAdmissionApp.exception.InvalidDepartmentException;
import com.fssa.collageAdmissionApp.model.Department;
import com.fssa.collageAdmissionApp.model.DepartmentErrors;


public class DepartmentValidator {

	
	
	public static boolean validateDepartment(Department department) throws InvalidDepartmentException , SQLException{
		if (department == null) {
			throw new InvalidDepartmentException(DepartmentErrors.INVALID_DEPARTMENT);
		}
		try {
			validateDepartmentName(department.getName());
			validateDepartmentName(department.getName());
			validateId(department.getId());
			
			
		} catch (InvalidDepartmentException e) {
			throw new InvalidDepartmentException(DepartmentErrors.INVALID_DEPARTMENT);
		}

		return true;
	}
	
	public static boolean validateDepartmentName(String name) throws InvalidDepartmentException,SQLException {
		if (name == null || name.length() < 3 || name.trim().length() <= 0) {
			throw new InvalidDepartmentException(DepartmentErrors.INVALID_NAME);

		}
		return true;
		
	}
	
	public static boolean validateId(int id) throws InvalidDepartmentException,SQLException {
		if (id <= 0) {
			throw new InvalidDepartmentException(DepartmentErrors.INVALID_ID);
		}
		return true;
	}
}
