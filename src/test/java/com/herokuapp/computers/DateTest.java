package com.herokuapp.computers;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class DateTest extends BaseTest {

    private String companyName = "IBM";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u-MM-dd");


    @Test(dataProvider = "positiveDateEdges", dataProviderClass = AllDataProviders.class)
    public void positiveDateEdge(String intrDate, String discDate) {
        String createComputerName = "pc_" + System.nanoTime();

        BasePage basepage = open("/new", NewComputerPage.class)
                .fillAndSubmitComputerForm(createComputerName, intrDate, discDate, companyName)
                .searchByName(createComputerName);

        LocalDate actualIntrDate = basepage.getIntroducedLocalDate();
        LocalDate actualDiscDate = basepage.getDiscontinuedLocalDate();

        SoftAssert sf = new SoftAssert();
        sf.assertEquals(actualIntrDate, LocalDate.parse(intrDate, formatter));
        sf.assertEquals(actualDiscDate, LocalDate.parse(discDate, formatter));
        sf.assertAll();
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