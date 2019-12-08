package page.objects;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class InputField {

    private static final String INPUT_FIELD = "input.new-todo";

    private static final By ARROW_BUTTON = By.cssSelector("label[for='toggle-all']");

    public InputField typeToDo(String todo) {
        $(INPUT_FIELD).sendKeys(todo);
        return this;
    }

    public InputField clearToDo() {
        $(INPUT_FIELD).clear();
        return this;
    }

    public InputField submit() {
        $(INPUT_FIELD).submit();
        return this;
    }

    public InputField clickArrow() {
        $(ARROW_BUTTON).click();
        return this;
    }

    public String getFieldText() {
        return $(INPUT_FIELD).getValue();
    }

}
