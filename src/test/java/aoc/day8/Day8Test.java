package aoc.day8;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Day8Test {

    private final Day8 oracle = new Day8();

    @Test
    public void part1() {
        List<String> input = List.of("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");
        assertEquals(138, oracle.part1(input).intValue());
    }

    @Test
    public void part2() {
    }
}