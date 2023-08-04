package com.fssa.collageAdmissionApp.exception;



	public class InvalidDepartmentException extends Exception {

		private static final long serialVersionUID = 1L;

			public InvalidDepartmentException(String msg) {
				super(msg);
			}

			public InvalidDepartmentException(Throwable te) {
				super(te);
			}

			public InvalidDepartmentException(String msg, Throwable te) {
				super(msg, te);
			}
	}