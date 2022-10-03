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

public class KeyBoardEvents {

	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.edge.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\msedgedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\geckodriver.exe");

		WebDriverManager.firefoxdriver().setup();
		//все свойства можно посмотреть в about:config
		FirefoxOptions options = new FirefoxOptions()
			      .addPreference("network.trr.default_provider_uri", "https://mozilla.cloudflare-dns.com/dns-query")
			      .addPreference("network.trr.mode", 2);
		
		FirefoxDriver driver = new FirefoxDriver(options);
		
		driver.get("https://text-compare.com/");
		driver.manage().window().maximize();

		Actions action = new Actions(driver);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		//вставляем в буфер клавиатуры строку1 "☺️😩😍❤️😒😊😘😳👌🏽😁😏😭😔💕👌"
		String text1="This is text1 from clipboard";
		StringSelection stringSelection1 = new StringSelection(text1);
		clipboard.setContents(stringSelection1, null);
		//вставляем текст1
		WebElement textArea1 = driver.findElement(By.xpath("//textarea[@id='inputText1']"));
		action.keyDown(textArea1, Keys.CONTROL).sendKeys("v").build().perform();
		
		//вставляем в буфер клавиатуры строку2
		String text2="This is text2 from clipboard";
		StringSelection stringSelection2 = new StringSelection(text2);
		clipboard.setContents(stringSelection2, null);
		//вставляем текст 2
		WebElement textArea2 = driver.findElement(By.xpath("//textarea[@id='inputText2']"));
		action.keyDown(textArea2, Keys.CONTROL).sendKeys("v").build().perform();
		
		//нажимаем кнопку "compare"
		driver.findElement(By.className("compareButtonText")).click();		
		
		Thread.sleep(5000);
		
		driver.quit();

	}

}
