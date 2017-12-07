package com.qaprosoft.argon.models.db;

public class Message extends AbstractEntity
{

	private static final long serialVersionUID = 8018686058027185177L;

	private String text;
	private Attachment attachment;
	private User user;
	private Chat chat;
	private Boolean read;

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

	public Boolean getRead()
	{
		return read;
	}

	public void setRead(Boolean read)
	{
		this.read = read;
	}
}
