package com.qaprosoft.argon.models.db;

public class Setting extends AbstractEntity
{
	private static final long serialVersionUID = -380292703062844394L;

	private Boolean soundEnabled;
	private Boolean newsEnabled;
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
		return soundEnabled;
	}

	public void setSound(Boolean soundEnabled)
	{
		this.soundEnabled = soundEnabled;
	}

	public Boolean getNews()
	{
		return newsEnabled;
	}

	public void setNews(Boolean newsEnabled)
	{
		this.newsEnabled = newsEnabled;
	}
}
