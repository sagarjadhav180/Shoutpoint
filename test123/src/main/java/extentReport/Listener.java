package extentReport;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import bsh.util.Util;
import pom.CampaignBuilderPage;
import pom.HomePage;
import tests.*;


	public class Listener extends TestBase implements ITestListener  {
		static List<ITestNGMethod> passedtests = new ArrayList<ITestNGMethod>();
		static List<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();
		static List<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();

		public static String methodName;
		
		public void onFinish(ITestContext arg0) {
			// TODO Auto-generated method stub

		}


		public void onStart(ITestContext arg0) {
			// TODO Auto-generated method stub

		}


		public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
			// TODO Auto-generated method stub

		}

		public void onTestFailure(ITestResult result) {
			// TODO Auto-generated method stub
			failedtests.add(result.getMethod());
			System.out.println("Failed:" + result.getMethod());
			
			if (result.getStatus() == ITestResult.FAILURE) {

				try {
					System.out.println("In fail");
					Thread.sleep(1200);
					logger.log(LogStatus.FAIL, "" + result.getThrowable().getMessage() + "");
					logger.log(LogStatus.FAIL, "Failed.");
					methodName = result.getName();
//					String image_path = Util.createScreenshot(driver, methodName);
//					String img = Util.image_upload(image_path);
					System.out.println("Failure Method" + methodName);
//					logger.log(LogStatus.INFO, "Snapshot below: " + logger.addScreenCapture(img));
					
//					driver.findElement(By.xpath("//div/nav/div/ul/li/a/span")).click();
//					Thread.sleep(2000);
//					Util.getJavascriptExecutor().executeScript("window.scrollBy(0,-2000)");	
					if(methodName.startsWith("campaign")){
						driver.navigate().refresh();
						
						Thread.sleep(3000);
						HomePage hp=new HomePage(driver);
						hp.left_hand_navigation_bar_click();
						CampaignBuilderPage ct=new CampaignBuilderPage(driver,wait);
					    ct.clickAction("list");
					}
					else if(result.getTestClass().getRealClass().getSimpleName().startsWith("Looker") || result.getTestClass().getRealClass().getSimpleName().startsWith("GroupsAndUser")){
						
					}
					else{
						driver.navigate().refresh();
						
						Thread.sleep(3000);
						HomePage hp=new HomePage(driver);
						hp.left_hand_navigation_bar_click();
					}
					
				} catch (Exception e) {
					System.out.println("In Catch");
					e.printStackTrace();
				}

			}

			extent.endTest(logger);
			extent.flush();
		}

		public void onTestSkipped(ITestResult arg0) {
			// TODO Auto-generated method stub
			skippedtests.add(arg0.getMethod());
			System.out.println("Skipped:" + arg0.getMethod());
			System.out.println("Skipped:" + arg0.getMethod().getMethodName());
			System.out.println("Failed script name is" + methodName);
			logger = extent.startTest(arg0.getMethod().getMethodName());
			System.out.println("Current status is...... " + logger.getRunStatus());
			logger.log(LogStatus.SKIP, "This method is get skipped because the " + "<span class='red label'>" +methodName + "</span>"+" test case got failed.");
			logger.log(LogStatus.SKIP, arg0.getMethod().getMethodName());
			extent.endTest(logger);
			extent.flush();
		}

		public void onTestStart(ITestResult arg0) {
			// TODO Auto-generated method stub

		}

		public void onTestSuccess(ITestResult arg0) {
			// TODO Auto-generated method stub
			logger.log(LogStatus.PASS, "Success.");
			passedtests.add(arg0.getMethod());
			System.out.println("Passed:" + arg0.getMethod());
			extent.endTest(logger);
			extent.flush();
		}

		public static int[] count_of_test() {
			int[] result = { passedtests.size(), failedtests.size(), skippedtests.size() };
			return result;
		}
		// This belongs to ISuiteListener and will execute before the Suite start
	}

	

