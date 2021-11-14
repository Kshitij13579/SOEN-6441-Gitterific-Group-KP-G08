package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.Collections;

import model.Issues;

public class IssueStatService {
	
	public List<String> freqList;
	
	public List<String> wordCountDescening(List<Issues> issueList){
		

	
		List<String> titles=issueList.stream().map(Issues::getTitle).collect(Collectors.toList());
		
		//List<String> to String
		String joined = titles.stream().map(Object::toString).collect(Collectors.joining(" "));		
		
		Stream<String> stream=Stream.of(joined.toLowerCase().split(" "));
		
		Map<String, Long> wordFreq=stream.collect(Collectors.groupingBy(String::toString,Collectors.counting()));
		
		freqList=new ArrayList(wordFreq.entrySet());
		System.out.println(freqList);
		
		return freqList;
		
		
	}
	
	
}
