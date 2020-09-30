package pom;

import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import tests.TestBase;
import tests.Util;

@SuppressWarnings("unused")
public class HomePage extends TestBase {
	

	@FindBy(xpath="//button[@id='_pendo-close-guide_']")
	private WebElement pendo_close_button;	

	@FindBy(xpath="//div[@class='guide-header']")
	private WebElement pendo_popup;	

	//left hand navigation bar
	
	@FindBy(xpath="//*[@id='sidebar']/li/a/i//following-sibling::span[1]")
	private List<WebElement> left_hand_navigation_bar_links;	

	String[] left_hand_navigation_bar_links_names={"Home","Reports","Legacy Reports","Scorecard","Campaign & Tracking Number","Group & User","Settings"};
	
	String[] left_hand_navigation_bar_collapsible_links_names={"Legacy","Scorecard","Settings","Customization","Integration"};	
	
	@FindBy(xpath="//*[@id='sidebar']/li//ul//li/a/span[1]")
	private List<WebElement> left_hand_navigation_bar_sub_links;
	
	String[] left_hand_navigation_bar_sub_links_names={"Call Details","Group Activity","Tracking Number Settings","Manage Scorecard","Select & Score","My Scheduled Reports","Legacy Scheduled Reports","Customization","Blacklist","DNI","GeoRoute","Legacy Distribution Lists","Tags ","Webhook","Integration","Acquisio","Data Append","DoubleClick","Google Analytics"};
	String[] left_hand_navigation_bar_settings_sub_links_names={"Customization","Integration"};
	
	
	//dashboard 
	@FindBy(xpath="//canvas[@ class='flot-overlay']")
	private WebElement unique_calls_graph;

	@FindBy(xpath="//canvas[@ class='flot-base']")
	private WebElement calls_by_day_graph;
	
	@FindBy(xpath="//*[@id='wrap']/div[2]/div/div[1]/div//div//div[@class='tiles-heading']//div")
	private List<WebElement> dashboard_tiles;		

	String[] dashboard_tile_names={"Total Calls","Unique Calls","Answered Calls","Avg Call Duration","Calls by Day","Total Leads","% of Leads","Total Conversion","Conversion Rate"};
	
	@FindBy(xpath="//*[@id='wrap']/div[2]/div/div[1]/div//div//div[@class='tiles-heading']//div//parent::div//following-sibling::div//div[contains(@class,'pull-right')]")
	private List<WebElement> dashboard_tiles_values;		
	
	@FindBy(xpath="//h4[contains(text(),'Get Started')]")
	private List<WebElement> getstarted_label;	

	@FindBy(xpath="//h4[contains(text(),'Integrations')]")
	private List<WebElement> Integration_label;
	
	@FindBy(xpath="//div[contains(text(),'Metrics on the Home Page reflect data for the Group chosen in the Breadcrumbs at the top.')]")
	private WebElement metrics_note;	
	
	@FindBy(xpath="//*[@id='wrap']/div[2]/div/div[2]/div[2]//span")
	private List<WebElement> dashboard_getstarted_links;
	
	String[] dashboard_getstarted_link_names={"Update your profile","Configure your company","Add more users","Create a campaign"};

	@FindBy(xpath="//*[@id='wrap']/div[2]/div/div[2]/div[3]/div//span")
	private List<WebElement> dashboard_integrstion_links;
	
	String[] dashboard_integrstion_link_names={"Acquisio","DoubleClick","Google Analytics"};
	
	@FindBy(xpath="//a[@id='leftmenu-trigger']")
	private WebElement left_hand_navigation_bar_collapsible_button;	

	@FindBy(xpath="//a[@class='navbar-brand']")
	private WebElement logo;
	
	@FindBy(xpath="//tbody[@ id='progressLoader']")
	private WebElement loading_wheel;	

	@FindBy(xpath="//div[contains(text(),'Last 7 Days')]")
	private WebElement last_7_days_note;	

