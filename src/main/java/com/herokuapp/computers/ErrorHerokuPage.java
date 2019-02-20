package com.herokuapp.computers;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ErrorHerokuPage {

    private static final String FRAME = "iframe[src$='application-error.html']";
    private static final String ERR_TEXT = ".message__title";

    @Step
    public String getErrorMessage() {
        switchTo().frame($(FRAME));

        return $(ERR_TEXT).text();
    }


}
