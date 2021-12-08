package actors;

import com.google.inject.Inject;

import actors.SupervisorActor.Data;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import model.GithubApi;
import play.cache.AsyncCacheApi;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


import model.Issues;
import model.*;


import play.Logger;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import play.libs.Json;
import service.IssueStatService;


/**
 *The Issue Service Actor class is used to compute Issues  
 *statistics by making an API call every 10 seconds.
 *This actor subscribes to Supervisor Actor.
 * @author akshay dhabale
 * @version 1.0
 * @since 2021-12-07
 *
 */
public class IssueServiceActor extends AbstractActor{
	private final ActorRef ws;
	String user;
	String repository;
	
	@Inject AsyncCacheApi cache;
    GithubApi ghApi;
  
    /**
     * The IssueServiceActor is a constructor for Actor
     * @param wsOut ActorRef of Actor 
     * @param cache	Async cached being used in the main controller
     * @param ghApi GitHubApi Interface Object
     */
    public IssueServiceActor(final ActorRef wsOut,AsyncCacheApi cache, GithubApi ghApi) {
    	
    	
    	ws=wsOut;
    	this.cache=cache;
    	this.ghApi=ghApi;
    	
    	//Logger.debug("New Issue Service Actor{} for WebSocket {}", self(), wsOut);
    	
    }
    
    /**
     * Method to create the Actor and get Actor Protocol
     * @param wsout ActorRef of Actor
     * @param cache Async cached being used in the main controller
     * @param ghApi GitHubApi Interface Object
     * @return Props 
     */
    public static Props props(final ActorRef wsout,AsyncCacheApi cache,GithubApi ghApi) {
    	
        return Props.create(IssueServiceActor.class, wsout,cache,ghApi);
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
	 * Method called when Actor receives message 
	 * @return Receive
	 */
	@Override
	public Receive createReceive() {
		
		return receiveBuilder()
    			.match(Data.class, this::send)
    			.match(ObjectNode.class, o -> setData(o))
    			.build();
	}
	
	/**
	 * Method to get user and repository name on receiving response
	 * @param o ObjectNode response containing user and repository name
	 */
	private void setData(ObjectNode o) {
		
		this.user=o.get("user").asText();
		this.repository=o.get("repository").asText();
		Logger.debug("Received Parameters {} {} ",this.user,this.repository);
	}
	
	 
	 /**
	  * Method to compute Issue Statistics and send it to UI via JsonObject
	 * @param d Data
	 * @throws Exception
	 */
	private void send(Data d) throws Exception {
		 Logger.debug("User Name {} ",this.user);
		 Logger.debug("Repository Name {} ",this.repository);
		 
		 if(this.user !=null && this.repository!=null) {
			 
		this.ghApi.getIssuesFromResponse(user, repository, cache).thenAccept(issuesList->{
			
		IssueStatService issueStatService=new IssueStatService();
		List[] frequencyList=issueStatService.wordCountDescening(issuesList);

		 ObjectNode response = Json.newObject(); 
		List<String> titles=issuesList.stream().map(Issues::getTitle).collect(Collectors.toList());

		List<String> wordList=frequencyList[0];
		List<Long>   wordCount=frequencyList[1];	
			
		ArrayNode arrayNode = response.putArray("titles");
		for (String item : titles) {
			arrayNode.add(item);
		}
		
		ArrayNode arrayNode1 = response.putArray("words");
		for (String word : wordList) {
			arrayNode1.add(word);
		}		
		ArrayNode arrayNode2 = response.putArray("count");
		for (Long count : wordCount) {
			arrayNode2.add(count);
		}
		
		// Uncomment in local
		// Logger.debug("Response{}",response);
		 
	    ws.tell(response, self());
	    	 
		});
	    	 
		 }
		else {
			 Logger.debug("Either user or Repository is null");
		 }
		 
		 
	 }
}
