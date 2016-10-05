package dev.zero.pricingengine.io;

import java.util.Map;

public class ConsoleDataOutput implements DataOutput {

	public void displayData(Map<String, Double> productPriceMap) {
		int charNum = 65;
		for (Map.Entry<String, Double> price : productPriceMap.entrySet()) {
			System.out.println((char) charNum++ + " " + price.getValue());
		}

	}

}
