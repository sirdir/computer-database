package com.herokuapp.computers;

import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private static final String INTRODUCED_DATE = "tbody > tr > td:nth-child(2)";
    private static final String DISCONTINUED_DATE = "tbody > tr > td:nth-child(3)";
    private static final String COMPANY_NAME_LINK = ".computers td > a";
    private static final String ADD_BUTTON = "#add";
    private static final String SEARCH_INPUT = "#searchbox";
    private static final String ALERT_TEXT = ".alert-message";

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
    public LocalDate getIntroducedLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM u");
        String text = $(INTRODUCED_DATE).text();

        return LocalDate.parse(text, formatter);
    }

    @Step
    public LocalDate getDiscontinuedLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM u");
        String text = $(DISCONTINUED_DATE).text();

        return LocalDate.parse(text, formatter);
    }
}
