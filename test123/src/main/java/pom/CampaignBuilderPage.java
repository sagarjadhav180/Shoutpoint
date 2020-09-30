package pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import tests.TestBase;
import tests.Util;

public class CampaignBuilderPage extends TestBase {

	//labels
	String[] all_labels={"Campaign Name","Campaign External ID","Campaign Owner","Active?","Start Date and Time (ET)","End Date and Time (ET)"};
	static String label_name;
	
	@FindBy(xpath="(//div[ @class='form-group row'])[1]/div/div/div/label")
	private static List<WebElement> labels;		

	//header 
	@FindBy(xpath="//h1[contains(text(),'Campaign Builder')]")
	private static WebElement header;
	
	@FindBy(xpath="//h4[contains(text(),'"+account+"')]")
	private static WebElement collapsible_strip;
	
	//active/inactive toggle
	@FindBy(xpath="(//span[@class='knob ng-binding ng-scope'])[2]")
	private static WebElement active_InactiveCampaign_Button;

	@FindBy(xpath="//div[@id='CSSActive']//div[@class='switch-animate switch-off']")
	private static WebElement campaign_status_inactive;

	@FindBy(xpath="//div[@id='CSSActive']//div[@class='switch-animate switch-on']")
	private static WebElement campaign_status_active;
	
	@FindBy(xpath="(//span[@class='switch-right ng-binding ng-scope' and contains(text(),'No')])[2]")
	private static WebElement NO;	

	@FindBy(xpath="(//span[@class='switch-left ng-binding ng-scope' and contains(text(),'Yes')])[2]")
	private static WebElement Yes;

	@FindBy(xpath="//button[contains(text(),'OK')]")
	private static WebElement Ok_button_campaign_deactivate_confirmation_msg;
	
	@FindBy(xpath="//label[contains(text(),'Campaign Owner')]/..//following-sibling::div//span[contains(@class,'select2-chosen')]")
	private static WebElement campaignOwner_DropDown;	
	
	
	@FindBy(xpath="//input[@id='s2id_autogen2_search']")
	private static WebElement campaignOwner_TextBox;	
	
	@FindBy(xpath="//li/ul/li[1]/div")
	private static WebElement Searched_CampaignOwner;	
	
	@FindBy(id="s2id_autogen4")
	private static WebElement assignUser_Section;		
		
	String campaignName_TextBox_placeholder="Type Campaign Name";
	@FindBy(xpath="//label[contains(text(),'Campaign Name')]/..//following-sibling::div//input")
	private static WebElement campaignName_TextBox;

	String campaignID_TextBox_placeholder="Type Campaign External ID (Optional)";
	@FindBy(xpath="//input[@placeholder='Type Campaign External ID (Optional)']")
	private static WebElement campaignID_TextBox;

	
    //calendar
	@FindBy(xpath="(//md-input-container/button)[1]")
	private static WebElement startDate_Calender;	
	
	@FindBy(xpath="(//md-input-container/button)[2]")
	private static WebElement endDate_Calender;
	
	@FindBy(xpath="(//sm-date-picker[@id='Start DatePicker']//div//div[@class='date-picker']/div[2]/div/button[contains(@class,'md-icon-button scroll-button')])[1]")
	private static WebElement startDateCalender_leftArrow;
	
	@FindBy(xpath="(//sm-date-picker[@id='Start DatePicker']//div//div[@class='date-picker']/div[2]/div/button[contains(@class,'md-icon-button scroll-button')])[2]")
	private static WebElement StartCalender_rightArrow;

	@FindBy(xpath="(//sm-date-picker[@id='End DatePicker']//div//div[@class='date-picker']/div[2]/div/button[contains(@class,'md-icon-button scroll-button')])[1]")
	private static WebElement endDateCalender_leftArrow;
	
