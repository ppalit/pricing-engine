package dev.zero.pricingengine.model;

/**
 * Survey - Holds the details of individual survey
 * @author ppalit
 *
 */
public class Survey {

	private String productName;
	private String competitorName;
	private double competitorPrice;
	private boolean valid;

	public Survey(String productName, String competitorName, double competitorPrice) {
		super();
		this.productName = productName;
		this.competitorName = competitorName;
		this.competitorPrice = competitorPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCompetitorName() {
		return competitorName;
	}

	public void setCompetitorName(String competitorName) {
		this.competitorName = competitorName;
	}

	public double getCompetitorPrice() {
		return competitorPrice;
	}

	public void setCompetitorPrice(double competitorPrice) {
		this.competitorPrice = competitorPrice;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
