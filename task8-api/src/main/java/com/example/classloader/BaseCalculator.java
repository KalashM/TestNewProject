package com.example.classloader;

import java.util.ArrayList;

public class BaseCalculator implements Calculable {
    private ArrayList parametersList;
    private String description;

    @Override
    public int getParametersCount() {
        return parametersList.size();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public double getResult() {
        return 0;
    }

    public void setParametersList(ArrayList parametersList) {
        this.parametersList = parametersList;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
