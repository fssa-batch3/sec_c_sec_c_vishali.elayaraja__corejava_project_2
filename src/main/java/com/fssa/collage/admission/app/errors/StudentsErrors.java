package com.fssa.collage.admission.app.errors;

public interface StudentsErrors {

	public static final String INVALID_ID = "Student id cannot be 0 or lesser than 0";
	public static final String OBJECT_NOT_FOUND = "Student not found";
	public static final String INVALID_DATE = "Date cannot be in past or null";
	public static final String INVALID_STUDENT = "Student cannot be null";
	public static final String INVALID_GENDER = "Invalid Gender - valid values are male/female";
	public static final String INVALID_NAME = "Student name cannot be null or less than 2 characters";
	public static final String INVALID_DOB = "DOB cannot be null or lesser then 18";
	public static final String INVALID_EMAIL = "Email should contain @ and shouldn't have spaces";
	public static final String INVALID_MOBILE_NUMBER = "Mobile number cannot be null";
	public static final String INVALID_PASSWORD = "Minimum eight character and must contain one special character";

}
