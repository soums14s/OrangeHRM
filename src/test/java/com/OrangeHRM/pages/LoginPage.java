package com.OrangeHRM.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.utility.BaseClass;
import com.OrangeHRM.utility.ExtentManager;
import com.aventstack.extentreports.Status;

import bsh.org.objectweb.asm.Constants;

public class LoginPage extends ExtentManager {

	private static WebDriver driver;
	@FindBy(xpath = "//*[@id=\"txtUsername\"]") WebElement username_Txt;
	@FindBy(xpath = "//*[@id='txtPassword']") WebElement password_Txt;
	@FindBy(xpath="//*[@id=\"btnLogin\"]") WebElement loginBtn;
	@FindBy(xpath="//*[@id='welcome']") WebElement userProfile;
	@FindBy(xpath="/html/body/div[1]/div[1]/div[9]/ul/li[3]/a") WebElement logoutBtn;
	
	public LoginPage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);		
	}
	
	public void OrangeHRMLogin(String uName, String passwd) throws InterruptedException {
		username_Txt.sendKeys(uName);
		Thread.sleep(5000);
		password_Txt.sendKeys(passwd);
		Thread.sleep(5000);
		loginBtn.click();
		Thread.sleep(5000);
		
	
	}
	
	public void OrangeHRMLogout() throws InterruptedException, IOException {
		userProfile.click();
		Thread.sleep(10000);
		logoutBtn.click();
		Thread.sleep(5000);
	}
	
}
