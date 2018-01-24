package com.qaprosoft.argon.services.services.impl;

import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.qaprosoft.argon.models.db.Authority;
import com.qaprosoft.argon.models.db.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTService
{
	private String secret;
	private Integer authTokenExp;
	private Integer refreshTokenExp;

	public JWTService(String secret, Integer authTokenExp, Integer refreshTokenExp)
	{
		this.secret = secret;
		this.authTokenExp = authTokenExp;
		this.refreshTokenExp = refreshTokenExp;
	}

	/**
	 * Generates JWT auth token storing id, username, email, roles of the user and specifies expiration date.
	 * 
	 * @param user
	 *            that is used for token generation
	 * @return generated JWT token
	 */
	public String generateAuthToken(User user)
	{
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("username", user.getUsername());
		claims.put("authorities", user.getAuthorities().stream()
				.map(a -> a.getType().name()).collect(Collectors.joining(",")));
		return buildToken(claims, authTokenExp);
	}

	/**
	 * Generates JWT confirm token storing id, username, email, roles of the user and specifies expiration date.
	 *
	 * @param user
	 *            that is used for token generation
	 * @return generated JWT token
	 */
	public String generateConfirmToken(User user)
	{
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("username", user.getUsername());
		return buildToken(claims, authTokenExp);
	}

	/**
	 * Parses user details from JWT token.
	 * 
	 * @param JWT
	 *            token to parse
	 * @return retrieved user details
	 */
	public User parseAuthToken(String token)
	{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		User user = new User();
		user.setId(Long.valueOf(body.getSubject()));
		user.setUsername((String) body.get("username"));
		user.setAuthorities(Stream.of(((String) body.get("authorities")).split(","))
				.map(Authority.Type::valueOf)
				.map(Authority::new)
				.collect(Collectors.toList()));
		return user;
	}

	/**
	 * Verifies JWT refresh token.
	 * 
	 * @param refresh
	 *            token
	 * @param user
	 *            to verify
	 * @return verification status
	 */
	public User parseRefreshToken(String token)
	{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		User user = new User();
		user.setId(Long.valueOf(body.getSubject()));
		user.setPassword((String) body.get("password"));

		return user;
	}

	/**
	 * Verifies JWT confirm token.
	 *
	 * @param refresh
	 *            token
	 * @param user
	 *            to verify
	 * @return verification status
	 */
	public User parseConfirmToken(String token)
	{
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		User user = new User();
		user.setId(Long.valueOf(body.getSubject()));
		user.setUsername((String) body.get("username"));

		return user;
	}

	/**
	 * Generates JWT refresh token storing id, username, password of the user and specifies expiration date.
	 * 
	 * @param user
	 *            that is used for token generation
	 * @return generated JWT token
	 */
	public String generateRefreshToken(User user)
	{
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("password", user.getPassword());
		return buildToken(claims, refreshTokenExp);
	}

	/**
	 * Generates JWT access token storing id, password of the user and specifies expiration (that never expires).
	 * 
	 * @param user
	 *            that is used for token generation
	 * @return generated JWT token
	 */
	public String generateAccessToken(User user)
	{
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("password", user.getPassword());
		return buildToken(claims, Integer.MAX_VALUE);
	}

	private String buildToken(Claims claims, Integer exp)
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, exp);
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).setExpiration(c.getTime())
				.compact();
	}

	public Integer getExpiration()
	{
		return authTokenExp;
	}

}