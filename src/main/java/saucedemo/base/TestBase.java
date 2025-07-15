package saucedemo.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;





public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/config/config.properties");		
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static void initialization() throws InterruptedException{
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
			    put("credentials_enable_service", false);
			    put("profile.password_manager_enabled", false);
			}});
			options.addArguments("--guest");  // Open in guest mode (no sync/profile data)

			driver = new ChromeDriver(options); 
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.out.println("URL from config: " + prop.getProperty("url"));
			driver.get(prop.getProperty("url"));
			Thread.sleep(1000);		}
		
		

}
