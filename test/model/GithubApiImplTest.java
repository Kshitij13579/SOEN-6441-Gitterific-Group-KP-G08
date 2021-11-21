package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.inject.Bindings.bind;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.ConfigFactory;

import controllers.HomeController;
import play.Application;
import play.cache.AsyncCacheApi;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.Helpers;
import service.RepositorySearchService;

public class GithubApiImplTest {

	private GithubApi ghaMock;
	

	@Inject
	AsyncCacheApi cache;
	@Before
	public void startApp() {
		ghaMock = org.mockito.Mockito.mock(GithubApiImpl.class);
		String testResources = System.getProperty("user.dir") + "/test/resources/play.json";
		java.io.File file = new java.io.File(testResources);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = null;
		try {
			json = mapper.readTree(file);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			when(ghaMock.getResponse("topic:play", "10","1", "updated", cache)).thenReturn(json);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			when(ghaMock.getRepositoryInfo("play", true, cache)).thenCallRealMethod();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetRepositoryInfo() throws InterruptedException, ExecutionException {
		List<Repository> actualRepoList = ghaMock.getRepositoryInfo("play", true, cache);
		org.junit.Assert.assertTrue(actualRepoList.size()>0);
		org.junit.Assert.assertTrue(actualRepoList.get(0).getClass().getName() == "model.Repository");
	}
	
	@Test
	public void testGetIssuesFromRespose() throws InterruptedException, ExecutionException {
		
		ghaMock = org.mockito.Mockito.mock(GithubApiImpl.class);

		String testJson=System.getProperty("user.dir") +"/test/resources/issues.json";
		
		File tempFile=new File(testJson);
		ObjectMapper objectMapper=new ObjectMapper();
		
		JsonNode tempJson=null;
		
		try {
			tempJson=objectMapper.readTree(tempFile);
			
		}catch (Exception e) {
			
			// TODO: handle exception
		}	
		
		List<Issues> titleIssues=new ArrayList<Issues>();
		  
		tempJson.forEach(t->{ 	
			
			 String title=t.get("title").asText(); 
			 titleIssues.add(new Issues(title));
			 
			 });
		
		try {
			
			when(ghaMock.getIssuesFromResponse("er1", "s228", cache)).thenReturn(titleIssues);
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Issues> actualIssues=ghaMock.getIssuesFromResponse("er1", "s228", cache);
		assertTrue(actualIssues.size()>0);
		assertTrue(actualIssues.get(0).getClass().getName() == "model.Issues");
	}
	
}

