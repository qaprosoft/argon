package com.qaprosoft.argon.models.db;

public class Authority extends AbstractEntity
{
	private static final long serialVersionUID = 3820487023056423848L;

	private AuthorityType authorityType;

	public enum AuthorityType
	{
		USER, ADMIN, TEST_USER, TEST_ADMIN
	}

	public AuthorityType getAuthorityType()
	{
		return authorityType;
	}

	public void setAuthorityType(AuthorityType authorityType)
	{
		this.authorityType = authorityType;
	}
}