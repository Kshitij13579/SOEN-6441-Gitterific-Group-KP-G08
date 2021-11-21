package model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is the Test Class for Issues Model
 * @author Akshay
 * @version 1.0
 *
 */
public class IssuesTest {
	
	/**
	 * Creates issues variable
	 */
	private static Issues issues;

	/**
	 * Environment Setup for tests
	 * @author Akshay
	 * 
	 */
	@BeforeClass
	public static void init() {
		
		issues=new Issues();
		
	}
	
	/**
	 * Method tests setters
	 * @author Akshay
	 * 
	 */
	@Test
	public void testSetter() {
		
		issues.setTitle("testIssue");
		assertEquals("testIssue","testIssue");
		
	}
	
	/**
	 * Method tests Null Values
	 * @author Akshay
	 */
	@Test
	public void testNullSetter() {
	
		//null checks
		issues.setTitle(null);
		assertNull(issues.getTitle());
		
	}
	
	/**
	 * Method tests Getter
	 * @author Akshay
	 * 
	 */
	@Test
	public void testGetter() {
		
		Issues getIssues=new Issues("TestIssue");
		assertEquals("TestIssue",getIssues.getTitle());
		
	}
	
	/**
	 * Method tests Null Values
	 * @author Akshay
	 */
	@Test
	public void testNullGetter() {
		
		//null checks
		issues.setTitle(null);
		assertNull(issues.getTitle());
	}
	
	/**
	 * Teardown Method
	 */
	@AfterClass
	public static void stop() {
		
	
	}

}
