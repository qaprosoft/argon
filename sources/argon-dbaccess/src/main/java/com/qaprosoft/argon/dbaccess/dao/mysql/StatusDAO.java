package com.qaprosoft.argon.dbaccess.dao.mysql;

import java.util.List;

import com.qaprosoft.argon.models.db.Status;
import com.qaprosoft.argon.models.db.Status.StatusType;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface StatusDAO
{

	void createStatus(Status status);

	void updateStatus(Status status);
	
	List<Status> getAllStatuses();

	Status getStatusById(Long id);

	Status getStatusByStatusType(StatusType statusType);

	void deleteStatusById(Long id);

	void deleteStatusByStatusType(StatusType statusType);

}