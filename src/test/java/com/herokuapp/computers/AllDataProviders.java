package com.herokuapp.computers;

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
                //month
                {"2019-00-01"},
                {"2019-13-01"},
                //year
                {"1000000000-01-01"},
                {"-0001-01-01"},
                {"hello world"}
        };
    }

}
