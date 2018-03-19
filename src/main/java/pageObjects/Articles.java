package pageObjects;

import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;
import utils.WaitsAsserts;

public class Articles extends WaitsAsserts {
    private static final String articles = "//*[@qa-id=\"articles\"]";
    private static final String slider = "//*[@class=\"slider\"]";
    private final static String dataList = "//*[@class=\"data_list data_list_pubs\"]";
    private final static String filterDropDown = "//*[@class=\"filter_title_value title filter_item {is_on}\"]";
    private final static String averageTime = "//*[@data-name=\"timeread\"]";
    private final static String read = "//*[@data-name=\"readability\"]";
    private final static String recirculation = "//*[@data-name=\"recirculation\"]";
    private final static String newratio = "//*[@data-name=\"pageviews_new_ratio\"]";
    private final static String fb = "//*[@data-name=\"fb\"]";

    @Step
    public void getToArticles() {
        waitForClickable(articles);
        click(articles);
    }

    @Step
    public void switchFilterAverageTime() {
        waitForClickable(filterDropDown);
        click(filterDropDown);
        waitForVisibility(averageTime);
        click(averageTime);
    }

    @Step
    public void switchFilterRead() {
        waitForClickable(filterDropDown);
        click(filterDropDown);
        waitForVisibility(read);
        click(read);
    }

    @Step
    public void switchFilterRecirculation() {
        waitForClickable(filterDropDown);
        click(filterDropDown);
        waitForVisibility(recirculation);
        click(recirculation);
    }

    @Step
    public void switchFilterNewRatio() {
        waitForClickable(filterDropDown);
        click(filterDropDown);
        waitForVisibility(newratio);
        click(newratio);
    }

    @Step
    public void switchFilterFB() {
        waitForClickable(filterDropDown);
        click(filterDropDown);
        waitForVisibility(fb);
        click(fb);
    }

    @Step
    public void checkIfListFResultsChanged() {
        waitForVisibility(dataList);
        String firstElementText = getElement(dataList);
        sliderClick();
        String secondElementText = getElement(dataList);
        Assert.assertNotEquals(firstElementText, secondElementText);
    }

    private void sliderClick() {
        waitForClickable(slider);
        click(slider);
    }
}
