package model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class RepositoryProfileCollaboratorsTest {

private static RepositoryProfileCollaborators repoprofilecollab;
	
	@BeforeClass
	public static void init() {
		
		repoprofilecollab=new RepositoryProfileCollaborators();
		
	}

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
	
	@Test
	public void testgetLogin() {
		
		RepositoryProfileCollaborators repoprofilecollabGet  = new RepositoryProfileCollaborators("abc","abc","abc","abc");
		String actual = repoprofilecollabGet.getLogin();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
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
	
	@Test
	public void testgetId() {
		
		RepositoryProfileCollaborators repoprofilecollabGet  = new RepositoryProfileCollaborators("abc","abc","abc","abc");
		String actual = repoprofilecollabGet.getId();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
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
	
	@Test
	public void testgetRoleName() {
		
		RepositoryProfileCollaborators repoprofilecollabGet  = new RepositoryProfileCollaborators("abc","abc","abc","abc");
		String actual = repoprofilecollabGet.getRoleName();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
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
	
	@Test
	public void testgetUserUrl() {
		
		RepositoryProfileCollaborators repoprofilecollabGet  = new RepositoryProfileCollaborators("abc","abc","abc","abc");
		String actual = repoprofilecollabGet.getUserUrl();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
}
