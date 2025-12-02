package myy803.springboot.ebooks.strategies;

import org.springframework.stereotype.Service;

@Service
public class RecommendFactory {

	public RecommendStrategy getStrategy(String strategy) {
		
		RecommendStrategy recommendStrategy = null;
		
		if (strategy.equals("Category")) {
			recommendStrategy = new CategoryRecommendStrategy();
		}
		
		if (strategy.equals("Author")) {
			recommendStrategy = new AuthorRecommendStrategy();
		}
		
		if (strategy.equals("Combined")) {
			recommendStrategy = new CombinedRecommendStrategy();
		}
		
		return recommendStrategy;
	}
}
