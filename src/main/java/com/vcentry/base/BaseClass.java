package com.vcentry.base;

import java.io.File;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vcentry.utils.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentReports extent=new ExtentReports();
	public static ExtentSparkReporter spark=new ExtentSparkReporter(Function.getProp("reportPath"));
	public static ExtentTest test;
	public static Logger log=LogManager.getLogger(BaseClass.class);
	
	@BeforeSuite
	public void beforeSuite() {
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("MyReport");
	}
	
	
	@BeforeTest
	public static void initilize() {
		try {
		String browser=Function.getProp("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public static void waitForWebElement(By element) {
		try {
			 WebDriverWait wait =new WebDriverWait
			(driver, Duration.ofSeconds(Integer.parseInt(Function.getProp("wait"))));
		       wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		     test.log(Status.PASS,element +" is visible");
		     log.info(element +" is visible");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(Status.FAIL,element +" is not visible");
		     log.info(element +" is not visible");
		}
	}
		public static void clickElement(By element) {
			try {
				waitForWebElement(element);
				driver.findElement(element).click();
				test.log(Status.PASS,element +" is Cliked");
			     log.info(element +" is clicked");
			}catch (Exception e) {
				e.printStackTrace();
				test.log(Status.FAIL,element +" is not cliked");
			     log.info(element +" is not clicked");	
			}
		}
		
		public static void enterText(By element,String data) {
			try {
				waitForWebElement(element);
				driver.findElement(element).sendKeys(data);
				test.log(Status.PASS,data+"is enetered in"+element );
			     log.info(data+"is enetered in"+element);
			}catch (Exception e) {
				e.printStackTrace();
				test.log(Status.FAIL,data+"is not enetered in"+element);
			     log.info(data+"is not enetered in"+element);	
			}
		}
	
		public static String getText(By element) {
			String text=null;
			try {
				waitForWebElement(element);
				text =driver.findElement(element).getText();
				test.log(Status.PASS, text+" is present");
				log.info(text+" is present");
			}catch (Exception e) {
				e.printStackTrace();
				test.log(Status.FAIL, element+" is not present "+e.getMessage());
				log.info(element+" is not present");
				// TODO: handle exception
			}
			return text;
		}

		public static void selectByText(By element,String text) {
			
			try {
				waitForWebElement(element);
				
				Select s=new Select(driver.findElement(element));
				s.selectByVisibleText(text);
				test.log(Status.PASS, text+" is selected");
				log.info(text+" is selected");
			}catch (Exception e) {
				e.printStackTrace();
				test.log(Status.FAIL, element+" is not selected "+e.getMessage());
				log.info(element+" is not selected");
				// TODO: handle exception
			}
			
		}

		public static void selectCheckBox(By element) {
			
			try {
				waitForWebElement(element);
				WebElement elmt=driver.findElement(element);
				if(elmt.isSelected()) {
					test.log(Status.PASS, element+" checkbox already selected");
					log.info(element+" checkbox already selected");	
				}else {
					clickElement(element);
					test.log(Status.PASS, element+" checkbox is slected");
					log.info(element+" checkbox is selected");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				test.log(Status.FAIL, element+" checkbox is not selected "+e.getMessage());
				log.info(element+" checkbox is not selected");
				// TODO: handle exception
			}
			
		}

		public static String screenshot() {
			String destination=null;
			try {
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 destination =System.getProperty("user.dir")
					 +"/target/screenshot_"+Function.currentDateandTime()+".jpg";
		    FileHandler.copy(src,new File(destination));
			}catch (Exception e) {
				e.printStackTrace();
			}	
		return destination;
		}
	
}
