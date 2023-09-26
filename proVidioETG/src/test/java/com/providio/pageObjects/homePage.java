package com.providio.pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.providio.testcases.baseClass;

public class homePage extends baseClass {
WebDriver lDriver;
	
	public homePage(WebDriver rDriver){
		lDriver =rDriver;
		PageFactory.initElements(rDriver, this);
	}

	//profile
			@FindBy(xpath = "(//span[@class='registered-user-message dropdown-toggle'])[1]")
			WebElement profile;
			public void hoverOnProfile(WebDriver driver) throws InterruptedException {
				Thread.sleep(2000);
				Actions action = new Actions(driver);
		    	action.moveToElement(profile).build().perform();
			}
			
			//drop down my account
			@FindBy(xpath = "(//a[contains(text(),'My Account')])[1]")
			WebElement myAccount;
			public void clickOnMyAccount() throws InterruptedException {
				Thread.sleep(2000);
				myAccount.click();
				
			}
			//my orders
			@FindBy(xpath = "(//a[contains(text(),'My Orders')])[1]")
			WebElement myOrders;
			public void clickOnMyOrders() {
				myOrders.click();
			}
			
			//logout
			@FindBy(xpath = "(//a[contains(text(),'Logout')])[1]")
			WebElement logout;
			public void clickOnLogout() {
				logout.click();
			}
			
			//wishlist
			@FindBy(xpath = "(//span[contains(text(),'Wishlist')])[1]")
			WebElement wishlist;
			public void clickOnWishlist(WebDriver driver) throws InterruptedException {
				Thread.sleep(5000);
				JavascriptExecutor js = (JavascriptExecutor)driver;
				 js.executeScript("arguments[0].click();", wishlist);
			}
			
			//search bar
			@FindBy(xpath = "//input[@name='q']")
			WebElement searchBar;
			public void clickOnSearchBar(String typeSomething) throws InterruptedException {
				Thread.sleep(2000);
				searchBar.sendKeys(typeSomething);
			}
			//clicked on searched product
			@FindBy(xpath = "(//span[@class='name'])[2]")
			WebElement searchProduct;
			public void clickOnSearchedProduct() throws InterruptedException {
				Thread.sleep(3000);
				searchProduct.click();
				
			}
	
	
			
		//to get back to homepage 
			@FindBy(xpath = "//img[@class='logo']")
			WebElement forHomePage;
			public void clickOnLogo() throws InterruptedException {
				Thread.sleep(5000);
				forHomePage.click();
				
			}
	//carousel left click
			@FindBy(xpath = "//div[@class='slick-prev d-none d-md-block slick-arrow']")
			WebElement leftCarousel;

			public void clickOnLeftCarousel() throws InterruptedException {
					
							for(int j=1;j<=3;j++) {
								leftCarousel.click();
								}
							Thread.sleep(2000);
			}
			
			//carousel right click
			@FindBy(xpath = "//div[@class='slick-next d-none d-md-block slick-arrow']")
			WebElement rightCarousel;

			public void clickOnRightCarousel() {
					
					for(int i=1;i<=3;i++)
						{
						rightCarousel.click();
						}
					
			}
	 //2nd womencategory
		@FindBy(xpath = "(//a[@href='/s/RefArch/womens/?lang=en_US'])[2]")
		WebElement womens;
		public void clickOnWomens() throws InterruptedException {
			Thread.sleep(2000);
			womens.click();
		}
			//clothing
				@FindBy(xpath="//a[@title='Clothing']")
				WebElement clothing;
				public void clickOnClothing() throws InterruptedException {
					Thread.sleep(2000);
					clothing.click();
				}
				

				//jewellery
				@FindBy(xpath = "//a[@title='Jewelry']")
				WebElement jewellery;
				public void clickOnJewellery() throws InterruptedException {
					Thread.sleep(2000);
					jewellery.click();
				}
				
				//accessories
				
				@FindBy(xpath = "//a[@title='Accessories']")
				WebElement accessoriesInWomens;
				public void clickOnAccessoriesInWomens() throws InterruptedException {
					Thread.sleep(2000);
					accessoriesInWomens.click();
				}
			
				
		// mens
		@FindBy(xpath = "(//a[@href='/s/RefArch/mens/?lang=en_US'])[2]")
		WebElement mens;
		public void clickOnMens() throws InterruptedException {
			Thread.sleep(2000);
			mens.click();
		}
		
				//suits
				@FindBy(xpath = "//a[@title='Suits']")
				WebElement suits;
				public void clickOnSuits() throws InterruptedException {
					Thread.sleep(2000);
					suits.click();
				}
				//jackets and coats
				@FindBy(xpath = "//a[@title='Jackets & Coats']")
				WebElement jacketsAndCoats;
				public void clickOnJacketsAndCoats() throws InterruptedException {
					Thread.sleep(2000);
					jacketsAndCoats.click();
				}
				//shorts
				@FindBy(xpath = "//a[@title='Shorts']")
				WebElement shorts;
				public void clickOnShorts() throws InterruptedException {
					Thread.sleep(2000);
					shorts.click();
				}
				//dress shirts
				@FindBy(xpath = "//a[@title='Dress Shirts']")
				WebElement dressShirts;
				public void clickOnDressShirts() throws InterruptedException {
					Thread.sleep(2000);
					dressShirts.click();
				}
				//pants
				@FindBy(xpath = "//a[@title='Pants']")
				WebElement pants;
				public void clickOnPants() throws InterruptedException {
					Thread.sleep(2000);
					pants.click();
				}
				//accessories 
				@FindBy(xpath = "//a[@title='Accessories']")
				WebElement accessoriesInMens;
				public void clickOnAccessoriesInMens() throws InterruptedException {
					Thread.sleep(2000);
					accessoriesInMens.click();
				}
			
			
			
	
		
