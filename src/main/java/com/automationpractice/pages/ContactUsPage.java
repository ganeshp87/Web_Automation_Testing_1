package com.automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automationpractice.base.TestBase;

public class ContactUsPage extends TestBase  {
	
	
	@FindBy(xpath="//select[@id='id_contact']")
	WebElement subjectHeading;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement email;
	
	@FindBy(xpath="//select[@name='id_order']")
	WebElement orderNumber;
	
	@FindBy(xpath="//input[@name='id_order']")
	WebElement orderNumberInputText;
	
	@FindBy(xpath="//input[@id='fileUpload']")
	WebElement fileUpload;
	
	@FindBy(xpath="//textarea[@id='message']")
	WebElement message;

	@FindBy(xpath="//button[@id='submitMessage']")
	WebElement submitMessage;
	
	@FindBy(xpath="//*[@id='center_column']/h1[@class='page-heading bottom-indent']")
	WebElement customerServiceLabel;
	
	@FindBy(xpath="//a[@title='Contact Us']")
	WebElement contactUsHomeLink;
	
	@FindBy(xpath="//*[@id='center_column']/p[@class='alert alert-success']")
	WebElement successMessage;
	
	@FindBy(xpath="//a[contains(text(),'Sign in')]")
	WebElement signInText;
	
	@FindBy(xpath="//select[@name='id_product']")
	WebElement productDropDown;
	

	//Initializing the Page Objects in constructors:
		public ContactUsPage(){
			PageFactory.initElements(driver, this);
		}
	
            public ContactUsPage clickContacUsLink(){
            	contactUsHomeLink.click();
			 return new ContactUsPage();
			}   
		
		 public String validateCustomerServiceLabel(){
			 
			 return customerServiceLabel.getText();
			}
			
		public boolean isSginInText(){
			boolean flag;
			int count = driver.findElements(By.xpath("//a[contains(text(),'Sign in')]")).size();
					if(count==0){
						flag=false;
					}else{
						flag=true;
					}
			return flag;
			
		} 
		 public ContactUsPage validateSubmitMessage(){
			 subjectHeading.click();
			 Select select = new Select(subjectHeading);
			 select.selectByValue("2");
			 email.clear();
			 email.sendKeys("p.ganesh87@gmail.com");
			  if(isSginInText()){
				  orderNumberInputText.sendKeys("123456");
			 }else{
				 orderNumber.click();
			   Select select1 = new Select(orderNumber);
			   select1.selectByValue("61514");
			   productDropDown.click();
			   Select select2 = new Select(productDropDown);
			   select2.selectByValue("7");
			 }
			 
			 fileUpload.sendKeys("C:\\Users\\Ganesh\\Documents\\Sikuli documents\\TestUpload.docx");
			 message.sendKeys("Hello this is a test message");
			 WebElement element = submitMessage;
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			 return new ContactUsPage();
			}
		
		
      public boolean validateSuccessLabel(){
			 
			 return successMessage.isDisplayed();
			}

}
