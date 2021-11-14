package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Collections;

import model.Author;
import model.Commit;

public class CommitStatService {
   
   
   public CommitStatService() {
	   
   }
   
   public List<String> getShaList(JsonNode json){
	   List<String> shaList = new ArrayList<String>();
	   
	   json.forEach(j ->{
		   if(j.has("sha")) {
		     shaList.add(j.get("sha").asText());
		   }
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
   
   public int getCommitsByAuthor(String name,List<Commit> commList) {
	   int total = 0;
	   
	   Long l  =  commList.stream()
			   .map(Commit::getAuthor)
			   .collect(Collectors.groupingBy(Author::getName,Collectors.counting()))
			   .entrySet()
			   .stream()
			   .filter(map -> name.equals(map.getKey()))
			   .mapToLong(Map.Entry::getValue)
			   .findFirst().getAsLong();
	   
	   total = l.intValue();
	   
	   return total;
   }
   
   public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
   {
       Map<Object, Boolean> map = new ConcurrentHashMap<>();
       return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
   }
   
   public List<Author> getTopCommitterList(List<Commit> commList){
	   List<Author> topCommitterList = new ArrayList<Author>();
	   
	  final List<String> topcommitter = commList.stream()
			             .map(Commit::getAuthor)
			             .collect(Collectors.groupingBy(Author::getName,Collectors.counting()))
			             .entrySet()
			             .stream()
			             .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			             .limit(10)
			             .map(Map.Entry::getKey)
			             .collect(Collectors.toList());
	   
	   topCommitterList = commList.stream()
			               .map(Commit::getAuthor)
			              .filter(a -> topcommitter.contains(a.getName()))
			              .filter(distinctByKey(a -> a.getName()))
			              .collect(Collectors.toList());
	   
	   topCommitterList.forEach( author ->{
		   author.setCommits(getCommitsByAuthor(author.getName(), commList));
	   });
	   
	   topCommitterList = topCommitterList.stream()
			              .sorted(Collections.reverseOrder(Comparator.comparing(Author::getCommits)))
	                      .collect(Collectors.toList());
	   
	   return topCommitterList;
   }
   
}
