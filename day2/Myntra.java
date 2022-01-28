package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

		public static void main(String[] args) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			
				//Open url https://www.myntra.com/
			
				WebDriverManager.chromedriver().setup();
				ChromeDriver driver = new ChromeDriver();
				driver.get("https://www.myntra.com/");
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
			
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				//Mouse hover on MeN and Click Jackets 
	
				Actions builder = new Actions(driver);
				WebElement Men = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
				//WebElement Jackets =  driver.findElement(By.xpath("(//a[text()='Jackets'])[1]"));
				builder.moveToElement(Men).perform();
				driver.findElement(By.xpath("(//a[text()='Jackets'])[1]")).click();
				
				//Find the total count of item 
				String TotalCount = driver.findElement(By.className("title-count")).getText();
				System.out.println("Total count of Jackets"+ TotalCount);
				List<WebElement> Categories = driver.findElements(By.xpath("//ul[@class='categories-list']//label/span"));
				
				int Category_Count= Categories.size();
				
				//Validate the sum of categories count matches
				
				for(int i =0; i<Category_Count;i++)
				{
					System.out.println("Count of category of" + i + Categories.get(i).getText());
				}
						
				//Check jackets
				driver.findElement(By.xpath(" //input[@value='Jackets']/following-sibling::div")).click();
						
				//Click + More option under BRAND
				driver.findElement(By.xpath("//div[@class='brand-more']")).click();
							
				//Type Duke and click checkbox
				driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("Duke");
				Thread.sleep(2000);
			    WebElement Duke=driver.findElement(By.xpath("//input[@value='Duke']"));
			    Thread.sleep(2000);
			    builder.moveToElement(Duke).click(Duke).perform();
			    Thread.sleep(2000);
				//Close the pop-up x
				driver.findElement(By.xpath("//ul[@class='FilterDirectory-indices']/following-sibling::span")).click();
				
				//Confirm all the Coats are of brand Duke
				    //Hint : use List 
				List <WebElement> brands = driver.findElements(By.className("product-brand"));
				int Count_brand = brands.size();
				for( WebElement brand:brands)
				{
					if(brand.getText().matches("Duke"))
						System.out.println("Brand Duke matched");
					else
							System.out.println("Brand Duke mismatched");
				}
				
				// Sort by Better Discount
				driver.findElement(By.className("sort-sortBy")).click();
				driver.findElement(By.xpath("//input[@value='price_asc']/parent::label")).click();
				
				//Find the price of first displayed item and click first product
				String Price = driver.findElement(By.className("product-discountedPrice")).getText();
				System.out.println("First Price is "+ Price);
				
				driver.findElement(By.className("product-base")).click();
				
				//opens new window-> navigate to that window
				Set <String> windows = driver.getWindowHandles();
				List <String> window = new ArrayList(windows);
				driver.switchTo().window(window.get(1));
				
				//get a screen shot
				
				File source = driver.getScreenshotAs(OutputType.FILE);
				File Dest = new File("./screenshot/Jacket.png");
				FileUtils.copyFile(source, Dest);
				
				
				//Click on WishList Now
				driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
				Thread.sleep(1000);
				
				// Close Browser
				driver.quit();

			

		}


}
