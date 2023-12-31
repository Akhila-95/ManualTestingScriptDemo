package com.RegUserWith_GcAndCC;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.providio.commonfunctionality.Gc__CC_Paypal;
import com.providio.pageObjects.BundleProductFromEXcel;
import com.providio.pageObjects.GiftCertificate;
import com.providio.pageObjects.SimpleProductFromExcel;
import com.providio.pageObjects.checkOutPage;
import com.providio.paymentProccess.tc__CheckOutProcess;
import com.providio.paymentProccess.tc__CreditCardPaymentProcess;
import com.providio.testcases.baseClass;

public class tc__ShippingAndBillingAddressDiffer__Reg_InGcAndCC extends baseClass{
	int minicartCountValue;
	@Test(dependsOnMethods = {"com.providio.testcases.tc__LoginSc.verifySuccessfulLogin"}, alwaysRun = true)
	public void shippingAndBillingAddressDiffer_Guest() throws InterruptedException {
		
		if(isLoggedIn) {     
		 
			 //count of cart before adding the product
			 Thread.sleep(2000);
			 List<WebElement> minicartcountList = driver.findElements(By.cssSelector(".minicart-quantity"));
			 if(minicartcountList.size()>0) {
				 WebElement minicartcount = driver.findElement(By.cssSelector(".minicart-quantity"));
				 String countOfMinicart = minicartcount.getText();

	         // Check if the string is not empty and contains only digits
	         if (!countOfMinicart.isEmpty() && countOfMinicart.matches("\\d+")) {
	            minicartCountValue = Integer.parseInt(countOfMinicart);
	             System.out.println("The minicart count before adding the product is " + minicartCountValue);    		
	          }
			 }
	            
			 //searching the bundle product from excel sheet
			 BundleProductFromEXcel bundleProduct = new  BundleProductFromEXcel();
			 bundleProduct.performRandomOperations(driver);
			 logger.info("Searched a product");
			 		 
			 //all promtions applied to a simple product
			 SimpleProductFromExcel  simpleProduct = new SimpleProductFromExcel();	
			 simpleProduct.performRandomOperations(driver);
			 test.info("Searched for  simple product");
			 
			 
			// minicart count after adding products
			 if(minicartcountList.size()>0) {
	          WebElement minicartcountafteradding = driver.findElement(By.cssSelector(".minicart-quantity"));
	          String countOfMinicartafteradding = minicartcountafteradding.getText();
	          int minicartCountValueafteradding = Integer.parseInt(countOfMinicartafteradding);

		      logger.info("The minicart count after adding the product is "+minicartCountValueafteradding);

		     //validation for product add to cart
		      test.info("Verifying the product is added to cart or not ");
		
		      if( minicartCountValueafteradding!= minicartCountValue) {
		            test.pass("Product added to cart");
		            logger.info("Product is  added to cart");
		      }else {
		            test.fail("Product is not added to cart");
		            logger.info("Product is not added to cart");
		      }	        
			 }
			   //checkoutProcess	        
	         tc__CheckOutProcess cp1 = new tc__CheckOutProcess();            
	         cp1.checkoutprocess();
	         
	         //changing the billing address
		     List<WebElement> billingAddress = driver.findElements(By.xpath("//label[contains(text(),'Billing Address')]"));
		     if(billingAddress.size()>0) {
		    	 WebElement addNewAddress = driver.findElement(By.xpath("(//button[contains(@class,'btn-add-new')])[3]"));
		    	 addNewAddress.click();
		    	  tc__CheckOutProcess checkoutProcess = new tc__CheckOutProcess();
		    	  checkOutPage cp = new checkOutPage(driver);
		          // Assuming 'cp' is an instance of the 'checkOutPage' class
		          checkoutProcess.selectBillingAddress(cp);
		     }
	       
		     //common checkout process
             tc__CheckOutProcess cp = new tc__CheckOutProcess();        
             cp.checkoutprocess();
             
           //semi gc and cc 
             Gc__CC_Paypal gCandCC = new Gc__CC_Paypal();
			 gCandCC.paymentProccessByGCandCC(driver);
			 
		}else {
		   	 Assert.fail("User not logged in");
		   }
		
	}
}
