package model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class IssuesTest {
	
	private static Issues issues;

	@BeforeClass
	public static void init() {
		
		issues=new Issues();
		
	}
	
	@Test
	public void testSetter() {
		
		
		issues.setTitle("testIssue");
		
		assertEquals("testIssue","testIssue");
		
		//null checks
		issues.setTitle(null);
		assertNull(issues.getTitle());
		
	}
	
	@Test
	public void testGetter() {
		
		Issues getIssues=new Issues("TestIssue");
		
		assertEquals("TestIssue",getIssues.getTitle());
		
		//null checks
		issues.setTitle(null);
		assertNull(issues.getTitle());
		
	}
	
	@AfterClass
	public static void stop() {
		
	
	}

}
