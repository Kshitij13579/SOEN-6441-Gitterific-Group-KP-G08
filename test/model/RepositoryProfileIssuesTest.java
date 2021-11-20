package model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class RepositoryProfileIssuesTest {

	private static RepositoryProfileIssues repoprofileissues;
	
	@BeforeClass
	public static void init() {
		
		repoprofileissues=new RepositoryProfileIssues();
		
	}
	
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
	
	@Test
	public void testgetIssueNumber() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getIssueNumber();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
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
	
	@Test
	public void testgetIssueTitle() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getIssueTitle();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
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
	
	@Test
	public void testgetState() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getState();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
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
	
	@Test
	public void testgetCreatedAt() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getCreatedAt();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	
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
	
	@Test
	public void testgetUpdateAt() {
		
		RepositoryProfileIssues repoprofileisuesisuesGet  = new RepositoryProfileIssues("abc","abc","abc","abc","abc");
		String actual = repoprofileisuesisuesGet.getUpdateAt();
		String expected = "abc";
		assertEquals(expected,actual);
		
		
	}
	

}
