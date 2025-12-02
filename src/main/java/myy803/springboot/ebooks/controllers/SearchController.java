package myy803.springboot.ebooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import myy803.springboot.ebooks.formsdata.SearchFormData;
import myy803.springboot.ebooks.services.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value = "/search-form", method = RequestMethod.GET)
	public String getSearchForm(Model model) {
		
		return "search/search-form";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET) 
	public String search(@RequestParam("searchTitle") String titles, 
						 @RequestParam("searchAuthor") String authors, 
						 @RequestParam("searchStrategy") String strategy,
						 HttpServletRequest request, Model model) {
	
		// To be able to read the url to specify the template
		String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);
		
		SearchFormData searchFormData = new SearchFormData();
		
		for (String title: titles.strip().split("\n")) 
			searchFormData.getSearchTitle().add(title);
		
		for (String author: authors.strip().split("\n")) 
			searchFormData.getSearchAuthor().add(author);
		
		searchFormData.setSearchStrategy(strategy);
		
		model.addAttribute("books", searchService.search(searchFormData));
	                
		return "book/my-books";
	}
}
