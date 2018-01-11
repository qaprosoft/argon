package com.qaprosoft.argon.services.services.impl;

import com.qaprosoft.argon.services.exceptions.ForbiddenOperationException;
import com.qaprosoft.argon.services.exceptions.ServiceException;
import com.qaprosoft.argon.services.services.IEmailMessage;
import com.qaprosoft.argon.services.services.impl.email.EmailConfirmation;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.qaprosoft.argon.dbaccess.dao.mysql.ConfirmationDAO;
import com.qaprosoft.argon.models.db.Confirmation;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.services.services.IEmailService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfirmationService
{
	private static final Integer MAX_ATTEMPTS = 3;

	private static final String CONFIRMATION_PATH = "%s/api/auth/confirm?userId=%d&token=%s";
	private static final String SUBJECT = "Confirm registration";
	private static final String MESSAGE_TEXT = "Please, click the link to confirm your account ";
	private static final String MESSAGE_TEXT_AGAIN = "Please, click the link to confirm your account again.Your last link expired! ";

	@Autowired
	@Qualifier("emailService")
	private IEmailService emailService;

	@Value("${argon.webservice.url}")
	private String wsURL;

	@Autowired
	private ConfirmationDAO confirmationDAO;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserService userService;

	public Confirmation generateUserConfirmation(User user) throws ServiceException
	{
		Confirmation confirmation = new Confirmation();
		confirmation.setAttempts(0);
		confirmation.setUserId(user.getId());
		confirmation.setLink(jwtService.generateConfirmToken(user));
		confirmationDAO.createConfirmation(confirmation);
		String url = String.format(CONFIRMATION_PATH, wsURL, user.getId(), confirmation.getLink());
		IEmailMessage iEmailMessage = new EmailConfirmation(user, SUBJECT, MESSAGE_TEXT, url);
		emailService.sendEmail(iEmailMessage);
		return confirmation;
	}

	@Transactional(readOnly = true)
	public Confirmation getConfirmationByUserId(Long userId)
	{
		return confirmationDAO.getConfirmationByUserId(userId);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteConfirmationById(Long id)
	{
		confirmationDAO.deleteConfirmationById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateConfirmation(Confirmation confirmation)
	{
		confirmationDAO.updateConfirmation(confirmation);
	}

	public void confirmUser(Long userId, String token) throws ServiceException
	{
		User user = userService.getUserById(userId);
		Confirmation confirmation = getConfirmationByUserId(userId);

		try
		{
			jwtService.parseConfirmToken(token);
			if (confirmation.getAttempts() >= MAX_ATTEMPTS)
			{
				throw new ForbiddenOperationException("Count off attempts more than maximum");
			}
			if (confirmation.getLink().equals(token))
			{
				user.setVerified(true);
				userService.updateUser(user);
				deleteConfirmationById(confirmation.getId());
			}
		} catch (ExpiredJwtException e)
		{
			int attempts = confirmation.getAttempts();
			if (attempts >= MAX_ATTEMPTS)
			{
				throw new ForbiddenOperationException("Count off attempts more than maximum");
			}
			confirmation.setAttempts(++attempts);
			confirmation.setLink(jwtService.generateConfirmToken(user));
			updateConfirmation(confirmation);
			String url = String.format(CONFIRMATION_PATH, wsURL, user.getId(), confirmation.getLink());
			IEmailMessage iEmailMessage = new EmailConfirmation(user, SUBJECT, MESSAGE_TEXT_AGAIN, url);
			emailService.sendEmail(iEmailMessage);
			throw new ForbiddenOperationException("Token expired");
		}
	}

}
