package com.qaprosoft.argon.models.db;

public class Setting extends AbstractEntity
{
	private static final long serialVersionUID = -380292703062844394L;

	private Boolean sound;
	private Boolean news;
	private User user;

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Boolean getSound()
	{
		return sound;
	}

	public void setSound(Boolean sound)
	{
		this.sound = sound;
	}

	public Boolean getNews()
	{
		return news;
	}

	public void setNews(Boolean news)
	{
		this.news = news;
	}
}
