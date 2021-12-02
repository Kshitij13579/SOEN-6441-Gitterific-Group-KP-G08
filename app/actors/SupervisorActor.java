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

public class SupervisorActor extends AbstractActorWithTimers {
    
	
	private Set<ActorRef> userActors;
	
	@Override
    public void preStart() {
    	// Logger.info("TimeActor {} started", self());
        getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(10, TimeUnit.SECONDS));
    }
	
	private static final class Tick {
    }
    
    static public class RegisterMsg {
    	
    }
    
    static public class DeRegister {
    	
    }
    
    static public class Data{
    	
    }
    
    static public Props getProps() {
    	return Props.create(SupervisorActor.class,() -> new SupervisorActor());
    }
    

    private SupervisorActor() {
	  	// TODO Auto-generated constructor stub
    	this.userActors = new HashSet<>();
	}
	
	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder()
    			.match(RegisterMsg.class, msg -> userActors.add(sender()))
    			.match(Tick.class, msg -> notifyClients())
    			.match(DeRegister.class, msg -> userActors.remove(sender()))
    		    .build();
	}
	
	 private void notifyClients() throws Exception {
		 userActors.forEach(ar -> ar.tell(new Data(), self()));
	 }

}
