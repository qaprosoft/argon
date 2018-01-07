package com.qaprosoft.argon.services.services.impl;

import com.qaprosoft.argon.dbaccess.dao.mysql.ChatDAO;
import com.qaprosoft.argon.models.db.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

    @Autowired
    private ChatDAO chatDAO;

    @Transactional(rollbackFor = Exception.class)
    public Chat removeUserFromChat(Long userId, Long chatId)
    {
        chatDAO.removeUserFromChat(userId, chatId);
        return chatDAO.getChatById(chatId);
    }
}
