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
import model.GithubApiImpl;
import play.Application;
import play.cache.AsyncCacheApi;
import play.inject.guice.GuiceApplicationBuilder;
import actors.SupervisorActor.Data;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

import static play.inject.Bindings.bind;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * Test class for RepositoryProfileActorTest  
 * @author Yogesh Yadav
 *@version 1.0
 *@since 2021-12-07
 */
public class RepositoryProfileActorTest {
	 
	public static ActorSystem system;
	public static Materializer materializer;
	public AsyncCacheApi cache;
	public GithubApi ghApi;
	Application application;

		
		@Before
		  public  void setup() {
		    system = ActorSystem.create();
		    application = new GuiceApplicationBuilder().overrides(bind(GithubApi.class).to(GithubApiMock.class)).build();
		    //application = new GuiceApplicationBuilder().overrides(bind(GithubApi.class).to(GithubApiImpl.class)).build();
		    ghApi = application.injector().instanceOf(GithubApi.class);
		  }
		
		@Test
		public void testRepositoryMockProfileActor() {
		   
			new TestKit(system) {
				{   
					final Props props = RepositoryProfileActor.props(getTestActor(), cache, ghApi);
			        final ActorRef subject = system.actorOf(props);	
			        final TestKit probe = new TestKit(system);
			        
			        within(
			                Duration.ofSeconds(10),
			                () -> {
			                	
			                	subject.tell(new Data(),getRef());
			                	expectNoMsg();
			                	
			                	ObjectNode testData = Json.newObject(); 
			                	testData.put("user","twbs");
			                	testData.put("repository","bootstrap");
			                	//testData.put("user","yogeshoyadav08");
			                	//testData.put("repository","Inwincibles");
			                	Logger.debug("*********Expected Data {}",testData);
			                	subject.tell(testData,getRef());
			                	
			                	subject.tell(new Data(),getRef());
			                	ObjectNode node = expectMsgClass(ObjectNode.class);
			                	Logger.debug("*********Actual Data {}",node);
			                	//assertEquals("yogeshoyadav08",node.get("login").asText());
			                	//assertEquals("Inwincibles",node.get("name").asText());
			                	assertEquals("twbs",node.get("login").asText());
			                	assertEquals("bootstrap",node.get("name").asText());

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
