package com.fssa.collageAdmissionApp.validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;
import com.fssa.collageAdmissionApp.exception.InvalidStudentException;
import com.fssa.collageAdmissionApp.exception.ValidationException;
import com.fssa.collageAdmissionApp.model.Student;
import com.fssa.collageAdmissionApp.model.StudentsErrors;

public class StudentValidator {

	public static boolean validateStudent(Student student) throws InvalidStudentException {
		if (student == null) {
			throw new InvalidStudentException(StudentsErrors.INVALID_STUDENT);
		}
//		try {
			validateFirstName(student.getFirstName());
			validateLastName(student.getLastName());
			validateEmail(student.getEmailId());
			validatePassword(student.getPassword());
			validateGender(student.getGender());
			validateDateOfBirth(student.getDob());
			validateMobileNumber(student.getMobileNumber());
			
//			
//		} catch (InvalidStudentException e) {
//			throw new InvalidStudentException(StudentsErrors.INVALID_STUDENT);
//		}

		return true;
		
	}

	public static boolean validateFirstName(String firstName) throws InvalidStudentException {
		String regex =  "^[A-Za-z]{2,30}$";
		boolean matches = Pattern.compile(regex).matcher(firstName).matches();
		if (firstName == null || !matches) {
			throw new InvalidStudentException(StudentsErrors.INVALID_NAME);

		}
		return true;
		
	}
	
	public static boolean validateLastName(String LastName) throws InvalidStudentException {
		String regex =  "^[A-Za-z]{2,30}$";
		boolean matches = Pattern.compile(regex).matcher(LastName).matches();
		if (LastName == null || !matches) {
			throw new InvalidStudentException(StudentsErrors.INVALID_NAME);

		}
		return true;
	}

	public static boolean validateId(int id) throws InvalidStudentException {
		if (id <= 0) {
			throw new InvalidStudentException(StudentsErrors.INVALID_ID);
		}
		return true;
	}

	public static boolean validateEmail(String emailId) throws InvalidStudentException {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		boolean matches = Pattern.compile(regex).matcher(emailId).matches();
		if (matches) {
			return true;
			
		} else {
			throw new InvalidStudentException(StudentsErrors.INVALID_EMAIL);
		}
	}
	// TODO to add gender : others.

	public static boolean validateGender(String gender) throws InvalidStudentException {
		String genderLowerCase = gender.toLowerCase();
		if (genderLowerCase.equals("male") || genderLowerCase.equals("female")) {
			return true;
		} else {
			throw new InvalidStudentException(StudentsErrors.INVALID_GENDER);
		}
	}

	public static boolean validatePassword(String password) throws InvalidStudentException {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
		boolean matches = Pattern.compile(regex).matcher(password).matches();
		if (matches) {
			return true;
		} else {
			throw new InvalidStudentException(StudentsErrors.INVALID_PASSWORD);
		}
	}


	public static boolean validateDateOfBirth(LocalDate dob) throws InvalidStudentException {
		
		
		LocalDate dateOfBirth = dob;
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(dateOfBirth, 
        		currentDate);
        int years = age.getYears();
        if (years < 17) {
        	throw new InvalidStudentException(StudentsErrors.INVALID_DOB);

        }
        return true;
	}

	public static boolean validateMobileNumber(String mobileNumber) throws InvalidStudentException {
		String regex = "^[6789]\\d{9}$";
		boolean matches = Pattern.compile(regex).matcher(mobileNumber).matches();
		if (matches) {
			return true;
		} else {
			throw new InvalidStudentException(StudentsErrors.INVALID_MOBILE_NUMBER);
		}
	}
	
	public static boolean ValidateDate(LocalDate createdDate)throws ValidationException{
		LocalDate currentDate = LocalDate.now();
		if(createdDate==null) {
			throw new ValidationException(StudentsErrors.INVALID_DATE);
		}
		if(createdDate.isBefore(currentDate)) {
			throw new ValidationException(StudentsErrors.INVALID_DATE);
				
		}
		return true;
		
	}
	
}