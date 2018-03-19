package pageObjects;

import credentials.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Step;
import utils.WaitsAsserts;


public class LogIn extends WaitsAsserts {
    private static final String baseUri = "https://onthe.io/auth";
    private static final String logInTittle = ".io analytics | Authorization";
    private static final String projectInTittle = "RUHIGHLOAD (PROD) – Home – IO Technologies";
    private static final String emailInput = "//*[@type=\"email\" and @name=\"email\"]";
    private static final String passwordInput = "//*[@type=\"password\" and @name=\"pwd\"]";
    private static final String logInButton = "//*[text()=\" Log in \"]";
    private static final String projectLink = "//a[descendant::li[text()=\" RuHighload (prod)\"]]";
    private static final String confirm = "//*[text()=\"Continue\"]";

    @Step
    public void getToLoginPage(){
        getPage(baseUri);
        assertTitleContains(logInTittle);
    }

    @Step
    public void enterCreds() {
        Credentials cred = new Credentials();
        waitForVisibility(emailInput);
        sendKeys(emailInput, cred.getEmail());

        waitForVisibility(passwordInput);
        sendKeys(passwordInput, cred.getPassword());
    }

    @Step
    public void clickLogInButton() {
        waitForVisibility(logInButton);
        waitForClickable(logInButton);
        click(logInButton);
    }

    @Step
    public void pickProject() {
        waitForVisibility(projectLink);
        waitForClickable(projectLink);
        click(projectLink);

    }

    @Step
    public void confirm() {
        waitForVisibility(confirm);
        waitForClickable(confirm);
        click(confirm);
        switchToCurrentWindow();
        assertTitleContains(projectInTittle);
    }

    @Step
    public void logIn(){
        getToLoginPage();
        enterCreds();
        clickLogInButton();
        pickProject();
        confirm();
    }

    public void getChart() {
        WebDriver driver = new ChromeDriver();
        String xyi = driver.findElement(By.xpath("gjgjgj")).getAttribute("ddf");
    }

}
