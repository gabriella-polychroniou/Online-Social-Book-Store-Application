package myy803.springboot.ebooks.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.ebooks.models.BookAuthor;

public interface BookAuthorMapper extends JpaRepository<BookAuthor, Integer>{
	BookAuthor findByName(String name);
}
