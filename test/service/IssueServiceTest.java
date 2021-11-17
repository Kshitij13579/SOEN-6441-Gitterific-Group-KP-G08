package service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Issues;

public class IssueServiceTest {
	
	
	 IssueService issueService=new IssueService();
	 
	 Issues issue;
	 List<Issues> issuesList=new ArrayList<Issues>();
	 
	 Issues issue1,issue2;
	 
	 @Before
	 public void init() {
		 
		 issue1=new Issues("hi");
		 issue2=new Issues("hello");
		 
		 issuesList.add(issue1);
		 issuesList.add(issue2);
	 }

	@Test
	public void getTitleListTest() {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = "[{title : \"hi\"},{title : \"hello\"}]";
			JsonNode json= mapper.readTree(jsonString);
			
			String nullJsonString = "[{tnd : \"abc34\"},{zwr : \"bkb235\"}]";
			JsonNode nullJson= mapper.readTree(nullJsonString);
			
			
			List<Issues> expected_TitleList = new ArrayList<Issues>();
			expected_TitleList.add(issue1);
			expected_TitleList.add(issue2);
			

			assertTrue(issueService.getTitleList(json).containsAll(expected_TitleList));
			assertTrue(issueService.getTitleList(nullJson).isEmpty());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
