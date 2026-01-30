package com.test.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> currentStep = new ThreadLocal<>();

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");

            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Tiket.com Test Automation");
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void removeTest() {
        extentTest.remove();
        currentStep.remove();
    }

    public static void step(String stepDescription) {
        ExtentTest test = getTest();
        if (test != null) {
            ExtentTest stepNode = test.createNode(stepDescription);
            currentStep.set(stepNode);
            System.out.println("▶ STEP: " + stepDescription);
        }
    }

    public static void log(String message) {
        ExtentTest step = currentStep.get();
        if (step != null) {
            step.info(message);
        } else {
            ExtentTest test = getTest();
            if (test != null) {
                test.info(message);
            }
        }
        System.out.println("    ↳ " + message);
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}

