import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.LogIn;
import pageObjects.MainMenu;
import utils.Listener;

@Listeners(Listener.class)
public class TestTask {

    @Test()
    public void checkMainMenu() {
        MainMenu menu = new MainMenu();
        menu.checkMainMenu();
        menu.checkDataHome();
        menu.articles();
        menu.authors();
        menu.blocks();
        menu.video();
        menu.smm();
    }

}
