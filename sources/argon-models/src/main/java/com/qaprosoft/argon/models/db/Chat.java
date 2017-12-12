package com.qaprosoft.argon.models.db;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AbstractEntity
{
	private static final long serialVersionUID = -3241763413702060156L;

	private String name;
	private boolean privateEnabled;
	private List<User> users = new ArrayList<>();

	public List<User> getUsers()
	{
		return users;
	}

	public void setUsers(List<User> users)
	{
		this.users = users;
	}

	public boolean getPrivateEnabled() {
		return privateEnabled;
	}

	public void setPrivateEnabled(boolean privateEnabled) {
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
