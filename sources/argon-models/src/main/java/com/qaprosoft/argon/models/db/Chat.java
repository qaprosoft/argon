package com.qaprosoft.argon.models.db;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AbstractEntity
{
	private static final long serialVersionUID = -3241763413702060156L;

	private String name;
	private boolean privateEnabled;
	private List<Long> usersId = new ArrayList<>();

	public List<Long> getUsersId()
	{
		return usersId;
	}

	public void setUsers(List<Long> usersId)
	{
		this.usersId = usersId;
	}

	public boolean getPrivateEnabled()
	{
		return privateEnabled;
	}

	public void setPrivateEnabled(boolean privateEnabled)
	{
		this.privateEnabled = privateEnabled;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
