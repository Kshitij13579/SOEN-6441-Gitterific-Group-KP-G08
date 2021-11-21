package model;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for RepositoryProfileIssues  
 * @author Yogesh Yadav
 *@version 1.0
 *@since 2021-11-20
 */
public class RepositoryProfileIssuesTest {

	private static RepositoryProfileIssues repoprofileissues;
	
	/**
	 *  Initialize Object of RepositoryProfileIssues
	 */
	@BeforeClass
	public static void init() {
		
		repoprofileissues=new RepositoryProfileIssues();
		
	}
	
	/**
	 * Test to Validate setIssueNumber() from RepositoryProfileIssues  
	 */
	@Test
	public void testsetIssueNumber() {
		
		repoprofileissues.setIssueNumber("abc");
		String actual = repoprofileissues.issue_number;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofileissues.setIssueNumber(null);
		assertNull(repoprofileissues.getIssueNumber());
		
	}
	
	/**
	 * Test to Validate getIssueNumber() from RepositoryProfileIssues  
	 */
	@Test
	public void testgetIssueNumber() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getIssueNumber();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
	/**
	 * Test to Validate setIssueTitle() from RepositoryProfileIssues  
	 */
	@Test
	public void testsetIssueTitle() {
		
		repoprofileissues.setIssueTitle("abc");
		String actual = repoprofileissues.issue_title;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofileissues.setIssueTitle(null);
		assertNull(repoprofileissues.getIssueTitle());
		
	}
	
	/**
	 * Test to Validate getIssueTitle() from RepositoryProfileIssues  
	 */
	@Test
	public void testgetIssueTitle() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getIssueTitle();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
	/**
	 * Test to Validate setState() from RepositoryProfileIssues  
	 */
	@Test
	public void testsetState() {
		
		repoprofileissues.setState("abc");
		String actual = repoprofileissues.state;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofileissues.setState(null);
		assertNull(repoprofileissues.getState());
		
	}
	
	/**
	 * Test to Validate getState() from RepositoryProfileIssues  
	 */
	@Test
	public void testgetState() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getState();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
	/**
	 * Test to Validate setCreatedAt() from RepositoryProfileIssues  
	 */
	@Test
	public void testsetCreatedAt() {
		
		repoprofileissues.setCreatedAt("abc");
		String actual = repoprofileissues.created_at;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofileissues.setCreatedAt(null);
		assertNull(repoprofileissues.getCreatedAt());
		
	}
	
	/**
	 * Test to Validate getCreatedAt() from RepositoryProfileIssues  
	 */
	@Test
	public void testgetCreatedAt() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getCreatedAt();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
	/**
	 * Test to Validate setUpdateAt() from RepositoryProfileIssues  
	 */
	@Test
	public void testsetUpdateAt() {
		
		repoprofileissues.setUpdateAt("abc");
		String actual = repoprofileissues.updated_at;
		String expected = "abc";
		assertEquals(expected,actual);
		
		//null checks
		repoprofileissues.setUpdateAt(null);
		assertNull(repoprofileissues.getUpdateAt());
		
	}
	
	/**
	 * Test to Validate getUpdateAt() from RepositoryProfileIssues  
	 */
	@Test
	public void testgetUpdateAt() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getUpdateAt();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	

}
