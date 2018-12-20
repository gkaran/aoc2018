package aoc.day7;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Day7Test {

    private final Day7 oracle = new Day7();

    @Test
    public void part1() {
	List<String> input = List.of(
	    "Step C must be finished before step A can begin.",
	    "Step C must be finished before step F can begin.",
	    "Step A must be finished before step B can begin.",
	    "Step A must be finished before step D can begin.",
	    "Step B must be finished before step E can begin.",
	    "Step D must be finished before step E can begin.",
	    "Step F must be finished before step E can begin."
	);

	Assert.assertEquals("CABDFE", oracle.part1(input));
    }

    @Test
    public void part2() {
    }
}