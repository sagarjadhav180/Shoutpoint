package pom;

import java.util.List;


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

public class CampaignAndTrackingNumberPage extends TestBase

{
	
	@FindBy(xpath="//a[@class='btn btn-sm btn-default addcamp ng-scope'][@aria-hidden='false']")
	private static WebElement addCampaign_Button;
	String addCampaign_Button_text="Add Campaign";

	@FindBy(xpath="//h1")
	private static WebElement campaignandTrackingNumberPage_Label;		
	String campaignandTrackingNumberPage_Label_text="Campaign and Tracking Number";
	
	@FindBy(xpath="//form/div/div/h4")
	private static WebElement campaignDetails_Label;		
	
	@FindBy(xpath="//i[2]")
	private static WebElement backToCmapaignPage_Link;	
	
	
	//pagination tool box
	@FindBy(xpath="(//button[4])[1]")
	private static WebElement topNextPagination_Button;	

	@FindBy(xpath="(//button[contains(text(),'Prev 100')])[1]")
	private static WebElement topPrevPagination_Button;	
	
	@FindBy(xpath="//div[@class='text-right topPagination']//div//button[1]")
	private static WebElement topFirstPagination_count;	

	@FindBy(xpath="//div[@class='text-right topPagination']//div//button[5]")
	private static WebElement topLastPagination_count;	
	
	@FindBy(xpath="(//button[3])[1]")
	private static WebElement topPagination_count;

	
	@FindBy(xpath="(//button[4])[2]")
	private static WebElement bottomNextPagination_Button;
	
	@FindBy(xpath="(//button[3])[2]")
	private static WebElement bottomPagination_count;
	
	@FindBy(xpath="//div[@class='text-right topPagination']//div//button[5]")
	private static WebElement bottomFirstPagination_count;		
	
	@FindBy(xpath="//table[contains(@class,'table table-hover table-striped table-condensed table-responsive mt10 ng-isolate-scope')]/tbody/tr")
	private static List<WebElement> countOfCamapign;

	@FindBy(xpath="//table[contains(@class,'table table-hover table-striped table-condensed table-responsive mt10 ng-isolate-scope')]/tbody/tr/td[3]/span[1]")
	private static List<WebElement> camapignList;
	
	
	@FindBy(xpath="//h1")
	private static WebElement campaigns_Label;	
	
	
	
	
	String campaign_to_be_archived;
	@FindBy(xpath="//tr/td/span[contains(text(),'1234')]/ancestor::tr/td[9]/span[@class='actions-buttons']/button[1]")
	private static WebElement Archive_Button;	
	

	@FindBy(xpath="//h1")
	private static WebElement Campaigns_Label;	
	
	@FindBy(xpath="//div[1]/button[1]/span[contains(text(),'Export')]")
	private static WebElement ExportButton;	
	
	String ExportButton_text="Export"; 
	

	@FindBy(xpath="//div/div/div/div/h4[contains(text(),'Campaign List for')]")
	private static WebElement CampaignList_Header;	

	String CampaignList_text="CAMPAIGN LIST FOR";
	
	
	@FindBy(xpath="//tr[1]//th")
	private static List<WebElement> Column_Labels;	
	
	@FindBy(xpath="//tr[1]//th[1]")
	private static WebElement common_collapseExpand_button;		
	
	String[] Column_Labels_text={"Campaign","Campaign External ID","Campaign Start","Campaign End","Campaign Status","Tracking Number Quantity","Actions"};

	//column picker
	@FindBy(xpath="//button[@class='btn btn-block btn-default dropdown-toggle']")
	private static WebElement Column_Picker_button;		
	
//	String Column_Picker_options_selected;
	@FindBy(xpath="//ul[@id='columnpicker']/li/label/preceding-sibling::div//input")
	private static List<WebElement> Column_Picker_options_checkbox;	

	@FindBy(xpath="//ul[@id='columnpicker']/li/label/preceding-sibling::div")
	private static List<WebElement> Column_Picker_options_checkbox_tocheck;	
	
	@FindBy(xpath="//ul[@id='columnpicker']/li/label")
	private static List<WebElement> Column_Picker_options_checkbox_labels;
	
	@FindBy(xpath="//ul[@id='columnpicker']/li/label")
	private static List<WebElement> Column_Picker_options_labels;

