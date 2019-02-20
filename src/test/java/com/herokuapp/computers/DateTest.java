package com.herokuapp.computers;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.page;

public class DateTest extends BaseTest {

    private String companyName = "IBM";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u-MM-dd");




    @Test(dataProvider = "positiveDateEdges", dataProviderClass = AllDataProviders.class)
    public void positiveDateEdge(String date) {
        String createComputerName = "pc_" + System.nanoTime();

        BasePage basepage = page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, date, date, companyName)
                .searchByName(createComputerName);

        LocalDate actualIntrDate = basepage.getIntroducedLocalDate();
        LocalDate actualDiscDate = basepage.getDiscontinuedLocalDate();

        SoftAssert sf = new SoftAssert();
        sf.assertEquals(actualIntrDate, LocalDate.parse(date, formatter));
        sf.assertEquals(actualDiscDate, LocalDate.parse(date, formatter));
        sf.assertAll();
    }

    @Test(dataProvider = "negativeDateEdges", dataProviderClass = AllDataProviders.class)
    public void negativeDateEdge(String date) {
        String createComputerName = "pc_" + System.nanoTime();

        NewComputerPage computerPage = page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerFormNegative(createComputerName, date, date);

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(computerPage.isIntroducedValidationHighlighted(), "red border");
        sf.assertTrue(computerPage.isDiscontinuedValidationHighlighted(), "red border");
        sf.assertAll();
    }

}