package com.automationpractice.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.base.TestBase;

public class LoginPage extends TestBase {

	
	//Page Factory  or  OR-Object Repository
	
		@FindBy(xpath="//a[@class='login']")
		WebElement signInlink;
		
		@FindBy(id="email")
		WebElement email;
		
		@FindBy(id="passwd")
		WebElement password;
		
		@FindBy(name="SubmitLogin")
		WebElement submitLoginBtn;
		
		@FindBy(xpath="//img[@class='logo img-responsive']")
		WebElement logoLoginPage;
		
		@FindBy(xpath="//input[@id='email_create']")
		WebElement emailCreate;
		
		@FindBy(xpath="//button[@id='SubmitCreate']")
		WebElement submitCreate;
		
		@FindBy(xpath="//h3[contains(text(),'Your personal information')]")
		WebElement createAccountLabel;
		
		
		
		
		
		//Initializing the Page Objects in constructors:
			public LoginPage(){
				PageFactory.initElements(driver, this);
			}
		
			
			//Actions methods
				
			
   public String validateLoginPageTitle(){
	return driver.getTitle();
	}
    
    public boolean validateLogo(){
    	return logoLoginPage.isDisplayed();
    }
	
    public boolean validateCreateAccountLabel(){
    	return createAccountLabel.isDisplayed();
    }
    
	public MyAccountPage login(String em, String pss){
		signInlink.click();
		email.sendKeys(em);
		password.sendKeys(pss);
		submitLoginBtn.click();
		return new MyAccountPage();
	}
	
	public UserRegistrationPage createAccountLink(String em){
		signInlink.click();
		emailCreate.sendKeys(em);
		
		 WebElement element = submitCreate;
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		
		System.out.println("Inside Create account  Page " + driver.getTitle());
		boolean flag = validateCreateAccountLabel();
		System.out.println("label validation " + flag);
		return new UserRegistrationPage();
		
		
	}
	
}
