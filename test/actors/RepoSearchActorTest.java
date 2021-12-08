package actors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import play.Logger;

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
import actors.SupervisorActor.Data;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

import static play.inject.Bindings.bind;

import java.time.Duration;

/**
 * This is the Test Class to perform RepoSearch Actor testing
 * @author Kshitij Yerande
 * @since 2021-12-07
 * @version 1.0
 *
 */
public class RepoSearchActorTest {
    
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
	public void testRepoSearchActor() {
	   
		new TestKit(system) {
			{   
				final Props props = RepoSearchActor.props(getTestActor(), cache, ghApi);
		        final ActorRef subject = system.actorOf(props);	
		        final TestKit testProbe = new TestKit(system);
		        
		        within(
		                Duration.ofSeconds(20),
		                () -> {
		                	
		                	subject.tell(new Data(), getRef());
		                	expectNoMsg();
		                	
		                	ObjectNode testData = Json.newObject(); 
		                	testData.put("keyword","play");
		                	subject.tell(testData, getRef());
		                	
		                	subject.tell(new Data(), getRef());
		                	ObjectNode response = expectMsgClass(ObjectNode.class);
		                	assertTrue(response.get("topics").size() > 0 ? true : false);
		                	
		                	subject.tell(new Data(), getRef());
		                	response = expectMsgClass(ObjectNode.class);
		                	assertTrue(response.get("topics").size() > 0 ? true : false);

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
