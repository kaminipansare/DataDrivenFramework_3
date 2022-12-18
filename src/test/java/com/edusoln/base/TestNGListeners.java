package com.edusoln.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestNGListeners extends CommonMethods implements  ITestListener {
	
	public void onTestStart(ITestResult result) {
		System.out.println("========On Test Start============");
	}

	public void onTestSuccess(ITestResult result) {		
		System.out.println("========On Test Success============");		
	}
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("========On Test Skipped============");
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("========On Test Failure============");	
			test.log(Status.FAIL,"test is failed");
			 test.addScreenCaptureFromPath( getScreenshot("FailedSS_"+sentuniquenunmebr()));
		
	}
	
	
}
