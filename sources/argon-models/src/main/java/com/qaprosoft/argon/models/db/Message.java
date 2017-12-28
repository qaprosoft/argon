package com.qaprosoft.argon.models.db;

public class Message extends AbstractEntity
{

	private static final long serialVersionUID = 8018686058027185177L;

	private String body;
	private Attachment attachment;
	private Long userId;
	private Long chatId;
	private Boolean isRead;

	public enum Attachment
	{
		SOUND, VIDEO, PICTURE
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public Attachment getAttachment()
	{
		return attachment;
	}

	public void setAttachment(Attachment attachment)
	{
		this.attachment = attachment;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getChatId()
	{
		return chatId;
	}

	public void setChatId(Long chatId)
	{
		this.chatId = chatId;
	}

	public Boolean isRead()
	{
		return isRead;
	}

	public void setRead(Boolean isRead)
	{
		this.isRead = isRead;
	}

}
