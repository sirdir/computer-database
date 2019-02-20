package com.herokuapp.computers.tests;

import com.codeborne.selenide.Selenide;
import com.herokuapp.computers.pages.NewComputerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.herokuapp.computers.utils.Config.LONG_NAME;

public class ComputerNameTest extends BaseTest {

    private String empty = "";

    @Test
    public void serverErrorTooLongRequest8KiB() {

        String message = Selenide.open("/new", NewComputerPage.class)
                .fillAndSubmitComputerFormFatal(LONG_NAME)
                .getErrorMessage();

        Assert.assertEquals(message, "Application error");
    }

    @Test(dataProvider = "singleChars", dataProviderClass = AllDataProviders.class)
    public void singleCharName(String name) {

        String actualName = open("/new", NewComputerPage.class)
                .fillAndSubmitComputerForm(name)
                .searchByName(name)
                .getFirstComputerName();

        Assert.assertEquals(actualName, name, "name of company in first column of grid");
    }

    @Test
    public void cantCreateEmptyComputerName() {

        boolean isValidationExist = open("/new", NewComputerPage.class)
                .fillAndSubmitComputerFormNegative(empty)
                .isComputerNameValidationHighlighted();

        Assert.assertTrue(isValidationExist, "computer field didn't highlighted");
    }

    @Test
    public void cantUpdateComputerNameToEmpty() {
        String cpName = "cp_" + System.nanoTime();

        boolean isValidationExist = open("/new", NewComputerPage.class)
                .fillAndSubmitComputerForm(cpName)
                .searchByName(cpName)
                .openFirstComputer()
                .fillAndSubmitComputerFormNegative(empty)
                .isComputerNameValidationHighlighted();

        Assert.assertTrue(isValidationExist, "computer field didn't highlighted");
    }
}
