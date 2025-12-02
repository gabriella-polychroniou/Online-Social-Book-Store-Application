package myy803.springboot.ebooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.springboot.ebooks.formsdata.UserProfileFormData;
import myy803.springboot.ebooks.models.User;
import myy803.springboot.ebooks.services.AuthorService;
import myy803.springboot.ebooks.services.CategoryService;
import myy803.springboot.ebooks.services.NotificationService;
import myy803.springboot.ebooks.services.UserProfileService;
import myy803.springboot.ebooks.services.UserService;

@Controller
public class AuthController {
	
	@Autowired
    UserService userService;
	
	@Autowired
    AuthorService authorService;

	@Autowired
    CategoryService categoryService;
	
	@Autowired
	UserProfileService profileService;
	
	@Autowired
	NotificationService notificationService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new UserProfileFormData());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        
        return "user/register";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        model.addAttribute("user", profileService.findByUsername(username));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        
        return "user/edit";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") UserProfileFormData userProfile) {
    	profileService.edit(userProfile);
    	
    	return "redirect:/home";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String registerUser(@RequestParam("password") String password,
    							@ModelAttribute("user") UserProfileFormData userProfile, Model model){
    	User user = new User();
    	user.setUsername(userProfile.getUsername());
    	user.setPassword(new BCryptPasswordEncoder().encode(password));
    	
        if(userService.isUserPresent(user)){
            return "redirect:/register?error=true";
        }
                
        userService.save(user);
        profileService.save(userProfile);
        
        model.addAttribute("successMessage", true);

        return "user/login";
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome() {
    	return "dashboard";
    }
    
    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public String getNotifications(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        model.addAttribute("notifications", profileService.findAllNotifications(username));
        
        return "user/notifications";
    }
    
    @RequestMapping(value = "/notifications/delete", method = RequestMethod.GET)
    public String getNotifications(@RequestParam("not_id") int id) {
    	notificationService.delete(id);   
        return "redirect:/notifications";
    }
}
