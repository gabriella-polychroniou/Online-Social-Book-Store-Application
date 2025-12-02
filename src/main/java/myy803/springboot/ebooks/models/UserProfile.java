package myy803.springboot.ebooks.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="profiles")
public class UserProfile {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name="user_name", unique=true)
	String username;
	
	@Column(name = "phone_number", columnDefinition = "BIGINT")
	long phonenumber;
	
	@Column(name = "full_name")
	String fullname;
	
	@Column(name = "address") 
	String address;
	
	@Column(name = "age")
	int age;
	
	@ManyToMany
	Set<BookAuthor> favoriteBookAuthors = new HashSet<BookAuthor>();
	
	@ManyToMany
	Set<BookCategory> favoriteBookCategories = new HashSet<BookCategory>();
	
	@OneToMany(mappedBy = "owner")
	Set<Book> bookOffers = new HashSet<Book>();
	
	@ManyToMany
	@JoinTable(name = "book_requesting_users",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "book_id"))
	Set<Book> requestedBooks = new HashSet<Book>();
	
	@OneToMany(mappedBy = "user")
	Set<Notification> notifications = new HashSet<Notification>();
	
	public UserProfile() {}
	
	public UserProfile(String username, long phonenumber, String fullname, String address, int age,
			Set<BookAuthor> favoriteBookAuthors, Set<BookCategory> favoriteBookCategories) {
		super();
		this.username = username;
		this.phonenumber = phonenumber;
		this.fullname = fullname;
		this.address = address;
		this.age = age;
		this.favoriteBookAuthors = favoriteBookAuthors;
		this.favoriteBookCategories = favoriteBookCategories;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public long getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<BookAuthor> getFavoriteBookAuthors() {
		return favoriteBookAuthors;
	}

	public void setFavoriteBookAuthors(Set<BookAuthor> favoriteBookAuthors) {
		this.favoriteBookAuthors = favoriteBookAuthors;
	}

	public Set<BookCategory> getFavoriteBookCategories() {
		return favoriteBookCategories;
	}

	public void setFavoriteBookCategories(Set<BookCategory> favoriteBookCategories) {
		this.favoriteBookCategories = favoriteBookCategories;
	}

	public Set<Book> getBookOffers() {
		return bookOffers;
	}

	public void setBookOffers(Set<Book> bookOffers) {
		this.bookOffers = bookOffers;
	}

	public Set<Book> getRequestedBooks() {
		return requestedBooks;
	}

	public void setRequestedBooks(Set<Book> requestedBooks) {
		this.requestedBooks = requestedBooks;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

}
