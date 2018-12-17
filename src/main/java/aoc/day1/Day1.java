package aoc.day1;

import aoc.Solution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 implements Solution<Integer, Integer> {
    @Override
    public Integer part1(List<String> changes) {
	return changes.stream()
	    .map(Integer::parseInt)
	    .mapToInt(Integer::intValue)
	    .sum();
    }

    @Override
    public Integer part2(List<String> changes) {
	Set<Integer> frequencies = new HashSet<>();
	int sum = 0;
	int index = 0;

	do {
	    frequencies.add(sum);
	    sum += Integer.parseInt(changes.get(index));
	    if (++index == changes.size()) {
		index = 0;
	    }
	} while(!frequencies.contains(sum));

	return sum;
    }
}
