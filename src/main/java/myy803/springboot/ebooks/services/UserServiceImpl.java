package myy803.springboot.ebooks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import myy803.springboot.ebooks.mappers.UserMapper;
import myy803.springboot.ebooks.models.User;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	UserMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return mapper.findByUsername(username);
	}

	@Override
	public boolean isUserPresent(User user) {
		return mapper.existsByUsername(user.getUsername());
	}
	
	@Override
	public void save(User user) {
		mapper.save(user);
	}

	@Override
	public User findById(int id) {
		return mapper.findById(id);
	}

}
