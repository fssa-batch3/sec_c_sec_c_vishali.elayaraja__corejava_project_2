package com.fssa.collage.admission.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.collage.admission.app.dto.StudentDeptDTO;
import com.fssa.collage.admission.app.util.ConnectionUtil;

public class StudentDepartmentDAO {

//	public static boolean StudentDepartmentCount(String student )
//			throws DaoException, SQLException, InvalidStudentException {
//		StudentValidator.validateGender(student);
//		try (Connection connection = ConnectionUtil.getConnection()) {
//
//			String query = "SELECT gender, COUNT(*) as count\r\n"
//					+ "			FROM student\r\n"
//					+ "			GROUP BY gender;";
//			try (PreparedStatement pst = connection.prepareStatement(query)) {
//				pst.setString(1, student);
//				try (ResultSet resultSet = pst.executeQuery()) {
//					if (resultSet.next()) {
//						Logger.info("id: " + resultSet.getInt(1));
//						Logger.info("DepartmentName: " + resultSet.getString(2));
//					}
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new DaoException(e);
//			}
//		}
//		return true;
// 


	public static List<StudentDeptDTO> getGenderCounts() {
		List<StudentDeptDTO> genderCounts = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT gender, COUNT(*) as count FROM student GROUP BY gender";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					String gender = resultSet.getString("gender");
					int departmentId = resultSet.getInt("department");
					int count = resultSet.getInt("count");
					genderCounts.add(new StudentDeptDTO(gender, departmentId, count));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return genderCounts;
	}
}

//package com.fssa.collage.admission.app.dao;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//        
//        try (Connection connection = DriverManager.getConnection(url, user, password)) {
//            String query = "SELECT gender, COUNT(*) as count FROM students GROUP BY gender";
//            
//            try (PreparedStatement statement = connection.prepareStatement(query);
//                 ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    String gender = resultSet.getString("gender");
//                    int count = resultSet.getInt("count");
//                    genderCounts.add(new StudentDeptDto(gender, count));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        return genderCounts;
//    }
//}
