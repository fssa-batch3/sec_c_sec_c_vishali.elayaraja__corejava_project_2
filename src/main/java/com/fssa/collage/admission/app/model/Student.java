package com.fssa.collage.admission.app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private long mobileNumber;
	private String emailId;
	private String password;
	private String gender;
	private LocalDate dob;
	private LocalDateTime created_date;
	private String Status;
	private String applicationNo;
	private String department;
	private boolean isActive;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Student() {
//		default constructor
	}

	public Student(int id, String applicationNo, String firstName, String lastName, long mobileNumber, String emailId,
			String password, String gender, LocalDate dob, LocalDateTime created_date, String Status) {
		super();
		this.id = id;
		this.applicationNo = applicationNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.password = password;
		this.gender = gender;
		this.dob = dob;
		this.created_date = created_date;
		this.Status = Status;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	roll no should contain 7 digits (1111111 - 9999999)
	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String rollNo) {
		this.applicationNo = rollNo;
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

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long string) {
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

	public String getStatus() {

		return Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}

	public String fullName() {
		return firstName.concat(" ").concat(lastName);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + ", emailId=" + emailId + ", password=" + password + ", gender=" + gender + ", dob="
				+ dob + ", created_date=" + created_date + ", Status=" + Status + ", applicationNo=" + applicationNo
				+ ", department=" + department + ", isActive=" + isActive + "]\n";
	}
}
