package model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;

public class AuthorTest {
    
	@InjectMocks
	Author a = new Author();
	Author b = new Author();
	
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
	
	@Test
	public void hashCodeTest() {
		assertEquals(a.hashCode(), 29791);
	}
	
	
}
