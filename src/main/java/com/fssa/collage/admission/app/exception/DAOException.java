package com.fssa.collage.admission.app.exception;


	
	public class DAOException extends Exception {

		private static final long serialVersionUID = 1L;

			public DAOException(String msg) {
				super(msg);
			}

			public DAOException(Throwable te) {
				super(te);
			}

			public DAOException(String msg, Throwable te) {
				super(msg, te);
			}
	}


	



	
