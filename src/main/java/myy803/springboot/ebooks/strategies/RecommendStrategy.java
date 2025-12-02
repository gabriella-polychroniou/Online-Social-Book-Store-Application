package myy803.springboot.ebooks.strategies;

import java.util.List;

import myy803.springboot.ebooks.formsdata.BookFormData;
import myy803.springboot.ebooks.formsdata.RecommendFormData;
import myy803.springboot.ebooks.mappers.BookMapper;

public interface RecommendStrategy {
	public List<BookFormData> recommend(RecommendFormData recomFormData, BookMapper bookMapper);
}
