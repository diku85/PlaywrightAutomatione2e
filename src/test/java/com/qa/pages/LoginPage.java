package com.qa.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	Page page;
	public LoginPage(Page page) {
		this.page=page;
	}
	
	private String email="//input[@id='input-email']";
	private String password="//input[@id='input-password']";
	
	public String getLoginTitle() {
		return page.title();
	}
	
	public boolean validateEmailDisplayed() {
		return page.locator(email).isVisible();
	}
	
	public boolean validatePasswordDisplayed() {
		return page.locator(password).isVisible();
	}
	
	

}
