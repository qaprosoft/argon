package com.qaprosoft.argon.models.db;

public class Authority extends AbstractEntity
{
	private static final long serialVersionUID = 3820487023056423848L;

	private Type type;

	public enum Type
	{
		USER, ADMIN
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