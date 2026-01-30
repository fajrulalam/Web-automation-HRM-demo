package com.test.tests;

import com.test.base.BaseTest;
import com.test.data.TestData;
import com.test.pages.DashboardPage;
import com.test.pages.LoginPage;
import com.test.pages.NavBarTopPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(dataProvider = "testData", description = "Verify successful login with email and password")
    public void verifySuccessfulLoginThenLogout(TestData data) {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        NavBarTopPage navBarTop = new NavBarTopPage(driver);

        String username = data.get("userName");
        String password = data.get("password");

        step("Open the login page");
        loginPage.openLoginPage();

        step("Input Username and Password");
        loginPage.inputUserName(username);
        loginPage.inputPassword(password);

        step("Click 'Login' Button");
        loginPage.clickLoginButton();
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in");

        step("Logout");
        navBarTop.logout();

        Assert.assertTrue(loginPage.waitForPageToLoad(), "Ensure user is returned back to login page");
    }

    @Test(dataProvider = "testData", description = "Verify successful login with email and password")
    public void verifySuccessfulLogin(TestData data) {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        NavBarTopPage navBarTop = new NavBarTopPage(driver);

        String username = data.get("userName");
        String password = data.get("password");

        step("Open the login page");
        loginPage.openLoginPage();

        step("Input Username and Password");
        loginPage.inputUserName(username);
        loginPage.inputPassword(password);

        step("Click 'Login' Button");
        loginPage.clickLoginButton();
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in");

        step("Logout");
        navBarTop.logout();
    }
}
