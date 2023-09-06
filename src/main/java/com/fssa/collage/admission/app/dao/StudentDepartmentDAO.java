package com.fssa.collage.admission.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.collage.admission.app.dto.StudentDeptDTO;
import com.fssa.collage.admission.app.model.Student;
import com.fssa.collage.admission.app.util.ConnectionUtil;

public class StudentDepartmentDAO {

	public static boolean AddStudentDept(Student student, String departmentName) throws SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO student_class (student_id,department_id) VALUES (?,?)";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, getStudentIdByEmail(student.getEmailId()));
				pst.setInt(2, getDepartmentIdByName(departmentName));
				int row = pst.executeUpdate();
				return (row > 0);
			}
		}

	}

	public static int getStudentIdByEmail(String email) throws SQLException {
		int id = 0;
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT id FROM students WHERE email = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, email);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
						id = resultSet.getInt("id");
					}
				}
			}
		}

		return id;
	}

	public static int getDepartmentIdByName(String departmentName) throws SQLException {
		int id = 0;
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT id FROM departments WHERE dept_name = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, departmentName);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
						id = resultSet.getInt("id");
					}
				}
			}
		}
		return id;
	}

	public static List<StudentDeptDTO> getGenderCounts() {
		List<StudentDeptDTO> genderCounts = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT gender, COUNT(*) as count FROM student GROUP BY gender";

			try (PreparedStatement statement = connection.prepareStatement(query)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						String gender = resultSet.getString("gender");
						int departmentId = resultSet.getInt("department");
						int count = resultSet.getInt("count");
						genderCounts.add(new StudentDeptDTO(gender, departmentId, count));
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return genderCounts;
	}
}
