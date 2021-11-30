package com.example.figures;

import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void showTest() {
        Triangle triangle = new Triangle(3,4,5,0,0);

        assertEquals("Triangle", triangle.getType());
        assertEquals("X coordinate: ",0, triangle.getX());
        assertEquals("Y coordinate: ",0, triangle.getY());
        assertEquals(3, triangle.getSide1(),0);
        assertEquals(4, triangle.getSide2(),0);
        assertEquals(5, triangle.getSide3(),0);
    }

    @Test
    public void getAreaTest() {
        Triangle triangle = new Triangle(3,4,5,0,0);

        assertEquals(6,triangle.getArea(), 0);
    }

    @Test
    public void moveTest() {
        Triangle triangle = new Triangle(3,4,5,0,0);

        triangle.move(5,8);
        assertEquals(5, triangle.getX());
        assertEquals(8, triangle.getY());
    }

    @Test
    public void zoomPercentageTest() {
        Triangle triangle = new Triangle(3,4,5,0,0);

        triangle.zoomPercentage(200);
        assertEquals(6, triangle.getSide1(),0);
        assertEquals(8, triangle.getSide2(),0);
        assertEquals(10, triangle.getSide3(),0);
    }

}