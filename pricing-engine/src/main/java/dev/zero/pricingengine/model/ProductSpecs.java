package dev.zero.pricingengine.model;

/**
 * ProductSpecs - Holds the product specifications
 * @author ppalit
 *
 */
public class ProductSpecs {

	private String productName;
	private String supply;
	private String demand;

	public ProductSpecs(String productName, String supply, String demand) {
		super();
		this.productName = productName;
		this.supply = supply;
		this.demand = demand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupply() {
		return supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

}
