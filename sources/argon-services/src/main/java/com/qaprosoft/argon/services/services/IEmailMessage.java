package com.qaprosoft.argon.services.services;

import com.qaprosoft.argon.models.db.Message;

import java.util.List;

public interface IEmailMessage
{
	String getSubject();

	String getText();

	String getTemplate();

	List<Message.Attachment> getAttachments();
}
