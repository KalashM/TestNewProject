package com.example.figures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {

    private Rectangle rectangle ;

    @Before
    public void setUp() throws Exception {
         rectangle = new Rectangle(3, 6, 1,1);
    }

    @Test
    public void testShow() {
        assertEquals("Rectangle" , rectangle.getType());
        assertEquals("X coordinate: ",1, rectangle.getX());
        assertEquals( "Y coordinate: ",1, rectangle.getY());
        assertEquals(6, rectangle.getHeight(),0);
        assertEquals(3, rectangle.getWidth(),0);}

    @Test
    public void testArea() {
        assertEquals(18, rectangle.area(), 0);
    }

    @Test
    public void testMove() {
        rectangle.move(8,0);
        assertEquals(8, rectangle.getX());
        assertEquals(0, rectangle.getY());
    }

    @Test
    public void testZoomPercentage() {
        rectangle.zoomPercentage(200);
        assertEquals(6, rectangle.getWidth(), 0);
        assertEquals(12, rectangle.getHeight(), 0);
    }
}