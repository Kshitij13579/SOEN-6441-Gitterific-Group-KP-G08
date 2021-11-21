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

/**
* The CommitStatService class is used  to calculate statistics of all commits.
* @author  Kshitij Yerande
* @version 1.0
* @since   2021-11-20 
*/
public class CommitStatService {
   
   
   public CommitStatService() {
	   
   }
   
   /**
    * Method to return list of all sha id from commits.
    * @param json
    * @return List<String> returns sha of all commits fetched from API
    */
   public List<String> getShaList(JsonNode json){
	   List<String> shaList = new ArrayList<String>();
	   
	   json.forEach(j ->{
		   if(j.has("sha")) {
		     shaList.add(j.get("sha").asText());
		   }
	   });
	   
	   return shaList;
   }
   
/**
 * Method to get maximum number of additions from commit list.
 * @param commList - list of commits fetched from API
 * @return int returns maximum number of additions from commits.
 */
   public int getMaxAddition(List<Commit> commList) {
	   return commList.stream()
				  .mapToInt(Commit::getAdditions)
				  .max()
				  .getAsInt();
   }
   
   /**
    * Method to get maximum number of deletions from commit list.
    * @param commList - list of commits fetched from API
    * @return int returns maximum number of deletions from commits.
    */
   public int getMaxDeletion(List<Commit> commList) {
	   return commList.stream()
				  .mapToInt(Commit::getDeletions)
				  .max()
				  .getAsInt();
   }
   
   /**
    * Method to get minimum number of additions from commit list.
    * @param commList - list of commits fetched from API
    * @return int returns minimum number of additions from commits.
    */
   public int getMinAddition(List<Commit> commList) {
	   return commList.stream()
				  .mapToInt(Commit::getAdditions)
				  .min()
				  .getAsInt();
   }
   
   /**
    * Method to get minimum number of deletions from commit list.
    * @param commList - list of commits fetched from API
    * @return int returns minimum number of deletions from commits.
    */
   public int getMinDeletion(List<Commit> commList) {
	   return commList.stream()
			  .mapToInt(Commit::getDeletions)
			  .min()
			  .getAsInt();
   }
   
   /**
    * Method to get average number of additions from commit list.
    * @param commList - list of commits fetched from API
    * @return double returns average number of additions from commits.
    */
   public double getAvgAddition(List<Commit> commList) {
	   return commList.stream()
			  .mapToDouble(Commit::getAdditions)
			  .average()
			  .orElse(Double.NaN);
   }
   
   /**
    * Method to get average number of deletions from commit list.
    * @param commList - list of commits fetched from API
    * @return double returns average number of deletions from commits.
    */
   public double getAvgDeletion(List<Commit> commList) {
	   return commList.stream()
				  .mapToDouble(Commit::getDeletions)
				  .average()
				  .orElse(Double.NaN);
   }
   
   /**
    * Method to get total number of commits per Author.
    * @param commList - list of commits fetched from API
    * @param name - Author name
    * @return int returns number of commits for a particular Author.
    */
   public int getCommitsByAuthor(String name,List<Commit> commList) {
	   int total = 0;
	   
	   boolean isPresent = commList.stream()
			               .map(Commit::getAuthor)
			               .anyMatch( a -> a.getName().equals(name));
	   
	   if(!isPresent) {
		   return 0;
	   }
	   
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
   
   /**
    * Method to get distinct object by key from map.
    * @return Predicate returns maximum number of additions from commits.
    */
   public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
   {
       Map<Object, Boolean> map = new ConcurrentHashMap<>();
       return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
   }
   
   /**
    * Method to get list of top 10 committers from list of commits. 
    * @param commList - list of commits fetched from API
    * @return List<Author> returns list of Author.
    */
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
