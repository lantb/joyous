package com.collin.joyous.web.exception;

import org.apache.shiro.authc.AuthenticationException;

public class AuthenticationDBConnException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AuthenticationDBConnException()
    {
    }

    public AuthenticationDBConnException(String message)
    {
        super(message);
    }

    public AuthenticationDBConnException(Throwable cause)
    {
        super(cause);
    }

    public AuthenticationDBConnException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
