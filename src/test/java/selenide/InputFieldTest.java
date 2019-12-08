package selenide;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import page.objects.InputField;

import static com.codeborne.selenide.Selenide.open;
import static junit.framework.TestCase.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InputFieldTest {

    private static final String TEST_TEXT = "Test Text";
    private static InputField inputField;

    @BeforeClass
    public static void setUp() {
        Configuration.timeout = 800000000;
        Configuration.browser = "chrome";
        open("http://todomvc.com/examples/angularjs/");
        inputField = new InputField();
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
}
