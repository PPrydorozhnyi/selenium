package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import page.objects.ControlBar;
import page.objects.InputField;
import page.objects.ListItem;

import static junit.framework.TestCase.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControlBarTest {

    public static final int SIZE = 5;
    public static final int SIZE_OF_CHECKED = 2;
    private static final String TEST_TEXT = "Test Text";
    private static InputField inputField;
    private static ListItem listItem;
    private static ControlBar controlBar;

    @BeforeClass
    public static void init() {
        Configuration.timeout = 6000;
        Configuration.browser = "chrome";
        inputField = new InputField();
        listItem = new ListItem();
        controlBar = new ControlBar();
    }

    @Before
    public void setUp() {
        Selenide.open("http://todomvc.com/examples/angularjs/");
        for (int i = 0; i < SIZE; ++i) {
            inputField.typeToDo(TEST_TEXT).submit();
        }

        for (int i = 0; i < SIZE_OF_CHECKED; ++i) {
            listItem.checkItem(i);
        }
    }

    @After
    public void close() {
        Selenide.close();
    }

    @Test
    public void all() {
        controlBar.clickAll();
        assertEquals(SIZE, listItem.getAllListItems().size());
    }

    @Test
    public void active() {
        controlBar.clickActive();
        assertEquals(SIZE - SIZE_OF_CHECKED, listItem.getAllListItems().size());
    }

    @Test
    public void completed() {
        controlBar.clickCompleted();
        assertEquals(SIZE_OF_CHECKED, listItem.getAllListItems().size());
    }

    @Test
    public void clearCompleted() {
        controlBar.clickClearCompleted();
        assertEquals(SIZE - SIZE_OF_CHECKED, listItem.getAllListItems().size());
    }
}
