package com.providio.Validations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.providio.testcases.baseClass;

public class ShippingAndBilling_PaymentDetails extends baseClass {

	   //get the shipping ,billing and payment details in place order page 
    @FindBy(xpath = "(//div[@class='single-shipping'])[2]")
    List<WebElement> shippingDetails;
    @FindBy(xpath = "(//div[@class='address-summary'])[2]")
    List<WebElement> billingDetails;
    @FindBy(xpath = "//div[@class='summary-details']")
    List<WebElement> paymentDetails;
   
    
    public void placeOrderPageDetails() throws InterruptedException {
    	Thread.sleep(2000);
    	if(shippingDetails.size()>0) {
    		System.out.println();
    		WebElement shippingDetail = driver.findElement(By.xpath("(//div[@class='single-shipping'])[2]"));
    		test.info("shipping details are " + shippingDetail.getText());
    	}if(billingDetails.size()>0) {
    		WebElement billingDetail = driver.findElement(By.xpath("(//div[@class='address-summary'])[2]"));
    		test.info("Billing details " + billingDetail.getText());
    	}if(paymentDetails.size()>0) {
    		WebElement paymentDetail = driver.findElement(By.xpath("(//div[@class='address-summary'])[2]"));
    		test.info("Payment details " + paymentDetail.getText());
    	}
    }
}
