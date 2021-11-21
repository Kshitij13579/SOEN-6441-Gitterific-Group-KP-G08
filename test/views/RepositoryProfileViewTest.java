package views;

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
public class RepositoryProfileViewTest extends WithBrowser  {

	/**
	 * Test to Validate runInBrowser() from Routes  
	 */
	@Test
	  public void runInBrowser() {
	    browser.goTo("/repository/:username/:repository");
	    assertNotNull(browser.el("title").text());
	  }
}

