package com.providio.commonfunctionality;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.providio.pageObjects.homePage;
import com.providio.testcases.baseClass;

public class findAStore extends baseClass {

	public void findStore() throws InterruptedException {

   	 List<WebElement> findAStore = driver.findElements(By.xpath("//span[@class='header-store-name']"));
   	 if(findAStore.size()>0) {
   	
   		 test.info("Bopis will available Stripe and Cybersource payment");
   		 homePage hp = new homePage(driver);
   		 hp.findastore(driver);
   		 test.info("Clicked on Find a store");
   		 
   		 hp.findStore(driver);
   		 JavascriptExecutor js = (JavascriptExecutor) driver;
   	     js.executeScript("window.scrollBy(0,500)", "");
   	     
   	     hp.selectStore(driver);
   	     Thread.sleep(1000);
   	 }
	}
}
