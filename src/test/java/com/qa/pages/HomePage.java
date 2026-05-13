package com.qa.pages;

import com.microsoft.playwright.Page;

public class HomePage {

	Page page;

	// OR
	private String searchBox = "input[type='text']";
	private String searchButton = "//button[@type='button']//i[contains(@class,'fa-search')]";
	private String searchText = "//div[@id='content']//h1";
	private String myAccount="//a[@title='My Account']";
	private String login="//ul[contains(@class,'dropdown-menu')]//a[text()='Login']";

	public HomePage(Page page) {
		this.page = page;
	}
	
	public String getHomepageTitle() {
		return page.title();
	}
	
	public String getHomepageUrl() {
		return page.url();
	}

	public String validateSearchHeader(String value) {
		page.locator(searchBox).fill(value);
		page.locator(searchButton).click();
		return page.locator(searchText).innerText();
	}
	
	public LoginPage clickLogin() {
		page.locator(myAccount).click();
		page.locator(login).click();
		return new LoginPage(page);
	}
	
	

}
