package com.example.figures;

public class Main {
    public static void main(String[] args) {

        System.out.println("----- New Triangle creation: -------");
        Triangle triangle = new Triangle(3,4,5,0,0);
        if (triangle.ifExists()) {
            triangle.show();
            triangle.move(5,5);
            triangle.zoomPercentage(50);
            triangle.show();
        } else
            System.out.println("The triangle does not exist");

        System.out.println("----- New Square and Rectangle creation: -------");
        Rectangle rec[] = new Rectangle[10];
        for (int i = 0; i <= 4; i++) {
            rec[i] = new Rectangle(i+1, i+2, i+3, i+4);
        }
        for (int i = 5; i <= 9; i++) {
            rec[i] = new Square(i, i+2, i+3);
        }
        for (int i = 0; i <= 9; i++) {
            rec[i].show();
        }
    }
}
