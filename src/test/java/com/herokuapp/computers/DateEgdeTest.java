package com.herokuapp.computers;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.page;

public class DateEgdeTest extends BaseTest {

    private String companyName = "IBM";

    @DataProvider
    public Object[][] positiveDateEdges() {
        return new Object[][] {
                {"2019-01-01"},
                //days
                {"2019-01-31"},
                {"2019-02-28"},
                {"2020-02-29"}, //leap
                {"2019-03-30"},
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
                {"2019-03-31"},
                {"2019-04-32"},
                {"2019-00-01"},
                {"2019-13-01"},
                {"100000000-01-01"},
                {"-0001-01-01"},
        };
    }

    @Test
    public void minDate() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "0000-01-01";
        String endDate = "2020-01-01";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

    @Test
    public void maxDate() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "2018-01-01";
        String endDate = "100000000-12-31";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

    @Test(dataProvider = "positiveDateEdges")
    public void positiveDayInMonthEdge(String date) {
        String createComputerName = "pc_" + System.nanoTime();

        BasePage basepage = page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, date, date, companyName)
                .searchByName(createComputerName);

        String actualdIntrDate = basepage.getIntroducedDate();
        String actualdDiscDate = basepage.getDiscontinuedDate();

    }

    @Test(dataProvider = "negativeDateEdges")
    public void negativeDayInMonthEdge(String date) {
        String createComputerName = "pc_" + System.nanoTime();

        NewComputerPage computerPage = page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerFormNegative(createComputerName, date, date);
        Assert.assertTrue(computerPage.isIntroducedValidationHighlighted(), "red border");
        Assert.assertTrue(computerPage.isDiscontinuedValidationHighlighted(), "red border");
    }

    @Test
    public void serverErrorTooLong() {
        //todo too long request in post body
    }

}