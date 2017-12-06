package com.qaprosoft.argon.models.db;

public class Confirmation extends AbstractEntity
{

	private String link;
	private int attempts;
	private User user = new User();

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public int getAttempts()
	{
		return attempts;
	}

	public void setAttempts(int attempts)
	{
		this.attempts = attempts;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

}
