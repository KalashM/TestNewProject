package com.example.figures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SquareTest {
    private Square square;

    @Before
    public void setUp() throws Exception {
        square = new Square(5, 0,2);
    }

    @Test
    public void testArea() {
        assertEquals(25, square.area(), 0);
    }

    @Test
    public void testZoomPercentage() {
        square.zoomPercentage(300);
        assertEquals(15, square.getSide(), 0);
    }

    @Test
    public void testShow() {
        assertEquals("Square" , square.getType());
        assertEquals("X coordinate: ",0, square.getX());
        assertEquals( "Y coordinate: ",2, square.getY());
        assertEquals(5, square.getSide(),0);
    }
}