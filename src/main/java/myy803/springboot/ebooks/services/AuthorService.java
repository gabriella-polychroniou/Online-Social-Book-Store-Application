package myy803.springboot.ebooks.services;

import java.util.List;

import myy803.springboot.ebooks.models.BookAuthor;

public interface AuthorService {
	List<BookAuthor> findAll();
	void save(String name);
}