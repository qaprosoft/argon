package com.qaprosoft.argon.dbaccess.dao.mysql;

import java.util.List;

import com.qaprosoft.argon.models.db.Status;
import com.qaprosoft.argon.models.db.Status.Type;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface StatusDAO
{

	List<Status> getAllStatuses();

	Status getStatusByType(Type type);

}