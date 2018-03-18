package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.WaitsAsserts;

/**
 * Created by oGGi on 18.03.2018.
 */
public class LogIn extends WaitsAsserts {

    public void getChart() {
        WebDriver driver = new ChromeDriver();
        String xyi = driver.findElement(By.xpath("gjgjgj")).getAttribute("ddf");
    }

}
