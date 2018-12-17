package aoc.day4;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Day4Part2Test {

    private final Day4Part2 oracle = new Day4Part2();

    @Test
    public void solve() {
	List<String> input = List.of(
	    "[1518-11-01 00:00] Guard #10 begins shift",
	    "[1518-11-01 00:05] falls asleep",
	    "[1518-11-01 00:25] wakes up",
	    "[1518-11-01 00:30] falls asleep",
	    "[1518-11-01 00:55] wakes up",
	    "[1518-11-01 23:58] Guard #99 begins shift",
	    "[1518-11-02 00:40] falls asleep",
	    "[1518-11-02 00:50] wakes up",
	    "[1518-11-03 00:05] Guard #10 begins shift",
	    "[1518-11-03 00:24] falls asleep",
	    "[1518-11-03 00:29] wakes up",
	    "[1518-11-04 00:02] Guard #99 begins shift",
	    "[1518-11-04 00:36] falls asleep",
	    "[1518-11-04 00:46] wakes up",
	    "[1518-11-05 00:03] Guard #99 begins shift",
	    "[1518-11-05 00:45] falls asleep",
	    "[1518-11-05 00:55] wakes up"
	);
	int expected = 4455; // 99 * 45

	assertEquals(expected, oracle.solve(input).intValue());
    }
}