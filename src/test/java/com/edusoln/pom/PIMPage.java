package com.edusoln.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.edusoln.base.CommonMethods;

public class PIMPage extends CommonMethods{
	
	@FindBy(className = "oxd-brand-banner")
	WebElement orangeHRM_logo;
	@FindBy(css = "span[class='oxd-text oxd-text--span oxd-main-menu-item--name']")
	List<WebElement> PIM_btn;
	@FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
	WebElement add_btn;
	@FindBy(name = "firstName")
	WebElement firstname;
	@FindBy(name = "middleName")
	WebElement middlename;
	@FindBy(name = "lastName")
	WebElement lastname;
	@FindBy(css = "input[class='oxd-input oxd-input--active']")
	List<WebElement> emp_id;
	@FindBy(css = "i[class='oxd-icon bi-plus']")
	WebElement pic_upload;
	@FindBy(css = "button[type='submit']")
	WebElement submit_btn;
	@FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding ')]/h6")
	WebElement employe_title;
	
	
	public PIMPage() {
		PageFactory.initElements(driver, this);
	}
	public void addemployee(Map<Object,Object> data, ExtentTest test) {
		if(stringValue(data,"AddEmployee").equals("Yes")) {
		PIM_btn.get(1).click();
		hardwait(2000);
		getScreenshot("pim");		
		add_btn.click();
		firstname.sendKeys(stringValue(data, "FirstName"));
		middlename.sendKeys(stringValue(data, "MiddleName"));
		lastname.sendKeys(stringValue(data, "LastName"));	
		emp_id.get(1).clear();
		
		emp_id.get(1).sendKeys(stringValue(data, "EmpiID"));			
		waitForClick(pic_upload);
		pic_upload.click();
		StringSelection strSelection = new StringSelection(homepath+"\\src\\test\\resources\\Screenshot\\13-11-2022 01\\LoginSucess.png");
		   Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		   clipboard.setContents(strSelection, null);	
		   Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   robot.delay(3000);
		   robot.keyPress(KeyEvent.VK_CONTROL);
		   robot.keyPress(KeyEvent.VK_V);
		   robot.keyRelease(KeyEvent.VK_V);
		   robot.keyRelease(KeyEvent.VK_CONTROL);
		   robot.delay(1000);
		   robot.keyPress(KeyEvent.VK_ENTER);
		   robot.keyRelease(KeyEvent.VK_ENTER);
		   hardwait(3000);		
			test.log(Status.INFO, "Profile pic has been uploaded");
		submit_btn.click();
		waitForDisplay(employe_title);
		
		//Assert.assertTrue(employe_title.getText().equalsIgnoreCase("Personal details"));		
		softassert.assertEquals(employe_title.getText(), "Personal Details");
		System.out.println("ran after soft assertion");
		test.log(Status.PASS, "Employee has been added sucesfully");
		
	}
	}
}
