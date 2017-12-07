package com.qaprosoft.argon.models.db;

public class Confirmation extends AbstractEntity
{
	private static final long serialVersionUID = -1088752683405700742L;

	private String link;
	private Integer attempts;
	private User user;

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public Integer getAttempts()
	{
		return attempts;
	}

	public void setAttempts(Integer attempts)
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
