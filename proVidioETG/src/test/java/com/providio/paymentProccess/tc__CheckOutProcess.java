	package com.providio.paymentProccess;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.providio.Validations.QuantityValidation;
import com.providio.Validations.miniCartAndCartValidation;
import com.providio.pageObjects.checkOutPage;
import com.providio.pageObjects.guestUserLoginPage;
import com.providio.pageObjects.miniCartPage;
import com.providio.pageObjects.viewCartPage;
import com.providio.testcases.baseClass;
import com.providio.testcases.tc__TaxCalculationCheckoutPage;

public class tc__CheckOutProcess extends baseClass {

    public void checkoutprocess() throws InterruptedException {

       List <WebElement> minicartcount = driver.findElements(By.xpath("//span[contains(@class,'minicart')]"));
       List<WebElement> byBuyNow = driver.findElements(By.xpath("//span[contains(@class,'order-receipt-label grand-total-label')]"));
     
	       if(minicartcount.size()>0) {
	    	   WebElement miniCartDisplay = driver.findElement(By.xpath("//span[contains(@class,'minicart')]"));
	    	   if(miniCartDisplay.isDisplayed()) {
		    	   WebElement minicartcount1 = driver.findElement(By.xpath("//span[contains(@class,'minicart')]"));
		    	   String countOfMinicart = minicartcount1.getText();
		    	   int minicartCountValue = Integer.parseInt(countOfMinicart);
		
		        if (minicartCountValue > 0) {		
		            miniCartPage mc = new miniCartPage(driver);		            
		            Thread.sleep(5000);		               
		            //click on the cart button
		            mc.clickcartbutton(driver);
		            Thread.sleep(1000);
		            
		            List <WebElement> minicartPopUp = driver.findElements(By.xpath("(//h1)[1]"));
		            if(minicartPopUp.size()>0) {
			            //validate the button click 
			            miniCartAndCartValidation validation= new miniCartAndCartValidation();
			            validation.validateMiniCartClick();
	         
			            Thread.sleep(1000);
			            //click review order
			            mc.clickviewCartButton(driver);
						logger.info("clicked the view cart button in the minicart");
						
						//validate the view cart button click
						validation.validateViewCartClick();
						Thread.sleep(1000);
		            }
		
		            viewCartPage vcp = new viewCartPage(driver);
		            //click the checkout button
		            vcp.clickCheckout(driver);
		            Thread.sleep(1000);
		
		            //Taxcalculation
		            
		           // tc__TaxCalculationCheckoutPage tc = new tc__TaxCalculationCheckoutPage();           
		           // tc.taxCalculation();
		            
		           //if any guest user means guest checkout
		            clickContinueAsGuest();
		            Thread.sleep(2000);
		            List<WebElement> shippingLabel= driver.findElements(By.xpath("(//h2[contains(text(),'Shipping')])[2]"));
		            List<WebElement> pickUpStoreLabel= driver.findElements(By.xpath("(//span[contains(text(),'Store Pickup')])[1]")); 
		            if(shippingLabel.size()>0) {
		            	WebElement shippingLabelDisplay= driver.findElement(By.xpath("(//h2[contains(text(),'Shipping')])[2]"));
		            	if(shippingLabelDisplay.isDisplayed()) {
			            	checkOutPage cp = new checkOutPage(driver);
			            	//selecting shipping address
				            selectShippingAddress(cp);
				            
				            if(pickUpStoreLabel.size()>0) {
				            	//when bipois are enabled,store pickup works, and this code executes				            
				            	//clicking on paymwnt button
				            	selectPaymentMethod(cp);
				            	//entering billing address details
				            	selectBillingAddress(cp);
				            }
				            selectPaymentMethod(cp);
		            	}
		             
		            }else {
	            		 List<WebElement> billingAddress= driver.findElements(By.xpath("//label[contains(text(),'Billing Address')]"));		            	
		            	 checkOutPage cp = new checkOutPage(driver);
		            	 if(billingAddress.size()>0) {
		            		 selectBillingAddress(cp);
			            	 selectPaymentMethod(cp);
	            	 }		          
		        } 
		        
		       }else {
		            logger.info("The cart value is empty");
		            test.fail("The cart value is empty");
		       }
	       }
	       
	       if(byBuyNow.size()>0){
	    	   
	    	   //Taxcalculation
		           // tc__TaxCalculationCheckoutPage tc = new tc__TaxCalculationCheckoutPage();           
		          //  tc.taxCalculation();
		            
		           //if any guest user means guest checkout
		            clickContinueAsGuest();
		            
		
		            checkOutPage cp = new checkOutPage(driver);
		          //selecting shipping address
		            selectShippingAddress(cp);
		            selectPaymentMethod(cp);  
	          }
	       }
    } 
    public void clickContinueAsGuest() throws InterruptedException {
        List<WebElement> continueasAGuest = driver.findElements(By.xpath("//button[contains(text(),'Guest Checkout')]"));
        logger.info(continueasAGuest.size());

        if (continueasAGuest.size() > 0) {
            guestUserLoginPage guestLoginPage = new guestUserLoginPage(driver);
            guestLoginPage.clickOnGuestCheckOut();
            logger.info("Guest checkout");
            guestLoginPage.clickOnEmail(reEnterMail);
            logger.info("Guest mail again");
            guestLoginPage.clickOnContinueAsGuest();
            logger.info("Guest continue as guest");
            //Thread.sleep(5000L);
          
        }
  
    }

