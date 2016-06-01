package com.eCommunity.seleniumtest;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class ChromDriver {
    
   
   public static ChromeDriverService service;
   
   public static WebDriver driver;
    
    @BeforeSuite
    @Parameters({"url","seconds"})
    public  void init(String url,Integer seconds) throws Exception{
    	
    	
    	
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        service = new ChromeDriverService.Builder()
        .usingDriverExecutable(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe"))
        .usingAnyFreePort().build();
        service.start();
        // 创建一个 Chrome 的浏览器实例
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
        driver.get(url+"/login.jsp");
        driver.findElement(By.id("loginName")).sendKeys("wanke");
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys("123456");
        driver.findElement(By.id("kaptcha")).clear();
        Thread.sleep(seconds*1000); 
        driver.findElement(By.id("btnSubmit")).click();
        Thread.sleep(3*1000); 
       // driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    } 
    
    @AfterSuite()
    public void exit(){
            try {
                driver.close();
                driver.quit();
                service.stop();
            }catch(Exception e){
                
            }
            finally{
                service.stop();
            }
            
    }
}
