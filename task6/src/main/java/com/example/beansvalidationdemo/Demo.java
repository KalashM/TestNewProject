package com.example.beansvalidationdemo;

import com.example.beansvalidation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

    private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        Employee emp = new Employee();
        //emp.setId(10);
        emp.setEmail("marina.kalashnyk#gmail.com");
        for (String message : Validator.validate(emp)) {
            LOGGER.info(message);
        }
    }
}
