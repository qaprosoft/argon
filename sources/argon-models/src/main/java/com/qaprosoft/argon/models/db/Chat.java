package com.qaprosoft.argon.models.db;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AbstractEntity
{
	private static final long serialVersionUID = -3241763413702060156L;

	private String name;
	private boolean isPrivate;
	private Long ownerId;
	private List<Long> usersId = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public List<Long> getUsersId() {
		return usersId;
	}

	public void setUsersId(List<Long> usersId) {
		this.usersId = usersId;
	}

	public boolean isPrivate()
	{
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate)
	{
		this.isPrivate = isPrivate;
	}
}
