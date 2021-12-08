package actors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import model.GithubApi;
import model.GithubApiImpl;
import model.Repository;
import play.cache.AsyncCacheApi;
import java.util.concurrent.TimeUnit;
import com.google.inject.Inject;
import scala.concurrent.duration.Duration;

/**
 *The Supervisor Actor class is a timer which ticks every 10 seconds
 *and notifies all its clients  
 *
 * @author Kshitij Yerande
 * @version 1.0
 * @since 2021-12-07
 *
 */
public class SupervisorActor extends AbstractActorWithTimers {
    
	
	private Set<ActorRef> userActors;
	
    /**
     * Method Call before Actor is created and it starts sending Tick message
     * every 10 seconds
     * 
     */
	@Override
    public void preStart() {
    	// Logger.info("TimeActor {} started", self());
        getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(10, TimeUnit.SECONDS));
    }
	
	/**
	 * 
	 * This class is used to send Tick messages to other actors
	 *
	 */
	public static final class Tick {
    }
    
    /**
     * The class being used by other actor to
     * register with Supervisor actor
     *
     */
    static public class RegisterMsg {
    	
    }
    
    /**
     * The class is used by other actor to 
     * deregister from Supervisor Actor
     *
     */
    static public class DeRegister {
    	
    }
    
    /**
     * This class is used to send messages to Actors
     *
     */
    static public class Data{
    	
    }
    
    /**
     * The method is called to create Supervisor Actor
     * @return Props
     */
    static public Props getProps() {
    	return Props.create(SupervisorActor.class,() -> new SupervisorActor());
    }
    

    /**
     * The constructor for Supervisor Actor
     */
    private SupervisorActor() {
	  	// TODO Auto-generated constructor stub
    	this.userActors = new HashSet<>();
	}
	
	/**
	 *The method called when received message from other actors
	 *also add the registered actor into set of actors
	 *@return Receive 
	 */
	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder()
    			.match(RegisterMsg.class, msg -> userActors.add(sender()))
    			.match(Tick.class, msg -> notifyClients())
    			.match(DeRegister.class, msg -> userActors.remove(sender()))
    		    .build();
	}
	
	 /**
	  * The method to notify all the registered Actors
	 * @throws Exception
	 */
	private void notifyClients() throws Exception {
		 userActors.forEach(ar -> ar.tell(new Data(), self()));
	 }

}
