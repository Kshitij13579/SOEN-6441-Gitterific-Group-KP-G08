package actors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import akka.actor.AbstractActor.Receive;
import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.concurrent.TimeUnit;
import com.google.inject.Inject;
import scala.concurrent.duration.Duration;

/**
* The Commit Supervisor Actor class is a timer which ticks every 45 seconds
* and notifies all its clients.
*
* @author  Kshitij Yerande
* @version 1.0
* @since   2021-12-04 
*/
public class CommitSupervisorActor extends AbstractActorWithTimers {
    
private Set<ActorRef> commitActors;
	
    /**
     * Timer method which ticks every 45 seconds.
     */
	@Override
    public void preStart() {
    	// Logger.info("TimeActor {} started", self());
        getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(60, TimeUnit.SECONDS));
    }
	
	public static final class Tick {
    }
    
    static public class RegisterMsg {
    	
    }
    
    static public class DeRegister {
    	
    }
    
    static public class Data{
    	
    }
    
    static public Props getProps() {
    	return Props.create(CommitSupervisorActor.class, () -> new CommitSupervisorActor());
    }
    

    private CommitSupervisorActor() {
	  	// TODO Auto-generated constructor stub
    	this.commitActors = new HashSet<>();
	}
	
	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder()
    			.match(RegisterMsg.class, msg -> commitActors.add(sender()))
    			.match(Tick.class, msg -> notifyClients())
    			.match(DeRegister.class, msg -> commitActors.remove(sender()))
    		    .build();
	}
	
	
	 private void notifyClients() throws Exception {
		 commitActors.forEach(ar -> ar.tell(new Data(), self()));
	 }
}
