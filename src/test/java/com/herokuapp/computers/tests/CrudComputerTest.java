package com.herokuapp.computers.tests;

import com.codeborne.selenide.Selenide;
import com.herokuapp.computers.pages.BasePage;
import com.herokuapp.computers.pages.ExistedComputerPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.open;
import static com.herokuapp.computers.utils.Config.*;

public class CrudComputerTest extends BaseTest{

    private String createComputerName = "pc_" + System.nanoTime();
    private String changedComputerName = "new_pc_" + System.nanoTime();


    @Test
    public void createComputer() {
        String actualMessage = Selenide.open(BASE_URL, BasePage.class)
                .addNewPC()
                .fillAndSubmitComputerForm(createComputerName, DEFAULT_INTRODUCED, DEFAULT_DISCONTINUED, DEFAULT_COMPANY)
                .getMessage();

        String expMessage = String.format(CREATE_MESSAGE, createComputerName);

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
        sf.assertEquals(actualStartDate, DEFAULT_INTRODUCED);
        sf.assertEquals(actualEndDate, DEFAULT_DISCONTINUED);
        sf.assertEquals(actualCompany, DEFAULT_COMPANY);

        sf.assertAll();
    }

    @Test(dependsOnMethods = "readComputer")
    public void updateComputer() {
        String changedStartDate = "2018-05-30";
        String changedEndDate = "2023-10-03";
        String changedCompanyName = "ASUS";

        String actualMessage = open(BASE_URL, BasePage.class)
                .searchByName(createComputerName)
                .openFirstComputer()
                .fillAndSubmitComputerForm(changedComputerName, changedStartDate, changedEndDate, changedCompanyName)
                .getMessage();

        String expMessage = String.format(UPDATE_MESSAGE, changedComputerName);

        Assert.assertEquals(actualMessage, expMessage, "alert message");
    }

    @Test(dependsOnMethods = "updateComputer")
    public void deleteComputer() {
        String actualMessage = open(BASE_URL, BasePage.class)
                .searchByName(changedComputerName)
                .openFirstComputer()
                .deleteComputer()
                .getMessage();

        Assert.assertEquals(actualMessage, DELETE_MESSAGE, "alert message");
    }

}
