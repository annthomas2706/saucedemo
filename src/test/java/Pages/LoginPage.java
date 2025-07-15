package Pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseTests.TestBase;


public class LoginPage extends TestBase{
	

	//Page Factory - OR:
	@FindBy(name="user-name")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="login-button")
	WebElement login;
	
	//Initializing the Page Objects:
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
		//Actions:
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
		public void login(String un, String pwd){
			username.sendKeys("standard_user");
			password.sendKeys("secret_sauce");
			login.click();
		}
		
	
	

}
