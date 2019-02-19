package com.herokuapp.computers;

import io.qameta.allure.Step;

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

}
