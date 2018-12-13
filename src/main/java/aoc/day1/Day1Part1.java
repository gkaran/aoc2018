package aoc.day1;

import aoc.Solution;

import java.util.List;

public class Day1Part1 implements Solution<Integer> {
    @Override
    public Integer solve(List<String> changes) {
	return changes.stream()
	    .map(Integer::parseInt)
	    .mapToInt(Integer::intValue)
	    .sum();
    }
}
