package com.example.figures;

import com.sun.xml.internal.fastinfoset.tools.TransformInputOutput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {
    private Triangle triangle1;
    private Triangle triangleNotExist;

    @Before
    public void setUp() throws Exception {
        triangle1 = new Triangle(3,4,5,0,0);
        triangleNotExist = new Triangle(1,1,18,1,1);
    }

    @Test
    public void ifExistsTestTrue() {
        boolean resut = triangle1.ifExists();
        assertEquals(true, resut);
    }

    @Test
    public void ifExistsTestFalse() {
        boolean resut = triangleNotExist.ifExists();
        assertEquals(false, resut);
    }

    @Test
    public void showTest() {
        //String triangleType = triangle1.getType();
        assertEquals("Triangle", triangle1.getType());
        assertEquals("X coordinate: ",0, triangle1.getX());
        assertEquals("Y coordinate: ",0, triangle1.getY());
        assertEquals(3, triangle1.getSide1(),0);
        assertEquals(4, triangle1.getSide2(),0);
        assertEquals(5, triangle1.getSide3(),0);
    }

    @Test
    public void getAreaTest() {
        assertEquals(6,triangle1.getArea(), 0);
    }

    @Test
    public void moveTest() {
        triangle1.move(5,8);
        assertEquals(5, triangle1.getX());
        assertEquals(8, triangle1.getY());
    }

    @Test
    public void zoomPercentageTest() {
        triangle1.zoomPercentage(200);
        assertEquals(6, triangle1.getSide1(),0);
        assertEquals(8, triangle1.getSide2(),0);
        assertEquals(10, triangle1.getSide3(),0);
    }

}