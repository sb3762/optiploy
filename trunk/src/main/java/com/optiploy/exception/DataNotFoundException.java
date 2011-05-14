package com.optiploy.exception;

public class DataNotFoundException extends Exception
{
	private static final long serialVersionUID = 3669466024962862899L;	

	public DataNotFoundException()
    {}

    public DataNotFoundException(String message)
    {
        super(message);
    }

}
