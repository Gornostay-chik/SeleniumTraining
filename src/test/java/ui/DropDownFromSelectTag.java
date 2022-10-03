package ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class DropDownFromSelectTag {
	
	public static String browser = "Firefox";	
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		
		if (browser.equals("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			
			//все свойства можно посмотреть в about:config
			FirefoxOptions options = new FirefoxOptions()
				      .addPreference("network.trr.default_provider_uri", "https://mozilla.cloudflare-dns.com/dns-query")
				      .addPreference("network.trr.mode", 2);
			
			driver = new FirefoxDriver(options);
		} else if (browser.equals("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		
		driver.get("https://www.sugarcrm.com/request-demo/");
		WebElement we = driver.findElement(By.name("employees_c"));
		
		Select select = new Select(we);
				
		select.selectByVisibleText("51 - 100 employees");
		
		//driver.quit();
	
	}

}