	@FindBy(xpath="//tr[@class='subrowheader803 ng-scope']//tbody//tr[1]//th")
	private static List<WebElement> tracking_number_list_columns;
	
	String[] by_default_displayed_tracking_number_list_columns={"Tracking Number ","Tracking Number Name ","Ring-to Phone Number ","Tracking Number Type ","Spam Guard ","Tracking Number Status ","Actions "}; 

	String[] all_tracking_number_list_columns={"Tracking Number ","Tracking Number Name ","Ring-to Phone Number ","Tracking Number Type ","Spam Guard ","Tracking Number Status ","Call Value ","Repeat Interval ","Ad Source ","Voicemail ","Custom Source 1 ","Custom Source 2 ","Custom Source 3 ","Custom Source 4 ","Custom Source 5 ","Record Call ","Play Disclaimer ","Voice Prompt ","Whisper Message ","DNI ","DNI Type ","Host Domain ","Referring Website ","HTML Class ","Custom Parameters "," Pre-call Webhook ","Actions "};
	
	@FindBy(xpath="//button[@class='btn btn-block btn-default dropdown-toggle']")
	private static WebElement campaignList;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private static WebElement ok_button_in_archive_alert;	
	

	@FindBy(xpath="//table[@class='table']//tbody//tr[1]//th")
	private static List<WebElement> tracking_number_table_column_headers;
	
	String[] default_displayed_expected_tracking_number_table_column_headers={"Tracking Number","Tracking Number Name","Ring-to Phone Number","Tracking Number Type","Spam Guard","Tracking Number Status","Actions"};

	String[] all_expected_tracking_number_table_column_headers={"Tracking Number","Tracking Number Name","Ring-to Phone Number","Tracking Number Type","Spam Guard","Tracking Number Status","Call Value","Repeat Interval","Ad Source","Voicemail","Custom Source 1","Custom Source 2","Custom Source 3","Custom Source 4","Custom Source 5","Record Call","Play Disclaimer","Voice Prompt","Whisper Message","DNI","DNI Type","Host Domain","Referring Website","HTML Class","Custom Parameters","Pre-call Webhook","Actions"};
	
	String[] Expected_Column_Picker_options_labels ={"Ad Source",
			"Call Value",
			"Campaign",
			"Campaign External ID",
			"Campaign Start",
			"Campaign End",
			"Campaign Status",
			"Custom Parameters",
			"Custom Source 1",
			"Custom Source 2",
			"Custom Source 3",
			"Custom Source 4",
			"Custom Source 5",
			"DNI",
			"DNI Type",
			"Host Domain",
			"HTML Class",
			"Play Disclaimer",
			"Pre-call Webhook",
			"Record Call",
			"Referring Website",
			"Repeat Interval",
			"Ring-to Phone Number",
			"Spam Guard",
			"Tracking Number",
			"Tracking Number Name",
			"Tracking Number Quantity",
			"Tracking Number Status",
			"Tracking Number Type",
			"Voice Prompt",
			"Voicemail",
			"Whisper Message"};

	String[] default_selected_Expected_Column_Picker_options ={
			"",
			"",
			"Campaign",
			"Campaign External ID",
			"Campaign Start",
			"Campaign End",
			"Campaign Status",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"Ring-to Phone Number",
			"Spam Guard",
			"Tracking Number",
			"Tracking Number Name",
			"Tracking Number Quantity",
			"Tracking Number Status",
			"Tracking Number Type",
			"",
			"",
			""
			};
	
	int Expected_Column_Picker_options_count=32;
	
	@FindBy(xpath="//div[@class='pageProgressLoader']")
	private WebElement loading_wheel;	
	
	@FindBy(xpath="//button[@id='_pendo-close-guide_']")
	private WebElement pendo_close_button;	

	@FindBy(xpath="//div[@class='guide-header']")
	private WebElement pendo_popup;	
	public static WebDriverWait wait1;
	

	@FindBy(xpath="//div[@class='pageProgressLoader']")
	private WebElement loading_wheel_for_add_campaign;
	
	SoftAssert Assert1=new SoftAssert();
	
	//db variables
	String countOnUI_pagination;
	String countOnUI_listedcampaigns;
	String dbCount;
	
	
	
