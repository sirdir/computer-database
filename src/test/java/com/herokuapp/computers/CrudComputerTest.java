package com.herokuapp.computers;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class CrudComputerTest extends BaseTest{

    final String BASE_URL = "http://computer-database.herokuapp.com/computers";

    @Test
    public void createComputer() {
        String expName = "pc" + System.nanoTime();
        String expMessage = String.format("Done! Computer %s has been created", expName);
        String startDate = "0000-01-01";
        String endDate = "2020-01-01";
        String companyName = "IBM";

        String actualMessage = open(BASE_URL, BasePage.class)
                .addNewPC()
                .fillAndSuccessfulSubmitComputerForm(expName, startDate, endDate, companyName)
                .getMessage();

        Assert.assertEquals(actualMessage, expMessage, "alert message");
    }

    @Test(dependsOnMethods = "createComputer")
    public void readComputer() {
        open(BASE_URL, BasePage.class);
        Assert.fail("Hello world!"); //for debug
    }

    @Test(dependsOnMethods = "readComputer")
    public void updateComputer() {

    }

    @Test(dependsOnMethods = "updateComputer")
    public void deleteComputer() {

    }

}
