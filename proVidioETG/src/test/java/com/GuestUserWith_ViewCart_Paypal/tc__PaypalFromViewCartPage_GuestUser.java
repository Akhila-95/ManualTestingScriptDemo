package com.GuestUserWith_ViewCart_Paypal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.providio.commonfunctionality.navigationProccess;
import com.providio.paymentProccess.tc__CheckOutProcess;
import com.providio.paymentProccess.tc__CheckOutProcessByPayPal;
import com.providio.payments.size;
import com.providio.testcases.baseClass;

public class tc__PaypalFromViewCartPage_GuestUser extends baseClass {
	int minicartCountValue;
	@Test
	public void paypalFromViewCartPage() throws InterruptedException {
		
		 //entered into url
			  driver.get("https://zzqi-002.dx.commercecloud.salesforce.com/s/RefArch/electronics/ipod%20%26%20mp3%20players/apple-ipod-shuffleM.html?lang=en_US");
			  logger.info("Entered into url");
			  
		  // selects a random catgory and product add to cart
	          //navigationProccess navProccess = new navigationProccess();
	         // navProccess.commonNavigationProccess();
			  
			  List<WebElement> pdpPage = driver.findElements(By.xpath("//button[contains(@class,'add-to-cart btn btn-primary')]"));
		   		 if( pdpPage.size()>0) {
				          size s = new size();			        
				          s.selectSize(driver);
			    		 }
	          
	     
    	 //paypal checkout form view cart page
 	          tc__CheckOutProcessByPayPal paypal= new tc__CheckOutProcessByPayPal();	         
 	          paypal.checkoutprocessFromViewCart();
   
		        }

}
