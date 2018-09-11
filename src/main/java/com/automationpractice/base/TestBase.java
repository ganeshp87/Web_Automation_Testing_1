package com.automationpractice.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.automationpractice.util.TestUtil;
import com.automationpractice.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
   public static WebDriver driver;
   public static Properties prop;
   public  static EventFiringWebDriver e_driver;
   public static WebEventListener eventListener;
  public static ExtentReports extent;
  public static ExtentTest extentTest;
	//constructor
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/automationpractice"
					+ "/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getExtentReportPath(){
		String path= System.getProperty("user.dir")+"/test-output/Extent.html";
		return path;
	}
	
	public static void initialization(){
		
		String browserName = prop.getProperty("browser");
		String gecko_path = prop.getProperty("gecko_path");
		String chrome_path= prop.getProperty("chrome_path");
		String ie_path= prop.getProperty("ie_path");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", chrome_path);	
			driver = new ChromeDriver(); 
		}else if(browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver",gecko_path);	
			driver = new FirefoxDriver(); 
		}else if(browserName.equals("ie")){
			System.setProperty("webdriver.ie.driver",ie_path);	
			driver = new InternetExplorerDriver(); 
	    }

		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
}


