package myy803.springboot.ebooks.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.RecommendFormData;
import myy803.springboot.ebooks.mappers.BookMapper;
import myy803.springboot.ebooks.mappers.UserProfileMapper;
import myy803.springboot.ebooks.models.BookAuthor;
import myy803.springboot.ebooks.models.BookCategory;
import myy803.springboot.ebooks.models.UserProfile;
import myy803.springboot.ebooks.strategies.RecommendFactory;
import myy803.springboot.ebooks.strategies.RecommendStrategy;

@Service
public class RecommendServiceImpl implements RecommendService {

	@Autowired 
	RecommendFactory recommendFactory;
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	UserProfileMapper profileMapper;
	
	@Override
	public List<BookFormData> recommend(RecommendFormData recomFormData) {
		String strategy = recomFormData.getRecommendStrategy();
		RecommendStrategy recommendStrategy = recommendFactory.getStrategy(strategy);
		
		UserProfile profile = profileMapper.findByUsername(recomFormData.getCurrentUsername());
		
		Set<String> favAuthors = new HashSet<String>();
		for (BookAuthor author: profile.getFavoriteBookAuthors()) 
			favAuthors.add(author.getName());
		
		recomFormData.setFavoriteAuthors(favAuthors);
		
		Set<String> favCategories = new HashSet<String>();
		for (BookCategory category: profile.getFavoriteBookCategories()) 
			favCategories.add(category.getName());
		
		recomFormData.setFavoriteCategories(favCategories);
		
		return recommendStrategy.recommend(recomFormData, bookMapper);
	}

}
