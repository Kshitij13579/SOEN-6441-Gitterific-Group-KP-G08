package actors;

import play.Logger;
import play.cache.AsyncCacheApi;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import play.libs.Json;

import java.util.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import model.GithubApi;
import model.Repository;
import actors.SupervisorActor.Data;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedAbstractActor;
import akka.japi.Function;
import akka.actor.typed.javadsl.Behaviors;
import scala.concurrent.duration.Duration;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * The Repo Search Actor class is used to display
 * 10 results for prvided query by making an API call every 10 seconds.
 * This actor subscribes to Supervisor Actor.
 *
 * @author  Kshitij Yerande
 * @version 1.0
 * @since   2021-12-07
 *
 */
public class RepoSearchActor extends AbstractActor {
	private final ActorRef ws;
	
	@Inject AsyncCacheApi cache;
    GithubApi ghApi;
    String query;
    
    HashMap<String,List<Repository>> userSearchHist;
    
    /**
     * The RepoSearchActor is a constructor for Actor
     * @param wsOut ActorRef of Actor 
     * @param cache	Async cached being used in the main controller
     * @param ghApi GitHubApi Interface Object
     *
     */
    public RepoSearchActor(final ActorRef wsOut,AsyncCacheApi cache,GithubApi ghApi) {
    	ws =  wsOut;
    	this.cache = cache;
    	this.ghApi = ghApi;
    	this.userSearchHist = new HashMap<String,List<Repository>>();
    	Logger.debug("New Repo Search Actor{} for WebSocket {}", self(), wsOut);
    }
    
    /**
     * Method to create the Actor and get Actor Protocol
     * @param wsout ActorRef of Actor
     * @param cache Async cached being used in the main controller
     * @param ghApi GitHubApi Interface Object
     * @return Props 
     */
    public static Props props(final ActorRef wsout,AsyncCacheApi cache,GithubApi ghApi) {
        return Props.create(RepoSearchActor.class, wsout,cache,ghApi);
    }
    
    
    /**
     * Method Call before Actor is created and it registers with Supervisor Actor
     */
    @Override
    public void preStart() {
       	context().actorSelection("/user/supervisorActor/")
                 .tell(new SupervisorActor.RegisterMsg(), self());
    }
    /**
     * Method Call before Actor is stopped 
     */
    @Override
    public void postStop() {
       	Logger.debug("New Repo Search Actor{} Stopped",self());
    }
    
    /**
	 * Method called when Actor receives message 
	 * @return Receive
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
    			.match(Data.class, this::send)
    			.match(ObjectNode.class, o -> this.query = o.get("keyword").textValue())
    			.build();
	}
	
	 
	 /**
	  * Method to display 10 results for provided query and send it to UI via JsonObject
	 * @param d Data
	 * @throws Exception
	 */	
	 private void send(Data d) throws Exception {
		 Logger.debug("New Repo Search Actor Query {}",this.query);
		 if (this.query != null && this.query != "") {
			 CompletionStage<List<Repository>> repoList = ghApi.getRepositories(query, cache);
			 
				 repoList.thenAcceptAsync(res -> {
					 
					 if(userSearchHist.containsKey(this.query)) {
						 Logger.debug("Subsequent Query:{}",this.query);
						 res = getDifference(res);
					 }else {
						 Logger.debug("First Query:{}",this.query);
						 userSearchHist.put(this.query,res);
					 }
					 if(!res.isEmpty()) {
						 res.forEach(r -> {
					    	 
					    	 ObjectNode response = Json.newObject();
					         response.put("name", r.name);
					         response.put("login", r.login);
					         ArrayNode arrayNode = response.putArray("topics");
					         for (String item : r.topics) {
					             arrayNode.add(item);
					         }
					      // Uncomment in local
					         // Logger.debug("New Repo Search Actor Response {}",response);
					    	 ws.tell(response, self());
					    	 
					     });						 
					 }
				 });
			 
		 }
	 }
	 
	 /**
	  * The method to get the difference between search results
	 * @param repoList List of repositories
	 * @return List<Repository> List of updated repositories
	 */
	private List<Repository> getDifference(List<Repository> repoList){
		 
		 List<Repository> actorRepoList = this.userSearchHist.get(this.query);
		 List<Repository> res = repoList.stream()
				                .filter(a -> actorRepoList.stream().noneMatch(b -> a.name.equals(b.name)))
				                .collect(Collectors.toList());
		 
		 if(!res.isEmpty()) {
			 actorRepoList.addAll(res);
			 this.userSearchHist.replace(this.query,actorRepoList);
		 }

		 return res;
		 
	 }

}
