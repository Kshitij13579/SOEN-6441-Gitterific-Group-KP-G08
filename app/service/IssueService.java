package service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import model.Issues;

/**
 * This is the Service Class for Repository Issues which process json repsonse
 * @author Akshay
 * @version 1
 *
 */
public class IssueService {
	
	/**
	 * Empty Constructor for IssueService
	 */
	public IssueService() {
		
	}
	
	/**
	 * This method return issue titles from json file
	 * @param json This is the Json Response from API
	 * @return returning list of issues
	 */
	public List<Issues> getTitleList(JsonNode json){
		
		List<Issues> titleList=new ArrayList<Issues>();
		
		 json.forEach(t->{ 
			 String title=t.get("title").asText(); 
			 titleList.add(new Issues(title));
			 });
		 
		 return titleList;
		
	}

}
