package page.objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;

public class ListItem {
    private static final String COMPLETED_LIST_ITEM = "ul.todo-list li.completed";
    private static final String CHECK_BOX = "input.toggle";
    private static final String LIST_ITEM = "ul.todo-list li";
    private static final String CROSS_BUTTON = "button.destroy";

    public ElementsCollection getAllCompletedItems() {
        return $$(COMPLETED_LIST_ITEM);
    }

    public ListItem checkItem(int index) {
        ElementsCollection element = $$(CHECK_BOX);
        element.get(index).click();
        return this;
    }

    public void checkThatCompleted(int index) {
        getAllItems().get(index).shouldHave(Condition.cssClass("completed"));
    }

    public void checkThatNotCompleted(int index) {
        getAllItems().get(index).shouldNotHave(Condition.cssClass("completed"));
    }

    public ListItem removeItem(int index) {
        var listItem = getAllItems().get(index);
        var closeButton = $$(CROSS_BUTTON).get(index);
        actions()
                .moveToElement(listItem)
                .moveToElement(closeButton)
                .click()
                .build()
                .perform();
        return this;
    }

    public ElementsCollection getAllItems() {
        return $$(LIST_ITEM);
    }
}
