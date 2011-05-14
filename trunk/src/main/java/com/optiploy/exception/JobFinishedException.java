package com.optiploy.exception;

public class JobFinishedException extends Exception
{
	private static final long	serialVersionUID	= 6392676922996136937L;

	public JobFinishedException(String message)
    {
        super( message);
    }
}
