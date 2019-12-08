package selenide;

import org.junit.Test;
import page.objects.InputField;

import static junit.framework.TestCase.assertEquals;
import static selenide.TestConstants.TEST_TEXT;

public class InputFieldTest extends TestEnv {

    private static InputField inputField;

    public InputFieldTest() {
        inputField = new InputField();
    }

    @Test
    public void inputText() {
        inputField.typeToDo(TEST_TEXT);
        assertEquals(TEST_TEXT, inputField.getFieldText());
    }

    @Test
    public void eraseText() {
        inputField.typeToDo(TEST_TEXT);
        inputField.clearToDo();
        assertEquals("", inputField.getFieldText());
    }
}
