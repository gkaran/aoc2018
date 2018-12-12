package day3;

import helpers.InputLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3Part2 {
    int solve(List<String> claimStrs) {
        List<Claim> claims = claimStrs.stream()
                .map(Claim::fromString)
                .collect(Collectors.toList());

        int maxWidth = claims.stream().mapToInt(c -> c.getOffsetX() + c.getWidth()).max().orElse(0);
        int maxHeight = claims.stream().mapToInt(c -> c.getOffsetY() + c.getHeight()).max().orElse(0);

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
                .collect(Collectors.groupingBy(Cell::getSingleClain));

        return claimCellMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().containsAll(entry.getKey().getCells()))
                .findFirst()
                .map(entry -> entry.getKey().getId())
                .orElse(0);
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> codes = Files.lines(Paths.get(new InputLoader().getResource(3).toURI()))
                .collect(Collectors.toList());

        System.out.println(new Day3Part2().solve(codes));
    }
}