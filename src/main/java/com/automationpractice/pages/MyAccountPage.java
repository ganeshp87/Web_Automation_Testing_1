package com.automationpractice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.base.TestBase;

public class MyAccountPage extends TestBase {

	@FindBy(xpath="//span[contains(text(),'Ganesh Palanisamy')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//h1[contains(text(),'My account')]")
	WebElement myAccountLabel;
	
	@FindBy(xpath="//a[@title='Home']")
	WebElement homeBackBtn;
	
	@FindBy(xpath="//a[@title='Orders']")
	WebElement orderHistoryPageLink;
	
	@FindBy(xpath="//a[@title='Credit slips']")
	WebElement myCreditSlipsPageLink;

	@FindBy(xpath="//a[@title='Addresses']")
	WebElement myAddressPageLink;
	
	@FindBy(xpath="//a[@title='Information']")
	WebElement myPersonalPageLink;
	
	@FindBy(xpath="//a[@title='My wishlists']")
	WebElement myWishListPageLink;
	
	
	//Initializing the Page Objects in constructors:
	public MyAccountPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifyUserNameLabel(){
		
		return userNameLabel.isDisplayed();
	}
	
   public boolean verifyMyAccountLabel(){
		
		return myAccountLabel.isDisplayed();
	}
	
   public HomePage clickBackToHome(){
	    homeBackBtn.click();
		return new HomePage();
   }
   
   public OrderHistoryPage clickOrderHistoryLink(){
	   orderHistoryPageLink.click();
	   return new OrderHistoryPage();
   }
	
   public MyCreditSlipsPage clickMyCreditSlipsPageLink(){
	   myCreditSlipsPageLink.click();
	   return new MyCreditSlipsPage();
   }
   
   public MyAddressPage clickMyAddressPageLink(){
	   myAddressPageLink.click();
	   return new MyAddressPage();
   }
   
   public MyPersonalPage clickMyPersonalPageLink(){
	   myPersonalPageLink.click();
	   return new MyPersonalPage();
   }
   
   public MyWishListPage clickMyWishListPageLink(){
	   myWishListPageLink.click();
	   return new MyWishListPage();
   }
   
}
