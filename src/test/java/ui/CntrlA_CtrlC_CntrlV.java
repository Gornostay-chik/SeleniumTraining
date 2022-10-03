package ui;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CntrlA_CtrlC_CntrlV {

	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.edge.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\msedgedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\geckodriver.exe");

		WebDriverManager.firefoxdriver().setup();
		//все свойства можно посмотреть в about:config
		FirefoxOptions options = new FirefoxOptions()
			      .addPreference("network.trr.default_provider_uri", "https://mozilla.cloudflare-dns.com/dns-query")
			      .addPreference("network.trr.mode", 2);
		
		FirefoxDriver driver = new FirefoxDriver(options);
		
		driver.get("https://extendsclass.com/text-compare.html");
		driver.manage().window().maximize();

		WebElement copyFrom = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[2]/div/div[1]/textarea"));
		WebElement copyTo = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[3]/div[2]/div/div[1]/textarea"));
		
		//создаем и выполняем действие ctrl+A, cntrl+C
		Actions ctrlCcntrlA = new Actions(driver);
		ctrlCcntrlA.keyDown(copyFrom, Keys.CONTROL).sendKeys("a").sendKeys("c").build().perform();
		
		Thread.sleep(2000);
		
		//создаем и выполняем действие ctrl+A, cntrl+v
		Actions ctrlCcntrlV = new Actions(driver);
		ctrlCcntrlA.keyDown(copyTo, Keys.CONTROL).sendKeys("a").sendKeys("v").build().perform();
		
		Thread.sleep(5000);
		
		driver.quit();

	}

}
