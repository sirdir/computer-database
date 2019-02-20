package com.herokuapp.computers.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public abstract class ComputerForm {

    private static final String COMPUTER_NAME_INPUT = "#name";
    private static final String INTRODUCED_DATE_INPUT = "#introduced";
    private static final String DISCONTINUED_DATE_INPUT = "#discontinued";
    private static final String COMPANY_SELECTOR = "#company";

    @Step
    void fillComputerForm(String name, String intrDate, String discDate, String companyName) {
        $(COMPUTER_NAME_INPUT).val(name);
        $(INTRODUCED_DATE_INPUT).val(intrDate);
        $(DISCONTINUED_DATE_INPUT).val(discDate);
        $(COMPANY_SELECTOR).selectOptionContainingText(companyName);
    }

    @Step
    void fillComputerForm(String name, String intrDate, String discDate) {
        $(COMPUTER_NAME_INPUT).val(name);
        $(INTRODUCED_DATE_INPUT).val(intrDate);
        $(DISCONTINUED_DATE_INPUT).val(discDate);
    }
    @Step
    void fillComputerForm(String name) {
        $(COMPUTER_NAME_INPUT).val(name);
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
                .should(visible)
                .parent()
                .parent()
                .is(cssClass("error"));
    }

    @Step
    public boolean isDiscontinuedValidationHighlighted() {

        return $(DISCONTINUED_DATE_INPUT)
                .should(visible)
                .parent()
                .parent()
                .is(cssClass("error"));
    }

    @Step
    public boolean isComputerNameValidationHighlighted() {

        return $(COMPUTER_NAME_INPUT)
                .should(visible)
                .parent()
                .parent()
                .is(cssClass("error"));
    }
}
