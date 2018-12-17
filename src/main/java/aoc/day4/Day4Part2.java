package aoc.day4;

import aoc.Solution;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Day4Part2 implements Solution<Integer> {
    @Override
    public Integer solve(List<String> data) {
	Map<Integer, Guard> guards = CommonUtils.parseGuardData(data);
	CommonUtils.printSleepLog(guards);

	Guard guard = guards.values().stream()
	    .max(Comparator.comparing(Guard::getMostSleepyMinuteSleepTime))
	    .orElseThrow();

	System.out.println("Guard id: " + guard.getId() + ", minute: " + guard.getMostSleepyMinute() + ", time: "
	    + guard.getMostSleepyMinuteSleepTime());

	return guard.getId() * guard.getMostSleepyMinute();
    }

}