	@FindBy(xpath="//a[@class='dropdown-toggle username bootstro']")
	private WebElement profile_link;

	@FindBy(xpath="//a[contains(text(),'Edit')]")
	private WebElement edit_profile_link;

	@FindBy(xpath="//label[contains(text(),'Time Zone')]//parent::div//following-sibling::div//select")
	private WebElement time_zone_listbox;
	
	String expected_time_zone="Eastern (ET)";

	@FindBy(xpath="//div[@class='panel panel-midnightblue']//button[@class='btn btn-primary'][contains(text(),'Save')]")
	private WebElement user_profile_save_button;

	@FindBy(xpath="//div[@class='ui-pnotify-text'][text()='User Profile updated successfully.']")
	private WebElement user_profile_save_success_message;
	
	@FindBy(xpath="//a[contains(text(),'Support')]")
	private WebElement support_link;

	@FindBy(xpath="	//a[contains(text(),'Sign Out')]")
	private WebElement logout_link;
	
//	WebDriverWait wait;
//	ExtentTest logger;
	SoftAssert softassert=new SoftAssert();
	
	public HomePage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void click_subLink(String linkToBeClicked){
		for(int i=0;i<left_hand_navigation_bar_sub_links.size();i++){
			if(left_hand_navigation_bar_sub_links.get(i).getText().equals(linkToBeClicked)){
				left_hand_navigation_bar_sub_links.get(i).click();
				break;
			}
		}
	}
	
	public void clickAction(String linkToBeClicked) throws InterruptedException{
		
		for(int i=0;i<left_hand_navigation_bar_links.size();i++){
		WebElement link = left_hand_navigation_bar_links.get(i);
			if(link.getText().equals(linkToBeClicked)){
				
				if(link.getText().contains("Campaign")){
					link.click();

					try{
					wait.until(ExpectedConditions.invisibilityOf(loading_wheel));
					
					}catch(Exception e){}
//					driver.switchTo().activeElement();
//					Util.click(pendo_close_button);
//					Thread.sleep(1000);					
				}
				
				
				
				
				
				else{
				link.click();
				try{
//					driver.switchTo().activeElement();
//					Util.click(pendo_close_button);
//					Thread.sleep(2000);
					}
					catch(Exception e){}
					
				}


			}
		}
		
	}
	
    public void clickActionNewAccount(String linkToBeClicked) throws InterruptedException{
		
		for(int i=0;i<left_hand_navigation_bar_links.size();i++){
		WebElement link = left_hand_navigation_bar_links.get(i);
			if(link.getText().equals(linkToBeClicked)){
				
				if(link.getText().contains("Campaign")){
					link.click();

					try{
					wait.until(ExpectedConditions.invisibilityOf(loading_wheel));
					
					}catch(Exception e){
						
						try{
							driver.switchTo().activeElement();
							Util.click(pendo_close_button);
							Thread.sleep(1000);
						}
						catch(Exception c){
							
						}
						
						
					}
					
					
				}
				
				
				
				else{
				link.click();
				try{
					driver.switchTo().activeElement();
					Util.click(pendo_close_button);
					Thread.sleep(2000);
					}
					catch(Exception e){}
					
				}

			}
		}	
	}
	
	public void default_collpased_left_hand_navigation_bar_click(){
		logger.log(LogStatus.INFO,"Verifying if left_hand_navigation_bar is by default collapsed ");
		softassert.assertTrue(left_hand_navigation_bar_links.isEmpty(),"left_hand_navigation_bar_collapsible_button is not by default collapsed..");
		
		
	}
	
	public void left_hand_navigation_bar_click(){

		left_hand_navigation_bar_collapsible_button.click();
		
	}
	
