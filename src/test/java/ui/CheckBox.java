package ui;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class CheckBox {
	
	public static String browser = "Firefox";	
	public static WebDriver driver;
	
	static void clickCheckBox(WebDriver driver) throws Exception {
		//скролл чтобы видеть элементы
		//https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html
		WebElement radio = driver.findElement(By.name("employees_c"));//элемент, до которого нужно скролить
		Actions scroll = new Actions(driver);
		scroll.moveToElement(radio);
		scroll.perform();
		
		//нажимаем чекбоксы
				driver.findElement(By.id("interest_market_c0")).click();
				Thread.sleep(5000);
				driver.findElement(By.id("interest_sell_c0")).click();
				Thread.sleep(5000);
				driver.findElement(By.id("interest_serve_c0")).click();
				Thread.sleep(5000);
	}
	
	static void acceptCookies(WebDriver driver) throws Exception {
		driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
	}

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
		Thread.sleep(5000);
		
		//driver.manage().window().maximize();
		
		try {
			clickCheckBox(driver);
		} catch (Exception e)
		{
			if (e.getMessage().contains("CybotCookiebotDialogBodyUnderlay"))
			{
				acceptCookies(driver);
				clickCheckBox(driver);
			}
			else
			{
				System.out.println("Something went wrong");
			}
		}
		
		driver.quit();
	
	}

}
