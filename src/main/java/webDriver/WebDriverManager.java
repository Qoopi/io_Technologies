package webDriver;


import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (webDriver.get() != null) {
            try {
                webDriver.get().getTitle();
            } catch (NoSuchSessionException e) {
                webDriver.get().quit();
                WebDriverManager.setWebDriver(WebDriverFactory.createInstance("chrome"));
            }
        }
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
}