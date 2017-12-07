package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Status;
import com.qaprosoft.argon.models.db.Status.StatusType;

public interface StatusDAO
{

	void createStatus(Status status);

	void updateStatus(Status status);

	Status getStatusById(Long id);

	Status getStatusByStatus(StatusType status);

	void deleteStatusById(Long id);

	void deleteStatusByStatus(StatusType status);

}