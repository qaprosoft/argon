package com.qaprosoft.argon.dbaccess.utils;

import java.util.Collections;
import java.util.List;

import com.qaprosoft.argon.models.db.AbstractEntity;

public class SortUtil
{
	public static <T extends AbstractEntity> void sortById(List<T> abstractEntities)
	{
		Collections.sort(abstractEntities, (ae1, ae2) -> ae1.getId().compareTo(ae2.getId()));
	}

	public static <T extends AbstractEntity> void sortByCreatedAt(List<T> abstractEntities)
	{
		Collections.sort(abstractEntities, (ae1, ae2) -> ae1.getCreatedAt().compareTo(ae2.getCreatedAt()));
	}

	public static <T extends AbstractEntity> void sortByModifiedAt(List<T> abstractEntities)
	{
		Collections.sort(abstractEntities, (ae1, ae2) -> ae1.getModifiedAt().compareTo(ae2.getModifiedAt()));
	}

}