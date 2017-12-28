package com.qaprosoft.argon.ws.controller;

import javax.validation.Valid;

import com.qaprosoft.argon.models.db.Confirmation;
import com.qaprosoft.argon.models.dto.auth.RefreshTokenType;
import com.qaprosoft.argon.services.exceptions.ForbiddenOperationException;
import com.qaprosoft.argon.services.exceptions.UserNotFoundException;
import com.qaprosoft.argon.services.services.impl.ConfirmationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.qaprosoft.argon.models.db.Authority;
//import com.qaprosoft.argon.models.db.Group;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.models.dto.UserType;
import com.qaprosoft.argon.models.dto.auth.AuthTokenType;
import com.qaprosoft.argon.models.dto.auth.CredentialsType;
import com.qaprosoft.argon.services.exceptions.ServiceException;
import com.qaprosoft.argon.services.services.impl.JWTService;
import com.qaprosoft.argon.services.services.impl.UserService;
import com.qaprosoft.argon.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "Auth API")
@CrossOrigin
@RequestMapping("api/auth")
public class AuthAPIController extends AbstractController
{
	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserService userService;

	@Autowired
	private ConfirmationService confirmationService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private Mapper mapper;
	
	@ResponseStatusDetails
	@ApiOperation(value = "Registration", nickname = "register", code = 200, httpMethod = "POST")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void register(@Valid @RequestBody UserType userType) throws BadCredentialsException, ServiceException
	{
		userService.registerUser(mapper.map(userType, User.class), Authority.Type.USER);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Generates auth token", nickname = "login", code = 200, httpMethod = "POST", response = AuthTokenType.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthTokenType login(@Valid @RequestBody CredentialsType credentials) throws BadCredentialsException
	{
		AuthTokenType authToken = null;
		try
		{
			Authentication authentication = this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			User user = userService.getUserByUserName(credentials.getUsername());
			authToken = new AuthTokenType("Bearer", jwtService.generateAuthToken(user), jwtService.generateRefreshToken(user), jwtService.getExpiration());
		}
		catch (Exception e)
		{
			throw new BadCredentialsException(e.getMessage());
		}
		return authToken;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Refreshes auth token", nickname = "refreshToken", code = 200, httpMethod = "POST", response = AuthTokenType.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="refresh", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthTokenType refresh(@RequestBody @Valid RefreshTokenType refreshToken) throws BadCredentialsException, ForbiddenOperationException
	{
		AuthTokenType authToken = null;
		try
		{
			User jwtUser = jwtService.parseRefreshToken(refreshToken.getRefreshToken());
			User user = userService.getUserById(jwtUser.getId());
			if(user == null || !user.getPassword().equals(jwtUser.getPassword()))
			{
				throw new Exception("User password changed");
			}

			authToken = new AuthTokenType("Bearer",
					jwtService.generateAuthToken(user),
					jwtService.generateRefreshToken(user),
					jwtService.getExpiration());
		}
		catch(Exception e)
		{
			throw new ForbiddenOperationException(e);
		}

		return authToken;
	}


	@ResponseStatusDetails
	@ApiOperation(value = "Confirm registration", nickname = "confirmRegistration", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "confirm", method = RequestMethod.GET)
	public void confirmAccount(@RequestParam("userId") Long userId, @RequestParam("token") String token) throws ForbiddenOperationException {
		confirmationService.confirmUser(userId, token);
	}
}