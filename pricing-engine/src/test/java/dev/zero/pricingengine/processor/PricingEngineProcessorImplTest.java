/**
 * 
 */
package dev.zero.pricingengine.processor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import dev.zero.pricingengine.model.Survey;

/**
 * @author ppalit
 *
 */
public class PricingEngineProcessorImplTest {

	

	/**
	 * @throws java.lang.Exception
	 */
	
	private List<Survey> surveyList;
	
	@Before
	public void setUp() throws Exception {
		surveyList = new ArrayList<Survey>();
		Survey s1 = new Survey("ssd","W", 11.0);
		surveyList.add(s1);
		Survey s2 = new Survey("ssd","X", 12.0);
		surveyList.add(s2);
		Survey s3 = new Survey("ssd","V", 10.0);
		surveyList.add(s3);
		Survey s4 = new Survey("ssd","Y", 11.0);
		surveyList.add(s4);
		Survey s5 = new Survey("ssd","Z", 12.0);
		surveyList.add(s5);
		Survey s6 = new Survey("mp3player","X", 60.0);
		surveyList.add(s6);
		Survey s7 = new Survey("mp3player","Y", 20.0);
		surveyList.add(s7);
		Survey s8 = new Survey("mp3player","Z", 50.0);
		surveyList.add(s8);
		

	}

	@Test
	public void getAvgPricingTest() {
		PricingEngineProcessor processor = new PricingEngineProcessorImpl();
		Map<String,Double> productPriceMap = processor.getAvgPricing(surveyList);
		assertEquals("{mp3player=43.333333333333336, ssd=11.2}", productPriceMap.toString());
	}
	
	@Test
	public void markSurveyValidTest() {
		
		Map<String,Double> productPriceMap = new HashMap<String, Double>();
		productPriceMap.put("mp3player", 43.333333333333336);
		productPriceMap.put("ssd", 11.2);
		PricingEngineProcessorImpl processor = new PricingEngineProcessorImpl();
		processor.productPriceMap = productPriceMap;
		processor.markSurveyValid(surveyList);
		assertEquals(surveyList.get(2).getProductName()+":"+surveyList.get(2).getCompetitorPrice(),surveyList.get(2).isValid(), true);
		assertEquals(surveyList.get(6).getProductName()+":"+surveyList.get(6).getCompetitorPrice(),surveyList.get(6).isValid(), false);
	}
	
	@Test
	public void findMinProductPriceTest(){
		Map<Double,Integer> priceCount = new HashMap<Double,Integer>();
		priceCount.put(11.5,2);
		priceCount.put(13.5,3);
		priceCount.put(14.5,4);
		priceCount.put(12.5,4);
		priceCount.put(10.5,2);
		PricingEngineProcessorImpl processor = new PricingEngineProcessorImpl();
		assertEquals(12.5,processor.findMinProductPrice(priceCount),0);
	}
	
	@Test
	public void findBestPriceForProductTest(){
		Map<String,Double> productPriceMap = new HashMap<String, Double>();
		productPriceMap.put("mp3player", 43.333333333333336);
		productPriceMap.put("ssd", 11.2);
		for (Survey survey : surveyList) {
			survey.setValid(true);
		}
		PricingEngineProcessorImpl processor = new PricingEngineProcessorImpl();
		processor.productPriceMap = productPriceMap;
		assertEquals("{mp3player=20.0, ssd=11.0}", processor.findBestPriceForProduct(surveyList).toString());
	}

}
