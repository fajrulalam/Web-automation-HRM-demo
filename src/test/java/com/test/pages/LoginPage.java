package com.test.pages;

import com.test.core.Locator;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final String URL = "https://opensource-demo.orangehrmlive.com/";

    private static final Locator LOGO = Locator.byXpath(
            "LOGO",
            "//img[@alt='company-branding']"
    );

    private static final Locator USERNAME_INPUT = Locator.byXpath(
            "USERNAME_INPUT",
            "//input[@placeholder='Username']"
    );

    private static final Locator PASSWORD_INPUT = Locator.byXpath(
            "PASSWORD_INPUT",
            "//input[@placeholder='Password']"
    );

    private static final Locator LOGIN_BUTTON = Locator.byXpath(
            "LOGIN_BUTTON",
            "//button[normalize-space()='Login']"
    );

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public void openLoginPage() {
        driver.get(URL);
    }

    public boolean waitForPageToLoad() {
        waitForPresent(LOGO);
        return isDisplayed(LOGO);
    }

    public void inputUserName(String username) {
        sendKeys(USERNAME_INPUT, username);
    }

    public void inputPassword(String password) {
        sendKeys(PASSWORD_INPUT, password);
    }

    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }

    public void login(String username, String password) {
        openLoginPage();
        waitForPageToLoad();
        inputUserName(username);
        inputPassword(password);
        clickLoginButton();
    }
}
