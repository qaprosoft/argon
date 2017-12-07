package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Blacklist;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface BlacklistDAO
{

	void createBlacklist(Blacklist blacklist);

	void updateBlacklist(Blacklist blacklist);

	Blacklist getBlacklistById(Long id);

	void deleteBlacklistById(Long id);

}