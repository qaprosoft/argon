package com.qaprosoft.argon.models.db;

public class Blacklist extends AbstractEntity
{
	private static final long serialVersionUID = 2205225667932231007L;

	private User user;
	private Chat chat;

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Chat getChat()
	{
		return chat;
	}

	public void setChat(Chat chat)
	{
		this.chat = chat;
	}
}
