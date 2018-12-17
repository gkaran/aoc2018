package aoc.day6;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Day6Test {

    private final Day6 oracle = new Day6();

    @Test
    public void part1() {
        assertEquals(17, oracle.part1(List.of(
	    "1, 1",
	    "1, 6",
	    "8, 3",
	    "3, 4",
	    "5, 5",
	    "8, 9"
	)).intValue());
    }

    @Test
    public void part2() {
    }
}