package actors;

import play.Logger;
import play.cache.AsyncCacheApi;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import play.libs.Json;

import java.util.List;

import com.google.inject.Inject;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import model.GithubApi;
import model.Repository;
import actors.SupervisorActor.Data;

public class TopicSearchActor extends AbstractActor {
	private final ActorRef ws;
	
	@Inject AsyncCacheApi cache;
    GithubApi ghApi;
    String query;
    
    public TopicSearchActor(final ActorRef wsOut,AsyncCacheApi cache,GithubApi ghApi) {
    	ws =  wsOut;
    	this.cache = cache;
    	this.ghApi = ghApi;
    	Logger.debug("New Topic Search Actor{} for WebSocket {}", self(), wsOut);
    }
    
    public static Props props(final ActorRef wsout,AsyncCacheApi cache,GithubApi ghApi) {
        return Props.create(TopicSearchActor.class, wsout,cache,ghApi);
    }
    
    @Override
    public void preStart() {
       	context().actorSelection("/user/supervisorActor/")
                 .tell(new SupervisorActor.RegisterMsg(), self());
    }
    
	@Override
	public Receive createReceive() {
		return receiveBuilder()
    			.match(Data.class, this::send)
    			.match(ObjectNode.class, o -> this.query = o.get("keyword").textValue())
    			.build();
	}
	
	 private void send(Data d) throws Exception {
		 Logger.debug("New Topic Search Actor Query {}",this.query);
		 List<Repository> repoList = ghApi.getRepositoryInfo(query, cache);
	     repoList.forEach(r -> {
	    	 ObjectNode response = Json.newObject();
	         response.put("name", r.name);
	         response.put("login", r.login);
	         ArrayNode arrayNode = response.putArray("topics");
	         for (String item : r.topics) {
	             arrayNode.add(item);
	         }
	         // response.put("topics", r.topics);
	         Logger.debug("New Repo Search Actor Response {}",response);
	    	 ws.tell(response, self());
	    	 
	     });
	 }

}
