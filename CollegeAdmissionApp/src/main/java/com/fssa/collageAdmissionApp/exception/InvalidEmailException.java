package com.fssa.collageAdmissionApp.exception;

	
	public class InvalidEmailException extends Exception {

		private static final long serialVersionUID = 1L;

			public InvalidEmailException(String msg) {
				super(msg);
			}

			public InvalidEmailException(Throwable te) {
				super(te);
			}

			public InvalidEmailException(String msg, Throwable te) {
				super(msg, te);
			}
	}



