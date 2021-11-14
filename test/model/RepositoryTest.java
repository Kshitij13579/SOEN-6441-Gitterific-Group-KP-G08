package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RepositoryTest {

	@Test
	public void testGetters() {
		
	
		Repository repository=new Repository("er1","s228","https://api.github.com/repos/er1/s228/issues{/number}","https://api.github.com/repos/er1/s228/commits%7B/sha%7D%22");
		
		//assertFalse(repository.login.isEmpty());
		assertEquals("er1",repository.getLogin());
		//assertFalse(repository.name.isEmpty());
		assertEquals("s228",repository.getName());
		assertEquals("https://api.github.com/repos/er1/s228/issues{/number}",repository.issues_url);
		//assertFalse(repository.issues_url.isEmpty());
		
		//assertFalse(repository.commits_url.isEmpty());
		assertEquals("https://api.github.com/repos/er1/s228/commits%7B/sha%7D%22",repository.getCommits_url());
				
	}
	
	@Test
	public void testSettersGetters() {
		
		Repository repository=new Repository();
		
		repository.setName("testName");
		repository.setCommits_url("commitsTest");
		repository.setLogin("testLogin");
		repository.setIssues_url("testIssues");
		
		assertEquals("testName", repository.getName());
		assertEquals("commitsTest",repository.getCommits_url());
		assertEquals("testLogin",repository.getLogin());
		assertEquals("testIssues",repository.getIssues_url());
		
	}

}
