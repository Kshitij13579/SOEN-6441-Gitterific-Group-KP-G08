package controllers;

import javax.inject.Inject;
import play.cache.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

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
	
	@Inject
	  public HomeController(AsyncCacheApi cache) {
	    this.cache = cache;
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
	
    public Result index() throws InterruptedException, ExecutionException {
    	
    	List<Repository> repoList = new ArrayList<Repository>();
    	globalRepoList = new ArrayList<Repository>();
        return ok(index.render(repoList, ""));
  
    }
    
    @SuppressWarnings("deprecation")
	public Result search(String query) throws InterruptedException, ExecutionException {
    	
    	RepositorySearchService repoService = new RepositorySearchService();
															 
    	
    	WSRequest request = ws.url(ConfigFactory.load().getString("constants.git_search_repo_url"))
    			              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"))
    			              .addQueryParameter(GIT_PARAM.QUERY.value, query)
    			              .addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("constants.repo_per_page"))
    			              .addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("constants.repo_page"));

    	CompletionStage<JsonNode> jsonPromise = this.cache.getOrElseUpdate(request.getUrl()
    			+ GIT_PARAM.QUERY.value + query 
    			+ GIT_PARAM.PER_PAGE.value + ConfigFactory.load().getString("constants.repo_per_page")
    			+ GIT_PARAM.PAGE.value + ConfigFactory.load().getString("constants.repo_page"), 
    			new Callable<CompletionStage<JsonNode>>() {
    				public CompletionStage<JsonNode> call() {
    					return request.get().thenApply(r -> r.getBody(json()));
    				};
    	}, Integer.parseInt(ConfigFactory.load().getString("constants.CACHE_EXPIRY_TIME")));
    	
    	if(globalRepoList.isEmpty()) {
    		globalRepoList = repoService.getRepoList(jsonPromise.toCompletableFuture().get());
    	}else {
    		globalRepoList.addAll(repoService.getRepoList(jsonPromise.toCompletableFuture().get()));
    	}
    	
		return ok(index.render(globalRepoList, ""));
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
	 * @param username user name
	 * @return a HTML Response
	 * @throws InterruptedException InterruptedException Exception during runtime
	 * @throws ExecutionException ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
	 */
    public Result user_profile(String username) throws InterruptedException, ExecutionException{
    	
    	UserService repoService = new UserService();
    	UserProfile repoList = new UserProfile();
    	WSRequest request = ws.url(ConfigFactory.load().getString("constants.git_search_user_url")+"/"+username)
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"));
	             //; .addQueryParameter(GIT_PARAM.QUERY.value, username);
	              //.addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("constants.repo_per_page"))
	              //.addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("constants.repo_page"))
	

    	CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.getBody(json()));
    	repoList = repoService.getUser(jsonPromise.toCompletableFuture().get());
    	return ok(users.render(repoList));
    }
    
	/**
	 * This method retrieves user repository by taking user name as an input
	 * An API call is made and response is then processed.
	 * @param username user name
	 * @return a HTML Response
	 * @throws InterruptedException InterruptedException Exception during runtime
	 * @throws ExecutionException ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
	 */
    public Result user_repository(String username) throws InterruptedException, ExecutionException{
    	UserService repoService = new UserService();
    	List<UserRepository> repoList = new ArrayList<>();
    	WSRequest request = ws.url(ConfigFactory.load().getString("constants.git_search_user_url")+"/"+username+"/repos")
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"))
	             //; .addQueryParameter(GIT_PARAM.QUERY.value, username);
	              .addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("constants.repo_per_page_repo"))
	              .addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("constants.repo_page"));
	

    	CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.getBody(json()));
    	repoList = repoService.getUser_repository(jsonPromise.toCompletableFuture().get());
    	return ok(repositories.render(repoList));
    }
    
	 public Result repository_profile(String username, String repository) throws InterruptedException, ExecutionException{
    	RepositoryProfileService rps = new RepositoryProfileService();
    	RepositoryProfile rp = new RepositoryProfile();
    	List<RepositoryProfileIssues> rpi = new ArrayList<>();
    	List<RepositoryProfileCollaborators> rpc = new ArrayList<>();
    	//Repository Profile
    	WSRequest request = ws.url(ConfigFactory.load().getString("constants.git_repositoryprofile_url")+"/"+username + "/" + repository)
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"));
	              //; .addQueryParameter(GIT_PARAM.QUERY.value, username);
	              //.addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("repo_per_page"))
	              //.addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("repo_page"))
    	CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.getBody(json()));
    	// Repository Issues
    	WSRequest req = ws.url(ConfigFactory.load().getString("constants.git_repositoryprofile_url")+"/"+username + "/" + repository + "/issues?sort=created&direction=desc&per_page=20&page=1")
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"));
	              //; .addQueryParameter(GIT_PARAM.QUERY.value, username);
	              //.addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("repo_per_page"))
	              //.addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("repo_page"))
  		CompletionStage<JsonNode> json_issues = req.get().thenApply(r -> r.getBody(json()));
  		//Repository Collabs
  		WSRequest req_collab = ws.url(ConfigFactory.load().getString("constants.git_repositoryprofile_url")+"/"+username + "/" + repository + "/collaborators")
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"))
	              //; .addQueryParameter(GIT_PARAM.QUERY.value, username);
	              //.addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("repo_per_page"))
	              //.addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("repo_page"))
	              .setAuth(ConfigFactory.load().getString("constants.git_user"),ConfigFactory.load().getString("constants.git_token"));
		CompletionStage<JsonNode> json_collab = req_collab.get().thenApply(r -> r.getBody(json()));
		System.out.println(json_collab.toCompletableFuture().get());
  		rp =  rps.getRepositoryProfile(jsonPromise.toCompletableFuture().get());	
  		rpi = rps.getRepositoryProfile_Issue(json_issues.toCompletableFuture().get());
  		
  		//System.out.println(json_collab.toCompletableFuture().get());
  		try {
	    	
  			rpc = rps.getRepositoryProfile_Collaborators(json_collab.toCompletableFuture().get());
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
    	return ok(index.render(repoList, topic));
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
	 * This method performs repository issues title statistics by taking user and repository name as input
	 * An API call is made and response is then processed and calculated stats.
	 * @param user user repository owner
	 * @param repository repository name
	 * @return a HTML Response
	 * @throws InterruptedException InterruptedException Exception during runtime
	 * @throws ExecutionException ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
	 */
	public Result issues(String user, String repository) throws InterruptedException, ExecutionException{

		
	  List<Issues> issuesList = this.ghApi.getIssuesFromResponse(user, repository, cache);
	  
	  IssueStatService issueStatService=new IssueStatService();
	  
	  List[] frequencyList=issueStatService.wordCountDescening(issuesList);
	  	  
	  return ok(issues.render(issuesList,frequencyList[0],frequencyList[1],repository));
	  
	  }
	 
}
