package myy803.springboot.ebooks.strategies;

import java.util.ArrayList;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.SearchFormData;
import myy803.springboot.ebooks.mappers.BookMapper;

public interface SearchStrategy {
	public ArrayList<BookFormData> search(SearchFormData searchFormData, BookMapper bookMapper, String username);
}
