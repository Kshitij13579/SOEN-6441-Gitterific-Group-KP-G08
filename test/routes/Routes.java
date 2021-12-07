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
	 * Test to Validate runInBrowserRepoProfile() from Routes  
	  * @author Yogesh Yadav
	  *@version 1.0
	  **/
	@Test
	  public void runInBrowserRepoProfile() {
	    browser.goTo("/repository/:username/:repository");
	    assertNotNull(browser.el("title").text());
	  }
	/**
	 * Test to Validate runInBrowserIssues() from Routes  
	  * @author Yogesh Yadav
	  *@version 1.0
	  **/
	@Test
	  public void runInBrowserIssues() {
	    browser.goTo("/search/:user/:repository/issues");
	    assertNotNull(browser.el("title").text());
	  }
	/**
	 * Test to Validate runInBrowserTopics() from Routes  
	  * @author Yogesh Yadav
	  *@version 1.0
	  **/
	@Test
	  public void runInBrowserTopics() {
	    browser.goTo("/topics/:topic");
	    assertNotNull(browser.el("title").text());
	  }
	/**
	 * Test to Validate runInBrowserIndex() from Routes  
	  * @author Yogesh Yadav
	  *@version 1.0
	  **/
	@Test
	  public void runInBrowserIndex() {
	    browser.goTo("/");
	    assertNotNull(browser.el("title").text());
	  }
	/**
	 * Test to Validate runInBrowserSearch() from Routes  
	  * @author Yogesh Yadav
	  *@version 1.0
	  **/
	@Test
	  public void runInBrowserSearch() {
	    browser.goTo("/search");
	    assertNotNull(browser.el("title").text());
	  }
	/**
	 * Test to Validate runInBrowserCommits() from Routes  
	  * @author Yogesh Yadav
	  *@version 1.0
	  **/
	@Test
	  public void runInBrowserCommits() {
	    browser.goTo("/search/:user/:repository/commits");
	    assertNotNull(browser.el("title").text());
	  }
	/**
	 * Test to Validate runInBrowserUsers() from Routes  
	  * @author Yogesh Yadav
	  *@version 1.0
	  **/
	@Test
	  public void runInBrowserUsers() {
	    browser.goTo("/users/:username");
	    assertNotNull(browser.el("title").text());
	  }
	/**
	 * Test to Validate runInBrowserUserRepo() from Routes  
	  * @author Yogesh Yadav
	  *@version 1.0
	  **/
	@Test
	  public void runInBrowserUserRepo() {
	    browser.goTo("/users/repos/:username");
	    assertNotNull(browser.el("title").text());
	  }
	
	/**
	 * Test to Validate runInBrowserAssest() from Routes  
	  * @author Yogesh Yadav
	  *@version 1.0
	  **/
	@Test
	  public void runInBrowserAssest() {
	    browser.goTo("/assets/*file");
	    assertNotNull(browser.el("title").text());
	  }
}

