package model;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for RepositoryProfile  
 * @author Yogesh Yadav
 *@version 1.0
 *@since 2021-11-20
 */
public class RepositoryProfileTest {

	private static RepositoryProfile repoprofile;
	
	/**
	 *  Initialize Object of RepositoryProfile
	 */
	@BeforeClass
	public static void init() {
		
		repoprofile=new RepositoryProfile();
		
	}
	
	/**
	 * Test to Validate setLogin() from RepositoryProfile  
	 */
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
	
	/**
	 * Test to Validate getLogin() from RepositoryProfile  
	 */
	@Test
	public void testgetLogin() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getLogin();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	/**
	 * Test to Validate setNode_id() from RepositoryProfile  
	 */
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
	
	/**
	 * Test to Validate getNode_id() from RepositoryProfile  
	 */
	@Test
	public void testgetNode_id() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getNode_id();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	/**
	 * Test to Validate setAvatar_url() from RepositoryProfile  
	 */
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
	
	/**
	 * Test to Validate getAvatar_url() from RepositoryProfile  
	 */
	@Test
	public void testgetAvatar_url() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getAvatar_url();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
	/**
	 * Test to Validate setcollaborators_url() from RepositoryProfile  
	 */
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
	/**
	 * Test to Validate getcollaborators_url() from RepositoryProfile  
	 */
	@Test
	public void testgetcollaborators_url() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getcollaborators_url();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	/**
	 * Test to Validate setissues_url() from RepositoryProfile  
	 */
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
	
	/**
	 * Test to Validate getissues_url() from RepositoryProfile  
	 */
	@Test
	public void testgetissues_url() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getissues_url();
		String expected = "abc";
		assertEquals(expected,actual);
	}
	/**
	 * Test to Validate setopen_issues() from RepositoryProfile  
	 */
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
	
	/**
	 * Test to Validate getopen_issues() from RepositoryProfile  
	 */
	@Test
	public void testgetopen_issues() {
		
		RepositoryProfile repoprofileGet  = new RepositoryProfile("abc","abc","abc","abc","abc","abc","abc");
		String actual = repoprofileGet.getopen_issues();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
}
