package com.qaprosoft.argon.ws.util.dozer;

import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.services.services.UserService;

public class LongToUserConverter extends DozerConverter<Long, User>
{
	@Autowired
	private UserService userService;

	public LongToUserConverter()
	{
		super(Long.class, User.class);
	}

	@Override
	public User convertTo(Long source, User destination)
	{
		return (source == null) ? null : userService.getUserById(source);
	}

	@Override
	public Long convertFrom(User source, Long destination)
	{
		return (source == null) ? null : source.getId();
	}
}
