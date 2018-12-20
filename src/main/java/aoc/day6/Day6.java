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
	List<NamedPoint> points = getNamedPoints(data);
	String[][] coors = new String[getMaxX(points) + 1][getMaxY(points) + 1];

	for (var i = 0; i < coors.length; i++) {
	    for (var j = 0; j < coors[i].length; j++) {
		int minDistance = Integer.MAX_VALUE;
		for (var point : points) {
		    var distance = Math.abs(i - point.getX()) + Math.abs(j - point.getY());
		    if (distance < minDistance) {
			minDistance = distance;
			coors[i][j] = point.getName();
		    } else if (distance == minDistance) {
			coors[i][j] = ".";
		    }
		}
	    }
	}

	List<String> allPoints = Arrays.stream(coors).flatMap(Arrays::stream).collect(Collectors.toList());

	List<String> edgeNames = Stream
	    .of(
		Arrays.stream(coors[0]),
		Arrays.stream(coors[coors.length - 1]),
		IntStream.range(0, coors[0].length).mapToObj(i -> coors[i][coors[0].length - 1]),
		IntStream.range(0, coors[0].length).mapToObj(i -> coors[i][0])
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
	// Naive approach handling only a single section. Need to handle the case where multiple sections arise.
	List<NamedPoint> points = getNamedPoints(data);
	String[][] coors = new String[getMaxX(points) + 1][getMaxY(points) + 1];

	int sum = 0;

	for (var i = 0; i < coors.length; i++) {
	    for (var j = 0; j < coors[i].length; j++) {
		var totalDistance = 0;
		for (var point : points) {
		    totalDistance += Math.abs(i - point.getX()) + Math.abs(j - point.getY());
		}
		if (totalDistance < 10_000) {
		    coors[i][j] = "#";
		    sum++;
		} else {
		    coors[i][j] = ".";
		}
	    }
	    System.out.println(StringUtils.join(coors[i]));
	}

	return sum;
    }

    private int getMaxY(List<NamedPoint> points) {
	return points.stream().mapToInt(NamedPoint::getY).max().orElseThrow();
    }

    private int getMaxX(List<NamedPoint> points) {
	return points.stream().mapToInt(NamedPoint::getX).max().orElseThrow();
    }

    private List<NamedPoint> getNamedPoints(List<String> data) {
	return data.stream()
	    .map(point -> StringUtils.split(point.trim(), ", "))
	    .map(coords -> new NamedPoint(UUID.randomUUID().toString(), Integer.parseInt(coords[0]),
		Integer.parseInt(coords[1])))
	    .collect(Collectors.toList());
    }
}
