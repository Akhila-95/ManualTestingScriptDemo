package com.RegUserWith_ViewCart_Paypal;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.providio.commonfunctionality.Gc__CC_Paypal;
import com.providio.commonfunctionality.Mens_Pants_PickUpInStore;
import com.providio.commonfunctionality.findAStore;
import com.providio.commonfunctionality.navigationProccess;
import com.providio.pageObjects.homePage;
import com.providio.pageObjects.navigationPage;
import com.providio.pageObjects.productListingPage;
import com.providio.paymentProccess.tc__CheckOutProcess;
import com.providio.paymentProccess.tc__CheckOutProcessByPayPal;
import com.providio.testcases.baseClass;


public class  tc__PickUpStore_Reg_InVC_Paypal extends baseClass {
	int minicartCountValue;
	@Test(dependsOnMethods = {"com.providio.testcases.tc__LoginSc.verifySuccessfulLogin"}, alwaysRun = true)
	public void paypalFromCheckoutPage() throws InterruptedException {	

		if(isLoggedIn) {     
	   
				// to pick the store
		   	     findAStore  store = new findAStore();
		   	     store.findStore();
   	     
		   	     //selects a random catgory and product add to cart
		          navigationProccess navProccess = new navigationProccess();
		          navProccess.commonNavigationProccess();
		          
	    	   //paypal checkout form view cart page
 	             tc__CheckOutProcessByPayPal paypal= new tc__CheckOutProcessByPayPal();	         
 	             paypal.checkoutprocessFromViewCart();   
				 
	    	 
		}else {
		   	 Assert.fail("User not logged in");
		   }
		}
}
