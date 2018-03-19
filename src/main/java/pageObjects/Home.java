package pageObjects;

import ru.yandex.qatools.allure.annotations.Step;
import utils.WaitsAsserts;

public class Home extends WaitsAsserts {
    private static final String home = "//*[@qa-id=\"home\"]";
    //Data tool tips
    private final static String realTIme = "//*[@data-tooltip=\"Realtime\"]";
    private final static String last10minutes = "//*[@data-tooltip=\"Last 10 minutes\"]";
    private final static String lastHour = "//*[@data-tooltip=\"Last hour\"]";
    private final static String toDay = "//*[@data-tooltip=\"Today\"]";
    private final static String yesterday = "//*[@data-tooltip=\"Yesterday\"]";
    private final static String last7days = "//*[@data-tooltip=\"Last 7 days\"]";
    private final static String last30days = "//*[@data-tooltip=\"Last 30 days\"]";

    private final static String textXpath = "//*[@id=\"choose_date_input\"]";
    private final static String textToAssertRT = "Last minute";
    private final static String textToAssert10min = "Last 10 minutes";
    private final static String textToAssertLastHour = "Last hour";
    private final static String textToAssertToDay = "Today";
    private final static String textToAssertYesterday = "Yesterday";

    // filter by mane
    private final static String filterByAuthor = "//*[@class=\"row item \" and @data-id=\"72747\"]";
    private final static String dataList = "//*[@class=\"data_list data_list_pubs\"]";

    @Step
    public void openHomeReport() {
        waitForClickable(home);
        click(home);
    }

    @Step
    public void switchToRT() {
        waitForClickable(realTIme);
        click(realTIme);
        sleep(5000);
        assertText(textXpath, textToAssertRT);
    }

    @Step
    public void switchToLast10Minutes() {
        waitForClickable(last10minutes);
        click(last10minutes);
        sleep(5000);
        assertText(textXpath, textToAssert10min);
    }

    @Step
    public void switchToLastHour() {
        waitForClickable(lastHour);
        click(lastHour);
        sleep(2000);
        assertText(textXpath, textToAssertLastHour);
    }

    @Step
    public void switchToToDay() {
        waitForClickable(toDay);
        click(toDay);
        sleep(2000);
        assertText(textXpath, textToAssertToDay);
    }

    @Step
    public void switchToYesterday() {
        waitForClickable(yesterday);
        click(yesterday);
        sleep(2000);
        assertText(textXpath, textToAssertYesterday);
    }

    @Step
    public void switchTo7Days() {
        waitForClickable(last7days);
        click(last7days);
    }

    @Step
    public void switchTo30Days() {
        waitForClickable(last30days);
        click(last30days);

    }

    @Step
    public void filterByAuthor(){
        waitForClickable(filterByAuthor);
        switchTo30Days();
        click(filterByAuthor);
        sleep(3000);
        getElements(dataList);
    }

}
