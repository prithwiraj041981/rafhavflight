package FlightBooking;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.TestBase;

public class Book_a_flight extends TestBase{
public static final long TIMEOUT = 30;

	
	public static String Usernamefromexcel;
	public static String passwordfromexcel;

	public Book_a_flight() {
		PageFactory.initElements(driver, this);

	}
	/* this code is developed for identifying elements */
	//will be modified
	@FindBy(xpath="//input[@name='ctl00_mainContent_ddl_originStation1_CTXT']")
	WebElement from;
	@FindBy(xpath="//div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul")
	WebElement gettablefrom;
	
	
	@FindBy(xpath="//input[@name='ctl00_mainContent_ddl_destinationStation1_CTXT']")
	WebElement TO;
	
	@FindBy(xpath="//div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul")
	WebElement gettableto;
	
	@FindBy(xpath="//span[@id='view_fulldate_id_1']")
	WebElement Date;
	
	@FindBy(xpath="//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-right']//")
	WebElement Date1;
	
	@FindBy(xpath="//div[@id='divpaxinfo']")
	WebElement Passenger;
	
	@FindBy(xpath="//input[@name='ctl00$mainContent$chk_StudentDiscount']")
	List<WebElement> CheckboxStudent;
	
	@FindBy(xpath="//div[@class='ui-datepicker-group ui-datepicker-group-last']")
	WebElement gettable;
	
	@FindBy(xpath="//div[@class='ui-datepicker-group ui-datepicker-group-last']/table[@class='ui-datepicker-calendar']")
	WebElement gettabledate;
	
	@FindBy(xpath="//input[@name='ctl00$mainContent$btn_FindFlights']")
	WebElement SearchButton;
	@FindBy(xpath="//input[@name='ctl00$mainContent$ddl_originStation1']")
	WebElement Selectto;
	
	@FindBy(xpath = "//div[@class='search_options_menucontentbg']/div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']/a[contains(text(),'Kochi')]")
	WebElement todes;
	
	@FindBy(xpath = "//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody/tr[5]/td[5]/a")
	WebElement location;
	
	@FindBy(xpath = "//a[contains(text(),'31')]")
	List<WebElement> date123;
	
	@FindBy(xpath = "//table[@class='ui-datepicker-calendar']")
	List<WebElement> date321;
	
	public void searchflight() throws IOException, Exception {
		from.click();
		//List<WebElement> rowsList1 = gettablefrom.findElements(By.tagName("ul"));
		Thread.sleep(2000);
		 List<WebElement> columnsList11 = gettablefrom.findElements(By.tagName("li"));
		 Thread.sleep(2000);
		 columnsList11.get(10).click();
		Thread.sleep(2000);
		TO.click();
		//List<WebElement> rowsList1 = gettablefrom.findElements(By.tagName("ul"));
		Thread.sleep(2000);
		//location.click();
		WebElement loc = driver.findElement(By.xpath("//*[@id=\"dropdownGroup1\"]/div/ul[3]/li[1]/a"));
		Thread.sleep(2000);
		String javascript = "arguments[0].click()";      
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;        
		jsExecutor.executeScript(javascript, loc); 
		//WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul")));
		//List<WebElement> columnsList12 = gettableto.findElements(By.tagName("li"));
		//driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		Thread.sleep(2000);
		 
		 //columnsList12.get(1).click();
		//todes.click();
		//Date.click();
		
		Thread.sleep(2000);
		
		  List<WebElement> location1=date321.get(1).findElements(By.tagName("tr"));
		  Thread.sleep(2000);
		  List<WebElement>
		  location2=location1.get(2).findElements(By.tagName("td"));
		  location2.get(5).click();
		 
		//location.click();
		/*
		 * try { driver.findElement(By.xpath(
		 * "//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody/tr[5]/td[5]/a")).click();
		 * } catch (Exception e) { WebElement loc1 = driver.findElement(By.xpath(
		 * "//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody/tr[5]/td[5]/a"));
		 * Thread.sleep(2000); String javascript1 = "arguments[0].click()";
		 * JavascriptExecutor jsExecutor1 = (JavascriptExecutor) driver;
		 * jsExecutor1.executeScript(javascript1, loc1); }
		 */
		
		
		/*
		 * Actions act = new Actions(driver);
		 * act.moveToElement(driver.findElement(By.xpath(
		 * "//*[@id=\\\"ui-datepicker-div\\\"]/div[2]/table/tbody/tr[5]/td[5]/a"))).
		 * click().perform();
		 */
		
		  
		  
		 
	
		 
		 
		//date321.click();
		/*
		 * List<WebElement> rowsList = date123.findElements(By.tagName("tr"));
		 * Thread.sleep(2000); List<WebElement> columnsList =
		 * gettabledate.findElements(By.tagName("td")); //List<WebElement> columnsList =
		 * null; Thread.sleep(2000);
		 * 
		 * for (WebElement row : rowsList) { columnsList
		 * =row.findElements(By.tagName("td")); Thread.sleep(2000);
		 * System.out.println("colums:"+columnsList);
		 * 
		 * }
		 */
			 
	      // columnsList.get(10).click();
	       Thread.sleep(2000);
	       CheckboxStudent.get(0).click();
	       Thread.sleep(2000);
	      SearchButton.click();
	}



}
