package myy803.springboot.ebooks.strategies;

import java.util.ArrayList;
import java.util.List;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.SearchFormData;
import myy803.springboot.ebooks.mappers.BookMapper;
import myy803.springboot.ebooks.models.Book;
import myy803.springboot.ebooks.models.UserProfile;

public abstract class TemplateSearchStrategy implements SearchStrategy {
	
	@Override
	public ArrayList<BookFormData> search(SearchFormData searchFormData, BookMapper bookMapper, String username) {
		List<Book> books = makeInitialListOfBooks(searchFormData, bookMapper);
		
		ArrayList<BookFormData> booksFD = new ArrayList<BookFormData>();
		for (Book book: books)
			if (checkIfAuthorsMatch(searchFormData, book) && !book.getOwner().getUsername().equals(username)
					&& !checkIfIsAlreadyRequested(book, username))
				booksFD.add(new BookFormData(book.getId(), 
											 book.getTitle(),
											 book.getSummary(),
											 book.getCategory().getName(),
											 book.getAuthorsToString()));
		
		
		return booksFD;
	}
	
	public abstract List<Book> makeInitialListOfBooks(SearchFormData searchFormData, BookMapper bookMapper);
	
	public abstract boolean checkIfAuthorsMatch(SearchFormData searchFormData, Book book);
	
	private boolean checkIfIsAlreadyRequested(Book book, String username) {
		
		for (UserProfile user: book.getRequestingUsers()) 
			if (user.getUsername().equals(username)) 
				return true;
			
		return false;
	}
}
