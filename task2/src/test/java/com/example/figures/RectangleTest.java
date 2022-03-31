package com.example.figures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleTest {
    @Test
    public void testShow() {
        Rectangle rectangle = new Rectangle(3, 6, 1,1);
        assertEquals("Rectangle", rectangle.getType());
        assertEquals(1, rectangle.getX());
        assertEquals(1, rectangle.getY());
        assertEquals(6, rectangle.getHeight(),0);
        assertEquals(3, rectangle.getWidth(),0);
    }

    @Test
    public void testArea() {
        Rectangle rectangle = new Rectangle(3, 6, 1,1);
        assertEquals(18, rectangle.getArea(), 0);
    }

    @Test
    public void testMove() {
        Rectangle rectangle = new Rectangle(3, 6, 1,1);
        rectangle.move(8,0);
        assertEquals(8, rectangle.getX());
        assertEquals(0, rectangle.getY());
    }

    @Test
    public void testZoomPercentage() {
        Rectangle rectangle = new Rectangle(3, 6, 1,1);
        rectangle.zoomPercentage(200);
        assertEquals(6, rectangle.getWidth(), 0);
        assertEquals(12, rectangle.getHeight(), 0);
    }
}