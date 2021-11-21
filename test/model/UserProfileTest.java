package model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class is for Testing User Profile Model Class
 *  
 * @author Siddhartha
 * @version 1.0
 *
 */
public class UserProfileTest {

	/**
	 * This method is to test the getter methods of User Profile Model Class
	 */
	@Test
	public void testGetters() {
		UserProfile userprofile=new UserProfile("sj07","989238","MDQ6VXNlcjU4MzYDA--","https://avatars.githubusercontent.com/u/536680?v=4\r\n","https://api.github.com/users/sj07/repos", "sj07@gmail.com", "sj07twitter", "21","22", "https://api.github.com/users/sj07/subscriptions\r\n","https://api.github.com/users/sj07/orgs");
		assertEquals("sj07",userprofile.getLogin());
		assertEquals("989238",userprofile.getId());
		assertEquals("MDQ6VXNlcjU4MzYDA--",userprofile.node_id);
		assertEquals("https://avatars.githubusercontent.com/u/536680?v=4\r\n", userprofile.avatar_url);
		assertEquals("https://api.github.com/users/sj07/repos",userprofile.repos_url);
		assertEquals("sj07twitter", userprofile.twitter_username);
		assertEquals("sj07@gmail.com", userprofile.email);
		assertEquals("21",userprofile.followers);
		assertEquals("22", userprofile.following);
		assertEquals("https://api.github.com/users/sj07/subscriptions\r\n", userprofile.subscriptions_url);
		assertEquals("https://api.github.com/users/sj07/orgs", userprofile.organizations_url);
	}

	/**
	 * This method is to test the setter methods of User Profile Model Class
	 */
	@Test
	public void testSetters() {
		
		UserProfile userprofile= new UserProfile();
		
		userprofile.setLogin("login");
		userprofile.setId("id");
		userprofile.setNode_id("node_id");
		userprofile.setAvatar_url("avatar_url");
		userprofile.setRepos_url("repos_url");
		userprofile.setEmail("email");
		userprofile.setTwitter_username("twitter_username");
		userprofile.setFollowers("followers");
		userprofile.setFollowing("following");
		userprofile.setSubscriptions_url("subscriptions_url");
		userprofile.setOrganizations_url("organizations_url");
		
		assertEquals("login", userprofile.getLogin());
		assertEquals("id",userprofile.getId());
		assertEquals("node_id",userprofile.getNode_id());
		assertEquals("avatar_url",userprofile.getAvatar_url());
		assertEquals("repos_url",userprofile.getRepos_url());
		assertEquals("email",userprofile.getEmail());
		assertEquals("twitter_username",userprofile.getTwitter_username());
		assertEquals("followers",userprofile.getFollowers());
		assertEquals("following",userprofile.getFollowing());
		assertEquals("subscriptions_url", userprofile.getSubscriptions_url());
		assertEquals("organizations_url",userprofile.getOrganizations_url());
	
	}

}
