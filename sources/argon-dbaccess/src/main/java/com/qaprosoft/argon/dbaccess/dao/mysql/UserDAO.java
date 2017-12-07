package com.qaprosoft.argon.dbaccess.dao.mysql;

import org.apache.ibatis.annotations.Param;

import com.qaprosoft.argon.models.db.User;

public interface UserDAO
{

	void createUser(User user);

	void updateUser(User user);

	User getUserByEmail(String email);

	User getUserByUsername(String username);

	User getUserById(Long id);

	void deleteUserByEmail(String email);

	void deleteUserByUsername(String username);

	void deleteUserById(Long id);

	void addAuthority(@Param("userId") Long userId, @Param("authorityId") Long authorityId);

	void deleteAuthority(@Param("userId") Long userId, @Param("authorityId") Long authorityId);

}