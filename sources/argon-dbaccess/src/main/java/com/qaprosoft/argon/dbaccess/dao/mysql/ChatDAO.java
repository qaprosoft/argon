package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Chat;

public interface ChatDAO
{

	void createChat(Chat chat);

	void updateChat(Chat chat);

	Chat getChatById(Long id);

	Chat getChatByName(String name);

	void deleteChatByName(String name);

	void deleteChatById(Long id);

}