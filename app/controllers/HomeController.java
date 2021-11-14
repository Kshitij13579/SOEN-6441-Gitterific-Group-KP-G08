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
import model.GithubApi;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.mvc.Http.MultipartFormData.Part;
import service.CommitStatService;
import service.IssueService;
import service.IssueStatService;
import service.RepositorySearchService;
import service.UserService;
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
        return ok(index.render(repoList));
  
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
    	
		return ok(index.render(globalRepoList));
    }
    

    public Result commits(String user, String repository) throws InterruptedException, ExecutionException {
    	
    	CommitStatService commStatService = new CommitStatService();
    	List<Commit> commitList = new ArrayList<Commit>();
    	
    	WSRequest request = ws.url("https://api.github.com/repos/"+user+"/"+repository+"/commits")
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"))
	              .addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("constants.commits_per_page"))
	              .addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("constants.commits_page"))
	              .setAuth(ConfigFactory.load().getString("constants.git_user"),ConfigFactory.load().getString("constants.git_token"));

    	CompletionStage<JsonNode> jsonPromise = this.cache.getOrElseUpdate(user+"-"+repository+"-list", 
    			new Callable<CompletionStage<JsonNode>>() {
    				public CompletionStage<JsonNode> call() {
    					return request.get().thenApply(r -> r.getBody(json()));
    				};
    	}, 3600);
    	
    	JsonNode commits = jsonPromise.toCompletableFuture().get();
    	
    	List<String> shaList = commStatService.getShaList(commits);
    	
    	shaList.forEach(sha -> {
    		WSRequest r = ws.url("https://api.github.com/repos/"+user+"/"+repository+"/commits/"+sha)
  	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"))
    		      .setAuth(ConfigFactory.load().getString("constants.git_user"),ConfigFactory.load().getString("constants.git_token"));

    		CompletionStage<JsonNode> jsonCommit = this.cache.getOrElseUpdate(sha, 
        			new Callable<CompletionStage<JsonNode>>() {
        				public CompletionStage<JsonNode> call() {
        					return r.get().thenApply(r -> r.getBody(json()));
        				};
        	}, 3600);
    		
    	    try {
    	    	
				JsonNode temp = jsonCommit.toCompletableFuture().get();
				commitList.add(new Commit(
						       new Author(temp.get("commit").get("author").get("name").asText(), 
						                  (temp.get("author").has("login")) ? temp.get("author").get("login").asText() : "null",
						                  0
						       ),
						       sha,
						       (temp.has("stats")) ? temp.get("stats").get("additions").asInt() : 0 , 
						       (temp.has("stats")) ? temp.get("stats").get("deletions").asInt() : 0 ));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	});
    	
    	
    	
    	CommitStat commitStat = new CommitStat(commStatService.getTopCommitterList(commitList)
    			                              ,commStatService.getAvgAddition(commitList)
    			                              ,commStatService.getAvgDeletion(commitList)
    			                              ,commStatService.getMaxAddition(commitList)
    			                              ,commStatService.getMaxDeletion(commitList)
    			                              ,commStatService.getMinAddition(commitList)
    			                              ,commStatService.getMinDeletion(commitList)
    			                              ,repository
    			                              );
    	
    	
    	return ok(commit.render(commitStat));
   }
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
    
	public Result topics(String topic) throws InterruptedException, ExecutionException, FileNotFoundException {
		List<Repository> repoList = this.ghApi.getRepositoryInfo(topic, true, this.cache);
    	return ok(topicPage.render(repoList, topic));
	}
	
	  public Result issues(String user, String repository) throws InterruptedException, ExecutionException{
	  
	  IssueService issueService=new IssueService();
	  
	  IssueStatService issueStatService=new IssueStatService();
	  List<Issues> issuesList=new ArrayList<Issues>();
	  
	  WSRequest request =
	  ws.url("https://api.github.com/repos/"+user+"/"+repository+"/issues")
	  .addHeader(GIT_HEADER.CONTENT_TYPE.value,
	  ConfigFactory.load().getString("git_header.Content-Type"))
	  .addQueryParameter(GIT_PARAM.PER_PAGE.value,
	  ConfigFactory.load().getString("issues_per_page"))
	  .addQueryParameter(GIT_PARAM.PAGE.value,
	  ConfigFactory.load().getString("issues_page") );
	  
	  CompletionStage<JsonNode>
	  jsonPromise=request.get().thenApply(r->r.getBody(json()));
	  
	  JsonNode repoIssues=jsonPromise.toCompletableFuture().get();
	  
	  issuesList=issueService.getTitleList(repoIssues);
	 
	  List[] frequencyList=issueStatService.wordCountDescening(issuesList);
	  
	  return ok(issues.render(issuesList,frequencyList[0],frequencyList[1],repository));
	  
	  }
	 
}
