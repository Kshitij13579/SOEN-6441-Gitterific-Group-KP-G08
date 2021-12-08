package actors;

import play.Logger;
import play.cache.AsyncCacheApi;

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

/**
* The User Profile Search Actor class is used to fetch details of a user
* by making an API call every 10 seconds.
* This actor subscribes to Supervisor Actor.
*
* @author  Siddhartha Jha
* @version 1.0
* @since   2021-12-04 
*/
public class UserProfileSearchActor extends AbstractActor {
	private final ActorRef ws;
    GithubApi ghApi;
	String username;
    
    public UserProfileSearchActor(final ActorRef wsOut,GithubApi ghApi) {
    	ws =  wsOut;
    	this.ghApi = ghApi;
    	Logger.debug("New User Search Actor{} for WebSocket {}", self(), wsOut);
    }
    
    /**
     * Method to get the Actor protocols and create the actor
     * @param wsout
     * @param ghApi
     * @return Props
     */    
    public static Props props(final ActorRef wsout,GithubApi ghApi) {
        return Props.create(UserProfileSearchActor.class, wsout, ghApi);
    }

    /**
     * Method call before Actor is started to subscribe to supervisor actor.
     */    
    @Override
    public void preStart() {
       	context().actorSelection("/user/supervisorActor/")
                 .tell(new SupervisorActor.RegisterMsg(), self());
    }
 
    /**
     * Method call n messages received to actor.
     * @return Receive 
     */    
	@Override
	public Receive createReceive() {
		return receiveBuilder()
    			.match(Data.class, this::send)
    			.match(ObjectNode.class, o -> this.username = o.get("keyword").textValue())
    			.build();
	}
	
	/**
	 * Method to fetch user details and send to UI.
	 * @param d
	 * @throws Exception
	 */	
	 private void send(Data d) throws Exception {
		 Logger.debug("New User Search Actor Query {}",username);
		 UserProfile userList = ghApi.getUserProfile(username);
	    	 ObjectNode response = Json.newObject();
	         response.put("name", userList.login);
	         response.put("id", userList.id);
	         response.put("node_id", userList.node_id);
	         response.put("avatar_url", userList.avatar_url);
	         response.put("repos_url", userList.repos_url);
	         response.put("email", userList.email);
	         response.put("twitter_username", userList.twitter_username);
	         response.put("followers", userList.followers);
	         response.put("following", userList.following);
	         response.put("subscriptions_url", userList.subscriptions_url);
	         response.put("organizations_url", userList.organizations_url);
	      // Uncomment in local
	         // Logger.debug("New User Search Actor Response {}",response);
	    	 ws.tell(response, self());
	       
	 }
}

