package com.optiploy.exception;


public class UserExistsException extends Exception 
{
	private static final long	serialVersionUID	= 4557697213190373078L;

	public UserExistsException(final String message) 
    {
        super(message);
    }
}
