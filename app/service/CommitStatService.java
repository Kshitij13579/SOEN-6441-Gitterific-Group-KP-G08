package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Collections;
import model.Commit;

public class CommitStatService {
   
   
   public CommitStatService() {
	   
   }
   
   public List<String> getShaList(JsonNode json){
	   List<String> shaList = new ArrayList<String>();
	   
	   json.forEach(j ->{
		   shaList.add(j.get("sha").asText());
	   });
	   
	   return shaList;
   }
   
   public int getMaxAddition(List<Commit> commList) {
	   return commList.stream()
				  .mapToInt(Commit::getAdditions)
				  .max()
				  .getAsInt();
   }
   
   public int getMaxDeletion(List<Commit> commList) {
	   return commList.stream()
				  .mapToInt(Commit::getDeletions)
				  .max()
				  .getAsInt();
   }
   
   public int getMinAddition(List<Commit> commList) {
	   return commList.stream()
				  .mapToInt(Commit::getAdditions)
				  .min()
				  .getAsInt();
   }
   
   public int getMinDeletion(List<Commit> commList) {
	   return commList.stream()
			  .mapToInt(Commit::getDeletions)
			  .min()
			  .getAsInt();
   }
   
   public double getAvgAddition(List<Commit> commList) {
	   return commList.stream()
			  .mapToDouble(Commit::getAdditions)
			  .average()
			  .orElse(Double.NaN);
   }
   
   public double getAvgDeletion(List<Commit> commList) {
	   return commList.stream()
				  .mapToDouble(Commit::getDeletions)
				  .average()
				  .orElse(Double.NaN);
   }
   
   public List<String> getTopCommitter(List<Commit> commList){
	   List<String> topCommitterList = new ArrayList<String>();
	   
	   topCommitterList = commList.stream()
			             .collect(Collectors.groupingBy(Commit::getAuthor,Collectors.counting()))
			             .entrySet()
			             .stream()
			             .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			             .limit(10)
			             .map(Map.Entry::getKey)
			             .collect(Collectors.toList());
	   
	   return topCommitterList;
   }
}
