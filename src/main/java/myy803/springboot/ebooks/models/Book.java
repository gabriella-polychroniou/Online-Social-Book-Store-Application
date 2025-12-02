package myy803.springboot.ebooks.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="books") 
public class Book {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "title")
	String title;
	
	@Column(name = "summary", length = 1000)
	String summary;
	
	@ManyToOne
	UserProfile owner;
	
	@ManyToMany
	@JoinTable(name = "book_authors",
		joinColumns = @JoinColumn(name = "book_id"),
		inverseJoinColumns = @JoinColumn(name = "author_id"))
	Set<BookAuthor> authors = new HashSet<BookAuthor>();
	
	@ManyToOne
	BookCategory category;
	
	@ManyToMany
	@JoinTable(name = "book_requesting_users",
	    joinColumns = @JoinColumn(name = "book_id"),
	    inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<UserProfile> requestingUsers = new HashSet<UserProfile>();
	
	public Book() {}
	
	public Book(String title, String summary, Set<BookAuthor> authors, BookCategory category) {
		this.title = title;
		this.summary = summary;
		this.authors = authors;
		this.category = category;
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
	
	public UserProfile getOwner() {
		return owner;
	}

	public void setOwner(UserProfile owner) {
		this.owner = owner;
	}

	public Set<BookAuthor> getAuthors() {
		return authors;
	}
	
	public Set<String> getAuthorsToString() {
		Set<String> authorsT = new HashSet<String>();
		for (BookAuthor author: this.authors) {
			authorsT.add(author.getName());
		}
		
		return authorsT;
	}
	
	public void setAuthors(Set<BookAuthor> authors) {
		this.authors = authors;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public Set<UserProfile> getRequestingUsers() {
		return requestingUsers;
	}

	public void setRequestingUsers(Set<UserProfile> requestingUsers) {
		this.requestingUsers = requestingUsers;
	}
}