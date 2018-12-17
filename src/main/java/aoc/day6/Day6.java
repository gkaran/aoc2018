package aoc.day6;

import aoc.Solution;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day6 implements Solution<Integer, Integer> {
    @Override
    public Integer part1(List<String> data) {
	List<NamedPoint> points = data.stream()
	    .map(point -> StringUtils.split(point.trim(), ", "))
	    .map(coords -> new NamedPoint(UUID.randomUUID().toString(), Integer.parseInt(coords[0]),
		Integer.parseInt(coords[1])))
	    .collect(Collectors.toList());

	int maxX = points.stream().mapToInt(NamedPoint::getX).max().orElseThrow();
	int maxY = points.stream().mapToInt(NamedPoint::getY).max().orElseThrow();

	String[][] coors = new String[maxX + 1][maxY + 1];
	for (var i = 0; i <= maxX; i++) {
	    for (var j = 0; j <= maxY; j++) {
		double minDistance = Double.MAX_VALUE;
		for (var point : points) {
		    var distance = Math.abs(point.getX() - i) + Math.abs(point.getY() - j);
		    if (distance < minDistance) {
			minDistance = distance;
			coors[i][j] = point.getName();
		    }
		    if (distance == minDistance) {
			coors[i][j] = ".";
		    }
		}
	    }
	}

	List<String> allPoints = Arrays.stream(coors).flatMap(Arrays::stream).collect(Collectors.toList());

	List<String> edgeNames = Stream
	    .of(
		Arrays.stream(coors[0]),
		Arrays.stream(coors[maxX]),
		IntStream.range(0, maxY).mapToObj(i -> coors[i][maxY]),
		IntStream.range(0, maxY).mapToObj(i -> coors[i][0])
	    )
	    .flatMap(Function.identity())
	    .distinct()
	    .collect(Collectors.toList());

	return (int) points.stream()
	    .map(NamedPoint::getName)
	    .distinct()
	    .filter(name -> !edgeNames.contains(name))
	    .mapToLong(name -> allPoints.stream().filter(name::equals).count())
	    .max()
	    .orElseThrow();
    }

    @Override
    public Integer part2(List<String> data) {
	return null;
    }
}
