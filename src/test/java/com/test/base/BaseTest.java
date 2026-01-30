package com.test.base;

import com.test.data.DataProviderUtil;
import com.test.listeners.ExtentReportListener;
import com.test.utils.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;
import java.time.Duration;

@Listeners(ExtentReportListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private static final int IMPLICIT_WAIT_SECONDS = 10;
    private static final int EXPLICIT_WAIT_SECONDS = 30;
    private static final int PAGE_LOAD_TIMEOUT_SECONDS = 120;
    private static final int SCRIPT_TIMEOUT_SECONDS = 60;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(options);
        driverThreadLocal.set(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT_SECONDS));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(SCRIPT_TIMEOUT_SECONDS));

        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    protected void step(String stepDescription) {
        ExtentManager.step(stepDescription);
    }

    protected void log(String message) {
        ExtentManager.log(message);
    }

    protected void navigateTo(String url) {
        log("Navigate to: " + url);
        driver.get(url);
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    @DataProvider(name = "testData")
    public Object[][] testDataProvider(Method method) {
        return DataProviderUtil.getTestData(method);
    }
}
