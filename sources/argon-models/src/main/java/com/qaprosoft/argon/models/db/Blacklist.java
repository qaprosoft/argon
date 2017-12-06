package com.qaprosoft.argon.models.db;

public class Blacklist extends AbstractEntity
{

	private User user = new User();
	private Chat chat = new Chat();

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
