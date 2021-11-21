package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.inject.Bindings.bind;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.junit.AfterClass;
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

	private static GithubApi ghaMock;

	/**
	 * Sets up initial configuration required for the test cases
	 * Uses locally saved sample json for API testing
	 * Mocks the getResponse method for GithubApi class for testing
	 * @author Mrinal Rai
	 * @since 2021-11-20 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws FileNotFoundException
	 */
	@Inject
	static AsyncCacheApi cache;
	@BeforeClass
	public static void startApp() {
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
			when(ghaMock.getRepositoryInfo("play", cache)).thenCallRealMethod();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getRepsitoryInfo method in GithubApi class
	 * Asserts the repository list's size and the type of the data it is storing
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * 
	 * @author Mrinal Rai
	 * @since 2021-11-20
	 */
	@Test
	public void testGetRepositoryInfo() throws InterruptedException, ExecutionException {
		List<Repository> actualRepoList = ghaMock.getRepositoryInfo("play", cache);
		org.junit.Assert.assertTrue(actualRepoList.size()>0);
		org.junit.Assert.assertTrue(actualRepoList.get(0).getClass().getName() == "model.Repository");
	}
}
