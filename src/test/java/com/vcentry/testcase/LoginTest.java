package com.vcentry.testcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vcentry.base.BaseClass;
import com.vcentry.pages.LoginPage;
import com.vcentry.utils.Function;

public class LoginTest extends BaseClass{
     LoginPage login;
	@BeforeMethod
	public void launchUrl() {
		driver.get(Function.getProp("loginUrl"));
		login=new LoginPage();
	}
	
	@Test(dataProvider = "ValidLogin")
	public void LoginWithValidCredentials(String uName,String pwd,String expectedResult) {
		login.enterUsername(uName);
		login.enterPassword(pwd);
		login.clickLoginBtn();
		login.verifyHomePage(expectedResult);
	}
	
	@Test(dataProvider = "inValidLogin")
	public void LoginWithInvalidCredentials(String uName,String pwd,String expectedResult) {
		login.enterUsername(uName);
		login.enterPassword(pwd);
		login.clickLoginBtn();
		login.verifyError(expectedResult);
	}
	
	@DataProvider(name="ValidLogin")
	public String[][] validLogin(){
		return Function.getTestData("login", "ValidCredentails");
	}
	
	@DataProvider(name="inValidLogin")
	public String[][] inValidLogin(){
		return Function.getTestData("login", "InvalidCredetials");
	} 	
}
