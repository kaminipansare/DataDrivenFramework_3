package com.edusoln.runner;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.edusoln.base.CommonMethods;
import com.edusoln.base.Constants;
import com.edusoln.pom.LoginPage;
import com.edusoln.pom.PIMPage;
import com.edusoln.utilities.ExcelReader;
import com.edusoln.utilities.ExtentReporter;
import com.edusoln.utilities.PropertyReader;


public class TestNGRunner extends CommonMethods {
	LoginPage login;
	PIMPage pim;
	static Logger log=LogManager.getLogger(TestNGRunner.class);

	 ExtentReports report;
	@BeforeTest
	public void beforeTest() {
		report=ExtentReporter.initReport();
		log.info("Execution has started");
	}

	@BeforeMethod
	public void browserSetUp() {	
		browserLauch(PropertyReader.getPropValue(Constants.BROWSER));
		driver.get(PropertyReader.getPropValue(Constants.URL));	
		log.debug(PropertyReader.getPropValue(Constants.BROWSER)+"has been launched");
	}	
	
	@DataProvider(name="dp")
	public Object[][] dataSupplier()
	{
		Object[][] finaldata=ExcelReader.readExcel();  		
		return finaldata;		
	}
	
	@Test(dataProvider="dp", priority=0)
	public void testcase(Map<Object, Object> data) {	
		log.info("test has been started");
		softassert=new SoftAssert();
		test=report.createTest(stringValue(data, "TestCaseObjective"));
		if(stringValue(data, "Execute").equals("Yes")){
		login=new LoginPage();
		login.orangeHRMLogin(data,test);			
		pim=new PIMPage();
		pim.addemployee(data, test);	
		log.warn("soft assertion failured observed");
		softassert.assertAll();
		
		}
		else {
			test.log(Status.SKIP, "test has been skipped");
			log.error("test has been skiped");
			throw new SkipException("Test case is not run ");
			
		}

	
}
	
	@AfterMethod
	public void tearDown() {
		hardwait(2000);
		driver.quit();
	
	}
	
	@AfterTest
	public void afterTest()
	{
		report.flush();
		System.out.println("Execution completed");
	}
	
}
