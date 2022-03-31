package com.example.figures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareTest {
    @Test
    public void testArea() {
        Square square = new Square(5, 0,2);

        assertEquals(25, square.getArea(), 0);
    }

    @Test
    public void testZoomPercentage() {
        Square square = new Square(5, 0,2);

        square.zoomPercentage(300);
        assertEquals(15, square.getWidth(), 0);
    }

    @Test
    public void testShow() {
        Square square = new Square(5, 0,2);

        assertEquals("Square", square.getType());
        assertEquals(0, square.getX());
        assertEquals(2, square.getY());
        assertEquals(5, square.getWidth(),0);
    }
}