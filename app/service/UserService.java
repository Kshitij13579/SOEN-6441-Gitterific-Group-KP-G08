package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import model.Repository;
import model.UserProfile;
import model.UserRepository;

/**
 * This is the Service Class for User Profile and User Repository which process json repsonse from API
 * @author Siddhartha
 * @version 1.0
 *
 */
public class UserService {

	/**
	 * User Profile Object
	 */
	UserProfile userprofile;
	
	/**
	 * Empty User Service Constructor
	 */
	public UserService() {
		this.userprofile = new UserProfile();
	}
	
	/**
	 * Constructor to initialize to User service
	 * @param userprofile userprofile object
	 */
	public UserService(UserProfile userprofile) {
		this.userprofile = userprofile;
	}

    /**
     * This method process json and returns user details
     * 
     * @param json json response from API call
     * @return user profile object
     * @throws InterruptedException Exception during runtime
	 * @throws ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
     */
    public UserProfile getUser(JsonNode json) throws InterruptedException, ExecutionException {
		
        	String login = json.get("login").asText();
        	String id = json.get("id").asText();
        	String node_id = json.get("node_id").asText();
        	String avatar_url = json.get("avatar_url").asText();
        	String repos_url = json.get("repos_url").asText();
        	String email = json.get("email").asText();
 			String twitter_username = json.get("twitter_username").asText();
 			String followers = json.get("followers").asText();
 			String following = json.get("following").asText();
 			String subscriptions_url = json.get("subscriptions_url").asText();
 			String organizations_url = json.get("organizations_url").asText();
        	return new UserProfile(login, id, node_id, avatar_url, repos_url, email,
        			twitter_username,  followers,  following,  subscriptions_url,
        			 organizations_url);
        	}
   
    /**
     * This method process json and returns user details
     * 
     * @param json json response from API call
     * @return list of user repository
     * @throws InterruptedException Exception during runtime
	 * @throws ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
     */
    public List<UserRepository> getUser_repository(JsonNode json) throws InterruptedException, ExecutionException {
		
    	List<UserRepository> repos= new ArrayList<>();
    	
        json.forEach(items ->{
        	String login = items.get("owner").get("login").asText();
        	String name = items.get("name").asText();
        	String reponame = items.get("issues_url").asText();
        	repos.add(new UserRepository(login,name,reponame));
        });
		return repos;
     }
}


