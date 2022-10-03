package ui;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ResizeWidget {

	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.edge.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\msedgedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\geckodriver.exe");

		WebDriverManager.firefoxdriver().setup();
		//EdgeDriver driver = new EdgeDriver();
		
		FirefoxDriver driver = new FirefoxDriver();
		
		driver.get("https://jqueryui.com/resizable/");
		driver.manage().window().maximize();
	
		//переходим во фрейм
		WebElement iFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
	    driver.switchTo().frame(iFrame);
	    Thread.sleep(5000);
		//находим элемент, который нужно перетащить
		WebElement resize = driver.findElement(By.id("resizable"));

		
		Actions resizeAction = new Actions(driver);
		resizeAction.dragAndDropBy(resize, 300, 200).perform();
		
		Thread.sleep(5000);
		
		driver.quit();

	}

}
