package ui;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class HandleWindows {
	
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
		
		driver.get("https://www.startpage.com");
		Thread.sleep(2000);
		//driver.manage().window().maximize();
		
		WebElement searchStringParent = driver.findElement(By.xpath("//input[@id='q']"));
		
		searchStringParent.sendKeys("startpage");
		searchStringParent.sendKeys(Keys.ENTER);
		//нужно подождать чтобы результаты прогрузились
		Thread.sleep(5000);
		
		WebElement firstLink = driver.findElement(By.xpath("//div[@class='w-gl__result__main'][1]"));
		Thread.sleep(2000);
		firstLink.click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		
		Iterator<String> iterator = windowHandles.iterator();
		String parentWindow = iterator.next();
		String childWindow = iterator.next();
		
		driver.switchTo().window(childWindow);
		//нужно подождать чтобы результаты прогрузились
		Thread.sleep(5000);
		WebElement searchStringChild = driver.findElement(By.xpath("//input[@id='q']"));
		searchStringChild.sendKeys("google");
		searchStringChild.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		
		//возвращаемся в первое окно
		driver.switchTo().window(parentWindow);
		
		Thread.sleep(5000);
		
		driver.quit();
	
	}

}
