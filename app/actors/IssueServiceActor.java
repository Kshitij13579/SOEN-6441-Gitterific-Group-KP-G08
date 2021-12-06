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

import java.util.List;

import com.google.inject.Inject;


public class IssueServiceActor extends AbstractActor{
	private final ActorRef ws;
	String user;
	String repository;
	//private final List<Issues> issueList;
	//private final IssueStatService issueStatService;
	
	@Inject AsyncCacheApi cache;
    GithubApi ghApi;
  
    public IssueServiceActor(final ActorRef wsOut,AsyncCacheApi cache, GithubApi ghApi) {
    	
    	
    	ws=wsOut;
    	this.cache=cache;
    	this.ghApi=ghApi;
    	//this.issueList=issueList;
    	//this.issueStatService=issueStatService;
    	
    	Logger.debug("New Issue Service Actor{} for WebSocket {}", self(), wsOut);
    	
    }
    
    public static Props props(final ActorRef wsout,AsyncCacheApi cache,GithubApi ghApi) {
    	
        return Props.create(IssueServiceActor.class, wsout,cache,ghApi);
    }
    
    @Override
    public void preStart() {
    	
     	context().actorSelection("/user/supervisorActor/")
        .tell(new SupervisorActor.RegisterMsg(), self());
    }
	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder()
    			.match(Data.class, this::send)
    			.match(ObjectNode.class, o -> setData(o))
    			.build();
	}
	
	private void setData(ObjectNode o) {
		
		this.user=o.get("user").asText();
		this.repository=o.get("repository").asText();
		Logger.debug("Received Parameters {} {} ",this.user,this.repository);
	}
	
	 
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

		Logger.debug("Response{}",response);
		 
	    	 ws.tell(response, self());
	    	 
		});
	    	 
		 }
		else {
			 Logger.debug("Either user or Repository is null");
		 }
		 
		 
	 }
}
