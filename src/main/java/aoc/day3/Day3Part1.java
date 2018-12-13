package aoc.day3;

import aoc.Solution;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3Part1 implements Solution<Long> {
    public Long solve(List<String> claimStrs){

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

}
