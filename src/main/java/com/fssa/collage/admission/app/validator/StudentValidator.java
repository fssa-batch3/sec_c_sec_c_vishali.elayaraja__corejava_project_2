package com.fssa.collage.admission.app.validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import com.fssa.collage.admission.app.errors.StudentsErrors;
import com.fssa.collage.admission.app.exception.InvalidStudentException;
import com.fssa.collage.admission.app.exception.ValidationException;
import com.fssa.collage.admission.app.model.Student;

public class StudentValidator {

	private StudentValidator() {

	}

	public static boolean validateStudent(Student student) throws InvalidStudentException {
		if (student == null) {
			throw new InvalidStudentException(StudentsErrors.INVALID_STUDENT);
		}
		validateFirstName(student.getFirstName());
		validateLastName(student.getLastName());
		validateEmail(student.getEmailId());
		validatePassword(student.getPassword());
		validateGender(student.getGender());
		validateDateOfBirth(student.getDob());
		validateMobileNumber(student.getMobileNumber());

		return true;

	}

	public static boolean validateStudentApplyForm(Student student) throws InvalidStudentException {
		if (student == null) {
			throw new InvalidStudentException(StudentsErrors.INVALID_STUDENT);
		}
		validateFirstName(student.getFirstName());
		validateLastName(student.getLastName());
		validateEmail(student.getEmailId());
		validateGender(student.getGender());
		validateDateOfBirth(student.getDob());
		validateMobileNumber(student.getMobileNumber());

		return true;

	}

	/**
	 * first name must contain only aphabets.length can be between 2 - 30 characters
	 * 
	 * @param firstName
	 * @return
	 * @throws InvalidStudentException
	 */
	public static boolean validateFirstName(String firstName) throws InvalidStudentException {
		String firstNameLowercase = firstName.toLowerCase();
		String regex = "^[A-Za-z]{2,30}$";
		boolean matches = Pattern.compile(regex).matcher(firstName).matches();
		if (firstNameLowercase == null || !matches) {
			throw new InvalidStudentException(StudentsErrors.INVALID_NAME);

		}
		return true;

	}

	public static boolean validateLastName(String LastName) throws InvalidStudentException {
		String LastNameLowercase = LastName.toLowerCase();
		String regex = "^[A-Za-z]{2,30}$";
		boolean matches = Pattern.compile(regex).matcher(LastName).matches();
		if (LastNameLowercase == null || !matches) {
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
		String regex = "^[A-Za-z0-9+_.-]+@[^\\s]+\\.(.+)$";
		boolean matches = Pattern.compile(regex).matcher(emailId).matches();
		if (matches) {
			return true;

		} else {
			throw new InvalidStudentException(StudentsErrors.INVALID_EMAIL);
		}
	}

	public static boolean validateGender(String gender) throws InvalidStudentException {
		String genderLowerCase = gender.toLowerCase().trim();
		if (genderLowerCase.equals("male") || genderLowerCase.equals("female") || genderLowerCase.equals("others")) {
			return true;
		} else {
			throw new InvalidStudentException(StudentsErrors.INVALID_GENDER);
		}
	}

	public static boolean validatePassword(String password) throws InvalidStudentException {
//		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[a-zA-Z]).{8,}$";
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
		Period age = Period.between(dateOfBirth, currentDate);
		int years = age.getYears();
		if (years < 17) {
			throw new InvalidStudentException(StudentsErrors.INVALID_DOB);

		}
		return true;
	}

	public static boolean validateMobileNumber(long number) throws InvalidStudentException {
		String regex = "^[6789]\\d{9}$";
		String numberStr = String.valueOf(number);
		boolean matches = Pattern.compile(regex).matcher(numberStr).matches();
		if (matches) {
			return true;
		} else {
			throw new InvalidStudentException(StudentsErrors.INVALID_MOBILE_NUMBER);
		}
	}

	public static boolean validateDatePattern(LocalDate createdDate) throws ValidationException {
		LocalDate currentDate = LocalDate.now();
		if (createdDate == null) {
			throw new ValidationException(StudentsErrors.INVALID_DATE);
		}
		if (createdDate.isBefore(currentDate)) {
			throw new ValidationException(StudentsErrors.INVALID_DATE);

		}
		return true;

	}

}