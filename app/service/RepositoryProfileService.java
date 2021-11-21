package service;

import model.RepositoryProfile;
import model.RepositoryProfileIssues;
import model.RepositoryProfileCollaborators;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.*;

/**
 * The RepositoryProfileService class is used  to convert JSON Data coming from GIT API based on repository model objects - RepositoryProfile, RepositoryProfileIssues and RepositoryProfileCollaborations.
 * @author Yogesh Yadav
 * @version 1.0
 * @since   2021-11-20 
 */
public class RepositoryProfileService {

	/**
	 * Java Variable - Object of Class -  RepositoryProfile, ConfigFactory
	 */
	RepositoryProfile repositoryprofile;
	ConfigFactory config;
	
	/**
	 * Constructor : to Initialize RepositoryProfile object
	 */
	public RepositoryProfileService() {
		this.repositoryprofile = new RepositoryProfile();
	}
	
	
	/**
	 * Method to covert JSON object from GIT API Call from Controller.repository_profile and initialize RepositoryProfile object.
	 * @param json
	 * @return RepositoryProfile object
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public RepositoryProfile getRepositoryProfile(JsonNode json)  throws InterruptedException, ExecutionException {
		String login = json.get("owner").get("login").asText();
		String id = json.get("name").asText();
		String node_id = json.get("owner").get("node_id").asText();
		String avatar_url = json.get("owner").get("avatar_url").asText();
		String collaborators_url = json.get("collaborators_url").asText();
		String issues_url = json.get("issues_url").asText();
		String open_issues = json.get("open_issues_count").asText();
		return new RepositoryProfile(login,id,node_id,avatar_url,collaborators_url,issues_url,open_issues);
	}
	
	
	/**
	 * Method to covert JSON object from GIT API Call from Controller.repository_profile and initialize RepositoryProfileissues object. 
	 * @param json
	 * @return List of RepositoryProfileIssues objects
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public List<RepositoryProfileIssues> getRepositoryProfile_Issue(JsonNode json) throws InterruptedException, ExecutionException  {
		 List<RepositoryProfileIssues> rpi = new ArrayList<>();
		 json.forEach(items -> {
			 String issue_number = items.get("number").asText();
			 String issue_title = items.get("title").asText();
			 String state = items.get("state").asText();
			 String created_at = items.get("created_at").asText();
			 String updated_at = items.get("updated_at").asText();
			 rpi.add(new RepositoryProfileIssues(issue_number,issue_title,state,created_at,updated_at));
		 });
		 return rpi;
	}
	

	/**
	 * Method to covert JSON object from GIT API Call from Controller.repository_profile and initialize RepositoryProfileCollborations object.
	 * @param json
	 * @return List of RepositoryProfileCollaborations objects
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public List<RepositoryProfileCollaborators> getRepositoryProfile_Collaborators(JsonNode json) throws InterruptedException, ExecutionException  {
		 List<RepositoryProfileCollaborators> rpc = new ArrayList<>();
		 json.forEach(items -> {
			 String login = items.get("login").asText();
			 String id = items.get("id").asText();
			 String role_name = items.get("role_name").asText();
			 String user_url = items.get("url").asText();
			 rpc.add(new RepositoryProfileCollaborators(login,id,role_name,user_url));
		 });
		 return rpc;
	}
}


