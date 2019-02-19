package com.herokuapp.computers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public abstract class ComputerForm {

    private final String COMPUTER_NAME_INPUT = "#name";
    private final String INTRODUCED_DATE_INPUT = "#introduced";
    private final String DISCONTINUED_DATE_INPUT = "#discontinued";
    private final String COMPANY_SELECTOR = "#company";

    @Step
    void fillComputerForm(String name, String startDate, String endDate, String companyName) {
        $(COMPUTER_NAME_INPUT).val(name);
        $(INTRODUCED_DATE_INPUT).val(startDate);
        $(DISCONTINUED_DATE_INPUT).val(endDate);
        $(COMPANY_SELECTOR).selectOptionContainingText(companyName);
    }

    @Step
    void fillComputerForm(String name, String startDate, String endDate) {
        $(COMPUTER_NAME_INPUT).val(name);
        $(INTRODUCED_DATE_INPUT).val(startDate);
        $(DISCONTINUED_DATE_INPUT).val(endDate);
    }

    @Step
    public String getCompanyName() {

        return $(COMPUTER_NAME_INPUT).val();
    }

    @Step
    public String getIntroducedDate() {

        return $(INTRODUCED_DATE_INPUT).val();
    }

    @Step
    public String getDiscontinuedDate() {

        return $(DISCONTINUED_DATE_INPUT).val();
    }

    @Step
    public String getCompany() {

        return $(COMPANY_SELECTOR).getSelectedText();
    }

    @Step
    public boolean isIntroducedValidationHighlighted() {

        return $(INTRODUCED_DATE_INPUT)
                .parent()
                .parent()
                .is(cssClass("error"));
    }

    @Step
    public boolean isDiscontinuedValidationHighlighted() {

        return $(DISCONTINUED_DATE_INPUT)
                .parent()
                .parent()
                .is(cssClass("error"));
    }
}
