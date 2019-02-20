package com.herokuapp.computers.tests;

import org.testng.annotations.DataProvider;

public class AllDataProviders {

    @DataProvider
    public Object[][] singleChars() {

        return new Object[][] {
                {"1"},
                {"`"}
        };
    }

    @DataProvider
    public Object[][] positiveDateEdges() {
        return new Object[][] {
                //same date
                {"2018-01-01", "2018-01-01"},
                //days
                {"2018-01-31", "2019-01-31"},
                {"2018-02-28", "2019-02-28"},
                {"2020-02-29", "2024-02-29"}, //leap
                {"2018-04-30", "2019-04-30"},
                //month
                {"2018-12-01", "2019-12-01"},
                //era boundary
                {"0-01-01", "0-01-01"},
                {"99999999-12-31", "99999999-12-31"},

        };
    }


    @DataProvider
    public Object[][] negativeDateEdges() {
        return new Object[][] {
                // discontinued before introduction
                {"2020-01-01", "2018-01-01"},
                //days
                {"2018-01-00", "2019-01-00"},
                {"2018-01-32", "2019-01-32"},
                {"2018-02-29", "2019-02-29"},
                {"2016-02-30", "2020-02-30"}, //leap
                {"2018-04-31", "2019-04-31"},
                //month
                {"2018-00-01", "2019-00-01"},
                {"2018-13-01", "2019-13-01"},
                //era boundary
                {"1000000000-01-01", "1000000000-01-01"},
                {"-0001-01-01", "-0001-01-01"},
                {"hello world", "hello world"}
        };
    }

}
