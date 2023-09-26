package com.providio.payments;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.providio.pageObjects.navigationPage;
import com.providio.pageObjects.productDescriptionPage;
import com.providio.pageObjects.productListingPage;
import com.providio.testcases.baseClass;

public class size extends baseClass{

	public void selectSize(WebDriver driver) throws InterruptedException {
		
		 
		//selecting attributes from the  pdp page by checking the condition 
			allElements(driver);
			Thread.sleep(2000);
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
         
			//to pick the store in pdp page 
		 List  <WebElement> pickUpStore =driver.findElements(By.id("delivery-options-store"));
		 if(pickUpStore.size()>0) {
			 WebElement pickUpStoreenabled =driver.findElement(By.id("delivery-options-store"));
			 if(pickUpStoreenabled.isEnabled()) {
				 js.executeScript("arguments[0].click();",pickUpStoreenabled);
				 test.info("Selected the pickUp in Store");
			 }else {
				 test.info("Pick up store is not available for this product");
			 }
		 }
		//checking the  add to cart enabled and  in stock
	     productDescriptionPage pdp = new productDescriptionPage(driver);
	     WebElement cartEnabled =driver.findElement(By.xpath("//button[contains(@class,'add-to-cart')]"));
	     List  <WebElement> inStock =driver.findElements(By.xpath("//div[contains(text(), 'In Stock')]"));
	     if(cartEnabled.isEnabled()&& inStock.size()>0) {
		     pdp.clickcartbutton(driver);      
	     }else {
	    	 //if the prodcut is outof stock then it will search for new prodcut by selecting random menu and random product
	    	 	System.out.println("Product is out of stock so searching for new product");	    	 
	    	 	//Thread.sleep(2000);
             
             // navigating randomly into menu and picking a random menu 
	    	 navigationPage navPage =new navigationPage(driver);
	    	 navPage.selectRandomMenu(driver);
	    	 
	    	 // selecting random product after landing into plp page
	    	 productListingPage plp = new productListingPage(driver);
	    	 plp.selectProductRandom(driver);
	
	    	//recursion -when a method calls itself it is called as recusrion, repeats the same processs untill product is instock and added to cart
	    	 selectSize(driver);	    	 	    	    	 
	     }	     
	}
	
