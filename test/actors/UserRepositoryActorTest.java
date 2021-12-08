package actors;

import play.Logger;

import static org.junit.Assert.*;
import static play.inject.Bindings.bind;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import model.GithubApi;
import model.GithubApiMock;
import play.Application;
import play.cache.AsyncCacheApi;
import play.inject.guice.GuiceApplicationBuilder;
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
import actors.SupervisorActor.Data;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import java.time.Duration;

import static play.inject.Bindings.bind;

/**
 * This is the Test Class to perform UserRepository Actor testing
 * @author Siddhartha Jha
 * @since 2021-12-07
 * @version 1.0
 *
 */
public class UserRepositoryActorTest {
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
		public void testUserRepositoryActor() {
		   
			new TestKit(system) {
				{   
					final Props props = UserRepositoryActor.props(getTestActor(), ghApi);
			        final ActorRef subject = system.actorOf(props);	
			        final TestKit probe = new TestKit(system);
			        
			        within(
			                Duration.ofSeconds(10),
			                () -> {
			         
			                	subject.tell(new Data(),getRef());
			                	//expectNoMsg();
			                	
			                  	ObjectNode testData = Json.newObject(); 
			                	testData.put("keyword","user");
			                	subject.tell(testData,getRef());
			                	
			                	subject.tell(new Data(),getRef());
			                	ObjectNode node = expectMsgClass(ObjectNode.class);
			                	
			                	assertEquals("abc",node.get("login").asText());
			                	assertEquals("def",node.get("name").asText());
			                	assertEquals("mno",node.get("reponame").asText());	                	

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
