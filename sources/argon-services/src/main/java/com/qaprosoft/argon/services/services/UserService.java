package com.qaprosoft.argon.services.services;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qaprosoft.argon.dbaccess.dao.mysql.UserMapper;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.services.exceptions.ServiceException;
import com.qaprosoft.argon.services.exceptions.UserNotFoundException;

@Service
public class UserService
{
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncryptor encryptor;

	@Transactional(readOnly = true)
	public User getUserById(long id) throws ServiceException
	{
		return userMapper.getUserById(id);
	}

	@Transactional(readOnly = true)
	public User getUserByUsername(String username) throws ServiceException
	{
		return userMapper.getUserByUserName(username);
	}

	@Transactional(readOnly = true)
	public User getNotNullUserById(long id) throws ServiceException
	{
		User user = getUserById(id);
		if (user == null)
		{
			throw new UserNotFoundException();
		}
		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	public void createUser(User user) throws ServiceException
	{
		user.setPassword(encryptor.encryptPassword(user.getPassword()));
		userMapper.createUser(user);
	}
}
