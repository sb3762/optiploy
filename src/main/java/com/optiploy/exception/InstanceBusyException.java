package com.optiploy.exception;

public class InstanceBusyException extends Exception
{
	private static final long	serialVersionUID	= 2290845256869000547L;

	public InstanceBusyException(String message)
    {
        super(message);
    }
}
