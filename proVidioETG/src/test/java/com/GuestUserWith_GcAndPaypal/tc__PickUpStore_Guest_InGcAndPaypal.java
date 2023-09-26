package com.GuestUserWith_GcAndPaypal;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.providio.commonfunctionality.Gc__CC_Paypal;
import com.providio.commonfunctionality.Mens_Pants_PickUpInStore;
import com.providio.commonfunctionality.findAStore;
import com.providio.commonfunctionality.navigationProccess;
import com.providio.pageObjects.homePage;
import com.providio.paymentProccess.tc__CheckOutProcess;
import com.providio.paymentProccess.tc__CreditCardPaymentProcess;
import com.providio.testcases.baseClass;


public class  tc__PickUpStore_Guest_InGcAndPaypal extends baseClass {
	int minicartCountValue;
	@Test//(dependsOnMethods = {"com.providio.testcases.tc__LoginSc.verifySuccessfulLogin"}, alwaysRun = true)
	public void paypalFromCheckoutPage() throws InterruptedException {	

				driver.get(baseURL);
				test.info("Entered into Browser");
				
				// to pick the store
	    	     findAStore  store = new findAStore();
	    	     store.findStore();
	    	     
	    	     //selects a random catgory and product add to cart
		          navigationProccess navProccess = new navigationProccess();
		          navProccess.commonNavigationProccess();

		       //common checkoutProcess	         
				 tc__CheckOutProcess cp = new tc__CheckOutProcess();         
				 cp.checkoutprocess();
				
			   //gc and paypal
				  Gc__CC_Paypal gcAndPaypal = new Gc__CC_Paypal();
				  gcAndPaypal.paymentProccessByGCandPaypal();
	
	    	 
		}
}
