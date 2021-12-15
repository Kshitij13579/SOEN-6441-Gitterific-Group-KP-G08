package actors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import actors.CommitSupervisorActor.Data;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import java.time.Duration;

/**
 * This test file is to test the CommitSupervisorActor class functionality.
 * 
 * @author Kshitij Yerande
 * @version 1.0
 * @since 7-Dec-2021
 *
 */
public class CommitSupervisorActorTest {
    
	public static ActorSystem system;
	
	@Before
	  public  void setup() {
	    system = ActorSystem.create();
	  }
  
	
	@Test
	public void testCommitSupervisorActor() {
		
		new TestKit(system) {
			{
				final Props props = CommitSupervisorActor.getProps();
		        final ActorRef subject = system.actorOf(props);	
		        final TestKit probe = new TestKit(system);
		        
		        within(
		                Duration.ofSeconds(10),
		                () -> {
		                	subject.tell(new CommitSupervisorActor.RegisterMsg(),getRef());
		                	expectNoMsg();
		                	
		                	subject.tell(new CommitSupervisorActor.Tick(),getRef());
		                	expectMsgClass(Data.class);
		                	
		                	subject.tell(new CommitSupervisorActor.DeRegister(),getRef());
		                	expectNoMsg();
		                	
		                	return null;
		                });
			}
			
		};
	}
	
	@After
	  public  void teardown() {
	    TestKit.shutdownActorSystem(system);
	    system = null;
	  }
}
