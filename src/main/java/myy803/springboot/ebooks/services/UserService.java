package myy803.springboot.ebooks.services;

import myy803.springboot.ebooks.models.User;

public interface UserService {
	boolean isUserPresent(User user);
	void save(User user);
	User findById(int id);
}
