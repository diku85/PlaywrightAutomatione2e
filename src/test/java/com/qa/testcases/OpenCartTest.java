package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.qa.BaseTest.BaseTest;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.pages.HomePage;

public class OpenCartTest extends BaseTest {

	@Test
	public void validateHomePageTitle() {
		System.out.println(homePage.getHomepageTitle());
		Assert.assertEquals(homePage.getHomepageTitle(), "Your Store");

	}

	@Test
	public void validateHomePageUrl() {
		System.out.println(homePage.getHomepageUrl());
		Assert.assertEquals(homePage.getHomepageUrl(), "https://naveenautomationlabs.com/opencart/");

	}

	@Test
	public void validateSearchHeader() {
		String actualText = homePage.validateSearchHeader("macbook");
		System.out.println(actualText);
		Assert.assertEquals(actualText, "Search - macbookssss");
	}

	

}
