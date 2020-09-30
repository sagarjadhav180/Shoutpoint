package extentReport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.api.client.googleapis.util.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class ExtentReportsGenerator {
	
	
	private static ExtentReports extent;

	public static ExtentReports getInstance(Boolean replace_existing)   {
		
		String environment = null;
		try{
		Properties property=new Properties();
		FileInputStream file=new FileInputStream(".//property");
		property.load(file);
		 environment = property.getProperty("Environment");
		}catch(Exception e){}
		

		
		if (extent == null) {
			if (replace_existing == true) {
				extent = new ExtentReports(".//Extent.html", true);
			} else {
				extent = new ExtentReports(".//Extent.html", false);
			}
//			logger = extent.startTest("");
			
			// optional added Build environment
			extent.addSystemInfo("Selenium Version", "3.11.0").addSystemInfo("Environment", "QA");
			extent.config().documentTitle("CFA_Automation_Test_Report").reportName("Automation Report -")
					.reportHeadline("Find Below test result").insertCustomStyles(".logger { border:2px solid #444; }");
            
			
			// Optional added system info
			extent.addSystemInfo("Selenium Version", "3.11.0");
			extent.addSystemInfo("Environment", environment);
			Map sysInfo = new HashMap();
			sysInfo.put("Selenium Version", "3.11.0");
			sysInfo.put("Environment", environment);
			extent.addSystemInfo(sysInfo);
			

		}
		 		

		
		
		return extent;

	}

	private static Object function() {
		// TODO Auto-generated method stub
		return null;
	}

}
