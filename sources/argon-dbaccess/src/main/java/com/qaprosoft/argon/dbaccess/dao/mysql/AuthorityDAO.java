package com.qaprosoft.argon.dbaccess.dao.mysql;

import java.util.List;

import com.qaprosoft.argon.models.db.Authority;
import com.qaprosoft.argon.models.db.Authority.AuthorityType;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface AuthorityDAO
{

	void createAuthority(Authority authority);

	void updateAuthority(Authority authority);
	
	List<Authority> getAllAuthorities();

	Authority getAuthorityById(Long id);

	Authority getAuthorityByAuthorityType(AuthorityType authority);

	void deleteAuthorityByAuthorityType(AuthorityType authority);

	void deleteAuthorityById(Long id);

}