	@FindBy(xpath="(//sm-date-picker[@id='End DatePicker']//div//div[@class='date-picker']/div[2]/div/button[contains(@class,'md-icon-button scroll-button')])[2]")
	private static WebElement endCalender_rightArrow;
	
	@FindBy(xpath="(//*[@id='Start DatePicker']/div/md-content/div/div[3]/button)[1]")
	private static WebElement startdatePicker_cancel_Button;	

	@FindBy(xpath="(//*[@id='Start DatePicker']/div/md-content/div/div[3]/button)[2]")
	private static WebElement startdatePicker_ok_Button;

	@FindBy(xpath="(//*[@id='End DatePicker']/div/md-content/div/div[3]/button)[2]")
	private static WebElement enddatePicker_ok_Button;

	@FindBy(xpath="(//*[@id='End DatePicker']/div/md-content/div/div[3]/button)[1]")
	private static WebElement enddatePicker_cancel_Button;
	
	@FindBy(xpath="//button[contains(text(),'Save Campaign Details')]")
	private static WebElement SaveCampaignDetails_Button;	

	@FindBy(xpath="//div[@class='ui-pnotify-text']")
	private static WebElement campaignCreatedSuccessfully_Message;
	
	@FindBy(xpath="//*[contains(text(), 'Successfully updated campaign')]")
	private static WebElement campaignUpdated_SuccessfullyMessage;	
	
	@FindBy(xpath="//a[@class='btn btn-default hidden-xs']")
	private static WebElement campaignList;		
	
	@FindBy(xpath="//div[@class='alert ui-pnotify-container alert-success ui-pnotify-shadow']/div[contains(text(),'Campaign created successfully')]")
	private static WebElement createCampaign_success_message;		

	@FindBy(xpath="//div[@class='ui-pnotify-text' and contains(text(),'Campaign updated successfully.')]")
	private static WebElement updateCampaign_success_message;	
			
	@FindBy(xpath="//div[@class='ui-pnotify-text'][contains(text(),'Campaign start date should not be before current time')]")
	private static WebElement alertForPastDateCampaign;	
			

	@FindBy(xpath="//button[@id='_pendo-close-guide_']")
	private WebElement pendo_close_button;	

	@FindBy(xpath="//tbody[@ id='progressLoader']")
	private WebElement loading_wheel_for_list;

	@FindBy(xpath="/html/body/div[4]/div/div[1]")
	private WebElement campaign_success_msg_close_button;

	@FindBy(xpath="/html/body/div[4]/div/div[2]")
	private WebElement campaign_success_msg_pause_button;
	
	
	@FindBy(xpath="//div[@class='guide-header']")
	private WebElement pendo_popup;	
	WebDriverWait wait;
	SoftAssert Assert1=new SoftAssert();
	
	
	
	public CampaignBuilderPage(WebDriver driver,WebDriverWait wait1){
		PageFactory.initElements(driver, this);
		wait=wait1;
	}
	
	
	
	public void clickAction(String buttonName){
		if(buttonName.contains("list")){
			wait.until(ExpectedConditions.elementToBeClickable(campaignList));
			tests.Util.click(campaignList);
			try{
			wait.until(ExpectedConditions.invisibilityOf(loading_wheel_for_list));
			}catch(Exception e){}
		}
		else if(buttonName.contains("list")){
			
		}
	}
	
	
	
