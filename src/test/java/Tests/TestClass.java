package Tests;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseTests.TestBase;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;

@Listeners(Utilities.ExtentReport.class)

public class TestClass extends TestBase{
	LoginPage loginPage;
	ProductsPage productsPage;
	CartPage cartPage;
	String expensiveItem;
	
	
	
	public TestClass(){
		super();
	}
	
	@BeforeClass
	public void setUp() throws InterruptedException{
		initialization();
		loginPage = new LoginPage();	
		productsPage = new ProductsPage();	
		cartPage=new CartPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		try {
		logger.info("Verifying Home Page Title");
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Swag Labs");}
		catch (AssertionError ae) {
		    logger.error("Assertion failed", ae);
		    throw ae;}
		catch(Exception e) {
			logger.error("Test failed",e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		
	}
	
	@Test(priority=2,dependsOnMethods="loginPageTitleTest")
	public void loginTest() throws InterruptedException{
		try {
		logger.info("Logging to the page with valid credentials");
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(1000);
	}
		catch(Exception e) {
			logger.error("Test failed",e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}}
	
	@Test(priority=3,dependsOnMethods="loginTest")
	public void addToCart() throws InterruptedException{
		try {
		logger.info("Most expensive item is adding to the cart ");
		expensiveItem=productsPage.getMostExpensiveItemAndAddToCart();
		Thread.sleep(1000);}
		catch(Exception e) {
			logger.error("Test failed",e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		
	}
	
	@Test(priority=4,dependsOnMethods="addToCart")
	public void verifyCart() throws InterruptedException{
		try {
		logger.info("Going to cart and verifying the cartItem");
		cartPage.clickCart();
		Assert.assertTrue(cartPage.cartItemName.size()==1);
		Assert.assertEquals(cartPage.cartItemName.get(0).getText(),expensiveItem);
		Thread.sleep(1000);}
		catch (AssertionError ae) {
		    logger.error("Assertion failed", ae);
		    Assert.fail("Test failed due to exception: " + ae.getMessage());;}
		catch(Exception e) {
			logger.error("Test failed"+e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		}
	

	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	

}
