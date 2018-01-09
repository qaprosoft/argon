package com.qaprosoft.argon.services.services.impl;

import com.qaprosoft.argon.dbaccess.dao.mysql.BlacklistDAO;
import com.qaprosoft.argon.dbaccess.dao.mysql.ChatDAO;
import com.qaprosoft.argon.models.db.Blacklist;
import com.qaprosoft.argon.models.db.Chat;
import com.qaprosoft.argon.services.exceptions.ForbiddenOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService
{

	@Autowired
	private ChatDAO chatDAO;

	@Autowired
	private BlacklistDAO blacklistDAO;

	@Transactional(rollbackFor = Exception.class)
	public Chat removeUserFromChat(Long userId, Long chatId)
	{
		chatDAO.removeUserFromChat(userId, chatId);
		return chatDAO.getChatById(chatId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Chat addUserToChat(Long userId, Long chatId) throws ForbiddenOperationException
	{
		if (isUserInChatBlacklist(userId, chatId))
		{
			throw new ForbiddenOperationException("You in blacklist in this chat!");
		}
		chatDAO.addUserToChat(userId, chatId);
		return chatDAO.getChatById(chatId);
	}

	public Chat joinChat(Long userId, Long chatId) throws ForbiddenOperationException
	{
		Chat chat = chatDAO.getChatById(chatId);

		if (isUserInChatBlacklist(userId, chatId))
		{
			throw new ForbiddenOperationException("You in blacklist in this chat!");
		}

		if (chat.isPrivate())
		{
			throw new ForbiddenOperationException("Chat is private!You can't join to it");
		}

		chatDAO.addUserToChat(userId, chatId);
		return chatDAO.getChatById(chatId);
	}

	private boolean isUserInChatBlacklist(Long userId, Long chatId)
	{
		Blacklist blacklist = blacklistDAO.getBlacklistByUserIdAndChatId(userId, chatId);
		if (blacklist == null) return false;
		return true;
	}
}