	public void campaignBuilderPageUIVerification() throws InterruptedException{
		
		//Verifying if campaignList button present at top right corner is displayed and its enabled
		logger.log(LogStatus.INFO, "verification of campaignList");
		wait.until(ExpectedConditions.visibilityOf(campaignList));
		Assert1.assertTrue(campaignList.isDisplayed(),"campaignList is not displayed or locator changed");
		logger.log(LogStatus.INFO, "verifying if campaignList button is enabled..");
		wait.until(ExpectedConditions.visibilityOf(campaignList));
		Assert1.assertTrue(campaignList.isDisplayed(),"campaignList button is not enabled");
		
		
		//verification of header
		logger.log(LogStatus.INFO, "verification of header");
		wait.until(ExpectedConditions.visibilityOf(header));
		Assert1.assertTrue(header.isDisplayed(),"header is not displayed or locator changed");
		
		//verifying if collapsible_strip is displayed
		logger.log(LogStatus.INFO, "verification of collapsible_strip");
		Assert1.assertTrue(collapsible_strip.isDisplayed(),"collapsible_strip is not displayed or locator changed");

		//verifying if collapsible_strip is enabled
		logger.log(LogStatus.INFO, "verifying if collapsible_strip is enabled");
		Assert1.assertTrue(collapsible_strip.isEnabled(),"collapsible_strip is not enabled");
		
		//verification of labels
		
		for(int i=0;i<all_labels.length;){

			label_name=all_labels[i];
			for(int j=0;j<labels.size()-2;j++){
				System.out.println("expected label is-- "+all_labels[i]);
				System.out.println("actual label is-- "+labels.get(j).getText());
				logger.log(LogStatus.INFO, "verifying if "+all_labels[i]+" is present..");
				Assert1.assertTrue(labels.get(j).getText().equals(all_labels[i])," label-- "+all_labels[i]+" is not displayed or locator changed");
				i++;
			}
			
		}

		//verifying if active/inactive toggle button is displayed and its enabled
		logger.log(LogStatus.INFO, "verifying if active/inactive toggle button is displayed");				
		Assert1.assertTrue(active_InactiveCampaign_Button.isDisplayed(),"active_InactiveCampaign_Button is not displayed or locator changed");
		logger.log(LogStatus.INFO, "verifying if active/inactive toggle button is enabled");	
		Assert1.assertTrue(active_InactiveCampaign_Button.isEnabled(),"active_InactiveCampaign_Button is not enabled");
		
		//verifying if campaignOwner_DropDown is displayed and its enabled 
		wait.until(ExpectedConditions.visibilityOf(campaignOwner_DropDown));
		logger.log(LogStatus.INFO, "verifying if campaignOwner_DropDown is displayed");
		Assert1.assertTrue(campaignOwner_DropDown.isDisplayed(),"campaignOwner_DropDown is not displayed or locator changed");
		logger.log(LogStatus.INFO, "verifying if campaignOwner_DropDown is enabled");
		Assert1.assertTrue(campaignOwner_DropDown.isEnabled(),"campaignOwner_DropDown is not enabled()");
		
		//verifying if campaignName_TextBox is present
		logger.log(LogStatus.INFO, "verifying if campaignName_TextBox is present");
		Assert1.assertTrue(campaignName_TextBox.isDisplayed(),"campaignName_TextBox is not displayed or locator changed");
		
		//verifying if campaignName_TextBox placeholder is present
		logger.log(LogStatus.INFO, "verifying if campaignName_TextBox placeholder is present");
		Assert1.assertEquals(campaignName_TextBox.getAttribute("placeholder"), campaignName_TextBox_placeholder,"campaignName_TextBox_placeholder is not displayed or locator changed");
	
		//verifying if campaignID_TextBox is displayed
		logger.log(LogStatus.INFO,"verifying if campaignID_TextBox is displayed");
		Assert1.assertTrue(campaignID_TextBox.isDisplayed(),"campaignID_TextBox is not displayed or locator changed");
		
		//verifying if campaignID_TextBox placeholder
		logger.log(LogStatus.INFO, "verifying if campaignID_TextBox placeholder");
		Assert1.assertEquals(campaignID_TextBox.getAttribute("placeholder"), campaignID_TextBox_placeholder,"campaignID_TextBox_placeholder is not displayed or locator changed");
		
		//verifying if startDate_Calender is displayed and its enabled
		logger.log(LogStatus.INFO, "verifying if startDate_Calender is displayed..");
		Assert1.assertTrue(startDate_Calender.isDisplayed(),"startDate_Calender is not displayed or locator changed");
		logger.log(LogStatus.INFO, "verifying if startDate_Calender is enabled..");
		Assert1.assertTrue(startDate_Calender.isEnabled(),"startDate_Calender is not enabled");
		
		//verifying if endDate_Calender is displayed and its enabled
		logger.log(LogStatus.INFO, "verifying if endDate_Calender is displayed");
		Assert1.assertTrue(endDate_Calender.isDisplayed(),"endDate_Calender is not displayed or locator changed");
		logger.log(LogStatus.INFO, "verifying if endDate_Calender is enabled");
		Assert1.assertTrue(endDate_Calender.isEnabled(),"endDate_Calender is not enabled");
		Assert1.assertAll();
		
	}
	
