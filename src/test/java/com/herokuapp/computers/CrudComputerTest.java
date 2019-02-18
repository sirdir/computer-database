package com.herokuapp.computers;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class CrudComputerTest extends BaseTest{

    final String BASE_URL = "http://computer-database.herokuapp.com/computers";

    @Test
    public void createComputer() {
        open(BASE_URL, Page.class)
                .addNewPC()
                ;
    }

    @Test(dependsOnMethods = "createComputer")
    public void readComputer() {
        open(BASE_URL, Page.class);
        Assert.fail("Hello world!");
    }

    @Test(dependsOnMethods = "readComputer")
    public void updateComputer() {

    }

    @Test(dependsOnMethods = "updateComputer")
    public void deleteComputer() {

    }

}
