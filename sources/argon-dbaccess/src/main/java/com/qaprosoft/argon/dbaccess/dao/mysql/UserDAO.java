package com.qaprosoft.argon.dbaccess.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qaprosoft.argon.dbaccess.dao.mysql.search.UserSearchCriteria;
import com.qaprosoft.argon.models.db.User;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface UserDAO
{

	void createUser(User user);

	void updateUser(User user);

	User getUserByEmail(String email);

	User getUserByUserName(String username);

	User getUserById(Long id);

	List<User> getUsersForConfirmationMailing();

	void deleteUserByEmail(String email);

	void deleteUserByUserName(String username);

	void deleteUserById(Long id);

	void addAuthority(@Param("userId") Long userId, @Param("authorityId") Long authorityId);

	void deleteAuthority(@Param("userId") Long userId, @Param("authorityId") Long authorityId);

	List<User> searchUsers(UserSearchCriteria sc);

	Integer getUserSearchCount(UserSearchCriteria sc);

}