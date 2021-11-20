package controllers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.mvc.Http.MultipartFormData.Part;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.CommitStat;
import model.GithubApi;
import model.GithubApiMock;
import model.Issues;
import model.Repository;
import play.Application;
import play.cache.AsyncCacheApi;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.mvc.Call;
import play.mvc.Http;
import play.mvc.*;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.routing.RoutingDsl;
import play.server.Server;
import play.test.Helpers;
import play.test.WithApplication;
import service.IssueStatService;

import java.io.FileNotFoundException;
import java.lang.*;
import views.html.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.contentAsString;
import static play.inject.Bindings.bind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import java.util.*;
import java.lang.String;

import play.*;
import play.mvc.*;
import models.*;
import static org.mockito.Mockito.*;

public class HomeControllerTest extends WithApplication {

	private static Application application;
	private static HomeController hcMock;
	static final int NOT_FOUND = 404;

	@Inject
	static
	AsyncCacheApi cache;
	@BeforeClass
	public static void startApp() throws InterruptedException, ExecutionException, FileNotFoundException {
		application = new GuiceApplicationBuilder().overrides(bind(GithubApi.class).to(GithubApiMock.class)).build();
		GithubApi testApi = application.injector().instanceOf(GithubApi.class);
		
		Helpers.start(application);

		hcMock = mock(HomeController.class);
		List<Repository> repoList = testApi.getRepositoryInfo("play", true, cache);
		when(hcMock.fetchRepositoryInfo("play", true)).thenReturn(repoList);
		when(hcMock.topics("play")).thenCallRealMethod();
	}

	@Override
	protected Application provideApplication() {
		return new GuiceApplicationBuilder().build();
	}

	// Testing the Index page for HTML Response
	@Test
	public void testIndex() {
		Http.RequestBuilder request = new Http.RequestBuilder().method(GET).uri("/");

		Result result = route(app, request);
		assertEquals(OK, result.status());

	}

	// Test Index Page to return a HTML Response with expected status code,content
	// type and character set
	@Test
	public void testIndex1() throws InterruptedException, ExecutionException {
		// Result result = new HomeController.index();
		RequestBuilder request = Helpers.fakeRequest(routes.HomeController.index());
		Result result = route(app, request);
		assertEquals(OK, result.status());
		assertEquals("text/html", result.contentType().get());
		assertEquals("utf-8", result.charset().get());
	}

	// Testing Controller Action through Routing : Good and Bad Route Testing
	@Test
	public void testBadRouteForIndex() {
		RequestBuilder request = Helpers.fakeRequest().method(GET).uri("/xx/Kiwi");

		Result result = route(app, request);
		assertEquals(NOT_FOUND, result.status());
	}

	@Test
	public void testGoodRouteCallForIndex() {
		RequestBuilder request = Helpers.fakeRequest(routes.HomeController.index());
		Result result = route(app, request);
		// ###replace: assertEquals(OK, result.status());
		assertEquals(OK, result.status());
		// assertEquals(NOT_FOUND, result.status()); // NOT_FOUND since the routes files
		// aren't used
	}

	@Test
	public void testTopics() throws InterruptedException, ExecutionException, FileNotFoundException {
		Result result = hcMock.topics("play");
		assertEquals(OK, result.status());
		assertEquals("text/html", result.contentType().get());
		assertEquals("utf-8", result.charset().get());
		assertTrue(contentAsString(result).contains("play"));
	}
	
    
    @Test
    public void testCommitPage() throws InterruptedException, ExecutionException, FileNotFoundException  {
      GithubApi testApi = application.injector().instanceOf(GithubApi.class);
      CommitStat commStat = testApi.getCommitStatistics("test", "repo", cache);
      Result result = play.mvc.Results.ok(commit.render(commStat));
      assertEquals(OK, result.status());
      assertEquals("text/html", result.contentType().get());
      assertEquals("utf-8", result.charset().get());
    } 
    
    @Test
    public void testIssuesPage() throws InterruptedException,ExecutionException,FileNotFoundException{
    	
    	GithubApi testApi=application.injector().instanceOf(GithubApi.class);
    	List<Issues> issuesList=testApi.getIssuesFromResponse("er1", "s228", cache);
    	
    	IssueStatService issueStatService=new IssueStatService();
  	  
    	List[] frequencyList=issueStatService.wordCountDescening(issuesList);
    	
    	Result result=Results.ok(issues.render(issuesList,frequencyList[0],frequencyList[1],"s228"));
    	
    	assertEquals(OK,result.status());
    	
    	assertEquals("text/html",result.contentType().get());
    	
    	assertEquals("utf-8", result.charset().get());
    }

	@AfterClass
	public static void stopApp() {
		Helpers.stop(application);
	}


}