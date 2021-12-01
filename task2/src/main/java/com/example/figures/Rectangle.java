package com.example.figures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rectangle implements Figure {

    private static Logger LOGGER = LoggerFactory.getLogger(Rectangle.class);
    private double width;
    private double height;
    private int x;
    private int y;
    private final String type = "Rectangle";

    /**
     * Constructor - creation of a new object
     * @see Rectangle#Rectangle(double, double, int, int)
     * @see Rectangle#Rectangle(double, double)
     * @see Rectangle#Rectangle(int, int)
     */
    public Rectangle() {
    }

    /**
     * Constructor - creation of a new object with particular parameters
     * @param width rectangle width
     * @param height rectangle height
     * @param x rectangle X coordinate
     * @param y rectangle Y coordinate
     * @see Rectangle#Rectangle()
     * @see Rectangle#Rectangle(double, double)
     * @see Rectangle#Rectangle(int, int)
     */
    public Rectangle(double width, double height, int x, int y) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public Rectangle(double width, double height) {
        this.height = height;
        this.width = width;
    }

    public Rectangle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public void show() {
        String position = "(" + this.x + ", " + this.y + ")";
        double area = this.getArea();
        //System.out.println("Object type: " + this.type + ". Object coordinates: " + position + ". Object area: " + position + ".");
        LOGGER.info("Object type: {} . Object coordinates: {}. Object area: {}.", this.type, position, area);
    }

    public double getArea() {
        return width * height;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void zoomPercentage(int percent) {
        this.height = height * percent / 100;
        this.width = width * percent / 100;
    }

}
