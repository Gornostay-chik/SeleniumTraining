package ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class LoginTest {
	
	public static String browser = "Firefox";	
	public static WebDriver driver;

	public static void main(String[] args) {
		
		if (browser.equals("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		
		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();
		
		//The example of Relative locator
		By passwordLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"));
		
		driver.findElement(passwordLocator).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		
		String currentURL = driver.getCurrentUrl();
		System.out.println("Current url is: " + currentURL);
		
		String titlePage = driver.getTitle();
		System.out.println("Current title is: " + titlePage);
		
		String pageSource = driver.getPageSource();
		System.out.println("THe page source is: " + pageSource);
		
		List<WebElement> myListWE = driver.findElements(By.xpath("//div[@class='inventory_list']/div/div/a/img"));
		
		for(WebElement myWE:myListWE) {
			System.out.println("WebElement is: " + myWE.getAttribute("alt"));
		}
		
		driver.navigate().to("https://en.wikipedia.org");
		String windowHandle = driver.getWindowHandle();
		System.out.println("Current handle is: " + windowHandle);
		
		
		driver.quit();
	
	}

}
