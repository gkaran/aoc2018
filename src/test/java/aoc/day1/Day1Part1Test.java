package aoc.day1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day1Part1Test {
    private final Day1Part1 oracle = new Day1Part1();

    @Test
    public void assertTestCase1() {
	assertEquals(oracle.solve(List.of("+1", "+1", "+1")).intValue(), 3);
    }

    @Test
    public void assertTestCase2() {
	assertEquals(oracle.solve(List.of("+1", "+1", "-2")).intValue(), 0);
    }

    @Test
    public void assertTestCase3() {
	assertEquals(oracle.solve(List.of("-1", "-2", "-3")).intValue(), -6);
    }

}