	public CampaignAndTrackingNumberPage(WebDriver driver){
		PageFactory.initElements(driver, this);
      }

	
	public void clickAction(String buttonName,String campaignToBeEdited) throws InterruptedException{
		if(buttonName.contains("add")){
				
			
			wait.until(ExpectedConditions.visibilityOf(addCampaign_Button)).isDisplayed();
			
			Thread.sleep(2000);					
					
			Util.click(addCampaign_Button);
			wait.until(ExpectedConditions.invisibilityOf(loading_wheel_for_add_campaign));
				
		}
		
		
		else if(buttonName.contains("list")){
			wait.until(ExpectedConditions.visibilityOf(campaignList));
			campaignList.click();
//			try{
//				driver.switchTo().activeElement();
//			Util.click(pendo_close_button);
//			Thread.sleep(3000);
//			}
//			catch(Exception e){			
//				wait.until(ExpectedConditions.invisibilityOf(loading_wheel));
//				}
//			wait.until(ExpectedConditions.invisibilityOf(loading_wheel));
			
			
		}
		else if(buttonName.contains("update")){
            Thread.sleep(30000);
            WebElement edit = null;
            if(topNextPagination_Button.isEnabled()){
            	try{
                    edit= driver.findElement(By.xpath("//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//span[@class='actions-buttons']//button[contains(text(),'Edit')]"));            		
            	}
            	catch(Exception e){
            		topNextPagination_Button.click();
                    edit= driver.findElement(By.xpath("//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//span[@class='actions-buttons']//button[contains(text(),'Edit')]"));            		
            	}
            }
           
            else{
            	edit= driver.findElement(By.xpath("//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//span[@class='actions-buttons']//button[contains(text(),'Edit')]"));
            }
            
			tests.Util.scrollFunction(edit);
			tests.Util.click(edit);
			wait.until(ExpectedConditions.invisibilityOf(loading_wheel));

		}
		else if(buttonName.contains("expand")){
			
			WebElement expand_campaign = driver.findElement(By.xpath("(//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//a//i)[1]"));
			
			tests.Util.scrollFunction(expand_campaign);
			tests.Util.click(expand_campaign);
			tests.Util.getJavascriptExecutor().executeScript("window.scrollBy(0,-200)" );

		}
		
		else if(buttonName.contains("collapse")){
			

			WebElement collapse = driver.findElement(By.xpath("(//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//a//i)[1]"));
			
			tests.Util.scrollFunction(collapse);
			tests.Util.click(collapse);
			tests.Util.getJavascriptExecutor().executeScript("window.scrollBy(0,-200)" );

		}
		
		else if(buttonName.contains("archive")){
			
						
			WebElement archive = null;
            if(topNextPagination_Button.isEnabled()){
            	try{
                    archive= driver.findElement(By.xpath("//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//span[@class='actions-buttons']//button[contains(text(),'Archive')]"));            		
            	}
            	catch(Exception e){
            		topNextPagination_Button.click();
                    archive= driver.findElement(By.xpath("//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//span[@class='actions-buttons']//button[contains(text(),'Archive')]"));            		
            	}
            }
			
            else{
            	archive= driver.findElement(By.xpath("//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//span[@class='actions-buttons']//button[contains(text(),'Archive')]"));
            }
			
			tests.Util.scrollFunction(archive);
			tests.Util.click(archive);

			driver.switchTo().activeElement();
			wait.until(ExpectedConditions.visibilityOf(ok_button_in_archive_alert)).click();
			String archived_campaign="campaignToBeEdited";
			WebElement deleted_campaign_xpath = null;
			
			try{
			deleted_campaign_xpath= driver.findElement(By.xpath("//div[@class='ui-pnotify-text']"));
			}
			catch(Exception e){
				Thread.sleep(2000);
				deleted_campaign_xpath= driver.findElement(By.xpath("//div[@class='ui-pnotify-text']"));				
			}
			
			Assert1.assertTrue(deleted_campaign_xpath.getText().contains(archived_campaign),archived_campaign+"campaign not archived successfully");
		    wait.until(ExpectedConditions.invisibilityOf(deleted_campaign_xpath));
            Util.getJavascriptExecutor().executeScript("window.scrollBy(0,-2000)");	
            Thread.sleep(2000);    
		}
		
		else if(buttonName.contains("expand")){
			

			WebElement expand = driver.findElement(By.xpath("(//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//i)[1]"));
//			wait.until(ExpectedConditions.visibilityOf(expand));
			tests.Util.scrollFunction(expand);
			Thread.sleep(1000);
			tests.Util.getJavascriptExecutor().executeScript("window.scrollBy(0,-200)","");
			
			tests.Util.click(expand);
			
		}
		
	      else if(buttonName.contains("collapse")){
			

			WebElement collapse = driver.findElement(By.xpath("(//tr[contains(@id,'rowdataitem')]/td[3]/span[contains(text(),'"+campaignToBeEdited+"')]/ancestor::tr//i)[1]"));
//			wait.until(ExpectedConditions.visibilityOf(expand));
			
			Thread.sleep(1000);
			
			tests.Util.click(collapse);
			
		}
		
	}
	
