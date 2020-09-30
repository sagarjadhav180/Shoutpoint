package extentReport;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

import tests.TestBase;
import tests.Util;

public class SendEmail extends TestBase {

	
	@Test
	public static void execute(String reportFileName, int totalFailedTestCase, int totalPassedTestCase,
			int totalSkipedTestCases) throws Exception

	{

		// String pie_chart_link =
		// Helper_Class.createpiechart(test_status_count[0],test_status_count[1],
		// test_status_count[2]);
		// email address :- ""saurabhk@alohatechnology.com",
		// "surajm@alohatechnology.com","ahrarh@alohatechnology.com" "
		//"lsnow@convirza.com"
		//"rakesh.m@moentek.com"
		String[] to = {"lsnow@convirza.com","ganesh.g@moentek.com","rakesh.m@moentek.com","amit.madhukar@moentek.com"};
		String[] cc = {"sagar.j@moentek.com"};
		String[] bcc = {};
//
//		String[] to = { "rahilaloha@gmail.com" };
//		String[] cc = {};
//		String[] bcc = {};

		// String writer = "<!DOCTYPE html> <html> <head></head> <body> <p
		// style='color:black;font-size:13px;font-family:sans-serif'>Hi
		// All,<br><br> This is automated mail from Convirza CFA Team. <br>
		// Please find the attachment for the log result.</p>" + build_status +
		// "<span
		// style='color:black;font-size:13px;font-family:sans-serif'><b>Project:</b>
		// CONVIRZA FOR ADVERTISER</span><br> <span
		// style='color:black;font-size:13px;font-family:sans-serif'><b>Date of
		// build: </b>"+ new java.util.Date() +"</span><br><br><br> <b><span
		// style='color:black;font-size:13px;font-family:sans-serif'> Number of
		// Test Cases Failed: </span></b>" + test_status_count[1] + "<br>
		// <b><span style='color:black;font-size:13px;font-family:sans-serif'>
		// Number of Test Cases Passed: </span></b>" + test_status_count[0] +
		// "<br> <b><span
		// style='color:black;font-size:13px;font-family:sans-serif'> Number of
		// Test Cases Skipped: </span></b>"+ test_status_count[2]+ "<br><br><br>
		// <img src ="+ pie_chart_link +"/> <br/><br/> <b
		// style='font-size:13.5px;font-family:open
		// sans-serif;padding-left:5px'>Thanks,</b><br/> <b
		// style='font-size:13.5px;font-family:open
		// sans-serif;padding-left:5px'>Convirza CQM Team</b><br/><img
		// src='http://i.imgur.com/PkLRdkY.png' width = '150px'/></body>
		// </html>";
		// Image path = "http://i.imgur.com/PkLRdkY.png"
		
		String pie_chart_link = Util.createpiechart(totalPassedTestCase, totalFailedTestCase,
				totalSkipedTestCases);
		String writer = "<!DOCTYPE html> <html> <head></head> <body> <p style='color:black;font-size:13px;font-family:sans-serif'>Hi All,<br><br> This is automated mail from QA Automation Team. Please find the below automation script result and also find the attachment for the log result.</p>"
				+ "<span style='color:black;font-size:13px;font-family:sans-serif'><b>Project:</b>Convirza for Advertisers</span><br> <span style='color:black;font-size:13px;font-family:sans-serif'><b>Date of build: </b>"
				+ new java.util.Date() + "<br>"
				+ "<span style='color:black;font-size:13px;font-family:sans-serif'><b>Build url: </b>"
				+ TestBase.Base_Url
				+ "</span><br><br><br> <b><span style='color:black;font-size:13px;font-family:sans-serif'> Number of Test Cases Failed: </b>"
				+ totalFailedTestCase + "</span>"
				+ "<br> <b><span style='color:black;font-size:13px;font-family:sans-serif'> Number of Test Cases Passed: </b>"
				+ totalPassedTestCase + "</span>"
				+ "<br> <b><span style='color:black;font-size:13px;font-family:sans-serif'> Number of Test Cases Skipped: </b>"
				+ totalSkipedTestCases + " </span><br/><br/>" + "<img src=" + pie_chart_link + "/>" + "<br><br><br>"
				+ "<br/><br/> <b style='font-size:13.5px;font-family:open sans-serif;padding-left:5px'>Thanks,</b><br/> <b style='font-size:13.5px;font-family:open sans-serif;padding-left:5px'>QA Team</b><br/></body> </html>";
		
		if(totalFailedTestCase>0) {
			String[] cc1 = {"sagar.j@moentek.com","pratik.d@moentek.com"};

			SendEmail.sendMail("CFAAppAutomation@gmail.com", "CFAAppAutomation@123", "smtp.gmail.com", "465", "true", "true", true,
					"javax.net.ssl.SSLSocketFactory", "false", to, cc1, bcc, "CFA App Automation Test Result", writer,
					".//Extent.html", "Automation_Report.html", "");
		}
		else {
			SendEmail.sendMail("CFAAppAutomation@gmail.com", "CFAAppAutomation@123", "smtp.gmail.com", "465", "true", "true", true,
					"javax.net.ssl.SSLSocketFactory", "false", to, cc, bcc, "CFA App Automation Test Result", writer,
					".//Extent.html", "Automation_Report.html", "");			
		}
		
		
		
//		
//		SendEmail.sendMail("CFAAppAutomation@gmail.com", "CFAAppAutomation@123", "smtp.gmail.com", "465", "true", "true", true,
//				"javax.net.ssl.SSLSocketFactory", "false", to, cc, bcc, "CFA App Automation Test Result", writer,
//				".//Extent.html", "Automation_Report.html", "");
		
	}

