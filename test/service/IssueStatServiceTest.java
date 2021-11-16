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

public class IssueStatServiceTest {
	
	
	 List<String> word = new ArrayList<>();
	 List<Long> count = new ArrayList<>();	
	 
	 IssueStatService issueStatService=new IssueStatService();
	 
	 List<Issues> issuesList=Arrays.asList(new Issues("hi"));
	 
	 List[] test=issueStatService.wordCountDescening(issuesList);
	
	
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
