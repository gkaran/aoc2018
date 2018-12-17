package aoc.day5;

import aoc.Solution;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 implements Solution<Integer, String> {
    @Override
    public Integer part1(List<String> data) {
	List<String> polymer = new ArrayList(Arrays.asList(data.get(0).split("")));

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

    @Override
    public String part2(List<String> data) {
	return "";
    }
}
