package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import webDriver.WebDriverFactory;
import webDriver.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class WaitsAsserts {
    private int defaultTimeoutSec = 20;
    private int maxRetries = 4;

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void clearInput(String inputXpath) {
        WebDriverManager.getDriver().findElement(By.xpath(inputXpath)).clear();
    }

    protected void getPage(String url) {
        WebDriver driver = WebDriverManager.getDriver();
        driver.get(url);
    }

    protected void assertExists(String xpath) {
        new JSWaiter().waitUntilJSReady();
        Assert.assertTrue(elementExists(xpath), AssertMessages.elementIsNotFound);
    }

    protected void assertNotExists(String xpath) {
        new JSWaiter().waitUntilJSReady();
        Assert.assertFalse(elementExists(xpath), AssertMessages.elementIsFound);
    }

    protected void switchToCurrentWindow() {
        for (String winHandle : WebDriverManager.getDriver().getWindowHandles ()){
            WebDriverManager.getDriver().switchTo().window(winHandle);
        }
    }



    protected void assertText(String xpath, String text) {
        waitForVisibility(xpath);
        String anElementTile = getText(xpath);
        Assert.assertTrue(anElementTile.contains(text), AssertMessages.elementTextIsNotAsExpected);
    }

    protected void assertVisible(String xpath) {
        WebDriver driver = WebDriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeoutSec);
        new JSWaiter().waitUntilJSReady();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed(), AssertMessages.elementIsNotVisible);
    }

    protected void waitForVisibility(String xpath) {
        WebDriver driver = WebDriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeoutSec, 100);
        new JSWaiter().waitUntilJSReady();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected void waitForClickable(String xpath) {
        new JSWaiter().waitUntilJSReady();
        WebDriver driver = WebDriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeoutSec);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    protected boolean elementExists(String xpath) {
        new JSWaiter().waitUntilJSReady();
        WebDriver driver = WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        boolean exists = !driver.findElements(By.xpath(xpath)).isEmpty();
        driver.manage().timeouts().implicitlyWait(WebDriverFactory.webDriverImplicitlyWait, TimeUnit.SECONDS);
        return exists;
    }

    protected int elementsCount(String xpath) {
        new JSWaiter().waitUntilJSReady();
        WebDriver driver = WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int elementCount = driver.findElements(By.xpath(xpath)).size();
        driver.manage().timeouts().implicitlyWait(WebDriverFactory.webDriverImplicitlyWait, TimeUnit.SECONDS);
        return elementCount;
    }

    protected String getText(String xpath) {
        new JSWaiter().waitUntilJSReady();
        WebDriver driver = WebDriverManager.getDriver();
        String text = null;
        int attempts = 0;
        while (text == null && attempts < maxRetries) {
            try {
                text = driver.findElement(By.xpath(xpath)).getText();
                break;
            } catch (WebDriverException e) {
                sleep(500);
            }
            attempts++;
        }
        if (attempts != 0 || text == null) {
            System.out.println("Retrying get text:");
            System.out.println("Attempts: " + attempts);
            System.out.println("Result :" + text);
        }
        return text;
    }

    protected boolean sendKeys(String xpath, String keys) {
        new JSWaiter().waitUntilJSReady();
        waitForVisibility(xpath);
        WebElement element = retryingFindElementByXpath(xpath);
        boolean result = false;
        int attempts = 0;
        while (result == false && attempts <= maxRetries) {
            try {
                element.clear();
                element.sendKeys(keys);
                result = true;
                break;
            } catch (WebDriverException e) {
                sleep(500);
                result = false;
            }
            attempts++;
        }
        if (attempts != 0 || !result) {
            System.out.println("Retrying send keys:");
            System.out.println("Attempts: " + attempts);
            System.out.println("Result :" + result);
        }
        if (!result) {
            System.out.println("FAILED TO SEND KEYS TO ELEMENT: '" + xpath + "'");
        }
        sleep(200);
        return result;
    }

    protected boolean sendKeys(String xpath, Keys keys) {
        new JSWaiter().waitUntilJSReady();
        WebElement element = retryingFindElementByXpath(xpath);
        boolean result = false;
        int attempts = 0;
        while (result == false && attempts <= maxRetries) {
            try {
                element.sendKeys(keys);
                result = true;
                break;
            } catch (WebDriverException e) {
                sleep(500);
                result = false;
            }
            attempts++;
        }
        if (attempts != 0 || !result) {
            System.out.println("Retrying send keys:");
            System.out.println("Attempts: " + attempts);
            System.out.println("Result :" + result);
        }
        if (!result) {
            System.out.println("FAILED TO SEND KEYS TO ELEMENT: '" + xpath + "'");
        }
        sleep(200);
        return result;
    }

    protected WebElement retryingFindElementByXpath(String xpath) {
        WebDriver driver = WebDriverManager.getDriver();
        WebElement element = null;
        int attempts = 0;
        while (element == null && attempts <= maxRetries) {
            try {
                element = driver.findElement(By.xpath(xpath));
                break;
            } catch (WebDriverException e) {
                sleep(500);
            }
            attempts++;
        }
        if (element == null) {
            System.out.println("FAILED FIND ELEMENT: '" + xpath + "'");
        }
        Assert.assertTrue(element != null, AssertMessages.elementIsNotFound + " ' " + xpath + " ' ");
        return element;
    }

    protected String getElementAttribute(String xpath, String attribute) {
        new JSWaiter().waitUntilJSReady();
        waitForVisibility(xpath);
        WebDriver driver = WebDriverManager.getDriver();
        String elementAttribute = null;
        int attempts = 0;
        while (elementAttribute == null && attempts <= maxRetries) {
            try {
                WebElement element = driver.findElement(By.xpath(xpath));
                elementAttribute = element.getAttribute(attribute);
                break;
            } catch (WebDriverException e) {
                sleep(500);
            }
            attempts++;
        }
        if (elementAttribute == null) {
            System.out.println("FAILED TO GET ELEMENT'S ATTRIBUTE: '" + xpath + "'");
        }
        return elementAttribute;
    }

    /**
     * Use this method to click on elements like drop-down menu items
     * Otherwise, material-ui may bring you a lot of difficulties
     */
    protected void clickAnimated(String xpath) {
        waitForVisibility(xpath);
        waitForClickable(xpath);
        retryingClickByXpath(xpath);
    }

    /**
     * Use this method to click on elements, which might be below or above current area of visibility
     */
    protected boolean click(String xpath) {
        new JSWaiter().waitUntilJSReady();
        boolean result;
        result = new WaitsAsserts().retryingClickByXpath(xpath);
        if (!result) {
            System.out.println("Scrolling page down...");
            WaitsAsserts.sleep(500);
            ((JavascriptExecutor) WebDriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", retryingFindElementByXpath(xpath));
            WaitsAsserts.sleep(500);
            result = new WaitsAsserts().retryingClickByXpath(xpath);
        }
        if (!result) {
            System.out.println("Scrolling page up...");
            WaitsAsserts.sleep(500);
            ((JavascriptExecutor) WebDriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", retryingFindElementByXpath(xpath));
            WaitsAsserts.sleep(500);
            result = new WaitsAsserts().retryingClickByXpath(xpath);
        }
        if (!result) {
            System.out.println("FAILED TO CLICK ON ELEMENT: '" + xpath + "'");
        }
        return result;
    }

    private boolean retryingClickByXpath(String xpath) {
        new JSWaiter().waitUntilJSReady();
        sleep(100);
        boolean result = false;
        int attempts = 0;
        while (!result && attempts < maxRetries) {
            try {
                WebDriverManager.getDriver().findElement(By.xpath(xpath)).click();
                result = true;
                break;
            } catch (WebDriverException e) {
                sleep(500);
            }
            attempts++;
        }
        if (attempts != 0 || !result) {
            System.out.println("Retrying click:");
            System.out.println("Attempts: " + attempts);
            System.out.println("Result :" + result);
        }
        if (!result) {
            System.out.println("FAILED TO CLICK ON ELEMENT: '" + xpath + "'");
        }
        return result;
    }

    protected void assertTitleContains(String title) {
        new JSWaiter().waitUntilJSReady();
        String currentTitle = WebDriverManager.getDriver().getTitle();
        Assert.assertTrue(currentTitle.contains(title), AssertMessages.elementTextIsNotAsExpected);

    }

}