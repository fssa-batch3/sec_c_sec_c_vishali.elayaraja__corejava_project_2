package com.fssa.collage.admission.app.exception;

	
	public class ValidationException extends Exception {

		private static final long serialVersionUID = 1L;

			public ValidationException(String msg) {
				super(msg);
			}

			public ValidationException(Throwable te) {
				super(te);
			}

			public ValidationException(String msg, Throwable te) {
				super(msg, te);
			}
	}

