package com.project.EmployeeManagement.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

	public EmailAlreadyExistsException(String message) {
        super(message);
    }
}

