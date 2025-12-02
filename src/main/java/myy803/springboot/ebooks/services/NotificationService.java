package myy803.springboot.ebooks.services;

public interface NotificationService {
	void send(String username, String title, boolean success);
	void delete(int id);
}
