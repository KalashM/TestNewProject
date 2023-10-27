package com.example.beansvalidationdemo;

import com.example.beansvalidation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Demo {

    private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);
    private static String localeConfigPath = Thread.currentThread().getContextClassLoader().getResource("locale-config.properties").getPath();

    public static void main(String[] args) {

        Properties localeProps = new Properties();
        try {
            localeProps.load(new FileInputStream(localeConfigPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String loc = localeProps.getProperty("locale");

        Locale locale = Locale.ROOT;
        if (!loc.isEmpty()) {
            if (loc.equalsIgnoreCase("de")) {
                locale = Locale.GERMAN;
            } else if (loc.equalsIgnoreCase("ukr")) {
                locale = new Locale.Builder().setLanguage("ukr").setScript("Cyrl").build();
            }
        }

        Employee emp = new Employee();
        emp.setEmail("marina.kalashnyk#gmail.com");

        ResourceBundle labels = ResourceBundle.getBundle("EmployeeBundle", locale);

        for (String message : Validator.validate(emp)) {
           LOGGER.info(new String(labels.getString(message).getBytes(), StandardCharsets.UTF_8));
        }
    }
}