	public void toCheckDefaultDisplayedColumns(){

		
		for(int i=0;i<default_displayed_expected_tracking_number_table_column_headers.length;){
			
			for(int j=0;j<tracking_number_table_column_headers.size();j++){
				if(default_displayed_expected_tracking_number_table_column_headers[i].equals(tracking_number_table_column_headers.get(j).getText())){
					System.out.println(default_displayed_expected_tracking_number_table_column_headers[i]);
					System.out.println(tracking_number_table_column_headers.get(j).getText());
					logger.log(LogStatus.INFO, "verifying if column "+default_displayed_expected_tracking_number_table_column_headers[i]+"is present..");
					Assert1.assertTrue(default_displayed_expected_tracking_number_table_column_headers[i].equals(tracking_number_table_column_headers.get(j).getText()),default_displayed_expected_tracking_number_table_column_headers[i]+" is not displayed..");
				    Assert1.assertAll();
				}
			}
			i++;
		}
		
	}
	
	public void toCheckAllDisplayedColumns(){
		
		for(int j=0;j<tracking_number_table_column_headers.size();){
			
			for(int i=0;i<all_expected_tracking_number_table_column_headers.length;i++){
				if(all_expected_tracking_number_table_column_headers[i].equals(tracking_number_table_column_headers.get(j).getText())){
					System.out.println(all_expected_tracking_number_table_column_headers[i]);
					System.out.println(tracking_number_table_column_headers.get(j).getText());
					logger.log(LogStatus.INFO, "verifying if column "+all_expected_tracking_number_table_column_headers[i]+" is present..");
					try{
					Assert1.assertTrue(all_expected_tracking_number_table_column_headers[i].equals(tracking_number_table_column_headers.get(j).getText()),default_displayed_expected_tracking_number_table_column_headers[i]+" is not displayed..");
					}catch(Exception e){
						e.printStackTrace();
					}
					Assert1.assertAll();
				}
			}
			j++;
		}
		
	}
	
	public void toCheckOptionsFromColumnPicker(){
	
		logger.log(LogStatus.INFO, "Checking all options from column picker..");
	    Util.click(Column_Picker_button);		
		Util.getJavascriptExecutor().executeScript("window.scrollBy(0,200)");
	    for(int i=0;i<Column_Picker_options_checkbox_tocheck.size();){
                
	    
	    	for(int j=0;j<Column_Picker_options_checkbox.size();j++){
	    	
          	if(!Column_Picker_options_checkbox.get(i).getAttribute("aria-checked").equals("true")){
 					System.out.println(Column_Picker_options_checkbox_tocheck.get(i));
                    Util.Action().moveToElement(Column_Picker_options_checkbox_tocheck.get(i)).click().perform();
// 					break;
           	}
 				
	      }    
	    	
	    	i++; 
//	    break;
	    }

		Util.getJavascriptExecutor().executeScript("window.scrollBy(0,-200)");
	    Util.click(Column_Picker_button);
	   
	}
	

