package com.OrangeHRM.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.OrangeHRM.constants.Constants;



public class BaseClass {

public static WebDriver driver;
	
		public static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
	
	@BeforeTest
	public void setUp() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", Constants.chromePath);
		driver=new ChromeDriver();		
		driver.get(Constants.url);
		driver.manage().window().maximize();
		fCaptureScreenShot(driver, "LoginPage");
		
	}
	
	@AfterTest
	public void tearDown() throws IOException {
		driver.quit();
	}		
	
	public static String fCaptureScreenShot(WebDriver driver, String screenshotName) throws IOException {
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + screenshotName + "_" + timeStamp + ".png";
		try {
			FileUtils.copyFile(source, new File(screenshotPath));
		} catch (Exception e) {
			e.getMessage();
			System.out.println(e.getMessage());
		}			
		return screenshotPath;
	}
	
		
}
