package aoc.day2;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day2Part2Test {

    private final Day2Part2 oracle = new Day2Part2();

    @Test
    public void solve() {
	assertEquals("fgij", oracle.solve(List.of(
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