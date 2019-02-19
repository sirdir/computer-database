package com.herokuapp.computers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BasePage {

    private final String ADD_BUTTON = "#add";
    private final String SEARCH_INPUT = "#searchbox";
    private final String ALERT_TEXT = ".alert-message";

    @Step
    public NewComputerPage addNewPC() {

        $(ADD_BUTTON).click();
        return page(NewComputerPage.class);
    }

    @Step
    public BasePage searchByName(String searchQuery) {

        $(SEARCH_INPUT)
                .val(searchQuery)
                .submit();
        return this;
    }

    @Step
    public String getMessage() {

        return $(ALERT_TEXT).should(visible).text();
    }
}
