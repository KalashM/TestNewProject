package com.example.figures;

public class Rectangle {
    private double width;
    private double height;
    private int x, y;
    private final String type = "Rectangle";

    public Rectangle(double width, double height, int x, int y) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public void show() {
        String position = "(" + this.x + ", " + this.y + ")";
        double area = this.area();
        System.out.println("Object type: " + type + ".\nObject coordinates: " + position + ".\nObject area: " + area + ".");
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
