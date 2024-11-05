package com.vcentry.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.vcentry.base.BaseClass;

public class LoginPage extends BaseClass{
	
	private By username= By.id("id_username");
	private By password=By.id("id_password");
	private By loginBtn=By.xpath("//button[text()='Login']");
	private By user=By.xpath("(//a[@class='nav-item nav-link'])[4]");
	private By errorMessage=By.xpath("//div[contains(@class,'alert-block')]/descendant::li");
    
	public void enterUsername(String uName) {
		enterText(username, uName);
	}
	
	public void enterPassword(String pwd) {
		enterText(password, pwd);
	}
	
	public void clickLoginBtn() {
		clickElement(loginBtn);
	}
	
	public void verifyHomePage(String username) {
		Assert.assertEquals(getText(user), username);
	}
	
	public void verifyError(String errorMsg) {
		Assert.assertEquals(getText(errorMessage), errorMsg);
	}
	
	
}
