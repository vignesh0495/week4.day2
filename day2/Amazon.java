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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		//Load the url
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//search as oneplus 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		//Get the price of the first product
		String price1 = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		
		String replaceAll=price1.replaceAll("[^0-9]","");
		int price11 = Integer.parseInt(replaceAll);
		System.out.println("Thr price of the mobile"+" "+price11);
		
		//Print the number of customer ratings for the first displayed product
		WebElement ra = driver.findElement(By.xpath("(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style']/span)[2]"));
		String rating = ra.getText();
		System.out.println("Customer rating is"+" "+rating);

		//click on the stars
		driver.findElement(By.xpath("//i[@class='a-icon a-icon-star-small a-star-small-4-5 aok-align-bottom']")).click();		
		//Get the percentage of ratings for the 5 star.
		WebElement percent = driver.findElement(By.xpath("//table[@id='histogramTable']//td/following::td/following::td//a"));
		String ratingpercent = percent.getText();
		System.out.println("Rating percent for 5 star is"+" "+ratingpercent);
		
		//Click the first text link of the first image
		driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
		
		//switch to windows
		Set<String> windowHandles = driver.getWindowHandles();
		List<String>listwindow=new ArrayList<String>(windowHandles);
		String string = listwindow.get(1);
		listwindow.get(0);
		driver.switchTo().window(string);

		//Take a screen shot of the product displayed
		Thread.sleep(3000);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination=new File("./images/Amazon.png");
		FileUtils.copyFile(source, destination);
		
		Thread.sleep(3000);
		//Click 'Add to Cart' button
		driver.findElement(By.id("add-to-cart-button")).click();
		//Get the cart subtotal and verify if it is correct.
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@id='attach-sidesheet-view-cart-button-announce']/preceding-sibling::input")).click();
		
		String subtotal1 = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']/span")).getText();
		String replaceAll1=subtotal1.replaceAll("[^0-9.]","");
		float fnum = Float.parseFloat(replaceAll1);
		 int subtotal11= (int) fnum;
		
		System.out.println(subtotal11);
		if(price11==subtotal11) {
			System.out.println("Subtotal would similar Price");
		}
		else {
			System.out.println("Not matched");
		
		}
		driver.quit();
		
	}

}
