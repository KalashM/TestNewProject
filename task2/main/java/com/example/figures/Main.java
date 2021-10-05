package com.example.figures;

public class Main {
    public static void main(String[] args) {

        System.out.println("----- New Rectangle creation: -------");
        Rectangle rectangle = new Rectangle(5, 2, 0, 3);
        rectangle.show();
        rectangle.move(5, 0);
        rectangle.zoomPercentage(125);
        rectangle.show();

        System.out.println("----- New Triangle creation: -------");
        Triangle triangle = new Triangle(3,4,5,0,0);
        if (triangle.ifExists()) {
            triangle.show();
            triangle.move(5,5);
            triangle.zoomPercentage(50);
            triangle.show();
        } else
            System.out.println("The triangle does not exist");

        System.out.println("----- New Square creation: -------");
        Rectangle square = new Square(3, 2,2);
        square.show();
        square.move(10, 10);
        square.zoomPercentage(200);
        square.show();

        Square square1 = new Square(4,1,1);
        square1.show();
    }
}
