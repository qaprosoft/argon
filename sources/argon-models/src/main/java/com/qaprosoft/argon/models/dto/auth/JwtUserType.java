package com.qaprosoft.argon.models.dto.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.qaprosoft.argon.models.db.Authority;

//import com.qaprosoft.argon.models.db.Group.Role;

/**
 * All user information handled by the JWT token
 */
public class JwtUserType implements UserDetails
{
	private static final long serialVersionUID = 2105145272583220476L;

	private long id;
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities = new ArrayList<>();

	public JwtUserType(long id, String userName, List<Authority> authorities)
	{
		this.id = id;
		this.userName = userName;
		for (Authority authority : authorities)
		{
			this.authorities.add(new SimpleGrantedAuthority(authority.getAuthorityType().name()));
		}
		// TODO: removed when default role populated for all
		if (this.authorities.isEmpty())
		{
			this.authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}
	
	public JwtUserType(long id, String userName, String password, List<Authority> authorities)
	{
		this(id, userName, authorities);
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return authorities;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	public long getId()
	{
		return id;
	}

	@Override
	public String getUsername()
	{
		return userName;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}
}