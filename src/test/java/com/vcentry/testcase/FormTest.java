package com.vcentry.testcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vcentry.base.BaseClass;
import com.vcentry.pages.FormPage;
import com.vcentry.pages.LoginPage;
import com.vcentry.utils.Function;

public class FormTest extends BaseClass{

	FormPage form;
	@BeforeMethod
	public void launchUrl() {
		driver.get(Function.getProp("formUrl"));
		form=new FormPage();
	}
	
	@Test(dataProvider="validData")
	public void placeOrder(String product,String mobileNum,String emailId,String category,String quantity,String name,String gst,String payment,String msg) {
		form.enterProdName(product);
		form.enterMobNum(mobileNum);
		form.enterEmailId(emailId);
		form.selectProdCtg(category);
		form.enterProdQty(quantity);
		form.enterPurName(name);
		form.selectGst(gst);
		form.selecPayment(payment);
		form.placeOrder();
		form.verifyOrderPlaced(msg);
		System.out.println("msg");
	}
	
	@DataProvider(name= "validData")
	public String[][] validData(){
		return Function.getTestData("form", "validData");
	}
}
