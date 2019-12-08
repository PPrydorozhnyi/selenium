package selenide;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;
import page.objects.InputField;
import page.objects.ListItem;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static selenide.TestConstants.TEST_TEXT;

public class ListItemTest extends TestEnv {

    private static final String EDIT_NAME = " Edit";
    private static final int SIZE = 2;
    private InputField inputField;
    private ListItem listItem;

    public ListItemTest() {
        inputField = new InputField();
        listItem = new ListItem();
    }

    public void setUp() {
        super.setUp();
        inputField.typeToDo(TEST_TEXT).submit();
        inputField.typeToDo(TEST_TEXT).submit();
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

        inputField.clickArrow();

        final ElementsCollection allCompletedItems = listItem.getAllCompletedItems();

        assertNotNull(allCompletedItems);
        assertEquals(SIZE, allCompletedItems.size());
    }

    @Test
    public void deselect() {

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

    @Test
    public void edit() {
        listItem.doubleClickItem(0).editName(EDIT_NAME);
        assertEquals(TEST_TEXT, listItem.getAllListItems().get(1).getText());
        assertEquals(TEST_TEXT + EDIT_NAME, listItem.getAllListItems().get(0).getText());
    }

}
