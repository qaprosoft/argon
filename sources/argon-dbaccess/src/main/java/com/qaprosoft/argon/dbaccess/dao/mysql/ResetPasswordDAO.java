package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.ResetPassword;

/**
 * @author kbugrim
 * @since 09 Jan 2018
 */
public interface ResetPasswordDAO
{
	void createResetPassword(ResetPassword blacklist);

	void updateResetPassword(ResetPassword blacklist);

	ResetPassword getResetPasswordById(Long id);

	ResetPassword getResetPasswordByUserId(Long userId);

	void deleteResetPasswordById(Long id);

	void deleteResetPasswordByToken(String token);
}
