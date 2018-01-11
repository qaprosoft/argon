package com.qaprosoft.argon.ws.controller;

import com.qaprosoft.argon.models.db.Chat;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.models.dto.ChatType;
import com.qaprosoft.argon.models.dto.UserType;
import com.qaprosoft.argon.services.exceptions.ForbiddenOperationException;
import com.qaprosoft.argon.services.services.impl.ChatService;
import com.qaprosoft.argon.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(value = "Chats API")
@CrossOrigin
@RequestMapping("api/chats")
public class ChatAPIController extends AbstractController
{
	@Autowired
	private ChatService chatService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Leave chat", nickname = "leaveChat", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "{chatId}/leave", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ChatType leaveChat(@PathVariable("chatId") Long chatId)
	{
		Chat chat = chatService.removeUserFromChat(getPrincipalId(), chatId);
		return mapper.map(chat, ChatType.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Invite user to chat", nickname = "invite", code = 200, httpMethod = "POST", response = ChatType.class)
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "{userId}/invite", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ChatType invite(@PathVariable(value = "userId") Long userId,
			@RequestBody @Valid ChatType chatType)
			throws ForbiddenOperationException
	{
		Chat chat = chatService.addUserToChat(userId, chatType.getId());
		return mapper.map(chat, ChatType.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Join public chat", nickname = "joinPublicChat", code = 200, httpMethod = "GET", response = ChatType.class)
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "{chatId}/join", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ChatType joinChat(@PathVariable("chatId") Long chatId) throws ForbiddenOperationException
	{
		Chat chat = chatService.joinChat(getPrincipalId(), chatId);
		return mapper.map(chat, ChatType.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get blacklisted users", nickname = "getBlacklistedUsers", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@RequestMapping(value = "{chatId}/blacklist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserType> getBlacklistedUsersInChat(@PathVariable("chatId") Long chatId)
	{
		List<UserType> userTypes = new ArrayList<>();
		List<User> users = chatService.getAllUsersInChatBlacklist(chatId);

		for (User user : users)
		{
			userTypes.add(mapper.map(user, UserType.class));
		}
		return userTypes;
	}
}