	//UI Verification of Home page
	public void UIVerification() throws InterruptedException{
		
		
		//To check if unique_calls_graph is displayed
		logger.log(LogStatus.INFO, "verifying if unique_calls_graph is displayed");
		softassert.assertTrue(unique_calls_graph.isDisplayed(),"unique_calls_graph is not displayed or locator has been changed..");		

		//To check if calls_by_day_graph is displayed
		logger.log(LogStatus.INFO, "verifying if calls_by_day_graph is displayed");
		softassert.assertTrue(calls_by_day_graph.isDisplayed(),"calls_by_day_graph is not displayed or locator has been changed..");
		
		//To check if left_hand_navigation_bar is collapsible
		left_hand_navigation_bar_collapsible_button.click();
		Thread.sleep(1000);

		
		//To check if logo is present
		logger.log(LogStatus.INFO, "verifying if logo is displayed");
		softassert.assertTrue(logo.isDisplayed(),"logo is not displayed or locator has been changed..");

		//To check if metric note is present
		logger.log(LogStatus.INFO, "verifying if metrics_note is displayed");
		softassert.assertTrue(metrics_note.isDisplayed(),"metric note is not displayed or locator has been changed..");
		
		//To check if last_7_days_note is displayed
		logger.log(LogStatus.INFO, "verifying if last_7_days_note is displayed");
		softassert.assertTrue(last_7_days_note.isDisplayed(),"last_7_days_note is not displayed or locator has been changed..");

		//To check if profile_link is displayed
		logger.log(LogStatus.INFO, "verifying if profile_link is displayed");
		softassert.assertTrue(profile_link.isDisplayed(),"profile_link is not displayed or locator has been changed..");
		
		Util.click(profile_link);
		
		//To check if edit_profile_link is displayed
		logger.log(LogStatus.INFO, "verifying if edit_profile_link is displayed");
		softassert.assertTrue(edit_profile_link.isDisplayed(),"edit_profile_link is not displayed or locator has been changed..");
		
		//To check if support_link is displayed
		logger.log(LogStatus.INFO, "verifying if support_link is displayed");
		softassert.assertTrue(support_link.isDisplayed(),"support_link is not displayed or locator has been changed..");

		//To check if logout_link is displayed
		logger.log(LogStatus.INFO, "verifying if logout_link is displayed");
		softassert.assertTrue(logout_link.isDisplayed(),"logout_link is not displayed or locator has been changed..");
		
		profile_link.click();
		
		for(int i=0;i<left_hand_navigation_bar_links.size();){

			
			for(int j=0;j<left_hand_navigation_bar_links_names.length;j++){


				if(left_hand_navigation_bar_links.get(i).getText().equals(left_hand_navigation_bar_links_names[j])){
				
				//to check if link is present and link name	is correct
				logger.log(LogStatus.INFO, "verifying tab "+left_hand_navigation_bar_links.get(i).getText());
				softassert.assertEquals(left_hand_navigation_bar_links.get(i).getText(), left_hand_navigation_bar_links_names[j],left_hand_navigation_bar_links.get(i)+" link is not present or locator has been changed..");
				
				}
			}
			//To verify links are clickable in left hand navigation bar
			logger.log(LogStatus.INFO, "verifying if "+left_hand_navigation_bar_links.get(i).getText()+" is clickable");
			softassert.assertTrue(left_hand_navigation_bar_links.get(i).isEnabled(),left_hand_navigation_bar_links.get(i).getText()+" is not clickable");

			//To check sublinks for legacy reports
			if(left_hand_navigation_bar_links.get(i).getText().contains("Legacy")){
				left_hand_navigation_bar_links.get(i).click();
				Thread.sleep(1000);
				for(int s=0;s<left_hand_navigation_bar_sub_links.size();){

                     for(int t=0;t<left_hand_navigation_bar_sub_links_names.length;t++){
                    	 if(left_hand_navigation_bar_sub_links.get(s).getText().equals(left_hand_navigation_bar_sub_links_names[t])){
                    		 System.out.println("we "+left_hand_navigation_bar_sub_links.get(s).getText());
                    		 System.out.println("array "+left_hand_navigation_bar_sub_links_names[t]);
                    		 logger.log(LogStatus.INFO, "Verifying if "+left_hand_navigation_bar_sub_links.get(s).getText()+" is present");
                    		 softassert.assertTrue(left_hand_navigation_bar_sub_links.get(s).getText().equals(left_hand_navigation_bar_sub_links_names[t]),left_hand_navigation_bar_sub_links.get(s).getText()+" is not present or locator has been changed..");
                    		 logger.log(LogStatus.INFO, "Verifying if "+left_hand_navigation_bar_sub_links.get(s).getText()+" is enabled.");
                    		 softassert.assertTrue(left_hand_navigation_bar_sub_links.get(s).isEnabled(),left_hand_navigation_bar_sub_links.get(s).getText()+" is not enabled..");                    		 
                    	 }
                     }
                     s++;
				}
				
			}
			
			else if(left_hand_navigation_bar_links.get(i).getText().contains("Scorecard")){
				left_hand_navigation_bar_links.get(i).click();
				Thread.sleep(1000);
				for(int u=0;u<left_hand_navigation_bar_sub_links.size();){

                     for(int v=0;v<left_hand_navigation_bar_sub_links_names.length;v++){
                    	 if(left_hand_navigation_bar_sub_links.get(u).getText().equals(left_hand_navigation_bar_sub_links_names[v])){
                    		 System.out.println("we "+left_hand_navigation_bar_sub_links.get(u).getText());
                    		 System.out.println("array "+left_hand_navigation_bar_sub_links_names[v]);
                    		 logger.log(LogStatus.INFO, "Verifying if "+left_hand_navigation_bar_sub_links.get(u).getText()+" is present");
                    		 softassert.assertTrue(left_hand_navigation_bar_sub_links.get(u).getText().equals(left_hand_navigation_bar_sub_links_names[v]),left_hand_navigation_bar_sub_links.get(u).getText()+" is not present or locator has been changed..");
                    		 logger.log(LogStatus.INFO, "Verifying if "+left_hand_navigation_bar_sub_links.get(u).getText()+" is enabled.");
                    		 softassert.assertTrue(left_hand_navigation_bar_sub_links.get(u).isEnabled(),left_hand_navigation_bar_sub_links.get(u).getText()+" is not enabled..");                    		 
                    	 }
                     }
                     u++;
				}
				
			}
			else if(left_hand_navigation_bar_links.get(i).getText().contains("Settings")){
				left_hand_navigation_bar_links.get(i).click();
				Thread.sleep(1000);
				for(int w=0;w<left_hand_navigation_bar_sub_links.size();){

                     for(int x=0;x<left_hand_navigation_bar_sub_links_names.length;x++){
                    	 if(left_hand_navigation_bar_sub_links.get(w).getText().equals(left_hand_navigation_bar_sub_links_names[x])){
                    		 System.out.println("we "+left_hand_navigation_bar_sub_links.get(w).getText());
                    		 System.out.println("array "+left_hand_navigation_bar_sub_links_names[x]);
                    		 logger.log(LogStatus.INFO, "Verifying if "+left_hand_navigation_bar_sub_links.get(w).getText()+" is present");
                    		 softassert.assertTrue(left_hand_navigation_bar_sub_links.get(w).getText().equals(left_hand_navigation_bar_sub_links_names[x]),left_hand_navigation_bar_sub_links.get(w).getText()+" is not present or locator has been changed..");
                    		 logger.log(LogStatus.INFO, "Verifying if "+left_hand_navigation_bar_sub_links.get(w).getText()+" is enabled.");
                    		 softassert.assertTrue(left_hand_navigation_bar_sub_links.get(w).isEnabled(),left_hand_navigation_bar_sub_links.get(w).getText()+" is not enabled..");                    		 
                    	 }
                     }
                     for(int y=0;y<left_hand_navigation_bar_settings_sub_links_names.length;y++){
         				if(left_hand_navigation_bar_settings_sub_links_names[y].equals(left_hand_navigation_bar_sub_links.get(w).getText())){
         					Thread.sleep(1000);
         					left_hand_navigation_bar_sub_links.get(w).click();
         					System.out.println("we "+left_hand_navigation_bar_sub_links.get(w).getText());
         					System.out.println("array "+left_hand_navigation_bar_settings_sub_links_names[y]);
         					logger.log(LogStatus.INFO,"verifying if "+left_hand_navigation_bar_sub_links.get(w).getText()+" is present");
         					softassert.assertTrue(left_hand_navigation_bar_settings_sub_links_names[y].equals(left_hand_navigation_bar_sub_links.get(w).getText()),left_hand_navigation_bar_sub_links.get(w).getText()+" link is not present or locator changed");
         				}
         			}
                     w++;
				}
				
			}
			
			i++;
		}
	
		//To verify all tiles are present in dashboard
		for(int k=0;k<dashboard_tiles.size();){
			for(int l=0;l<dashboard_tile_names.length;l++){
				System.out.println(dashboard_tiles.get(k).getText());
				System.out.println(dashboard_tile_names[l].toUpperCase());
				if(dashboard_tiles.get(k).getText().equals(dashboard_tile_names[l].toUpperCase())){
					
					logger.log(LogStatus.INFO, "verifying if "+dashboard_tiles.get(k).getText()+" tile is presnt");
					softassert.assertEquals(dashboard_tiles.get(k).getText(), dashboard_tile_names[l].toUpperCase(),dashboard_tiles.get(k).getText()+" tile is not present or locator changed..");
				}
			}
			k++;
		}
		
		//To verify all links are presnet in dashboard
		for(int m=0;m<dashboard_getstarted_links.size();){
			for(int n=0;n<dashboard_getstarted_link_names.length;n++){
				System.out.println(dashboard_getstarted_links.get(m).getText());
				System.out.println(dashboard_getstarted_link_names[n]);
				if(dashboard_getstarted_links.get(m).getText().equals(dashboard_getstarted_link_names[n])){
					
					logger.log(LogStatus.INFO, "verifying if "+dashboard_getstarted_links.get(m).getText()+" is present");
					softassert.assertEquals(dashboard_getstarted_links.get(m).getText(), dashboard_getstarted_link_names[n],dashboard_getstarted_links.get(m).getText()+" link is not present or locator has been changed..");
				}
				
			}

			//To verify all links are clickable in dashboard
			logger.log(LogStatus.INFO, "verifying if "+dashboard_getstarted_links.get(m).getText()+" is enabled");
			softassert.assertTrue(dashboard_getstarted_links.get(m).isEnabled(),dashboard_getstarted_links.get(m).getText()+" link is not enabled..");
			
			m++;
			
			
		}
		softassert.assertAll();
	}
	
	
	public void timeZoneSetting() throws InterruptedException{
		
		Util.click(profile_link);
		Util.click(edit_profile_link);
		
		Select select=new Select(time_zone_listbox);
		Thread.sleep(3000);
		System.out.println(select.getFirstSelectedOption().getText());
		
		if(!(select.getFirstSelectedOption().getText().equalsIgnoreCase(expected_time_zone))){
			select.selectByVisibleText(expected_time_zone);
			wait.until(ExpectedConditions.visibilityOf(user_profile_save_button));
			Util.click(user_profile_save_button);
			wait.until(ExpectedConditions.visibilityOf(user_profile_save_success_message));
			Assert.assertTrue(user_profile_save_success_message.isDisplayed(),"User profile settings not saved");

		}
	}
	
