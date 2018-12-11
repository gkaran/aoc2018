package day1;

import helpers.InputLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day1Part1 {

    int solve(List<Integer> changes) {
	return changes.stream()
	    .mapToInt(Integer::intValue)
	    .sum();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
	List<Integer> changes = Files.lines(Paths.get(new InputLoader().getResource(1).toURI()))
	    .map(Integer::parseInt)
	    .collect(Collectors.toList());

	System.out.println(new Day1Part1().solve(changes));
    }
}
