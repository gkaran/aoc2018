package day3;

import helpers.InputLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3Part1 {
    long solve(List<String> claimStrs) {

	List<Claim> claims = claimStrs.stream()
	    .map(Claim::fromString)
	    .collect(Collectors.toList());

	int maxWidth = claims.stream().mapToInt(c -> c.getOffsetX() + c.getWidth()).max().orElse(0);
	int maxHeight = claims.stream().mapToInt(c -> c.getOffsetY() + c.getHeight()).max().orElse(0);

	int[][] cells = IntStream
	    .range(0, maxWidth)
	    .mapToObj(i -> IntStream.generate(() -> 0).limit(maxHeight).toArray())
	    .toArray(i -> new int[maxWidth][]);

	for (var claim : claims) {
	    for (var i = claim.getOffsetX(); i < claim.getOffsetX() + claim.getWidth(); i++) {
		for (var j = claim.getOffsetY(); j < claim.getOffsetY() + claim.getHeight(); j++) {
		    cells[i][j]++;
		}
	    }
	}

	Arrays.stream(cells)
	    .map(row -> Arrays.stream(row).mapToObj(Integer::toString).collect(Collectors.joining(" ")))
	    .forEachOrdered(System.out::println);

	return Arrays.stream(cells)
	    .flatMapToInt(Arrays::stream)
	    .filter(i -> i > 1)
	    .count();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
	List<String> codes = Files.lines(Paths.get(new InputLoader().getResource(3).toURI()))
	    .collect(Collectors.toList());

	System.out.println(new Day3Part1().solve(codes));
    }

}
