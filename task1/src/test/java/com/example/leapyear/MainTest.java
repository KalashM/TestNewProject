package com.example.leapyear;

        import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.*;

        public class MainTest {

    @Test
    public void testIsLeapYearFullyDivisibleBy4() {
        boolean result = Main.isLeapYear(2020);
        assertEquals(true, result);
    }

    @Test
    public void testIsLeapYearFullyDivisibleBy400() {
        boolean result = Main.isLeapYear(2000);
        assertEquals(true, result);
    }

    @Test
    public void testIsLeapYearFullyDivisibleBy100Not400() {
        boolean result = Main.isLeapYear(1700);
        assertEquals(false, result);
    }

    @Test
    public void testIsLeapYearBadValue() {
        boolean result = Main.isLeapYear(0);
        assertEquals(false, result);
    }

    @Test
    public void testIsLeapYearNotDivisibleBy4() {
        boolean result = Main.isLeapYear(2005);
        assertEquals(false, result);
    }

}