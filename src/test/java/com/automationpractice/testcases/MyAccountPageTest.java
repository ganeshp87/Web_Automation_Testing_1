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

public class MyAccountPageTest extends TestBase {
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	HomePage homePage;
	OrderHistoryPage orderHistoryPage;
	MyCreditSlipsPage myCreditSlipPage;
	MyAddressPage myAddressPage;
	MyPersonalPage myPersonalPage;
	MyWishListPage myWishListPage;
	public MyAccountPageTest(){
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
		orderHistoryPage= new OrderHistoryPage();
		myCreditSlipPage= new MyCreditSlipsPage();
		myAddressPage = new MyAddressPage();
		myPersonalPage = new MyPersonalPage();
		myWishListPage = new MyWishListPage();
		myAccountPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyUserLabelTest(){
		boolean flag=myAccountPage.verifyUserNameLabel();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=2)
	public void verifyAccountLabelTest(){
		boolean flag=myAccountPage.verifyMyAccountLabel();
		Assert.assertTrue(flag);
		
	}
	
	@Test(priority=3)
	public void verifyBackToHomeLinkTest(){
		homePage= myAccountPage.clickBackToHome();   //Its return homepage objects
		
	}
	
	@Test(priority=4)
	public void verifyOrderHistoryLinkTest(){
	  orderHistoryPage= myAccountPage.clickOrderHistoryLink();  //Its returning orderhistory page objects
		
	}
	
	@Test(priority=5)
	public void verifyCreditSlipsLinkTest(){
		myCreditSlipPage=myAccountPage.clickMyCreditSlipsPageLink();
	}
	
	@Test(priority=6)
	public void verifyMyAddressPageLinkTest(){
		myAddressPage=myAccountPage.clickMyAddressPageLink();
	}
	
	@Test(priority=7)
	public void verifyMyPersonalPageLinkTest(){
		myPersonalPage = myAccountPage.clickMyPersonalPageLink();
		
	}
	
	@Test(priority=8) 
	public void verifyMyWishListPageLinkTest(){
		myWishListPage=myAccountPage.clickMyWishListPageLink();
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
