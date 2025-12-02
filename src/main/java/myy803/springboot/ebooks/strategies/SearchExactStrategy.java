package myy803.springboot.ebooks.strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import myy803.springboot.ebooks.formsdata.SearchFormData;
import myy803.springboot.ebooks.mappers.BookMapper;
import myy803.springboot.ebooks.models.Book;
import myy803.springboot.ebooks.models.BookAuthor;

public class SearchExactStrategy extends TemplateSearchStrategy {

	@Override
	public List<Book> makeInitialListOfBooks(SearchFormData searchFormData, BookMapper bookMapper) {
		
		List<Book> books = new ArrayList<Book>();
		
		for (String title: searchFormData.getSearchTitle()) {
			books.addAll(bookMapper.findByTitle(title));
		}
		
		return books;
	}

	@Override
	public boolean checkIfAuthorsMatch(SearchFormData searchFormData, Book book) {

		boolean[] flags = new boolean[searchFormData.getSearchAuthor().size()];
		Arrays.fill(flags, false);
        		
		int i = 0;
		for (String author: searchFormData.getSearchAuthor()) {
			for (BookAuthor bookAuthor: book.getAuthors()) {
				if (bookAuthor.getName().equals(author)) {
					flags[i] = true;
					break;
				}
			}
			i++;
		}
		
		for (boolean flag: flags)
			if (!flag)
				return false;
		
		return true;
	}

}
