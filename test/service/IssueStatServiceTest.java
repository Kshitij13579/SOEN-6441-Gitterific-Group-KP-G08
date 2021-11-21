package service;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matcher;

import org.junit.Test;
import org.mockito.internal.matchers.Contains;
import com.gargoylesoftware.htmlunit.javascript.host.dom.AbstractList;

import model.Issues;

/**
 * This Method tests Issue Statistics Service 
 * @author Akshay
 *
 */
public class IssueStatServiceTest {
	
	
	 /**
	 * List of words
	 */
	List<String> word = new ArrayList<>();
	 /**
	 * List of word counts
	 */
	List<Long> count = new ArrayList<>();	
	 
	 /**
	 * 
	 * Creates issue service object
	 */
	IssueStatService issueStatService=new IssueStatService();
	 
	 /**
	 * 
	 * List of Issues
	 */
	List<Issues> issuesList=Arrays.asList(new Issues("hi"));
	 
	 /**
	 * 
	 * List of words and Count
	 */
	List[] test=issueStatService.wordCountDescening(issuesList);
	
	
	/**
	 * Method tests word count for issues
	 */
	@Test
	public  void testwordCountDescending() {
		
		 word.add("hi");
			count.add(1l);
			test[0]=word;
			test[1]=count;
		
		assertEquals(test[0],word);
		assertEquals(test[1],count);	
		
		//test nulls
		test[0]=null;
		test[1]=null;
		
		assertNull(test[0]);
		assertNull(test[1]);
		
	}
	
	/**
	 * Method tests not null values
	 */
	@Test
	public void testNotEquals() {
		
		 word.add("hi");
			count.add(1l);
			test[0]=word;
			test[1]=count;
		
		assertNotEquals(test[1],word);
		assertNotEquals(test[0],count);
	}



	

}
