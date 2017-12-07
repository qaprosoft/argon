package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Blacklist;

public interface BlacklistDAO
{

	void createBlacklist(Blacklist blacklist);

	void updateBlacklist(Blacklist blacklist);

	Blacklist getBlacklistById(Long id);

	void deleteBlacklistById(Long id);

}