package myy803.springboot.ebooks.services;

import java.util.Set;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.UserProfileFormData;

public interface BookService {
	void save(BookFormData bookFormData, String username);
	Set<BookFormData> findMyBookOffers(String username);
	void delete(int book_id, String username);
	Set<BookFormData> findAll();
	void request(int book_id, String username);
	Set<UserProfileFormData> findBookRequests(int id);
	void informUsers(String username, int book_id);
}
