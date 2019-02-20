package com.herokuapp.computers.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeSuite;

import static com.herokuapp.computers.utils.Config.BASE_URL;

public class BaseTest {

    @BeforeSuite
    public void setUp() {
        //todo verbose, consider of delete
        Configuration.baseUrl = BASE_URL;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }
}
