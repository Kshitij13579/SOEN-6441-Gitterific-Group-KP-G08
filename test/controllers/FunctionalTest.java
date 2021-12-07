package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import play.libs.Json;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.netty.ws.NettyWebSocket;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocket;
import play.test.TestServer;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

public class FunctionalTest {


    /**
     * Tests if the ws() is accepting WebSocket requests
     * @author Mrinal Rai
     */
    @Test
    public void testWsAcceptWebSocket() {
        TestServer server = testServer(37117);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                WebSocketClient webSocketClient = new WebSocketClient(client);

                try {
                    String serverURL = "ws://localhost:37117/ws";
                    WebSocketClient.LoggingListener listener = new WebSocketClient.LoggingListener(message -> {});
                    CompletableFuture<NettyWebSocket> completionStage = webSocketClient.call(serverURL, serverURL, listener);
                    await().until(completionStage::isDone);
                    assertNotNull(completionStage.get());
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                // fail("Unexpected exception", e);
            }
        });
    }
    
    /**
     * Tests if the wsTopic() is accepting WebSocket requests
     * @author Mrinal Rai
     */
    @Test
    public void testWsTopicAcceptWebSocket() {
        TestServer server = testServer(37117);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                WebSocketClient webSocketClient = new WebSocketClient(client);

                try {
                    String serverURL = "ws://localhost:37117/wsTopic";
                    WebSocketClient.LoggingListener listener = new WebSocketClient.LoggingListener(message -> {});
                    CompletableFuture<NettyWebSocket> completionStage = webSocketClient.call(serverURL, serverURL, listener);
                    await().until(completionStage::isDone);
                    assertNotNull(completionStage.get());
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                // fail("Unexpected exception", e);
            }
        });
    }
    
    /**
     * Tests if the wsRepositoryProfile() is accepting WebSocket requests
     * @author Mrinal Rai
     */
    @Test
    public void testwsRepositoryProfileAcceptWebSocket() {
        TestServer server = testServer(37117);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                WebSocketClient webSocketClient = new WebSocketClient(client);

                try {
                    String serverURL = "ws://localhost:37117/wsRepositoryProfile";
                    WebSocketClient.LoggingListener listener = new WebSocketClient.LoggingListener(message -> {});
                    CompletableFuture<NettyWebSocket> completionStage = webSocketClient.call(serverURL, serverURL, listener);
                    await().until(completionStage::isDone);
                    assertNotNull(completionStage.get());
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                // fail("Unexpected exception", e);
            }
        });
    }
    
    /**
     * Tests if the ws() is accepting WebSocket requests
     * @author Mrinal Rai
     */
    @Test
    public void testwsCommitAcceptWebSocket() {
        TestServer server = testServer(37117);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                WebSocketClient webSocketClient = new WebSocketClient(client);

                try {
                    String serverURL = "ws://localhost:37117/wsCommit";
                    WebSocketClient.LoggingListener listener = new WebSocketClient.LoggingListener(message -> {});
                    CompletableFuture<NettyWebSocket> completionStage = webSocketClient.call(serverURL, serverURL, listener);
                    await().until(completionStage::isDone);
                    assertNotNull(completionStage.get());
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                // fail("Unexpected exception", e);
            }
        });
    }
    
    /**
     * Tests if the wsup() is accepting WebSocket requests
     * @author Mrinal Rai
     */
    @Test
    public void testwsupCommitAcceptWebSocket() {
        TestServer server = testServer(37117);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                WebSocketClient webSocketClient = new WebSocketClient(client);

                try {
                    String serverURL = "ws://localhost:37117/wsup";
                    WebSocketClient.LoggingListener listener = new WebSocketClient.LoggingListener(message -> {});
                    CompletableFuture<NettyWebSocket> completionStage = webSocketClient.call(serverURL, serverURL, listener);
                    await().until(completionStage::isDone);
                    assertNotNull(completionStage.get());
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                // fail("Unexpected exception", e);
            }
        });
    }
    
    /**
     * Tests if the wsur() is accepting WebSocket requests
     * @author Mrinal Rai
     */
    @Test
    public void testwsurCommitAcceptWebSocket() {
        TestServer server = testServer(37117);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                WebSocketClient webSocketClient = new WebSocketClient(client);

                try {
                    String serverURL = "ws://localhost:37117/wsur";
                    WebSocketClient.LoggingListener listener = new WebSocketClient.LoggingListener(message -> {});
                    CompletableFuture<NettyWebSocket> completionStage = webSocketClient.call(serverURL, serverURL, listener);
                    await().until(completionStage::isDone);
                    assertNotNull(completionStage.get());
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                // fail("Unexpected exception", e);
            }
        });
    }
    
    /**
     * Tests if the wsIssue() is accepting WebSocket requests
     * @author Mrinal Rai
     */
    @Test
    public void testwsIssueCommitAcceptWebSocket() {
        TestServer server = testServer(37117);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                WebSocketClient webSocketClient = new WebSocketClient(client);

                try {
                    String serverURL = "ws://localhost:37117/wsIssue";
                    WebSocketClient.LoggingListener listener = new WebSocketClient.LoggingListener(message -> {});
                    CompletableFuture<NettyWebSocket> completionStage = webSocketClient.call(serverURL, serverURL, listener);
                    await().until(completionStage::isDone);
                    assertNotNull(completionStage.get());
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                // fail("Unexpected exception", e);
            }
        });
    }
}
