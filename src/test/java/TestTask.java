import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.Home;
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

    @Test
    public void checkDataToolTipsSwitcher() {
        Home home = new Home();
        home.openHomeReport();
        home.switchToRT();
        home.switchToLast10Minutes();
        home.switchToLastHour();
        home.switchToToDay();
        home.switchToYesterday();
        home.switchTo7Days();
        home.switchTo30Days();
    }

    @Test
    public void filterByAuthorCheck(){
        Home home = new Home();
        MainMenu menu = new MainMenu();
        menu.getToEditorable();
        home.openHomeReport();
        home.filterByAuthor();
    }
}
