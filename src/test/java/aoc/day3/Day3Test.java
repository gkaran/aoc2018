package aoc.day3;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day3Test {
    private final Day3 oracle = new Day3();

    @Test
    public void assertPart1TestCase() {
	assertEquals(4, oracle.part1(List.of(
	    "#1 @ 1,3: 4x4",
	    "#2 @ 3,1: 4x4",
	    "#3 @ 5,5: 2x2"
	)).longValue());
    }

    @Test
    public void assertPart2TestCase() {
	assertEquals(3, oracle.part2(List.of(
	    "#1 @ 1,3: 4x4",
	    "#2 @ 3,1: 4x4",
	    "#3 @ 5,5: 2x2"
	)).longValue());
    }
}