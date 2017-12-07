package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Message;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface MessageDAO
{

	void createMessage(Message message);

	void updateMessage(Message message);

	Message getMessageById(Long id);

	void deleteMessageById(Long id);

}