package pom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import tests.TestBase;
import tests.Util;

public class Calling extends TestBase
{
	

	String URL="https://convirza.awsapps.com/auth/?client_id=06919f4fd8ed324e&redirect_uri=https%3A%2F%2Fconvirza.awsapps.com%2Fconnect%2Fauth%2Fcode";

    String user="ggarde";
    String pass="ChangeMe1";
    
    
    
	

	@FindBy(xpath="//input[@id='wdc_username']")
	private WebElement username;

	@FindBy(xpath="//input[@id='wdc_password']")
	private WebElement password;

	@FindBy(xpath="//button[@id='wdc_login_button']")
	private WebElement login_button;

	@FindBy(xpath="//span[contains(text(),'Dial number')]")
	private WebElement dial_button;

	@FindBy(xpath="//span[text()='Enter a phone number']/preceding-sibling::input")
	private WebElement number_textbox;

	@FindBy(xpath="//button[starts-with(@class,'SoftphoneButton')][2]")
	private WebElement dial;
	
	@FindBy(xpath="//span[text()='End call']/../..")
	private WebElement disconnect;
	
	@FindBy(xpath="//span[text()='Number pad']/../..")
	private WebElement number_pad;
    
	public WebDriverWait wait;	
//	WebDriver driver;
	
    public Calling(WebDriver driver,WebDriverWait wait1){
		
		PageFactory.initElements(driver, this);
        	this.wait=wait1;
	}

    
    public void launchAWS(String user,String pass) throws InterruptedException {
    	Thread.sleep(5000);

    	System.out.println("username "+username);    	
    	wait.until(ExpectedConditions.visibilityOf(username));
    	username.sendKeys(user);

    	wait.until(ExpectedConditions.visibilityOf(password));
    	password.sendKeys(pass);

    	wait.until(ExpectedConditions.visibilityOf(login_button));
    	login_button.click();
    	
    	try {
        	wait.until(ExpectedConditions.visibilityOf(number_pad));
        	number_pad.click();    		
    	}catch(Exception e) {
    		driver.navigate().refresh();
        	wait.until(ExpectedConditions.visibilityOf(number_pad));
        	number_pad.click();        		
    	}
    
    }
            
        public String call(String number) throws InterruptedException {
       		
//    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='a-box a-color-offset-background']"))));
    	
         	
       	 String call_started = Util.getDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "0");
       	 
       	wait.until(ExpectedConditions.visibilityOf(number_textbox)); 
       	number_textbox.sendKeys(number);
       	 
    	logger.log(LogStatus.INFO, "Phone number is "+number);
    	logger.log(LogStatus.INFO, "Ring to number is "+"8018786943");
    	
        dial.click();
        Thread.sleep(40000);

    	wait.until(ExpectedConditions.visibilityOf(disconnect));
 
    	disconnect.click();
    	Thread.sleep(40000);
        		
    	 String dbUrl="jdbc:postgresql://stag-5-pg-1.convirza.com:5432/ct_stg";
    	 String username="moentekdbrw";
    	 String password="hyPdua14GAu6";
    	 Connection connection=null;
    	 Statement stmpt=null;
    	    	 
    	 String query="SELECT * FROM call WHERE tracking='"+number+"' AND call_started>'"+call_started+"' ORDER BY call_started ASC LIMIT 1" ;
    	 
    	String call_id = null;
    	String disposition = null;
    	String duration = null;
		try{
			
			Class.forName("org.postgresql.Driver");
		   
			 connection = DriverManager.getConnection(dbUrl,username,password);
		    
			stmpt=connection.createStatement();
			
			ResultSet result = stmpt.executeQuery(query);
		
//			try {
//				call_id = result.getString("call_id");
//			}catch(Exception e) {
//				Assert.fail("No records found in db");
//				
//			}

				while(result.next()){
//					Array campaign_count = result.getArray("count");
					
						call_id = result.getString("call_id");
						 disposition = result.getString("disposition");
						 duration = result.getString("duration");

						 System.out.println(call_id);
						
						logger.log(LogStatus.PASS, "Call got conneccted successfully");
						logger.log(LogStatus.PASS, "Call entry is dispalyed in DB");
						logger.log(LogStatus.PASS, "Call_id is "+call_id);
						logger.log(LogStatus.PASS, "disposition is "+disposition);
						logger.log(LogStatus.PASS, "duration is "+duration);	
					
					
				}
	
		
		
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
    	return disposition;
    	
    	
    	
        }
        
        
        public String test12() {
        	
        	System.out.println("test");
        	String status="pass";
        	return status;
        	
        }
        
        
        
        
        
	}

	
    

	

