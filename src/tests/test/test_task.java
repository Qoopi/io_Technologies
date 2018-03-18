import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.LogIn;
import utils.Listener;

import static utils.WaitsAsserts.sleep;

@Listeners(Listener.class)
public class test_task {

    @Test
    public void test() {
        LogIn log = new LogIn();
        log.getToLoginPage();
        log.LoginIntoApp();
        sleep(8000);
    }

}
