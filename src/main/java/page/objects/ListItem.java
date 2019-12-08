package page.objects;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class ListItem {
    private static final String COMPLETED_LIST_ITEM = "ul.todo-list li.completed";

    public ElementsCollection getAllCompletedItems() {
        return $$(COMPLETED_LIST_ITEM);
    }
}
