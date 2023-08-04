package com.fssa.collageAdmissionApp.exception;


	
	public class InvalidStudentDobException  extends Exception {

		private static final long serialVersionUID = 1L;

			public InvalidStudentDobException(String msg) {
				super(msg);
			}

			public InvalidStudentDobException(Throwable te) {
				super(te);
			}

			public InvalidStudentDobException(String msg, Throwable te) {
				super(msg, te);
			}
}

