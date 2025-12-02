package myy803.springboot.ebooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.ebooks.mappers.BookCategoryMapper;
import myy803.springboot.ebooks.models.BookCategory;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	BookCategoryMapper mapper;
	
	@Override
	public List<BookCategory> findAll() {
		return mapper.findAll();
	}

	@Override
	public void save(String name) {
		if (mapper.findByName(name) != null)
			return;
		
		BookCategory category = new BookCategory(name);
		mapper.save(category);
	}
	
	@Override
	public BookCategory findByName(String name) {
		return mapper.findByName(name);
	}
}

