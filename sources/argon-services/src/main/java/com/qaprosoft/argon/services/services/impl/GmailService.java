package com.qaprosoft.argon.services.services.impl;

import java.util.Properties;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.services.exceptions.ServiceException;
import com.qaprosoft.argon.services.services.IEmailMessage;
import freemarker.template.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import com.qaprosoft.argon.services.services.IEmailService;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import javax.mail.internet.MimeMessage;

@Service("emailService")
public class GmailService implements IEmailService
{
	private Logger LOGGER = Logger.getLogger(GmailService.class);

	@Autowired
	private Configuration freemarkerConfiguration;

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

	@Override
	public void sendEmail(User user, String subject, String message, String url) throws ServiceException {

		IEmailMessage emailMessage =  new EmailConfirmation(user, subject, message, url);
		final String text = getFreeMarkerTemplateContent(emailMessage);

			MimeMessagePreparator preparator = new MimeMessagePreparator()
			{
				public void prepare(MimeMessage mimeMessage) throws Exception
				{
					boolean hasAttachments = emailMessage.getAttachments() != null;

					MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, hasAttachments);
					msg.setSubject(emailMessage.getSubject());
					msg.setTo(user.getEmail());
					msg.setFrom("Argon");
					msg.setText(text, true);
				}
			};

		mailSender.send(preparator);
	}


	public String getFreeMarkerTemplateContent(IEmailMessage message) throws ServiceException {
		StringBuffer content = new StringBuffer();
		try
		{
			content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(message.getTemplate()), message));
		} catch (Exception e)
		{
			LOGGER.error("Problem with email template compilation: " + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return content.toString();
	}
}
