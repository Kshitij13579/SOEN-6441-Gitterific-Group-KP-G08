package actors;

import play.Logger;
import play.cache.AsyncCacheApi;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import play.libs.Json;

import java.util.*;
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

public class RepoSearchActor extends AbstractActor {
	private final ActorRef ws;
	
	@Inject AsyncCacheApi cache;
    GithubApi ghApi;
    String query;
    
    HashMap<String,List<Repository>> userSearchHist;
    
    public RepoSearchActor(final ActorRef wsOut,AsyncCacheApi cache,GithubApi ghApi) {
    	ws =  wsOut;
    	this.cache = cache;
    	this.ghApi = ghApi;
    	this.userSearchHist = new HashMap<String,List<Repository>>();
    	Logger.debug("New Repo Search Actor{} for WebSocket {}", self(), wsOut);
    }
    
    public static Props props(final ActorRef wsout,AsyncCacheApi cache,GithubApi ghApi) {
        return Props.create(RepoSearchActor.class, wsout,cache,ghApi);
    }
    
    @Override
    public void preStart() {
       	context().actorSelection("/user/supervisorActor/")
                 .tell(new SupervisorActor.RegisterMsg(), self());
    }
    
    @Override
    public void postStop() {
       	Logger.debug("New Repo Search Actor{} Stopped",self());
    }
    
	@Override
	public Receive createReceive() {
		return receiveBuilder()
    			.match(Data.class, this::send)
    			.match(ObjectNode.class, o -> this.query = o.get("keyword").textValue())
    			.build();
	}
	
	 private void send(Data d) throws Exception {
		 Logger.debug("New Repo Search Actor Query {}",this.query);
		 List<Repository> repoList = ghApi.getRepositories(query, cache);
		 
		 if(userSearchHist.containsKey(this.query)) {
			 Logger.debug("Subsequent Query:{}",this.query);
			 repoList = getDifference(repoList);
		 }else {
			 Logger.debug("First Query:{}",this.query);
			 userSearchHist.put(this.query,repoList);
		 }
		 
		 if(!repoList.isEmpty()) {
		     repoList.forEach(r -> {
		    	 
		    	 ObjectNode response = Json.newObject();
		         response.put("name", r.name);
		         response.put("login", r.login);
		         ArrayNode arrayNode = response.putArray("topics");
		         for (String item : r.topics) {
		             arrayNode.add(item);
		         }
		         Logger.debug("New Repo Search Actor Response {}",response);
		    	 ws.tell(response, self());
		    	 
		     });
		 }
	 }
	 
	 private List<Repository> getDifference(List<Repository> repoList){
		 
		 List<Repository> actorRepoList = userSearchHist.get(this.query);
		 List<Repository> res = repoList.stream()
				                .filter(a -> actorRepoList.stream().noneMatch(b -> a.name.equals(b.name)))
				                .collect(Collectors.toList());
		 
		 if(!res.isEmpty()) {
			 actorRepoList.addAll(res);
			 userSearchHist.replace(this.query,actorRepoList);
		 }

		 return res;
		 
	 }

}
