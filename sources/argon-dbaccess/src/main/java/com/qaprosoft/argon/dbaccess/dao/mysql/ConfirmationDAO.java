package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Confirmation;

public interface ConfirmationDAO
{

	void createConfirmation(Confirmation confirmation);

	void updateConfirmation(Confirmation confirmation);

	Confirmation getConfirmationById(Long id);

	Confirmation getConfirmationByLink(String link);

	void deleteConfirmationByLink(String link);

	void deleteConfirmationById(Long id);

}