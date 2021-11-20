package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;

public class RepositoryTest {

	@Test
	public void testGetters() {
		
	    ArrayList<String> topics = new ArrayList<String>();
	    topics.add("java");
		Repository repository=new Repository("er1","s228","https://api.github.com/repos/er1/s228/issues{/number}","https://api.github.com/repos/er1/s228/commits%7B/sha%7D%22",topics);

		assertEquals("er1",repository.getLogin());
		assertEquals("s228",repository.getName());
		assertEquals("https://api.github.com/repos/er1/s228/issues{/number}",repository.issues_url);

		assertEquals("https://api.github.com/repos/er1/s228/commits%7B/sha%7D%22",repository.getCommits_url());
				
	}
	
	@Test
	public void testSettersGetters() {
		
		Repository repository=new Repository();
		
		repository.setName("testName");
		repository.setCommits_url("commitsTest");
		repository.setLogin("testLogin");
		repository.setIssues_url("testIssues");
		
		assertEquals("testName", repository.getName());
		assertEquals("commitsTest",repository.getCommits_url());
		assertEquals("testLogin",repository.getLogin());
		assertEquals("testIssues",repository.getIssues_url());
		
	}
	
	@Test
    public void testSetGetTopics(){
        Repository testRepo = new Repository();
        ArrayList<String> testData = new ArrayList<String>(Arrays.asList("Java", "Play")); 
        testRepo.setTopics(testData);
        
        assertTrue(testRepo.getTopics().size() > 0);
        assertTrue(EqualsBuilder.reflectionEquals(testRepo.getTopics(), testData));
    }
	
	@Test
    public void testSetGetTopicsWithEmptyData(){
        Repository testRepo = new Repository();
        ArrayList<String> emptyTestData = new ArrayList<String>(); 
        testRepo.setTopics(emptyTestData);
        
        assertTrue(testRepo.getTopics().size() == 0);
        assertTrue(testRepo.getTopics().isEmpty());
    }
	
	@Test
	public void testConstructor() {
		String testLogin = "username";
		String testName = "reponame";
		String testIssueUrl = "http://issues.com";
		String testCommitUrl = "http://commit.com";
		ArrayList<String> testTopics = new ArrayList<String>(Arrays.asList("Java", "Play")); 
		Repository testRepo = new Repository(testLogin, testName, testIssueUrl, testCommitUrl, testTopics);
		
		assertEquals(testRepo.getCommits_url(), testCommitUrl);
		assertEquals(testRepo.getIssues_url(), testIssueUrl);
		assertEquals(testRepo.getLogin(), testLogin);
		assertEquals(testRepo.getName(), testName);
		assertEquals(testRepo.getTopics(), testTopics);
	}
	
	@Test
	public void testEmptyConstructor() {
		String testLogin = "";
		String testName = "";
		String testIssueUrl = "";
		String testCommitUrl = "";
		ArrayList<String> testTopics = new ArrayList<String>(); 
		Repository testRepo = new Repository();
		
		assertEquals(testRepo.getCommits_url(), testCommitUrl);
		assertEquals(testRepo.getIssues_url(), testIssueUrl);
		assertEquals(testRepo.getLogin(), testLogin);
		assertEquals(testRepo.getName(), testName);
		assertEquals(testRepo.getTopics(), testTopics);
	}
	
	@Test
	public void testRepositoryMethod() {
		String testLogin = "username";
		String testName = "reponame";
		String testIssueUrl = "http://issues.com";
		String testCommitUrl = "http://commit.com";
		ArrayList<String> testTopics = new ArrayList<String>(Arrays.asList("Java", "Play")); 
		Repository testRepo = new Repository(testLogin, testName, testIssueUrl, testCommitUrl, testTopics);
		ArrayList<Repository> testRepoList = new ArrayList<Repository>();
		testRepoList.add(testRepo);

		Repository repository = new Repository();
		ArrayList<Repository> actualRepoList = repository.Repository(testLogin, testName, testIssueUrl, testCommitUrl, testTopics);
		
		assertTrue(testRepoList.size() == 1);
		assertEquals(testRepoList.get(0).getCommits_url(), actualRepoList.get(0).getCommits_url());
		assertEquals(testRepoList.get(0).getIssues_url(), actualRepoList.get(0).getIssues_url());
		assertEquals(testRepoList.get(0).getLogin(), actualRepoList.get(0).getLogin());
		assertEquals(testRepoList.get(0).getName(), actualRepoList.get(0).getName());
		assertEquals(testRepoList.get(0).getTopics(), actualRepoList.get(0).getTopics());
	}
}
