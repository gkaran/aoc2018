package aoc.day3;

import aoc.Solution;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 implements Solution<Long, Integer> {

    @Override
    public Long part1(List<String> data){
	List<Claim> claims = getClaims(data);

	int maxWidth = getMaxWidth(claims);
        int maxHeight = getMaxHeight(claims);

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

    @Override
    public Integer part2(List<String> data) {
	List<Claim> claims = getClaims(data);

	int maxWidth = getMaxWidth(claims);
	int maxHeight = getMaxHeight(claims);

	Cell[][] cells = IntStream
	    .range(0, maxWidth)
	    .mapToObj(i -> IntStream.range(0, maxHeight).mapToObj(j -> new Cell(i, j)).toArray(Cell[]::new))
	    .toArray(i -> new Cell[maxWidth][]);

	for (var claim : claims) {
	    for (var i = claim.getOffsetX(); i < claim.getOffsetX() + claim.getWidth(); i++) {
		for (var j = claim.getOffsetY(); j < claim.getOffsetY() + claim.getHeight(); j++) {
		    cells[i][j].addClaim(claim);
		}
	    }
	}

	Map<Claim, List<Cell>> claimCellMap = Arrays.stream(cells)
	    .flatMap(Arrays::stream)
	    .filter(Cell::hasSingleClaim)
	    .collect(Collectors.groupingBy(Cell::getSingleClaim));

	return claimCellMap.entrySet()
	    .stream()
	    .filter(entry -> entry.getValue().containsAll(entry.getKey().getCells()))
	    .findFirst()
	    .map(entry -> entry.getKey().getId())
	    .orElse(0);
    }

    private int getMaxHeight(List<Claim> claims) {
	return claims.stream().mapToInt(c -> c.getOffsetY() + c.getHeight()).max().orElse(0);
    }

    private int getMaxWidth(List<Claim> claims) {
	return claims.stream().mapToInt(c -> c.getOffsetX() + c.getWidth()).max().orElse(0);
    }

    private List<Claim> getClaims(List<String> data) {
	return data.stream()
	    .map(Claim::fromString)
	    .collect(Collectors.toList());
    }

}
