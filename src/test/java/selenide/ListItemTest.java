package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import page.objects.InputField;
import page.objects.ListItem;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ListItemTest {

    private static final String TEST_TEXT = "Test Text";
    public static final int TIMES = 3;
    private static InputField inputField;
    private static ListItem listItem;

    @BeforeClass
    public static void init() {
        Configuration.timeout = 6000;
        Configuration.browser = "chrome";
        inputField = new InputField();
        listItem = new ListItem();
    }

    @Before
    public void setUp() {
        Selenide.open("http://todomvc.com/examples/angularjs/");
        inputField.typeToDo(TEST_TEXT).submit();
        inputField.typeToDo(TEST_TEXT).submit();
    }

    @After
    public void close() {
        Selenide.close();
    }

    @Test
    public void check() {
        listItem.checkItem(1);

        listItem.checkThatNotCompleted(0);
        listItem.checkThatCompleted(1);
    }

    @Test
    public void uncheck() {
        listItem.checkItem(1).checkItem(1);

        listItem.checkThatNotCompleted(0);
        listItem.checkThatNotCompleted(1);
    }

    @Test
    public void select() {
        for (int i = 0; i < TIMES; ++i) {
            inputField.typeToDo(TEST_TEXT).submit();
        }

        inputField.clickArrow();

        final ElementsCollection allCompletedItems = listItem.getAllCompletedItems();

        assertNotNull(allCompletedItems);
        assertEquals(TIMES, allCompletedItems.size());
    }

    @Test
    public void deselect() {
        for (int i = 0; i < TIMES; ++i) {
            inputField.typeToDo(TEST_TEXT).submit();
        }

        inputField.clickArrow().clickArrow();

        final ElementsCollection allCompletedItems = listItem.getAllCompletedItems();

        assertNotNull(allCompletedItems);
        assertEquals(0, allCompletedItems.size());
    }

    @Test
    public void removeItem() {
        listItem.removeItem(1);

        assertEquals(1, listItem.getAllListItems().size());
    }

    @Test
    public void doubleClick() {
        final var textInput = listItem.doubleClickItem(1).getTextInput();
        assertNotNull(textInput);
        assertEquals(TEST_TEXT, textInput.getValue());
    }

}
