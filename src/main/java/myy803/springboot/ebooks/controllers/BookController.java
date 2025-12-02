package myy803.springboot.ebooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.services.AuthorService;
import myy803.springboot.ebooks.services.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	AuthorService authorService;
		
	@RequestMapping(value = "/book/new-offer", method = RequestMethod.GET)
	public String createNewBookOffer(Model model) {
		model.addAttribute("offerform", new BookFormData());
		model.addAttribute("authorsT", authorService.findAll());
		return "book/new";
	}
	
	@RequestMapping(value = "/book/new-offer", method = RequestMethod.POST)
	public String submitBookForm(@ModelAttribute("offerform") BookFormData book) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
		bookService.save(book, username);
		
		return "redirect:/book/my-books";
	}
	
	@RequestMapping(value = "/book/new-author", method = RequestMethod.GET)
	public String createNewAuthor() {
	
		return "book/new-author";
	}
	
	@RequestMapping(value = "/book/new-author", method = RequestMethod.POST)
	public String submitAuthorForm(@RequestParam("name") String name) {
		authorService.save(name);
		
		return "redirect:/home	";
	}
	
	@RequestMapping(value = "/book/my-books", method = RequestMethod.GET)
	public String getMyBooksList(HttpServletRequest request, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
		model.addAttribute("books", bookService.findMyBookOffers(username));
		
		String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);
        
		return "book/my-books";
	}
	
	@RequestMapping(value = "/book/delete", method = RequestMethod.GET)
	public String deleteBook(@RequestParam("book_id") int book_id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
		bookService.delete(book_id, username);
		
		return "redirect:/book/my-books";
	}
	
	@RequestMapping(value = "/book/request", method = RequestMethod.GET)
	public String requestBook(@RequestParam("book_id") int book_id, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
		bookService.request(book_id, username);
		
		return "redirect:" + request.getHeader("referer");
	}
		
	@RequestMapping(value = "/book/requests", method = RequestMethod.GET)
	public String getBookRequests(@RequestParam("book_id") int book_id, Model model) {
		model.addAttribute("users", bookService.findBookRequests(book_id));
		model.addAttribute("book_id", book_id);
        
		return "book/requests";
	}
	
	@RequestMapping(value = "/book/requests/choose", method = RequestMethod.GET) 
	public String chooseRequestingUser(@RequestParam("user") String username, @RequestParam("book_id") int book_id) {
		
		bookService.informUsers(username, book_id);
		
		return "redirect:/book/my-books";
	}	
}
