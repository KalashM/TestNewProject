package com.example.classloader.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

//@SuppressWarnings("unchecked")
public class Demo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String pathName = getModulsLocation("location.properties");
        List<File> jars = Arrays.asList(new File(pathName).listFiles());
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
        Thread.currentThread().setContextClassLoader(childClassLoader);

        LOGGER.info("Enter the operation name for calculation (Add/Multiply/Square Root): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operation = reader.readLine();

        LOGGER.info("Enter the parameters to calculate the result of operation: ");
        Scanner in = new Scanner(System.in);

        ArrayList<Double> paramList = new ArrayList<>();
        while (in.hasNextDouble()) {
            double param = in.nextDouble();
            paramList.add(param);
        }
        LOGGER.info(paramList.toString());

        double result = performCalculation(childClassLoader, operation, paramList);
        LOGGER.info(String.valueOf(result));
    }

    private static double performCalculation(URLClassLoader childClassLoader, String operationName, ArrayList<Double> paramList) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        switch (operationName.toLowerCase()) {
            case "add":
                return getCalculationResult(childClassLoader, "com.example.classloader.implement.CalculateAdd", paramList);
            case "multiply":
                return getCalculationResult(childClassLoader, "com.example.classloader.implement.CalculateMultiply", paramList);
            case "square root":
                return getCalculationResult(childClassLoader, "com.example.classloader.implement.CalculateSquareRoot", paramList);
            default:
                LOGGER.info("The operation name entered is not correct!");
                return 0;
        }
    }

    private static double getCalculationResult(URLClassLoader childClassLoader, String binaryClassName, ArrayList<Double> paramList) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class[] parameterType = new Class[]{ArrayList.class};
        Class myClass = childClassLoader.loadClass(binaryClassName);
        Constructor constructor = myClass.getConstructor(parameterType);
        Object calculate = constructor.newInstance(paramList);
        Method getResultmethod = myClass.getMethod("getResult");
        return (double) getResultmethod.invoke(calculate);
    }

    private static String getModulsLocation(String propertiesFile) throws IOException {
        try (InputStream input = Demo.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            Properties props = new Properties();
            props.load(input);
            return props.getProperty("modulsLocation");
        }
    }
}
