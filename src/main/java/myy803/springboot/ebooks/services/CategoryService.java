package myy803.springboot.ebooks.services;

import java.util.List;

import myy803.springboot.ebooks.models.BookCategory;

public interface CategoryService {
	List<BookCategory> findAll();
	void save(String name);
	BookCategory findByName(String name);
}
