package myy803.springboot.ebooks.services;

import java.util.List;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.SearchFormData;

public interface SearchService {
	List<BookFormData> search(SearchFormData searchFormData);
}
