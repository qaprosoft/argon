package com.qaprosoft.argon.models.db;

/**
 * @author kbugrim
 * @since 09 Jan 2018
 */
public class ResetPassword extends AbstractEntity
{
	private static final long serialVersionUID = 8378990027227753590L;

	private String token;
	private Long userId;
	private String newPassword;

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
}
