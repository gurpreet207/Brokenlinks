package links;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FreeCRM_testlinks {

	public static void main(String[] args) {
		
	WebDriver driver =new FirefoxDriver();
	driver.get("https://www.freecrm.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	
	driver.findElement(By.name("username")).sendKeys("gurpreet");
	driver.findElement(By.name("password")).sendKeys("test@123");
	driver.findElement(By.xpath(".//input[@type='submit']")).click();
	
	

	}

}
