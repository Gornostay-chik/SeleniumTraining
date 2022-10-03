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

public class JavaScriptExecutor {
	
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
		
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
		driver.manage().window().maximize();
		
		//Print the title of the page
		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		String jsScript = "return document.title;";
		String title = (String) jsExec.executeScript(jsScript);
		System.out.println("The title is: " + title);
		
		//Click to the button "Try it!"
		//переключаемся по фрейм
		WebElement iFrame = driver.findElement(By.id("iframeResult"));
		driver.switchTo().frame(iFrame);
		jsExec.executeScript("myFunction()");
		
		
		Thread.sleep(5000);
		//переключаемся в модальное окно
		Alert alertWindow = driver.switchTo().alert();
		//выводим текст из модального окна с алертом
		String alertText = alertWindow.getText();
		System.out.println(alertText);
		//нажимаем кнопку "Ok" в модальном окне
		alertWindow.accept();
		
		//highlight button "Try it!"
		WebElement buttonTryIt = driver.findElement(By.xpath("/html/body/button"));
		jsExec.executeScript("arguments[0].style.border='5px solid green'", buttonTryIt);
		
		//scroll
		driver.navigate().to("https://www.w3schools.com/");
		WebElement certifiedSQL = driver.findElement(By.xpath("/html/body/div[5]/div[6]/div/div[1]/a[3]"));
		jsExec.executeScript("arguments[0].scrollIntoView(true);", certifiedSQL);
		
		
		Thread.sleep(5000);
		driver.quit();
	
	}

}
