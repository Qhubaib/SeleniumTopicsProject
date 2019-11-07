package SeleniumTopics;

import java.util.List;
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
 * 
 */

public class BootStrapDropdownTest {
	
	static WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_1");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void test()
	{
		driver.findElement(By.xpath("//div[@class='btn-group']//button")).click();
		
		List<WebElement> dropdown = driver.findElements(By.xpath("//ul[contains(@class,'multiselect')]//li//a"));
		System.out.println(dropdown.size());
		for(int i=0;i<dropdown.size();i++)
		{
			String value=dropdown.get(i).getText();
			System.out.println(value);
			if(value.equalsIgnoreCase("HTML"))
			{
				System.out.println(value);
				dropdown.get(i).click();
				break;
			}
		}
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
