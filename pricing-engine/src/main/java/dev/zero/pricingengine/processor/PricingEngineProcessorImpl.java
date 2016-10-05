package dev.zero.pricingengine.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.zero.pricingengine.model.Survey;
import dev.zero.pricingengine.rules.PricingRules;
import dev.zero.pricingengine.rules.Rules;

public class PricingEngineProcessorImpl implements PricingEngineProcessor {
	
	Map<String,Double> productPriceMap; 
	private Map<String,Integer> productCountMap;
	private Rules pricingRules;
	
	public PricingEngineProcessorImpl() {
		this.productPriceMap = new HashMap<String, Double>();
		this.productCountMap = new HashMap<String, Integer>();
		this.pricingRules = new PricingRules();
	}

	public Map<String, Double> getAvgPricing(List<Survey> surveyList) {
		
		for (Survey surveyElement : surveyList) {
			Double d1 = productPriceMap.get(surveyElement.getProductName());
			if(d1==null){
				productPriceMap.put(surveyElement.getProductName(), surveyElement.getCompetitorPrice());
				productCountMap.put(surveyElement.getProductName(), 1);
			}
			else{
				productPriceMap.put(surveyElement.getProductName(), surveyElement.getCompetitorPrice() + d1);
				productCountMap.put(surveyElement.getProductName(),productCountMap.get(surveyElement.getProductName())+1);
			}
		}

		for (Map.Entry<String, Double> entry : productPriceMap.entrySet()){
		    productPriceMap.put(entry.getKey(), entry.getValue() / productCountMap.get(entry.getKey()));
		    
		}
		
		return productPriceMap;
	}

	public void markSurveyValid(List<Survey> surveyList) {
		for (Survey survey : surveyList) {
			double price = productPriceMap.get(survey.getProductName());
			if(survey.getCompetitorPrice() < (price * 1.5 ) && survey.getCompetitorPrice() > (price * 0.5)) {
				survey.setValid(true);
			}else{
				survey.setValid(false);
			}
		}
	}

	public Map<String, Double> findBestPriceForProduct(List<Survey> surveyList) {
		Map<Double,Integer> priceCount= new HashMap<Double, Integer>();
		for (Map.Entry<String, Double> entry : productPriceMap.entrySet()){
			for (Survey survey : surveyList) {
				//System.out.println("survey.getProductName()"+survey.getProductName()+"entry.getKey()"+entry.getKey()+"survey.isValid()"+survey.isValid());
				if(survey.getProductName().equals(entry.getKey()) && survey.isValid()){
					if(priceCount == null){
						priceCount.put(survey.getCompetitorPrice(), 1);
					}else{
						Integer value = priceCount.get(survey.getCompetitorPrice()); 
						value=value==null?0:value;
						priceCount.put(survey.getCompetitorPrice(), value + 1);
					}
				}
			}
			// find min and put it in productPriceMap			
		    productPriceMap.put(entry.getKey(), findMinProductPrice(priceCount));
		    priceCount.clear();
		    
		}
		return productPriceMap;
			
	}
	
	double findMinProductPrice(Map<Double,Integer> priceCount){
		int min = 0 ;
		double productPrice = 0;
		for (Map.Entry<Double,Integer> price : priceCount.entrySet()){
			if(price.getValue() > min){					
				min = price.getValue();
				productPrice = price.getKey();
				
			}else if(price.getValue() == min && price.getKey() < productPrice){
				min = price.getValue();
				productPrice = price.getKey();
			}
			
		}
		
		return productPrice;
	}
	
	

}
