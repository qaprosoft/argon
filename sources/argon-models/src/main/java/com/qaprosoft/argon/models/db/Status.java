package com.qaprosoft.argon.models.db;

public class Status extends AbstractEntity
{
	private static final long serialVersionUID = 848908001702031120L;

	private Type type;

	public enum Type
	{
		ONLINE, OFFLINE
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}
}
