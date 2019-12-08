package page.objects;

import static com.codeborne.selenide.Selenide.$;

public class ControlBar {

    private static final String ALL_BUTTON = "ul.filters li a[href='#/']";
    private static final String ACTIVE_BUTTON = "ul.filters li a[href='#/active']";
    private static final String COMPLETED_BUTTON = "ul.filters li a[href='#/completed']";
    private static final String CLEAR_COMPLETED_BUTTON = "button.clear-completed";

    public ControlBar clickAll() {
        return clickButton(ALL_BUTTON);
    }

    public ControlBar clickActive() {
        return clickButton(ACTIVE_BUTTON);
    }

    public ControlBar clickCompleted() {
        return clickButton(COMPLETED_BUTTON);
    }

    public ControlBar clickClearCompleted() {
        return clickButton(CLEAR_COMPLETED_BUTTON);
    }

    private ControlBar clickButton(String buttonIdentity) {
        $(buttonIdentity).click();
        return this;
    }
}
