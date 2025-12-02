package myy803.springboot.ebooks.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.ebooks.models.BookCategory;

public interface BookCategoryMapper extends JpaRepository<BookCategory, Integer>{
	BookCategory findByName(String name);
}
