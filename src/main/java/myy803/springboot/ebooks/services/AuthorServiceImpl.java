package myy803.springboot.ebooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.ebooks.mappers.BookAuthorMapper;
import myy803.springboot.ebooks.models.BookAuthor;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	BookAuthorMapper mapper;
	
	@Override
	public List<BookAuthor> findAll() {
		return mapper.findAll();
	}

	@Override
	public void save(String name) {
		
		if (mapper.findByName(name) != null) 
			return;
		
		BookAuthor author = new BookAuthor();
		author.setName(name);
		
		mapper.save(author);
	}

}
