package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IClass;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.*;

import extentReport.*;
import pom.Calling;
import pom.CampaignAndTrackingNumberPage;
import pom.CampaignBuilderPage;
import pom.HomePage;
import testdata.TestData;

@SuppressWarnings("unused")
@Listeners(extentReport.Listener.class)
public class TestBase
{

	//Environment Variables
	public static final String billing_id="70045";
	public static final String account="Automation Account";
	public static final String campaignToBeEdited="00000SJC-1";
	public static final String tracking_number="SJ TN-1";
	public static final String geo_location="do_not_delete_location(automation)";
	public static final String webhook="automation_webhook_do_not_delete";	
	public static final String webhook_url="https://eneq4s0znwehc.x.pipedream.net/";	
	public static final String account_timezone="America/New_York";
	
	
	public static String org_unit_id="70045"; 
    public static String campaign_id = "46";
	public static String campaign_ou_id="70045";
	public static String first_last_name="Autmation Account";
	public static String first_name="Autmation";	
	public static String last_name="Account";
	public static String user_id="automation_account@yopmail.com";
	public static String password="lmc2demo";
	public static String env="stag-5";
	public static String Base_Url = "https://"+env+"-cmo-1.convirza.com";
	public static ExtentTest logger;
	public static ExtentReports extent = ExtentReportsGenerator.getInstance(true);
	public static WebDriver driver;
	public static  WebDriverWait wait;
	static int totalFailedTestCases;
	static int totalPassedTestCases;
	static int totalSkipedTestCases;

//	List<ITestNGMethod> passedtests = new ArrayList<ITestNGMethod>();
//	List<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();
//	List<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();
//	public static String methodName;
	String url_to_hit;
	
	@BeforeSuite
	public void testSetUp() throws Exception{
        
		Properties prop=new Properties();
		
		FileInputStream file=new FileInputStream(".//property");
		prop.load(file);
		String user=prop.getProperty("username");
		TestBase.setUser_id(user);
		String pass=prop.getProperty("password");
		TestBase.setPassword(pass);
		String environment = prop.getProperty("Environment");
		TestBase.setEnv(environment);

		
	}
	

