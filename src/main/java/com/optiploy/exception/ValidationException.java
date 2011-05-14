package com.optiploy.exception;

public class ValidationException extends Exception
{
	private static final long	serialVersionUID	= 5081854989578678599L;

	public ValidationException()
    {}

    public ValidationException(String message)
    {
        super(message);
    }

}
