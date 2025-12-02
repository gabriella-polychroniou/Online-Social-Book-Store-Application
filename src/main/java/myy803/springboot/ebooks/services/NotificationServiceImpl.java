package myy803.springboot.ebooks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.ebooks.mappers.NotificationMapper;
import myy803.springboot.ebooks.mappers.UserProfileMapper;
import myy803.springboot.ebooks.models.Notification;
import myy803.springboot.ebooks.models.UserProfile;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired 
	NotificationMapper mapper;
	
	@Autowired
	UserProfileMapper userMapper;
	
	@Override
	public void send(String username, String title, boolean success) {
		Notification notification = new Notification();
	
		UserProfile user = userMapper.findByUsername(username);
		notification.setUser(user);
		
		if (success)
			notification.setText("Έχεις επιλεγεί για να λάβεις το βιβλίο με τίτλο: " + title + ". ");
		else 
			notification.setText("Δυστυχώς το βιβλίο με τίτλο: " + title + " έχει δωθεί σε άλλο χρήστη. ");
		
		mapper.save(notification);
	}

	@Override
	public void delete(int id) {
		mapper.deleteById(id);		
	}
	
}

