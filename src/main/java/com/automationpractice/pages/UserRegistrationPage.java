package com.automationpractice.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automationpractice.base.TestBase;

public class UserRegistrationPage extends TestBase {

	@FindBy(name="customer_firstname")
	WebElement myPersonal_firstname;
	
	@FindBy(name="customer_lastname")
	WebElement myPersonal_lasttname;
	
	@FindBy(name="email")
	WebElement myPersonal_email;
	
	@FindBy(name="passwd")
	WebElement myPersonal_passwd;
	
	@FindBy(id="firstname")
	WebElement  address_firstname;
	
	@FindBy(id="lastname")
	WebElement  address_lastname;
	
	@FindBy(id="address1")
	WebElement  address1;
	
	@FindBy(id="city")
	WebElement  city;
	
	@FindBy(xpath="//select[@id='id_state']")
	WebElement state;
	
	@FindBy(id="postcode")
	WebElement  postcode;    
	
	@FindBy(xpath="//select[@id='id_country']")
	WebElement country;
	
	@FindBy(id="phone_mobile")
	WebElement  phone_mobile; 
	
	@FindBy(id="alias")
	WebElement  aliasAddress; 
	
	@FindBy(xpath="//button[@id='submitAccount']")
	WebElement registerBtn;
	
	@FindBy(xpath="//select[@id='days']")
	WebElement days;
	
	@FindBy(xpath="//select[@id='months']")
	WebElement months;
	
	@FindBy(xpath="//select[@id='years']")
	WebElement years;
	
	@FindBy(id="newsletter")
	WebElement  newsletterSignUp;  
	
	@FindBy(name="optin")
	WebElement specialOffers;
	
	@FindBy(xpath="//textarea[@name='other']")
	WebElement additionalInfo;
	
	@FindBy(xpath="//input[@name='address2']")
	WebElement address2;
	
	@FindBy(xpath="//input[@id='phone']")
	WebElement homePhone;
	
	@FindBy(xpath="//input[@name='company']")
	WebElement company;
	
	
	public WebElement selectGenderRadioButton(String value){
		WebElement  select=null;
	          if("Mr.".equalsIgnoreCase(value)){
	        	  select= driver.findElement(By.xpath("//input[@id='id_gender1']"));
	           }else {
	        	   select= driver.findElement(By.xpath("//input[@id='id_gender2']"));
	           }
	          return select;
		 }
	
	public static void selectDateOfBirth(String date){
		  String day,month,year;
	      String[] items = date.split("-");
	      List<String> itemList = new ArrayList<String>(Arrays.asList(items));
	      
	      if(itemList.get(0).startsWith("0"))
		      day=itemList.get(0).substring(1); //removing zero in front of string.
		      else  day=itemList.get(0);
	         
		      if(itemList.get(1).startsWith("0"))
		      month=itemList.get(1).substring(1);
		      else  month=itemList.get(1);
		      
		      year=itemList.get(2);
	      
	      Select da = new Select(driver.findElement(By.xpath("//select[@id='days']")));
		  da.selectByValue(day); 
	     
	      Select mon= new Select(driver.findElement(By.xpath("//select[@id='months']")));
	      mon.selectByValue(month); 
	    
	      Select yer= new Select(driver.findElement(By.xpath("//select[@id='years']")));
	      yer.selectByValue(year); 
		
	}
	
	public void createNewAccount(String gender,String p_fstNam,String p_lstNam, String p_email,
			 String p_pass,String date,String newslett,String offers,String add_fstNam,String add_lstName,String comp,String add1,String add2, String ci,
			 String sta, String postCod,String countr,String additInfo,String homePho,String phone_mob,String aliasAdd){
		WebElement radioGender=selectGenderRadioButton(gender);
		radioGender.click();
		myPersonal_firstname.sendKeys(p_fstNam);
		myPersonal_lasttname.sendKeys(p_lstNam);
		myPersonal_email.sendKeys(p_email);
		myPersonal_passwd.sendKeys(p_pass);
		selectDateOfBirth(date);
		if(newslett.equalsIgnoreCase("Y"))
		   newsletterSignUp.click();
		if(offers.equalsIgnoreCase("Y"))
	       specialOffers.click();
		additionalInfo.click();
		address_firstname.sendKeys(add_lstName);
		address_lastname.sendKeys(add_lstName);
		company.sendKeys(comp);
		address1.sendKeys(add1);
		address2.sendKeys(add2);
		city.sendKeys(ci);
		Select selectState = new Select (state);
		selectState.selectByValue(sta); //32 New York
		postcode.sendKeys(postCod);
		Select selectCountry = new Select (country);
		selectCountry.selectByValue(countr); //21 United States
		additionalInfo.sendKeys(additInfo);
		homePhone.sendKeys(homePho);
		phone_mobile.sendKeys(phone_mob);
		aliasAddress.clear();
		aliasAddress.sendKeys(aliasAdd);
		WebElement element = registerBtn;
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}
	
	
	
	
	//Initializing the Page Objects in constructors:
		public UserRegistrationPage(){
			PageFactory.initElements(driver, this);
		}
		
	
	
	
	
	
	
}
