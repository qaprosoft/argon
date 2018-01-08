package com.qaprosoft.argon.dbaccess.dao.mysql.search;

public class UserSearchCriteria extends SearchCriteria
{
	private Long id;
	private String username;
	private String firstLastName;
	private String email;
	private Integer ageMin;
	private Integer ageMax;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getFirstLastName()
	{
		return firstLastName;
	}

	public void setFirstLastName(String firstLastName)
	{
		this.firstLastName = firstLastName;
	}

	public Integer getAgeMin()
	{
		return ageMin;
	}

	public void setAgeMin(Integer ageMin)
	{
		this.ageMin = ageMin;
	}

	public Integer getAgeMax()
	{
		return ageMax;
	}

	public void setAgeMax(Integer ageMax)
	{
		this.ageMax = ageMax;
	}

}
