package aoc.day2;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Day2Part1Test {

    private static final Day2Part1 oracle = new Day2Part1();

    @Test
    public void assertTestCase() {
        assertEquals(12, oracle.solve(List.of(
            "abcdef",
	    "bababc",
	    "abbcde",
	    "abcccd",
	    "aabcdd",
	    "abcdee",
	    "ababab"
	)).longValue());
    }
}