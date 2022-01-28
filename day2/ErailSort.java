package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailSort {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		//Launch the URL - https://erail.in/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		
		driver.get("https://erail.in/");
		
		//Uncheck the check box - sort on date
		driver.findElement(By.id("chkSelectDateOnly")).click();
		
		//clear and type in the source station 
		WebElement from = driver.findElement(By.id("txtStationFrom"));
		from.clear();
		from.sendKeys("MDU");
		from.sendKeys(Keys.ENTER);
			
		//clear and type in the destination station
		WebElement to = driver.findElement(By.id("txtStationTo"));
		to.clear();
		to.sendKeys("CBE");
		to.sendKeys(Keys.ENTER);
		
		//Find all the train names using xpath and store it in a list
		int row_count = driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr")).size();
		List <String> TrainName = new ArrayList<String>();
		for(int i=1;i<=row_count;i++)
		{	
			String names= driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr["+i+"]//td[2]")).getText();
			TrainName.add(names);
		}
		
		//Use Collections method to sorting it and then print it
		Collections.sort(TrainName);
		for(int i=0;i<row_count;i++)
		{	
			System.out.println(TrainName.get(i));
		}
		
		driver.close();
	}


}
