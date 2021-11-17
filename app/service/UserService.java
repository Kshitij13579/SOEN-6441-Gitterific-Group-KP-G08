package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import model.Repository;
import model.UserProfile;
import model.UserRepository;

public class UserService {
	
	UserProfile userprofile;
	ConfigFactory config;
	
	public UserService() {
		this.userprofile = new UserProfile();
	}
	
	public UserService(UserProfile userprofile) {
		this.userprofile = userprofile;
	}
	
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
    
    public List<UserRepository> getUser_repository(JsonNode json) throws InterruptedException, ExecutionException {
		
    	List<UserRepository> repos= new ArrayList<>();
    	
        json.forEach(items ->{
        	String login = items.get("owner").get("login").asText();
        	String id = items.get("name").toString();
        	String name = items.get("issues_url").toString();
        	repos.add(new UserRepository(login,id,name));
        });

		return repos;
    
}
}


