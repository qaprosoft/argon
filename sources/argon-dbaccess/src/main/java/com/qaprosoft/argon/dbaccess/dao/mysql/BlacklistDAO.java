package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Blacklist;
import org.apache.ibatis.annotations.Param;
import java.util.List;

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

	List<Blacklist> getBlacklistsByChatId(Long chatId);

	List<Blacklist> getBlacklistsByUserId(Long userId);

	Blacklist getBlacklistByUserIdAndChatId( @Param("userId") Long userId, @Param("chatId") Long chatId);

}