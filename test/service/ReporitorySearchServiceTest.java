package service;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;


import org.junit.Test;

import model.Repository;
import play.api.libs.json.JsObject;
import play.libs.Json;


public class ReporitorySearchServiceTest {

	@Test
	public void test() throws InterruptedException, ExecutionException {
		
		List<Repository> repos= new ArrayList<Repository>();
		ArrayList<String> topics = new ArrayList<String>();
		topics.add("java");
		RepositorySearchService rss=new RepositorySearchService();
		JsonNode json = Json.parse("{\"login\":\"abc\", \"name\":\"def\", \"issues_url\":\"mno\",\"commits_url\":\"pqr\" }");
		repos = rss.getRepoList(json);
		List<Repository> expected = rss.repository.Repository("abc","def","mno","pqr",topics);
		assertTrue(expected.equals(repos));
		
	}

}