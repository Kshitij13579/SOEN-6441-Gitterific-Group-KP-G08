package views;

import static org.junit.Assert.*;

import org.junit.Test;

import play.test.WithBrowser;

public class RepositoryProfileViewTest extends WithBrowser  {

	@Test
	  public void runInBrowser() {
	    browser.goTo("/repository/:username/:repository");
	    assertNotNull(browser.el("title").text());
	  }
}

