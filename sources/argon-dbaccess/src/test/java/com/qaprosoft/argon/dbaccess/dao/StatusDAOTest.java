package com.qaprosoft.argon.dbaccess.dao;

import static org.testng.Assert.*;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import com.qaprosoft.argon.dbaccess.dao.mysql.StatusDAO;
import com.qaprosoft.argon.models.db.Status;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
@Test
@ContextConfiguration("classpath:com/qaprosoft/argon/dbaccess/dbaccess-test.xml")
public class StatusDAOTest extends AbstractTestNGSpringContextTests
{
	private static final boolean ENABLED = false;

	@Autowired
	private StatusDAO statusDAO;

	@Test(enabled = ENABLED)
	public void getAllStatuses()
	{
		assertEquals(statusDAO.getAllStatuses().size(), Status.Type.values().length,
				"Number of Statuses fetched from DB is not as expected.");
	}

	@Test(enabled = ENABLED, dependsOnMethods = "createStatus")
	public void getStatusListByType()
	{
		Stream.of(Status.Type.values())
				.forEach(type -> assertNotNull(statusDAO.getStatusByType(type),
						String.format("Cannot fetch Status of type [%s] from DB.", type)));
	}

}
