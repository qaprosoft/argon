package com.qaprosoft.argon.services.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qaprosoft.argon.dbaccess.dao.mysql.ConfirmationDAO;
import com.qaprosoft.argon.models.db.Confirmation;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.services.services.IEmailService;

@Service
public class ConfirmationService
{
	private static final Integer MAX_ATTEMPTS = 3;
	
	private static final String CONFIRMATION_PATH = "%s/api/v1/auth/confirm?user=%d&code=%s";
	
	@Autowired
	@Qualifier("emailService") 
	private IEmailService emailService;
	
	@Value("${argon.webservice.url}")
	private String wsURL;
	
	@Autowired
	private ConfirmationDAO confirmationDAO;

	public Confirmation generateUserConfirmation(User user)
	{
		Confirmation confirmation = new Confirmation();
		confirmation.setAttempts(MAX_ATTEMPTS);
		confirmation.setUser(user);
		confirmation.setLink(UUID.randomUUID().toString());
		confirmationDAO.createConfirmation(confirmation);
		
		String url = String.format(CONFIRMATION_PATH, wsURL, user.getId(), confirmation.getLink());
		emailService.sendEmail(user.getEmail(), "Confirm registration", url);
		
		return confirmation;
	}
	
}
