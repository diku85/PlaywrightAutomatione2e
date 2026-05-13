package com.qa.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.extentmanager.ExtentManager;
import com.qa.opencart.factory.PlaywrightFactory;

public class Listener implements ITestListener {

	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);

		String msg = (String) result.getAttribute("customMessage");
		if (msg != null) {
			test.get().log(Status.INFO, "Custom Message: " + msg);
		}
	}

	public void onTestSuccess(ITestResult result) {
		String msg = (String) result.getAttribute("customMessage");
		test.get().log(Status.PASS, msg);
	}

	public void onTestFailure(ITestResult result) {
		String msg = (String) result.getAttribute("customMessage");
		System.out.println(result.getMethod().getMethodName() + " failed !!");
		test.get().log(Status.FAIL, msg);
		test.get().fail(result.getThrowable(),
				MediaEntityBuilder.createScreenCaptureFromPath(PlaywrightFactory.takeScreenshot()).build());

	}

	public void onFinish(ITestContext context) {
		extent.flush(); // write report to disk
	}

}
