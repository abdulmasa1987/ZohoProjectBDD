package zoho.managers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class WebDriverManager {
	
	WebDriver driver;
	ExtentTest test;
	Properties prop;
	SoftAssert softAssert;
	
	public WebDriverManager()
	{
		try
		{
		prop=new Properties();
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
		FileInputStream fs = new FileInputStream(path);
		prop.load(fs);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		softAssert =new SoftAssert();
	}
		
 
	/*
	public void openBrowser(String browser)
	{
		if(getProperty("grid_run").equals("Y"))
		//run on grid
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			if(browser.equals("Mozilla")){
				
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(browser.equals("Chrome")){
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				// hit the hub
				driver = new RemoteWebDriver(new URL("http://localhost:4444b"), cap);
			} catch (Exception e) {
			  e.printStackTrace();
			}
		}
		else {
		log("Opening Browser "+browser);
		if(browser.equals("Mozilla"))
			driver=new FirefoxDriver();
		else if(browser.equals("Chrome"))
			driver=new ChromeDriver();
		else if(browser.equals("Edge"))
			driver=new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90,TimeUnit.SECONDS);
	}
	
	*/
	
	
	public void openBrowser(String browser) {
		log("Opening The Browser "+ browser);
		if(getProperty("grid_run").equals("Y"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			if(browser.equals("Mozilla")){
				
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(browser.equals("Chrome")){
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				// hit the hub
				driver = new RemoteWebDriver(new URL("http://localhost:4444"), cap);
			} catch (Exception e) {
			  e.printStackTrace();
			}
		}else {  //local machine
			if(browser.equals("Chrome"))
				driver=new ChromeDriver();
			else if(browser.equals("Mozilla"))
				driver = new FirefoxDriver();
			else if(browser.equals("Edge"))
				driver = new EdgeDriver();
			
		}
		// implicit wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
    }
    
	public void navigate(String urlkey)
	{
		log("Navigating to "+urlkey);
		driver.get(prop.getProperty(urlkey));
	}
	
	
	public void click(String locatorkey) //assuming locator is xpath
	{
		log("Clicking on to "+locatorkey);
		findElement(locatorkey).click();
	}
	
	
	
	public void type(String locatorkey,String data) //assuming locator is xpath
	{
		log("Typing in "+locatorkey);
		findElement(locatorkey).sendKeys(data);;
	}
	
	
	
	public WebElement findElement(String locatorkey)
	{
		By locator=getlocator(locatorkey);
		System.out.println("sss"+locator);
		WebElement e=null;
		try {
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			e=driver.findElement(locator);
		}catch(Exception ex)
		{
			//report failure
			logFailure("Object not found "+locatorkey,true);
		}
		
		return e;
		
	}
	
	public By getlocator(String locatorkey)
	{
		if(locatorkey.endsWith("_id"))
		   return By.id(getProperty(locatorkey));
		else if(locatorkey.endsWith("_name"))
			return By.name(getProperty(locatorkey));
		else if(locatorkey.endsWith("_xp"))
			return By.xpath(getProperty(locatorkey));
		else return By.cssSelector(getProperty(locatorkey));	
	}

	/***********Validation Functions**********************/
	public boolean verifyTitle(String expectedTitleKey)
	{
		String expectedTitle=getProperty(expectedTitleKey);
		String actualTitle=driver.getTitle();
		if(expectedTitle.equals(actualTitle))
			return true;
		else return false;
	}
	
	
	/*********Utility Funcations**************/
	
	
	public String getProperty(String key)
	 {
		   return prop.getProperty(key);
	   
	 }
		
	public void init(ExtentTest test)
	 {
			this.test=test;
	  }
	
	public void log(String msg)
	{
		System.out.println(msg);
		test.log(Status.INFO, msg);
	}
	
	public void logFailure(String msg,boolean stopExecution)
	{
	  System.out.println("FAILURE--"+msg);
	  //take screenshot and embed in reports
	  //fail in extent report
	  test.log(Status.FAIL,msg);
	  //fail in cucumber-testng
	  //Assert.fail(msg); //Hard assertion
	  softAssert.fail(msg);
	  
	  if(stopExecution)
		  softAssert.assertAll();
	}



	public void quit() {
		
		if(driver!=null)
			driver.quit();
		if(softAssert!=null)
			softAssert.assertAll();
		
	}



	public boolean isElementPresent(String locatorkey) {
	  
	By locator=getlocator(locatorkey);
	try {
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}catch(Exception ex)
		{
			return false;
		}
	
		return true;
	}
	
	public int getLeadRowNumberWithCellData(String leadName)
	{
		List<WebElement> leadnames=driver.findElements(getlocator("leadnames_xp"));
		for(int i=0;i<leadnames.size();i++)
		{
			if(leadName.equalsIgnoreCase(leadnames.get(i).getText()))
					return i+1;
		}
		return -1;
	}



	public void selectLeadCheckbox(int rowNum) {

		driver.findElement(By.xpath("(//span[@data-zcqa='selectEntity'])["+rowNum+"]")).click();
	}
	
}
