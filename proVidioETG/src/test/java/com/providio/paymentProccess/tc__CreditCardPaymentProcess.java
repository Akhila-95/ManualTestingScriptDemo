package com.providio.paymentProccess;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.providio.Validations.Checkout_Validation;
import com.providio.Validations.ShippingAndBilling_PaymentDetails;
import com.providio.pageObjects.paymentpPage;
import com.providio.pageObjects.reviewOrderPage;
import com.providio.testcases.baseClass;

public class tc__CreditCardPaymentProcess extends baseClass{
	
	public void paymentByCreditCard() throws InterruptedException {
		
		List<WebElement> billingPage = driver.findElements(By.xpath("//label[contains(text(),'Billing Address')]"));		
		System.out.println("Billing page " +billingPage.size());
		if(billingPage.size()>0) { 
			
			WebElement billingPageDisplay = driver.findElement(By.xpath("//label[contains(text(),'Billing Address')]"));
			if(billingPageDisplay.isDisplayed()) {			
					
			        List<WebElement> continueasAGuest = driver.findElements(By.xpath("//button[contains(text(),'Guest Checkout')]"));
			        logger.info(continueasAGuest.size());
			        
			        paymentpPage pp = new paymentpPage(driver);
			        
			        JavascriptExecutor js = (JavascriptExecutor) driver;	    		  
		    		js.executeScript("window.scrollBy(0,700)", "");
					//brain tree
					List<WebElement> creditcardscheck = driver.findElements(By.xpath("//a[@class ='nav-link creditcard-tab active']"));
				    System.out.println("Brain treee"+creditcardscheck.size());
				    
				    //creditcard salesfornce
		
					List<WebElement> creditcardsSalesForce = driver.findElements(By.xpath("//div[@class='sfpp-payment-method-header sfpp-payment-method-header-card']"));
				    System.out.println(" Salesforce "+creditcardsSalesForce.size());
				    
				    //stripe
				    List<WebElement> stripePayment = driver.findElements(By.xpath("(//div[contains(@class,'StripeElement')])[1]"));
				    System.out.println("The stripe paymnet is " +stripePayment.size());
				    
				    //cybersource
				    List<WebElement> cyberSourcePayment = driver.findElements(By.xpath("//li[@data-method-id='CREDIT_CARD']"));
				    System.out.println("The cybersource  paymnet is " +cyberSourcePayment.size());
				    
					    if(creditcardscheck.size()>0) {
					    	
					    	List<WebElement> savedCardsBrainTree = driver.findElements(By.xpath("//option[@class ='js_stored_card']"));
					    	System.out.println(savedCardsBrainTree.size());
					    	if(savedCardsBrainTree.size()>0) {
					    		
					    		logger.info("Saved cards are there selected one of them");
					    		
					    	}else {
					    		
					    		Thread.sleep(5000);
					    		if(continueasAGuest.size()>0) {
					    			//guest cc
					    			guestUserCCpayment();			    			
				
				                }else {
				                	//new card
				                	registerUserCC();
				                }
					    		    		
					    	}
					   	
					    }else if(creditcardsSalesForce.size()>0) {
					    	
					    	
				    		if(continueasAGuest.size()>0) {
				    			//guest cc
				    			test.info("Salesforce payment is activated");
				    			salesforcePaymentProcessguest();			    			
			
			                }else {
			                	//new card						    	
			                	salesforcePaymentProcessguest();
			                }
		
					    	
					    	
					    }else if(stripePayment.size()>0) {
					    	test.info("Stripe payment activated");
					    	List<WebElement> savedCardsContainers= driver.findElements(By.id("saved-cards-container"));
					    	if(savedCardsContainers.size()>0) {
					    		List<WebElement> addNewCardList= driver.findElements(By.id("switch-to-add-card"));
					    		if(addNewCardList.size()>0) {
					    			WebElement addNewCard= driver.findElement(By.id("switch-to-add-card"));
					    			//addNewCard.click();
					    			//stripePayment();
					    			List<WebElement> savedCards= driver.findElements(By.id("switch-to-saved-cards"));
					    			WebElement reviewOrder= driver.findElement(By.xpath("//button[contains(text(), 'Next: Review Order')]"));	
					    			if(reviewOrder.isDisplayed()) {
					    				reviewOrderPage rop = new reviewOrderPage(driver);			    			
							    		rop.clickonReviewOrder(driver);
							    		logger.info("Clicked on review order button");
							    		Thread.sleep(2000);
					    			
					    		}
					    	}			    	
					     }else {
					    	 stripePayment();
					    	 js.executeScript("window.scrollBy(0,300)", "");
					     }
					    	
					    }else if(cyberSourcePayment.size()>0){
					    	test.info("Cyber source payment activated");
					    	List<WebElement> savedCardsCyberSourece = driver.findElements(By.cssSelector("div.saved-payment-instrument"));
					    	if(savedCardsCyberSourece.size()>0) {
					    	System.out.println("Cybersource paymnet"+savedCardsCyberSourece.size());
					    	
					    	
					    		//select one and send the cvv number of that card					    		
					    		List<WebElement> savedCards = driver.findElements(By.cssSelector("div.saved-payment-instrument"));
					    		Random random = new Random();
					            int randomIndex = random.nextInt(savedCards.size());
					            // Select the randomly chosen payment instrument
					            WebElement selectedPaymentInstrument = savedCards.get(randomIndex);
					            // Perform actions on the selected payment instrument (e.g., click)
					            Thread.sleep(1000);
					            selectedPaymentInstrument.click();
					            WebElement securityCode = driver.findElement(By.id("saved-payment-security-code"));
					            securityCode.sendKeys("987");
					    	
					    		
					    }else {
					    	
					    	cyberSourceNewcard();
					    }
					    }
					    //Salesforce payment integration place the order
					    if(creditcardsSalesForce.size()>0) {
					    	
					    	logger.info("clicking the salesforce place the order");
					    	
					    	//click the place order button
					    	pp.placetheOrder(driver);
					    	
					    }else {
					    	
					    	// review order page
				    		reviewOrderPage rop = new reviewOrderPage(driver);
				    		Thread.sleep(4000);			    		
				    		
				    		//List<WebElement> reviewOrderList= driver.findElements(By.xpath("//button[contains(text(), 'Next: Review Order')]"));
				    		WebElement reviewOrder= driver.findElement(By.xpath("//button[contains(text(), 'Next: Review Order')]"));	
				    		
				    		if(reviewOrder.isDisplayed()) {
					    		rop.clickonReviewOrder(driver);
					    		logger.info("Clicked on review order button");
					    		Thread.sleep(2000);
	
				    		}
				    		//placeorder
				    		WebElement placeOrder= driver.findElement(By.cssSelector("button.place-order"));	
				    		//List<WebElement> placeOrderList= driver.findElements(By.cssSelector("button.place-order"));
				    		if (placeOrder.isDisplayed()) { 
				    			 js.executeScript("window.scrollBy(0,300)", "");
				    			 Thread.sleep(3000);
				    			 WebElement placeOrderButton= driver.findElement(By.cssSelector("button.place-order"));		    						    				
				    			 js.executeScript("arguments[0].click();", placeOrderButton);				    			 
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
		    			 Thread.sleep(5000);
		    			}					
				}
		}
	}
	
	//payment of cyber source 
	private void cyberSourceNewcard() throws InterruptedException {
		
		paymentpPage pp = new paymentpPage(driver);
		
//		pp.addPaymentButton(driver);
//    	logger.info("click on the add payment button");
    	
        pp.latestcardnumber(driver);
        logger.info("entered card number");
        pp.latestExpDate(driver);
        logger.info("entered exp month");
        pp.latestExpYear(driver);
        logger.info("entered exp year");
        pp.latestSceuritycode(driver);
        logger.info("entered scecode");
		
	}
	
	//payment of salesforce integeration new one
	
	private void salesforcePaymentProcessguest() throws InterruptedException {
		paymentpPage pp = new paymentpPage(driver);
		
		//pp.radiobutton(driver);
		
		pp.cardNumber(driver);
    	
    	logger.info("entered card number");
    	pp.expiryDate(driver);
    	
    	logger.info("entered cvv");
    	pp.cvc(driver);
    	logger.info("entered exp");
	}
	//payment method of reg new card details
	private void registerUserCC() throws InterruptedException {
		
		paymentpPage pp = new paymentpPage(driver);

    	pp.selectnewcardindropdown();  
		logger.info("Selectd the new card");
		
		pp.setcardholdername(driver);
		logger.info("entered card name");
		
		pp.setcardnumber(driver);
		logger.info("entered card number");
		
		pp.setcardcvv(driver);
		logger.info("entered cvv");
		
		pp.setcardexp(driver);
		logger.info("entered exp");
	}
	
	//payment method of guest user cc
	private void guestUserCCpayment() throws InterruptedException {
		
		paymentpPage pp = new paymentpPage(driver);
		
		pp.setcardholdername(driver);
		logger.info("entered card name");
		
		pp.setcardnumber(driver);
		logger.info("entered card number");
		
		pp.setcardcvv(driver);
		logger.info("entered cvv");
		
		pp.setcardexp(driver);
		logger.info("entered exp");
		
	}
	
	public void stripePayment() {
		paymentpPage pp = new paymentpPage(driver);
		
		pp.cardNum(driver);
		test.info("entered card number");
		pp. expDate(driver);
		test.info("entered exp date");		
		pp.cvv(driver);
		test.info("entered cvv");
		pp.postalCode();
		test.info("entered postal code");
	}
	   
	//validate the payment page
	private void validatePaymentButtonClk() {
		
		//validate the payment page
	    WebElement paymentPage = driver.findElement(By.xpath("//label[contains(text(), 'Payment Method')]"));
	    if( paymentPage.isDisplayed()) {
		    String ActualTitleofpaymentPage = paymentPage.getText();
		    String ExpectedTitlepaymentPage = "Payment Method";
		    logger.info(paymentPage.getText());
		    
		    if (ActualTitleofpaymentPage.equals(ExpectedTitlepaymentPage)) {
		    	test.info("Verify that shipping address added");
		    	 test.pass("Successfully added the shipping address");
		    	 test.info("Verify the payment button is clicked");
		        test.pass("Successfully clicked on the Payment button");
		        logger.info("Successfully clicked on the Payment button");
		    } else {
		        test.fail( "The page Title does not match expected " + ExpectedTitlepaymentPage + " " + "  but found" + " " + ActualTitleofpaymentPage + " ");
		        logger.info("Click failed");
		    }
	    }
	
	}
	
	//validate after clicking the review oreder page
	private void validatethebuttonisclicked() {
		
		// Find the button using the XPath expression.
        List<WebElement> button = driver.findElements(By.xpath("//button[@name='submit' and @value='place-order']"));
        if(button.size()>0) {
        	 WebElement button1 = driver.findElement(By.xpath("//button[@name='submit' and @value='place-order']"));
	        // Check if the button is displayed on the page.
	        if (button1.isDisplayed()) {
	            System.out.println("Button is displayed.");
	        } else {
	            System.out.println("Button is not displayed.");
	        }
	}
	}
}