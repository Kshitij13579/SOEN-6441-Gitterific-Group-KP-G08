package controllers;

import javax.inject.Inject;

import org.w3c.dom.Document;
import play.cache.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import akka.stream.javadsl.Source;
import akka.util.ByteString;
import model.Commit;
import model.CommitStat;
import model.GIT_HEADER;
import model.GIT_PARAM;
import model.Repository;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.mvc.Http.MultipartFormData.Part;
import service.CommitStatService;
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
	
    public Result index() throws InterruptedException, ExecutionException {
    	
    	List<Repository> repoList = new ArrayList<Repository>();
        return ok(index.render(repoList));
    }
    
    @SuppressWarnings("deprecation")
	public Result search(String query) throws InterruptedException, ExecutionException {
    	
    	RepositorySearchService repoService = new RepositorySearchService();
    	List<Repository> repoList = new ArrayList<Repository>();
    	
    	WSRequest request = ws.url(ConfigFactory.load().getString("git_search_repo_url"))
    			              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("git_header.Content-Type"))
    			              .addQueryParameter(GIT_PARAM.QUERY.value, query)
    			              .addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("repo_per_page"))
    			              .addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("repo_page"));
    	CompletionStage<JsonNode> jsonPromise = this.cache.getOrElseUpdate(query, 
    			new Callable<CompletionStage<JsonNode>>() {
    				public CompletionStage<JsonNode> call() {
    					return request.get().thenApply(r -> r.getBody(json()));
    				};
    	}, 3600);	// caching response for 1 hour
    	repoList = repoService.getRepoList(jsonPromise.toCompletableFuture().get());
		return ok(index.render(repoList));
    }
    
    public Result commits(String user, String repository) throws InterruptedException, ExecutionException {
    	
    	CommitStatService commStatService = new CommitStatService();
    	List<Commit> commitList = new ArrayList<Commit>();
    	
    	WSRequest request = ws.url("https://api.github.com/repos/"+user+"/"+repository+"/commits")
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("git_header.Content-Type"))
	              .addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("commits_per_page"))
	              .addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("commits_page"))
	              .setAuth(ConfigFactory.load().getString("git_user"),ConfigFactory.load().getString("git_token"));

        CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.getBody(json()));
    	JsonNode commits = jsonPromise.toCompletableFuture().get();
    	
    	List<String> shaList = commStatService.getShaList(commits);
    	
    	shaList.forEach(sha -> {
    		WSRequest r = ws.url("https://api.github.com/repos/"+user+"/"+repository+"/commits/"+sha)
  	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("git_header.Content-Type"))
    		      .setAuth(ConfigFactory.load().getString("git_user"),ConfigFactory.load().getString("git_token"));

    		CompletionStage<JsonNode> jsonCommit = r.get().thenApply(j -> j.getBody(json()));
    	    try {
    	    	
				JsonNode temp = jsonCommit.toCompletableFuture().get();
				commitList.add(new Commit(temp.get("commit").get("author").get("name").asText(), sha, temp.get("stats").get("additions").asInt(), temp.get("stats").get("deletions").asInt()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	});
    	
    	CommitStat commitStat = new CommitStat(commStatService.getTopCommitter(commitList)
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
    
}
