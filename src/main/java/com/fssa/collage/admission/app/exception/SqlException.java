package com.fssa.collage.admission.app.exception;
import java.sql.SQLException;


public class SqlException extends SQLException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SqlException(String message){

        super(message);
    }
    public static void main(String[] args) throws SqlException {

        throw new SqlException("You have already applied for the selected department");

    }

}  

