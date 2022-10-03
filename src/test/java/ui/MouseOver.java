package ui;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseOver {

	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.edge.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\msedgedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\geckodriver.exe");

		WebDriverManager.firefoxdriver().setup();
		//EdgeDriver driver = new EdgeDriver();
		//все свойства можно посмотреть в about:config
		FirefoxOptions options = new FirefoxOptions()
			      .addPreference("network.trr.default_provider_uri", "https://mozilla.cloudflare-dns.com/dns-query")
			      .addPreference("network.trr.mode", 2);
		
		FirefoxDriver driver = new FirefoxDriver(options);
		
		driver.get("https://www.ebay.com");
		//driver.manage().window().maximize();
	
		//заходим раздел "Электроника" в меню сайта
		WebElement electronics = driver.findElement(By.xpath("/html/body/div[5]/div[1]/ul/li[3]/a"));
		Actions moveToElectronics = new Actions(driver);
		moveToElectronics.moveToElement(electronics).perform();
		//IMPLICIT WAIT
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//EXPLICIT WAIT
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[1]/ul/li[3]/div[2]/div[2]/iframe")));
		
		//FLUENT WAIT	
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					       .withTimeout(Duration.ofSeconds(20))
					       .pollingEvery(Duration.ofSeconds(2))
					       .withMessage("This is custom message!")
					       .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[1]/ul/li[3]/div[2]/div[2]/iframe")));
		
		//в открывшемся меню переключаемся во фрейм с баннером  "Настройтесь на общение..."
		WebElement iFrame = driver.findElement(By.xpath("/html/body/div[5]/div[1]/ul/li[3]/div[2]/div[2]/iframe"));
		//переключаемся по фрейм
		driver.switchTo().frame(iFrame);
		//находим сам баннер и нажимаем на него
		WebElement banner = driver.findElement(By.xpath("/html/body/a"));
		banner.click();
		
		
		Thread.sleep(5000);
		
		driver.quit();

	}

}
