package com.qaprosoft.argon.ws.controller;

import com.qaprosoft.argon.models.dto.PasswordType;
import com.qaprosoft.argon.services.exceptions.ForbiddenOperationException;
import com.qaprosoft.argon.services.exceptions.ServiceException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.models.dto.UserType;
import com.qaprosoft.argon.services.exceptions.UserNotFoundException;
import com.qaprosoft.argon.services.services.impl.UserService;
import com.qaprosoft.argon.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@Api(value = "Users API")
@CrossOrigin
@RequestMapping("api/users")
public class UsersAPIController extends AbstractController
{
	@Autowired
	private UserService userService;

	@Autowired
	private Mapper mapper;
	
	@ResponseStatusDetails
	@ApiOperation(value = "Get user profile", nickname = "getUserProfile", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserType getUserProfile() throws UserNotFoundException
	{
		User user = userService.getNotNullUserById(getPrincipalId());
		return mapper.map(user, UserType.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update user password", nickname = "updateUserPassword", code = 200, httpMethod = "PUT")
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "password", method = RequestMethod.PUT)
	public void updateUserPassword(@Valid @RequestBody PasswordType password) throws ServiceException
	{
		if(!Objects.equals(getPrincipalId(), password.getUserId()))
		{
			throw new ForbiddenOperationException("No permissions to update password");
		}
		userService.updateUserPassword(password.getUserId(), password.getPassword());
	}

}