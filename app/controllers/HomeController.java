package controllers;

import javax.inject.Inject;

import org.checkerframework.checker.units.qual.g;

import play.cache.AsyncCacheApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import actors.IssueServiceActor;
import actors.CommitSupervisorActor;
import actors.CommitStatActor;
import actors.RepoSearchActor;
import actors.RepositoryProfileActor;
import actors.SupervisorActor;
import actors.UserProfileSearchActor;
import actors.UserRepositoryActor;
import actors.TopicSearchActor;
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
import java.util.concurrent.CompletableFuture;
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
	    system.actorOf(CommitSupervisorActor.getProps(),"commitSupervisorActor");
	  }
	
	
	@Inject
	WSClient ws;
    
    /**
     * An action that renders an HTML page with a welcome message.
     * @param request Http Request 
     * @return Result showing the 10 latest repositories for the query
     * @author Kshitij Yerande
     * @since 2021-12-07
     */
    public Result index(Http.Request request) throws InterruptedException, ExecutionException {

        return ok(index.render(request));
    }

	/**
	 * An action that renders an Topic HTML page with 10 latest repositories for the selected topic.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/topics/topicName</code>.
	 * 
	 * @author Mrinal Rai
	 * @param request Request sent by the topics page
	 * @param topic	selected by the user on the main search page
	 * @return Result showing the 10 latest repositories for the selected topic
	 */
    public Result topics(Http.Request request, String topic) throws InterruptedException, ExecutionException {
        return ok(topics.render(request, topic));
    }
    
	 /**
	 * Method to Accept Username and Repository from Index HTML Page and update Repository Profile HTML Page
	 * @author Yogesh Yadav
	 * @param username - Git Username
	 * @param repository - Git Repository name
	 * @return Goto Render Repository Profile HTML page
	 */
    public Result repository_profile(Http.Request request, String username, String repository) throws InterruptedException, ExecutionException {
        return ok(repositoryprofile.render(request, username, repository));
    }
    
	  /**
	   * Handles WebSocket for Index Page
	   * @return WebSocket object
	   * @author Kshitij Yerande
	   * @since  2021-12-07
	   */
    public WebSocket ws() {
    	return WebSocket.Json.accept(request -> ActorFlow.actorRef( ws -> RepoSearchActor.props(ws, cache,ghApi), actorSystem, materializer));
    }
    
    /**
     * Handles WebSocket for Topics Page
     * @return WebSocket
     */
    public WebSocket wsTopic() {
    	return WebSocket.Json.accept(request -> ActorFlow.actorRef( ws -> TopicSearchActor.props(ws, cache,ghApi), actorSystem, materializer));
    }
    
    /**
	   * Handles WebSocket for RepositoryProfile Page
	   * @return WebSocket object
	   * @author Yogesh Yadav
	   * @since  2021-12-07
	   */
    public WebSocket wsRepositoryProfile() {
    	return WebSocket.Json.accept(request -> ActorFlow.actorRef( ws -> RepositoryProfileActor.props(ws, cache,ghApi), actorSystem, materializer));
    
    }
    
    
    /**
	 * This method calls github API to get statistics of top 100 commits.
	 * An API call is made and response is then processed and calculated stats.
	 * @param user user repository owner
	 * @param repository repository name
	 * @return a HTML Response
	 */
	public Result commits(String user,String repository,Http.Request request) throws InterruptedException, ExecutionException {
    	return ok(commit.render(request));
    }
    
	
	 /**
	   * Handles WebSocket for Commits Page
	   * @return WebSocket object
	   * @author Kshitij Yerande
	   * @since  2021-12-07
	   */
    public WebSocket wsCommit() {
    	return WebSocket.Json.accept(request -> ActorFlow.actorRef( ws -> CommitStatActor.props(ws, cache,ghApi), actorSystem, materializer));
    }

	/**
	 * This method retrieves user profile by taking user name as an input
	 * An API call is made and response is then processed.
	 * @author Siddhartha
	 * @param username user name
	 * @return a HTML Response
	 */
    public Result user_profile(String username , Http.Request request) throws InterruptedException, ExecutionException{
    	
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
	 */
    public Result user_repository(String username, Http.Request request) throws InterruptedException, ExecutionException{


    	return ok(repositories.render(request));
    }
    
    public WebSocket wsur() {
    	return WebSocket.Json.accept(request -> ActorFlow.actorRef( ws -> UserRepositoryActor.props(ws,ghApi), actorSystem, materializer));
    }
	
	
	/**
	 * This method performs repository issues title statistics by taking user and repository name as input
	 * An API call is made and response is then processed and calculated statistics.
	 * @param Http Request
	 * @param user user repository owner
	 * @param repository repository name
	 * @return a HTML Response
	 * @author Akshay
	 * @since 2021-12-07
	 * 
	 */
	public Result issues(Http.Request request, String user, String repository) throws InterruptedException, ExecutionException{

	return ok(issues.render(request,user,repository));
	  
	  }
	  /**
	   * Handles WebSocket for Issue Statistics Page
	   * @return WebSocket object
	   * @author Akshay
	   * @since 2021-12-07
	   */
	public WebSocket wsIssue() {
	    	return WebSocket.Json.accept(request -> ActorFlow.actorRef( ws -> IssueServiceActor.props(ws, cache,ghApi), actorSystem, materializer));
	    }	 
}
