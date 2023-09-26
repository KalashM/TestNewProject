package com.example.beansvalidationdemo;

import com.example.beansvalidation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public class Demo {

    private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {

        Locale[] supportedLocales = {
                Locale.ENGLISH,
                Locale.GERMAN,
                new Locale("ua", "UA")
        };

        Employee emp = new Employee();
        emp.setEmail("marina.kalashnyk#gmail.com");

        ResourceBundle labels = ResourceBundle.getBundle("EmployeeBundle", supportedLocales[2]);

        for (String message : Validator.validate(emp)) {
            LOGGER.info(labels.getString(message));
        }
    }
}
