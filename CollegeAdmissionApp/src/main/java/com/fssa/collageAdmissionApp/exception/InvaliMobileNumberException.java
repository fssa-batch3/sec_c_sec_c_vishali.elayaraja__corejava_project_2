package com.fssa.collageAdmissionApp.exception;



	public class InvaliMobileNumberException extends Exception {

		private static final long serialVersionUID = 1L;

			public InvaliMobileNumberException(String msg) {
				super(msg);
			}

			public InvaliMobileNumberException(Throwable te) {
				super(te);
			}

			public InvaliMobileNumberException(String msg, Throwable te) {
				super(msg, te);
			}
}
