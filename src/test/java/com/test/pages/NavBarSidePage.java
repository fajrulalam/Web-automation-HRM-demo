package com.test.pages;

import com.test.core.Locator;
import org.openqa.selenium.WebDriver;

public class NavBarSidePage extends BasePage {

    private static final Locator ADMIN = Locator.byXpath(
            "ADMIN",
            "//span[normalize-space()='Admin']"
    );
    private static final Locator PIM = Locator.byXpath(
            "PIM",
            "//span[normalize-space()='PIM']"
    );
    private static final Locator LEAVE = Locator.byXpath(
            "LEAVE",
            "//span[normalize-space()='Leave']"
    );
    private static final Locator TIME = Locator.byXpath(
            "TIME",
            "//span[normalize-space()='Time']"
    );
    private static final Locator RECRUITMENT = Locator.byXpath(
            "RECRUITMENT",
            "//span[normalize-space()='Recruitment']"
    );
    private static final Locator MY_INFO = Locator.byXpath(
            "MY_INFO",
            "//span[normalize-space()='My Info']"
    );
    private static final Locator PERFORMANCE = Locator.byXpath(
            "PERFORMANCE",
            "//span[normalize-space()='Performance']"
    );
    private static final Locator DASHBOARD = Locator.byXpath(
            "DASHBOARD",
            "//span[normalize-space()='Dashboard']"
    );
    private static final Locator DIRECTORY = Locator.byXpath(
            "DIRECTORY",
            "//span[normalize-space()='Directory']"
    );
    private static final Locator MAINTENANCE = Locator.byXpath(
            "MAINTENANCE",
            "//span[normalize-space()='Maintenance']"
    );
    private static final Locator CLAIM = Locator.byXpath(
            "CLAIM",
            "//span[normalize-space()='Claim']"
    );
    private static final Locator BUZZ = Locator.byXpath(
            "BUZZ",
            "//span[normalize-space()='Buzz']"
    );

    public enum MenuItem {
        ADMIN(NavBarSidePage.ADMIN),
        PIM(NavBarSidePage.PIM),
        LEAVE(NavBarSidePage.LEAVE),
        TIME(NavBarSidePage.TIME),
        RECRUITMENT(NavBarSidePage.RECRUITMENT),
        MY_INFO(NavBarSidePage.MY_INFO),
        PERFORMANCE(NavBarSidePage.PERFORMANCE),
        DASHBOARD(NavBarSidePage.DASHBOARD),
        DIRECTORY(NavBarSidePage.DIRECTORY),
        MAINTENANCE(NavBarSidePage.MAINTENANCE),
        CLAIM(NavBarSidePage.CLAIM),
        BUZZ(NavBarSidePage.BUZZ);

        private final Locator locator;

        MenuItem(Locator locator) {
            this.locator = locator;
        }

        public Locator getLocator() {
            return locator;
        }
    }

    public NavBarSidePage(WebDriver driver) {
        super(driver);
    }

    public NavBarSidePage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public void open(MenuItem menuItem) {
        click(menuItem.getLocator());
    }
}
