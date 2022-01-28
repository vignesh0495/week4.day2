package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {
	public static void main(String[] args) {
		//setup the driver
		WebDriverManager.chromedriver().setup();
		// launch the browser
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		// To maximize window
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    //create actions object
		Actions builder = new Actions(driver);
		WebElement sp = driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		WebElement sh = driver.findElement(By.xpath("//span[text()=\"shirts\"]"));
		
		builder.moveToElement(sp).click(sh).perform();
		
		

}

}
