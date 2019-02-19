package com.herokuapp.computers;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ExistedComputerPage extends ComputerForm {

    private final String SUBMIT_BUTTON = "input[value='Save this computer']";
    private final String DELETE_BUTTON = "input[value='Delete this computer']";

    @Step
    private void submitComputerForm() {
        $(SUBMIT_BUTTON).click();
    }

    @Step
    public BasePage deleteComputer() {
        $(DELETE_BUTTON).click();

        return page(BasePage.class);
    }

    @Step
    public BasePage fillAndSubmitComputerForm(String name, String startDate, String endDate, String companyName) {
        fillComputerForm(name, startDate, endDate, companyName);
        submitComputerForm();

        return page(BasePage.class);
    }

}
