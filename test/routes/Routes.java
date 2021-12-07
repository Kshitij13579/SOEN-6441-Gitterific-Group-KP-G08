package routes;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import play.test.WithBrowser;

/**
 * Test class for Routes  
 * @author Yogesh Yadav
 *@version 1.0
 *@since 2021-11-20
 */
public class Routes extends WithBrowser  {

	/**
	 * Test to Validate runInBrowser() from Routes  
	 */
	@Test
	  public void runInBrowserRepoProfile() {
	    browser.goTo("/repository/:username/:repository");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserIssues() {
	    browser.goTo("/search/:user/:repository/issues");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserTopics() {
	    browser.goTo("/topics/:topic");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserIndex() {
	    browser.goTo("/");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserSearch() {
	    browser.goTo("/search");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserCommits() {
	    browser.goTo("/search/:user/:repository/commits");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserUsers() {
	    browser.goTo("/users/:username");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserUserRepo() {
	    browser.goTo("/users/repos/:username");
	    assertNotNull(browser.el("title").text());
	  }
	/*
	 @Test
	 
	  public void runInBrowserWS() {
	    browser.goTo("/ws");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserWSCommit() {
	    browser.goTo("/wsCommit");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserWSTopic() {
	    browser.goTo("/wsTopic");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserWSUserProfile() {
	    browser.goTo("/wsup");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserWSUserProfileRepo() {
	    browser.goTo("/wsur");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserWSRepoProfile() {
	    browser.goTo("/wsRepositoryProfile");
	    assertNotNull(browser.el("title").text());
	  }
	@Test
	  public void runInBrowserWSIssue() {
	    browser.goTo("/wsIssue");
	    assertNotNull(browser.el("title").text());
	  }
	*/
	@Test
	  public void runInBrowserAssest() {
	    browser.goTo("/assets/*file");
	    assertNotNull(browser.el("title").text());
	  }
}

