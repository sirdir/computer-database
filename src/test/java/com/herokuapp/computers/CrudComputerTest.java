package com.herokuapp.computers;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.open;

public class CrudComputerTest extends BaseTest{

    private String createComputerName = "pc_" + System.nanoTime();
    private String startDate = "2010-01-01";
    private String endDate = "2020-01-01";
    private String companyName = "IBM";
    private String changedComputerName = "new_pc_" + System.nanoTime();
    private String changedStartDate = "2018-05-30";
    private String changedEndDate = "2023-10-03";
    private String changedCompanyName = "ASUS";

    @Test
    public void createComputer() {
        String expMessage = String.format("Done! Computer %s has been created", createComputerName);

        String actualMessage = open(BASE_URL, BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, startDate, endDate, companyName)
                .getMessage();

        Assert.assertEquals(actualMessage, expMessage, "alert message");
    }

    @Test(dependsOnMethods = "createComputer")
    public void readComputer() {
        ExistedComputerPage compPage = open(BASE_URL, BasePage.class)
                .searchByName(createComputerName)
                .openFirstComputer();

        String actualComputerName = compPage.getCompanyName();
        String actualStartDate = compPage.getIntroducedDate();
        String actualEndDate = compPage.getDiscontinuedDate();
        String actualCompany = compPage.getCompany();

        SoftAssert sf = new SoftAssert();

        sf.assertEquals(actualComputerName, createComputerName);
        sf.assertEquals(actualStartDate, startDate);
        sf.assertEquals(actualEndDate, endDate);
        sf.assertEquals(actualCompany, companyName);

        sf.assertAll();
    }

    @Test(dependsOnMethods = "readComputer")
    public void updateComputer() {
        String expMessage = String.format("Done! Computer %s has been updated", changedComputerName);

        String actualMessage = open(BASE_URL, BasePage.class)
                .searchByName(createComputerName)
                .openFirstComputer()
                .fillAndSubmitComputerForm(changedComputerName, changedStartDate, changedEndDate, changedCompanyName)
                .getMessage();

        Assert.assertEquals(actualMessage, expMessage, "alert message");
    }

    @Test(dependsOnMethods = "updateComputer")
    public void deleteComputer() {
        String expMessage = "Done! Computer has been deleted";

        String actualMessage = open(BASE_URL, BasePage.class)
                .searchByName(changedComputerName)
                .openFirstComputer()
                .deleteComputer()
                .getMessage();

        Assert.assertEquals(actualMessage, expMessage, "alert message");
    }

}
