package com.qaprosoft.argon.models.db;

public class Status extends AbstractEntity
{
	private static final long serialVersionUID = 848908001702031120L;

	private StatusType statusType;

	public enum StatusType
	{
		ONLINE, OFFLINE, TEST_ONLINE, TEST_OFLINE
	}

	public StatusType getStatusType()
	{
		return statusType;
	}

	public void setStatusType(StatusType statusType)
	{
		this.statusType = statusType;
	}
}
