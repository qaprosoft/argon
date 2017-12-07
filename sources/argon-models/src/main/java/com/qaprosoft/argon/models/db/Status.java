package com.qaprosoft.argon.models.db;

public class Status extends AbstractEntity
{
	private static final long serialVersionUID = 848908001702031120L;

	private StatusType status;

	public enum StatusType
	{
		ONLINE, OFFLINE, TEST_ONLINE, TEST_OFLINE
	}

	public StatusType getStatus()
	{
		return status;
	}

	public void setStatus(StatusType status)
	{
		this.status = status;
	}
}
