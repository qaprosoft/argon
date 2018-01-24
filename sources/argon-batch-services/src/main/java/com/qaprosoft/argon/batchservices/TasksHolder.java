package com.qaprosoft.argon.batchservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.qaprosoft.argon.services.exceptions.ServiceException;
import com.qaprosoft.argon.services.services.impl.ConfirmationService;
import com.qaprosoft.argon.services.services.impl.UserService;

@Configuration
@ImportResource("classpath:argon-services.xml")
public class TasksHolder
{

	@Autowired
	private UserService userService;

	@Autowired
	private ConfirmationService confirmationService;

	@Bean
	public Runnable sendConfirmationEmailsTask()
	{
		return () -> userService.getUsersForConfirmationMailing().stream()
				.forEach(user -> {
					try
					{
						confirmationService.generateUserConfirmation(user);
					} catch (ServiceException e)
					{
						e.printStackTrace();
					}
				});
	}
}
