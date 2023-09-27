package com.example.beansvalidationdemo;

import com.example.beansvalidation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class Demo {

    private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        Locale locale = Locale.ROOT;
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("en")) {
                locale = Locale.ENGLISH;
            } else if (args[0].equalsIgnoreCase("de")) {
                locale = Locale.GERMAN;
            } else if (args[0].equalsIgnoreCase("ukr")) {
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
