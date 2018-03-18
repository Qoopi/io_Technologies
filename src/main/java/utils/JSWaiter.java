package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import webDriver.WebDriverManager;

class JSWaiter {
    private int defaultTimeoutSec = 30;

    /**
     * as you can tell by it's name - this class represents waiter for js/angular/jquery for selenium
     */

    //Wait Until JS Ready
    void waitUntilJSReady() {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), defaultTimeoutSec);
        JavascriptExecutor jsExec = (JavascriptExecutor) WebDriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) WebDriverManager.getDriver())
                .executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("JS in NOT Ready!");
            //Wait for Javascript to load
            wait.until(jsLoad);
        }
    }

}
