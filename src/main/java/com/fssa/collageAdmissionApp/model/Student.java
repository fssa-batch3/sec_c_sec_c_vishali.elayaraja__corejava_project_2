package com.fssa.collageAdmissionApp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String emailId;
	private String password;
	private String gender;
	private LocalDate dob;
	private LocalDateTime created_date;
	private Boolean isActive;

	
	public Student() {
//		default constructor
	}

	public Student(int id, String firstName, String lastName, String mobileNumber, String emailId, String password,
			String gender, LocalDate dob, LocalDateTime created_date, Boolean isActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.password = password;
		this.gender = gender;
		this.dob = dob;
		this.created_date = created_date;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String string) {
		this.mobileNumber = string;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
		
	}

	public LocalDateTime getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}

	public Boolean getIsActive() {
		
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String fullName() {
		return firstName.concat(" ").concat(lastName);
	}

	@Override
	public String toString() {
		return "User [  firstName=" + firstName + ", lastName=" + lastName + ", email=" + emailId + ", password="
				+ password + ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", dob=" + dob
				+ ", created_date=" + created_date + ", isActive=" + isActive + "]";
		

	}
}
