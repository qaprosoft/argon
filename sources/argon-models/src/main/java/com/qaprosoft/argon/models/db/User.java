package com.qaprosoft.argon.models.db;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class User extends AbstractEntity
{
	private static final long serialVersionUID = -8127492654937722329L;

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean enabled;
	private Boolean verified;
	private Date dob;
	private List<Authority> authorities;
	private Status status;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Boolean getEnabled()
	{
		return enabled;
	}

	public void setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
	}

	public Boolean getVerified()
	{
		return verified;
	}

	public void setVerified(Boolean verified)
	{
		this.verified = verified;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public List<Authority> getAuthorities()
	{
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities)
	{
		this.authorities = authorities;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}
}