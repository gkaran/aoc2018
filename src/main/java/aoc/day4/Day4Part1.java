package aoc.day4;

import aoc.Solution;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Day4Part1 implements Solution<Integer> {
    @Override
    public Integer solve(List<String> data) {
	Map<Integer, Guard> guards = CommonUtils.parseGuardData(data);

	CommonUtils.printSleepLog(guards);

	Guard mostSleepyGuard = guards.values().stream()
	    .max(Comparator.comparing(Guard::getTotalSleepMinutes).thenComparingInt(Guard::getId))
	    .orElseThrow();

	System.out.println(
	    "Sleepy Guard id: " + mostSleepyGuard.getId() + ", minute: " + mostSleepyGuard.getMostSleepyMinute());

	return mostSleepyGuard.getId() * mostSleepyGuard.getMostSleepyMinute();
    }

}
