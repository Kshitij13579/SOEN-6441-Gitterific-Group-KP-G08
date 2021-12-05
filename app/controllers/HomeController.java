package controllers;

import javax.inject.Inject;

import org.checkerframework.checker.units.qual.g;

import play.cache.AsyncCacheApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import actors.RepoSearchActor;
import actors.SupervisorActor;
import actors.UserProfileSearchActor;
import actors.UserRepositoryActor;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import model.Author;
import model.Commit;
import model.CommitStat;
import model.GIT_HEADER;
import model.GIT_PARAM;
import model.Issues;
import model.Repository;
import model.UserProfile;
import model.UserRepository;
import model.RepositoryProfile;
import model.RepositoryProfileCollaborators;
import model.RepositoryProfileIssues;							   
import model.GithubApi;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.mvc.Http.MultipartFormData.Part;

import service.CommitStatService;
import service.IssueStatService;
import service.RepositorySearchService;
import service.UserService;
import service.RepositoryProfileService;										

import views.html.*;
import play.data.Form;
import play.data.FormFactory;
import play.libs.streams.ActorFlow;
import play.libs.ws.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller implements WSBodyReadables {
	private AsyncCacheApi cache;
	@Inject
	private GithubApi ghApi;
	
	@Inject private ActorSystem actorSystem;
	@Inject private Materializer materializer;
	
	@Inject
	  public HomeController(AsyncCacheApi cache,ActorSystem system) {
	    this.cache = cache;
	    system.actorOf(SupervisorActor.getProps(),"supervisorActor");
	  }
	
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	
	@Inject
	WSClient ws;
	List<Repository> globalRepoList = new ArrayList<Repository>();  
	
//    public Result index() throws InterruptedException, ExecutionException {
//    	
//    	List<Repository> repoList = new ArrayList<Repository>();
//    	globalRepoList = new ArrayList<Repository>();
//        return ok(index.render(repoList, ""));
//  
//    }
    
    public Result index(Http.Request request) throws InterruptedException, ExecutionException {

        return ok(index.render(request));
    }
    
    public WebSocket ws() {
    	return WebSocket.Json.accept(request -> ActorFlow.actorRef( ws -> RepoSearchActor.props(ws, cache,ghApi), actorSystem, materializer));
    }
    
    /**
     * This method retrieves repositories by taking query as an input
	 * An API call is made and response is then processed.
     * @author Kshitij Yerande
     * @param query Search made by the user
     * @return Result showing the 10 latest repositories for the searched query
     * @throws InterruptedException
     * @throws ExecutionException
     * @since 2021-11-20
     */
    @SuppressWarnings("deprecation")
	public Result search(String query)  throws InterruptedException, ExecutionException, FileNotFoundException {    	
//    	if(globalRepoList.isEmpty()) {
//    		globalRepoList = this.fetchRepositories(query);
//    	}else {
//    		globalRepoList.addAll(this.fetchRepositories(query));
//    	}
		return ok(temp.render(globalRepoList, ""));
    }
    
    
    /**
	 * This method calls github API to get statistics of top 100 commits.
	 * An API call is made and response is then processed and calculated stats.
	 * @param user user repository owner
	 * @param repository repository name
	 * @return a HTML Response
	 * @throws InterruptedException InterruptedException Exception during runtime
	 * @throws ExecutionException ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
	 */
    public Result commits(String user, String repository) throws InterruptedException, ExecutionException {
    	
    	CommitStat commitStat = this.ghApi.getCommitStatistics(user, repository, cache);
    	return ok(commit.render(commitStat));
   }

	/**
	 * This method retrieves user profile by taking user name as an input
	 * An API call is made and response is then processed.
	 * @author Siddhartha
	 * @param username user name
	 * @return a HTML Response
	 * @throws InterruptedException InterruptedException Exception during runtime
	 * @throws ExecutionException ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
	 */
    public Result user_profile(String username , Http.Request request) throws InterruptedException, ExecutionException{
    	
//    	UserService repoService = new UserService();
//    	UserProfile repoList = new UserProfile();
//    	WSRequest request = ws.url(ConfigFactory.load().getString("constants.git_search_user_url")+"/"+username)
//	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"));
//	   
//    	CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.getBody(json()));
//    	repoList = repoService.getUser(jsonPromise.toCompletableFuture().get());
    	return ok(users.render(request));
    }
    
    public WebSocket wsup() {
    	return WebSocket.Json.accept(request -> ActorFlow.actorRef( ws -> UserProfileSearchActor.props(ws,ghApi), actorSystem, materializer));
    }
    
	/**
	 * This method retrieves user repository by taking user name as an input
	 * An API call is made and response is then processed.
	 * @author Siddhartha
	 * @param username user name
	 * @return a HTML Response
	 * @throws InterruptedException InterruptedException Exception during runtime
	 * @throws ExecutionException ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
	 */
    public Result user_repository(String username, Http.Request request) throws InterruptedException, ExecutionException{
//    	UserService repoService = new UserService();
//    	List<UserRepository> repoList = new ArrayList<>();
//    	WSRequest request = ws.url(ConfigFactory.load().getString("constants.git_search_user_url")+"/"+username+"/repos")
//	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"))
//	              .addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("constants.repo_per_page_repo"))
//	              .addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("constants.repo_page"));
//    	CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.getBody(json()));
//    	repoList = repoService.getUser_repository(jsonPromise.toCompletableFuture().get());
//      return ok(repositories.render(repoList));
    	return ok(users.render(request));

    }
    
    public WebSocket wsur() {
    	return WebSocket.Json.accept(request -> ActorFlow.actorRef( ws -> UserRepositoryActor.props(ws,ghApi), actorSystem, materializer));
    }
    
	 /**
	 * Method to Accept Username and Repository from Index HTML Page and update Repository Profile HTML Page
	 * @author Yogesh Yadav
	 * @param username - Git Username
	 * @param repository - Git Repository name
	 * @return Goto Render Repository Profile HTML page
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public Result repository_profile(String username, String repository) throws InterruptedException, ExecutionException{
		 
		    RepositoryProfileService rps = new RepositoryProfileService();
	    	RepositoryProfile rp = new RepositoryProfile();
	    	List<RepositoryProfileIssues> rpi = new ArrayList<>();
	    	List<RepositoryProfileCollaborators> rpc = new ArrayList<>();
	    	JsonNode reppprofile = this.ghApi.getRepositoryProfileFromResponse(username, repository, cache);
	    	JsonNode repoprofileissues = this.ghApi.getRepositoryProfileIssuesFromResponse(username, repository, cache);
	    	
	    	JsonNode repoprofilecollab = this.ghApi.getRepositoryProfileCollaborationsFromResponse(username, repository, cache);
	    	
	    	rp =  rps.getRepositoryProfile(reppprofile);	
	  		rpi = rps.getRepositoryProfile_Issue(repoprofileissues);
	  		
	  		try {
		    	
	  			rpc = rps.getRepositoryProfile_Collaborators(repoprofilecollab);
			} catch (Exception e) {
				e.printStackTrace();
	        } 
  		return ok(repositoryprofile.render(rp,rpi,rpc));
    }	
	 
	/**
	 * An action that renders an Topic HTML page with 10 latest repositories for the selected topic.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/topics/topicName</code>.
	 * 
	 * @author Mrinal Rai
	 * @param topic	selected by the user on the main search page
	 * @return Result showing the 10 latest repositories for the selected topic
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws FileNotFoundException
	 */
	public Result topics(String topic) throws InterruptedException, ExecutionException, FileNotFoundException {
		List<Repository> repoList = this.fetchRepositoryInfo(topic);
    	return ok(temp.render(repoList, topic));
	}
	
	/**
	 * Gets the response from Github API for the searched topic
	 * 
	 * @author Mrinal Rai
	 * @param topic selected by the user on the main search page 
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws FileNotFoundException
	 */
	public List<Repository> fetchRepositoryInfo(String topic) throws InterruptedException, ExecutionException, FileNotFoundException {
		return this.ghApi.getRepositoryInfo(topic, this.cache);
	}
	
	/**
	 * Gets the response from Github API for the searched query
	 * 
	 * @author Mrinal Rai
	 * @param topic selected by the user on the main search page 
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws FileNotFoundException
	 */
	public List<Repository> fetchRepositories(String query) throws InterruptedException, ExecutionException, FileNotFoundException {
		return this.ghApi.getRepositories(query, this.cache);
	}
	
	
	/**
	 * This method performs repository issues title statistics by taking user and repository name as input
	 * An API call is made and response is then processed and calculated statistics.
	 * @param user user repository owner
	 * @param repository repository name
	 * @return a HTML Response
	 * @throws InterruptedException InterruptedException Exception during runtime
	 * @throws ExecutionException ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
	 * @author Akshay
	 * 
	 */
	public Result issues(String user, String repository) throws InterruptedException, ExecutionException{

	  
	  List<Issues> issuesList = this.ghApi.getIssuesFromResponse(user, repository, cache);	  
	  IssueStatService issueStatService=new IssueStatService();
	  List[] frequencyList=issueStatService.wordCountDescening(issuesList);	  	  
	  return ok(issues.render(issuesList,frequencyList[0],frequencyList[1],repository));
	  
	  }
	 
}
