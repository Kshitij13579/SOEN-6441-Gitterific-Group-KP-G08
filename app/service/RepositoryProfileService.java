package service;

import model.RepositoryProfile;
import model.RepositoryProfileIssues;
import model.RepositoryProfileCollaborators;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.*;

public class RepositoryProfileService {

	RepositoryProfile repositoryprofile;
	ConfigFactory config;
	
	public RepositoryProfileService() {
		this.repositoryprofile = new RepositoryProfile();
	}
	
	public RepositoryProfileService(RepositoryProfile repositoryprofile) {
		this.repositoryprofile = repositoryprofile;
	}
	
	public RepositoryProfile getRepositoryProfile(JsonNode json)  throws InterruptedException, ExecutionException {
		String login = json.get("owner").get("login").asText();
		String id = json.get("name").toString();
		String node_id = json.get("owner").get("node_id").toString();
		String avatar_url = json.get("owner").get("avatar_url").asText();
		String collaborators_url = json.get("collaborators_url").asText();
		String issues_url = json.get("issues_url").asText();
		String open_issues = json.get("open_issues_count").toString();
		return new RepositoryProfile(login,id,node_id,avatar_url,collaborators_url,issues_url,open_issues);
	}
	
	public List<RepositoryProfileIssues> getRepositoryProfile_Issue(JsonNode json) throws InterruptedException, ExecutionException  {
		 List<RepositoryProfileIssues> rpi = new ArrayList<>();
		 json.forEach(items -> {
			 String issue_number = items.get("number").toString();
			 String issue_title = items.get("title").toString();
			 String state = items.get("state").toString();
			 String created_at = items.get("created_at").toString();
			 String updated_at = items.get("updated_at").toString();
			 rpi.add(new RepositoryProfileIssues(issue_number,issue_title,state,created_at,updated_at));
		 });
		 return rpi;
	}
	

	public List<String> getissuesList(JsonNode json){
		List<String> issues = new ArrayList<String>();
		json.forEach(items -> {
			issues.add(items.get("title").asText());
		});
		return issues;
	}
	public List<RepositoryProfileCollaborators> getRepositoryProfile_Collaborators(JsonNode json) throws InterruptedException, ExecutionException  {
		 List<RepositoryProfileCollaborators> rpc = new ArrayList<>();
		 json.forEach(items -> {
			 String login = items.get("login").asText();
			 String id = items.get("id").toString();
			 String role_name = items.get("role_name").toString();
			 String user_url = items.get("url").toString();
			 rpc.add(new RepositoryProfileCollaborators(login,id,role_name,user_url));
		 });
		 return rpc;
	}
}


