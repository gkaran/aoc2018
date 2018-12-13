package aoc.day2;

import aoc.Solution;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Day2Part2 implements Solution<String> {
    public String solve(List<String> codes) {
	for (String codeA : codes) {
	    for (String codeB : codes) {
		if (codeA.equals(codeB) || codeA.length() != codeB.length()) {
		    continue;
		}
		String commonPrefix = StringUtils.getCommonPrefix(codeA, codeB);
		String commonSuffix = StringUtils.getCommonPrefix(StringUtils.reverse(codeA),
		    StringUtils.reverse(codeB));
		if (commonPrefix.length() + commonSuffix.length() == codeA.length() - 1) {
		    return commonPrefix + StringUtils.reverse(commonSuffix);
		}
	    }
	}
	return "";
    }

}
