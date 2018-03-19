package pageObjects;

import ru.yandex.qatools.allure.annotations.Step;
import utils.WaitsAsserts;

/**
 * Created by oGGi on 18.03.2018.
 */
public class MainMenu extends WaitsAsserts {
    private static final String editorial = "//*[@data-title=\"editorial\"]";
    private static final String home = "//*[@qa-id=\"home\"]";
    private static final String dataHome = "//*[@class=\"data_home\"]";
    private static final String articles = "//*[@qa-id=\"articles\"]";
    private static final String dataArticles = "//*[@class=\"data_articles\"]";
    private static final String authors = "//*[@qa-id=\"authors\"]";
    private static final String dataAuthors = "//*[@class=\"data_authors\"]";
    private static final String blocks = "//*[@qa-id=\"blocks\"]";
    private static final String dataBlocks = "//*[@class=\"data_blocks\"]";


    private static final String video = "//*[@data-title=\"video\"]";
    private static final String dataVideos = "//*[@class=\"data_videos\"]";

    private static final String smm = "//*[@data-title=\"smm\"]";
    private static final String dataSMM = "//*[@class=\"data_smm\"]";

    @Step
    public void getToEditorable(){
        waitForClickable(editorial);
        click(editorial);
    }
    @Step
    public void checkMainMenu() {
        waitForVisibility(editorial);
        waitForVisibility(video);
        waitForVisibility(smm);
        assertExists(editorial);
        assertExists(video);
        assertExists(smm);
    }

    @Step
    public void checkDataHome() {
        waitForVisibility(home);
        assertExists(dataHome);
    }

    @Step
    public void articles() {
        waitForClickable(articles);
        click(articles);
        waitForVisibility(dataArticles);
        assertExists(dataArticles);
    }

    @Step
    public void authors() {
        waitForClickable(authors);
        click(authors);
        waitForVisibility(dataAuthors);
        assertExists(dataAuthors);
    }

    @Step
    public void blocks() {
        waitForClickable(blocks);
        click(blocks);
        waitForVisibility(dataBlocks);
        assertExists(dataBlocks);
    }

    @Step
    public void video() {
        waitForClickable(video);
        click(video);
        waitForVisibility(dataVideos);
        assertExists(dataVideos);
    }

    @Step
    public void smm() {
        waitForClickable(smm);
        click(smm);
        waitForVisibility(dataSMM);
        assertExists(dataSMM);
    }

}
