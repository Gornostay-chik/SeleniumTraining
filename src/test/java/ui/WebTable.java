package ui;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable {

	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.edge.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\msedgedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Acer\\eclipse-workspace\\brouserDrivers\\geckodriver.exe");

		//все свойства можно посмотреть в about:config
		FirefoxOptions options = new FirefoxOptions()
			      .addPreference("network.trr.default_provider_uri", "https://mozilla.cloudflare-dns.com/dns-query")
			      .addPreference("network.trr.mode", 2);
		
		WebDriverManager.firefoxdriver().setup();
		//EdgeDriver driver = new EdgeDriver();
		
		FirefoxDriver driver = new FirefoxDriver(options);
		
		driver.get("https://www.wellsfargo.com/foreign-exchange/currency-rates/");
		//driver.manage().window().maximize();
		
		//find all rows
		List<WebElement> rowElements =driver.findElements(By.xpath("/html/body/div[1]/div[3]/div/div[3]/div[1]/div/div[1]/table/tbody/tr"));
		int rowSize = rowElements.size();
		System.out.println("Amount of the row: " + rowSize);
				
		//find all columns
		List<WebElement> columnElements =driver.findElements(By.xpath("/html/body/div[1]/div[3]/div/div[3]/div[1]/div/div[1]/table/tbody/tr[1]/td"));
		int columnSize = columnElements.size();
		System.out.println("Amount of the columns: " + columnElements.size());
		
		for(int i=1;i<=rowSize; i++) {
			for(int j=1; j<=columnSize; j++) {
				String cellText = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[3]/div[1]/div/div[1]/table/tbody/tr["+i+"]/td["+j+"]")).getText();
				System.out.print(cellText + "	");
			}
			System.out.println("");
		}
		
		driver.quit();

	}

}
