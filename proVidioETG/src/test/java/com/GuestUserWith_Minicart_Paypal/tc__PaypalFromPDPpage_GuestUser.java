package com.GuestUserWith_Minicart_Paypal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.providio.pageObjects.bundleProductAddAllToCart;
import com.providio.pageObjects.navigationPage;
import com.providio.pageObjects.productListingPage;
import com.providio.paymentProccess.tc__CheckOutProcessByPayPal;
import com.providio.payments.size;
import com.providio.testcases.baseClass;

public class tc__PaypalFromPDPpage_GuestUser  extends baseClass{
	int minicartCountValue;
	@Test
	public void paypalFromPDPpage() throws InterruptedException {
		
		
		//enter  into url
			driver.get(baseURL);
			test.info("Entered into url");
			logger.info("Enterd into url");
			
			// to navigate to pdp 
			navigationToPdp() ;
		
	   	  List<WebElement> bundleProduct = driver.findElements(By.cssSelector(".bundle-item"));
	   	     if(bundleProduct.size()>0){   	    	
	   	    	navigationToPdp() ;
	   	     }   		
	  //paypal buy now button
	   	     List<WebElement> pdpPage = driver.findElements(By.xpath("//button[contains(@class,'add-to-cart btn btn-primary')]"));
	   	     if(pdpPage.size()>0) {
	    		 List<WebElement> paypalbuyNowPdp =driver.findElements(By.xpath("//div[contains(@class,'salesforce-buynow-element ')]"));
	    		 if( paypalbuyNowPdp.size()>0) {
	    			 
	    			    size s = new size();	    			    
	    				s.paypalBuyNowFromPDP(driver);
	    				

    				//paypal checkout procces
    					tc__CheckOutProcessByPayPal paypal= new tc__CheckOutProcessByPayPal();
    			        Thread.sleep(5000);
    			        paypal.paypalCheckoutFromPDP();
		    		 }
	    		 else {
	    			 test.pass("Paypal buy now is only in salsforce payment not in cybersource and brain tree");
	    		 }		
	   	     }

		
	}
	public void navigationToPdp() throws InterruptedException {
		//naviagated into random menu
		navigationPage navPage= new navigationPage(driver);
		navPage.selectRandomMenu(driver);
		logger.info("Entered into menu");
		// to know the category entered 
    	 List<WebElement> pageTitle = driver.findElements(By.cssSelector("h1.page-title"));
    	 if(pageTitle.size()>0)
    	 {
    		 WebElement pageTitle1 = driver.findElement(By.cssSelector("h1.page-title"));
    		 test.info("Entered into random menu " + pageTitle1.getText());
    		 Thread.sleep(2000);
    	 }
	
	//plp page	
		productListingPage plp = new productListingPage(driver);
		plp.selectProductRandom(driver);
		logger.info("Entered into plp page");
		List<WebElement> productName1 = driver.findElements(By.xpath("//h1[@class='product-name hidden-sm-down']"));
   	     if(productName1.size()>0) {
   	    	 WebElement productName = driver.findElement(By.xpath("//h1[@class='product-name hidden-sm-down']"));
   	    	 test.info("productName is " +productName.getText());
   	     }
	}
}
