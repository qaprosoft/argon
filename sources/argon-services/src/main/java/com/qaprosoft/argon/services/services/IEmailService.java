package com.qaprosoft.argon.services.services;

import com.qaprosoft.argon.services.exceptions.ServiceException;

public interface IEmailService
{
	void sendEmail(IEmailMessage emailMessage) throws ServiceException;
}
