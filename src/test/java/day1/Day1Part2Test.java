package day1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day1Part2Test {
    private final Day1Part2 oracle = new Day1Part2();

    @Test
    public void assertTestCase1() {
	assertEquals(oracle.solve(List.of(+1, -1)), 0);
    }

    @Test
    public void assertTestCase2() {
	assertEquals(oracle.solve(List.of(+3, +3, +4, -2, -4)), 10);
    }

    @Test
    public void assertTestCase3() {
	assertEquals(oracle.solve(List.of(-6, +3, +8, +5, -6)), 5);
    }

    @Test
    public void assertTestCase4() {
	assertEquals(oracle.solve(List.of(+7, +7, -2, -7, -4)), 14);
    }

}