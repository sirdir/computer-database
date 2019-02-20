package com.herokuapp.computers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.page;

public class DateTest extends BaseTest {

    private String companyName = "IBM";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u-MM-dd");

    @DataProvider
    public Object[][] positiveDateEdges() {
        return new Object[][] {
                {"2019-01-01"},
                //days
                {"2019-01-31"},
                {"2019-02-28"},
                {"2020-02-29"}, //leap
                {"2019-04-30"},
                //month
                {"2019-12-01"},
                //year
                {"0000-01-01"},
                {"99999999-01-01"},

        };
    }

    @DataProvider
    public Object[][] negativeDateEdges() {
        return new Object[][] {
                //days
                {"2019-01-00"},
                {"2019-01-32"},
                {"2019-02-29"},
                {"2020-02-30"}, //leap
                {"2019-04-31"},
                {"2019-00-01"},
                {"2019-13-01"},
                {"1000000000-01-01"},
                {"-0001-01-01"},
        };
    }

    @Test(dataProvider = "positiveDateEdges")
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

    @Test(dataProvider = "negativeDateEdges")
    public void negativeDateEdge(String date) {
        String createComputerName = "pc_" + System.nanoTime();

        NewComputerPage computerPage = page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerFormFatal(createComputerName, date, date);

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(computerPage.isIntroducedValidationHighlighted(), "red border");
        sf.assertTrue(computerPage.isDiscontinuedValidationHighlighted(), "red border");
        sf.assertAll();
    }

}