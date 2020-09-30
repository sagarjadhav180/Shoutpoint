package tests;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pom.Calling;

public class AWSCallingClass extends TestBase{
	
	String number="3018934412";
	String number2="2403219586";
	String disposition;
	int count=0; 
	
//	@Parameters({"user","pass"})
//	@BeforeClass
	public void setUPAWS(String user,String pass) throws InterruptedException {
		
		String URL="https://convirza.awsapps.com/auth/?client_id=06919f4fd8ed324e&redirect_uri=https%3A%2F%2Fconvirza.awsapps.com%2Fconnect%2Fauth%2Fcode";
		Calling cl=new Calling(driver,wait);
		cl.launchAWS(user,pass);
	}
	
	
	@Test(threadPoolSize=1,invocationCount = 2)
	public void shoutPointNumberCall() throws InterruptedException {
		
	
		String str="Verifying if call is getting through and entry of the same in DB";
		logger=extent.startTest("Call Test"); 
        logger.log(LogStatus.INFO, str.toUpperCase());
    
		Calling cl=new Calling(driver,wait);
		disposition=cl.call(number);
		if(!disposition.equals("ANSWERED") && count<2) {
			count++;
			shoutPointNumberCall();			
		}
		
	}
	
	@Test(threadPoolSize=1,invocationCount = 2)
	public void sipNumberCall() throws InterruptedException {
		
	
		String str="Verifying if call is getting through and entry of the same in DB";
		logger=extent.startTest("Call Test"); 
        logger.log(LogStatus.INFO, str.toUpperCase());
    
		Calling cl=new Calling(driver,wait);
		disposition=cl.call(number);
		if(!disposition.equals("ANSWERED") && count<2) {
			count++;
			shoutPointNumberCall();			
		}
	}

//	@Test(priority=3)
	public void callSourceNumberCall() throws InterruptedException {
		
		String str="Verifying if call is getting through and entry of the same in DB";
		logger=extent.startTest("Call Test"); 
        logger.log(LogStatus.INFO, str.toUpperCase());
    
		Calling cl=new Calling(driver,wait);
		

		String tc_status = cl.call(number);
		
        if(!tc_status.equals("pass") && count<2) {
            count++;

            callSourceNumberCall();

        }
		
	}
	

//	@Test
	public void testForMail() {
		logger=extent.startTest("Call Mail Test"); 
		
		
		Calling cl=new Calling(driver,wait);
		
		String tc_status = cl.test12();
		
        if(!tc_status.equals("pass") && count<2) {
            count++;

            testForMail();

        }

        
	}
}
