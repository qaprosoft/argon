package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Chat;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface ChatDAO
{

	void createChat(Chat chat);

	void updateChat(Chat chat);

	Chat getChatById(Long id);

	Chat getChatByName(String name);

	void deleteChatByName(String name);

	void deleteChatById(Long id);

}