package myy803.springboot.ebooks.formsdata;

import java.util.HashSet;
import java.util.Set;

public class SearchFormData {
	
	Set<String> searchTitle = new HashSet<String>();
	Set<String> searchAuthor = new HashSet<String>();
	String searchStrategy;
		
	public Set<String> getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(Set<String> searchTitle) {
		this.searchTitle = searchTitle;
	}

	public Set<String> getSearchAuthor() {
		return searchAuthor;
	}

	public void setSearchAuthor(Set<String> searchAuthor) {
		this.searchAuthor = searchAuthor;
	}

	public String getSearchStrategy() {
		return searchStrategy;
	}
	
	public void setSearchStrategy(String searchStrategy) {
		this.searchStrategy = searchStrategy;
	}
}
