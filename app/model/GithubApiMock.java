package model;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.ConfigFactory;

import akka.stream.scaladsl.Source;
import akka.util.ByteString;
import play.api.libs.*;
import play.api.libs.json.JsValue;
import play.api.libs.ws.BodyReadable;
import play.api.libs.ws.WSBodyReadables;
import play.cache.AsyncCacheApi;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import scala.xml.Elem;
import service.RepositorySearchService;

public class GithubApiMock implements GithubApi {
	@Inject AsyncCacheApi cache;
	@Override
	public List<Repository> getRepositoryInfo(String query, boolean isTopic, AsyncCacheApi cache) throws InterruptedException, ExecutionException {
		JsonNode json = getResponse("", "", "", "", cache);
		List<Repository> repoList = new ArrayList<Repository>();
		RepositorySearchService repoService = new RepositorySearchService();
		repoList = repoService.getRepoList(json);
		return repoList;
	}
	
	@Inject WSClient ws;
	@Override
	public JsonNode getResponse(String query, String per_page, String page, String sort, AsyncCacheApi cache) throws InterruptedException, ExecutionException {
		String testResources = System.getProperty("user.dir") + "/test/resources/play.json";
		java.io.File file = new java.io.File(testResources);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = null;
		try {
			json = mapper.readTree(file);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
