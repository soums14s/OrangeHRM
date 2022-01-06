package com.OrangeHRM.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.OrangeHRM.pages.LoginPage;
import com.OrangeHRM.utility.BaseClass;
import com.OrangeHRM.utility.excelDataProvider;

public class TC_002 extends BaseClass{

	@Test(dataProviderClass = excelDataProvider.class, dataProvider = "loginCredentials")
	public void OrangeHRM(String uname, String pwd) throws InterruptedException, IOException {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.OrangeHRMLogin(uname, pwd);
		loginPage.OrangeHRMLogout();
	}
	
}
