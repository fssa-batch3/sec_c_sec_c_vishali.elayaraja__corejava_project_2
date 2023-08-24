package com.fssa.collage.admission.app.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.collage.admission.app.errors.DepartmentErrors;
import com.fssa.collage.admission.app.exception.DAOException;
import com.fssa.collage.admission.app.exception.InvalidDepartmentException;
import com.fssa.collage.admission.app.model.Department;
import com.fssa.collage.admission.app.service.DepartmentService;

class TestDepartmentService {

	@Test
	void TestAddDepartment() throws SQLException, InvalidDepartmentException {
		Department department = new Department();
		department.setId(106);
		department.setName("Btech");

		Assertions.assertTrue(DepartmentService.addDepartment(department));
	}

	@Test

	void testInvalidAddDepartment() throws DAOException, SQLException, InvalidDepartmentException {

		try {
			DepartmentService.addDepartment(null);
			Assertions.fail("Cannot add a new Department");
		} catch (InvalidDepartmentException e) {
			Assertions.assertEquals(DepartmentErrors.INVALID_DEPARTMENT, e.getMessage());
		}
	}

	@Test
	void TestUpdateDepartment() throws InvalidDepartmentException, DAOException, SQLException {
		Department department = new Department();
		department.setId(101);
		department.setName("BioTech");

		Assertions.assertTrue(DepartmentService.updateDepartment(department, 101));

	}

	@Test
	void TestInvalidUpdateStudent() throws DAOException, SQLException, InvalidDepartmentException {
		try {
			DepartmentService.updateDepartment(null, -1);
			Assertions.fail("Invalid Department");
		} catch (InvalidDepartmentException e) {

			Assertions.assertEquals(DepartmentErrors.INVALID_DEPARTMENT, e.getMessage());
		}
	}

	@Test
	void testremoveDepartment() throws DAOException, InvalidDepartmentException, SQLException {

		Assertions.assertTrue(DepartmentService.removeDepartment(106));

	}

	@Test
	void testInvalidremoveDepartment() throws DAOException, SQLException {
		try {
			Assertions.assertTrue(DepartmentService.removeDepartment(0));
			Assertions.fail("Invalid Department");
		} catch (InvalidDepartmentException e) {
			Assertions.assertEquals(DepartmentErrors.INVALID_ID, e.getMessage());
		}
	}

	@Test

	void testValidReadDepartment() throws DAOException, SQLException, InvalidDepartmentException {

		Assertions.assertTrue(DepartmentService.readDepartment());

	}

	@Test

	void testInvalidReadDepartment() throws DAOException, SQLException, InvalidDepartmentException {

		try {

			DepartmentService.readDepartment();
		} catch (DAOException e) {
			Assertions.assertEquals("No Objects Found", e.getMessage());
		}
		Assertions.assertTrue(true);
	}

	@Test
	void testValidfindDepartmentByName() throws DAOException, SQLException, InvalidDepartmentException {

		Assertions.assertTrue(DepartmentService.findDepartmentByName("cse"));

	}

	@Test
	void testInvalidfindDepartmentByName() throws DAOException, SQLException, InvalidDepartmentException {

		try {
			Assertions.assertTrue(DepartmentService.findDepartmentByName("b"));
			Assertions.fail("Invalid Department");
		} catch (InvalidDepartmentException e) {
			Assertions.assertEquals(DepartmentErrors.INVALID_NAME, e.getMessage());
		}
	}

}
