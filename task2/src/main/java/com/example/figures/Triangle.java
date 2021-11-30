package com.example.figures;

public class Triangle implements Figure {

    private double side1;
    private double side2;
    private double side3;
    private int x;
    private int y;
    private final String type = "Triangle";

    public Triangle(double side1, double side2, double side3, int x, int y) {
        if ((side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1)) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
            this.x = x;
            this.y = y;
        } else {
            throw new IllegalArgumentException("Triangle cannot be created if sum of two any sides is less then the third side");
        }
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
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
        System.out.println("Object type: " + type + ". Object coordinates: " + position + ". Object area: " + area + ".");
    }

    public double getArea() {
        double p = (side1 + side2 + side3) / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void zoomPercentage(int percent) {
        this.side1 = side1 * percent / 100;
        this.side2 = side2 * percent / 100;
        this.side3 = side3 * percent / 100;
    }

}
