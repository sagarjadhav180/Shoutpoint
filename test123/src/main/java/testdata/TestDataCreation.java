package testdata;

import java.io.IOException;

import com.relevantcodes.extentreports.LogStatus;

import constants.Constants;
import pom.Breadcrumb;
import pom.CampaignAndTrackingNumberPage;
import pom.CampaignBuilderPage;
import pom.GeoRouteLocationsPage;
import pom.GroupsAndUserPage;
import pom.HomePage;
import pom.LoginPage;
import pom.TrackingNumberBuilderPage;
import pom.WebHookPage;
import tests.TestBase;
import tests.TrackingNumberTest;
import tests.Util;
import tests.WebhookTest;

@SuppressWarnings("unused")
public class TestDataCreation extends TestBase implements TestDataFunctions {

	public void login() throws Exception {
	
		LoginPage lp=new LoginPage(driver);

		lp.validLogin();
		
	}

	public void createGroup() throws InterruptedException {
		
		HomePage hp=new HomePage(driver);
		hp.clickAction(Constants.HomePage.group_User_page);
		
		GroupsAndUserPage gp=new GroupsAndUserPage(driver);
		gp.expandSection(Constants.GroupsAndUser.sub_groups_strip);
		gp.createSubGroup(account);
//		Thread.sleep(2000);
		hp.clickAction(Constants.HomePage.home_page);
		
	}

	public void navigateToGroup() throws InterruptedException {
		
		Breadcrumb bc=new Breadcrumb(driver);
		bc.goToGroup(account,Constants.groupHeirarchyCompany);
	}
	
	public void createUser(String f_name,String l_name,String userID) throws InterruptedException {
		HomePage hp=new HomePage(driver);
		hp.clickAction(Constants.HomePage.group_User_page);
		
		GroupsAndUserPage gp=new GroupsAndUserPage(driver);
		gp.expandSection(Constants.GroupsAndUser.user_settings_strip);
		gp.createUser(f_name, l_name, userID, Constants.GroupsAndUser.admin_role);;
	    Thread.sleep(2000);
	    hp.clickAction(Constants.HomePage.home_page);
	    
	}

    public void trackingNumberSettings() throws InterruptedException {
    	HomePage hp=new HomePage(driver);
		hp.clickActionNewAccount(Constants.HomePage.group_User_page);
		
		GroupsAndUserPage gp=new GroupsAndUserPage(driver);
		gp.expandSection(Constants.GroupsAndUser.tn_settings_strip);
		gp.updateTNSettings();
		hp.clickAction(Constants.HomePage.home_page);		
	}
	
	public void createCampaign() throws InterruptedException {
		
		HomePage hp=new HomePage(driver);
        
		try {
			hp.clickActionNewAccount(Constants.HomePage.campaign_tracking_number_page);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CampaignBuilderPage cb=new CampaignBuilderPage(driver, wait);
		CampaignAndTrackingNumberPage cp=new CampaignAndTrackingNumberPage(driver);			
		
		cp.clickAction("add","");
		cb.create(campaignToBeEdited,"",0,0);
		cb.clickAction("list");
		hp.clickAction(Constants.HomePage.home_page);
		Thread.sleep(3000);

	}

	public void createTrackingNumber() throws InterruptedException {
        HomePage hp=new HomePage(driver);
        
		try {
			hp.clickAction(Constants.HomePage.campaign_tracking_number_page);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CampaignAndTrackingNumberPage ct;
		TrackingNumberBuilderPage cb;
		TrackingNumberTest tt=new TrackingNumberTest();
		ct=tt.createInstanceCampaignAndTrackingNumberPage();	
		cb=tt.createInstance();	    
		
		ct.clickAction("update",campaignToBeEdited);
		cb.createSimpleNumber(tracking_number);
	    Thread.sleep(2000);		
		hp.clickAction(Constants.HomePage.home_page);
		Thread.sleep(2000);

	}

	public void timeZone() throws InterruptedException {
		
		HomePage hp=new HomePage(driver);
		hp.timeZoneSetting();
		hp.clickAction(Constants.HomePage.home_page);		
	}

	public void createGeoLoaction() throws InterruptedException {
		HomePage hp=new HomePage(driver);
		hp.clickAction(Constants.HomePage.settings_section);
		hp.click_subLink("Customization");
		hp.click_subLink(Constants.HomePage.geo_locations);
		
		GeoRouteLocationsPage geo=new GeoRouteLocationsPage(driver);
		geo.addMainLocation(geo_location);
		hp.clickAction(Constants.HomePage.home_page);				
		Thread.sleep(2000);
	}

	public void createWebhook() throws InterruptedException {
		HomePage hp=new HomePage(driver);
		hp.clickAction(Constants.HomePage.settings_section);
		hp.click_subLink("Customization");
		hp.click_subLink(Constants.HomePage.webhook);
		WebHookPage wh=new WebHookPage(driver);
		
		wh.createWebHook(webhook, webhook_url);

		hp.clickAction(Constants.HomePage.home_page);
		Thread.sleep(2000);
	}

	public void logOut() {
		LoginPage lp=new LoginPage(driver);
		lp.logOut();
//		driver.close();
		
	}

	

}
