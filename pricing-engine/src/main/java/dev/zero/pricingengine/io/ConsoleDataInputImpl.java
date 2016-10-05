package dev.zero.pricingengine.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.zero.pricingengine.model.InputBean;
import dev.zero.pricingengine.model.ProductSpecs;
import dev.zero.pricingengine.model.Survey;

public class ConsoleDataInputImpl implements DataInput {

	private String lineArr[];
	private ProductSpecs spec;
	private List<ProductSpecs> productList;
	private Survey survey;
	private List<Survey> surveyList;

	public ConsoleDataInputImpl() {
		this.productList = new ArrayList<ProductSpecs>();
		this.surveyList = new ArrayList<Survey>();
		this.lineArr = new String[5];
	}

	public InputBean getInputData() {
		InputBean inputBean = new InputBean();
		readInputFromConsole(inputBean);
		return inputBean;
	}

	private void readInputFromConsole(InputBean inputBean) {

		Scanner scanner = new Scanner(System.in);

		try {
			inputBean.setNumberOfProducts(scanner.nextInt());
			for (int i = 0; i < inputBean.getNumberOfProducts(); i++) {
				scanner = new Scanner(System.in);
				lineArr = scanner.nextLine().split(" ");
				spec = new ProductSpecs(lineArr[0], lineArr[1], lineArr[2]);
				productList.add(spec);

			}
			scanner = new Scanner(System.in);
			inputBean.setNumberOfSurvey(scanner.nextInt());
			for (int i = 0; i < inputBean.getNumberOfSurvey(); i++) {
				scanner = new Scanner(System.in);
				lineArr = scanner.nextLine().split(" ");
				survey = new Survey(lineArr[0], lineArr[1], Double.parseDouble(lineArr[2]));
				surveyList.add(survey);

			}
			inputBean.setProductList(productList);
			inputBean.setSurveyList(surveyList);
		}catch(Exception e){
			System.out.println("Invalid user input!");
		}
		finally {
			scanner.close();
		}
		

	}

}
