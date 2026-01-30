package com.test.pages;

import com.test.core.Locator;
import org.openqa.selenium.WebDriver;

public class NavBarTopPage extends BasePage {

    private static final Locator USER_DROPDOWN = Locator.byXpath(
            "USER_DROPDOWN",
            "//span[@class='oxd-userdropdown-tab']"
    );

    private static final Locator LOGOUT_OPTION = Locator.byXpath(
            "LOGOUT_OPTION",
            "//a[normalize-space()='Logout']"
    );

    public NavBarTopPage(WebDriver driver) {
        super(driver);
    }

    public NavBarTopPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public void logout() {
        click(USER_DROPDOWN);
        waitForVisible(LOGOUT_OPTION);
        click(LOGOUT_OPTION);
    }








}