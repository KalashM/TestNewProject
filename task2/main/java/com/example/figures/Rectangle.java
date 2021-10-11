package com.example.figures;

public class Rectangle {
    private double width;
    private double height;
    private int x, y;
    private final String type = "Rectangle";

    public Rectangle() {
    }

    public Rectangle(double width, double height, int x, int y) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }
    public Rectangle(double width, double height){
        this.height = height;
        this.width = width;
    }
    public Rectangle(int x, int y){
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

    public void show() {
        String position = "(" + this.x + ", " + this.y + ")";
        double area = this.area();
        System.out.println("Object type: " + this.type + ". Object coordinates: " + position + ". Object area: " + area + ".");
    }
    public double area() {
        return width * height;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void zoomPercentage(int percent) {
        this.height = height*percent/100;
        this.width = width*percent/100;
    }

}
