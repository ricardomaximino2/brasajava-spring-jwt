package com.brasajava.basicjwt.exception;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = -1580484001114717795L;

	public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
