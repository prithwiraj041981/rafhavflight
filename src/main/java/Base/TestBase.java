package Base;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



import io.github.bonigarcia.wdm.WebDriverManager;

/* this code is developed by prithwiraj */
/* this class is developed for base information */
public class TestBase {
	

	public static WebDriver driver=null;
	public static Properties prop;

	public static String executionPlatform="";
	public static String browserName="";
	public static String exec_os="";
	static ArrayList<String> TestCases = new ArrayList<String>();
	static ArrayList<Integer> Res = new ArrayList<Integer>();
	public ExtentHtmlReporter htmlReporter;
	public com.aventstack.extentreports.ExtentReports reports;
	public static com.aventstack.extentreports.ExtentTest logger;
	String className = "";


	final long TIMEOUT = 30;
	

	@BeforeSuite
	public void CreateDir(){
	String filepath = System.getProperty("user.dir") + "\\Reports";
	File directory = new File(filepath);
	if (! directory.exists()){
	directory.mkdir();
	}
	}
	public TestBase() {
		/* this code is developed for ROC main properties */
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "datafile.properties" );
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	@BeforeClass()
	public void setupReport() throws Exception
	{   

		System.out.println("Before Class Setup Report Executing >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		className = this.getClass().getName();
		System.out.println("######### Starting Class Name Being Executed : "+ className );


		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh-mm-ss");		
//		htmlReporter= new ExtentSparkReporter(new File(System.getProperty("user.dir") +"/Reports/"+className+"-"+dateFormat.format(new Date())+".html"));
		htmlReporter= new ExtentHtmlReporter(new File(System.getProperty("user.dir") +"/Reports/"+className+"-"+dateFormat.format(new Date())+".html"));
	
		
		reports=new com.aventstack.extentreports.ExtentReports();
		reports.setSystemInfo("Environment", "Test Env");
		reports.setSystemInfo("Machine", 	"Windows 10" + "64 Bit");
		reports.setSystemInfo("Selenium", "3.4.0");
		reports.setSystemInfo("Maven", "4.0.0");
		reports.setSystemInfo("User Name", System.getProperty("user.name"));
		reports.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		reports.setSystemInfo("Java Version : ", System.getProperty("java.version"));
		reports.setSystemInfo("OS : ", System.getProperty("os.name"));
		reports.setSystemInfo("Browser : ", ""+prop.getProperty("browser")+"");
		reports.setSystemInfo("Class Name : ", className );
		reports.attachReporter(htmlReporter);
		
	}  
	
	@BeforeMethod()
	public void register(Method method)
	{
		System.out.println("Before Method Executing >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		String testname=method.getName();
		System.out.println("Current Test Name Being Executed is : "+ testname);
		logger=reports.createTest(testname);
	}
	
	@AfterMethod()
	public void getResult(ITestResult result) throws Exception
	{
		System.out.println("#################### After Method executing ################## ");
		
		Res.add(result.getStatus());
		
		System.out.println(">>>>>>>>" + Res);
		System.out.println(">>>>>>>> Status Code for Sucess : " + ITestResult.SUCCESS);
		System.out.println(">>>>>>>> Status Code for Failure : " + ITestResult.FAILURE);
		System.out.println(">>>>>>>> Status Code for Skip : " + ITestResult.SKIP);

		if (result.getStatus() == ITestResult.FAILURE)
		{
			String screenShotPath = TestBase.getScreenShot(driver, "FailedTestcaseScreenshot");            
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
			logger.fail(result.getThrowable());
			
			
			logger.fail("Test case FAILED due to below issues,Please find Snapshot as below: " + logger.addScreenCaptureFromPath(screenShotPath));
			TestCases.add(result.getName());
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
			TestCases.add(result.getName());
		}
		else
		{
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
			logger.skip(result.getThrowable());
			TestCases.add(result.getName());

		}
		reports.flush();

	}
	

	public void Base_url() throws IOException {

		/* this code is developed for Test Environment */
		if (executionPlatform.isBlank()) {
			String executionPlatform = prop.getProperty("exec_platform");
			System.out.println("Test is running on"+" "+executionPlatform);
		} 

		if (browserName.isBlank()) {
			browserName = prop.getProperty("browser");
			System.out.println("Test is running on"+" "+browserName);

		}

		if (exec_os.isBlank()) {
			exec_os = prop.getProperty("exec_OS");
			System.out.println("Test is running on"+" "+exec_os);


		}

		if (browserName.equalsIgnoreCase("Chrome") && exec_os.equalsIgnoreCase("windows")) {
			WebDriverManager.chromedriver().setup();


			driver = new ChromeDriver();

		}
		else if (browserName.equalsIgnoreCase("Firefox")&& exec_os.equalsIgnoreCase("windows")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
	}
	/* this code is developed for Taking Screenshot */
	public static String getScreenShot(WebDriver driver, String screenshotName) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh-mm-ss");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateFormat.format(new Date())+".png";

		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

		return destination;
	}
	
	@AfterClass
	public void tearDown(){
		System.out.println("#################### After Class executing ################## ");
		if(driver!=null)
		{
			System.out.println("Closing All Tabs for the Browser.");
			
			driver.close();
			driver.quit();
		}
	}


}
