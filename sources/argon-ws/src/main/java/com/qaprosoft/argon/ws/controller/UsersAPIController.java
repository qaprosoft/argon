package com.qaprosoft.argon.ws.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qaprosoft.argon.dbaccess.dao.mysql.search.SearchResult;
import com.qaprosoft.argon.dbaccess.dao.mysql.search.UserSearchCriteria;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.models.dto.user.UserType;
import com.qaprosoft.argon.services.exceptions.ForbiddenOperationException;
import com.qaprosoft.argon.services.exceptions.UserNotFoundException;
import com.qaprosoft.argon.services.services.impl.UserService;
import com.qaprosoft.argon.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserType getUserProfile() throws UserNotFoundException
	{
		User user = userService.getNotNullUserById(getPrincipalId());
		return mapper.map(user, UserType.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get user profile by id", nickname = "getUserProfileById", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "{id}/profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserType getUserProfileById(@PathVariable Long id) throws UserNotFoundException
	{
		User user = userService.getNotNullUserById(id);
		return mapper.map(user, UserType.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update user profile", nickname = "updateUserProfile", code = 200, httpMethod = "PUT")
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "profile", method = RequestMethod.PUT)
	public void updateUserProfile(@Valid @RequestBody UserType userType) throws UserNotFoundException
	{
		User user = mapper.map(userType, User.class);
		user.setId(getPrincipalId());
		userService.updateUserProfile(user);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update user profile by id", nickname = "updateUserProfileById", code = 200, httpMethod = "PUT")
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "{id}/profile", method = RequestMethod.PUT)
	public void updateUserProfileById(@Valid @RequestBody UserType userType, @PathVariable Long id)
			throws UserNotFoundException, ForbiddenOperationException
	{
		checkCurrentUserAccess(id);
		User user = mapper.map(userType, User.class);
		user.setId(id);
		userService.updateUserProfile(user);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Search users", nickname = "searchUsers", code = 200, httpMethod = "POST", response = SearchResult.class)
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SearchResult<UserType> searchUsers(UserSearchCriteria sc)
	{
		SearchResult<User> userResults = userService.searchUsers(sc);
		SearchResult<UserType> usersTypeResults = new SearchResult<>(sc);
		usersTypeResults.setTotalResults(userResults.getTotalResults());
		usersTypeResults.setResults(userResults.getResults().stream() //
				.map(user -> mapper.map(user, UserType.class)) //
				.collect(Collectors.toList()));
		return usersTypeResults;
	}

}