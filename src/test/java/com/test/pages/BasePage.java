package com.test.pages;

import com.test.core.Locator;
import com.test.utils.ExtentManager;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final int DEFAULT_TIMEOUT_SECONDS = 30;
    private static final int PAGE_LOAD_TIMEOUT_SECONDS = 60;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }

    public BasePage(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    public void navigateTo(String url) {
        log("Navigate to: " + url);
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitForPageLoad() {
        waitForPageLoad(PAGE_LOAD_TIMEOUT_SECONDS);
    }

    public void waitForPageLoad(int timeoutSeconds) {
        log("Wait for page to load (max " + timeoutSeconds + "s)");
        WebDriverWait pageWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        pageWait.until(driver -> {
            String readyState = (String) ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState");
            return "complete".equals(readyState);
        });

        waitForJQueryAndAjax(timeoutSeconds);
    }

    private void waitForJQueryAndAjax(int timeoutSeconds) {
        WebDriverWait ajaxWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        try {
            ajaxWait.until(driver -> {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                Boolean jQueryDone = (Boolean) js.executeScript(
                        "return (typeof jQuery === 'undefined') || (jQuery.active === 0)");
                Boolean jsReady = js.executeScript("return document.readyState").equals("complete");
                return jQueryDone && jsReady;
            });
        } catch (Exception e) {
            log("jQuery/AJAX wait skipped (not present or timed out)");
        }
    }

    public void waitForNetworkIdle(int idleTimeMs) {
        log("Wait for network idle (" + idleTimeMs + "ms)");
        try {
            Thread.sleep(idleTimeMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public WebElement waitForVisible(Locator locator) {
        return waitForVisible(locator, DEFAULT_TIMEOUT_SECONDS);
    }

    public WebElement waitForVisible(Locator locator, int timeoutSeconds) {
        log("Wait for visible: " + locator.getName() + " (max " + timeoutSeconds + "s)");
        return waitWithStaleRetry(locator, timeoutSeconds,
            () -> new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator.getBy())));
    }

    public WebElement waitForClickable(Locator locator) {
        return waitForClickable(locator, DEFAULT_TIMEOUT_SECONDS);
    }

    public WebElement waitForClickable(Locator locator, int timeoutSeconds) {
        log("Wait for clickable: " + locator.getName() + " (max " + timeoutSeconds + "s)");
        return waitWithStaleRetry(locator, timeoutSeconds, 
            () -> new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator.getBy())));
    }

    private WebElement waitWithStaleRetry(Locator locator, int timeoutSeconds, java.util.function.Supplier<WebElement> action) {
        int maxRetries = 3;
        int[] retryWaits = {0, 2000, 3000};
        
        for (int i = 0; i < maxRetries; i++) {
            try {
                if (retryWaits[i] > 0) {
                    log("StaleElement retry " + i + " for " + locator.getName() + " - waiting " + (retryWaits[i] / 1000) + "s");
                    Thread.sleep(retryWaits[i]);
                }
                return action.get();
            } catch (StaleElementReferenceException e) {
                log("StaleElementReferenceException on: " + locator.getName() + " - " + locator.getBy().toString());
                if (i == maxRetries - 1) {
                    log("FAILED after " + maxRetries + " attempts due to stale element: " + locator.getName());
                    throw e;
                }
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting to retry: " + locator.getName(), ie);
            }
        }
        throw new StaleElementReferenceException("Failed to get stable element: " + locator.getName());
    }

    public WebElement waitForPresent(Locator locator) {
        return waitForPresent(locator, DEFAULT_TIMEOUT_SECONDS);
    }

    public WebElement waitForPresent(Locator locator, int timeoutSeconds) {
        log("Wait for present: " + locator.getName() + " (max " + timeoutSeconds + "s)");
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
    }

    public WebElement findElement(Locator locator) {
        return driver.findElement(locator.getBy());
    }

    public List<WebElement> findElements(Locator locator) {
        return driver.findElements(locator.getBy());
    }

    public void click(Locator locator) {
        log("Click on: " + locator.getName());
        
        int[] retryWaits = {0, 5000, 10000};
        Exception lastException = null;
        
        for (int i = 0; i < retryWaits.length; i++) {
            try {
                if (retryWaits[i] > 0) {
                    log("Retry " + i + " for " + locator.getName() + " - waiting " + (retryWaits[i] / 1000) + "s");
                    Thread.sleep(retryWaits[i]);
                }
                waitForClickable(locator).click();
                return;
            } catch (ElementClickInterceptedException e) {
                log("ElementClickInterceptedException on: " + locator.getName() + " - " + locator.getBy().toString());
                lastException = e;
            } catch (StaleElementReferenceException e) {
                log("StaleElementReferenceException on click: " + locator.getName() + " - " + locator.getBy().toString());
                lastException = e;
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting to retry click on: " + locator.getName(), ie);
            }
            
            if (i == retryWaits.length - 1) {
                log("FAILED after " + retryWaits.length + " attempts to click: " + locator.getName());
                throw new RuntimeException("Failed to click on " + locator.getName() + " after " + retryWaits.length + " attempts", lastException);
            }
        }
    }

    public void type(Locator locator, String text) {
        log("Type '" + text + "' into: " + locator.getName());
        WebElement element = waitForVisible(locator);
        clearFieldCompletely(element);
        element.sendKeys(text);
    }

    private void clearFieldCompletely(WebElement element) {
        element.clear();
        
        String currentValue = element.getAttribute("value");
        if (currentValue != null && !currentValue.isEmpty()) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
        }
        
        currentValue = element.getAttribute("value");
        if (currentValue != null && !currentValue.isEmpty()) {
            executeScript("arguments[0].value = '';", element);
        }
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void typeDateField(Locator locator, String dateText) {
        log("Type date '" + dateText + "' into: " + locator.getName());
        WebElement element = waitForVisible(locator);
        
        element.click();
        try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        clearFieldCompletely(element);
        
        element.sendKeys(dateText);
        
        String actualValue = element.getAttribute("value");
        if (actualValue != null && actualValue.length() > dateText.length()) {
            log("Detected double input, clearing and retrying...");
            clearFieldCompletely(element);
            element.sendKeys(dateText);
        }
        
        element.sendKeys(Keys.TAB);
    }

    public void sendKeys(Locator locator, Keys... keys) {
        log("Send keys to: " + locator.getName());
        WebElement element = waitForVisible(locator);
        element.sendKeys(keys);
    }

    public void sendKeys(Locator locator, String text) {
        log("Send keys '" + text + "' to: " + locator.getName());
        WebElement element = waitForVisible(locator);
        element.sendKeys(text);
    }

    public void typeAndSubmit(Locator locator, String text) {
        log("Type '" + text + "' and press ENTER: " + locator.getName());
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text, Keys.ENTER);
    }

    public String getText(Locator locator) {
        log("Get text from: " + locator.getName());
        return waitForVisible(locator).getText();
    }

    public String getAttribute(Locator locator, String attribute) {
        log("Get attribute '" + attribute + "' from: " + locator.getName());
        return waitForPresent(locator).getAttribute(attribute);
    }

    public boolean isDisplayed(Locator locator) {
        int maxRetries = 3;
        for (int i = 0; i < maxRetries; i++) {
            try {
                return findElement(locator).isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            } catch (StaleElementReferenceException e) {
                log("StaleElement in isDisplayed for " + locator.getName() + ", retry " + (i + 1));
                waitForNetworkIdle(500);
            }
        }
        return false;
    }

    public boolean isPresent(Locator locator) {
        int maxRetries = 3;
        for (int i = 0; i < maxRetries; i++) {
            try {
                return !findElements(locator).isEmpty();
            } catch (StaleElementReferenceException e) {
                log("StaleElement in isPresent for " + locator.getName() + ", retry " + (i + 1));
                waitForNetworkIdle(500);
            }
        }
        return false;
    }

    public boolean isEnabled(Locator locator) {
        try {
            return findElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public void scrollToElement(Locator locator) {
        log("Scroll to: " + locator.getName());
        WebElement element = findElement(locator);
        executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public void jsClick(Locator locator) {
        log("JS click on: " + locator.getName());
        WebElement element = findElement(locator);
        executeScript("arguments[0].click();", element);
    }

    protected void log(String message) {
        ExtentManager.log(message);
    }
}
