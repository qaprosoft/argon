package com.qaprosoft.argon.services.services;

public interface IEmailService
{
	void sendEmail(String to, String subject, String text);
}