	public void collapseExpand_collapsible_strip(){
		wait.until(ExpectedConditions.visibilityOf(collapsible_strip));
		
		try{
			collapsible_strip.click();
			Assert1.assertFalse(SaveCampaignDetails_Button.isDisplayed(),"SaveCampaignDetails_Button is not displayed or locator changed");
			collapsible_strip.click();			
			Assert1.assertTrue(SaveCampaignDetails_Button.isDisplayed(),"SaveCampaignDetails_Button is not displayed or locator changed");
		}
		catch(Exception e){
			System.out.println("collapsible_strip is not working..");
		}
		Assert1.assertAll();
	}
	

	public void defaultCampaignOwner() throws IOException, InterruptedException{
		
		wait.until(ExpectedConditions.visibilityOf(campaignOwner_DropDown));
		String actual_campaign_owner = campaignOwner_DropDown.getText();
        
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream(".//property");
		prop.load(file);
		String expected_username=prop.getProperty("username");
		
		logger.log(LogStatus.INFO, "Verifying if logged in user is displayed by defeult in campaign owner dropdown");
		Assert1.assertEquals(actual_campaign_owner, expected_username,"logged in user is displayed by defeult in campaign owner dropdown");
		Assert1.assertAll();
		Thread.sleep(1000);
	}
	

	public void create(String campaign_name,String externalID,int futureStartDate,int futureEndDate) throws InterruptedException{
		
        wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
		wait.until(ExpectedConditions.attributeContains(campaignName_TextBox, "tabindex", "0"));

		campaignName_TextBox.clear();
        campaignName_TextBox.sendKeys(campaign_name);
        
        if(!(externalID=="")){
            campaignID_TextBox.clear();
            campaignID_TextBox.sendKeys(externalID);
        
        
        if(!(futureStartDate==0)){
        	startDate_Calender.click();
    		
    		WebElement element;
    		int startDate;
    		String startDateToBeSelected;
    		if(futureStartDate<26){
    		startDate =futureStartDate+1;
    		startDateToBeSelected=String.valueOf(startDate);
    		element=driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
    		
    		
    		}else {
                Util.Action().moveToElement(StartCalender_rightArrow).click().perform();
                startDate=2;
                startDateToBeSelected=String.valueOf(startDate);
    			element = driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
    		}
    		Util.Action().moveToElement(element).click().perform();
    		Util.Action().moveToElement(startdatePicker_ok_Button).click().perform();
        }
        	
        }
		
        if(!(futureEndDate==0)){
        	wait.until(ExpectedConditions.visibilityOf(endDate_Calender));
    		endDate_Calender.click();
    		
    		WebElement element1;
    		int endDate = 0;
    		String endDateToBeSelected;
    		if(futureEndDate<26){
    		endDate =futureEndDate+2;
    		endDateToBeSelected=String.valueOf(endDate);
    		element1=driver.findElement(By.xpath("(//sm-date-picker[@id='End DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+endDateToBeSelected+"])"));
    		
    		
    		}else {
                Util.Action().moveToElement(endCalender_rightArrow).click().perform();
                endDate=3;
                endDateToBeSelected=String.valueOf(endDate);
    			element1 = driver.findElement(By.xpath("(//sm-date-picker[@id='End DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+endDateToBeSelected+"])"));
    		}
    		Util.Action().moveToElement(element1).click().perform();
    		Util.Action().moveToElement(enddatePicker_ok_Button).click().perform();
        	
        }
		
		wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
		SaveCampaignDetails_Button.click();
		wait.until(ExpectedConditions.visibilityOf(createCampaign_success_message));
		
		Assert1.assertTrue(createCampaign_success_message.isDisplayed(),campaign_name+" campaign not created");
		Assert1.assertAll();
		Thread.sleep(1000);
				
	}
	
	

