package com.qaprosoft.argon.models.dto.user;

import java.util.Date;

import javax.validation.constraints.AssertTrue;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.qaprosoft.argon.models.db.Status;
import com.qaprosoft.argon.models.dto.AbstractType;

@JsonInclude(Include.NON_NULL)
public class UserType extends AbstractType
{
	private static final long serialVersionUID = -6663692781158665080L;

	private String email;

	private String userName;

	private String firstName;

	private String lastName;

	private String password;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	private Status status;

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

	public Status getStatus()
	{
		return status;
	}

	@JsonGetter("status")
	public Status.Type getJsonStatus()
	{
		return status.getType();
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	@JsonIgnore
	@AssertTrue(message = "At least one of the fields must be not null")
	public boolean isAnyFieldNotNull()
	{
		return email != null ||
				userName != null ||
				firstName != null ||
				lastName != null ||
				dob != null;
	}
}