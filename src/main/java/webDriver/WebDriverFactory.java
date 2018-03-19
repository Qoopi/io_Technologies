package webDriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class WebDriverFactory {
    private static final String path = "src\\main\\resources\\drivers\\chromedriver.exe";
    public static final int webDriverImplicitlyWait = 40;

    public static WebDriver createInstance() {

        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", path);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.WARNING);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(webDriverImplicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        return driver;
    }

}