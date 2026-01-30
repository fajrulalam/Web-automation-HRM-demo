package com.test.pages;

import com.test.core.Locator;
import org.openqa.selenium.WebDriver;

public class DashboardPage  extends BasePage{

    private static final Locator DASHBOARD_HEADER = Locator.byXpath(
            "DASHBOARD_HEADER",
            "//h6[normalize-space()='Dashboard']"
    );

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public boolean isUserLoggedIn() {
        waitForPresent(DASHBOARD_HEADER);
        return isDisplayed(DASHBOARD_HEADER);
    }

    public boolean isAlreadyLoggedIn() {
        return isPresent(DASHBOARD_HEADER);
    }


}
