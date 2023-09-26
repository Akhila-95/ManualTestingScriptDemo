package com.providio.commonfunctionality;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.providio.Validations.Checkout_Validation;
import com.providio.paymentProccess.tc__CheckOutProcessByPayPal;
import com.providio.paymentProccess.tc__CreditCardPaymentProcess;
import com.providio.paymentProccess.tc__PaymentProccessByGC;
import com.providio.paymentProccess.tc__PaymentProccessByGC_CC_Paypal;
import com.providio.testcases.baseClass;

public class Gc__CC_Paypal extends baseClass{

	public void paymentProccessByGCandCC(WebDriver driver) throws InterruptedException {
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0, 500);");        
		
		 //checking whether gift certificate div is available or not
		 List<WebElement> giftCertificateDiv= driver.findElements(By.cssSelector(".payment-form-fields"));
		 List<WebElement> redeemGiftCertificate= driver.findElements(By.xpath("//label[contains(text(),'Redeem gift certificate')]"));
		 //Thread.sleep(3000);
         if(giftCertificateDiv.size()>0 &&  redeemGiftCertificate.size()>0) {      	
         	test.info("Gift certificate div is displaying in payment page");

				// checking availability of brain tree 
				List<WebElement> creditcardsBraintree = driver.findElements(By.xpath("//a[@class ='nav-link creditcard-tab active']"));
				System.out.println("Brain tree " + creditcardsBraintree.size());
				//checking availability of salesforce
				List<WebElement> creditcardsSalesForce = driver.findElements(By.xpath("//div[@class='sfpp-payment-method-header sfpp-payment-method-header-card']"));
				System.out.println("Salesforce " + creditcardsSalesForce.size());
				 //checking availability of stripe
			    List<WebElement> stripePayment = driver.findElements(By.xpath("(//div[contains(@class,'StripeElement')])[1]"));
			    System.out.println("Stripe paymnet " + stripePayment.size());
			 
			    // checking availability ofcybersource
			    List<WebElement>  cyberSourcePayment= driver.findElements(By.xpath("//li[@data-method-id='CREDIT_CARD']"));
			    System.out.println("Cyber source " + cyberSourcePayment.size());
			 
				    //if brain tree available if condition executes
					 if(creditcardsBraintree.size()>0) {
							 test.info("brain tree payment is activated");
							 //checking whether the gift card input box available
							 List<WebElement> giftCertificate = driver.findElements(By.xpath("//input[@class='giftCertCode']"));
							 if(giftCertificate.size()>0) {
								 //fetching paymentBysemiGc class by creating the instance
								 tc__PaymentProccessByGC_CC_Paypal code= new  tc__PaymentProccessByGC_CC_Paypal();				
								 code.paymentBySemiGC();					
								 logger.info("applied gift card code ");
							 }
					 }//if salesforce available if condition executes
					 else if(creditcardsSalesForce.size()>0) {
							 test.info("salesforce payment is activated");	
							 //checking whether the gift card input box available
							 List<WebElement> giftCertificate = driver.findElements(By.xpath("//input[@class='giftCertCode']"));				 
							 if(giftCertificate.size()>0) {
								 //fetching paymentBysemiGc class by creating the instance
								 tc__PaymentProccessByGC_CC_Paypal code= new  tc__PaymentProccessByGC_CC_Paypal();				
								 code.paymentBySemiGC();					
								 logger.info("applied gift card code ");
							 }
					 }//if stripe available if condition executes
					 else if(stripePayment.size()>0) {
					 		test.info("Stripe payment activated");	
					 		//checking whether the gift card input box available
					 		 List<WebElement> giftCertificate = driver.findElements(By.xpath("//input[@class='giftCertCode']"));				 
							 if(giftCertificate.size()>0) {
								 //fetching paymentBysemiGc class by creating the instance
								 tc__PaymentProccessByGC_CC_Paypal code= new  tc__PaymentProccessByGC_CC_Paypal();				
								 code.paymentBySemiGC();					
								 logger.info("applied gift card code ");
							 }		 		
					 }//if cybersource available if condition executes
					 else if(cyberSourcePayment.size()>0) {
						 	 test.info("Cyber source payment is activated");
							 //checking whether the gift card input box available
						 	 List<WebElement> giftCertificate = driver.findElements(By.xpath("//input[@class='giftCertCode']"));		
						 	 if(giftCertificate.size()>0) {
						 		 //fetching paymentBysemiGc class by creating the instance
								 tc__PaymentProccessByGC_CC_Paypal code= new  tc__PaymentProccessByGC_CC_Paypal();				
								 code.paymentBySemiGC();					
								 logger.info("applied gift card code ");
							 }
					 }
					 //If Gc totally redeemed the cart value then no need of another payment like credit card
					 //or paypal at that time directly pay with Gift certificate 
					 List<WebElement> gcText= driver.findElements(By.xpath("//div[@class='success giftcert-pi']")); 
					 List<WebElement> gcOrderRedemption= driver.findElements(By.xpath("//div[contains(text(),'Your order has been paid using gift certificates.')]")); 
					 if(gcOrderRedemption.size()>0) {
						 test.info("As total price is redeemed with Gc ,We are placing order with Gift certificate in place of Gc and CC payment");
							Thread.sleep(2000);
				    		List<WebElement> placeOrder= driver.findElements(By.cssSelector("button.place-order"));		    		
				    		if (placeOrder.size()>0) { 
				    			WebElement placeOrderDisplay= driver.findElement(By.cssSelector("button.place-order"));
				    			if(placeOrderDisplay.isDisplayed()) {
					    			 js.executeScript("window.scrollBy(0,350)", "");
					    			 //Thread.sleep(3000);
					    			 WebElement placeOrderButton= driver.findElement(By.cssSelector("button.place-order"));		    			    				
					    			 js.executeScript("arguments[0].click();", placeOrderButton);
					    			 Thread.sleep(5000);
						    		 logger.info("successfully click on the place order button by normal click");
				    				}
					    		}
				    		Thread.sleep(5000);
							 //Checkout_Validation checkout= new Checkout_Validation();
				    		if(driver.getTitle().endsWith("Order Confirmation | Providio")) {
				    			
				    			 Checkout_Validation checkout= new Checkout_Validation();
				    		 //validate the final place the order page
				    			 checkout.validatePlacetheOrderPage();
				    		
				    	     //ordernumberandOrderdate
				    			 checkout.ordernumberandOrderdate();
				    			 //Thread.sleep(5000);
				    			}
					 }					 
					//credit card process from checkout							
					 else if(creditcardsBraintree.size()>0 || creditcardsSalesForce.size()>0 ||stripePayment .size()>0 || cyberSourcePayment.size()>0) {
						 if(gcText.size()>0) {
							 test.info("GC  is redemeed");
							 //fetching credit card class by creating   tc__CreditCardPaymentProcess instance
						     tc__CreditCardPaymentProcess cc = new tc__CreditCardPaymentProcess();			     
						     cc.paymentByCreditCard();
						 }else {
							 test.info("No GC code is redemeed ");
							 test.pass("No GC code is redemeed because of insufficient balnce or Gc belongs to different customer");
						 }
					}  
		}else {			
			test.info("As gift card is in cart ,so gift certificate div is not displaying , so choosing credit card payment ");			
			//fetching credit card class by creating   tc__CreditCardPaymentProcess instance
			 tc__CreditCardPaymentProcess cc = new tc__CreditCardPaymentProcess();			     
		     cc.paymentByCreditCard();
			
		}
	}
	
	public void paymentProccessByGCandPaypal() throws InterruptedException {
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0, 500);");  
		// checking availability of brain tree  
		List<WebElement> creditcardsBraintree = driver.findElements(By.xpath("//a[@class ='nav-link creditcard-tab active']"));				
					
		//checking availability of salesforce
		List<WebElement> creditcardsSalesForce = driver.findElements(By.xpath("//div[@class='sfpp-payment-method-header sfpp-payment-method-header-card']"));
					
		 // checking availability of stripe
	    List<WebElement> stripePayment = driver.findElements(By.xpath("(//div[contains(@class,'StripeElement')])[1]"));
	 
	    //checking availability of cybersource
	    List<WebElement> cyberSourcePayment = driver.findElements(By.xpath("//li[@data-method-id='CREDIT_CARD']"));
	    System.out.println("The cybersource  paymnet is " +cyberSourcePayment.size());
	    
		 //checking whether gift certificate div is available or not
		 List<WebElement> giftCertificateDiv= driver.findElements(By.cssSelector(".payment-form-fields"));
		 List<WebElement> redeemGiftCertificate= driver.findElements(By.xpath("//label[contains(text(),'Redeem gift certificate')]"));
		 //Thread.sleep(3000);
        if(giftCertificateDiv.size()>0 &&  redeemGiftCertificate.size()>0) {   
        	test.info("Gift certificate div is displaying in payment page");	
        
        		//to check the availability of paypal in payment page
        	if(creditcardsBraintree.size()>0 || creditcardsSalesForce.size()>0) {
        		test.info("Paypal payment is available");		
		        	
				  //if brain tree available if condition executes
					 if(creditcardsBraintree.size()>0) {
							 test.info("brain tree payment is activated");
							 List<WebElement> giftCertificate = driver.findElements(By.xpath("//input[@class='giftCertCode']"));
							 if(giftCertificate.size()>0) {					 
								 tc__PaymentProccessByGC_CC_Paypal code= new  tc__PaymentProccessByGC_CC_Paypal();				
								 code.paymentBySemiGC();					
								 logger.info("applied gift card code ");
							 }
					 }//if salesforce available if condition executes
					 else if(creditcardsSalesForce.size()>0) {
							 test.info("salesforce payment is activated");					 
							 List<WebElement> giftCertificate = driver.findElements(By.id("giftCert"));
							 if(giftCertificate.size()>0) {
								 tc__PaymentProccessByGC_CC_Paypal code= new  tc__PaymentProccessByGC_CC_Paypal();				
								 code.paymentBySemiGC();					
								 logger.info("applied gift card code ");
							 }
					 }//if stripe available if condition executes
					 else if(stripePayment .size()>0) {
					 		test.info("Stripe payment activated");
					 		 List<WebElement> giftCertificate = driver.findElements(By.id("giftCert"));
					 		if(giftCertificate.size()>0) {
								 tc__PaymentProccessByGC_CC_Paypal code= new  tc__PaymentProccessByGC_CC_Paypal();				
								 code.paymentBySemiGC();					
								 logger.info("applied gift card code ");
							 }
					 }//if cyber source available if condition executes
					 else {
						 	 test.info("Cyber source payment is activated");
						 	 List<WebElement> giftCertificate = driver.findElements(By.id("giftCert"));
						 		if(giftCertificate.size()>0) {
									 tc__PaymentProccessByGC_CC_Paypal code= new  tc__PaymentProccessByGC_CC_Paypal();				
									 code.paymentBySemiGC();					
									 logger.info("applied gift card code ");
								 }
					 }
					 
					 //If Gc totally redeemed the cart value then no need of another payment like credit card
					 //or paypal at that time directly pay with Gift certificate 
					 
					 List<WebElement> gcText= driver.findElements(By.xpath("//div[@class='success giftcert-pi']")); 
					 List<WebElement> gcOrderRedemption= driver.findElements(By.xpath("//div[contains(text(),'Your order has been paid using gift certificates.')]")); 
					 if(gcOrderRedemption.size()>0) {
						 test.info("As total price is redeemed with Gc ,We are placing order with Gift certificate in place of Gc and paypal payment");
							Thread.sleep(2000);
				    		List<WebElement> placeOrder= driver.findElements(By.cssSelector("button.place-order"));		    		
				    		if (placeOrder.size()>0) { 
				    			WebElement placeOrderDisplay= driver.findElement(By.cssSelector("button.place-order"));
				    			if(placeOrderDisplay.isDisplayed()) {
					    			 js.executeScript("window.scrollBy(0,350)", "");
					    			 //Thread.sleep(3000);
					    			 WebElement placeOrderButton= driver.findElement(By.cssSelector("button.place-order"));		    			    				
					    			 js.executeScript("arguments[0].click();", placeOrderButton);
					    			 Thread.sleep(5000);
						    		 logger.info("successfully click on the place order button by normal click");
				    				}
					    		}
				    		Thread.sleep(5000);
							 //Checkout_Validation checkout= new Checkout_Validation();
				    		if(driver.getTitle().endsWith("Order Confirmation | Providio")) {
				    			
				    			 Checkout_Validation checkout= new Checkout_Validation();
				    		 //validate the final place the order page
				    			 checkout.validatePlacetheOrderPage();
				    		
				    	     //ordernumberandOrderdate
				    			 checkout.ordernumberandOrderdate();
				    			 //Thread.sleep(5000);
				    			}
					 }			
					 
					//paypal process from chechout	
					 //after applying the Gc ,a message will appear as gift certificate is applied		 
					 //if Braintree or SalesForce or stripePayment or cyberSource available then if condition will execute and payment will done with paypal 
					 else  if(creditcardsBraintree.size()>0 || creditcardsSalesForce.size()>0){
						 if(gcText.size()>0) {
							 	test.info("GC  is redemeed");
							 	logger.info("Entered into paypal");
								tc__CheckOutProcessByPayPal cpp = new tc__CheckOutProcessByPayPal();
								cpp.checkoutprocessFromCheckout();
						 }else {
								 test.info("No GC code is redemeed");
								 test.pass("No GC code is redemeed because of insufficient balnce or Gc beloAngs to different customer");				 
						 }
					 }
			
			}else {
				test.info("As no availability of paypal paying with Credit card");
				 tc__CreditCardPaymentProcess cc = new tc__CreditCardPaymentProcess();			     
			     cc.paymentByCreditCard();
			}
        }else {
        	test.info("As gift card is in cart ,so gift certificate div is not displaying , so choosing Paypal payment ");
    		tc__CheckOutProcessByPayPal cpp = new tc__CheckOutProcessByPayPal();
    		cpp.checkoutprocessFromCheckout();
        }
  }
	
	public void paymentByGiftCard() throws InterruptedException {
		// checking availability of brain tree
		List<WebElement> creditcardsBraintree = driver.findElements(By.xpath("//a[@class ='nav-link creditcard-tab active']"));				
		System.out.println("Braintree is "+ creditcardsBraintree.size());
		
		// checking availability of salesforce
		List<WebElement> creditcardsSalesForce = driver.findElements(By.xpath("//div[@class='sfpp-payment-method-header sfpp-payment-method-header-card']"));
		System.out.println("Saleforce is " +creditcardsSalesForce.size());
		
		//checking availability of  stripe payment				
	    List<WebElement> stripePayment = driver.findElements(By.xpath("(//div[contains(@class,'StripeElement')])[1]"));			
		System.out.println("stripePayment  is " +creditcardsSalesForce.size());
		
		//checking availability of  cybersource
	    List<WebElement> cyberSourcePayment = driver.findElements(By.xpath("//li[@data-method-id='CREDIT_CARD']"));
	    System.out.println("The cybersource  paymnet is " +cyberSourcePayment.size());
	    

		 //checking whether gift certificate div is available or not
		 List<WebElement> giftCertificateDiv= driver.findElements(By.cssSelector(".payment-form-fields"));
		 List<WebElement> redeemGiftCertificate= driver.findElements(By.xpath("//label[contains(text(),'Redeem gift certificate')]"));
		 //Thread.sleep(3000);
        if(giftCertificateDiv.size()>0 &&  redeemGiftCertificate.size()>0) {      	
        	test.info("Gift certificate div is displaying in payment page");
				 if(creditcardsBraintree.size()>0) {					 
						 test.info("brain tree payment is activated");
						 tc__PaymentProccessByGC code= new tc__PaymentProccessByGC();
					     //code.performRandomOperations(driver);
						 List<WebElement> giftCertificate = driver.findElements(By.id("giftCert"));
						 if(giftCertificate.size()>0) {
							 // checking the availability of gift certificate and sending gift certificate codes to input box
							 code.performSequentialOperations(driver);						
							 logger.info("applied gift card code");
							 test.info("Gc code applied");
						 }
							 
				 } else if(creditcardsSalesForce.size()>0) {					 
						 test.info("salesforce payment is activated");					 
						 tc__PaymentProccessByGC code= new tc__PaymentProccessByGC();
						 List<WebElement> giftCertificate = driver.findElements(By.id("giftCert"));
						 if(giftCertificate.size()>0) {
							 // checking the availability of gift certificate and sending gift certificate codes to input box
							 code.performSequentialOperations(driver);						
							 logger.info("applied gift card code");
							 test.info("Gc code applied");
						 }
				 }else if(stripePayment.size()>0) {
					 
					 	test.info("Stripe payment is activated");
					 	tc__PaymentProccessByGC code= new tc__PaymentProccessByGC();
						 List<WebElement> giftCertificate = driver.findElements(By.id("giftCert"));
						 if(giftCertificate.size()>0) {
							 // checking the availability of gift certificate and sending gift certificate codes to input box
							 code.performSequentialOperations(driver);						
							 logger.info("applied gift card code");
							 test.info("Gc code applied");
						 }
					 	
				 }else if(cyberSourcePayment.size()>0){
					 test.info("Cyber source payment is activated");
					 tc__PaymentProccessByGC code= new tc__PaymentProccessByGC();
					 List<WebElement> giftCertificate = driver.findElements(By.id("giftCert"));
					 if(giftCertificate.size()>0) {
						 // checking the availability of gift certificate and sending gift certificate codes to input box
						 code.performSequentialOperations(driver);						
						 logger.info("applied gift card code");
						 test.info("Gc code applied");
					 }
				 }		
 
		}else {	
			test.info("As gift card is in cart ,so gift certificate div is not displaying , so choosing credit card payment ");
			//if Braintree or SalesForce or stripePayment or cyberSource available then if condition will execute and payment will done with credit card
			if(creditcardsBraintree.size()>0 || creditcardsSalesForce.size()>0 ||stripePayment .size()>0 || cyberSourcePayment.size()>0) {	
				     tc__CreditCardPaymentProcess cc = new tc__CreditCardPaymentProcess();			     
				     cc.paymentByCreditCard();				 
			}else {
				//if Braintree or SalesForce or stripePayment or cyberSource available then if condition will execute and payment will done with paypal 
				if(creditcardsBraintree.size()>0 || creditcardsSalesForce.size()>0 ||stripePayment .size()>0 || cyberSourcePayment.size()>0){		 	
					tc__CheckOutProcessByPayPal cpp = new tc__CheckOutProcessByPayPal();
					cpp.checkoutprocessFromCheckout();				
					}
			}
		}
	}
}
