package controllers;

import javax.inject.Inject;

//import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import akka.stream.javadsl.Source;
import akka.util.ByteString;
import model.GIT_HEADER;
import model.GIT_PARAM;
import model.Repository;
import model.UserProfile;
import model.UserRepository;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.mvc.Http.MultipartFormData.Part;
import service.RepositorySearchService;
import service.UserService;
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
    
    public Result user_profile(String username) throws InterruptedException, ExecutionException{
    	
    	UserService repoService = new UserService();
    	UserProfile repoList = new UserProfile();
    	WSRequest request = ws.url(ConfigFactory.load().getString("git_search_user_url")+"/"+username)
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("git_header.Content-Type"));
	             //; .addQueryParameter(GIT_PARAM.QUERY.value, username);
	              //.addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("repo_per_page"))
	              //.addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("repo_page"))
	

    	CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.getBody(json()));
    	repoList = repoService.getUser(jsonPromise.toCompletableFuture().get());
    	return ok(users.render(repoList));
    }
    
    public Result user_repository(String username) throws InterruptedException, ExecutionException{
    	
    	UserService repoService = new UserService();
    	List<UserRepository> repoList = new ArrayList<>();
    	WSRequest request = ws.url(ConfigFactory.load().getString("git_search_user_url")+"/"+username+"/repos")
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("git_header.Content-Type"))
	             //; .addQueryParameter(GIT_PARAM.QUERY.value, username);
	              .addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("repo_per_page_repo"))
	              .addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("repo_page"));
	

    	CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.getBody(json()));
    	repoList = repoService.getUser_repository(jsonPromise.toCompletableFuture().get());
    	return ok(repositories.render(repoList));
    }
    
}
