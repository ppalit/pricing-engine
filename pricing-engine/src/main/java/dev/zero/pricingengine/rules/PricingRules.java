package dev.zero.pricingengine.rules;

/**
 * PricingRules - defines the rules for marking up or down the price.
 * @author ppalit
 *
 */
public class PricingRules implements Rules{
	
	public int markup(String supply, String demand){
		int markupFactor = 0;
		if(supply.equalsIgnoreCase("H") && demand.equalsIgnoreCase("H")){
			markupFactor = 0;
		}
		else if(supply.equalsIgnoreCase("H") && demand.equalsIgnoreCase("L")){
			markupFactor = -5;
		}
		else if(supply.equalsIgnoreCase("L") && demand.equalsIgnoreCase("H")){
			markupFactor = 5;
		}
		else if(supply.equalsIgnoreCase("L") && demand.equalsIgnoreCase("L")){
			markupFactor = 10;
		}
		return markupFactor; 
	}

}