		//OnlyAccessories
		@FindBy(xpath = "/s/RefArch/mens/accessories/?lang=en_US")
		WebElement onlyAccesories;
		public void clickOnOnlyAccesories() throws InterruptedException {
			Thread.sleep(2000);
			onlyAccesories.click();
		}
		
				
			
		//luggage
		@FindBy(xpath = "(//a[@href='/s/RefArch/mens/?lang=en_US'])[2]")
		WebElement luggage;
		public void clickOnLuggage() throws InterruptedException {
			Thread.sleep(2000);
			luggage.click();
		}
		
		//to find the store icon
		@FindBy(xpath="//span[@class='header-store-name']")
		WebElement clickOnFindAStore;
		public void findastore(WebDriver driver) throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			 js.executeScript("arguments[0].click();", clickOnFindAStore);
			 Thread.sleep(2000);
			
		}		
		//to find the store button
			@FindBy(xpath="//button[contains(@class,' storelocator-search')]")
			WebElement findStores;
			public void findStore(WebDriver driver) throws InterruptedException {
				JavascriptExecutor js = (JavascriptExecutor)driver;
				 js.executeScript("arguments[0].click();", findStores);
				 Thread.sleep(2000);
				
			}
			
			//selected commerce cloud store
			@FindBy(xpath="//button[contains(@class,' select-store')]")
			List <WebElement> selectStore;
			public void selectStore(WebDriver driver) throws InterruptedException {
				// Use the Random class to generate a random index
		        Random random = new Random();
		        int randomIndex = random.nextInt(selectStore.size());
		        
		        WebElement randomButton = selectStore.get(randomIndex);

				JavascriptExecutor js = (JavascriptExecutor)driver;
				 js.executeScript("arguments[0].click();",  randomButton);				 
				test.info("The selected store is " +randomButton .getText());
				 Thread.sleep(2000);				
			}
			
		//zip code or postal code
			@FindBy(id = "store-postal-code")
			WebElement zipCodeInStore;
			public void clickOnZipCode(String zipcodeStore) {
				zipCodeInStore.sendKeys(zipcodeStore);
			}
			
		//radius in fina a store
			@FindBy(id="radius")
			WebElement radius;
			public void clickOnRadius() {
				Select selectRadius = new Select(radius);
				selectRadius.selectByIndex(1);
			}
			@FindBy(xpath = "//div[@id='findInStoreModal']")
			WebElement closeFindStore;
			
			public void clickOnCloseFindStore(WebDriver driver ) throws InterruptedException {
				Thread.sleep(2000);
				closeFindStore.click();
			}
			//validating the Home page
			//menus
			public void menus(WebDriver driver) throws InterruptedException {
				List<WebElement> navMenu = driver.findElements(By.xpath("//a[@class='nav-link dropdown-toggle text-uppercase font-weight-bold level-1']"));
			    int count = navMenu.size();
			    if(count>0) {
			    	 logger.info("Menus are displayed on the page.");
			    } else {
			    	logger.info("Menus are not displayed on the page.");
			    }
			    Thread.sleep(2000);
			}
	 
	      //logo
		  public  void logo(WebDriver driver) throws InterruptedException {
		    WebElement Logo = driver.findElement(By.xpath("//img[@class='logo']"));
		    if (Logo.isDisplayed()) {
		    	logger.info("Logo is displayed on the page.");
		    } else {
		    	logger.info("Logo is not displayed on the page.");
		    }
		    Thread.sleep(2000);
		 }
		  
		 //search bar
		 public  void search(WebDriver driver) throws InterruptedException {
			 WebElement Search = driver.findElement(By.xpath("//input[@name='q']"));
			    if (Search.isEnabled()) {
			    	logger.info("Search input field is enabled.");
			    } else {
			    	logger.info("Search input field is not enabled.");
			    }
			    Thread.sleep(2000);
		 }
		 
		 //mini cart
		    public  void minicart(WebDriver driver) throws InterruptedException {

		    WebElement MiniCart = driver.findElement(By.xpath("//a[contains(@class, 'minicart')]"));
		    if (MiniCart.isDisplayed()) {
		    	logger.info("MiniCart is displayed on the page.");
		    } else {
		    	logger.info("MiniCart is not displayed on the page.");
		    }
		    
		    Thread.sleep(2000);
		    }
		    
		    //wishList
		    public  void wishlist(WebDriver driver) throws InterruptedException {
		    	
		    WebElement WishList = driver.findElement(By.xpath("(//span[@class = 'sr-only' and text() ='Wishlist'])[1]"));
		    if (WishList.isDisplayed()) {
		    	logger.info("Wishlist is displayed on the page.");
		    } else {
		    	logger.info("Wishlist is not displayed on the page.");
		    }
		    Thread.sleep(2000);
		    }
		    
		    //profile
		    public  void profile(WebDriver driver) throws InterruptedException {
		    	
		    WebElement Profile = driver.findElement(By.xpath("//span[contains(text(), 'Sign In')]"));
		    if (Profile.isDisplayed()) {
		    	logger.info("Profile (Sign In) is displayed on the page.");
		    } else {
		    	logger.info("Profile (Sign In) is not displayed on the page.");
		    }
		    Thread.sleep(2000);
		    }
		    
		    //footer
		    public  void footer(WebDriver driver) {
		    
		    WebElement Footer = driver.findElement(By.xpath("//footer[@id='footercontent']"));
		    if (Footer.isDisplayed()) {
		    	logger.info("Footer is displayed on the page.");
		    } else {
		    	logger.info("Footer is not displayed on the page.");
		    }
		    }
			
}
