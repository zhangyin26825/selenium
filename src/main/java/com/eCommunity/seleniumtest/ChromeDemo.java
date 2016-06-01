package com.eCommunity.seleniumtest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeDemo {

	 public static ChromeDriverService service;
	    
	static{
		  System.setProperty(
	                "webdriver.chrome.driver",
	                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		
	}
	
	public static void main(String[] args) {
		  try {
			service = new ChromeDriverService.Builder()
			    .usingDriverExecutable(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe"))
			    .usingAnyFreePort().build();
			  service.start();
			String email="aiiowjw095686@163.com";
			String password="lmm9806";
			
			findnickname(email, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			service.stop();
		}

	}
	
	public static  void findnickname(String email,String password){
		  WebDriver	 driver = new RemoteWebDriver(service.getUrl(),
	                DesiredCapabilities.chrome());
		  String   baseUrl = "http://reg.163.com/";
		  driver.get(baseUrl);
		  //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  //driver.findElement(By.xpath("//input")).clear();
		  
		  driver.switchTo().frame(1);
		  
		  WebElement loginform = driver.findElement(By.id("login-form"));
		  System.out.println();
		  loginform.findElement(By.xpath("//input[@name='email' and @type='text' ]")).sendKeys(email);
		 // driver.findElement(By.xpath("//input[@name='password' and @type='password' ]")).clear();
		  loginform.findElement(By.xpath("//input[@name='password' and @type='password' ]")).sendKeys(password);
		  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		  loginform.findElement(By.id("dologin")).click();
	//	  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.navigate().to("https://reg.163.com/account/accountInfo.jsp");
		  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		  
		 String pageSource = driver.getPageSource();
		 System.out.println(pageSource); 																									
		 WebElement findElement = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/ul[1]/li[2]"));
		 String content=findElement.getText();
		 String[] split = content.split(":");
		 System.out.println(email+","+split[1].trim());	
	}

}