    public void selectShippingAddress(checkOutPage cp) throws InterruptedException {
        List<WebElement> existingAddress1 = driver.findElements(By.xpath("(//label[contains(text(),'Shipping To')])[1]"));
	        if(existingAddress1.size()>0) {
	        	 WebElement existingAddress = driver.findElement(By.xpath("(//label[contains(text(),'Shipping To')])[1]"));
	        	 if(existingAddress.isDisplayed()) {
		            logger.info("Address already exists");
		        	WebElement addNewAddress = driver.findElement(By.xpath("(//button[contains(@class,'btn-add-new')])[1]"));
			        	if(addNewAddress.isDisplayed()) {
			        		JavascriptExecutor js = (JavascriptExecutor) driver;
			    	    	js.executeScript("arguments[0].click();",  addNewAddress);				        	
			    	    	addressDetails(cp);
			        	}
		        	        	 
		        }else {
		        	addressDetails(cp);			          
		        	}
	        }
        
    }

    public void selectBillingAddress(checkOutPage cp) throws InterruptedException {
    	 WebElement billingAddress = driver.findElement(By.xpath("//label[contains(text(),'Billing Address')]"));
    	 
    	 if(billingAddress.isDisplayed()) {    		  
    		 WebElement billingName = driver.findElement(By.id("billingFirstName"));
    		 if(billingName.isDisplayed()) {
			    	Thread.sleep(1000);		    	
			        cp.setBillingFName();		
			        logger.info("Entered fname");		
			        Thread.sleep(1000);		
			        cp.setBillingLName();		
			        logger.info("Entered lname");		
			        WebElement Address1 = driver.findElement(By.xpath("//input[@id='billingAddressOne']"));		
			        Random random = new Random();		
			        int randomNumber = 123; // Generates a random number between 100 and 999		
			        address = String.valueOf(randomNumber);		
			        Address1.clear();		
			        Address1.sendKeys(address);		
			        WebElement Address11 = driver.switchTo().activeElement();
			        Thread.sleep(1000);		
			        Address11.sendKeys(Keys.ARROW_DOWN);		
			        Thread.sleep(1000);		
			        Address11.sendKeys(Keys.ARROW_DOWN);		
			        Address11.sendKeys(Keys.ENTER);		
			        Thread.sleep(2000);		
			        cp.setBillingPhoneNum();		
			        logger.info("Entered phone number");		        
			        Thread.sleep(2000);
    		 }
    	 }	        
    }
    
