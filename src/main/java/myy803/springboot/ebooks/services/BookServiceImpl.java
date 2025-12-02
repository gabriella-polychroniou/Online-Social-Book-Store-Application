package myy803.springboot.ebooks.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.UserProfileFormData;
import myy803.springboot.ebooks.mappers.BookAuthorMapper;
import myy803.springboot.ebooks.mappers.BookCategoryMapper;
import myy803.springboot.ebooks.mappers.BookMapper;
import myy803.springboot.ebooks.mappers.UserProfileMapper;
import myy803.springboot.ebooks.models.Book;
import myy803.springboot.ebooks.models.BookAuthor;
import myy803.springboot.ebooks.models.BookCategory;
import myy803.springboot.ebooks.models.UserProfile;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	BookAuthorMapper authorMapper;
	
	@Autowired
	BookCategoryMapper categoryMapper;
	
	@Autowired
	UserProfileMapper profileMapper;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	NotificationService notificationService;
	
	@Override
	public void save(BookFormData bookFormData, String username) {
		Set<BookAuthor> authors = new HashSet<BookAuthor>();
		for (String author: bookFormData.getAuthors()) {
			authors.add(authorMapper.findByName(author));
		}
		
		categoryService.save(bookFormData.getCategory());
		
		Book book = new Book(bookFormData.getTitle(), 
							bookFormData.getSummary(),
							authors,
							categoryMapper.findByName(bookFormData.getCategory()));
		
		UserProfile userProfile = profileMapper.findByUsername(username);
		userProfile.getBookOffers().add(book);
		book.setOwner(userProfile);
		
		bookMapper.save(book);
	}

	@Override
	public Set<BookFormData> findMyBookOffers(String username) {
		Set<Book> books = profileMapper.findByUsername(username).getBookOffers();
		
		Set<BookFormData> bookForms = new HashSet<BookFormData>();
		for (Book book: books) {
			bookForms.add(new BookFormData(book.getId(), 
											book.getTitle(),
											book.getSummary(),
											book.getCategory().getName(),
											book.getAuthorsToString()));
			
	
		}
		
		return bookForms;
	}

	@Override
	public void delete(int book_id, String username) {
		UserProfile user = profileMapper.findByUsername(username);
		Optional<Book> book = bookMapper.findById(book_id);
		
		if (book != null && user.getBookOffers().contains(book.get())) 
			bookMapper.delete(book.get());
		
	}

	@Override
	public Set<BookFormData> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserProfile user = profileMapper.findByUsername(username);
        
		Set<BookFormData> allbooks = new HashSet<BookFormData>();
		for (Book book: bookMapper.findAll()) {
			if (!user.getBookOffers().contains(book)) {
				allbooks.add(new BookFormData(book.getId(), 
												book.getTitle(),
												book.getSummary(),
												book.getCategory().getName(),
												book.getAuthorsToString()));
			}
				
		}
		
		return allbooks;
	}

	@Override
	public void request(int book_id, String username) {
		Optional<Book> book = bookMapper.findById(book_id);
		UserProfile profile = profileMapper.findByUsername(username);
		
		if (book != null) {
			profile.getRequestedBooks().add(book.get());
			
			profileMapper.save(profile);
		}
	}
	
	@Override
	public Set<UserProfileFormData> findBookRequests(int id) {
		Optional<Book> book = bookMapper.findById(id);
				
		Set<UserProfileFormData> users = new HashSet<UserProfileFormData>();
		for (UserProfile user: book.get().getRequestingUsers()) {
			Set<String> categories = new HashSet<String>();
			for (BookCategory category: user.getFavoriteBookCategories()) 
				categories.add(category.getName());
			
			Set<String> authors = new HashSet<String>();
			for (BookAuthor author: user.getFavoriteBookAuthors()) 
				authors.add(author.getName());
			
			users.add(new UserProfileFormData(user.getId(),
												user.getUsername(),
												user.getPhonenumber(),
												user.getFullname(),
												user.getAddress(),
												user.getAge(),
												authors,
												categories));
		}
		
		return users;
	}

	@Override
	public void informUsers(String username, int book_id) {
		Optional<Book> book = bookMapper.findById(book_id);
		
		if (book != null) {
			for (UserProfile user: book.get().getRequestingUsers()) {
				if (user.getUsername().equals(username)) 
					notificationService.send(user.getUsername(), book.get().getTitle(), true);
				else 
					notificationService.send(user.getUsername(), book.get().getTitle(), false);
			}
		}
	}
}

