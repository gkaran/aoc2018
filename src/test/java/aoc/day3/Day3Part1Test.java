package aoc.day3;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day3Part1Test {
    private final Day3Part1 oracle = new Day3Part1();

    @Test
    public void solve() {
	assertEquals(4, oracle.solve(List.of(
	    "#1 @ 1,3: 4x4",
	    "#2 @ 3,1: 4x4",
	    "#3 @ 5,5: 2x2"
	)).longValue());
    }
}