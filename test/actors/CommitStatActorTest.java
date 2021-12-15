package actors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import akka.testkit.javadsl.TestKit;
import model.GithubApi;
import model.GithubApiMock;
import play.Application;
import play.cache.AsyncCacheApi;
import play.inject.guice.GuiceApplicationBuilder;
import actors.CommitSupervisorActor.Data;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

import static play.inject.Bindings.bind;

import java.time.Duration;

/**
 * This test file is to test the CommitStatActor class functionality.
 * 
 * @author Kshitij Yerande
 * @version 1.0
 * @since 7-Dec-2021
 *
 */
public class CommitStatActorTest {
    
public static ActorSystem system;
public static Materializer materializer;
public AsyncCacheApi cache;
public GithubApi ghApi;
Application application;

	
	@Before
	  public  void setup() {
	    system = ActorSystem.create();
	    application = new GuiceApplicationBuilder().overrides(bind(GithubApi.class).to(GithubApiMock.class)).build();
	    ghApi = application.injector().instanceOf(GithubApi.class);
	  }
	
	@Test
	public void testCommitStatActor() {
	   
		new TestKit(system) {
			{   
				final Props props = CommitStatActor.props(getTestActor(), cache, ghApi);
		        final ActorRef subject = system.actorOf(props);	
		        final TestKit probe = new TestKit(system);
		        
		        within(
		                Duration.ofSeconds(10),
		                () -> {
		                	
		                	subject.tell(new Data(),getRef());
		                	expectNoMsg();
		                	
		                	ObjectNode testData = Json.newObject(); 
		                	testData.put("user","test");
		                	testData.put("repository","repo");
		                	subject.tell(testData,getRef());
		                	
		                	subject.tell(new Data(),getRef());
		                	ObjectNode node = expectMsgClass(ObjectNode.class);
		                	
		                	assertEquals(10,node.get("max_add").intValue());
		                	assertEquals(10,node.get("min_add").intValue());
		                	assertEquals(10,node.get("avg_add").intValue());
		                	assertEquals(10,node.get("max_del").intValue());
		                	assertEquals(10,node.get("min_del").intValue());
		                	assertEquals(10,node.get("avg_del").intValue());

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
