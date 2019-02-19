package com.herokuapp.computers;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    final static String BASE_URL = "http://computer-database.herokuapp.com/computers";

    @BeforeSuite
    public void setUp() {
        //todo too verbose consider of delete
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeMethod
    public void openBaseUrl() {
        open(BASE_URL);
    }
}
