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
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Swag Labs");
	}
	
	@Test(priority=2,dependsOnMethods="loginPageTitleTest")
	public void loginTest() throws InterruptedException{
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(1000);
		
	}
	
	@Test(priority=3,dependsOnMethods="loginTest")
	public void addToCart() throws InterruptedException{
		expensiveItem=productsPage.getMostExpensiveItemAndAddToCart();
		Thread.sleep(1000);
		
	}
	
	@Test(priority=4,dependsOnMethods="addToCart")
	public void verifyCart() throws InterruptedException{
		cartPage.clickCart();
		Assert.assertTrue(cartPage.cartItemName.size()==1);
		Assert.assertEquals(cartPage.cartItemName.get(0).getText(),expensiveItem);
		Thread.sleep(1000);
		}
	
	
	

	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	

}
