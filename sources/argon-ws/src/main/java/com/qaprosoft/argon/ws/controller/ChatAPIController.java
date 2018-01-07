package com.qaprosoft.argon.ws.controller;


import com.qaprosoft.argon.models.db.Chat;
import com.qaprosoft.argon.models.dto.ChatType;
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

}
