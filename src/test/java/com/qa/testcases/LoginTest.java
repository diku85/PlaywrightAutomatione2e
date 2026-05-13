package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.BaseTest.BaseTest;

public class LoginTest extends BaseTest {

	@Test
	public void validateUserNamePassword() {
		loginPage = homePage.clickLogin();
		Assert.assertTrue(loginPage.validateEmailDisplayed());
		Assert.assertTrue(loginPage.validatePasswordDisplayed());
	}

	@Test
	public void validateLoginTitle() {
		loginPage = homePage.clickLogin();
		System.out.println(loginPage.getLoginTitle());
		Assert.assertEquals(loginPage.getLoginTitle(), "Account Login");
	}

}
