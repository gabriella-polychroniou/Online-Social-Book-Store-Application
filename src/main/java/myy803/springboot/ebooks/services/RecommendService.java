package myy803.springboot.ebooks.services;

import java.util.List;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.RecommendFormData;

public interface RecommendService {
	List<BookFormData> recommend(RecommendFormData recomFormData);
}
