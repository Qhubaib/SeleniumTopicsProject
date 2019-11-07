package SeleniumTopics;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Qhubaib
 * 
 * To Click on Single WebEement in a List<WebElement> Randomly
 * 		list.get(new Random().nextInt(list.size())).click();
 * 
 * To Click on Multiple WebEements in a List<WebElement> Randomly
 * 		for(int i=0;i<size;i++)
 * 			list.get(new Random().nextInt(list.size())).click();
 *
 */

public class ClickOnRandomLink {
	
	static WebDriver driver;
	WebDriverWait wait;
	
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
		driver.findElement(By.linkText("Gift Ideas")).click();
		List<WebElement> links = driver.findElements(By.cssSelector("ul#zg_browseRoot a"));
		int size = links.size();
		System.out.println(size);
		Random rand = new Random();
		System.out.println("Before Click"+driver.getTitle());
		for(int i=0;i<links.size();i++)
		{
			System.out.println(links.get(rand.nextInt(size)).getText()+"\n");
			//links.get(rand.nextInt(size)).click();
			links.get(new Random().nextInt(links.size())).click();
			driver.navigate().back();
			links = driver.findElements(By.cssSelector("ul#zg_browseRoot a"));
		}

	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
