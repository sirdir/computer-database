package com.herokuapp.computers.tests;

import com.codeborne.selenide.Selenide;
import com.herokuapp.computers.pages.BasePage;
import com.herokuapp.computers.pages.NewComputerPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.open;
import static com.herokuapp.computers.utils.Config.CREATE_MESSAGE;
import static com.herokuapp.computers.utils.Config.DEFAULT_COMPANY;

public class DateTest extends BaseTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u-MM-dd");
    private String empty = "";


    @Test(dataProvider = "positiveDateEdges", dataProviderClass = AllDataProviders.class)
    public void positiveDateEdge(String intrDate, String discDate) {
        String createComputerName = "pc_" + System.nanoTime();

        BasePage basepage = Selenide.open("/new", NewComputerPage.class)
                .fillAndSubmitComputerForm(createComputerName, intrDate, discDate, DEFAULT_COMPANY)
                .searchByName(createComputerName);

        LocalDate actualIntrDate = basepage.getIntroducedLocalDate();
        LocalDate actualDiscDate = basepage.getDiscontinuedLocalDate();

        SoftAssert sf = new SoftAssert();
        sf.assertEquals(actualIntrDate, LocalDate.parse(intrDate, formatter));
        sf.assertEquals(actualDiscDate, LocalDate.parse(discDate, formatter));
        sf.assertAll();
    }

    @Test
    public void introducedWithoutDiscontinued() {
        String createComputerName = "pc_" + System.nanoTime();
        String intrDate = "2018-10-10";

        String actualMessage = open("/new", NewComputerPage.class)
                .fillAndSubmitComputerForm(createComputerName, intrDate, empty, DEFAULT_COMPANY)
                .getMessage();

        String expMessage = String.format(CREATE_MESSAGE, createComputerName);

        Assert.assertEquals(actualMessage, expMessage, "alert message");
    }

    @Test
    public void discontinuedWithoutIntroduced() {
        String createComputerName = "pc_" + System.nanoTime();
        String discDate = "2019-12-01";

        boolean isDiscValidationHighlighted = open("/new", NewComputerPage.class)
                .fillAndSubmitComputerFormNegative(createComputerName, empty, discDate)
                .isDiscontinuedValidationHighlighted();

        Assert.assertTrue(isDiscValidationHighlighted, "red border");
    }


    @Test(dataProvider = "negativeDateEdges", dataProviderClass = AllDataProviders.class)
    public void negativeDateEdge(String intrDate, String discDate) {
        String createComputerName = "pc_" + System.nanoTime();

        NewComputerPage computerPage = open("/new", NewComputerPage.class)
                .fillAndSubmitComputerFormNegative(createComputerName, intrDate, discDate);

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(computerPage.isIntroducedValidationHighlighted(), "red border");
        sf.assertTrue(computerPage.isDiscontinuedValidationHighlighted(), "red border");
        sf.assertAll();
    }

}