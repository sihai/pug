/**
 * pug
 */
package com.iacrqq.pug.exception;

/**
 * 
 * @author sihai
 *
 */
public class ValidateException extends Exception
{

	private static final long serialVersionUID = 3574639653323097983L;

	public ValidateException(Throwable t)
	{
		super(t);
	}
	
	public ValidateException(String msg)
	{
		super(msg);
	}
	
	public ValidateException(String msg, Throwable t)
	{
		super(msg, t);
	}
}
