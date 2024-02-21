package com.example.classloader.app;

import com.example.classloader.Calculable;
import com.example.classloader.implement.CalculateAdd;
import com.example.classloader.implement.CalculateMultiply;
import com.example.classloader.implement.CalculateSquareRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws IOException {

        //TO DO: Get the list of available operations programmatically.
        LOGGER.info("Enter the operation name for calculation (Add/Multiply/Square Root): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operation = reader.readLine();
        //TO DO: check operation if correct

        LOGGER.info("Enter the parameters to calculate the result of operation: ");

        Scanner in = new Scanner(System.in);

        ArrayList<Double> paramList = new ArrayList<Double>();
        while (in.hasNextDouble()) {
            double param = in.nextDouble();
            paramList.add(param);
        }

        LOGGER.info(paramList.toString());

        Calculable calculate;
        double result = 0;
        switch (operation) {
            case "Add":
                calculate = new CalculateAdd(paramList);
                result = calculate.getResult();
                break;
            case "Multiply":
                calculate = new CalculateMultiply(paramList);
                result = calculate.getResult();
                break;
            case "Square Root":
                calculate = new CalculateSquareRoot(paramList);
                result = calculate.getResult();
                break;
            default:
                LOGGER.info("The operation name entered is not correct!");
        }
        LOGGER.info(String.valueOf(result));
    }
}
