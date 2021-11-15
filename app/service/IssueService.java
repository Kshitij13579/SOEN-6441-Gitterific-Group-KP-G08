package service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import model.Issues;

public class IssueService {
	
	public IssueService() {
		
	}
	
	public List<Issues> getTitleList(JsonNode json){
		
		List<Issues> titleList=new ArrayList<Issues>();
		
		
		 json.forEach(t->{ 
			 String title=t.get("title").asText(); 
			 titleList.add(new Issues(title));
			 });
		 
		 return titleList;
		
	}

}
