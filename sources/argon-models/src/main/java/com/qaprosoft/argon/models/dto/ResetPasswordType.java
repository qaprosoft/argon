package com.qaprosoft.argon.models.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ResetPasswordType implements Serializable
{
	@NotEmpty(message = "Password required")
	private String email;
	@Size(min = 5, message = "Too short password")
	private String newPassword;

	@NotEmpty(message = "Password confirmation required")
	private String repeatNewPassword;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public String getRepeatNewPassword()
	{
		return repeatNewPassword;
	}

	public void setRepeatNewPassword(String repeatNewPassword)
	{
		this.repeatNewPassword = repeatNewPassword;
	}

	@AssertTrue(message = "Password confirmation not matching")
	public boolean isConfirmationValid()
	{
		return newPassword != null && newPassword.equals(repeatNewPassword);
	}
}
