package day1;

import helpers.InputLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day1Part2 {

    void solve(List<Integer> changes) {
	Set<Integer> frequencies = new HashSet<>();
	int sum = 0;
	int index = 0;

	do {
	    frequencies.add(sum);
	    sum += changes.get(index);
	    if (++index == changes.size()) {
	        index = 0;
	    }
	} while(!frequencies.contains(sum));

	System.out.println(sum);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
	List<Integer> changes = Files.lines(Paths.get(new InputLoader().getResource(1).toURI()))
	    .map(Integer::parseInt)
	    .collect(Collectors.toList());

	new Day1Part2().solve(changes);
    }
}
