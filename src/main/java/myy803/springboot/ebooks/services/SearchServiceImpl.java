package myy803.springboot.ebooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.SearchFormData;
import myy803.springboot.ebooks.mappers.BookMapper;
import myy803.springboot.ebooks.strategies.SearchFactory;
import myy803.springboot.ebooks.strategies.SearchStrategy;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	SearchFactory searchFactory;
	
	@Override
	public List<BookFormData> search(SearchFormData searchFormData) {	
		
		String strategy = searchFormData.getSearchStrategy();
		SearchStrategy searchStrategy = searchFactory.getStrategy(strategy);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
		
		return searchStrategy.search(searchFormData, bookMapper, username);
	}

}
