package com.GuestUserWith_Minicart_Paypal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.providio.commonfunctionality.navigationProccess;
import com.providio.pageObjects.navigationPage;
import com.providio.pageObjects.productListingPage;
import com.providio.paymentProccess.tc__CheckOutProcess;
import com.providio.paymentProccess.tc__CheckOutProcessByPayPal;
import com.providio.payments.size;
import com.providio.testcases.baseClass;

public class tc__PaypalCheckout_MiniCart_GuestUser extends baseClass {
	int  minicartCountValue=0;
@Test
	public void paypalCheckout_MiniCart() throws InterruptedException {
		
			     driver.get("https://zzqi-002.dx.commercecloud.salesforce.com/s/RefArch/electronics/ipod%20%26%20mp3%20players/apple-ipod-shuffleM.html?lang=en_US");
		         logger.info("enterd into url");
		       
		     //selects a random catgory and product add to cart
		         //navigationProccess navProccess = new navigationProccess();
		      //   navProccess.commonNavigationProccess();		    
		     List<WebElement> pdpPage = driver.findElements(By.xpath("//button[contains(@class,'add-to-cart btn btn-primary')]"));
		   		 if( pdpPage.size()>0) {
			          size s = new size();			        
			          s.selectSize(driver);
		    		 }   
		         
		         
	         //checkout from mini cart by paypal	        
		        tc__CheckOutProcessByPayPal checkOutProcessByPayPal= new tc__CheckOutProcessByPayPal();
		        checkOutProcessByPayPal. checkoutprocessFromMiniCart();
	}

}
