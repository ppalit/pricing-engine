package dev.zero.pricingengine.model;
import java.util.List;

public class InputBean {
	
	int numberOfProducts;
	List<ProductSpecs> productList;
	
	int numberOfSurvey;
	List<Survey> surveyList;
	
	public int getNumberOfProducts() {
		return numberOfProducts;
	}
	public void setNumberOfProducts(int numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}
	public List<ProductSpecs> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductSpecs> productList) {
		this.productList = productList;
	}
	public int getNumberOfSurvey() {
		return numberOfSurvey;
	}
	public void setNumberOfSurvey(int numberOfSurvey) {
		this.numberOfSurvey = numberOfSurvey;
	}
	public List<Survey> getSurveyList() {
		return surveyList;
	}
	public void setSurveyList(List<Survey> surveyList) {
		this.surveyList = surveyList;
	}
	
	
	

}
