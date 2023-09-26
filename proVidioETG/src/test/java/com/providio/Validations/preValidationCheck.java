package com.providio.Validations;

import com.providio.pageObjects.homePage;
import com.providio.pageObjects.productDescriptionPage;
import com.providio.pageObjects.productListingPage;
import com.providio.testcases.baseClass;

public class preValidationCheck extends baseClass{
	
	public static void prevalidationHome() throws InterruptedException {
		
		//validation 
		homePage hm = new homePage(driver);
		//menus
		hm.menus(driver);
		//logo
		hm.logo(driver);
		//search
		hm.search(driver);
		//mini cart
		hm.minicart(driver);
		//wishlist 
		hm.wishlist(driver);
		//profile
		hm.profile(driver);
		//footer
		hm.footer(driver);
		
	}
	
	public static void prevalidationPLP() throws InterruptedException {
		productListingPage plp = new productListingPage(driver);
		//Name
		plp.name(driver);
		//BreadCreamps
		plp.BreadCrumbs(driver);
		//filters
		plp.Filters(driver);
		//products
		plp.Products(driver);

	}
	
	public static void preValidationPDP() {
		productDescriptionPage pdp = new productDescriptionPage(driver);
		
		//product name
		pdp.productNameValidation(driver);
		//prouct Image
		pdp.productImageValidation(driver);
		//Addtocart button
		pdp.productAddtocartButtonValidation(driver);
		//review button
		pdp.productReviewLinkValidation(driver);
		//Details 
		pdp.productSepecificationsValidation(driver);
	}
	
}
