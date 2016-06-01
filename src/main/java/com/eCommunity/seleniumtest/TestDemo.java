package com.eCommunity.seleniumtest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDemo {
	
	
	 static{
			System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");   	
	 }
	
	public static void main(String[] args) {

	String email="aiiowjw095686@163.com";
	String password="lmm9806";
	
	findnickname(email, password);
		
	}
	
	
	public static  void findnickname(String email,String password){
		  WebDriver	driver = new FirefoxDriver();
		  String   baseUrl = "http://reg.163.com/";

		//  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		  driver.findElement(ByXPath.xpath("//input[@name='email' and @type='text' ]")).clear();
		  driver.findElement(ByXPath.xpath("//input[@name='email' and @type='text' ]")).sendKeys(email);
		  driver.findElement(ByXPath.xpath("//input[@name='password' and @type='password' ]")).clear();
		  driver.findElement(ByXPath.xpath("//input[@name='password' and @type='password' ]")).sendKeys(password);
		  driver.findElement(By.id("dologin")).click();
	//	  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		  driver.navigate().to("https://reg.163.com/account/accountInfo.jsp");
		  
		 WebElement findElement = driver.findElement(By.xpath("html/body/div[3]/div[2]/div[2]/ul[1]/li[2]"));
		 String content=findElement.getText();
		 String[] split = content.split(":");
		 System.out.println(email+","+split[1].trim());	
	}
}
