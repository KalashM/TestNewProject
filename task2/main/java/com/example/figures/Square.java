package com.example.figures;

public class Square extends Rectangle {

    private double side;
    private final String type = "Square";

    public Square(double side, int x, int y) {
        super(x, y);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public void zoomPercentage(int percent) {
        this.side = side*percent/100;
    }

    @Override
    public void show(){
        String position = "(" + super.getX() + ", " + super.getY() + ")";
        double area = this.area();
        System.out.println("Object type: " + this.type + ".\nObject coordinates: " + position + ".\nObject area: " + area + ".");
    }


}
