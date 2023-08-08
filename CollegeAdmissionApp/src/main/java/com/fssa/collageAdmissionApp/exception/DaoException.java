package com.fssa.collageAdmissionApp.exception;


	
	public class DaoException extends Exception {

		private static final long serialVersionUID = 1L;

			public DaoException(String msg) {
				super(msg);
			}

			public DaoException(Throwable te) {
				super(te);
			}

			public DaoException(String msg, Throwable te) {
				super(msg, te);
			}
	}





	
