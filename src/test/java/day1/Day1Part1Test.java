package day1;

import com.sun.tools.javac.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day1Part1Test {
    private final Day1Part1 oracle = new Day1Part1();

    @Test
    public void assertTestCase1() {
	assertEquals(oracle.solve(List.of(+1, +1, +1)), 3);
    }

    @Test
    public void assertTestCase2() {
	assertEquals(oracle.solve(List.of(+1, +1, -2)), 0);
    }

    @Test
    public void assertTestCase3() {
	assertEquals(oracle.solve(List.of(-1, -2, -3)), -6);
    }

}