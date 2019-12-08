package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestEnv {

    @BeforeClass
    public static void init() {
        Configuration.timeout = 6000;
        Configuration.browser = "chrome";
    }

    @Before
    public void setUp() {
        Selenide.open("http://todomvc.com/examples/angularjs/");
    }

    @After
    public void close() {
        Selenide.close();
    }
}
