package actors;

import play.Logger;
import play.cache.AsyncCacheApi;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClientConfig.ResponseBodyPartFactory;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.typed.eventstream.EventStream.Subscribe;
import model.CommitStat;
import model.GithubApi;
import actors.CommitSupervisorActor.Data;

/**
* The Commit Statistics Actor class is used to compute commit 
* statistics by making an API call every 45 seconds.
* This actor subscribes to CommitSupervisor Actor.
*
* @author  Kshitij Yerande
* @version 1.0
* @since   2021-12-04 
*/
public class CommitStatActor extends AbstractActor {
	
	private final ActorRef ws;
	
	@Inject AsyncCacheApi cache;
    GithubApi ghApi;
    String user;
    String repository;
    
    public CommitStatActor(final ActorRef wsOut,AsyncCacheApi cache,GithubApi ghApi) {
    	ws =  wsOut;
    	this.cache = cache;
    	this.ghApi = ghApi;
    	Logger.debug("New Commit Stat Actor{} for WebSocket {}", self(), wsOut);
    }
    
    /**
     * Method to get the Actor protocols and create the actor
     * @param wsout
     * @param cache
     * @param ghApi
     * @return Props
     */
    public static Props props(final ActorRef wsout,AsyncCacheApi cache,GithubApi ghApi) {
        return Props.create(CommitStatActor.class, wsout,cache,ghApi);
    }
    
    /**
     * Method call before Actor is started to subscribe to supervisor actor.
     */
    @Override
    public void preStart() {
       	context().actorSelection("/user/commitSupervisorActor/")
                 .tell(new CommitSupervisorActor.RegisterMsg(), self());
    }
    
    /**
     * Method call n messages received to actor.
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
	 * Method to set the user and repository variables
	 * @param o - Json object containing user and repository details
	 */
	private void setData(ObjectNode o) {
		this.user = o.get("user").asText();
		this.repository = o.get("repository").asText();
		Logger.debug("Recevied parameters {} {}",this.user,this.repository);
	}
	
	/**
	 * Method to compute commit statistics and send to UI.
	 * @param d
	 * @throws Exception
	 */
	 private void send(Data d) throws Exception {
		 Logger.debug("New Commit Stat Actor Call Send");
		 if(this.user !=null && this.repository!=null) {
			 this.ghApi.getCommitStatistics(user, repository, cache).thenAccept(commitStat ->{
				 Logger.debug("New Commit Stat Actor Data Received");
				 String authorList = commitStat.getTop_committers().stream()
						             .map(a -> a.getName()+"@"+a.getLogin()+"@"+a.getCommits())
						             .collect(Collectors.joining(","));
				 
				 ObjectNode response = Json.newObject();
				 response.put("author_list",authorList);
				 response.put("min_add", commitStat.getMin_additions());
				 response.put("max_add", commitStat.getMax_additions());
				 response.put("avg_add", commitStat.getAvg_additions());
				 response.put("min_del", commitStat.getMin_deletions());
				 response.put("max_del", commitStat.getMax_deletions());
				 response.put("avg_del", commitStat.getAvg_deletions());
				// Uncomment in local
				 // Logger.debug("Commit Stat Response {}",response);
				 ws.tell(response, self());
			 });
			 
		 }else {
			 Logger.debug("Either user or Repository is null");
		 }
	     
	 }

}
