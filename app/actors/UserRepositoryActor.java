package actors;

import model.UserRepository;
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

/**
* The User Repository Actor class is used to fetch all the repositories of a user
* by making an API call every 10 seconds.
* This actor subscribes to Supervisor Actor.
*
* @author  Siddhartha Jha
* @version 1.0
* @since   2021-12-04 
*/
public class UserRepositoryActor extends AbstractActor {
	private final ActorRef ws;
    GithubApi ghApi;
	String username;
	
    
    public UserRepositoryActor(final ActorRef wsOut,GithubApi ghApi) {
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
        return Props.create(UserRepositoryActor.class, wsout, ghApi);
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
	 * Method to fetch user repositories list and send to UI.
	 * @param d
	 * @throws Exception
	 */		
	 private void send(Data d) throws Exception {
         Logger.debug("New Repo Search Actor Response {}",username);
		 List<UserRepository> userrepoList = ghApi.getuser_repository(username);
		 userrepoList.forEach(r -> {
	    	 ObjectNode response = Json.newObject();
	         response.put("login", r.login);
	         response.put("name", r.name);
	         response.put("reponame", r.reponame);
	      // Uncomment in local
	         // Logger.debug("New Repo Search Actor Response {}",response);
	    	 ws.tell(response, self());	 
	     });
	 }
}
