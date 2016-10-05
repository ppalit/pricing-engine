package dev.zero.pricingengine;

import java.util.List;
import java.util.Map;

import dev.zero.pricingengine.io.ConsoleDataInputImpl;
import dev.zero.pricingengine.io.ConsoleDataOutput;
import dev.zero.pricingengine.io.DataInput;
import dev.zero.pricingengine.io.DataOutput;
import dev.zero.pricingengine.model.InputBean;
import dev.zero.pricingengine.model.ProductSpecs;
import dev.zero.pricingengine.processor.PricingEngineProcessor;
import dev.zero.pricingengine.processor.PricingEngineProcessorImpl;
import dev.zero.pricingengine.rules.PricingRules;
import dev.zero.pricingengine.rules.Rules;
/**
 * AppControl - Class that controls the flow of execution
 * @author ppalit
 *
 */
public class AppControl {
	InputBean inputBean;
	DataInput dataInput;
	DataOutput dataOutput;
	PricingEngineProcessor processor;
	Rules pricingRules;
	
	public AppControl() {
		this.inputBean = new InputBean();
		this.dataInput = new ConsoleDataInputImpl();
		this.dataOutput = new ConsoleDataOutput();
		this.processor = new PricingEngineProcessorImpl();
		this.pricingRules = new PricingRules();
	}
	
	public void execute(){
		
		//get data from user
		inputBean = dataInput.getInputData();
		
		// find avg price of each product
		Map<String,Double> productPriceMap =  processor.getAvgPricing(inputBean.getSurveyList());
		
		// check which prices to consider
		processor.markSurveyValid(inputBean.getSurveyList());
		
		// among them which price is the most freqently occuring with least price
		productPriceMap = processor.findBestPriceForProduct(inputBean.getSurveyList());
				
		//add factor
		productPriceMap = finalPriceForProduct(productPriceMap);
	
		// print to console
		dataOutput.displayData(productPriceMap);		
		
	}
	
	public Map<String, Double> finalPriceForProduct(Map<String, Double> finalPricingMap){
		List<ProductSpecs> l1 = inputBean.getProductList();
		double value;
		for (ProductSpecs productSpecs : l1) {
			int markup = pricingRules.markup(productSpecs.getSupply(), productSpecs.getDemand());
			value =  finalPricingMap.get(productSpecs.getProductName());
			double val = value + (value*markup/100);
			finalPricingMap.put(productSpecs.getProductName(), val);
		}
		return finalPricingMap;
	}
	
	
	
	

}
