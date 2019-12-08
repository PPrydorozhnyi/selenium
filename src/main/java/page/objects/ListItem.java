package page.objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ListItem {
    private static final String COMPLETED_LIST_ITEM = "ul.todo-list li.completed";
    private static final String CHECK_BOX = "input.toggle";
    private static final String LIST_ITEM = "ul.todo-list li";
    private static final String CROSS_BUTTON = "button.destroy";
    private static final String TEXT_INPUT = "ul.todo-list li form input";

    public ElementsCollection getAllCompletedItems() {
        return $$(COMPLETED_LIST_ITEM);
    }

    public ListItem checkItem(int index) {
        ElementsCollection element = $$(CHECK_BOX);
        element.get(index).click();
        return this;
    }

    public void checkThatCompleted(int index) {
        getAllListItems().get(index).shouldHave(Condition.cssClass("completed"));
    }

    public void checkThatNotCompleted(int index) {
        getAllListItems().get(index).shouldNotHave(Condition.cssClass("completed"));
    }

    public ListItem removeItem(int index) {
        var listItem = getAllListItems().get(index);
        var closeButton = $$(CROSS_BUTTON).get(index);
        actions()
                .moveToElement(listItem)
                .moveToElement(closeButton)
                .click()
                .build()
                .perform();
        return this;
    }

    public ElementsCollection getAllListItems() {
        return $$(LIST_ITEM);
    }

    public ListItem doubleClickItem(int index) {
        actions()
                .doubleClick(getAllListItems().get(index))
                .build()
                .perform();
        return this;
    }

    public SelenideElement getTextInput() {
        return $(TEXT_INPUT);
    }

    public ListItem editName(String newName) {
        var itemName = getTextInput();
        itemName.append(newName);
        itemName.submit();
        return this;
    }
}
