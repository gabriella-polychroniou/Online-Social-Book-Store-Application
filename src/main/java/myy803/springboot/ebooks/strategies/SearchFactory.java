package myy803.springboot.ebooks.strategies;

import org.springframework.stereotype.Service;

@Service
public class SearchFactory {
	
	public SearchStrategy getStrategy(String strategy) {
		
		SearchStrategy searchStrategy = null;
		
		if (strategy.equals("ExactSearch")) {
			searchStrategy = new SearchExactStrategy();
		}
		
		if (strategy.equals("ApproximateSearch")) {
			searchStrategy = new SearchApproxStrategy();
		}
		
		return searchStrategy;
	}
}
