package com.automationpractice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.base.TestBase;

public class MyPersonalPage extends TestBase {

	@FindBy(xpath="//h1[contains(text(),'Your personal information')]")
	WebElement myPersonalPageLabel;
	
	@FindBy(xpath="//*[@id='center_column']/ul/li[1]/a")
	WebElement backToAccount;
	
	@FindBy(xpath="//*[@id='center_column']/ul/li[2]/a")
	WebElement backToHome;
	
	//Initializing the Page Objects in constructors:
		public MyPersonalPage(){
			PageFactory.initElements(driver, this);
		}
		
		 public String validateMyPersonalPageTitle(){
				return driver.getTitle();
				}
			    
		 public boolean verifyMyPersonalPageLabel(){
				
				return myPersonalPageLabel.isDisplayed();
			}
	     
		 public HomePage clickBackToHome(){
		    	 backToHome.click();
				return new HomePage();
		   }
		   
		   public MyAccountPage clickBackToMyAccountLink(){
			   backToAccount.click();
			   return new MyAccountPage();
		   }
	
	
}
