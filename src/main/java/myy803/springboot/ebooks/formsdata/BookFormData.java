package myy803.springboot.ebooks.formsdata;

import java.util.Set;

public class BookFormData {

	int id;
	
	String title;
	
	String summary;
	
	String category;
	
	Set<String> authors;
	
	public BookFormData() {}
	
	public BookFormData(int id, String title, String summary, String category, Set<String> authors) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.category = category;
		this.authors = authors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<String> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<String> authors) {
		this.authors = authors;
	}
	
	
}
