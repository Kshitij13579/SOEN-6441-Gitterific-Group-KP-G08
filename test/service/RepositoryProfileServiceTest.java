package service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.builder.EqualsBuilder;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import model.RepositoryProfile;
import model.RepositoryProfileCollaborators;
import model.RepositoryProfileIssues;
import play.libs.Json;

public class RepositoryProfileServiceTest {
	

	


	@Test
	public void testgetRepositoryProfile() throws InterruptedException, ExecutionException {
		
		// Calculated Repos from dummy data
		RepositoryProfileService rps=new RepositoryProfileService();
		JsonNode json = Json.parse("{\n\"owner\": {\n  \"login\": \"abc\", \"node_id\": \"abc\",\"avatar_url\": \"abc\"\n  },\n  \"name\": \"def\",\n  \"collaborators_url\": \"pqr\",\n \"issues_url\": \"pqr\",\n \"open_issues_count\": \"pqr\"\n}");
		RepositoryProfile repos = rps.getRepositoryProfile(json);
		
		// Expected expectedRepo
		RepositoryProfile expectedRepo = new RepositoryProfile("abc","def","ghi","mno","pqr","xyz","rst");
		assertTrue(EqualsBuilder.reflectionEquals(repos.login.charAt(0),expectedRepo.login.charAt(0)));

	}

	@Test
	public void testgetRepositoryProfile_Issue() throws InterruptedException, ExecutionException {
		
		// Calculated RepoList from dummy data
		RepositoryProfileService rps=new RepositoryProfileService();
		JsonNode json = Json.parse("[{  \"number\": \"def\",  \"title\": \"pqr\", \"state\": \"pqr\", \"created_at\": \"pqr\" , \"updated_at\": \"pqr\" }]");
		//System.out.println(json);
		List<RepositoryProfileIssues> rpi = rps.getRepositoryProfile_Issue(json);
		//System.out.println(rpi.get(0).issue_number);
		// Expected RepoList
		RepositoryProfileIssues expectedRepo = new RepositoryProfileIssues("def","pqr","pqr","pqr","pqr");
		//RepositoryProfileIssues expectedRepo = new RepositoryProfileIssues("def");
		List<RepositoryProfileIssues> expectedRepos = new ArrayList<RepositoryProfileIssues>();
		expectedRepos.add(expectedRepo);
		//System.out.println(expectedRepos.get(0).issue_number);
		assertTrue(EqualsBuilder.reflectionEquals(expectedRepos.get(0),rpi.get(0)));
	}
	
	@Test
	public void testgetRepositoryProfile_Collaborators() throws InterruptedException, ExecutionException {
		
		// Calculated RepoList from dummy data
		RepositoryProfileService rps=new RepositoryProfileService();
		JsonNode json = Json.parse("[{  \"login\": \"def\",  \"id\": \"pqr\", \"role_name\": \"pqr\", \"url\": \"pqr\" }]");
		//System.out.println(json);
		List<RepositoryProfileCollaborators> rpc = rps.getRepositoryProfile_Collaborators(json);
		System.out.println(rpc.get(0).login);
		// Expected RepoList
		RepositoryProfileCollaborators expectedRepo = new RepositoryProfileCollaborators("def","pqr","pqr","pqr");
		//RepositoryProfileIssues expectedRepo = new RepositoryProfileIssues("def");
		List<RepositoryProfileCollaborators> expectedRepos = new ArrayList<RepositoryProfileCollaborators>();
		expectedRepos.add(expectedRepo);
		//System.out.println(expectedRepos.get(0).login);
		assertTrue(EqualsBuilder.reflectionEquals(expectedRepos.get(0),rpc.get(0)));
	}

}
