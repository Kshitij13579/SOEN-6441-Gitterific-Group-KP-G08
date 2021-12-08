package actors;

import play.Logger;
import play.cache.AsyncCacheApi;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import play.libs.Json;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletionStage;

import com.google.inject.Inject;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import model.GithubApi;
import model.Repository;
import actors.SupervisorActor.Data;
import java.util.stream.Collectors;

/**
 * It is used to display latest
 * 10 results for provided topic by making an API call every 10 seconds.
 * This actor subscribes to Supervisor Actor.
 *
 * @author  Mrinal Rai
 * @version 1.0
 * @since   2021-12-07
 *
 */
public class TopicSearchActor extends AbstractActor {
	private final ActorRef ws;
	
	@Inject AsyncCacheApi cache;
    GithubApi ghApi;
    String query;
    HashMap<String,List<Repository>> repoHistory;
    
    /**
     * Constructor for TopicSearchActor
     * @param wsOut ActorRef of Actor 
     * @param cache	Async cached being used in the main controller
     * @param ghApi GitHubApi Interface Object
     *
     */
    public TopicSearchActor(final ActorRef wsOut,AsyncCacheApi cache,GithubApi ghApi) {
    	ws =  wsOut;
    	this.cache = cache;
    	this.ghApi = ghApi;
    	this.repoHistory = new HashMap<String,List<Repository>>();
    	Logger.debug("New Topic Search Actor{} for WebSocket {}", self(), wsOut);
    }
    
    /**
     * Creates the Actor and get Actor Protocol
     * @param wsout ActorRef of Actor
     * @param cache Async cached being used in the main controller
     * @param ghApi GitHubApi Interface Object
     * @return Props 
     */
    public static Props props(final ActorRef wsout,AsyncCacheApi cache,GithubApi ghApi) {
        return Props.create(TopicSearchActor.class, wsout,cache,ghApi);
    }
    
    /**
     * Gets called before Actor is created and it registers with Supervisor Actor
     */
    @Override
    public void preStart() {
       	context().actorSelection("/user/supervisorActor/")
                 .tell(new SupervisorActor.RegisterMsg(), self());
    }
    
    /**
     * Gets called before Actor is stopped 
     */
    @Override
    public void postStop() {
       	Logger.debug("New Repo Search Actor{} Stopped",self());
    }
    
    /**
	 * Gets called when Actor receives message 
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
	 * Displays 10 latest results for provided query and send it to UI via JsonObject
	 * @param d Data
	 * @throws Exception
	 */	
	 private void send(Data d) throws Exception {
		 Logger.debug("New Topic Search Actor Query {}",this.query);
		 if (this.query != null && this.query != "") {
			 CompletionStage<List<Repository>> repoList = ghApi.getRepositoryInfo(query, cache);
			 
			 repoList.thenAcceptAsync(res -> {

				 if(this.repoHistory.containsKey(this.query)) {
					 Logger.debug("Subsequent Query:{}",this.query);
					 res = getDifference(res);
				 }else {
					 Logger.debug("First Query:{}",this.query);
					 this.repoHistory.put(this.query,res);
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
	 * Fetches the difference between the new and old topic results
	 * @param repoList List of repositories
	 * @return List<Repository> List of updated repositories
	 */
	 private List<Repository> getDifference(List<Repository> repoList){
		 
		 List<Repository> actorRepoList = this.repoHistory.get(this.query);
		 List<Repository> res = repoList.stream()
				                .filter(a -> actorRepoList.stream().noneMatch(b -> a.name.equals(b.name)))
				                .collect(Collectors.toList());
		 
		 if(!res.isEmpty()) {
			 actorRepoList.addAll(res);
			 this.repoHistory.replace(this.query,actorRepoList);
		 }

		 return res;
		 
	 }

}