	public void campaignPageUIVerification()
	{
     
    //label verification
	logger.log(LogStatus.INFO, "Verifying presence campaignBuilder_Label");
	wait.until(ExpectedConditions.visibilityOf(campaignandTrackingNumberPage_Label));
	Assert1.assertEquals(campaignandTrackingNumberPage_Label_text, campaignandTrackingNumberPage_Label.getText(),"locator changed or campaignandTrackingNumberPage_Label not present");
	
	//add campaign button verification
	logger.log(LogStatus.INFO, "Verifying presence addCampaign_Button");
	wait.until(ExpectedConditions.visibilityOf(addCampaign_Button));
	Assert1.assertTrue(addCampaign_Button.isDisplayed(),"locator changed or add campaign button not present");	

	//Check if add campaign button is enabled
	logger.log(LogStatus.INFO, "Verifying if addCampaign_Button is enabled..");
	wait.until(ExpectedConditions.visibilityOf(addCampaign_Button));
	Assert1.assertTrue(addCampaign_Button.isEnabled(),"add campaign button not enabled");	
	
	//add campaign button text verification
	logger.log(LogStatus.INFO, "Verifying text on addCampaign_Button");
	Assert1.assertEquals(addCampaign_Button_text, addCampaign_Button.getText(),"addCampaign_Button text not present");	
	
	//export button verification
	logger.log(LogStatus.INFO, "Verifying presence ExportButton");
	Assert1.assertTrue(ExportButton.isDisplayed(),"ExportButton is not present or locator has been changed..");		

	//Check if export button is enabled
	logger.log(LogStatus.INFO, "Verifying if ExportButton is enabled..");
	wait.until(ExpectedConditions.visibilityOf(ExportButton));
	Assert1.assertTrue(ExportButton.isEnabled(),"ExportButton not enabled");	
	
	//export button text verification	
	logger.log(LogStatus.INFO, "Verifying text on ExportButton");	
	Assert1.assertEquals(ExportButton_text, ExportButton.getText(),"text not present");	
	
	//CampaignList title verification
	logger.log(LogStatus.INFO, "Verifying presence CampaignList");
	Assert1.assertTrue(CampaignList_Header.isDisplayed(),"CampaignList header is not present or locator has been changed..");		

	//CampaignList title text verification	
	logger.log(LogStatus.INFO, "Verifying text on CampaignList");
	System.out.println("---"+CampaignList_text+"-----");
	System.out.println("---"+CampaignList_Header.getText()+"-----");
	Assert1.assertTrue(CampaignList_Header.getText().contains(CampaignList_text),"CampaignList title text not present");	

	
	
	//verification of column picker
	logger.log(LogStatus.INFO, "Verifying presence of Column_Picker_button");	
    Assert1.assertTrue(Column_Picker_button.isDisplayed(),"Column_Picker_button is not present or locator changed");
    wait.until(ExpectedConditions.elementToBeClickable(Column_Picker_button));
    
    //Check if Column_Picker_button is enabled
  	logger.log(LogStatus.INFO, "Verifying if Column_Picker_button is enabled..");
  	wait.until(ExpectedConditions.visibilityOf(Column_Picker_button));
  	Assert1.assertTrue(Column_Picker_button.isEnabled(),"Column_Picker_button not enabled");
    
    
    //To check if all column picker options are present and enabled 
  	
    Util.click(Column_Picker_button);
    for(int i=0;i<Column_Picker_options_labels.size();){
    	for(int j=0;j<Expected_Column_Picker_options_labels.length;j++){
    	    logger.log(LogStatus.INFO, "Verifying if column picker option - "+Expected_Column_Picker_options_labels[j]+" is present");
    		
//    	    System.out.println("expected - "+Expected_Column_Picker_options_labels[j]);
//    	    System.out.println("actual "+Column_Picker_options_labels.get(i).getText());
    	    
    	    Assert1.assertTrue(Column_Picker_options_labels.get(i).getText().equalsIgnoreCase(Expected_Column_Picker_options_labels[j]),"column picker option - "+Expected_Column_Picker_options_labels[j]+" is not present ");
    	    logger.log(LogStatus.INFO, "Verifying if column picker option - "+Expected_Column_Picker_options_labels[j]+" is enabled");
    		Assert1.assertTrue(Column_Picker_options_labels.get(i).isEnabled(),"Verifying if column picker option - "+Expected_Column_Picker_options_labels[j]+" is not enabled");   
    		i++;
    	}
    }

    //Column_Picker_options - verifications of by default checked options

 	logger.log(LogStatus.INFO, "verifying by default checked options..");

 	for(int i=0;i<Column_Picker_options_checkbox.size();){

 		for(int j=0;j<Column_Picker_options_checkbox_labels.size();){

 			for(int k=0;k<default_selected_Expected_Column_Picker_options.length;){

 				if(Column_Picker_options_checkbox_labels.get(j).getText().equals(default_selected_Expected_Column_Picker_options[k])){
 					System.out.println("Column_Picker_options_checkbox_labels is "+Column_Picker_options_checkbox_labels.get(j).getText());
 					System.out.println("default_selected_Expected_Column_Picker_options is "+Column_Picker_options_checkbox_labels.get(j).getText());
 					String aria = Column_Picker_options_checkbox.get(i).getAttribute("aria-checked");
 					Assert1.assertEquals(aria, "true",Column_Picker_options_checkbox.get(i).getText()+" is not by default checked");
 					
 				}
 				i++;
 				j++;
 				k++;	
 			}
 			
 		}
 	}
    



    Util.click(Column_Picker_button);
    
	
    
    //verification of presence of column headers 
//    logger.log(LogStatus.INFO, "verifying presence of column headers..");
//    for(int i=1;i<Column_Labels.size();){
//    
//   		Assert1.assertTrue(Column_Labels.get(i).isDisplayed());;
//       	
//    }
     
    //verification of column headers text
 	logger.log(LogStatus.INFO, "verifying column header text..");
    for(int i=1;i<Column_Labels.size();){
    	
    	for(int j=0;j<Column_Labels_text.length;j++){
    		System.out.println("actual "+Column_Labels.get(i).getText());

    		
    		System.out.println("expected "+Column_Labels_text[j]);


    		Assert1.assertEquals(Column_Labels.get(i).getText(),Column_Labels_text[j],Column_Labels.get(i).getText()+" header is not present or locator has been changed.." );
            i++;
    	}
    }
    
    //verifying if common_collapseExpand_button is displayed and its enabled
 	logger.log(LogStatus.INFO, "verifying if common_collapseExpand_button is displayed..");    
	wait.until(ExpectedConditions.visibilityOf(common_collapseExpand_button));
	Assert1.assertTrue(common_collapseExpand_button.isDisplayed(),common_collapseExpand_button+" is not displayed or locator has been changed..");
 	logger.log(LogStatus.INFO, "verifying if common_collapseExpand_button is enabled..");  
	Assert1.assertTrue(common_collapseExpand_button.isEnabled(),common_collapseExpand_button+" is not enabled..");

	
	
    
	//verifying if all buttons are displayed in top pagination toolbox 
	logger.log(LogStatus.INFO, "verifying presence of buttons in top pagination toolbox");
	wait.until(ExpectedConditions.visibilityOf(topNextPagination_Button));
	Assert1.assertTrue(topNextPagination_Button.isDisplayed(),"topNextPagination_Button is not present or locator changed");
	Assert1.assertTrue(topPrevPagination_Button.isDisplayed(),"topPrevPagination_Button is not present or locator changed");	
	Assert1.assertTrue(topFirstPagination_count.isDisplayed(),"topFirstPagination_count is not present or locator changed");	
	Assert1.assertTrue(topLastPagination_count.isDisplayed(),"topLastPagination_count is not present or locator changed");	
	

	//verification of count in top pagination toolbox	
	dbCount = Util.readingFromDB("SELECT count(*) as count FROM campaign WHERE campaign_ou_id='"+campaign_ou_id+"' AND campaign_status NOT IN ('deleted')" );


	countOnUI_pagination=topPagination_count.getText().substring(topPagination_count.getText().indexOf('f')+2);
	logger.log(LogStatus.INFO, "verifying count in top pagination toolbox");
	Assert1.assertEquals(dbCount, countOnUI_pagination,"count in top pagination toolbox is mismatching with db count");
		
	//verification of bottom pagination toolbox
        if(countOfCamapign.size()>100){
		Util.scrollFunction(bottomNextPagination_Button);
		wait.until(ExpectedConditions.visibilityOf(bottomNextPagination_Button));
		Assert1.assertTrue(bottomNextPagination_Button.isDisplayed(),"bottomNextPagination_Button is not present or locator changed");			
		Assert1.assertTrue(bottomFirstPagination_count.isDisplayed(),"bottomFirstPagination_count is not present or locator changed");	
        }
    
        //verification of add campaign button is enabled 
    	logger.log(LogStatus.INFO, "verifying if add campaign button is enabled"); 
    	Assert1.assertTrue(addCampaign_Button.isEnabled(),"addCampaign_Button is not enabled");
    	
        //verification of Export button is enabled
//    	logger.log(LogStatus.INFO, "verifying if export button is enabled"); 
//    	Assert1.assertTrue(ExportButton.isEnabled());    	
    	

    	//verification of top Pagination buttons are enabled
    	logger.log(LogStatus.INFO, "verifying if top Pagination buttons are clickable");
    	Assert1.assertTrue(topNextPagination_Button.isEnabled(),"topNextPagination_Button is not clickable");
    	Assert1.assertTrue(topPrevPagination_Button.isEnabled(),"topPrevPagination_Button is not clickable");	
    	Assert1.assertTrue(topFirstPagination_count.isEnabled(),"topFirstPagination_count is not clickable");	
    	Assert1.assertTrue(topLastPagination_count.isEnabled(),"topLastPagination_count is not clickable");    	

    	logger.log(LogStatus.INFO, "verifying count of listed campaigns");
    	
    	int final_count=countOfCamapign.size()+0;
    	if(!(topNextPagination_Button.getAttribute("class").endsWith("disabled"))){
    		Util.click(topNextPagination_Button);
    		wait.until(ExpectedConditions.invisibilityOf(loading_wheel));
    		final_count=final_count+countOfCamapign.size();
    	}
    	System.out.println("dbCount "+dbCount);
    	System.out.println("final_count "+final_count);
    	Assert1.assertEquals(dbCount, String.valueOf(final_count),"count  of listed campaigns is mismtching with db count");
    	
    Assert1.assertAll();
    Util.scrollFunction(topPrevPagination_Button);
    Util.click(topPrevPagination_Button);
    wait.until(ExpectedConditions.invisibilityOf(loading_wheel));
	
	}
	
	
	public void campaignCreated(String campaign_name){
		
		wait.until(ExpectedConditions.visibilityOf(CampaignList_Header));
		for(WebElement campaign:camapignList){
			if(campaign.getText().equals(campaign_name)){
			Assert1.assertEquals(campaign.getText(), campaign_name,"newly created campaign - "+campaign_name+" is not present..");
			}
		}
		Assert1.assertAll();
	}
	

