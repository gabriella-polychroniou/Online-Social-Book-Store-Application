package myy803.springboot.ebooks.services;

import java.util.Set;

import myy803.springboot.ebooks.formsdata.UserProfileFormData;
import myy803.springboot.ebooks.models.Notification;

public interface UserProfileService {
	UserProfileFormData findByUsername(String username);
	void save(UserProfileFormData userProfileFormData);
	Set<Notification> findAllNotifications(String username);
	void edit(UserProfileFormData userProfileFormData);
}
