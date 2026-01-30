package com.test.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.test.base.BaseTest;
import com.test.utils.ExtentManager;
import com.test.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    private ExtentReports extent;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("========================================");
        System.out.println("Test Suite Started: " + context.getName());
        System.out.println("========================================");
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        System.out.println(">>> Test Started: " + testName);

        ExtentTest test = extent.createTest(testName, description != null ? description : "");
        test.assignCategory(result.getTestClass().getName());
        ExtentManager.setTest(test);

        ExtentManager.getTest().info("Test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("✓ Test Passed: " + testName);

        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.pass(MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("✗ Test Failed: " + testName);

        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.fail(MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));
            test.fail(result.getThrowable());

            try {
                WebDriver driver = BaseTest.getDriver();
                
                if (driver != null) {
                    String base64Screenshot = ScreenshotUtil.captureScreenshotAsBase64(driver);
                    
                    if (base64Screenshot != null) {
                        String imgHtml = "<br><b>Screenshot at failure:</b><br>" +
                                "<img src='data:image/png;base64," + base64Screenshot + 
                                "' style='max-width:100%; border:1px solid #ccc; margin-top:10px;'/>";
                        test.fail(imgHtml);
                        System.out.println("✓ Screenshot attached to report for: " + testName);
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR capturing screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("⊘ Test Skipped: " + testName);

        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.skip(MarkupHelper.createLabel("TEST SKIPPED", ExtentColor.ORANGE));
            if (result.getThrowable() != null) {
                test.skip(result.getThrowable());
            }
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("========================================");
        System.out.println("Test Suite Finished: " + context.getName());
        System.out.println("Total Tests Run: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        System.out.println("========================================");

        ExtentManager.flush();
        ExtentManager.removeTest();

        System.out.println("Extent Report generated: extent-report.html");
    }
}
