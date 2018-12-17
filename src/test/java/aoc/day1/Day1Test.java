package aoc.day1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day1Test {
    private final Day1 oracle = new Day1();

    @Test
    public void assertPart1TestCase1() {
	assertEquals(oracle.part1(List.of("+1", "+1", "+1")).intValue(), 3);
    }

    @Test
    public void assertPart1TestCase2() {
	assertEquals(oracle.part1(List.of("+1", "+1", "-2")).intValue(), 0);
    }

    @Test
    public void assertPart1TestCase3() {
	assertEquals(oracle.part1(List.of("-1", "-2", "-3")).intValue(), -6);
    }

    @Test
    public void assertPart2TestCase1() {
	assertEquals(oracle.part2(List.of("+1", "-1")).intValue(), 0);
    }

    @Test
    public void assertPart2TestCase2() {
	assertEquals(oracle.part2(List.of("+3", "+3", "+4", "-2", "-4")).intValue(), 10);
    }

    @Test
    public void assertPart2TestCase3() {
	assertEquals(oracle.part2(List.of("-6", "+3", "+8", "+5", "-6")).intValue(), 5);
    }

    @Test
    public void assertPart2TestCase4() {
	assertEquals(oracle.part2(List.of("+7", "+7", "-2", "-7", "-4")).intValue(), 14);
    }

}