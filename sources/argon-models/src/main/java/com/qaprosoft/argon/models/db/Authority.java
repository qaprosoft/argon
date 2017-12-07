package com.qaprosoft.argon.models.db;

public class Authority extends AbstractEntity
{
	private static final long serialVersionUID = 3820487023056423848L;

	private AuthorityType authority;

	public enum AuthorityType
	{
		USER, ADMIN, TEST_USER, TEST_ADMIN
	}

	public AuthorityType getAuthority()
	{
		return authority;
	}

	public void setAuthority(AuthorityType authority)
	{
		this.authority = authority;
	}
}