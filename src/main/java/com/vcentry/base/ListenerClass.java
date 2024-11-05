package com.vcentry.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class ListenerClass extends BaseClass implements ITestListener{
	
	public void onTestStart(ITestResult result) {
	   	 test=extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getName()+" is passed");
		log.info(result.getName()+" is passed");
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getName()+" is Failed "+result.getThrowable());
		log.error(result.getName()+" is Failed");
		test.addScreenCaptureFromPath(screenshot());
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getName()+" is Skipped");
		log.info(result.getName()+" is Skipped");
	}

	public void onStart(ITestContext context) {
		log.info("*********************Execution Start***************************");
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		log.info("*********************Execution completed***************************");
		
	}

}
