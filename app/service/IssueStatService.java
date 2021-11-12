package service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.Collections;

import model.Issues;

public class IssueStatService {
	
	public Map<String,Long> wordCountDescening(List<Issues> issueList){
		

	//return issueList.stream().map(Issues::getTitle).collect(Collectors.groupingBy(w->w,Collectors.counting())).
	//entrySet().stream().filter(e->e.getValue()==1).count();
		List<String> titles=issueList.stream().map(Issues::getTitle).collect(Collectors.toList());
		
		//List<String> to String
		String joined = titles.stream().map(Object::toString).collect(Collectors.joining(" "));
		
		 Map<String, Long> strFrequency = Stream.of(joined).map(str->str.split(" ")).map(Object::toString)
				    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
				   System.out.println(strFrequency);
			
		//Map<String, Long>strfrq=titles.stream().map(s->s.split("")).
		//collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		return strFrequency;
		
		
	}
	
	
}
