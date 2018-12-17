package aoc.day5;

import aoc.Solution;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 implements Solution<Integer, Integer> {
    @Override
    public Integer part1(List<String> data) {
	List<String> polymer = getPolymerCharacters(data.get(0));
	return getShortenedPolymerLength(polymer);
    }

    @Override
    public Integer part2(List<String> data) {
	List<String> polymer = getPolymerCharacters(data.get(0));

	return polymer.stream()
	    .map(String::toLowerCase)
	    .distinct()
	    .mapToInt(unit -> {
	        List<String> copy = new ArrayList<>(polymer);
		copy.removeIf(unit::equalsIgnoreCase);
		return getShortenedPolymerLength(copy);
	    })
	    .min()
	    .orElseThrow();
    }

    private List<String> getPolymerCharacters(String polymer) {
        return Arrays.asList(polymer.split(""));
    }

    private int getShortenedPolymerLength(List<String> data) {
        List<String> polymer = new ArrayList<>(data);
	int i = 1;
	boolean pairRemoved = false;

	do {
	    String unitA = polymer.get(i);
	    String unitB = polymer.get(i - 1);
	    if (StringUtils.isMixedCase(unitA + unitB) && unitA.equalsIgnoreCase(unitB)) {
		polymer.remove(i);
		polymer.remove(i - 1);
		pairRemoved = true;
	    } else {
		i++;
	    }

	    if (polymer.isEmpty() || i > polymer.size()) {
		break;
	    }

	    if (i == polymer.size()) {
		i = 1;
		if (!pairRemoved) {
		    break;
		}
		pairRemoved = false;
	    }
	} while (i < polymer.size());

	return polymer.size();
    }
}
