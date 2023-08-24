package com.fssa.collage.admission.app.dto;

public class StudentDeptDTO {
	private String gender;
	private int departmentId;
	private int count;

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public StudentDeptDTO(String gender, int departmentId, int count) {
		super();
		this.gender = gender;
		this.departmentId = departmentId;
		this.count = count;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StudentDeptDto [gender=" + gender + ", count=" + count + "]";
	}
}
