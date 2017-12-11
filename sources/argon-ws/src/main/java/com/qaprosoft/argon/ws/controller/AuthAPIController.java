package com.qaprosoft.argon.ws.controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qaprosoft.argon.models.db.Authority;
//import com.qaprosoft.argon.models.db.Group;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.models.dto.UserType;
import com.qaprosoft.argon.models.dto.auth.AuthTokenType;
import com.qaprosoft.argon.models.dto.auth.CredentialsType;
import com.qaprosoft.argon.services.exceptions.ServiceException;
import com.qaprosoft.argon.services.services.UserService;
import com.qaprosoft.argon.services.services.auth.JWTService;
import com.qaprosoft.argon.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	
}