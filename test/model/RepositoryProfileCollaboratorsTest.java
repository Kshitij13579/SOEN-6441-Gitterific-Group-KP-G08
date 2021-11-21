package model;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Test class for RepositoryProfileCollaborators  
 * @author Yogesh Yadav
 *@version 1.0
 *@since 2021-11-20
 */
public class RepositoryProfileCollaboratorsTest {

private static RepositoryProfileCollaborators repoprofilecollab;
	
/**
 *  Initialize Object of RepositoryProfileCollaborators
 */
	@BeforeClass
	public static void init() {
		
		repoprofilecollab=new RepositoryProfileCollaborators();
		
	}

	/**
	 * Test to Validate setLogin() from RepositoryProfileCollaborators  
	 */
	@Test
	public void testsetLogin() {
		
		repoprofilecollab.setLogin("abc");
		String actual = repoprofilecollab.login;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofilecollab.setLogin(null);
		assertNull(repoprofilecollab.getLogin());
		
	}
	
	/**
	 * Test to Validate getLogin() from RepositoryProfileCollaborators  
	 */
	@Test
	public void testgetLogin() {
		
		RepositoryProfileCollaborators repoprofilecollabGet  = new RepositoryProfileCollaborators("abc","abc","abc","abc");
		String actual = repoprofilecollabGet.getLogin();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
	/**
	 * Test to Validate setId() from RepositoryProfileCollaborators  
	 */
	@Test
	public void testsetId() {
		
		repoprofilecollab.setId("abc");
		String actual = repoprofilecollab.id;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofilecollab.setId(null);
		assertNull(repoprofilecollab.getId());
		
	}
	

	/**
	 * Test to Validate getId() from RepositoryProfileCollaborators  
	 */
	@Test
	public void testgetId() {
		
		RepositoryProfileCollaborators repoprofilecollabGet  = new RepositoryProfileCollaborators("abc","abc","abc","abc");
		String actual = repoprofilecollabGet.getId();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	

	/**
	 * Test to Validate setRoleName() from RepositoryProfileCollaborators  
	 */
	@Test
	public void testsetRoleName() {
		
		repoprofilecollab.setRoleName("abc");
		String actual = repoprofilecollab.role_name;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofilecollab.setRoleName(null);
		assertNull(repoprofilecollab.getRoleName());
		
	}
	

	/**
	 * Test to Validate getRoleName() from RepositoryProfileCollaborators  
	 */
	@Test
	public void testgetRoleName() {
		
		RepositoryProfileCollaborators repoprofilecollabGet  = new RepositoryProfileCollaborators("abc","abc","abc","abc");
		String actual = repoprofilecollabGet.getRoleName();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	

	/**
	 * Test to Validate setUserUrl() from RepositoryProfileCollaborators  
	 */
	@Test
	public void testsetUserUrl() {
		
		repoprofilecollab.setUserUrl("abc");
		String actual = repoprofilecollab.user_url;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofilecollab.setUserUrl(null);
		assertNull(repoprofilecollab.getUserUrl());
		
	}
	
	/**
	 * Test to Validate getUserUrl() from RepositoryProfileCollaborators  
	 */
	@Test
	public void testgetUserUrl() {
		
		RepositoryProfileCollaborators repoprofilecollabGet  = new RepositoryProfileCollaborators("abc","abc","abc","abc");
		String actual = repoprofilecollabGet.getUserUrl();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
}
