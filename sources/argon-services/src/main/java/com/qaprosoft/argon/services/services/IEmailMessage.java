package com.qaprosoft.argon.services.services;

import com.qaprosoft.argon.models.db.Message;
import com.qaprosoft.argon.models.db.User;

import java.util.List;

public interface IEmailMessage
{
	String getSubject();

	String getText();

	String getTemplate();

	User getUser();

	List<Message.Attachment> getAttachments();
}
