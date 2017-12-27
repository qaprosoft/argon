package com.qaprosoft.argon.services.services.impl;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.qaprosoft.argon.services.services.IEmailService;

@Service("emailService")
public class GmailService implements IEmailService
{
	private JavaMailSender mailSender;
	
	public GmailService(String host, Integer port, String user, String pass)
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
	    mailSender.setPort(port);
	    mailSender.setUsername(user);
	    mailSender.setPassword(pass);
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    this.mailSender = mailSender;
	}
	
	@Override
	public void sendEmail(String to, String subject, String text)
	{
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        mailSender.send(message);
	}
}
