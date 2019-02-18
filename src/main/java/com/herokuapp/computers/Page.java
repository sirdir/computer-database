package com.herokuapp.computers;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class Page {

    @Step
    public Page addNewPC() {

        $("#add").click();
        return this;
    }
}
