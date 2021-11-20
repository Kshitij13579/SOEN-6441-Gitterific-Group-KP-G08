package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserRepositoryTest {

	@Test
	public void testGetters() {
		
		UserRepository userrepository=new UserRepository("sj07","soen6441","https://api.github.com/repos/sj07/SOEN6441/issues{/number}");
		assertEquals("sj07",userrepository.getLogin());
		assertEquals("soen6441",userrepository.getName());
		assertEquals("https://api.github.com/repos/sj07/SOEN6441/issues{/number}",userrepository.getReponame());
		}
	
	@Test
	public void testSetters() {
		
		UserRepository userrepository= new UserRepository();
		
		userrepository.setLogin("login");
		userrepository.setName("name");
		userrepository.setReponame("reponame");
		
		assertEquals("login", userrepository.getLogin());
		assertEquals("name",userrepository.getName());
		assertEquals("reponame",userrepository.getReponame());
	}
}
