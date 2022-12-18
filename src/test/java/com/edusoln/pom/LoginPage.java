package com.edusoln.pom;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.edusoln.base.CommonMethods;

import freemarker.log.Logger;

public class LoginPage extends CommonMethods {

	@FindBy(name = "username")
	WebElement userid;
	@FindBy(name = "password")
	WebElement password;
	@FindBy(css = "button[type='submit']")
	WebElement login_btn;
	@FindBy(className = "oxd-brand-banner")
	WebElement orangeHRM_logo;
	@FindBy(className="employee-image")
	WebElement profilepic;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void orangeHRMLogin(Map<Object, Object> data,ExtentTest test) {
		waitForClick(userid);
		userid.sendKeys(data.get("User id").toString());
		password.sendKeys(data.get("Password").toString());
		test.log(Status.INFO, "User ID and Password has been enetered");
		login_btn.click();	
		//Assert.assertTrue(orangeHRM_logo.isDisplayed());
		softassert.assertTrue(!orangeHRM_logo.isDisplayed());
		waitForDisplay(profilepic);
		hardwait(1000);
		test.log(Status.INFO, "Login is Sucesfull");			
	
	}

}
