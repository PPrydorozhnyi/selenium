package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import page.objects.InputField;

import static junit.framework.TestCase.assertEquals;

public class InputFieldTest {

    private static final String TEST_TEXT = "Test Text";
    private static InputField inputField;

    @BeforeClass
    public static void init() {
        Configuration.timeout = 6000;
        Configuration.browser = "chrome";
        inputField = new InputField();
    }

    @Before
    public void setUp() {
        Selenide.open("http://todomvc.com/examples/angularjs/");
    }

    @After
    public void close() {
        Selenide.close();
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
