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

public class iFrame {
	
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
		
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");
		Thread.sleep(2000);
		driver.manage().window().maximize();
		//переключаемся во фрейм
		//driver.switchTo().frame(0);//по индексу
		//driver.switchTo().frame("iframeResult");//по id или name
		//по самому элементу iFrame
		WebElement iFrame = driver.findElement(By.id("iframeResult"));
		
		//переключаемся по фрейм
		driver.switchTo().frame(iFrame);
		
		driver.findElement(By.xpath("/html/body/button")).click();
		Thread.sleep(5000);
		//переключаемся в модальное окно
		Alert alertWindow = driver.switchTo().alert();
		
		String alertText = alertWindow.getText();
		System.out.println(alertText);

		alertWindow.sendKeys("Kitty");
		
		Thread.sleep(5000);
		//нажимаем кнопку "Ok" в модальном окне
		alertWindow.accept();
		
		//возвращаемся в родительский фрейм
		driver.switchTo().parentFrame();
		System.out.println(driver.getTitle());
		
		Thread.sleep(5000);
		driver.quit();
	
	}

}
