package service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import model.UserProfile;
import model.UserRepository;
import play.libs.Json;

public class UserServiceTest {

	@Test
	public void test() throws InterruptedException, ExecutionException {
			
		UserProfile user_profile= new UserProfile();
		List<UserRepository> user_repository= new ArrayList<UserRepository>();
		UserService us=new UserService();
		JsonNode json1 = Json.parse("{\"login\":\"a\", \n \"id\":\"b\", \n  \"node_id\":\"c\" , \n  \"avatar_url\":\"d\" , \n \"repos_url\":\"e\" ,\n  \"email\":\"f\" ,\n \"twitter_username\":\"g\" ,\n \"followers\":\"h\" ,\n \"following\":\"i\" ,\n \"subscriptions_url\":\"j\" ,\n \"organizations_url\":\"k\"}");
		user_profile = us.getUser(json1);
		UserProfile expected = new UserProfile("a","b","c","d","e","f","g","h","i","j","k");
		assertEquals(expected.getLogin(), user_profile.getLogin());
		assertEquals(expected.getId(), user_profile.getId());
		assertEquals(expected.getNode_id(), user_profile.getNode_id());
		assertEquals(expected.getAvatar_url(), user_profile.getAvatar_url());
		assertEquals(expected.getEmail(), user_profile.getEmail());
		assertEquals(expected.getTwitter_username(), user_profile.getTwitter_username());
		assertEquals(expected.getFollowers(), user_profile.getFollowers());
		assertEquals(expected.getFollowing(), user_profile.getFollowing());
		assertEquals(expected.getSubscriptions_url(), user_profile.getSubscriptions_url());
		assertEquals(expected.getOrganizations_url(), user_profile.getOrganizations_url());
		
		
		JsonNode json2 = Json.parse("{ \"name\":\"b\", \"issues_url\":\"c\", \"owner\": {\"login\":\"a\"} }");
		user_repository = us.getUser_repository(json2);
		System.out.println("7879798798798798798798798798798779787987979879879879879879879879798797987987979879879879797");
		System.out.println(json2);
		List<UserRepository> expected2 = new ArrayList<UserRepository>();
		expected2.add(new UserRepository("a","b","c"));
		assertTrue(EqualsBuilder.reflectionEquals(expected2.get(0),user_repository.get(0)));
		//assertTrue(expected2.equals(user_repository));		
	}
}
