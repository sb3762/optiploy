package com.optiploy.exception;

public class OptiployException extends Exception
{
	private static final long	serialVersionUID	= 2275985712526182652L;

	public OptiployException()
    {}

    public OptiployException(String message)
    {
        super(
                message);
    }

    public OptiployException(String message, Throwable cause)
    {
        super(
                message,
                cause);
    }
}
