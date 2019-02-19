package com.herokuapp.computers;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.page;

public class DateEgdeTest extends BaseTest {

    private String companyName = "IBM";


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

    @Test
    public void February28() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "2018-10-10";
        String endDate = "2020-02-28";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

    @Test
    public void February29() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "2018-10-10";
        String endDate = "2020-02-29";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

    @Test
    public void February29Leap() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "2018-10-10";
        String endDate = "2020-02-29";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

    @Test
    public void February30Leap() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "2018-10-10";
        String endDate = "2020-02-30";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

    @Test
    public void March30() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "2018-10-10";
        String endDate = "2020-03-30";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

    @Test
    public void March31() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "2018-10-10";
        String endDate = "2020-03-31";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

    @Test
    public void April31() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "2018-10-10";
        String endDate = "2020-04-31";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

    @Test
    public void April32() {
        String createComputerName = "pc_" + System.nanoTime();
        String startDate = "2018-10-10";
        String endDate = "2020-04-32";

        page(BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();
    }

}