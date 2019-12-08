package selenide;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import page.objects.InputField;
import page.objects.ListItem;

import static com.codeborne.selenide.Selenide.open;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InputFieldTest {

    private static final String TEST_TEXT = "Test Text";
    private static InputField inputField;
    private static ListItem listItem;
    public static final int TIMES = 3;

    @BeforeClass
    public static void setUp() {
        Configuration.timeout = 6000;
        Configuration.browser = "chrome";
        open("http://todomvc.com/examples/angularjs/");
        inputField = new InputField();
        listItem = new ListItem();
    }

    @Test
    @Order(1)
    public void inputText() {
        inputField.typeToDo(TEST_TEXT);
        assertEquals(TEST_TEXT, inputField.getFieldText());
    }

    @Test
    @Order(2)
    public void eraseText() {
        inputField.clearToDo();
        assertEquals("", inputField.getFieldText());
    }

    @Test
    @Order(3)
    public void select() {
        for (int i = 0; i < TIMES; ++i) {
            inputField.typeToDo(TEST_TEXT).submit();
        }

        inputField.clickArrow();

        final var allCompletedItems = listItem.getAllCompletedItems();

        assertNotNull(allCompletedItems);
        assertEquals(TIMES, allCompletedItems.size());
    }

    @Test
    @Order(4)
    public void deselect() {

        inputField.clickArrow();

        final var allCompletedItems = listItem.getAllCompletedItems();

        assertNotNull(allCompletedItems);
        assertEquals(0, allCompletedItems.size());
    }
}
