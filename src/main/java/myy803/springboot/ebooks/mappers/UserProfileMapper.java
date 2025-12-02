package myy803.springboot.ebooks.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.ebooks.models.UserProfile;

public interface UserProfileMapper extends JpaRepository<UserProfile, Integer>{
	UserProfile findByUsername(String username);
}
