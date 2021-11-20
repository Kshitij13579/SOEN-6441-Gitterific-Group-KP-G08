package model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class is for Testing of User Repository Model Class
 *  
 * @author Siddhartha
 * @version 1.0
 *
 */
public class UserRepositoryTest {

	
	/**
	 * This method is to test the getter methods of User Repository Model Class
	 */
	@Test
	public void testGetters() {
		
		UserRepository userrepository=new UserRepository("sj07","soen6441","https://api.github.com/repos/sj07/SOEN6441/issues{/number}");
		assertEquals("sj07",userrepository.getLogin());
		assertEquals("soen6441",userrepository.getName());
		assertEquals("https://api.github.com/repos/sj07/SOEN6441/issues{/number}",userrepository.getReponame());
		}
	
	/**
	 * This method is to test the setter methods of User Repository Model Class
	 */	
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
