package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

import model.Issues;

/**
 * This class computes Repository Title Statistics
 * @author Akshay
 * @version 1
 *
 */
public class IssueStatService {

	
	/**
	 * This method accepts list of repository issues and process
	 * @author Akshay
	 * @param issueList list of repository issue titles
	 * @return	return lists of unique words and their occurrence 
	 */
	public List[] wordCountDescening(List<Issues> issueList){
		
		
		List<String> titles=issueList.stream().map(Issues::getTitle).collect(Collectors.toList());
		
		//List<String> to String
		String joined = titles.stream().map(Object::toString).collect(Collectors.joining(" "));		
		
		Stream<String> stream=Stream.of(joined.toLowerCase().split("[\\pP\\s&&[^']]+"));
		
		Map<String, Long> wordFreq=stream.collect(Collectors.groupingBy(String::toString,Collectors.counting()));
		
		Map<String,Long> sortedMapDescending=wordFreq.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
											collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1,LinkedHashMap::new));
		
		List<String> wordList=new ArrayList<String>(sortedMapDescending.keySet());
		List<Long>   wordCount=new ArrayList<Long>(sortedMapDescending.values());
		
		
		return new List[] {wordList,wordCount};
		
		
	}
	
	
}
