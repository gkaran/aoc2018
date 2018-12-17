package aoc.day2;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Day2Test {

    private static final Day2 oracle = new Day2();

    @Test
    public void assertPart1TestCase() {
        assertEquals(12, oracle.part1(List.of(
            "abcdef",
	    "bababc",
	    "abbcde",
	    "abcccd",
	    "aabcdd",
	    "abcdee",
	    "ababab"
	)).longValue());
    }

    @Test
    public void assertPart2TestCase() {
	assertEquals("fgij", oracle.part2(List.of(
	    "abcde",
	    "fghij",
	    "klmno",
	    "pqrst",
	    "fguij",
	    "axcye",
	    "wvxyz"
	)));
    }
}