	public void allElements(WebDriver driver) throws InterruptedException {
	
		//fetching the to collect the total number of div contains attributes  
		List <WebElement> mainDiv= driver.findElements(By.xpath("//div[@class='attributes px-0']"));
		System.out.println("The total number of size division are " +mainDiv.size());
		
		//fetching  all the attributes in pdp page 
		//fetching the color element in drop down
		List<WebElement> colorElement = driver.findElements(By.xpath("//span[contains(@class,' non-input-label')]"));
		System.out.println("The colordrop down count is " + colorElement.size());
		
		//fetching dropdown element in size
		List<WebElement> sizeElement = driver.findElements(By.xpath("//select[contains(@class,'select-size')]"));
		System.out.println("The size drop down count  is " +  sizeElement.size());
		
		//fecthing memeorysize element in dropdown
		List<WebElement> memoryElement = driver.findElements(By.id("memorySize-null"));
		
		//fetching width elements in drop down 
	    List<WebElement> widthElements = driver.findElements(By.xpath(".//select[@id='width-null']"));
        System.out.println(widthElements.size());
     
        //fetching showname element in drop down
        List<WebElement> showName = driver.findElements(By.xpath("//select[contains(@class,'select-showName')]"));
        System.out.println(showName.size());
	     
	     //another way of attributes :- selecting the attributes from box type
	     
        //fetching size elements from boxtype 
	     List<WebElement> sizeBox= driver.findElements(By.xpath("//div[contains(@class, 'select-size')]"));
	     
	     //fetching color element from box type
	     List<WebElement> colorBox= driver.findElements(By.xpath("//button[contains(@class,'color-attribute')]"));
	     
	     //fetching thememory element from box type
	     List<WebElement> memoryBox = driver.findElements(By.xpath("//div[contains(@class, 'select-memorySize')]"));
	     
	     //fetching the wiodth elements from box type 
		 List<WebElement> widthBox = driver.findElements(By.xpath("//div[contains(@class, 'select-width')]"));
		
		 // fetching show name from box type 
		 List<WebElement> showNameBox= driver.findElements(By.cssSelector(".showName"));
		 
		 // fetching the extended warranty eleent from box type
		 List<WebElement> extendedWarranty = driver.findElements(By.className("options-select"));
	     
		 //looping utill the product div count
	     for(int i=1; i<=mainDiv.size();i++) {
	    	 // checking whether color is in drop down or box type 
			if(colorElement.size()>0|| colorBox.size()>0) {
				productDescriptionPage pdp = new productDescriptionPage(driver);
				pdp.clickOnColor(driver);
				System.out.println("selected color");
				Thread.sleep(1000);
			}//// checking whether size is in drop down or box type 
			if(sizeElement.size()>0|| sizeBox.size()>0 ) {
				
				productDescriptionPage pdp = new productDescriptionPage(driver);
				pdp.clickOnSize(driver);
				System.out.println("selected size");
				//Thread.sleep(1000);					
			} // checking whether memory size is in drop down or box type 
			if(memoryElement.size()>0||  memoryBox.size()>0) {
				productDescriptionPage pdp = new productDescriptionPage(driver);
				pdp.selecttheMemorySize(driver);
				System.out.println("selected memory size");
				//Thread.sleep(1000);
			} // checking whether width is in drop down or box type 
			if(widthElements.size()>0 || widthBox.size()>0) {				
				productDescriptionPage pdp = new productDescriptionPage(driver);
				pdp.selectWidth(driver);
				System.out.println("selected width");
				//Thread.sleep(1000);
			}// checking whether showname is in drop down or box type 
			if(showName.size()>0 || showNameBox.size()>0) {
				//Thread.sleep(1000);
				productDescriptionPage pdp = new productDescriptionPage(driver);
				pdp.selectShowName(driver);
				System.out.println("Selected show name ");
			}// checking whether extened warranty is in drop down or box type 
			if(extendedWarranty.size()>0) {
				productDescriptionPage pdp = new productDescriptionPage(driver);
				pdp.selectExtendedWarranty(driver);
				System.out.println("Selected extended warranty ");
			}
	     }
	}
	public void clickOnBuyNow(WebDriver driver) throws InterruptedException {
		
		//selecting attributes from the  pdp page by checking the condition 
			allElements(driver);
			
		//checking the buy now button is enabled and in stock after seelecting the attributes 	
	     productDescriptionPage pdp = new productDescriptionPage(driver);
	     List <WebElement> buyNowEnabled =driver.findElements(By.xpath("//button[contains(@class,'buy-now btn btn-primary')]"));
	     List  <WebElement> inStock =driver.findElements(By.xpath("//div[contains(text(), 'In Stock')]"));
	     if((buyNowEnabled.size()>0)&& inStock.size()>0) {
	    	 
	    	 // verifying the buynow button is displayed or not 
	    	 Thread.sleep(3000);	    	 
	    	 WebElement buyNowEnabled1 =driver.findElement(By.xpath("//button[contains(@class,'buy-now btn btn-primary')]"));
		     test.info("Verifying Buy now button in PDP");
			     if(buyNowEnabled1.isDisplayed()) {
			    	 test.pass("Successfully clicked on buy now button");
			     }else {
			    	 test.fail("No  buy now button is clicked");
			     }
			 //clicking  on buy now button in pdp page   
		     pdp.clickOnBuyNowButton(driver);
		     System.out.println("Product added to cart by Buy now button");
		     
		     //clicking on place order button 
		     WebElement buyNowPlaceOrder =driver.findElement(By.cssSelector(".buynow-placeorder"));
		     JavascriptExecutor js = (JavascriptExecutor)driver;
		     js.executeScript("arguments[0].click();",buyNowPlaceOrder);     
		     
	     }else {
	    	//if the prodcut is outof stock then it will search for new prodcut by selecting random menu and random product
	    	 	System.out.println("Product is out of stock so searching for new product");	    	 
	    	 	//Thread.sleep(2000);
	    	 	navigationPage navPage =new navigationPage(driver);
		    	navPage.selectRandomMenu(driver);
		    	 
		    	 productListingPage plp = new productListingPage(driver);
		    	 plp.selectProductRandom(driver);	
		    	 
		    	//recursion -when a method calls itself it is called as recusrion, repeats the same processs untill product is instock and added to cart
		    	 selectSize(driver);    	 
	     }
	}
	
		public void paypalBuyNowFromPDP(WebDriver driver) throws InterruptedException {
			//checking if the buy now button is in avaible in pdp page if it is avaialble then executes the if condition 
			List<WebElement> paypalbuyNowPdp =driver.findElements(By.xpath("//div[contains(@class,'salesforce-buynow-element ')]"));
			if(paypalbuyNowPdp.size()>0) {
			
			//selecting attributes 
			  	 allElements(driver);
			  	 Thread.sleep(2000);
			  	
			  	//checking the paypal buy now button is enabled and in stock after seelecting the attributes 
			  	 WebElement paypalbuyNowEnabled =driver.findElement(By.xpath("//div[contains(@class,'salesforce-buynow-element ')]"));
			     List<WebElement> inStock =driver.findElements(By.xpath("//div[contains(text(), 'In Stock')]"));
			     if(paypalbuyNowEnabled.isEnabled()&& inStock.size()>0) {
			    	 productDescriptionPage pdp = new productDescriptionPage(driver);			    	 
			    	 pdp.clickOnPaypalBuyNow(driver);
			    	 
			    	 //verifying whether the paypalbuy now is displayed  after selecting attributes
			    	 test.info("Verifying the paypal buy now button");
			    	  if (paypalbuyNowEnabled.isDisplayed()) {
			    		  test.pass("Succesffuly clicked on paypal buy now button in pdp");
			    	  }
			    	  else {
			    		  test.fail("No  paypal buy now button in pdp");
			    	  }
			    	  
			     }else {
			    	//if the prodcut is outof stock then it will search for new prodcut by selecting random menu and random product
			    	 	System.out.println("Product is out of stock so searching for new product");			    	 
			    	 
				    	navigationPage navPage =new navigationPage(driver);
				    	navPage.selectRandomMenu(driver);
				    	 
				    	productListingPage plp = new productListingPage(driver);
				    	plp.selectProductRandom(driver);
				    
				    	//recursion -when a method calls itself it is called as recusrion, repeats the same processs untill product is instock and added to cart
				    	selectSize(driver);
			    	
			     }
			  
			}else {
				System.out.println("No paypal buy now button");
			}
		}	
}