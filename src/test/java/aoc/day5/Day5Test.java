package aoc.day5;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class Day5Test {

    private final Day5 oracle = new Day5();

    @Test
    public void part1() {
        assertEquals(10, oracle.part1(Collections.singletonList("dabAcCaCBAcCcaDA")).intValue());
    }

    @Test
    public void part2() {
        assertEquals(4, oracle.part2(Collections.singletonList("dabAcCaCBAcCcaDA")).intValue());
    }
}