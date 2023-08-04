package com.fssa.collageAdmissionApp.exception;


public class InvalidStudentException extends Exception {

	private static final long serialVersionUID = 1L;

		public InvalidStudentException(String msg) {
			super(msg);
		}

		public InvalidStudentException(Throwable te) {
			super(te);
		}

		public InvalidStudentException(String msg, Throwable te) {
			super(msg, te);
		}
	}