	//To check Tile count
	public void tilesCount(){
		
		   String endDateToBeUsed = Util.getDate("yyyy-MM-dd","-1");
		   String startDateToBeUsed = Util.getDate("yyyy-MM-dd","-8");

		   System.out.println("startDateToBeUsed is "+startDateToBeUsed);
		   System.out.println("endDateToBeUsed is "+endDateToBeUsed);
		
		   for(int i=0;i<dashboard_tiles_values.size();i++){
			
			//total calls count
			String total_call_count_from_ui = dashboard_tiles_values.get(0).getText();
			String total_call_count_from_db = Util.readingFromDB("SELECT count(*) as count FROM call WHERE org_unit_id="+TestBase.getOrg_unit_id()+" AND call_started BETWEEN '"+startDateToBeUsed+" 23:59' AND '"+endDateToBeUsed+" 23:59'");
			System.out.println("SELECT count(*) FROM call WHERE org_unit_id="+TestBase.getOrg_unit_id()+" AND call_started BETWEEN '"+startDateToBeUsed+" 23:59' AND '"+endDateToBeUsed+" 23:59'");
			System.out.println("total_count_from_ui is "+total_call_count_from_ui);
			System.out.println("total_count_from_db "+total_call_count_from_db);
			logger.log(LogStatus.INFO, "verifying total calls count..");
			softassert.assertTrue(total_call_count_from_ui.equals(total_call_count_from_db),"total_call_count_from_ui is not matching with db");
			
			//total unique calls count
			String unique_calls_count_from_ui = dashboard_tiles_values.get(1).getText();
			String unique_calls_count_from_db = Util.readingFromDB("SELECT count(*) as count FROM call WHERE org_unit_id="+TestBase.getOrg_unit_id()+" AND call_started BETWEEN '"+startDateToBeUsed+" 23:59' AND '"+endDateToBeUsed+" 23:59' AND repeat_call='false'");
			System.out.println("unique_calls_count_from_ui is "+unique_calls_count_from_ui);
			System.out.println("unique_calls_count_from_db "+unique_calls_count_from_db);
			logger.log(LogStatus.INFO, "verifying unique calls count..");
			softassert.assertTrue(unique_calls_count_from_db.equals(unique_calls_count_from_db),"unique_calls_count_from_ui is not matching with db");

			//total answered calls calls count
			String answered_calls_count_from_ui = dashboard_tiles_values.get(2).getText();
			String answered_calls_count_from_db = Util.readingFromDB("SELECT count(*) as count FROM call WHERE org_unit_id="+TestBase.getOrg_unit_id()+" AND call_started BETWEEN '"+startDateToBeUsed+" 23:59' AND '"+endDateToBeUsed+" 23:59' AND disposition IN ('ANSWERED')");
			System.out.println("answered_calls_count_from_ui is "+answered_calls_count_from_ui);
			System.out.println("answered_calls_count_from_db "+answered_calls_count_from_db);
			logger.log(LogStatus.INFO, "verifying answered calls count..");
			softassert.assertTrue(answered_calls_count_from_ui.equals(answered_calls_count_from_db),"answered_calls_count_from_ui is not matching with db");

			//AVG CALL DURATION
			String avg_call_duration__from_ui = dashboard_tiles_values.get(3).getText();
			String avg_call_duration_from_db = Util.readingFromDB("SELECT ROUND(AVG(duration)) as count FROM call WHERE org_unit_id="+TestBase.getOrg_unit_id()+" AND call_started BETWEEN '"+startDateToBeUsed+" 23:59' AND '"+endDateToBeUsed+" 23:59'");
			if(avg_call_duration_from_db==null){
				avg_call_duration_from_db="0";
			}
			System.out.println("avg_call_duration__from_ui is "+avg_call_duration__from_ui.substring(5,7));
			System.out.println("avg_call_duration_from_db "+avg_call_duration_from_db);
			logger.log(LogStatus.INFO, "verifying average call dusration..");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("softassert "+softassert);
			System.out.println("avg_call_duration_from_db "+avg_call_duration_from_db);
			System.out.println("avg_call_duration__from_ui "+avg_call_duration__from_ui.substring(5,avg_call_duration__from_ui.indexOf('s')));
			System.out.println();
			
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			softassert.assertTrue(avg_call_duration_from_db.equals(avg_call_duration__from_ui.substring(5,avg_call_duration__from_ui.indexOf('s'))),"AVG CALL DURATION from ui is not matching with db");
			
			//TOTAL LEADS
			String total_leads__from_ui = dashboard_tiles_values.get(4).getText();
			String total_leads_from_db = Util.readingFromDB("SELECT count(*) as count FROM indicator_score WHERE call_id IN (SELECT call_id FROM call WHERE org_unit_id='"+TestBase.getOrg_unit_id()+"' AND call_started BETWEEN '"+startDateToBeUsed+" 23:59' AND '"+endDateToBeUsed+" 23:59') AND indicator_id='51'");
			System.out.println("total_leads__from_ui is "+total_leads__from_ui);
			System.out.println("total_leads_from_db "+total_leads_from_db);
			logger.log(LogStatus.INFO, "verifying total leads..");
			if(total_leads_from_db==null || total_leads_from_db=="0"){
			softassert.assertTrue("0".equals(total_leads__from_ui),"total_leads__from_ui is not matching with db");
			}
			else{
			softassert.assertTrue(total_leads_from_db.equals(total_leads__from_ui),"total_leads__from_ui is not matching with db");				
			}
			//% LEADS
			String leads;
			try{
            leads = String.valueOf(Math.round((100*Integer.valueOf(total_leads_from_db))/Integer.valueOf(total_call_count_from_db)))+" %";
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				leads="0";
			}
			System.out.println("% leadds from ui "+dashboard_tiles_values.get(5).getText().substring(0,1));
			System.out.println("% leadds from calc "+String.valueOf(leads));
			logger.log(LogStatus.INFO, "verifying % of leads..");
			softassert.assertTrue(leads.equals(dashboard_tiles_values.get(5).getText().substring(0,1)),"% leads is incorrect");
			
			//TOTAL CONVERSIONS
			String total_conversion__from_ui = dashboard_tiles_values.get(6).getText();
			String total_conversion_from_db = Util.readingFromDB("SELECT score_value as count FROM indicator_score WHERE call_id IN (SELECT call_id FROM call WHERE org_unit_id="+TestBase.getOrg_unit_id()+" AND call_started BETWEEN '"+startDateToBeUsed+" 23:59' AND '"+endDateToBeUsed+" 23:59'') AND indicator_id='18'");
			if(total_conversion_from_db==null){
				total_conversion_from_db="0";
			}
			System.out.println("total_conversion__from_ui is "+total_conversion__from_ui);
			System.out.println("total_conversion_from_db "+total_conversion_from_db);
			logger.log(LogStatus.INFO, "verifying total conversion..");
			
			softassert.assertTrue(total_conversion__from_ui.equals("0"),"total_conversion__from_ui is not matching with db");					
			
			//conversion rate
			String conversion_rate;
			try{
				conversion_rate = String.valueOf(Math.round((100*Integer.valueOf(total_leads_from_db))/Integer.valueOf(total_call_count_from_db)))+" %";
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				conversion_rate="0";
			}
			
			System.out.println("conversion_rate from calc "+conversion_rate);
            System.out.println("conversion rate from ui "+(dashboard_tiles_values.get(7).getText().substring(0,1)));
            logger.log(LogStatus.INFO, "verifying conversion rate..");
//			softassert.assertTrue(String.valueOf(conversion_rate).equals(dashboard_tiles_values.get(7).getText()),"conversion rate is incorrect..");	
            softassert.assertTrue(conversion_rate.equals(dashboard_tiles_values.get(7).getText().substring(0,1)),"conversion rate is incorrect..");	
            
			break;
		}
		softassert.assertAll();
	}

}