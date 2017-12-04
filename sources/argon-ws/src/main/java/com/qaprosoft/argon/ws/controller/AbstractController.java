package com.qaprosoft.argon.ws.controller;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qaprosoft.argon.models.dto.auth.JwtUserType;
import com.qaprosoft.argon.models.dto.errors.Error;
import com.qaprosoft.argon.models.dto.errors.ErrorCode;
import com.qaprosoft.argon.models.dto.errors.ErrorResponse;
import com.qaprosoft.argon.services.exceptions.ForbiddenOperationException;

public abstract class AbstractController {
	@Resource(name = "messageSource")
	protected MessageSource messageSource;

	protected JwtUserType getPrincipal() {
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user instanceof JwtUserType ? (JwtUserType) user : null;
	}

	protected Long getPrincipalId() {
		JwtUserType user = getPrincipal();
		return user != null ? user.getId() : 0;
	}

	protected String getPrincipalName() {
		UserDetails user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return user != null ? user.getUsername() : "";
	}

	protected boolean isAdmin() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	protected boolean isAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}

	@ExceptionHandler(ForbiddenOperationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorResponse handleForbiddenOperationException(ForbiddenOperationException e) {
		ErrorResponse result = new ErrorResponse();
		result.setError(new Error(ErrorCode.FORBIDDENT));
		return result;
	}

	protected void checkCurrentUserAccess(long userId) throws ForbiddenOperationException {
		if (!isAdmin() && userId != getPrincipalId()) {
			throw new ForbiddenOperationException();
		}
	}
}
