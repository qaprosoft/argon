package com.qaprosoft.argon.models.dto.errors;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qaprosoft.argon.models.dto.errors.ErrorCodeSerializer;

@JsonSerialize(using = ErrorCodeSerializer.class)
public enum ErrorCode
{
	UNAUTHORIZED(401), FORBIDDEN(403),

	USER_NOT_FOUND(1000);

	private int code;

	private ErrorCode(int code)
	{
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}
}