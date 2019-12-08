package selenide;

import org.junit.Test;
import page.objects.ControlBar;
import page.objects.InputField;
import page.objects.ListItem;

import static junit.framework.TestCase.assertEquals;
import static selenide.TestConstants.TEST_TEXT;

public class ControlBarTest extends TestEnv {

    private static final int SIZE = 5;
    private static final int SIZE_OF_CHECKED = 2;
    private InputField inputField;
    private ListItem listItem;
    private ControlBar controlBar;

    public ControlBarTest() {
        inputField = new InputField();
        listItem = new ListItem();
        controlBar = new ControlBar();
    }

    @Override
    public void setUp() {
        super.setUp();
        for (int i = 0; i < SIZE; ++i) {
            inputField.typeToDo(TEST_TEXT).submit();
        }

        for (int i = 0; i < SIZE_OF_CHECKED; ++i) {
            listItem.checkItem(i);
        }
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
