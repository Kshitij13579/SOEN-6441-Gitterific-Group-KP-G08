package model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;
/**
 * Test class for Author model.
 * @author Kshitij Yerande
 * @version 1.0
 * @since 2021-11-20
 */
public class AuthorTest {
    
	@InjectMocks
	Author a = new Author();
	Author b = new Author();
	
	/**
	 * Test to validate getter and setter.
	 * 1. Getter - validate the variable values of Author object
	 * 2. Setter - check that the values are set in Author object.
	 */
	@Test
	public void GettersandSettersTest() {
		a.setCommits(10);
		a.setLogin("jack");
		a.setName("jack ryan");
		
		assertEquals(a.getCommits(),10);
		assertEquals(a.getName(),"jack ryan");
		assertEquals(a.getLogin(),"jack");
		
		a.setCommits(0);
		a.setLogin(null);
		a.setName(null);
		
		assertNull(a.getLogin());
		assertNull(a.getName());
		assertEquals(a.getCommits(),0);
	}
	
	/**
	 * Test compare two Author objects
	 */
	@Test
	public void EqualsTest() {
		a.setCommits(10);
		a.setLogin("jack");
		a.setName("jack ryan");
		
		b.setCommits(10);
		b.setLogin("jack");
		b.setName("jack ryan");
		
		assertEquals(a, b);
		assertTrue(a.equals(a));
		assertFalse(a.equals(null));
	}
	
	/**
	 * Test for hashCode method to verify the hashcode of Author object.
	 */
	@Test
	public void hashCodeTest() {
		assertEquals(a.hashCode(), 29791);
	}
	
	
}
