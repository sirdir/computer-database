package com.herokuapp.computers.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static final String BASE_URL;
    public static final String DEFAULT_COMPANY;
    public static final String CREATE_MESSAGE;
    public static final String UPDATE_MESSAGE;
    public static final String DELETE_MESSAGE;
    public static final String DEFAULT_INTRODUCED;
    public static final String DEFAULT_DISCONTINUED;
    public static final String LONG_NAME;

    static {
        Properties prop = new Properties();
        String sep = File.separator;

        try {
            String path = "build" + sep + "resources" + sep + "test" + sep + "config.properties";
            FileInputStream input = new FileInputStream(path);
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BASE_URL = prop.getProperty("base.url");
        DEFAULT_COMPANY = prop.getProperty("default.company");
        CREATE_MESSAGE = prop.getProperty("create.message");
        UPDATE_MESSAGE = prop.getProperty("update.message");
        DELETE_MESSAGE = prop.getProperty("delete.message");
        DEFAULT_INTRODUCED = prop.getProperty("default.introduced");
        DEFAULT_DISCONTINUED = prop.getProperty("default.discontinued");
        LONG_NAME = prop.getProperty("loooooong.name");
    }
}
