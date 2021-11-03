package com.example.figures;
/**Class <strong>Rectangle</strong> allows to create a rectangle,
 * get its main characteristics such as <b>width</b>, <b>height</b>, etc..
 * @author - Marina Panchenko
 * @version - 1.0
 */
public class Rectangle {
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

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /** Method displays object's information - type, area and coordinates
     */

    public void show() {
        String position = "(" + this.x + ", " + this.y + ")";
        double area = this.area();
        System.out.println("Object type: " + this.type + ". Object coordinates: " + position + ". Object area: " + area + ".");
    }

    /** Method returns object area
     * @return object area
     */

    public double area() {
        return width * height;
    }

    /** Method moves object to the new coordinates
     * @param x new X coordinate
     * @param y new Y coordinate
     */

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Method zoom/scale object
     * @param percent value in %
     */

    public void zoomPercentage(int percent) {
        this.height = height * percent / 100;
        this.width = width * percent / 100;
    }

}
