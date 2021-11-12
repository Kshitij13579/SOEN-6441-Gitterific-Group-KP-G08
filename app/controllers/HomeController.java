package controllers;

import javax.inject.Inject;

import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import akka.stream.javadsl.Source;
import akka.util.ByteString;
import model.GIT_HEADER;
import model.GIT_PARAM;
import model.Issues;
import model.Repository;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.mvc.Http.MultipartFormData.Part;
import service.IssueService;
import service.IssueStatService;
import service.RepositorySearchService;
import views.html.*;
import play.data.Form;
import play.data.FormFactory;
import play.libs.ws.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller implements WSBodyReadables {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	
	@Inject
	WSClient ws;
	
    public Result index() throws InterruptedException, ExecutionException {
    	
    	List<Repository> repoList = new ArrayList<Repository>();
        return ok(index.render(repoList));
  
    }
    
    public Result fetch(String query) throws InterruptedException, ExecutionException {
    	
    	RepositorySearchService repoService = new RepositorySearchService();
    	List<Repository> repoList = new ArrayList<Repository>();
    	
    	WSRequest request = ws.url(ConfigFactory.load().getString("git_search_repo_url"))
    			              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("git_header.Content-Type"))
    			              .addQueryParameter(GIT_PARAM.QUERY.value, query)
    			              .addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("repo_per_page"))
    			              .addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("repo_page"));
    	
    	CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.getBody(json()));
    	repoList = repoService.getRepoList(jsonPromise.toCompletableFuture().get());
		return ok(index.render(repoList));
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
	  
	  Map<String,Long> test=issueStatService.wordCountDescening(issuesList);
	  
	  System.out.println(test);
	  
	  return ok(issues.render(issuesList)); }
	 
}
