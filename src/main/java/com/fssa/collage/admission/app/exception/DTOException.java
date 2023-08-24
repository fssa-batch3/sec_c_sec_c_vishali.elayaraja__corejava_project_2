package com.fssa.collage.admission.app.exception;


	public class DTOException extends Exception {

		private static final long serialVersionUID = 1L;

			public DTOException(String msg) {
				super(msg);
			}

			public DTOException(Throwable te) {
				super(te);
			}

			public DTOException(String msg, Throwable te) {
				super(msg, te);
			}
	}
