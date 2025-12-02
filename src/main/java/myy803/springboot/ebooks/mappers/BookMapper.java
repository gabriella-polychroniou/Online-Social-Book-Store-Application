package myy803.springboot.ebooks.mappers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.ebooks.models.Book;

public interface BookMapper extends JpaRepository<Book, Integer> {
	List<Book> findByTitle(String title);
	List<Book> findByTitleContaining(String title);
}
