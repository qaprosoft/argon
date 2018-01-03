package com.qaprosoft.argon.models.db;

public class Blacklist extends AbstractEntity
{
	private static final long serialVersionUID = 2205225667932231007L;

	private Long userId;
	private Long chatId;

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

}
