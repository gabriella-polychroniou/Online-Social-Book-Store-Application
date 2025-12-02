package myy803.springboot.ebooks.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.ebooks.models.User;

public interface UserMapper extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	User findById(int id);
	boolean existsByUsername(String username);
}
