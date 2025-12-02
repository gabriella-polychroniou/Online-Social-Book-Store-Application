package myy803.springboot.ebooks.formsdata;

import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class RecommendFormData {
	
	String currentUsername;
	
	Set<String> favoriteAuthors;
	
	Set<String> favoriteCategories;
	
	String recommendStrategy;
	
	public String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		currentUsername = authentication.getName();
        
        return currentUsername;
	}

	public String getRecommendStrategy() {
		return recommendStrategy;
	}

	public void setRecommendStrategy(String recommendStrategy) {
		this.recommendStrategy = recommendStrategy;
	}

	public Set<String> getFavoriteAuthors() {
		return favoriteAuthors;
	}

	public void setFavoriteAuthors(Set<String> favoriteAuthors) {
		this.favoriteAuthors = favoriteAuthors;
	}

	public Set<String> getFavoriteCategories() {
		return favoriteCategories;
	}

	public void setFavoriteCategories(Set<String> favoriteCategories) {
		this.favoriteCategories = favoriteCategories;
	}	
}
