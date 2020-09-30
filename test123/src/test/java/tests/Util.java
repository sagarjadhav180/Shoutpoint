package tests;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;
import com.relevantcodes.extentreports.LogStatus;

public class Util extends TestBase{
	
	static JsonNode rootNode;
	static JavascriptExecutor jse = (JavascriptExecutor)driver;
	static String dbUrl="jdbc:postgresql://"+TestBase.getEnv()+"-pg-1.convirza.com:5432/ct_stg";
	static String username="moentekdbrw";
	static String password="hyPdua14GAu6";
	static Connection connection=null;
	static Statement stmpt=null;
//	static Actions actions;
	
	public static void elementHighlight(WebElement element){
	
		try{
			for(int i=0; i<2; i++)
			{
				JavascriptExecutor jse=(JavascriptExecutor)driver;
				jse.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,"color: red; border: 3px solid red;");
			}
		}
		catch(Exception e){
			System.out.println("unable to highlight element..");
		}		
	}
	
	
	public static void removeElementHighlight(WebElement element){
		
		try{
			for(int i=0; i<2; i++)
			{
				JavascriptExecutor jse=(JavascriptExecutor)driver;
				jse.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,"");
			}
		}
		catch(Exception e){
			System.out.println("unable to unhighlight element..");
		}		
	}
	
	
	public static String createScreenshot(WebDriver driver, String method_name) throws IOException {
		String imageLocation = ".//Screenshots/";
		String image_path = imageLocation + method_name + ".png";
		// generate screenshot as a file object
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(image_path));
		return image_path;
	}
	
	public static String image_upload(String image){
        String uploaded_image_link = "";
        String upload_to = "https://api.imgur.com/3/upload.json";
        String API_key = "6ce9f890db1c13bab4076752571ef4861c467151";
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(upload_to);
        httpPost.addHeader("Authorization", "Client-ID " + "d12b61ca3109d70");
        try {
          final MultipartEntity entity = new MultipartEntity(
                    HttpMultipartMode.BROWSER_COMPATIBLE);
            entity.addPart("image", new FileBody(new File(image)));
            entity.addPart("key", new StringBody(API_key));
            httpPost.setEntity(entity);
            final HttpResponse response = httpClient.execute(httpPost,
                    localContext);
            final String response_string = EntityUtils.toString(response
                    .getEntity());
            rootNode = new ObjectMapper().readTree(new StringReader(response_string));
            JsonNode innerNode = rootNode.get("data");
            System.out.println(rootNode);
            JsonNode aField = innerNode.get("link");
            uploaded_image_link = aField.getTextValue();
            System.out.println(uploaded_image_link);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
      return uploaded_image_link;
  }
	
	

	   public static String createpiechart(int passed, int failed, int skipped){
	        int total = passed + failed + skipped;
	        int passed_percentage = (passed == 0) ? 0 : (passed * 100 / total);
	        int failed_percentage = (failed == 0) ? 0 : (failed * 100 / total);
	        int skipped_percentage = (skipped == 0) ? 0 : (skipped * 100 / total);
	        Slice s1 = Slice.newSlice(passed_percentage, Color.FORESTGREEN, "Passed", "Passed");
	        Slice s2 = Slice.newSlice(failed_percentage, Color.RED, "Failed", "Failed");
	        Slice s3 = Slice.newSlice(skipped_percentage, Color.newColor("CACACA"), "Skipped", "Skipped");
	        PieChart chart = GCharts.newPieChart(s1, s2, s3);
	        chart.setTitle("CFA Automation Result", Color.BLACK, 16);
	        chart.setSize(500, 200);
	        chart.setThreeD(true);
	        String url = chart.toURLString();
	        return url;
	    }
	
	
	
	public static void scrollFunction(WebElement element){
        System.out.println("scrolling function called..");
		jse.executeScript("arguments[0].scrollIntoView(true)", element);
		
	}
	
	public static void scrollFunctionForWebElement(WebElement element){
        System.out.println("scrolling function called..");
		EventFiringWebDriver event=new EventFiringWebDriver(driver);
		event.executeScript("document.querySelector(element).scrollTop=500");
		
	}
	
	
	public static void click(WebElement elementToBeClicked){
		jse.executeScript("arguments[0].click();",elementToBeClicked );
	}
	
	
	public static Actions Action(){
		Actions actions=new Actions(driver);
		return actions;
	}
	
	public static String readingFromDB(String query){
		
		String count = null;
		try{
			
			Class.forName("org.postgresql.Driver");
		   
			connection=DriverManager.getConnection(dbUrl,username,password);
		    
			stmpt=connection.createStatement();
			
			ResultSet result = stmpt.executeQuery(query);
		
		while(result.next()){
//			Array campaign_count = result.getArray("count");
			count = result.getString("count");
			
		}
		
		
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return count;
		
    }
	
	
	public static int generateRandomNumber(){
		Random randomNumber=new Random();
		int number = randomNumber.nextInt();
		return number;
	}
	
		
	public static String getDate(String Format,String days){
		SimpleDateFormat dateFormat = new SimpleDateFormat(Format);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Integer.valueOf(days));
		
		Date todate1 = cal.getTime();
	    String date = dateFormat.format(todate1);

	    System.out.println(date);
	    return date;
		
	}
	
	public static JavascriptExecutor getJavascriptExecutor(){
		return jse;
		
	}


	public static int getDate() {
		
		Date date=new Date();
		DateFormat dateFormat = new SimpleDateFormat();
		int currentDate = date.getDate();
		return currentDate;
	}
	
//	public static void waitTillPageLoad(){
//	    wait.until(jse.executeScript("return document.readyState").equals("complete"));
//	    wait.until(ExpectedConditions.jsReturnsValue(javaScript));
//		
//	}
	


			  
	}
