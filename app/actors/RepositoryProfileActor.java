package actors;

import java.util.stream.Collectors;
import com.google.inject.Inject;
import play.Logger;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import actors.SupervisorActor.Data;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;
import model.GithubApi;
import play.cache.AsyncCacheApi;
import model.RepositoryProfile;
import model.RepositoryProfileCollaborators;
import model.RepositoryProfileIssues;	
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import play.libs.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import service.RepositoryProfileService;
import views.html.*;

/**
* The Repository Profile Actor class is used to for computing and displaying repository profile 
* details by making an API call every 10 seconds.
* This actor subscribes to CommitSupervisor Actor.
*
* @author  Yogesh Yadav
* @since   2021-12-07 
*/
public class RepositoryProfileActor extends AbstractActor {
	private final ActorRef ws;
	
	@Inject AsyncCacheApi cache;
	GithubApi ghApi;
    String username;
    String repository;
    
    /**
     * Constructor to initialize the Repository Actor
     * @author  Yogesh Yadav
     * @since   2021-12-07 
     * @param wsOut 
     * @param cache 
     * @param ghApi
     */
    public RepositoryProfileActor(final ActorRef wsOut,AsyncCacheApi cache,GithubApi ghApi) {
    	this.ws =  wsOut;
    	this.cache = cache;
    	this.ghApi = ghApi;
    	Logger.debug("***************New Repository Profile Actor{} for WebSocket {}", self(), wsOut);
    }
	
    /**     
     * Method to start the Actor
     * @author  Yogesh Yadav
     * @since   2021-12-07 
     * @param wsout
     * @param cache
     * @param ghApi
     * @return
     */
    public static Props props(final ActorRef wsout,AsyncCacheApi cache,GithubApi ghApi) {
        return Props.create(RepositoryProfileActor.class, wsout,cache,ghApi);
    }
    
    /**
     * Method to register to supervisor
     * @author  Yogesh Yadav
     * @since   2021-12-07 
     */
    @Override
    public void preStart() {
       	context().actorSelection("/user/supervisorActor/")
                 .tell(new SupervisorActor.RegisterMsg(), self());
    }
    
    /**
     * Method to receive data from front end
     * @author  Yogesh Yadav
     * @since   2021-12-07 
     */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
    			.match(Data.class, this::send)
    			.match(ObjectNode.class, o -> setData(o))
    			.build();
	}
	
	 /**
     * Method to set Actor variables
     * @author  Yogesh Yadav
     * @since   2021-12-07 
     */
	private void setData(ObjectNode o) {
		this.username = o.get("user").asText();
		this.repository = o.get("repository").asText();
		Logger.debug("*********Recevied parameters {} {}",this.username,this.repository);
	}
	
	 /**
	 * Method to call git api calls and compute repository details and share data to front end
     * @author  Yogesh Yadav
     * @since   2021-12-07 * 
     * @param d
	 * @throws Exception
	 */
	private void send(Data d) throws Exception {
		 Logger.debug("*********  User ******* {} ",username);
		 Logger.debug("*********  Repository ******* {} ",repository);
		 if(this.username !=null && this.repository!=null) {
		    RepositoryProfileService rps = new RepositoryProfileService();
	    	RepositoryProfile rp = new RepositoryProfile();
	    	List<RepositoryProfileIssues> rpi = new ArrayList<>();
	    	List<RepositoryProfileCollaborators> rpc = new ArrayList<>();
	    	CompletableFuture<Object> reppprofile = this.ghApi.getRepositoryProfileFromResponse(username, repository, cache);
	    	CompletableFuture<Object> repoprofileissues = this.ghApi.getRepositoryProfileIssuesFromResponse(username, repository, cache);
	    	
	    	CompletableFuture<Object> repoprofilecollab = this.ghApi.getRepositoryProfileCollaborationsFromResponse(username, repository, cache);
	    	
	    	rp =  rps.getRepositoryProfile(reppprofile);	
	  		rpi = rps.getRepositoryProfile_Issue(repoprofileissues);
	  		
	  		try {
		    	
	  			rpc = rps.getRepositoryProfile_Collaborators(repoprofilecollab);
			} catch (Exception e) {
				e.printStackTrace();
	        } 	
	  		Logger.debug("*****Response RPI- {}",rpi.get(0).issue_title);
	  		Logger.debug("*****Response RPI- {}",rpi.get(1).issue_title);
	     ObjectNode response = Json.newObject();
	  	
	     response.put("login", rp.login);
	     response.put("name", rp.id);
	     response.put("node_id", rp.node_id);
	     response.put("avatar_url", rp.avatar_url);
	     response.put("open_issues", rp.open_issues);
	     
	     	List<String> issueNumber = rpi.stream().map(RepositoryProfileIssues::getIssueNumber).collect(Collectors.toList()) ;
	     	ArrayNode arrayNode = response.putArray("issue_number");
	     	for (String item : issueNumber ) {
	             arrayNode.add(item);
	         }
	     	List<String> issuetitle = rpi.stream().map(RepositoryProfileIssues::getIssueTitle).collect(Collectors.toList()) ;
	     	ArrayNode arrayNode1 = response.putArray("issue_title");
	     	for (String item : issuetitle ) {
	     		arrayNode1.add(item);
	         }
	     	List<String> state = rpi.stream().map(RepositoryProfileIssues::getState).collect(Collectors.toList()) ;
	     	ArrayNode arrayNode2 = response.putArray("state");
	     	for (String item : state ) {
	     		arrayNode2.add(item);
	         }
	     	List<String> createdAt = rpi.stream().map(RepositoryProfileIssues::getCreatedAt).collect(Collectors.toList()) ;
	     	ArrayNode arrayNode3 = response.putArray("created_at");
	     	for (String item : createdAt ) {
	     		arrayNode3.add(item);
	         }
	     	List<String> updatedAt = rpi.stream().map(RepositoryProfileIssues::getUpdateAt).collect(Collectors.toList()) ;
	     	ArrayNode arrayNode4 = response.putArray("updated_at");
	     	for (String item : updatedAt ) {
	     		arrayNode4.add(item);
	         }
	     	try {
	     		List<String> user = rpc.stream().map(RepositoryProfileCollaborators::getLogin).collect(Collectors.toList()) ;
		     	ArrayNode arrayNode5 = response.putArray("user_name");
		     	for (String item : user ) {
		             arrayNode5.add(item);
		         }
		     	List<String> reponame = rpc.stream().map(RepositoryProfileCollaborators::getId).collect(Collectors.toList()) ;
		     	ArrayNode arrayNode6 = response.putArray("repo_name");
		     	for (String item : reponame ) {
		             arrayNode6.add(item);
		         }
		     	List<String> rolename = rpc.stream().map(RepositoryProfileCollaborators::getRoleName).collect(Collectors.toList()) ;
		     	ArrayNode arrayNode7 = response.putArray("role_name");
		     	for (String item : rolename ) {
		             arrayNode7.add(item);
		         }
		     	List<String> userurl = rpc.stream().map(RepositoryProfileCollaborators::getUserUrl).collect(Collectors.toList()) ;
		     	ArrayNode arrayNode8 = response.putArray("user_url");
		     	for (String item : userurl ) {
		             arrayNode8.add(item);
		         }
	     	}catch (Exception e) {
				e.printStackTrace();
	        }
	     	
	     // Uncomment in local
	    	// Logger.debug("*****Response - {}",response);
	 	    ws.tell(response, self());
	 	    
		 }else {
			 Logger.debug("Either user or Repository is null");
		 }
	 }
	
}