	@BeforeTest(alwaysRun=true)
	@Parameters({"browser","user","pass"})
	public void setUp(String browser,String user,String pass) throws Exception{
		
//		url_to_hit=url;
		String sf = null;
		String sc = null ;
		if(browser.contains("chrome")){

			System.setProperty("webdriver.chrome.driver",".//chromedriver_linux");
		    driver=new ChromeDriver();
			 
		    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			SessionId session = ((ChromeDriver)driver).getSessionId();
			 sc = session.toString();
			System.out.println("Session id of ChromeDriver: " + session.toString());
		    
			String URL="https://convirza.awsapps.com/connect/ccp-v2/softphone";
//		    driver.get(url_to_hit);
			driver.get(URL);
//		    TestData.createData();			    
			extent.loadConfig(new File(".//src//main//java//extentReport//extent_config.xml"));
			AWSCallingClass cl=new AWSCallingClass();
			cl.setUPAWS(user, pass);
			
		}
		else if(browser.contains("firefox")){
			System.setProperty("webdriver.gecko.driver", ".//geckodriver.exe");
			driver=new FirefoxDriver();
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		    
		}
		 else if (browser.equals("headless")) {
			
			 String chromeDriverPath = ".//chromedriver" ;
			 System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			 ChromeOptions options = new ChromeOptions();
			 options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
			 driver = new ChromeDriver(options);
			 extent.loadConfig(new File(".//src//main//java//extentReport//extent_config.xml"));
			 AWSCallingClass cl=new AWSCallingClass();
			 cl.setUPAWS(user, pass);
			}

		wait= new WebDriverWait(driver,60);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//to delete cookies
	    driver.manage().deleteAllCookies();
//		String URL="https://convirza.awsapps.com/connect/ccp-v2/softphone";
//	    driver.get(url_to_hit);
//		driver.get(URL);
//	    TestData.createData();			    
//		extent.loadConfig(new File(".//src//main//java//extentReport//extent_config.xml"));
//		Calling cl=new Calling(driver,wait);
//		cl.launchAWS(user,pass);
	
	}

//	@Parameters({"user","pass"})
//	@BeforeTest
//	public void loginAWS(String user,String pass) throws InterruptedException {
//		Calling cl=new Calling(driver,wait);
//		cl.launchAWS(user,pass);
//	}

	
	
//	@AfterMethod
//	public void tearDown(ITestResult result) throws Exception {
//
//		if (result.getStatus() == ITestResult.FAILURE) {
//
//			try {
//				System.out.println("In fail");
//				Thread.sleep(1200);
//				failedtests.add(result.getMethod());
//				logger.log(LogStatus.FAIL, "" + result.getThrowable().getMessage() + "");
//				logger.log(LogStatus.FAIL, "Failed.");
//				methodName = result.getName();
//				String image_path = Util.createScreenshot(driver, methodName);
//				String img = Util.image_upload(image_path);
//				System.out.println("Failure Method" + methodName);
//				logger.log(LogStatus.INFO, "Snapshot below: " + logger.addScreenCapture(img));
//				
////				driver.findElement(By.xpath("//div/nav/div/ul/li/a/span")).click();
////				Thread.sleep(2000);
//				Util.getJavascriptExecutor().executeScript("window.scrollBy(0,-2000)");	
//				if(methodName.startsWith("campaign")){
//					driver.navigate().refresh();
//					
//					Thread.sleep(3000);
//					HomePage hp=new HomePage(driver);
//					hp.left_hand_navigation_bar_click();
//					CampaignBuilderPage ct=new CampaignBuilderPage(driver,wait);
//				    ct.clickAction("list");
//				}
//				else if(result.getTestClass().getRealClass().getSimpleName().startsWith("Looker")){
//					
//				}
//				else{
//					driver.navigate().refresh();
//					
//					Thread.sleep(3000);
//					HomePage hp=new HomePage(driver);
//					hp.left_hand_navigation_bar_click();
//				}
//				
//			} catch (Exception e) {
//				System.out.println("In Catch");
//				e.printStackTrace();
//			}
//
//		}
//
//		else if (result.getStatus() == ITestResult.SUCCESS) {
//			System.out.println("In Pass");
//			logger.log(LogStatus.PASS, "Success.");
//			passedtests.add(result.getMethod());
//		}
//
//		else {
//			System.out.println("In skip");
//			logger.log(LogStatus.SKIP, "Test case skipped.");
//			skippedtests.add(result.getMethod());
//
//		}
//		extent.endTest(logger);
//		extent.flush();
//
//	}

	public static String getUser_id() {
		return user_id;
	}

	public static void setUser_id(String user_id) {
		TestBase.user_id = user_id;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		TestBase.password = password;
	}

	public static String getFirst_last_name() {
		return first_last_name;
	}

	public static void setFirst_last_name(String first_last_name) {
		TestBase.first_last_name = first_last_name;
	}

	public static String getFirst_name() {
		return first_name;
	}

	public static void setFirst_name(String first_name) {
		TestBase.first_name = first_name;
	}

	public static String getLast_name() {
		return last_name;
	}

	public static void setLast_name(String last_name) {
		TestBase.last_name = last_name;
	}

	
	public static String getEnv() {
		return env;
	}

	public static void setEnv(String env) {
		TestBase.env = env;
	}
	
	public static String getOrg_unit_id() {
		return org_unit_id;
	}

	public static void setOrg_unit_id(String org_unit_id) {
		TestBase.org_unit_id = org_unit_id;
	}

	public static String getCampaign_id() {
		return campaign_id;
	}

	public static void setCampaign_id(String campaign_id) {
		TestBase.campaign_id = campaign_id;
	}

	public static String getCampaign_ou_id() {
		return campaign_ou_id;
	}

	public static void setCampaign_ou_id(String campaign_ou_id) {
		TestBase.campaign_ou_id = campaign_ou_id;
	}

	@AfterTest
	public void tearDown() throws Exception{
		if(driver!=null){
		driver.quit();
		}
	}
	

	@AfterSuite
	public void close_connection() throws Exception {

		int[] result = extentReport.Listener.count_of_test();
		totalPassedTestCases = result[0];
		totalFailedTestCases = result[1];
		totalSkipedTestCases = result[2];
		SendEmail.execute("Extent.html", totalFailedTestCases, totalPassedTestCases, totalSkipedTestCases);
		driver.quit();
	}

}

