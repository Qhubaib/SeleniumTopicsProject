package SeleniumTopics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
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
 * First we need list of webElements for links
 * Then we need to create TreeSet<String> Object --> If we want Capture unique elements & in Ascending Order
 * 	Store the List<WebElements> in TreeSet<String> By using add() Method
 * We can use Sorting Methods also 
 * 			Like --> Collections.sort(List<>)
 * 				 --> Arrays.sort(a,Collections.revereOrder())
 *Collections.reverse()
 */

public class AllLinksCapture {
	
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
		List<WebElement> links = driver.findElements(By.tagName("a"));
		Random rand = new Random();
		rand.nextInt(links.size());
		System.out.println("Size of Links in list<WebElement>: "+links.size());
		Set<String> set = new TreeSet<String>();
		List<String> displayedLinks = new ArrayList<String>();
		Set<String> setEmpty1 = new TreeSet<String>();
		List<String> setEmpty = new ArrayList<String>();
		for(int i=0;i<links.size();i++)
		{
			set.add(links.get(i).getText());
			if(links.get(i).isDisplayed())
				displayedLinks.add(links.get(i).getText());
			//System.out.println(links.get(i).getText());
		}
		System.out.println("Size of Links in SET: "+set.size());
		System.out.println("Size of Displayed Links in ArrayLinks: "+displayedLinks.size());
		for(String link:displayedLinks)
		{
			if(!link.isEmpty())
			{
				setEmpty1.add(link);
				setEmpty.add(link);
			}
		}
		System.out.println("Size of Links in ArrayList_Empty: "+setEmpty.size());
		System.out.println("Size of Links in SET_Empty: "+setEmpty1.size());
		String a[] = new String[setEmpty.size()];
		for(int i=0;i<setEmpty.size();i++)
		{
			a[i]=setEmpty.get(i);
		}
		
		Arrays.parallelSort(a,Collections.reverseOrder());
		for(String s:a)
		{
			System.out.println(s);
		}
		
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