	public void edit(String campaign_name_updated) throws InterruptedException{
			
		wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));

		Thread.sleep(3000);
		System.out.println(campaignName_TextBox);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+campaign_name_updated+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		campaignName_TextBox.clear();
		campaignName_TextBox.sendKeys(campaign_name_updated);
		
//		try{
//			campaign_status_active.isDisplayed();
//		}
//		catch(Exception e){
//			active_InactiveCampaign_Button.click();
//		}
			 			
		wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
		SaveCampaignDetails_Button.click();
			
		wait.until(ExpectedConditions.visibilityOf(updateCampaign_success_message));
		Assert1.assertTrue(updateCampaign_success_message.isDisplayed(),"campaign not updated ");
		Assert1.assertAll();
	
		Thread.sleep(1000);
					
	}

	
	
	
	
	
	//to verify campaign is getting created without external id
//	public void createCampaign(String campaign_name) throws InterruptedException{
//		
//		wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
//		
//		wait.until(ExpectedConditions.elementToBeClickable(campaignName_TextBox));
//		campaignName_TextBox.clear();
//		campaignName_TextBox.sendKeys(campaign_name);
//		wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
//		SaveCampaignDetails_Button.click();
//		
//		wait.until(ExpectedConditions.visibilityOf(createCampaign_success_message));
//		Assert1.assertTrue(createCampaign_success_message.isDisplayed(),campaign_name+" campaign not created");
//
//		Assert1.assertAll();
////		driver.switchTo().activeElement();
////		Util.Action().moveToElement(campaign_success_msg_pause_button).click().perform();
////		Util.Action().moveToElement(campaign_success_msg_close_button).click().perform();
//		
//		
//		Thread.sleep(1000);
//
//	}
//	
//	
//	
//	
//	//to verify campaign is getting created with external id
//		public void createCampaign(String campaign_name,String externalID) throws InterruptedException{
//			
//
//			wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
//			
//			campaignName_TextBox.clear();
//			campaignName_TextBox.sendKeys(campaign_name);
//			campaignID_TextBox.clear();
//			campaignID_TextBox.sendKeys(externalID);
//			wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
//			SaveCampaignDetails_Button.click();
//			
//			wait.until(ExpectedConditions.visibilityOf(createCampaign_success_message));
//			Assert1.assertTrue(createCampaign_success_message.isDisplayed(),campaign_name+" campaign not created");
//			Assert1.assertAll();
////			driver.switchTo().activeElement();
////			campaign_success_msg_pause_button.click();
////			campaign_success_msg_close_button.click();
//			
//			Thread.sleep(1000);
//					
//		}
//		
//		//createCampaignWithFutureStartDateAndNeverEndDate
//		public void createCampaign(String campaign_name,String externalID,int futureStartDate) throws InterruptedException{
//			
//            wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
//            wait.until(ExpectedConditions.elementToBeClickable(campaignName_TextBox));
//            campaignName_TextBox.clear();
//            campaignName_TextBox.sendKeys(campaign_name);
//            campaignID_TextBox.clear();
//            campaignID_TextBox.sendKeys(externalID);
//			startDate_Calender.click();
//			WebElement element;
//			int startdate;
//			String startDateToBeSelected;
//			if(futureStartDate<26){
//			startdate =futureStartDate+2;
//			startDateToBeSelected=String.valueOf(startdate);
//			element=driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
//			
//			}else {
//                Util.Action().moveToElement(StartCalender_rightArrow).click().perform();
//                startdate=2;
//				startDateToBeSelected=String.valueOf(startdate);
//				element = driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
//			}
//			Util.Action().moveToElement(element).click().perform();
//			Util.Action().moveToElement(startdatePicker_ok_Button).click().perform();
//			wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
//			SaveCampaignDetails_Button.click();
//			
//			wait.until(ExpectedConditions.visibilityOf(createCampaign_success_message));
//			Assert1.assertTrue(createCampaign_success_message.isDisplayed(),campaign_name+" campaign not created");
//			Assert1.assertAll();
////			driver.switchTo().activeElement();
////			campaign_success_msg_pause_button.click();
////			campaign_success_msg_close_button.click();
//			
//			Thread.sleep(1000);
//					
//		}
//		
//		
//		//updateCampaignWithFutureStartDateAndNeverEndDate
//            public void EditCampaign(String campaign_name_updated,String externalID,int futureStartDate) throws InterruptedException{
//			
//
//			wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
//
//			campaignName_TextBox.clear();
//			campaignName_TextBox.sendKeys(campaign_name_updated);
//			campaignID_TextBox.clear();
//			campaignID_TextBox.sendKeys(externalID);
//			active_InactiveCampaign_Button.click();
//			startDate_Calender.click();
//			WebElement element;
//			int startdate = 0;
//			String dateToBeSelected;
//			String startDateToBeSelected=String.valueOf(startdate);
//			startdate =futureStartDate;
//			
//			if(futureStartDate<26){
//				startdate =futureStartDate+3;
//				startDateToBeSelected=String.valueOf(startdate);
//				element=driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
//				
//				}else {
//	                Util.Action().moveToElement(StartCalender_rightArrow).click().perform();
//	                startdate=3;
//					startDateToBeSelected=String.valueOf(startdate);
//					element = driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
//				}
//			
//			Util.Action().moveToElement(element).click().perform();
//			Util.Action().moveToElement(startdatePicker_ok_Button).click().perform();
//            
//			
//			wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
//			SaveCampaignDetails_Button.click();
//			
//			wait.until(ExpectedConditions.visibilityOf(updateCampaign_success_message));
//			Assert1.assertTrue(updateCampaign_success_message.isDisplayed(),"campaign not updated ");
//			Assert1.assertAll();
////			driver.switchTo().activeElement();
////			campaign_success_msg_pause_button.click();
////			campaign_success_msg_close_button.click();
//			
//			Thread.sleep(1000);
//					
//		}
//
//		
//		
//		//createCampaignWithFutureStartDateAndFutureEndDate
//		public void createCampaign(String campaign_name,String externalID,int futureStartDate,int futureEndDate) throws InterruptedException{
//			
//            wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
//            campaignName_TextBox.clear();
//            campaignName_TextBox.sendKeys(campaign_name);
//            campaignID_TextBox.clear();
//            campaignID_TextBox.sendKeys(externalID);
//			startDate_Calender.click();
//			
//			WebElement element;
//			int startDate;
//			String startDateToBeSelected;
//			if(futureStartDate<26){
//			startDate =futureStartDate+1;
//			startDateToBeSelected=String.valueOf(startDate);
//			element=driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
//			
//			
//			}else {
//                Util.Action().moveToElement(StartCalender_rightArrow).click().perform();
//                startDate=2;
//                startDateToBeSelected=String.valueOf(startDate);
//				element = driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
//			}
//			Util.Action().moveToElement(element).click().perform();
//			Util.Action().moveToElement(startdatePicker_ok_Button).click().perform();
//			
//			wait.until(ExpectedConditions.visibilityOf(endDate_Calender));
//			endDate_Calender.click();
//			
//			WebElement element1;
//			int endDate = 0;
//			String endDateToBeSelected;
//			if(futureEndDate<26){
//			endDate =futureEndDate+2;
//			endDateToBeSelected=String.valueOf(endDate);
//			element1=driver.findElement(By.xpath("(//sm-date-picker[@id='End DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+endDateToBeSelected+"])"));
//			
//			
//			}else {
//                Util.Action().moveToElement(endCalender_rightArrow).click().perform();
//                endDate=3;
//                endDateToBeSelected=String.valueOf(endDate);
//				element1 = driver.findElement(By.xpath("(//sm-date-picker[@id='End DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+endDateToBeSelected+"])"));
//			}
//			Util.Action().moveToElement(element1).click().perform();
//			Util.Action().moveToElement(enddatePicker_ok_Button).click().perform();
//			
//			
//			wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
//			SaveCampaignDetails_Button.click();
////			
//			wait.until(ExpectedConditions.visibilityOf(createCampaign_success_message));
//			Assert1.assertTrue(createCampaign_success_message.isDisplayed(),campaign_name+" campaign not created");
//			Assert1.assertAll();
////			driver.switchTo().activeElement();
////			campaign_success_msg_pause_button.click();
////			campaign_success_msg_close_button.click();
//			
//			Thread.sleep(1000);
//					
//		}
//
//           public void EditCampaign(String campaign_name_updated,String externalID,int futureStartDate,int futureEndDate) throws InterruptedException{
//			
//
//			wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
//
//		    Thread.sleep(3000);
//			System.out.println(campaignName_TextBox);
//			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+campaign_name_updated+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//			campaignName_TextBox.clear();
//			campaignName_TextBox.sendKeys(campaign_name_updated);
//			campaignID_TextBox.clear();
//			campaignID_TextBox.sendKeys(externalID);
//			active_InactiveCampaign_Button.click();
//			startDate_Calender.click();
//			WebElement element;
//			int startDate;
//			String startDateToBeSelected;
//			
//			startDate =futureStartDate;
//			startDateToBeSelected=String.valueOf(startDate);
//			if(futureStartDate<26){
//				startDate =futureStartDate+2;
//				startDateToBeSelected=String.valueOf(startDate);
//				element=driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
//				
//				
//				}else {
//	                Util.Action().moveToElement(StartCalender_rightArrow).click().perform();
//	                startDate=3;
//	                startDateToBeSelected=String.valueOf(startDate);
//					element = driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
//			
//				}
//			Util.Action().moveToElement(element).click().perform();
//			Util.Action().moveToElement(startdatePicker_ok_Button).click().perform();
//			
//			try{
//				endDate_Calender.click();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			WebElement element1;
//			int endDate;
//			String endDateToBeSelected;
//			
//			endDate =futureEndDate;
//			endDateToBeSelected=String.valueOf(endDate);
//			if(futureEndDate<26){
//				endDate =futureEndDate+3;
//				endDateToBeSelected=String.valueOf(endDate);
//				element1=driver.findElement(By.xpath("(//sm-date-picker[@id='End DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+endDateToBeSelected+"])"));
//				
//				
//				}else {
//	                Util.Action().moveToElement(endCalender_rightArrow).click().perform();
//	                endDate=4;
//	                endDateToBeSelected=String.valueOf(endDate);
//					element1 = driver.findElement(By.xpath("(//sm-date-picker[@id='End DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+endDateToBeSelected+"])"));
//				}
//			
//			Util.Action().moveToElement(element1).click().perform();
//			Util.Action().moveToElement(enddatePicker_ok_Button).click().perform();
//			
//            
//			
//			wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
//			SaveCampaignDetails_Button.click();
//			
//			wait.until(ExpectedConditions.visibilityOf(updateCampaign_success_message));
//			Assert1.assertTrue(updateCampaign_success_message.isDisplayed(),"campaign not updated ");
//			Assert1.assertAll();
////			driver.switchTo().activeElement();
////			campaign_success_msg_pause_button.click();
////			campaign_success_msg_close_button.click();
//			
//			Thread.sleep(1000);
//					
//		}
//
//		
//		
//		
//		
//		
//		//to verify campaign is getting edited without external id
//		public void EditCampaign(String campaign_name_updated) throws InterruptedException{
//			
//
//			wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
//
//		    Thread.sleep(3000);
//			System.out.println(campaignName_TextBox);
//			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+campaign_name_updated+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//			campaignName_TextBox.clear();
//			campaignName_TextBox.sendKeys(campaign_name_updated);
//			wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
//			active_InactiveCampaign_Button.click();
//			Thread.sleep(2000);
//			SaveCampaignDetails_Button.click();
//			
//            Thread.sleep(2000);
//			driver.switchTo().activeElement();
//			wait.until(ExpectedConditions.visibilityOf(Ok_button_campaign_deactivate_confirmation_msg)).click();
//			
//			wait.until(ExpectedConditions.visibilityOf(updateCampaign_success_message));
//			Assert1.assertTrue(updateCampaign_success_message.isDisplayed(),"campaign not updated ");
//			Assert1.assertAll();
////			driver.switchTo().activeElement();
////			campaign_success_msg_pause_button.click();
////			campaign_success_msg_close_button.click();
//			
//			Thread.sleep(1000);
//					
//		}
//		
//		//to verify campaign is getting edited with external id
//		public void EditCampaign(String campaign_name_updated,String externalID) throws InterruptedException{
//			
//
//			wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
//			System.out.println(campaignName_TextBox);
//			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+campaign_name_updated+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//			campaignName_TextBox.clear();
//			campaignName_TextBox.sendKeys(campaign_name_updated);
//			campaignID_TextBox.clear();
//			campaignID_TextBox.sendKeys(externalID);
//			wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
//			SaveCampaignDetails_Button.click();
//			
//			wait.until(ExpectedConditions.visibilityOf(updateCampaign_success_message));
//			Assert1.assertTrue(updateCampaign_success_message.isDisplayed(),"campaign not updated ");
//			Assert1.assertAll();
////			driver.switchTo().activeElement();
////			campaign_success_msg_pause_button.click();
////			campaign_success_msg_close_button.click();
//			
//			Thread.sleep(1000);
//					
//		}
		
            public void createPastDateCampaign(String campaign_name,int pastStartDate) throws InterruptedException{
			
            wait.until(ExpectedConditions.visibilityOf(campaignName_TextBox));
            wait.until(ExpectedConditions.elementToBeClickable(campaignName_TextBox));
            campaignName_TextBox.clear();
            campaignName_TextBox.sendKeys(campaign_name);
            
			startDate_Calender.click();
			WebElement element;
			int startdate;
			String startDateToBeSelected;
			
			startdate =pastStartDate-1;
			startDateToBeSelected=String.valueOf(startdate);
			element=driver.findElement(By.xpath("(//sm-date-picker[@id='Start DatePicker']//div//sm-calender[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope']//span[@class='ng-binding ng-scope'][text()="+startDateToBeSelected+"])"));
			
			
			Util.Action().moveToElement(element).click().perform();
			Util.Action().moveToElement(startdatePicker_ok_Button).click().perform();
			wait.until(ExpectedConditions.visibilityOf(SaveCampaignDetails_Button));
			SaveCampaignDetails_Button.click();
			
			wait.until(ExpectedConditions.visibilityOf(alertForPastDateCampaign));
			Assert1.assertTrue(alertForPastDateCampaign.isDisplayed(),campaign_name+" campaign not created");
//			driver.switchTo().activeElement();
//			campaign_success_msg_pause_button.click();
//			campaign_success_msg_close_button.click();
			Assert1.assertAll();
			Thread.sleep(1000);
					
		}
		
          
}
