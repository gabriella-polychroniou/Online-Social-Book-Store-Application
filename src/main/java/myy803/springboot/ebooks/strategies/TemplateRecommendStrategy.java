package myy803.springboot.ebooks.strategies;

import java.util.ArrayList;
import java.util.List;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.RecommendFormData;
import myy803.springboot.ebooks.mappers.BookMapper;
import myy803.springboot.ebooks.models.Book;
import myy803.springboot.ebooks.models.UserProfile;

public abstract class TemplateRecommendStrategy implements RecommendStrategy {
	
	@Override
	public List<BookFormData> recommend(RecommendFormData recomFormData, BookMapper bookMapper) {
		List<Book> books = makeInitialListOfBooks(recomFormData, bookMapper);
		
		ArrayList<BookFormData> booksFD = new ArrayList<BookFormData>();
		for (Book book: books)
			if (!book.getOwner().getUsername().equals(recomFormData.getCurrentUsername()) && 
					!checkIfIsAlreadyRequested(book, recomFormData.getCurrentUsername()))
			
				booksFD.add(new BookFormData(book.getId(), 
											 book.getTitle(),
											 book.getSummary(),
											 book.getCategory().getName(),
											 book.getAuthorsToString()));
		
		
		return booksFD;
	}
	
	public abstract List<Book> makeInitialListOfBooks(RecommendFormData recomFormData, BookMapper bookMapper);

	private boolean checkIfIsAlreadyRequested(Book book, String username) {
		
		for (UserProfile user: book.getRequestingUsers()) 
			if (user.getUsername().equals(username)) 
				return true;
			
		return false;
	}
}
