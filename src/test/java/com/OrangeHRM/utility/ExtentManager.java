package com.OrangeHRM.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
		
	public void onStart(ITestContext testContext)
	{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/ExtentReports/"+"OrangeHRM_"+BaseClass.timeStamp+".html");
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environemnt","UAT");
		extent.setSystemInfo("User","Test Engineer");
		
		htmlReporter.config().setDocumentTitle("OrangeHRM Automation Test");
		htmlReporter.config().setReportName("OrangeHRM Test Automation Report"); 
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	public void onTestSuccess(ITestResult result)
	{
		logger=extent.createTest(result.getName()); 
		logger.log(Status.PASS,MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));

	}
	
	public void onTestFailure(ITestResult result)
	{
		logger=extent.createTest(result.getName()); 
		logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName(),ExtentColor.RED)); 
		
		try {
			
			String imagePath=BaseClass.fCaptureScreenShot(BaseClass.driver, result.getName());
			logger.addScreenCaptureFromPath(imagePath);
		}catch(Exception e) {
			System.out.println("Screenshot not captured "+e.getMessage());
		}
		}
		

	public void onTestSkipped(ITestResult result)
	{
		logger=extent.createTest(result.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}
