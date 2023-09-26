package com.providio.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.providio.pageObjects.homePage;
import com.providio.pageObjects.navigationPage;
import com.providio.pageObjects.productDescriptionPage;
import com.providio.pageObjects.productListingPage;
import com.providio.paymentProccess.tc__CheckOutProcess;
import com.providio.paymentProccess.tc__CreditCardPaymentProcess;
import com.providio.payments.size;

public class tc__By_BuyNow_Button extends baseClass{
	@Test(dependsOnMethods = {"com.providio.testcases.tc__LoginSc.verifySuccessfulLogin"}, alwaysRun = true)
	public void byNowButton() throws InterruptedException {
		if(isLoggedIn) {   
		//step2 1: site url
			driver.get(baseURL);
			Thread.sleep(3000);
			
		
	   //naviagated into random menu
			navigationPage navPage= new navigationPage(driver);
			navPage.selectRandomMenu(driver);
			test.info("Entered into menu");
		
			//plp page	
			productListingPage plp = new productListingPage(driver);
			plp.selectProductRandom(driver);
			logger.info("Entered into plp page");
			WebElement productName = driver.findElement(By.xpath("//h1[contains(@class,'product-name hidden-sm-down')]"));
	        test.info(productName.getText());
	      
			 List<WebElement> buyNowButton = driver.findElements(By.xpath("//button[contains(@class,'buy-now btn btn-primary')]"));
	    	if(buyNowButton.size()>0) {
	    		size s =new size();
	    	    s.clickOnBuyNow(driver);
	    	}
    	}else {
		   	 Assert.fail("User not logged in");
		   }
		 
	}
}
