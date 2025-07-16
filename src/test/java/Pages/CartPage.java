package Pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseTests.TestBase;

public class CartPage extends TestBase {
	
	
	public CartPage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="shopping_cart_badge")
	public WebElement cartIcon;
	
	@FindBy(className="inventory_item_name")
	public List<WebElement> cartItemName;
	
	//actions:
	public void clickCart() { 
		cartIcon.click();
	}
	
	

}
