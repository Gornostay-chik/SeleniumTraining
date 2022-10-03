package ui;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDrop {

	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.edge.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\msedgedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\geckodriver.exe");

		WebDriverManager.firefoxdriver().setup();
		//EdgeDriver driver = new EdgeDriver();
		
		FirefoxDriver driver = new FirefoxDriver();
		
		driver.get("https://jqueryui.com/droppable/");
		//driver.manage().window().maximize();
	
		//переходим во фрейм
		WebElement iFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
	    driver.switchTo().frame(iFrame);
		//находим элемент, который нужно перетащить
		WebElement drag = driver.findElement(By.id("draggable"));
		//находим элемент, куда нужно перетащить
		WebElement drop = driver.findElement(By.id("droppable"));
		
		Actions dragAndDrop = new Actions(driver);
		dragAndDrop.dragAndDrop(drag, drop).perform();
		
		Thread.sleep(5000);
		
		driver.quit();

	}

}
