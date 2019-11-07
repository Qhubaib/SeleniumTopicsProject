package SeleniumTopics;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author "Syed Qhubaib Ahmed"
 * 
 * Through implicitlyWait --> if we dont write proper xpath (or) if the element is not available then it will through 
 * 		"NoSuchElementException"
 * 		org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {"method":"xpath","selector":"//input[@type='submit1']"}
 * 
 * Through ExplicitlyWait --> org.openqa.selenium.TimeoutException: Expected condition failed: waiting for visibility of element located by 
 * 			By.xpath: (//div[contains(@class,'a-section')]//img1)[5] (tried for 30 second(s) with 500 milliseconds interval)
 * 
 * Through FluentWait --> Caused by: org.openqa.selenium.NoSuchElementException: 
 * 			no such element: Unable to locate element: {"method":"link text","selector":"Amazon Fresh"}
 *
 */

public class WaitsTesting {
	
	static WebDriver driver;
	WebDriverWait wait;
	
	String parent;
	
	public static void fluentwaitmethod(By locator)
	{
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(40))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
		fwait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
	}
	
	@BeforeTest
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void test()
	{
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("watches");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		parent = driver.getWindowHandle();
		System.out.println("Parent window is: "+driver.getTitle());
		for(int i=1;i<=4;i++)
		{
			By image = By.xpath("(//div[contains(@class,'a-section')]//img)["+i+"]");
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(image)).click();
			Set<String> childwindows = driver.getWindowHandles();
			for(String child:childwindows)
			{
				if(!parent.equalsIgnoreCase(child))
				{
					driver.switchTo().window(child);
					System.out.println("Child window is: "+driver.getTitle()+"\n");
					driver.close();
				}
				driver.switchTo().window(parent);
			}			
			
		}
		By amazonfresh = By.linkText("Best Sellers");
		System.out.println(driver.getTitle());
		fluentwaitmethod(amazonfresh);
		System.out.println(driver.getTitle());
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
