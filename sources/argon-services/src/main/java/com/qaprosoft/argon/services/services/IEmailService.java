package com.qaprosoft.argon.services.services;

import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.services.exceptions.ServiceException;

public interface IEmailService
{
	void sendEmail(String to, String subject, String text);

	void sendEmail(User user, String subject, String message, String url) throws ServiceException;
}
