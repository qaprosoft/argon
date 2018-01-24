package com.qaprosoft.argon.models.dto.user;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UserRegistrationType implements Serializable
{
	private static final long serialVersionUID = -7439211292910789808L;

	@NotEmpty(message = "Email required")
	private String email;

	@NotEmpty(message = "Username required")
	private String userName;

	@NotEmpty(message = "Password required")
	private String password;

	@NotNull(message = "DOB required")
	private Date dob;

	private String firstName;

	private String lastName;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

}
