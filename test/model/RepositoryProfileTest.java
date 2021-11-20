package model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class RepositoryProfileTest {

	private static RepositoryProfile repoprofile;
	
	@BeforeClass
	public static void init() {
		
		repoprofile=new RepositoryProfile();
		
	}
	
	@Test
	public void testsetLogin() {
		
		repoprofile.setLogin("abc");
		String actual = repoprofile.login;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofile.setLogin(null);
		assertNull(repoprofile.getLogin());
		
	}
	
	@Test
	public void testgetLogin() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getLogin();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	@Test
	public void testsetNode_id() {
		
		repoprofile.setNode_id("abc");
		String actual = repoprofile.node_id;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofile.setNode_id(null);
		assertNull(repoprofile.getNode_id());
		
	}
	
	@Test
	public void testgetNode_id() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getNode_id();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	@Test
	public void testsetAvatar_url() {
		
		repoprofile.setAvatar_url("abc");
		String actual = repoprofile.avatar_url;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofile.setAvatar_url(null);
		assertNull(repoprofile.getAvatar_url());
		
	}
	
	@Test
	public void testgetAvatar_url() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getAvatar_url();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	@Test
	public void testsetcollaborators_url() {
		
		repoprofile.setcollaborators_url("abc");
		String actual = repoprofile.collaborators_url;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofile.setcollaborators_url(null);
		assertNull(repoprofile.getcollaborators_url());
		
	}
	
	@Test
	public void testgetcollaborators_url() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getcollaborators_url();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	@Test
	public void testsetissues_url() {
		
		repoprofile.setissues_url("abc");
		String actual = repoprofile.issues_url;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofile.setissues_url(null);
		assertNull(repoprofile.getissues_url());
		
	}
	
	@Test
	public void testgetissues_url() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getissues_url();
		String expected = "abc";
		assertEquals(expected,actual);
	}
	@Test
	public void testsetopen_issues() {
		
		repoprofile.setopen_issues("abc");
		String actual = repoprofile.open_issues;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofile.setopen_issues(null);
		assertNull(repoprofile.getopen_issues());
		
	}
	
	@Test
	public void testgetopen_issues() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getopen_issues();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
}
