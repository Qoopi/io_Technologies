package pageObjects;

import credentials.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Step;
import utils.WaitsAsserts;

public class LogIn extends WaitsAsserts {
    private static final String BaseURI = "https://onthe.io/auth";
    private static final String logInTittle = ".io analytics | Authorization";
    private static final String emailInput = "//*[@type=\"email\" and @name=\"email\"]";
    private static final String passwordInput = "//*[@type=\"password\" and @name=\"pwd\"]";

    @Step
    public void getToLoginPage(){
        getPage(BaseURI);
        assertTitleContains(logInTittle);
    }
    @Step
    public void LoginIntoApp(){
        Credentials cred = new Credentials();
        waitForVisibility(emailInput);
        sendKeys(emailInput, cred.getEmail());

        waitForVisibility(passwordInput);
        sendKeys(passwordInput, cred.getPassword());
    }
    public void getChart() {
        WebDriver driver = new ChromeDriver();
        String xyi = driver.findElement(By.xpath("gjgjgj")).getAttribute("ddf");
    }

}
