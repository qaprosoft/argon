package com.qaprosoft.argon.dbaccess.dao;

import static org.testng.Assert.*;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import com.qaprosoft.argon.dbaccess.dao.mysql.AuthorityDAO;
import com.qaprosoft.argon.models.db.Authority;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
@Test
@ContextConfiguration("classpath:com/qaprosoft/argon/dbaccess/dbaccess-test.xml")
public class AuthorityDAOTest extends AbstractTestNGSpringContextTests
{

	private static final boolean ENABLED = false;

	@Autowired
	private AuthorityDAO authorityDAO;

	@Test(enabled = ENABLED)
	public void getAllAuthorities()
	{
		assertEquals(authorityDAO.getAllAuthorities().size(), Authority.Type.values().length,
				"Number of Authorities fetched from DB is not as expected.");
	}

	@Test(enabled = ENABLED)
	public void getAuthorityByType()
	{
		Stream.of(Authority.Type.values())
				.forEach(type -> assertNotNull(authorityDAO.getAuthorityByType(type),
						String.format("Cannot fetch Authority of type [%s] from DB.", type)));
	}
}
