package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	private static Playwright playwright;
	private static Browser browser;
	private static BrowserContext browserContext;
	private static Page page;

	private static ThreadLocal<Playwright> tPlayWright = new ThreadLocal<>();
	private static ThreadLocal<Browser> tBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tPage = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return tPlayWright.get();
	}

	public static Browser getBrowser() {
		return tBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tBrowserContext.get();
	}

	public static Page getPage() {
		return tPage.get();
	}

	private static Properties prop;

	public static Page initBrowser(Properties prop) {

		System.out.println("Browser Name:" + prop.getProperty("browser"));
//		playwright = Playwright.create();
		tPlayWright.set(Playwright.create());
		switch (prop.getProperty("browser")) {
		case "chrome":
//			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "firefox":
			tBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		}

//		browserContext = browser.newContext();
		tBrowserContext.set(getBrowser().newContext());
//		page = browserContext.newPage();
		tPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("applicationurl"));
		return getPage();

	}

	public static Properties readFile() {
		String path = "src/test/resources/config.properties";
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

		return path;
	}

}
