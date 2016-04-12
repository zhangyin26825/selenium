package com.eCommunity.seleniumtest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CellFileSearch {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  
  @BeforeClass(alwaysRun = true)
  @Parameters({"url"})
  public void setUp(String url) throws Exception {
      driver = ChromDriver.driver;
      baseUrl = url;
      driver.navigate().to(url+"/index.jsp");
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void testCellFileSearch() throws Exception {
    //driver.get(baseUrl + "/ecommunity/index.jsp");
 
    driver.findElement(By.linkText("小区档案")).click();
    WebElement frame=driver.findElement(By.tagName("iframe"));
    driver.switchTo().frame(frame);
    new Select(driver.findElement(By.id("provinceCode"))).selectByVisibleText("广东省");
    driver.findElement(By.cssSelector("option[value=\"440000\"]")).click();
    new Select(driver.findElement(By.id("cityCode"))).selectByVisibleText("深圳市");
    driver.findElement(By.xpath("//button[@type='button']")).click();
    WebElement findElement = driver.findElement(By.xpath(".//*[@id='aazone.dataListZone']/table/tbody/tr[1]/td[1]"));
    assert findElement!=null;
    System.out.println("小区档案搜索结果不为空");
  }

  @AfterClass(alwaysRun = false)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
