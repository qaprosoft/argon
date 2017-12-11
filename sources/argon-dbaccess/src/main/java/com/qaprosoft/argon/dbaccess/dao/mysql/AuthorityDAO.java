package com.qaprosoft.argon.dbaccess.dao.mysql;

import java.util.List;

import com.qaprosoft.argon.models.db.Authority;
import com.qaprosoft.argon.models.db.Authority.Type;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface AuthorityDAO
{

	List<Authority> getAllAuthorities();

	Authority getAuthorityByType(Type type);

}