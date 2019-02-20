package com.herokuapp.computers.pages;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class NewComputerPage extends ComputerForm {

    private static final String SUBMIT_BUTTON = "input[value='Create this computer']";

    @Step
    private void submitComputerForm() {
        $(SUBMIT_BUTTON).click();
    }

    @Step
    public BasePage fillAndSubmitComputerForm(String name, String intrDate, String discDate, String companyName) {
        fillComputerForm(name, intrDate, discDate, companyName);
        submitComputerForm();

        return page(BasePage.class);
    }

    @Step
    public NewComputerPage fillAndSubmitComputerFormNegative(String name, String intrDate, String discDate) {
        fillComputerForm(name, intrDate, discDate);
        submitComputerForm();

        return this;
    }

    @Step
    public ErrorHerokuPage fillAndSubmitComputerFormFatal(String name) {
        Configuration.fastSetValue = true;
        try {
            fillComputerForm(name);
        }
        finally {
            Configuration.fastSetValue = false;
        }
        submitComputerForm();

        return page(ErrorHerokuPage.class);
    }

    @Step
    public BasePage fillAndSubmitComputerForm(String name) {
        fillComputerForm(name);
        submitComputerForm();

        return page(BasePage.class);
    }

    @Step
    public NewComputerPage fillAndSubmitComputerFormNegative(String name) {
        fillComputerForm(name);
        submitComputerForm();

        return page(NewComputerPage.class);
    }

}
