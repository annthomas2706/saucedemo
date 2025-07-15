package saucedemo;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

import saucedemo.base.TestBase;

public class TestClass extends TestBase{
	LoginPage loginPage;
	ProductsPage productsPage;
	
	
	
	public TestClass(){
		super();
	}
	
	@BeforeClass
	public void setUp() throws InterruptedException{
		initialization();
		loginPage = new LoginPage();	
		productsPage = new ProductsPage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Swag Labs");
	}
	
	@Test(priority=2)
	public void loginTest() throws InterruptedException{
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(3000);
		
	}
	
	@Test(priority=3)
	public void addToCart() throws InterruptedException{
		productsPage.getMostExpensiveItemAndAddToCart();
		Thread.sleep(3000);
		
	}
	
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	

}
