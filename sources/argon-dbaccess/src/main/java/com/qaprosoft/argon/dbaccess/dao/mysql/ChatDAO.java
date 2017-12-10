package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Chat;
import org.apache.ibatis.annotations.Param;

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

	void addUserToChat(@Param("userId") Long userId, @Param("chatId") Long chatId);

	void removeUserFromChat(@Param("userId") Long userId, @Param("chatId") Long chatId);

	void deleteChatByName(String name);

	void deleteChatById(Long id);

}