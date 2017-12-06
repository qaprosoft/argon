package com.qaprosoft.argon.models.db;

public class Message extends AbstractEntity
{
	private static final long serialVersionUID = 8018686058027185177L;
	
	private String text;
	private Attachment attachment;
	private User user = new User();
	private Chat chat = new Chat();
	private boolean isRead = false;

	public enum Attachment
	{
		SOUND, VIDEO, PICTURE
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Attachment getAttachment()
	{
		return attachment;
	}

	public void setAttachment(Attachment attachment)
	{
		this.attachment = attachment;
	}

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

	public boolean isRead()
	{
		return isRead;
	}

	public void setRead(boolean read)
	{
		isRead = read;
	}
}
