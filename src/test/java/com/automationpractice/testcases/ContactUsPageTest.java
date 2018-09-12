package com.automationpractice.testcases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationpractice.base.TestBase;
import com.automationpractice.pages.ContactUsPage;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.MyAccountPage;
import com.automationpractice.pages.UserRegistrationPage;
import com.automationpractice.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ContactUsPageTest extends TestBase {
	ContactUsPage contactUsPage;
	LoginPage loginPage;
	ExtentTest logger;
	MyAccountPage myAccountPage;
	
	public ContactUsPageTest(){
		super();
	 }
	//test cases should be seperated -- Independent with each other.
	//before each test case -- launch the broswer and login
	//@test -- execute the test cases
	//after each browser close the broswer.. this is the good practice.
	
	
	@BeforeMethod
	public void setUp(){
		initialization();
		 contactUsPage = new ContactUsPage();
	     loginPage = new LoginPage();
	}
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(getExtentReportPath(), true); // this code is to get extent report path to add screenshot.
	 }
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
    	}
	
	@Test(priority=1)
	public void verifyLoginPageContactUsLabelTest(){
		 extentTest = extent.startTest("verifyLoginPageContactUsLabelTest");
		 contactUsPage.clickContacUsLink();
	     String label = contactUsPage.validateCustomerServiceLabel();
	     Assert.assertEquals(label, "CUSTOMER SERVICE - CONTACT US");
	}
	
	@Test(priority=2)
	public void verifyContactUsSendMessageTest(){
		 extentTest = extent.startTest("verifyContactUsSendMessageTest");
		 myAccountPage= loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		 contactUsPage.clickContacUsLink();
		 contactUsPage.validateSubmitMessage();
		 Assert.assertTrue(contactUsPage.validateSuccessLabel());
	}
	
	@Test(priority=3)
	public void verifyContactUsSendMessageWithoutSignInTest(){
		 extentTest = extent.startTest("verifyContactUsSendMessageWithoutSignInTest");
		 contactUsPage.clickContacUsLink();
		 contactUsPage.validateSubmitMessage();
		 Assert.assertTrue(contactUsPage.validateSuccessLabel());
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, InterruptedException{
		if(result.getStatus()==ITestResult.FAILURE){  //status is 2
			extentTest.log(LogStatus.FAIL,result.getThrowable()); //to add error/exception in extent report
			String screenshotPath = TestUtil.takeScreenshotOnFailure(driver, result.getName());//To take only screenshot of visible page in the screen. 
			//String screenshotPath = TestUtil.takeFullPageScreenShot(driver, result.getName());//To take full page screenshot.
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){   //status is 3
			extentTest.log(LogStatus.SKIP,result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){  //status is 1
			extentTest.log(LogStatus.PASS,result.getName());
		}
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}
	
}
