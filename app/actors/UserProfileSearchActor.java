package actors;

import play.Logger;
import play.cache.AsyncCacheApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.libs.ws.WSRequest;
import service.UserService;
import views.html.users;

import java.util.List;
import java.util.concurrent.CompletionStage;

import com.google.inject.Inject;
import com.typesafe.config.ConfigFactory;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import model.GIT_HEADER;
import model.GithubApi;
import model.Repository;
import model.UserProfile;
import actors.SupervisorActor.Data;

public class UserProfileSearchActor extends AbstractActor {
	private final ActorRef ws;
    GithubApi ghApi;
	String username;
    
    public UserProfileSearchActor(final ActorRef wsOut,GithubApi ghApi) {
    	ws =  wsOut;
    	this.ghApi = ghApi;
    	Logger.debug("New User Search Actor{} for WebSocket {}", self(), wsOut);
    }
    
    public static Props props(final ActorRef wsout,GithubApi ghApi) {
        return Props.create(UserProfileSearchActor.class, wsout, ghApi);
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
    			//.match(ObjectNode.class, o -> this.query = o.get("keyword").textValue())
    			.build();
	}
	
	 private void send(Data d) throws Exception {
		 //Logger.debug("New User Search Actor Query {}",this.query);
		 UserProfile userList = ghApi.getUserProfile("siddharthajha07");
	    	 ObjectNode response = Json.newObject();
	         response.put("name", userList.login);
	         response.put("id", userList.id);
//	         response.put("id", userList.node_id);
//	         response.put("id", userList.avatar_url);
//	         response.put("id", userList.repos_url);
//	         response.put("id", userList.email);
//	         response.put("id", userList.twitter_username);
//	         response.put("id", userList.followers);
//	         response.put("id", userList.following);
//	         response.put("id", userList.subscriptions_url);
//	         response.put("id", userList.organizations_url);

	         Logger.debug("New User Search Actor Response {}",response);
	    	 ws.tell(response, self());
	     
	    
	 }
}

