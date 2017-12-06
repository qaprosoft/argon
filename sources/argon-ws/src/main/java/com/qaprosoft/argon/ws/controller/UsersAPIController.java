package com.qaprosoft.argon.ws.controller;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.models.dto.UserType;
import com.qaprosoft.argon.services.exceptions.ServiceException;
import com.qaprosoft.argon.services.services.UserService;
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

}
