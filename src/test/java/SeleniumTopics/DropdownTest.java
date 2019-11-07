package SeleniumTopics;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Qhubaib
 * 
 * 1.Select sel = new Select(WebElement);
 * 		sel.selectByIndex(0), sel.selectByValue(value attribute), sel.selectByVisibleText(text value) -- 
 * sel.getOptions() -- > return List<WebElement> --> By using this we can navigate through for loop
 * 
 * 2.List<WebElement> list = driver.findElements(By.tagName("option"));
 *  for(int i=0;i<size;i++)
 *  	driver.get(i).click();
 * 
 */

public class DropdownTest {
	
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
		WebElement ele = driver.findElement(By.id("searchDropdownBox"));
		Select select = new Select(ele);
		select.selectByIndex(11);
		System.out.println("Select By Index Test: "+select.getFirstSelectedOption().getText());
		select.selectByValue("search-alias=beauty");
		System.out.println("Select By Value Test: "+select.getFirstSelectedOption().getText());
		select.selectByVisibleText("Gift Cards");
		System.out.println("Select By Visible Text Test: "+select.getFirstSelectedOption().getText());
		
		List<WebElement> drop=select.getOptions();
		for(int i=0;i<drop.size();i++)
		{
			//String text=drop.get(i).getText();
			String value=drop.get(i).getAttribute("value");
			//select.selectByVisibleText(text);
			//select.selectByIndex(i);
			System.out.println("The Value is: "+value);
			select.selectByValue(value);
		}

		List<WebElement> dropdown = ele.findElements(By.tagName("option"));
		for(int i=0;i<dropdown.size();i++)
		{
			System.out.println("The dropdown values are: "+dropdown.get(i).getText());
			dropdown.get(i).click();
		}
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
