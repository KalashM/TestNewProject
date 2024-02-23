package com.example.classloader.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
@SuppressWarnings("unchecked")
public class Demo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        List<File> jars = Arrays.asList(new File("task8-main/build/libs/moduls").listFiles());
        URL[] urls = new URL[jars.size()];
        for (int i = 0; i < jars.size(); i++) {
            try {
                urls[i] = jars.get(i).toURI().toURL();
                System.out.println(urls[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        URLClassLoader childClassLoader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
        Class.forName("com.example.classloader.Calculable", true, childClassLoader);
        Class.forName("com.example.classloader.implement.CalculateAdd", true, childClassLoader);
        Class.forName("com.example.classloader.implement.CalculateMultiply", true, childClassLoader);
        Class.forName("com.example.classloader.implement.CalculateSquareRoot", true, childClassLoader);
        Thread.currentThread().setContextClassLoader(childClassLoader);

        //TO DO: Get the list of available operations programmatically.
        LOGGER.info("Enter the operation name for calculation (Add/Multiply/Square Root): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operation = reader.readLine();
        //TO DO: check operation if correct

        LOGGER.info("Enter the parameters to calculate the result of operation: ");

        Scanner in = new Scanner(System.in);

        ArrayList<Double> paramList = new ArrayList<>();
        while (in.hasNextDouble()) {
            double param = in.nextDouble();
            paramList.add(param);
        }

        LOGGER.info(paramList.toString());

        double result = 0;
        switch (operation) {
            case "Add":
                result = performCalculation(childClassLoader, "Add", paramList);
                break;
            /*case "Multiply":
                calculate = new CalculateMultiply(paramList);
                result = calculate.getResult();
                break;
            case "Square Root":
                calculate = new CalculateSquareRoot(paramList);
                result = calculate.getResult();
                break;*/
            default:
                LOGGER.info("The operation name entered is not correct!");
        }
        LOGGER.info(String.valueOf(result));
    }

    private static double performCalculation(URLClassLoader childClassLoader, String operationName, ArrayList<Double> paramList) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class[] parameterType = new Class[]{ArrayList.class};
        Class myClass = childClassLoader.loadClass("com.example.classloader.implement.CalculateAdd");
        Constructor constructor = myClass.getConstructor(parameterType);
        Object calculate = constructor.newInstance(paramList);
        Method getResultmethod = myClass.getMethod("getResult");
        return (double) getResultmethod.invoke(calculate);
    }
}
