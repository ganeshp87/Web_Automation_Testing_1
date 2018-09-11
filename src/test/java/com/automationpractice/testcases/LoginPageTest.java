package com.automationpractice.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.ITestResult;

import com.automationpractice.base.TestBase;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.MyAccountPage;
import com.automationpractice.pages.UserRegistrationPage;
import com.automationpractice.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	ExtentTest logger;
	MyAccountPage myAccountPage;
	UserRegistrationPage userRegistrationPage;
	
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(getExtentReportPath(), true); // this code is to get extent report path to add screenshot.
	 }
	
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();
		userRegistrationPage = new UserRegistrationPage();
	}
	
	
	
	
	//@Test(priority=1)
	public void signInTest(){
	 extentTest = extent.startTest("signInTest");
	 myAccountPage= loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	 System.out.println("Successfully Logged In");
	 String title = loginPage.validateLoginPageTitle();
	 Assert.assertEquals(title, "My account - My Store");
	}
	
	@Test(priority=2)
	public void loginPageLogoTest(){
		extentTest = extent.startTest("loginPageLogoTest");
		boolean flag=loginPage.validateLogo();
		Assert.assertFalse(flag);
	}
	

	//@Test(priority=3)
	
	public void verifyCreateAccountLinkTest(){
		extentTest = extent.startTest("verifyCreateAccountLinkTest");
		userRegistrationPage =loginPage.createAccountLink("test1234567@gmail.com");
		System.out.println("Logged into Create Account page successfully");
	}
	
	
	
	
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
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
