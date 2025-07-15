package Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseTests.TestBase;

public class ProductsPage extends TestBase{
	
	
	//Initializing the Page Objects:
			public ProductsPage(){
				PageFactory.initElements(driver, this);
			}

	 // List of item prices
    @FindBy(className = "inventory_item_price")
    List<WebElement> prices;

    // List of item names
    @FindBy(className = "inventory_item_name")
    List<WebElement> itemNames;
    
    @FindBy(xpath = "//button[contains(text(),'Add to cart')]")
    List<WebElement> addToCartButtons;
    
    

 // action
    public String  getMostExpensiveItemAndAddToCart() {
        double highestPrice = 0.0;
        int maxIndex = -1;
        String maxItemName="";

        for (int i = 0; i < prices.size(); i++) {
            String priceText = prices.get(i).getText().replace("$", "").trim();
            double price = Double.parseDouble(priceText);

            if (price > highestPrice) {
            	highestPrice = price;
            	maxItemName=itemNames.get(i).getText();
            	 maxIndex = i;
            	
             }
         }
        	System.out.println(maxItemName);
             addToCartButtons.get(maxIndex).click();
         
            return maxItemName;    
        }

    }


