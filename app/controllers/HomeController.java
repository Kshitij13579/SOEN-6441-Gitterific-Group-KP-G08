package controllers;

import javax.inject.Inject;

import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.JsonNode;

import akka.stream.javadsl.Source;
import akka.util.ByteString;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.mvc.Http.MultipartFormData.Part;
import views.html.index;
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
	List<String> tasks = new ArrayList<String>();
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	@Inject WSClient ws;
	@Inject FormFactory formFactory;
    public Result index() throws InterruptedException, ExecutionException {
    	Form<UrlParam> urlForm = formFactory.form(UrlParam.class);
        return ok(index.render(tasks,urlForm));
    }
    
    public Result create() {
    	Form<UrlParam> urlForm = formFactory.form(UrlParam.class);
    	List<String> s = new ArrayList<String>();
    	return ok(index.render(s,urlForm));
    }
    
    public Result search(String query) throws InterruptedException, ExecutionException {
    	Form<UrlParam> urlForm = formFactory.form(UrlParam.class).bindFromRequest();
    	UrlParam param = urlForm.get();
    	WSRequest request = ws.url("https://api.github.com/search/repositories?q="+query+"&page=1&per_page=10");
    	request.setMethod("GET");
    	request.addHeader("ACCEPT", "application/vnd.github.v3+json");
    	
    	CompletionStage<JsonNode> jsonPromise = request.get().thenApply(r -> r.asJson());
    	List<String> tasks = new ArrayList<String>();
    	for(int i=0;i<jsonPromise.toCompletableFuture().get().get("items").size();i++) {
        	tasks.add(jsonPromise.toCompletableFuture().get().get("items").get(i).get("name").toString());
        	
        	}
    	
		return ok(index.render(tasks, urlForm));
    }
    
}
