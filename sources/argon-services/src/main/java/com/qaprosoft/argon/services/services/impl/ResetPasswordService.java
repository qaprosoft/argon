package com.qaprosoft.argon.services.services.impl;

import com.qaprosoft.argon.dbaccess.dao.mysql.ResetPasswordDAO;
import com.qaprosoft.argon.models.db.ResetPassword;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.services.exceptions.ForbiddenOperationException;
import com.qaprosoft.argon.services.exceptions.ServiceException;
import com.qaprosoft.argon.services.exceptions.UserNotFoundException;
import com.qaprosoft.argon.services.services.IEmailMessage;
import com.qaprosoft.argon.services.services.IEmailService;
import com.qaprosoft.argon.services.services.impl.email.EmailResetPassword;
import io.jsonwebtoken.ExpiredJwtException;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService
{
	private static final String RESET_PASSWORD_PATH = "%s/api/auth/password/reset?token=%s";
	private static final String SUBJECT = "Reset password";
	private static final String MESSAGE_TEXT = "Please, click the link to reset your password";

	@Autowired
	@Qualifier("emailService")
	private IEmailService emailService;

	@Value("${argon.webservice.url}")
	private String wsURL;

	@Autowired
	private ResetPasswordDAO resetPasswordDAO;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	public ResetPassword forgotUserPassword(User user, String newPassword) throws ServiceException
	{
		ResetPassword resetPassword = new ResetPassword();
		resetPassword.setUserId(user.getId());
		resetPassword.setToken(jwtService.generateResetPasswordToken(user));
		resetPassword.setNewPassword(passwordEncryptor.encryptPassword(newPassword));
		resetPasswordDAO.createResetPassword(resetPassword);
		String url = String.format(RESET_PASSWORD_PATH, wsURL, resetPassword.getToken());
		IEmailMessage iEmailMessage = new EmailResetPassword(user, SUBJECT, MESSAGE_TEXT, url);
		emailService.sendEmail(iEmailMessage);
		return resetPassword;
	}

	public void resetUserPassword(String token) throws UserNotFoundException, ForbiddenOperationException
	{
		try
		{
			Long userId = jwtService.parseResetPasswordToken(token).getId();
			ResetPassword resetPassword = resetPasswordDAO.getResetPasswordByUserId(userId);
			User user = userService.getNotNullUserById(userId);
			user.setPassword(resetPassword.getNewPassword());
			userService.updateUser(user);
			resetPasswordDAO.deleteResetPasswordById(resetPassword.getId());
		} catch (ExpiredJwtException e)
		{
			resetPasswordDAO.deleteResetPasswordByToken(token);
			throw new ForbiddenOperationException("Token is expired!Try to reset password again");
		}
	}

}
