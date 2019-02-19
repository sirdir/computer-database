package com.herokuapp.computers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private final String INTRODUCED_DATE = "tbody > tr > td:nth-child(2)";
    private final String DISCONTINUED_DATE = "tbody > tr > td:nth-child(3)";
    private final String COMPANY_NAME_LINK = ".computers td > a";
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

    @Step
    public ExistedComputerPage openFirstComputer() {
        $$(COMPANY_NAME_LINK).first().click();

        return page(ExistedComputerPage.class);
    }

    @Step
    public String getIntroducedDate() {

        return $(INTRODUCED_DATE).text();
    }

    @Step
    public String getDiscontinuedDate() {

        return $(DISCONTINUED_DATE).text();
    }
}
