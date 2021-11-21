package views;

import views.html.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import service.IssueStatService;
import org.junit.Test;
import model.Issues;
import model.Repository;
import play.twirl.api.Content;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.contentAsString;

/**
 * Tests the views of the application
 * 
 * @author Mrinal Rai
 * @version 1.0
 * @since 2021-11-20
 */
public class viewTest {

	/**
	 * Tests the view of the index action in HomeController class 
	 * Tests the content-type and the Title <code>"Gitterific"</code> in the text of the view
	 * 
	 * @author Mrinal Rai
	 * @since 2021-11-20
	 */
	@Test
	public void testIndexView() {
		
		List<Repository> repoList = new ArrayList<Repository>();
		Content html=views.html.index.render(repoList, "");
		assertEquals("text/html",html.contentType().toString());
		assertTrue(contentAsString(html).contains("Gitterific"));
	}
	
	@Test 
	public void testIssues() {
		
		String repo="testRep";
		List<Issues> issuesList=Arrays.asList(new Issues("hello"));
		IssueStatService issueStatService=new IssueStatService();
		List[] frequencyList=issueStatService.wordCountDescening(issuesList);
		Content html=views.html.issues.render(issuesList,frequencyList[0],frequencyList[1],repo);
		assertEquals("text/html", html.contentType().toString());
		assertTrue(contentAsString(html).contains("Repository Issues"));		
	}
	
}
