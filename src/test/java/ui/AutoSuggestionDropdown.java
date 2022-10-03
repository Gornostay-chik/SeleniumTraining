package ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class AutoSuggestionDropdown {
	
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
		
		
		driver.get("https://www.mvideo.ru");
		
		driver.manage().window().maximize();
		
		//подвал станицы появляется только после того, как до него проскролишь! Поэтому сделан скролл!
		//Как реализовать скроллинг
		//https://www.browserstack.com/guide/selenium-scroll-tutorial
		//https://www.guru99.com/scroll-up-down-selenium-webdriver.html#3
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		System.out.println("SCROOLING!");
		
		//ждем 5 секунд, чтобы элемент "подвал" появился
		Thread.sleep(5000);
		
		//можно искать элемент
		List<WebElement> allLinks = driver.findElements(By.xpath("/html/body/mvid-root/div/mvid-primary-layout/mvid-layout/div/div[2]/mvid-footer-container/mvid-footer/footer/div/div[3]/div[1]//child::a"));
		
		for(WebElement link:allLinks) {
		System.out.println(link.getText() + " " + link.getAttribute("href"));
		}
		
		
		driver.quit();
	
	}

}
