package myy803.springboot.ebooks.formsdata;

import java.util.HashSet;
import java.util.Set;

public class UserProfileFormData {
	
	int id;
	
	String username;
	
	long phonenumber;
	
	String fullname;
	
	String address;
	
	int age;
	
	Set<String> favoriteBookAuthors = new HashSet<String>();
	
	Set<String> favoriteBookCategories = new HashSet<String>();
	
	public UserProfileFormData() {}
		
	public UserProfileFormData(int id, String username, long phonenumber, String fullname, String address, int age,
			Set<String> favoriteBookAuthors, Set<String> favoriteBookCategories) {
		super();
		this.id = id;
		this.username = username;
		this.phonenumber = phonenumber;
		this.fullname = fullname;
		this.address = address;
		this.age = age;
		this.favoriteBookAuthors = favoriteBookAuthors;
		this.favoriteBookCategories = favoriteBookCategories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<String> getFavoriteBookAuthors() {
		return favoriteBookAuthors;
	}

	public void setFavoriteBookAuthors(Set<String> favoriteBookAuthors) {
		this.favoriteBookAuthors = favoriteBookAuthors;
	}

	public Set<String> getFavoriteBookCategories() {
		return favoriteBookCategories;
	}

	public void setFavoriteBookCategories(Set<String> favoriteBookCategories) {
		this.favoriteBookCategories = favoriteBookCategories;
	}	
}
