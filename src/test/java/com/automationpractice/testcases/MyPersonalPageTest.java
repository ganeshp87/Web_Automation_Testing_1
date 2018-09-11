package com.automationpractice.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automationpractice.base.TestBase;
import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.MyAccountPage;
import com.automationpractice.pages.MyAddressPage;
import com.automationpractice.pages.MyCreditSlipsPage;
import com.automationpractice.pages.MyPersonalPage;
import com.automationpractice.pages.MyWishListPage;
import com.automationpractice.pages.OrderHistoryPage;

public class MyPersonalPageTest extends TestBase {
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	HomePage homePage;
	MyPersonalPage myPersonalPage;
	
	public MyPersonalPageTest(){
		super();
	 }
	//test cases should be seperated -- Independent with each other.
	//before each test case -- launch the broswer and login
	//@test -- execute the test cases
	//after each browser close the broswer.. this is the good practice.
	
	
	@BeforeMethod
	public void setUp(){
		initialization();
		homePage=new HomePage();
		loginPage = new LoginPage();
		myPersonalPage = new MyPersonalPage();
		myAccountPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		myAccountPage.clickMyPersonalPageLink();
	}
	
	@Test(priority=1)
	public void verifyMyPersonalPageTitleTest(){
		String title=myPersonalPage.validateMyPersonalPageTitle();
		Assert.assertEquals(title,"Identity - My Store","Title is not matched"); //The comment will execute only with failed test cases.
	}
	
	@Test(priority=2)
	public void verifyMyPersonalLabelTest(){
		boolean flag=myPersonalPage.verifyMyPersonalPageLabel();
		Assert.assertTrue(flag);
		
	}
	
	@Test(priority=3)
	public void verifyBackToHomeLinkTest(){
		homePage= myPersonalPage.clickBackToHome(); //Its return homepage objects
		System.out.println("Successfully Reached BackToHome Page");
		
	}
	
	@Test(priority=4)
	public void verifyBackToAccountLinkTest(){
		myAccountPage= myPersonalPage.clickBackToMyAccountLink();  //Its returning myAccountPage objects
		boolean flag= myAccountPage.verifyMyAccountLabel();
		Assert.assertTrue(flag);
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
