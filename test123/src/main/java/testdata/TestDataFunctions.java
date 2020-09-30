package testdata;

public interface TestDataFunctions {

	public void login() throws Exception;
	public void createGroup() throws InterruptedException;
	public void navigateToGroup()throws InterruptedException;
	public void createUser(String f_name,String l_name,String userID) throws InterruptedException;
	public void createCampaign() throws InterruptedException;
	public void createTrackingNumber() throws InterruptedException;
	public void trackingNumberSettings() throws InterruptedException;
	public void timeZone() throws InterruptedException;
	public void createGeoLoaction() throws InterruptedException;
	public void createWebhook() throws InterruptedException;
	public void logOut();
		
	
}
