package week4.day2;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingMultipleElements {
	
	public static void main(String[] args) {
		//setup the driver
		WebDriverManager.chromedriver().setup();
		// launch the browser
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/frame.html");
		// To maximize window
		driver.manage().window().maximize();

}
}