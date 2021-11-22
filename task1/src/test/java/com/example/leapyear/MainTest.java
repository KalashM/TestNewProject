package com.example.leapyear;

        import org.junit.Assert;
        import org.junit.Test;

        import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testIsLeapYearFullyDivisibleBy4() {
        boolean result = Main.isLeapYear(2020);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsLeapYearFullyDivisibleBy400() {
        boolean result = Main.isLeapYear(2000);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsLeapYearFullyDivisibleBy100Not400() {
        boolean result = Main.isLeapYear(1700);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsLeapYearBadValue() {
        boolean result = Main.isLeapYear(0);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsLeapYearNotDivisibleBy4() {
        boolean result = Main.isLeapYear(2005);
        Assert.assertEquals(false, result);
    }

}