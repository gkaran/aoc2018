package aoc.day2;

import aoc.Solution;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Day2 implements Solution<Long, String> {

    @Override
    public Long part1(List<String> codes) {

	Map<Integer, Long> results = codes.stream()
	    .flatMapToInt(code -> code.chars()
		.distinct()
		.map(c -> StringUtils.countMatches(code, (char) c))
		.filter(count -> count == 2 || count == 3)
		.distinct()
	    )
	    .boxed()
	    .collect(groupingBy(Function.identity(), Collectors.counting()));

	return results.getOrDefault(2, 0L) * results.getOrDefault(3, 0L);
    }

    @Override
    public String part2(List<String> codes) {
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
