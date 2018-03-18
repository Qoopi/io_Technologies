package utils;

import org.openqa.selenium.JavascriptExecutor;
import webDriver.WebDriverManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UiUtils {


    public void scrollPageUp() {
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getDriver();
        jse.executeScript("scroll(0, -250);");
    }

    public void scrollPageDown() {
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getDriver();
        jse.executeScript("scroll(0,1000 );");
    }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("(dd.MM.yyyy) HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void refreshPage() {
        new JSWaiter().waitUntilJSReady();
        WebDriverManager.getDriver().navigate().refresh();
        new JSWaiter().waitUntilJSReady();
    }
}
