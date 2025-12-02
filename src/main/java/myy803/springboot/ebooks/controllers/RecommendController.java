package myy803.springboot.ebooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import myy803.springboot.ebooks.formsdata.RecommendFormData;
import myy803.springboot.ebooks.services.RecommendService;

@Controller
public class RecommendController {

	@Autowired
	RecommendService recommendService;
	
	@RequestMapping(value = "/recommend", method = RequestMethod.GET) 
	public String recommend(@RequestParam("strategy") String strategy,
							HttpServletRequest request, Model model) {		
		String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);
        
        RecommendFormData recomFormData = new RecommendFormData();
        recomFormData.setRecommendStrategy(strategy);
        
        model.addAttribute("books", recommendService.recommend(recomFormData));
        
		return "book/my-books";
	}
}
