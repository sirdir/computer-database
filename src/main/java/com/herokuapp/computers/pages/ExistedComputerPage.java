package com.herokuapp.computers.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ExistedComputerPage extends ComputerForm {

    private static final String SUBMIT_BUTTON = "input[value='Save this computer']";
    private static final String DELETE_BUTTON = "input[value='Delete this computer']";

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
    public BasePage fillAndSubmitComputerForm(String name, String intrDate, String discDate, String companyName) {
        fillComputerForm(name, intrDate, discDate, companyName);
        submitComputerForm();

        return page(BasePage.class);
    }


    @Step
    public ExistedComputerPage fillAndSubmitComputerFormNegative(String name) {
        fillComputerForm(name);
        submitComputerForm();

        return this;
    }

}
