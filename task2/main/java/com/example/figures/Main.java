package com.example.figures;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5, 2, 0, 3);

        rectangle.show();
        rectangle.move(5, 0);
        rectangle.zoomPercentage(125);

        rectangle.show();

    }
}
