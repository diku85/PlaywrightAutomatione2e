package com.qa.BaseTest;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class BaseTest {
	
	Page page;
	public HomePage homePage;
	public LoginPage loginPage;
	Properties prop;
	
	@BeforeTest
	public void setUp() {
		prop=PlaywrightFactory.readFile();
		page = PlaywrightFactory.initBrowser(prop);
		homePage=new HomePage(page);
		loginPage=new LoginPage(page);
	}
	
	@AfterTest
	public void closeResources() {
		page.context().browser().close();
	}

}
