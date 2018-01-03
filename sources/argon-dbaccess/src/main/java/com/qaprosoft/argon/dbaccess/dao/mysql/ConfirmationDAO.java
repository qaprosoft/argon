package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Confirmation;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface ConfirmationDAO
{

	void createConfirmation(Confirmation confirmation);

	void updateConfirmation(Confirmation confirmation);

	Confirmation getConfirmationById(Long id);

	Confirmation getConfirmationByLink(String link);

	Confirmation getConfirmationByUserId(Long userId);

	void deleteConfirmationByLink(String link);

	void deleteConfirmationById(Long id);

}