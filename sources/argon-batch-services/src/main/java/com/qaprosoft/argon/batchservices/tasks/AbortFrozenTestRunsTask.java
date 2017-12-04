package com.qaprosoft.argon.batchservices.tasks;

import org.springframework.beans.factory.annotation.Value;

import com.qaprosoft.argon.services.exceptions.ServiceException;

public class AbortFrozenTestRunsTask
{
	@Value("${argon.batch.jobs.abortFrozenTestRuns.testRunExpirationHours}")
	private int testRunExpirationHours;

	public void runTask() throws ServiceException
	{
		// TODO: implement logic
	}
}