    public void selectPaymentMethod(checkOutPage cp) throws InterruptedException {
    	
    	List<WebElement> addTOAccount = driver.findElements(By.id("addShippingAddressToMyAccount"));
        if(addTOAccount.size()>0) {
        	WebElement addTOAccount1 = driver.findElement(By.id("addShippingAddressToMyAccount"));
        	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("arguments[0].click();",addTOAccount1);
	    	test.info("New address is added to account");
        }
        List<WebElement> paymentButton = driver.findElements(By.cssSelector(".submit-shipping"));
        if(paymentButton.size()>0) {
	        cp.clickpaymentbutton(driver);
	        logger.info("Clicked on the payment button");
	       
        }

        List<WebElement> errorMessageInvalidCityList = driver.findElements(By.xpath("//div[@id='defaultCity']"));
        if(errorMessageInvalidCityList.size()>0) {       	
	        WebElement errorMessageInvalidCity = driver.findElement(By.xpath("//div[@id='defaultCity']"));
	        if(errorMessageInvalidCity.isDisplayed()) {
	        boolean isDisplayedinvalidcity = errorMessageInvalidCity.isDisplayed();
	        test.info("The error is " + errorMessageInvalidCity.getText() );

	        if(isDisplayedinvalidcity) {
	        	test.info("User entered the wrong city and we are entering the new address");
	        	test.pass("User entered the wrong city and we are entering the new address");         
	            logger.info("User entered the wrong city and we are entering the new address");
	
	        	WebElement Address1 = driver.findElement(By.xpath("//input[@id='shippingAddressOnedefault']"));
	        	Address1.clear();          
	            int randomNumber = 123; //random.nextInt(900) + 100// Generates a random number between 100 and 999
	            address = String.valueOf(randomNumber);
	            Address1.sendKeys(address);
	            WebElement Address11 = driver.switchTo().activeElement();         
	            Thread.sleep(1000);
	            Address11.sendKeys(Keys.ARROW_DOWN);
	            Thread.sleep(1000);
	            Address11.sendKeys(Keys.ARROW_DOWN);
	            Address11.sendKeys(Keys.ENTER);
	            Thread.sleep(1000);
	            cp.clickpaymentbutton(driver);
	            logger.info("Clicked on the payment button 2nd time");
	        }
	          }
	        }
        }
        
   /*public void selectPaymentMethod(checkOutPage cp) throws InterruptedException {	   
        cp.clickpaymentbutton(driver);
        logger.info("Clicked on the payment button");
        Thread.sleep(2000);
    }*/
        
      
  public void addressDetails(checkOutPage cp) throws InterruptedException {
		  Thread.sleep(1000);	
		  WebElement firstName = driver.findElement(By.xpath("//input[@id='shippingFirstNamedefault']"));
		if(firstName.isDisplayed()) {
	        cp.setFisrtName(fname);	
	        logger.info("Entered fname");	
	        Thread.sleep(1000);	
	        cp.setLastname(lname);	
	        logger.info("Entered lname");	
	        WebElement Address1 = driver.findElement(By.xpath("//input[@id='shippingAddressOnedefault']"));	
	        Random random = new Random();	
	        int randomNumber = 123; // Generates a random number between 100 and 999	
	        address = String.valueOf(randomNumber);	
	        Address1.clear();	
	        Address1.sendKeys(address);	
	        WebElement Address11 = driver.switchTo().activeElement();
	        Thread.sleep(1000);	
	        Address11.sendKeys(Keys.ARROW_DOWN);	
	        Thread.sleep(1000);	
	        Address11.sendKeys(Keys.ARROW_DOWN);	
	        Address11.sendKeys(Keys.ENTER);	
	        Thread.sleep(2000);	
	        cp.setPhone(phonenumber);	
	        logger.info("Entered phone number");	            
	        Thread.sleep(2000);   
		}
  }
}
