package org.anchoredportals.main;

/**
 *
 * @author Iain E. Davis <iain@ruhlendavis.org>
 */
public class InvalidPortalLocationException extends RuntimeException
{
	public InvalidPortalLocationException()
	{
		super("Invalid Portal Location");
	}
	
	public InvalidPortalLocationException(String message)
	{
		super(message);
	}
}
