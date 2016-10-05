package dev.zero.pricingengine;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dev.zero.pricingengine.io.DataInput;
import dev.zero.pricingengine.model.InputBean;
import dev.zero.pricingengine.model.ProductSpecs;
import dev.zero.pricingengine.model.Survey;

public class AppControlTest {

	private InputBean inputBean;
	private DataInput dataInput;
	AppControl appControl;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception {
		inputBean = new InputBean();
		
		inputBean.setNumberOfProducts(2);
		List<ProductSpecs> productList = new ArrayList<ProductSpecs>();
		ProductSpecs prod1 = new ProductSpecs("flashdrive", "H", "H");
		productList.add(prod1);
		ProductSpecs prod2 = new ProductSpecs("ssd", "L", "H");
		productList.add(prod2);
		inputBean.setProductList(productList);
		
		inputBean.setNumberOfSurvey(5);
		List<Survey> surveyList = new ArrayList<Survey>();
		Survey s1 =  new Survey("flashdrive", "X", 1.0);
		surveyList.add(s1);
		Survey s2 =  new Survey("ssd", "X", 10.0);
		surveyList.add(s2);
		Survey s3 =  new Survey("flashdrive", "Y", 0.9);
		surveyList.add(s3);
		Survey s4 =  new Survey("flashdrive", "Z", 1.1);
		surveyList.add(s4);
		Survey s5 =  new Survey("ssd", "Y", 12.5);
		surveyList.add(s5);
		inputBean.setSurveyList(surveyList);
		
		appControl= new AppControl();
		System.setOut(new PrintStream(outContent));
		
	}

	/**
	 * Integration Test 
	 */
	@Test
	public void executeTest() {
		dataInput = Mockito.mock(DataInput.class);
		when(dataInput.getInputData()).thenReturn(inputBean);
		appControl.dataInput = dataInput;
		appControl.execute();
		
		ByteArrayOutputStream testContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testContent));
		System.out.println("A 0.9");
		System.out.println("B 10.5");
		assertEquals(testContent.toString(), outContent.toString());
	}
	
	@Test
	public void finalPriceForProductTest(){
		appControl.inputBean = inputBean;
		Map<String, Double> finalPricingMap = new HashMap<String, Double>();
		finalPricingMap.put("flashdrive", 0.9);
		finalPricingMap.put("ssd", 10.0);
		assertEquals("{flashdrive=0.9, ssd=10.5}",appControl.finalPriceForProduct(finalPricingMap).toString());
		
	}
	

}
