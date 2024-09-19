package Entrata_Automation;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumAutomation_Entrata {

	WebDriver driver;
	ChromeOptions option;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sarika\\eclipse-workspace\\Entrata_SeleniumAutomation\\Chrome\\chromedriver.exe");
		option=new ChromeOptions();
		
		driver = new ChromeDriver(option);
		
		
		// Step1:- open the browser and navigate to Entrata Website
		driver.get("https://www.entrata.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	
	@Test
	public void automatSubMenu_Products() throws InterruptedException {
		//locate accept cookies button and click it
		driver.findElement(By.id("cookie-accept")).click();
		//Step2:- Verify the title
		String actualTitle=driver.getTitle();
		String expectedTitle="Property Management Software | Entrata";
		
		Assert.assertEquals(actualTitle, expectedTitle);
		
		//pause the execution of the current thread of a specific time in milliseconds
		Thread.sleep(3000);
		
		//Step3:-move from product section to the facility management
		WebElement product =driver.findElement(By.xpath("//*[contains(@id,'w-dropdown-toggle-0')]"));
		Actions action = new Actions(driver);
		action.moveToElement(product).perform();
		Thread.sleep(1000);
		
		driver.findElement(By.linkText("Facility Management")).click();
		
		//Step4:locate the watch demo and click it.
		driver.findElement(By.xpath("//a[contains(@href,'https://go.entrata.com/schedule-demo.html')]")).click();
		
		String MainWindow = driver.getWindowHandle();
		System.out.println(MainWindow);
		Set<String> handles =driver.getWindowHandles();
		Iterator it = handles.iterator();
		while(it.hasNext()) {
			String ChildWindow=(String) it.next();

			if(!MainWindow.equalsIgnoreCase(ChildWindow)) {
				//Switch window to the child frame
				driver.switchTo().window(ChildWindow);
				System.out.println("Window is switch");
				
				Thread.sleep(2000);
				//Locate the form and enter details for Schedule a demo with one of representatives
				//Actions a = new Actions(driver);
				
				WebElement fname=driver.findElement(By.id("FirstName"));
				fname.sendKeys("Sarika");
				
				WebElement lname= driver.findElement(By.id("LastName"));
				lname.sendKeys("Sangle");
				
				WebElement email = driver.findElement(By.id("Email"));
				email.sendKeys("Email");
				
				WebElement company_name= driver.findElement(By.id("Company"));
				company_name.sendKeys("Entrata");
				
				WebElement phone_num= driver.findElement(By.id("Phone"));
				phone_num.sendKeys("9011771196");
				
				WebElement select_option = driver.findElement(By.xpath("//select[@id='Unit_Count__c']"));
				
				Select dropdown = new Select(select_option);
				dropdown.selectByIndex(3);
				
				WebElement title = driver.findElement(By.id("Title"));
				title.sendKeys("Software Test Engineer");
				
				Thread.sleep(3000);
				
				//Switch window to the parent frame
				driver.switchTo().window(MainWindow);
				System.out.println("Window is switch to parentframe");
				Thread.sleep(2000);
				
			}
		}
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		//Step : close the browser
		driver.quit();
	}

}
