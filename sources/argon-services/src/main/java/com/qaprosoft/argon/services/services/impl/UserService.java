package com.qaprosoft.argon.services.services.impl;

import java.util.Arrays;
import java.util.List;

import com.qaprosoft.argon.services.exceptions.ServiceException;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qaprosoft.argon.dbaccess.dao.mysql.AuthorityDAO;
import com.qaprosoft.argon.dbaccess.dao.mysql.StatusDAO;
import com.qaprosoft.argon.dbaccess.dao.mysql.UserDAO;
import com.qaprosoft.argon.dbaccess.dao.mysql.search.SearchResult;
import com.qaprosoft.argon.dbaccess.dao.mysql.search.UserSearchCriteria;
import com.qaprosoft.argon.models.db.Authority;
import com.qaprosoft.argon.models.db.Status;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.services.exceptions.UserNotFoundException;

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

	@Autowired
 	private ConfirmationService confirmationService;

	@Transactional
	public User createUser(User user)
	{
		userDAO.createUser(user);
		return user;
	}

	@Transactional(readOnly = true)
	public User getUserById(long id)
	{
		return userDAO.getUserById(id);
	}

	@Transactional(readOnly = true)
	public User getNotNullUserById(long id) throws UserNotFoundException
	{
		User user = getUserById(id);
		if (user == null)
		{
			throw new UserNotFoundException("Invalid user id");
		}
		return user;
	}

	@Transactional(readOnly = true)
	public User getUserByUserName(String userName)
	{
		return userDAO.getUserByUserName(userName);
	}

	@Transactional(readOnly = true)
	public List<User> getUsersForConfirmationMailing()
	{
		return userDAO.getUsersForConfirmationMailing();
	}

	@Transactional(readOnly = true)
	public SearchResult<User> searchUsers(UserSearchCriteria sc)
	{
		SearchResult<User> results = new SearchResult<User>(sc);
		results.setResults(userDAO.searchUsers(sc));
		results.setTotalResults(userDAO.getUserSearchCount(sc));
		return results;
	}

	@Transactional(rollbackFor = Exception.class)
	public User registerUser(User user, Authority.Type authority) throws ServiceException
	{
		user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
		user.setStatus(getStatusByType(Status.Type.OFFLINE));
		user.setAuthorities(Arrays.asList(getAuthorityByType(authority)));
		user.setEnabled(true);
		user.setVerified(false);
		userDAO.createUser(user);
		confirmationService.generateUserConfirmation(user);
		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateUser(User user)
	{
		userDAO.updateUser(user);
	}

	@Cacheable("statuses")
	public Status getStatusByType(Status.Type type)
	{
		return statusDAO.getStatusByType(type);
	}

	@Cacheable("authorities")
	public Authority getAuthorityByType(Authority.Type type)
	{
		return authorityDAO.getAuthorityByType(type);
	}
	@Transactional(rollbackFor = Exception.class)
	public void updateUserPassword(long id, String password) throws ServiceException
	{
		User user = getNotNullUserById(id);
		user.setPassword(passwordEncryptor.encryptPassword(password));
		updateUser(user);
	}
}