	public void defaultDisplayedTrackingNumberColumns(){
		
		for(int i=0;i<by_default_displayed_tracking_number_list_columns.length;){
			

			for(int j=0;j<tracking_number_list_columns.size();){
				System.out.println("tracking_number_list_columns "+tracking_number_list_columns.get(j).getText());					
				System.out.println("by_default_displayed_tracking_number_list_columns "+by_default_displayed_tracking_number_list_columns[i]);
				
				if(tracking_number_list_columns.get(j).getText().equals(by_default_displayed_tracking_number_list_columns[i])){
					System.out.println("tracking_number_list_columns "+tracking_number_list_columns.get(j).getText());					
					System.out.println("by_default_displayed_tracking_number_list_columns "+by_default_displayed_tracking_number_list_columns[i]);
					logger.log(LogStatus.INFO, "verifying if "+by_default_displayed_tracking_number_list_columns[i]+" is displayed");
					Assert1.assertTrue(tracking_number_list_columns.get(j).getText().equals(by_default_displayed_tracking_number_list_columns[i]));			
				    Assert1.assertAll();
				}
				
 				j++;
			}
			i++;
		}
	 	
	}
	
	public void allDisplayedTrackingNumberColumns(){
        
		for(int i=0;i<tracking_number_list_columns.size();){
			
			for(int j=0;j<all_tracking_number_list_columns.length;j++){
				if(tracking_number_list_columns.get(i).getText().equals(all_tracking_number_list_columns[j])){
					
					logger.log(LogStatus.INFO, "verifying if "+all_tracking_number_list_columns[j]+" is displayed");
					Assert1.assertTrue(tracking_number_list_columns.get(i).getText().equals(all_tracking_number_list_columns[j]));			
				}
			}
			i++;
		}
	 	
	}
	
	
	
		
}
