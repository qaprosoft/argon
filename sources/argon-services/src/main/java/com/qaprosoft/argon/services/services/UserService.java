package com.qaprosoft.argon.services.services;

import java.util.Arrays;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qaprosoft.argon.dbaccess.dao.mysql.AuthorityDAO;
import com.qaprosoft.argon.dbaccess.dao.mysql.StatusDAO;
import com.qaprosoft.argon.dbaccess.dao.mysql.UserDAO;
import com.qaprosoft.argon.models.db.Authority;
import com.qaprosoft.argon.models.db.Authority.AuthorityType;
import com.qaprosoft.argon.models.db.Status;
import com.qaprosoft.argon.models.db.Status.StatusType;
import com.qaprosoft.argon.models.db.User;

@Service
public class UserService
{
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private StatusDAO statusDAO;
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@Autowired
	private PasswordEncryptor passwordEncryptor;
	
	@Transactional
	public User createUser(User user)
	{
		userDAO.createUser(user);
		return user;
	}
	
	@Transactional(readOnly=true)
	public User getUserById(long id)
	{
		return userDAO.getUserById(id);
	}
	
	@Transactional(readOnly=true)
	public User getUserByUserName(String userName)
	{
		return userDAO.getUserByUserName(userName);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public User registerUser(User user, AuthorityType authority)
	{
		user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
		user.setStatus(getStatusByType(StatusType.OFFLINE));
		user.setAuthorities(Arrays.asList(getAuthorityByType(authority)));
		user.setEnabled(true);
		user.setVerified(false);
		userDAO.createUser(user);
		return user;
	}
	
	@Cacheable("statuses")
	public Status getStatusByType(StatusType type)
	{
		return statusDAO.getStatusByStatusType(type);
	}
	
	@Cacheable("authorities")
	public Authority getAuthorityByType(AuthorityType type)
	{
		return authorityDAO.getAuthorityByAuthorityType(type);
	}
}