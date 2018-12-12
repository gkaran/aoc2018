package day2;

import helpers.InputLoader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Day2Part1 {

    long solve(List<String> codes) {

	Map<Integer, Long> results = codes.stream()
	    .flatMapToInt(code -> code.chars()
		.distinct()
		.map(c -> StringUtils.countMatches(code, (char)c))
		.filter(count -> count == 2 || count == 3)
		.distinct()
	    )
	    .boxed()
	    .collect(groupingBy(Function.identity(), Collectors.counting()));

	return results.getOrDefault(2, 0L) * results.getOrDefault(3, 0L);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
	List<String> codes = Files.lines(Paths.get(new InputLoader().getResource(2).toURI()))
	    .collect(Collectors.toList());

	System.out.println(new Day2Part1().solve(codes));
    }

}