	@Test
	public static boolean sendMail(String userName, String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass, String fallback, String[] to, String[] cc,
			String[] bcc, String subject, String text, String attachmentPath, String attachmentName, String URL) {

		// Object Instantiation of a properties file.
		Properties props = new Properties();

		props.put("mail.smtp.user", userName);

		props.put("mail.smtp.host", host);

		if (!"".equals(port)) {
			props.put("mail.smtp.port", port);
		}

		if (!"".equals(starttls)) {
			props.put("mail.smtp.starttls.enable", starttls);
			props.put("mail.smtp.auth", auth);
		}

		if (debug) {

			props.put("mail.smtp.debug", "true");

		} else {

			props.put("mail.smtp.debug", "false");

		}

		if (!"".equals(port)) {
			props.put("mail.smtp.socketFactory.port", port);
		}
		if (!"".equals(socketFactoryClass)) {
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		}
		if (!"".equals(fallback)) {
			props.put("mail.smtp.socketFactory.fallback", fallback);
		}

		try {

			Session session = Session.getDefaultInstance(props, null);

			session.setDebug(debug);

			MimeMessage msg = new MimeMessage(session);

			msg.setText(text.toString(), "text/html");
			msg.setSubject(subject);

			Multipart mp = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(text.toString(), "text/html");

			MimeBodyPart messageBodyPart1 = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentPath);
			messageBodyPart1.setDataHandler(new DataHandler(source));
			messageBodyPart1.setFileName(attachmentName);

			mp.addBodyPart(messageBodyPart);
			mp.addBodyPart(messageBodyPart1);
			msg.setContent(mp);
			msg.setFrom(new InternetAddress(userName));

			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}

			for (int i = 0; i < cc.length; i++) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
			}

			// for(int i=0;i<bcc.length;i++){
			// msg.addRecipient(Message.RecipientType.BCC, new
			// InternetAddress(bcc[i]));
			// }

			msg.saveChanges();

			Transport transport = session.getTransport("smtp");

			transport.connect(host, userName, passWord);

			transport.sendMessage(msg, msg.getAllRecipients());

			transport.close();

			return true;

		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}

	
}
