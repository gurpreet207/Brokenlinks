package links;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FreeCRM_testlinks {

	public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {
	
		
	URL url=null;
	String portal=	"https://www.freecrm.com/";
	
	//Handling of Untrusted Certificates in Chrome *****************
	//DesiredCapabilities cap = new DesiredCapabilities();//Allows you launch browser with special capabilities
	//cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	//WebDriver driver =new FirefoxDriver(cap);
	//*****************************************************************
	
	//Handling of Untrusted Certificates in Firefox *****************
	DesiredCapabilities cap = new DesiredCapabilities();//Allows you launch browser with special capabilities
	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	WebDriver driver =new FirefoxDriver(cap);
		//*****************************************************************
		
	
	
	driver.get(portal);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	
	driver.findElement(By.name("username")).sendKeys("gurpreet");
	driver.findElement(By.name("password")).sendKeys("test@123");
	driver.findElement(By.xpath(".//input[@type='submit']")).click();
	driver.switchTo().frame("mainpanel");
	
	//links have "a" & "img" tag ,but all links may not have href property
	//first get list of a tags and then add webelements of image tag
	List<WebElement> list = driver.findElements(By.tagName("a"));
	list.addAll(driver.findElements(By.tagName("img")));
	System.out.println("Total Number of links and images"+ list.size());
	List<WebElement> activelinks =new ArrayList<WebElement>();
	
	//2.Iterate list: exclude all the links and images which doesn't have href attribute 
	for(int i=0;i<list.size();i++)
	{
		if(     list.get(i).getAttribute("href")!=null && (! list.get(i).getAttribute("href").contains("javascript"))   )
		{
		activelinks.add(list.get(i));	
		}
	}

	
	System.out.println("Number of active links and images"+ activelinks.size());
	
	//3. Checking Href & url connectionswith Hrtpconnections api
	//Check protocol followed by the application
	
	try {
		url = new URL(portal);
	} catch (MalformedURLException e) {
		
		e.printStackTrace();
	}  
	  
	System.out.println("Protocol: "+url.getProtocol());  
	System.out.println("Host Name: "+url.getHost());  
	 
	for (int j=0;j<activelinks.size();j++)
	{
		HttpURLConnection connection= (HttpURLConnection) new URL(activelinks.get(j).getAttribute("href")).openConnection();//open connection with url
		connection.connect();
		String respose=connection.getResponseMessage();	
		connection.disconnect();
		System.out.println(activelinks.get(j).getAttribute("href") +"--->" +respose);
	
	}
	
	
}

}
