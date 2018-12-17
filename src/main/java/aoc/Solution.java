package aoc;

import java.util.List;

public interface Solution<R, T> {
    R part1(List<String> data);

    T part2(List<String> data);
}
