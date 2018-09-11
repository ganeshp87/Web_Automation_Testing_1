package com.automationpractice.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationpractice.base.TestBase;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.MyAccountPage;
import com.automationpractice.pages.UserRegistrationPage;
import com.automationpractice.util.TestUtil;


public class UserRegistrationPageTest extends TestBase {
	LoginPage loginPage;
	UserRegistrationPage userRegistrationPage;
	MyAccountPage myAccountPage;
	String sheetName = "UserRegistration";
	
	public UserRegistrationPageTest(){
		super();
	 }
	//test cases should be seperated -- Independent with each other.
	//before each test case -- launch the broswer and login
	//@test -- execute the test cases
	//after each browser close the broswer.. this is the good practice.
	
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();
		userRegistrationPage = new UserRegistrationPage();
		myAccountPage = new MyAccountPage();
	}
	
	//@Test(priority=1)
	public void verifyLoginPageCreateAccountLinkTest(){
		userRegistrationPage =loginPage.createAccountLink("test1234@gmail.com");
		System.out.println("Logged into Create Account page successfully");
	}
	
	@DataProvider
	public Object[][] getUserRegistrationData(){
		Object data[][] = TestUtil.getTestData("UserRegistration");
		return data;
	}
	
	//Data driven values from Excel sheet for registering multiple users
   /*@Test(priority=2,dataProvider="getUserRegistrationData")
	public void verifyUserRegistrationTest(String Title,String FirstName,String LastName,String Email,String Password,String DateOfBirth,String SignupNewsLetter,String ReceiveOffers,
			String AddFrirstName,String AddLastName,String Company,String Address,String Address2,String City,String State,String ZipCode,String Country,
			String AddInfo,String HomePhone,String MobilePhone,String AddrAlias){
		userRegistrationPage=loginPage.createAccountLink(Email);
		userRegistrationPage.createNewAccount(Title,FirstName, LastName, "",Password ,DateOfBirth ,SignupNewsLetter,ReceiveOffers,
				AddFrirstName, AddLastName,Company,Address,Address2,City,State, ZipCode,Country,AddInfo,HomePhone,MobilePhone,AddrAlias);
		     Assert.assertTrue(myAccountPage.verifyMyAccountLabel());
	}*/
	  
	
	@Test(priority=2)   //single user registration with hardcode value
	public void verifyUserRegistrationTest(){
		userRegistrationPage=loginPage.createAccountLink("gp4@gmail.com");
		userRegistrationPage.createNewAccount("Mrs.","Subi", "Ganesh", "", "password1","22-12-1990" ,"Y","Y",
				"Subi", "Ganesh","Sapient","SRS Plaza","This is address 2","Bangalore", "32", "10009", "21","This is an Automation Practice test.","12345678", "9944321234", "Banaswasdi");
		     Assert.assertTrue(myAccountPage.verifyMyAccountLabel());
	}
	
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
