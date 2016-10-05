package dev.zero.pricingengine.processor;

import java.util.List;
import java.util.Map;

import dev.zero.pricingengine.model.Survey;

public interface PricingEngineProcessor {
	
	public Map<String,Double> getAvgPricing(List<Survey> surveyList);
	
	public void markSurveyValid(List<Survey> surveyList);
	
	public Map<String, Double> findBestPriceForProduct(List<Survey> surveyList);
	

}
