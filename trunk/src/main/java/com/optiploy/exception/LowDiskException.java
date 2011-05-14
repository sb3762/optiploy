package com.optiploy.exception;

public class LowDiskException extends Exception
{
	private static final long	serialVersionUID	= 4113714371773501480L;

	public LowDiskException(String message)
    {
        super(message);
    }
}
