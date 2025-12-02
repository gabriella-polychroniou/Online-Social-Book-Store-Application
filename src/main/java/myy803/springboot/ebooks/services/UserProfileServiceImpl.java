package myy803.springboot.ebooks.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.ebooks.formsdata.UserProfileFormData;
import myy803.springboot.ebooks.mappers.BookAuthorMapper;
import myy803.springboot.ebooks.mappers.UserProfileMapper;
import myy803.springboot.ebooks.models.BookAuthor;
import myy803.springboot.ebooks.models.BookCategory;
import myy803.springboot.ebooks.models.Notification;
import myy803.springboot.ebooks.models.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired
	UserProfileMapper mapper;
	
	@Autowired
	BookAuthorMapper authorMapper;
		
	@Autowired
	CategoryService categoryService;
	
	@Override
	public UserProfileFormData findByUsername(String username) {
		UserProfile profile = mapper.findByUsername(username);
		
		Set<String> categories = new HashSet<String>();
		for (BookCategory category: profile.getFavoriteBookCategories()) 
			categories.add(category.getName());
		
		Set<String> authors = new HashSet<String>();
		for (BookAuthor author: profile.getFavoriteBookAuthors()) 
			authors.add(author.getName());
		
		UserProfileFormData formdata = new UserProfileFormData(profile.getId(),
																profile.getUsername(),
																profile.getPhonenumber(),
																profile.getFullname(),
																profile.getAddress(),
																profile.getAge(),
																authors,
																categories);
		
		return formdata;
	}

	@Override
	public void save(UserProfileFormData userProfileFormData) {
		
		Set<BookCategory> categories = new HashSet<BookCategory>();
		for (String category: userProfileFormData.getFavoriteBookCategories()) {
			categoryService.save(category);
			categories.add(categoryService.findByName(category));
		}
		
		Set<BookAuthor> authors = new HashSet<BookAuthor>();
		for (String author: userProfileFormData.getFavoriteBookAuthors()) {
			authors.add(authorMapper.findByName(author));
		}
		
		mapper.save(new UserProfile(userProfileFormData.getUsername(),
									userProfileFormData.getPhonenumber(),
									userProfileFormData.getFullname(),
									userProfileFormData.getAddress(),
									userProfileFormData.getAge(),
									authors,
									categories));
		
	}
	
	@Override
	public void edit(UserProfileFormData userProfileFormData) {

		Set<BookCategory> categories = new HashSet<BookCategory>();
		for (String category: userProfileFormData.getFavoriteBookCategories()) {
			categoryService.save(category);
			categories.add(categoryService.findByName(category));
		}
		
		Set<BookAuthor> authors = new HashSet<BookAuthor>();
		for (String author: userProfileFormData.getFavoriteBookAuthors()) {
			authors.add(authorMapper.findByName(author));
		}
		
		UserProfile userProfile = mapper.findByUsername(userProfileFormData.getUsername());
		userProfile.setPhonenumber(userProfileFormData.getPhonenumber());
		userProfile.setFullname(userProfileFormData.getFullname());
		userProfile.setAddress(userProfileFormData.getAddress());
		userProfile.setAge(userProfileFormData.getAge());
		userProfile.setFavoriteBookAuthors(authors);
		userProfile.setFavoriteBookCategories(categories);
		
		mapper.save(userProfile);
	}

	@Override
	public Set<Notification> findAllNotifications(String username) {
		return mapper.findByUsername(username).getNotifications();
	}

}

